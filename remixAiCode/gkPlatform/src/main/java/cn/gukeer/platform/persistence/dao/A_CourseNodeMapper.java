package cn.gukeer.platform.persistence.dao;

import cn.gukeer.platform.persistence.entity.CourseNode;
import cn.gukeer.platform.persistence.entity.CourseNodeInit;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by LL on 2017/5/25.
 */
public interface A_CourseNodeMapper {
    void batchSaveCourseNode(@Param("courseNodeList") List<CourseNode> courseNodeList);

    void batchSaveCourseNodeInit(@Param("courseNodeInitList")List<CourseNodeInit> courseNodeInitList);
}
