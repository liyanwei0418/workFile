package cn.gukeer.classcard.service;

import cn.gukeer.classcard.modelView.ClassCardNotifyView;
import cn.gukeer.classcard.persistence.entity.ClassCardNotify;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * Created by alpha on 17-7-5.
 */
public interface ClassCardNotifyService {
    int insertClassCardNotify(ClassCardNotify classCardNotify);

    PageInfo<ClassCardNotifyView> findAllNotify(String title, int type, String schoolId, int pageNum, int pageSize);

    public List<ClassCardNotifyView> transforNotifyView(List<ClassCardNotifyView> classCardNotifyViews);

    ClassCardNotify  findById(String id);

    int deleteClassCardNotify(String id[]);

    List<ClassCardNotifyView> findAllNotifyViewByClassCardId(String classCardId);

    ClassCardNotifyView transfornotifyDate(ClassCardNotifyView classCardNotifyView);

    PageInfo<ClassCardNotifyView> findById(String title, List<String> ids, int pageNum, int pageSize);


}
