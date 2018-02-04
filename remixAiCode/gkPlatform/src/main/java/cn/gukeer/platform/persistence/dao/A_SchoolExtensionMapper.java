package cn.gukeer.platform.persistence.dao;


import cn.gukeer.platform.persistence.entity.School;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by conn on 2016/8/19.
 */
public interface A_SchoolExtensionMapper {

    List<Map<String, Object>> selectSchoolViewById(Map<Object, Object> paramMap);

    int insertSchoolBackId(School school);

    List<School> getSonSchoolList(@Param("schoolId") String schoolId);

}
