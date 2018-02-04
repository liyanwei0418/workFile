package cn.gukeer.classcard.service.impl;

import cn.gukeer.classcard.modelView.ClassCardConfigScreenOffView;
import cn.gukeer.classcard.persistence.dao.A_ClassCardConfigMapper;
import cn.gukeer.classcard.persistence.dao.ClassCardConfigScreenOffRefMapper;
import cn.gukeer.classcard.persistence.entity.ClassCardConfigScreenOffRef;
import cn.gukeer.classcard.persistence.entity.ClassCardConfigScreenOffRefExample;
import cn.gukeer.classcard.service.ClassCardConfigScreenOffRefService;
import cn.gukeer.common.utils.DateUtils;
import cn.gukeer.common.utils.PrimaryKey;
import cn.gukeer.platform.persistence.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ClassCardConfigScreenOffRefServiceImpl implements ClassCardConfigScreenOffRefService {

    @Autowired
    ClassCardConfigScreenOffRefMapper classCardConfigScreenOffRefMapper;

    @Autowired
    A_ClassCardConfigMapper a_classCardConfigMapper;

    @Override
    public Integer insertClassCardConfigScreenRef(String refId, String screenOffWeek, HashMap<String, String> screenOffTimeList) {
        int count = 0;

        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();

        for (Map.Entry<String, String> entry: screenOffTimeList.entrySet()){

            Long startTimeDLong = null;
            Long endTimeLong = null;
            try {
                startTimeDLong = DateUtils.stringToTimestamp(entry.getKey(),"HH:mm");
                endTimeLong = DateUtils.stringToTimestamp(entry.getValue(), "HH:mm");
            } catch (Exception e) {
                e.printStackTrace();
                return -500;
            }

            ClassCardConfigScreenOffRef ccScreenOffRef = new ClassCardConfigScreenOffRef();
            ccScreenOffRef.setId(PrimaryKey.get());
            ccScreenOffRef.setClassCardConfigId(refId);
            ccScreenOffRef.setScreenOffWeek(screenOffWeek);
            ccScreenOffRef.setScreenOffStartTime(startTimeDLong);
            ccScreenOffRef.setScreenOffEndTime(endTimeLong);
            count += classCardConfigScreenOffRefMapper.insertSelective(ccScreenOffRef);
        }
        return count;
    }

    @Override
    public Integer deleteClassCardConfigScreenRef(String[] configIdArr) {
        int count = 0;
        ClassCardConfigScreenOffRefExample example = new ClassCardConfigScreenOffRefExample();
        for (String configId : configIdArr) {
            example.createCriteria().andClassCardConfigIdEqualTo(configId);
            count += classCardConfigScreenOffRefMapper.deleteByExample(example);
            example.clear();
        }
        return count;
    }

    @Override
    public Integer updateClassCardConfigScreenRef(String id, String screenOffWeek, HashMap<String, String> screenOffTimeList) {
        int count = 0;
        //修改配置的息屏时间段，先删除，然后重新插入。
        String[] configIdArr = {id};
        count += deleteClassCardConfigScreenRef(configIdArr);
        count += insertClassCardConfigScreenRef(id, screenOffWeek, screenOffTimeList);
        return count;
    }

    @Override
    public List<ClassCardConfigScreenOffView> findListClassCardScreenOffByCCCId(String classCardConfigId) {
        List<ClassCardConfigScreenOffView> cccsoViewList = a_classCardConfigMapper.findListClassCardScreenOffByCCCId(classCardConfigId);
        //转换日期
        List<ClassCardConfigScreenOffView> resultList = new ArrayList<>();
        String sfm = "HH:mm";
        if(cccsoViewList != null && cccsoViewList.size()>0) {
            for (ClassCardConfigScreenOffView cv: cccsoViewList) {
                cv.setsStartTimeView(DateUtils.timestampToString(cv.getScreenOffStartTime(),sfm));
                cv.setsEndTimeView(DateUtils.timestampToString(cv.getScreenOffEndTime(),sfm));
                resultList.add(cv);
            }
        }
        return resultList;
    }
}
