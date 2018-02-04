package cn.gukeer.classcard.service.impl;

import cn.gukeer.classcard.modelView.ClassCardConfigView;
import cn.gukeer.classcard.persistence.dao.A_ClassCardConfigMapper;
import cn.gukeer.classcard.persistence.dao.ClassCardConfigMapper;
import cn.gukeer.classcard.persistence.entity.ClassCardConfig;
import cn.gukeer.classcard.persistence.entity.ClassCardConfigExample;
import cn.gukeer.classcard.service.ClassCardConfigService;
import cn.gukeer.common.utils.DateUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClassCardConfigServiceImpl implements ClassCardConfigService {

    @Autowired
    ClassCardConfigMapper classCardConfigMapper;

    @Autowired
    A_ClassCardConfigMapper a_classCardConfigMapper;

    @Override
    public PageInfo<ClassCardConfigView> findAllConfigBySchoolId(String schoolId, int pageNum, int pageSize) {
        if(pageSize != -1) {
            PageHelper.startPage(pageNum, pageSize);
        }
        List<ClassCardConfigView> classCardConfigViewsList = a_classCardConfigMapper.findAllConfigViewBySchoolId(schoolId);
        PageInfo<ClassCardConfigView> pageInfo = new PageInfo<>(classCardConfigViewsList);
        return pageInfo;
    }

    @Override
    public PageInfo<ClassCardConfigView> findAllConfig(int pageNum, int pageSize) {
        if(pageSize != -1) {
            PageHelper.startPage(pageNum, pageSize);
        }
        List<ClassCardConfigView> classCardConfigViewsList = a_classCardConfigMapper.findAllConfigView();
        PageInfo<ClassCardConfigView> pageInfo = new PageInfo<>(classCardConfigViewsList);
        return pageInfo;
    }

    @Override
    public ClassCardConfigView findOneClassCardConfigById(String classCardConfigId) {

        if ( !"".equals(classCardConfigId) && classCardConfigId != null) {
            ClassCardConfigView classCardConfigView = a_classCardConfigMapper.findOneConfigViewById(classCardConfigId);
            return classCardConfigView;
        }
        return null;
    }

    //转换日期格式
    @Override
    public List<ClassCardConfigView> transforConfig(List<ClassCardConfigView> classCardConfigList) {
        List<ClassCardConfigView> resultList = new ArrayList<>();
        String nyr = "yyyy-MM-dd";
        String sfm = "HH:mm";
        if(classCardConfigList != null && classCardConfigList.size()>0) {
            for (ClassCardConfigView cv: classCardConfigList) {
                cv.setStartDateView(DateUtils.timestampToString(cv.getStartDate(),nyr));
                cv.setEndDateView(DateUtils.timestampToString(cv.getEndDate(),nyr));
                cv.setStartTimeView(DateUtils.timestampToString(cv.getStartTime(),sfm));
                cv.setEndTimeView(DateUtils.timestampToString(cv.getEndTime(),sfm));
                resultList.add(cv);
            }
        }
        return resultList;
    }
    //转换日期格式,单个实体转换
    @Override
    public ClassCardConfigView transforConfigOne(ClassCardConfigView cv) {
        String nyr = "yyyy-MM-dd";
        String sfm = "HH:mm";
        if(cv != null) {
            cv.setStartDateView(DateUtils.timestampToString(cv.getStartDate(),nyr));
            cv.setEndDateView(DateUtils.timestampToString(cv.getEndDate(),nyr));
            cv.setStartTimeView(DateUtils.timestampToString(cv.getStartTime(),sfm));
            cv.setEndTimeView(DateUtils.timestampToString(cv.getEndTime(),sfm));
        }
        return cv;
    }

    @Override
    public Integer insertClassCardNotify(ClassCardConfig classCardConfig) {
        int count = classCardConfigMapper.insertSelective(classCardConfig);
        return count;
    }

    @Override
    public Integer updateClassCardConfig(ClassCardConfig classCardConfig) {
        int count = 0;
        ClassCardConfigExample example = new ClassCardConfigExample();
        example.createCriteria().andIdEqualTo(classCardConfig.getId());
        count += classCardConfigMapper.updateByExampleSelective (classCardConfig, example);
        return count;
    }

    @Override
    public Integer deleteClassCardConfig(String[] configIdArr) {
        int count = 0;
        ClassCardConfigExample example = new ClassCardConfigExample();
        for (String configId : configIdArr) {
            example.createCriteria().andIdEqualTo(configId);
            count += classCardConfigMapper.deleteByExample(example);
            example.clear();
        }
        return count;
    }
}
