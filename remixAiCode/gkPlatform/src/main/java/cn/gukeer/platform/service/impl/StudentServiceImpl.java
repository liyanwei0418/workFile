package cn.gukeer.platform.service.impl;

import cn.gukeer.common.service.BasicService;
import cn.gukeer.common.utils.NumberConvertUtil;
import cn.gukeer.common.utils.PrimaryKey;
import cn.gukeer.platform.modelView.StudentView;
import cn.gukeer.platform.persistence.dao.A_StudentExtensionMapper;
import cn.gukeer.platform.persistence.dao.PatriarchMapper;
import cn.gukeer.platform.persistence.dao.StudentMapper;
import cn.gukeer.platform.persistence.entity.*;
import cn.gukeer.platform.service.StudentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by conn on 2016/8/23.
 */
@Service
public class StudentServiceImpl extends BasicService implements StudentService {

    @Autowired
    StudentMapper studentMapper;

    @Autowired
    PatriarchMapper patriarchMapper;

    @Autowired
    A_StudentExtensionMapper extensionMapper;

    @Override
    public PageInfo<StudentView> selectStudentByChoose(String schoolId, String classId, String xd, int nj, int xq, int status, String name, int pageNum, int pageSize) {

        name = "%" + name + "%";
        if (pageSize != -1) {
            PageHelper.startPage(pageNum, pageSize);
        }
        List<StudentView> list = extensionMapper.selectByClassIdAndName(classId, schoolId, xd, nj, xq, status, name);
        PageInfo<StudentView> pageInfo = new PageInfo<StudentView>(list);
        return pageInfo;
    }

    @Override
    public int changeDelFlag(String id) {
        int count = 0;

        if (null != id && id != "")
            count = extensionMapper.changeDelFlag(id);
        return count;
    }

    @Override
    public int save(Student student) {

        int count = 0;
        if (null != student) {
            if (null != student.getId() && student.getId() != "") {
                StudentExample example = new StudentExample();
                example.createCriteria().andIdEqualTo(student.getId());
                count = studentMapper.updateByExampleSelective(student, example);
            } else {

                student.setId(PrimaryKey.get());
                count = studentMapper.insertSelective(student);
            }
        }

        return count;
    }

    @Override
    public Student selectStudentById(String id) {

        Student student = studentMapper.selectByPrimaryKey(id);
        return student;
    }

    @Override
    public String saveExtension(Student student) {

        String id = "";
        if (null != student) {
            if (StringUtil.isNotEmpty(student.getId())) {
                StudentExample example = new StudentExample();
                example.createCriteria().andIdEqualTo(student.getId());
                studentMapper.updateByExampleSelective(student, example);
                id = student.getId();
            } else {
                String pri = PrimaryKey.get();
                student.setId(pri);
                studentMapper.insertSelective(student);
                id = pri;//student.getId();
            }
        }

        return id;
    }

    @Override
    public List<Student> selectStudentByclassId(String schoolId, String classId) {
        StudentExample example = new StudentExample();
        StudentExample.Criteria criteria = example.createCriteria();
        if (!StringUtils.isEmpty(classId)) {
            criteria.andClassIdEqualTo(classId);
        }
        criteria.andSchoolIdEqualTo(schoolId).andDelFlagEqualTo(0);
        example.setOrderByClause("xh");
        List<Student> studentList = studentMapper.selectByExample(example);
        return studentList;
    }

    @Override
    public Map<Object, Object> getStudentList(Map<String, String> param, boolean flag, String schoolId, Integer sf) {
        String studentname = param.get("studentname");
        try {
            studentname = java.net.URLDecoder.decode(studentname, "UTF-8");//解决非post访问的中文乱码问题。
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        Map<Object, Object> map = new HashMap<Object, Object>();
        StudentExample example = new StudentExample();
        StudentExample.Criteria criteria = example.createCriteria();
        criteria.andSchoolIdEqualTo(schoolId).andDelFlagEqualTo(0);
        if (!StringUtils.isEmpty(studentname)) {
            criteria.andXsxmLike("%" + studentname + "%");
        }
        int pageSize = 0;
        int pageNum = 0;
        if (flag) {
            pageSize = NumberConvertUtil.convertS2I(param.get("pageSizeHave")) == 0 ? 10 : NumberConvertUtil.convertS2I(param.get("pageSizeHave"));
            pageNum = NumberConvertUtil.convertS2I(param.get("pageNumHave"));
           /* if (sf == 1) criteria.andStudentAccountIsNotNull();
            if (sf == 2) criteria.andPatriachAccountIsNotNull();*/
            if (sf == 1) criteria.andAccountIsNotNull();
        } else {
            pageSize = NumberConvertUtil.convertS2I(param.get("pageSizeNo")) == 0 ? 10 : NumberConvertUtil.convertS2I(param.get("pageSizeNo"));
            pageNum = NumberConvertUtil.convertS2I(param.get("pageNumNo"));
           /* if (sf == 1) criteria.andStudentAccountIsNull();
            if (sf == 2) criteria.andPatriachAccountIsNull();*/
            if (sf == 1) criteria.andAccountIsNull();
        }
        PageHelper.startPage(pageNum, pageSize);
        List<Student> studentlist = studentMapper.selectByExample(example);
        PageInfo<Student> pageInfo = new PageInfo<Student>(studentlist);
        map.put("studentlist", studentlist);
        map.put("pageInfo", pageInfo);

        return map;
    }

    @Override
    public List<Student> findNoAccountStudent(String schoolId) {
        StudentExample example = new StudentExample();
        //example.createCriteria().andStudentAccountIsNull().andSchoolIdEqualTo(schoolId);
        example.createCriteria().andAccountIsNull().andSchoolIdEqualTo(schoolId).andDelFlagEqualTo(0);
        List<Student> studentList = studentMapper.selectByExample(example);
        return studentList;
    }

    @Override
    public List<Patriarch> findNoAccountParent(String schoolId) {
        PatriarchExample example = new PatriarchExample();
        example.createCriteria().andAccountIsNull().andStudentSchoolIdEqualTo(schoolId).andDelFlagEqualTo(0);
        List<Patriarch> parList = patriarchMapper.selectByExample(example);
        return parList;
    }

    @Override
    public Integer batchInsertStudent(List<Student> studentList) {
        int flag = 0;
        if (studentList.size()>0)
            flag = extensionMapper.insertStudentBatch(studentList);
        return flag;
    }

    @Override
    public Map findStudentByStuNum(String schoolId, String stuNum) {
        List<Map> students = extensionMapper.findStudentByStuNum(schoolId, stuNum);
        if (students.size() > 0)
            return students.get(0);
        return null;
    }

    @Override
    public List<StudentView> selectBatchStudents(List<String> stuIdList, String schoolId) {
        return extensionMapper.selectBatchStudents(stuIdList, schoolId);
    }

    @Override
    public List<Map> genderReport(List<School> schoolList) {
        return extensionMapper.genderReport(schoolList);
    }

    @Override
    public List<Map> personCountReport(List<School> schoolList) {
        return extensionMapper.personCountReport(schoolList,schoolList.size());
    }

    @Override
    public Map lydqReport(List<School> schoolList) {
        List<Map> list = extensionMapper.lydqReport(schoolList);
        if (list.size() > 0) {
            return list.get(0);
        }
        return null;
    }
}
