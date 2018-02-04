package cn.gukeer.platform.service;

import cn.gukeer.platform.modelView.StudentView;
import cn.gukeer.platform.persistence.entity.Patriarch;
import cn.gukeer.platform.persistence.entity.School;
import cn.gukeer.platform.persistence.entity.Student;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * Created by conn on 2016/8/23.
 */
public interface StudentService {

    PageInfo<StudentView> selectStudentByChoose(String schoolId, String classId, String xd, int nj, int xq, int status, String name, int pageNum, int pageSize);

    int save(Student student);

    Student selectStudentById(String id);

    String saveExtension(Student student);

    int changeDelFlag(String id);

    List<Student> selectStudentByclassId(String schoolId, String classId);

    Map<Object, Object> getStudentList(Map<String, String> param, boolean flag, String schoolId, Integer sf);

    List<Student> findNoAccountStudent(String schoolId);

    List<Patriarch> findNoAccountParent(String schoolId);

    Integer batchInsertStudent(List<Student> studentList);

    Map findStudentByStuNum(String schoolId, String stuNum);

    List<StudentView> selectBatchStudents(List<String> stuIdList, String schoolId);

    List<Map> genderReport(List<School> schoolList);//学段性别比例柱状图

    List<Map> personCountReport(List<School> schoolList);//班级人数情况折线图

    Map lydqReport(List<School> schoolList);//来源地区饼状图
}
