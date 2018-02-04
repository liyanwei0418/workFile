package cn.gukeer.platform.service;

import cn.gukeer.platform.persistence.entity.*;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * Created by conn on 2016/8/8.
 */
public interface TeacherService {

    PageInfo<Teacher> findAllList(int pageNum, int pageSize, String schoolId, String teacherName);

    List<Teacher> findAllTeacher(String schoolId);

    Teacher findTeacherById(String id);

    //职务id，需要查询的机构id，当前机构的id，职工名字
    List<Teacher> findTeacherByTitleId(String titleId, String schoolId, String loginSchoolId, String teacherName);

    PageInfo<Map> findTeacherViewByTitleId(String titleId, String schoolId, String loginSchoolId,
                                           String teacherName, Integer pageNum, Integer pageSize);

    List<Map<String, Object>> findTeacherViewByParams(Map<Object, Object> param);

    int updateTeacher(Teacher teacher);

    int updateTeacherAllFiled(Teacher teacher);

    int updateTeacherByCriteria(Teacher teacher, List<String> departmentIds, String schoolId);

    int save(Teacher teacher);

    int saveTeacherBackId(Teacher teacher);

    int insertTeacher(Teacher teacher);

    PageInfo<Teacher> findListByParam(String schoolId, String departmentId, String name, int pageNum, int pageSize);

    List<Department> findDepartmentBySchool(String schoolId);

    List<Title> findTitleList();

    List<Title> selectTitleBySchoolId(String schoolId);

    Title selectTitleById(String id);

    int saveTitle(Title title);

    int updateTitle(Title title);

    List<Teacher> findNoAccountTeacher(String schoolId);

    List<Teacher> findHaveAccountTeacher(String schoolId);

    PageInfo<Map> findTeacherViewList(String departmentId, Teacher teacher, int pageNum, int pageSize);

    PageInfo<Teacher> findTeacherByDepartmentId(String departmentId, Teacher teacher, int pageNum, int pageSize);

    Map<Object, Object> getTeacherList(Map<String, String> param, boolean flag, String schoolId);

    List<Teacher> findTeacherLikeNameAndSchoolId(String name, String schoolId);

    int insertTeacherBatch(List<Teacher> teacherList);

    List<Teacher> selectTeacherNameLike(String name, String schoolId);

    List<Teacher> selectBatchTeachers(List<String> teacherList, String schoolId);

    PageInfo<Map> teacherListView(int pageNum, int pageSize, String currentSchoolId, String schoolId, String teacherName);

    //区平台id，机构id集合
    Map teacherReport(String loginSchoolId, List<School> schoolList);

    Course findCourseById(String courseId);

    List<Course> findAllCourseBySchoolId(String schoolId);

    int batchUpdateTeacher(List<String> teacherIdList, Teacher teacher);

}
