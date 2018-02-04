package cn.gukeer.platform.persistence.dao;

import cn.gukeer.platform.modelView.ClassView;
import org.apache.ibatis.annotations.Param;

/**
 * Created by LL on 2017/4/19.
 */
public interface A_ClassViewMapper {

    ClassView findClassNameByXdAndClassId(@Param("classId")String classId, @Param("xd")String xd);
}
