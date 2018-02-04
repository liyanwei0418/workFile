package cn.gukeer.classcard.persistence.dao;

import cn.gukeer.classcard.modelView.ClassCardNotifyView;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by alpha on 17-7-6.
 */
public interface A_ClassCardNotifyMapper {
    //通知公告首页
    List<ClassCardNotifyView> findAllNotifyView(@Param("title") String title, @Param("type") int type, @Param("schoolId") String schoolId);

    //通知公告接口
    List<ClassCardNotifyView> findAllNotifyViewByClassCardId(String classCardId);

    //根据通知id和标题查询view
    List<ClassCardNotifyView> findById(@Param("title") String title, @Param("list") List<String> list);
}
