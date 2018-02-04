package cn.gukeer.classcard.service.impl;

import cn.gukeer.classcard.modelView.ClassCardNotifyView;
import cn.gukeer.classcard.persistence.dao.A_ClassCardNotifyMapper;
import cn.gukeer.classcard.persistence.dao.ClassCardNotifyMapper;
import cn.gukeer.classcard.persistence.entity.ClassCardNotify;
import cn.gukeer.classcard.persistence.entity.ClassCardNotifyExample;
import cn.gukeer.classcard.service.ClassCardNotifyService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by alpha on 17-7-5.
 */
@Service
public class ClassCardNotifyServiceImpl implements ClassCardNotifyService {

    @Autowired
    ClassCardNotifyMapper classCardNotifyMapper;

    @Autowired
    A_ClassCardNotifyMapper a_classCardNotifyMapper;

    @Override
    public int insertClassCardNotify(ClassCardNotify classCardNotify) {
        int count = classCardNotifyMapper.insertSelective(classCardNotify);
        return count;
    }

    @Override
    public PageInfo<ClassCardNotifyView> findAllNotify(String title, int type, String schoolId , int pageNum, int pageSize) {
        if (pageSize != -1) {
            PageHelper.startPage(pageNum, pageSize);
        }
        List<ClassCardNotifyView> classCardNotifyViews = a_classCardNotifyMapper.findAllNotifyView(title, type,schoolId);
        PageInfo<ClassCardNotifyView> pageInfo = new PageInfo<>(classCardNotifyViews);
        return pageInfo;
    }


    @Override
    public List<ClassCardNotifyView> transforNotifyView(List<ClassCardNotifyView> classCardNotifyViews) {
        List<ClassCardNotifyView> resultList = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (classCardNotifyViews != null && classCardNotifyViews.size() > 0) {
            for (ClassCardNotifyView cv : classCardNotifyViews) {
                Date date = new Date(cv.getCreateDate());
                cv.setPublishTime(sdf.format(date));
                resultList.add(cv);
            }
        }
        return resultList;
    }

    @Override
    public ClassCardNotify findById(String id) {
        ClassCardNotify classCardNotify = classCardNotifyMapper.selectByPrimaryKey(id);
        return classCardNotify;
    }

    @Override
    public int deleteClassCardNotify(String id[]) {
        ClassCardNotifyExample example = new ClassCardNotifyExample();
        example.createCriteria().andIdIn(Arrays.asList(id));
        int count = classCardNotifyMapper.deleteByExample(example);
        return count;
    }

    @Override
    public List<ClassCardNotifyView> findAllNotifyViewByClassCardId(String classCardId) {
        List<ClassCardNotifyView> classCardNotifyViews = a_classCardNotifyMapper.findAllNotifyViewByClassCardId(classCardId);
        return classCardNotifyViews;
    }

    @Override
    public ClassCardNotifyView transfornotifyDate(ClassCardNotifyView classCardNotifyView) {

        ClassCardNotifyView cv = classCardNotifyView;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf2 = new SimpleDateFormat("MM-dd");
        SimpleDateFormat sdf3 = new SimpleDateFormat("HH:mm");
        Date today = new Date();
        Date cDate = new Date(cv.getCreateDate());
        if (sdf.format(today).equals(sdf.format(cDate))) {
            cv.setPublishTime(sdf3.format(cv.getCreateDate()));
        } else {
            cv.setPublishTime(sdf2.format(cv.getCreateDate()));
        }
        return cv;
    }

    @Override
    public PageInfo<ClassCardNotifyView> findById(String title,List<String> ids,int pageNum,int pageSize) {
        if (pageSize != -1) {
            PageHelper.startPage(pageNum, pageSize);
        }
        List<ClassCardNotifyView> views = new ArrayList<>();
        if (ids != null && ids.size() > 0) {
            views = a_classCardNotifyMapper.findById(title,ids);
        }
        PageInfo<ClassCardNotifyView> pageInfo = new PageInfo<>(views);
        return pageInfo;
    }
}