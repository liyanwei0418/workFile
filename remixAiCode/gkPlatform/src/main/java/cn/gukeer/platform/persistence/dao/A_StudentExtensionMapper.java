package cn.gukeer.platform.persistence.dao;

import cn.gukeer.platform.modelView.StudentView;
import cn.gukeer.platform.persistence.entity.Patriarch;
import cn.gukeer.platform.persistence.entity.School;
import cn.gukeer.platform.persistence.entity.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by conn on 2016/8/19.
 */
public interface A_StudentExtensionMapper {

    List<StudentView> selectByClassIdAndName(@Param("classId") String classId, @Param("schoolId") String schoolId, @Param("xd") String xd,
                                             @Param("nj") int nj, @Param("xq") int xq, @Param("status") int status, @Param("name") String name);

    int changeDelFlag(String id);

    int insertStudentBatch(List<Student> studentList);

    List<Map> findStudentByStuNum(@Param("schoolId") String schoolId, @Param("stuNum") String stuNum);

    int batchInsetPatriarch(List<Patriarch> patriarchList);

    List<StudentView> selectBatchStudents(@Param("idList") List<String> stuIds, @Param("schoolId") String schoolId);

    List<Map> getParList(Map map);

    List<Map> getAreaStuList(Map map);

    //学段性别比例柱状图
    List<Map> genderReport(@Param("schoolList") List<School> schoolList);

    //班级人数情况折线图
    List<Map> personCountReport(@Param("schoolList") List<School> schoolList, @Param("size") int size);

    //来源地区饼状图
    List<Map> lydqReport(@Param("schoolList") List<School> schoolList);

}
