package cn.gukeer.platform.service;


import cn.gukeer.platform.modelView.SchoolView;
import cn.gukeer.platform.persistence.entity.ClassSection;
import cn.gukeer.platform.persistence.entity.GradeClass;
import cn.gukeer.platform.persistence.entity.Teacher;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

public interface ClassService {

    PageInfo<GradeClass> selectClassBySchoolIdAndNj(String schoolId, String xq, int nj, int pageNum, int pageSize, String xd);

    int saveGradeClass(GradeClass gradeClass);

    int changeDelFlag(String id);

    ClassSection selectClassSectionById(String sectionId);

    GradeClass selectClassById(String id);

    List<GradeClass> selectClassByXdAndSchoolId(String xd, String schoolId);

    int saveClassSection(ClassSection section);

    List<GradeClass> getAllClassBySchoolId(String schoolId);

    List<ClassSection> getAllClassSectionBySchoolId(String schoolId);

    List<GradeClass> selectClassBySchoolIdXdNj(String schoolId, String xd, int nj);

    List<GradeClass> getClassBySchoolType(String schoolId, String schoolType);

    /**
     *
     * @param schoolId
     * @param judge
     * @param showOther 为true时班级树最下方显示其他
     * @return
     */
    SchoolView selectAndMakeTree(String schoolId, String[] judge,boolean showOther);

    int saveClassAndStudent(String classId, String xd, int nj);

    SchoolView makeClassTree(String schoolId, String[] judge);

    PageInfo<ClassSection> getSectionContainDefault(String schoolId, int pageNum, int pageSize);

    List<Teacher> getTeacherByClassId(String classId, int type);

    int saveTeacherClass(String teahcerid, String classId, int type);

    int delTeacherClass(String teahcerid, String classId, int type);

    int batchInsertGradeClass(List<GradeClass> list);

    PageInfo<Map> parentInfoList(Map param);

    int deleteParent(String prim);

    Map selectParentByPrim(String prim);

    //区级学生信息
    PageInfo<Map> getAreaStuList(Map param);


}
