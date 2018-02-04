package cn.gukeer.sync.service.push;

import cn.gukeer.common.tld.GukeerStringUtil;
import cn.gukeer.common.utils.LineToCameUtil;
import cn.gukeer.common.utils.PropertiesUtil;
import cn.gukeer.common.utils.StringUtils;
import cn.gukeer.common.utils.syncdata.MD5Util;
import cn.gukeer.platform.persistence.dao.A_TeachTableMapper;
import cn.gukeer.platform.persistence.dao.TeachCycleMapper;
import cn.gukeer.platform.persistence.entity.TeachCycle;
import cn.gukeer.platform.persistence.entity.TeachCycleExample;
import cn.gukeer.sync.dataDefinition.EventData;
import cn.gukeer.sync.dataDefinition.EventType;
import cn.gukeer.sync.persistence.dao.SyncBaseMapper;
import com.google.gson.Gson;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessagePostProcessor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.DeliveryMode;
import javax.jms.JMSException;
import javax.jms.Message;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by lx on 2017/2/7.
 */
@Service
public class SyncMian {
    @Autowired
    SyncBaseMapper syncBaseMapper;

    @Autowired
    TeachCycleMapper teachCycleMapper;

    @Autowired
    A_TeachTableMapper teachTableMapper;

    //入口方法
    //需要加事务
    public Map<String, String> execute() {
        //schema必须配置正确
        Properties properties = PropertiesUtil.getProperties("/syncData.properties");
        //数据库名称
        String schema = properties.getProperty("sync.jdbc.schema");
        //生成表前缀
        String _tableNamePrefix = properties.getProperty("sync.mark.table.prefix");
        //教务表特殊推送
        String specialNamePrefix = properties.getProperty("sync.mark.table.teach");

        //订阅该数据的系统的唯一标识
        String _mark = properties.getProperty("sync.mark.plat.id");
        String mark = _mark + ",";
        String notLikeMark = "%" + _mark + ",%";
        //同步表查询条件
        String tableNamePrefix = _tableNamePrefix + "%";
        List<String> tableNames = getTableNames(schema, tableNamePrefix, specialNamePrefix + "%");
        boolean flag = true;
        for (String tableName : tableNames) {

            //查询数据
            List<Map<String, Object>> datas = getDatas(tableName, notLikeMark);
            if (datas.size() > 0)
                flag = false;
            //获取数据的id集合
            List<String> ids = new ArrayList<>();
            for (Map<String, Object> data : datas) {
                ids.add(data.get("id").toString());
            }

            if (datas.size() == 0) {
                continue;
            }
            //序列化数据
            Map<String, Object> datasJson = formatDatas(datas, tableName);

            //推送数据
            try {
                if (!datasJson.get("insert").equals("false")) {
                    pushdata(datasJson.get("insert"));
                }
                if (!datasJson.get("modify").equals("false")) {
                    pushdata(datasJson.get("modify"));
                }
                if (!datasJson.get("delete").equals("false")) {
                    pushdata(datasJson.get("delete"));
                }
            } catch (Exception e) {
                //如果失败，则不标记同步过的数据，直接返回
                e.printStackTrace();
                break;
            }
            //标记已经同步过的数据
            try {
                if (tableName.equals("v_teach_teach_class") || tableName.equals("v_teach_course_manage"))
                    identityDeletion("sync_teach_ref_course_class", mark, ids, notLikeMark);
                else
                    identityDeletion(tableName, mark, ids, notLikeMark);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (flag)
            System.out.println("数据已经全部同步完毕数据已经全部同步完毕数据已经全部同步完毕数据已经全部同步完毕" +
                    "数据已经全部同步完毕数据已经全部同步完毕数据已经全部同步完毕数据已经全部同步完毕数据已经全部同步完毕" +
                    "数据已经全部同步完毕数据已经全部同步完毕数据已经全部同步完毕数据已经全部同步完毕" +
                    "数据已经全部同步完毕数据已经全部同步完毕数据已经全部同步完毕");
        return null;
    }

    //获取需要同步的表名
    public List<String> getTableNames(String schema, String tableNamePrefix, String special) {

        //获取需要同步的表   sync_开头但是不是sync_teach开头的表
        List<String> tableNames = syncBaseMapper.getTableNames(schema, tableNamePrefix, special, "sync_teacher%");

        //获取教务相关的视图
        tableNames.add("v_teach_cycle"); //教学周期信息表
        tableNames.add("v_teach_class_room"); //教室信息表
        tableNames.add("v_teach_room_type");//教室类型表
        tableNames.add("v_teach_course");//课程表
        tableNames.add("v_teach_course_type");//科目字典基本信息表
        tableNames.add("v_teach_standard_course");//标准课程信息表
        tableNames.add("v_teach_daily_hour");//班级课时表
        tableNames.add("sync_teach_course_node");
        tableNames.add("sync_teach_course_node_init");
        tableNames.add("v_teach_teacher_course_manage");
        tableNames.add("v_teach_table");

        //tableNames.add("v_teach_course_manage"); //课程管理信息表
        //tableNames.add("v_teach_teach_class");//教师授课安排信息表

        return tableNames;
    }

    //根据表名获取数据
    public List<Map<String, Object>> getDatas(String tableName, String notLikeMark) {

        //查询每个学校最新的教学周期
        List<Map> res = teachTableMapper.findLatestCycleGroupSchoolId();

        Map<Object, Map> latestCycleMap = new HashMap();
        for (Map map : res) {
            latestCycleMap.put(map.get("school_id"), map);
        }

        List<Map<String, Object>> _datas = syncBaseMapper.getDatas(tableName, notLikeMark);
        List<Map<String, Object>> datas = new ArrayList<>();
        for (Map<String, Object> data : _datas) {
            Map<String, Object> columnMap = new HashMap<>();
            //一条数据的所有key值
            Set<String> keySet = data.keySet();
            Iterator<String> keyIterator = keySet.iterator();
            while (keyIterator.hasNext()) {
                String _column = keyIterator.next();
                //列名转驼峰结构
                String column = LineToCameUtil.underlineToCamel2(_column);
                //过滤account为空的字段
                if (tableName.equals("sync_teacher") || tableName.equals("sync_student") || tableName.equals("sync_patriarch")) {
                    if (column.equals("account")) {
                        Object account = data.get("account");
                        if (GukeerStringUtil.isNullOrEmpty(account))
                            continue;
                        if (StringUtils.isNotEmpty(account.toString())) {
                            columnMap.put("passAccount", "yes");
                        }
                    }
                }
                //只发送最新学期的课表数据,周期和当前学校最新周期不相同的数据不推送
                if (tableName.equals("v_teach_table")) {
                    Map map = latestCycleMap.get(data.get("school_id"));
                    if (null == map || GukeerStringUtil.isNullOrEmpty(data.get("cycle_id")))
                        continue;
                    else if (!data.get("cycle_id").equals(map.get("id")))
                        continue;
                }

                if (column.equals("syncDate")) {
                    Timestamp _dataLong = (Timestamp) data.get(_column);
                    Long dataLong = _dataLong.getTime();
                    columnMap.put(column, dataLong + "");
                } else {
                    columnMap.put(column, data.get(_column));
                }
            }
            if (tableName.equals("sync_teacher") || tableName.equals("sync_student") || tableName.equals("sync_patriarch")) {
                if (columnMap.get("passAccount") != null) {
                    datas.add(columnMap);
                }
            } else {
                if (!GukeerStringUtil.isNullOrEmpty(columnMap))
                    datas.add(columnMap);
            }
        }
        return datas;
    }

    //序列化数据
    public Map<String, Object> formatDatas(List<Map<String, Object>> datas, String tableName) {
        //封装插入数据
        List<Map<String, Object>> insertDatas = new ArrayList<>();
        //封装修改数据
        List<Map<String, Object>> modifyDatas = new ArrayList<>();
        //封装删除数据
        List<Map<String, Object>> deleteDatas = new ArrayList<>();

        //将datas按照事件拆分为三种不同的list
        for (Map<String, Object> data : datas) {
            if (data.get("event").equals(EventType.INSERT.name())) {
                insertDatas.add(data);
            }
            if (data.get("event").equals(EventType.MODIFY.name())) {
                modifyDatas.add(data);
            }
            if (data.get("event").equals(EventType.DELETE.name())) {
                deleteDatas.add(data);
            }
        }
        Map<String, Object> datasJson = new HashMap<>();
        //封装插入数据
        if (insertDatas.size() != 0) {
            EventData insertEventData = new EventData();
            insertEventData.setDataList(insertDatas);
            insertEventData.setObjectKey(tableName);
            insertEventData.setEvent(EventType.INSERT);
            String insertDatasJson = eventDataToJson(insertEventData);
            datasJson.put("insert", insertDatasJson);
        } else {
            datasJson.put("insert", "false");
        }
        //封装修改数据
        if (modifyDatas.size() != 0) {
            EventData modifyEventData = new EventData();
            modifyEventData.setDataList(modifyDatas);
            modifyEventData.setObjectKey(tableName);
            modifyEventData.setEvent(EventType.MODIFY);
            String modifyDatasJson = eventDataToJson(modifyEventData);
            datasJson.put("modify", modifyDatasJson);
        } else {
            datasJson.put("modify", "false");
        }

        // 封装删除数据
        if (deleteDatas.size() != 0) {
            EventData deleteEventData = new EventData();
            deleteEventData.setDataList(deleteDatas);
            deleteEventData.setObjectKey(tableName);
            deleteEventData.setEvent(EventType.DELETE);
            String deleteDatasJson = eventDataToJson(deleteEventData);
            datasJson.put("delete", deleteDatasJson);
        } else {
            datasJson.put("delete", "false");
        }
        return datasJson;
    }

    //转json
    public String eventDataToJson(EventData eventData) {
       /* Gson gson = new GsonBuilder().disableHtmlEscaping().create();*/
        Gson gson = new Gson();
        String datasJson = gson.toJson(eventData);
        return datasJson;
    }

    //标记已经同步过的数据
    public void identityDeletion(String tableName, String mark, List<String> ids, String notLikeMark) {

        if (tableName.equals("v_teach_teacher_course_manage"))
            tableName = "sync_teach_ref_course_class";//多表联查的试图不支持更新，更新操作需要更改为更新实表
        if (tableName.equals("v_teach_table"))
            tableName = "sync_teach_table";

        syncBaseMapper.identityDeletion(tableName, mark, ids, notLikeMark);
    }

    //转化时间
    public String makeTime(Long time) {
        String format = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat fmt = new SimpleDateFormat(format);
        String timeString = fmt.format(time);
        return timeString;
    }

    //推送数据
    public String pushdata(Object messageContent) {
        Properties prop = PropertiesUtil.getProperties("/syncData.properties");
        String AGENT = prop.getProperty("agent");
        String ACCESSKEY = prop.getProperty("accesskey");
        String property = "userId:1";
        Map<String, Object> generateSignParams = new HashMap<String, Object>();
        generateSignParams.put("agent", AGENT);
        generateSignParams.put("accesskey", ACCESSKEY);
        generateSignParams.put("rand", RandomStringUtils.randomAlphanumeric(8));
        generateSignParams.put("time", new Date().getTime());
        generateSignParams.put("data", messageContent);

        //生成签名
        String sign = MD5Util.go(AGENT + ACCESSKEY + generateSignParams.get("rand").toString() + messageContent.toString() + generateSignParams.get("time").toString());
        System.out.println("签名是+++++++++++" + sign);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("rand", generateSignParams.get("rand"));
        params.put("sign", sign);
        params.put("time", generateSignParams.get("time"));
        params.put("property", property);
        params.put("agent", generateSignParams.get("agent"));
        params.put("datas", messageContent);
        send(params, prop.getProperty("sync.queue.name"));
        return null;
    }

    @Resource(name = "jmsTemplate")
    private JmsTemplate jmsTemplate;

    public void send(final Map<String, Object> message, String queuesName) {

        jmsTemplate.convertAndSend(queuesName, message, new MessagePostProcessor() {

            @Override
            public Message postProcessMessage(Message message) throws JMSException {
                message.setJMSDeliveryMode(DeliveryMode.PERSISTENT);
                message.setJMSCorrelationID("gkpltaform");
                return message;
            }
        });
    }


    public List<TeachCycle> getCycleList(String schoolId) {

        TeachCycleExample example = new TeachCycleExample();
        example.setOrderByClause("term_begin_time desc");
        example.createCriteria().andSchoolIdEqualTo(schoolId).andDelFlagEqualTo(0);

        return teachCycleMapper.selectByExample(example);
    }

}
