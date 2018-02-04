package cn.gukeer.sync.service.pull;

import cn.gukeer.common.utils.NumberConvertUtil;
import cn.gukeer.platform.persistence.dao.A_TeachTableMapper;
import cn.gukeer.platform.persistence.dao.TeachTableMapper;
import cn.gukeer.platform.persistence.entity.TeachTable;
import cn.gukeer.platform.persistence.entity.TeachTableExample;
import cn.gukeer.sync.dataDefinition.EventData;
import cn.gukeer.sync.dataDefinition.EventType;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.listener.SessionAwareMessageListener;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class QueueMessageListener implements SessionAwareMessageListener, Serializable {

    @Autowired
    TeachTableMapper teachTableMapper;

    A_TeachTableMapper a_teachTableMapper;


    @Override
    public void onMessage(Message msg, Session session) throws JMSException {
        try {
            TextMessage message = (TextMessage) msg;
            if (message == null) {
                return;
            }
            String content = null;
            try {
                content = message.getText();
            } catch (JMSException e) {
                return;
            }
            if (StringUtils.isEmpty(content)) {
                System.out.println("消息为空，忽略！");
                return;
            }
            //content
            List<EventData<Map<String, Object>>> eventDatas = JSONObject.parseObject(content, new TypeReference<List<EventData<Map<String, Object>>>>() {
            });

            List<TeachTable> insertTableList = new ArrayList<>();
            List<TeachTable> updateTableList = new ArrayList<>();
            List<TeachTable> deleteTableList = new ArrayList<>();
            for (EventData<Map<String, Object>> eventData : eventDatas) {
                System.out.println("数据标识：" + eventData.getObjectKey());
                System.out.println("操作类型：" + eventData.getEvent());
                System.out.println("数据列表：" + eventData.getDataList());
                //TODO 此处实现具体的业务代码

                List<Map<String, Object>> dataList = eventData.getDataList();
                EventType event = eventData.getEvent();
                for (Map data : dataList) {
                    TeachTable table = new TeachTable();
                    table.setId(nullTranslate(data.get("id")));//课表id作为主键
                    table.setClassId(nullTranslate(data.get("classId")));
                    table.setCourseId(nullTranslate(data.get("courseId")));
                    table.setTeacherId(nullTranslate(data.get("teacherId")));
                    table.setTableId(nullTranslate(data.get("tableId")));
                    table.setClassRoomId(nullTranslate(data.get("classRoomId")));
                    table.setWeekend(NumberConvertUtil.convertS2I(nullTranslate(data.get("weekend"))));
                    table.setSchoolId(nullTranslate(data.get("schoolId")));

                    if (EventType.INSERT.equals(event))
                        insertTableList.add(table);
                    if (EventType.MODIFY.equals(event))
                        updateTableList.add(table);
                    if (EventType.DELETE.equals(event))
                        deleteTableList.add(table);
                }

                //批量插入
                if (insertTableList.size() > 0)
                    a_teachTableMapper.batchInsertTeachTable(insertTableList);

                //批量更新
                for (TeachTable table : updateTableList) {
                    teachTableMapper.updateByPrimaryKeySelective(table);
                }

                //批量删除
                if (deleteTableList.size() > 0) {
                    List<String> idList = new ArrayList<>();
                    for (TeachTable table : deleteTableList) {
                        idList.add(table.getId());
                    }
                    TeachTableExample example = new TeachTableExample();
                    example.createCriteria().andIdIn(idList);
                    teachTableMapper.deleteByExample(example);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String nullTranslate(Object object) {
        if (StringUtils.isEmpty(object))
            return null;
        else
            return object.toString();
    }

}

