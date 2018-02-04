
package cn.gukeer.platform.service.impl;

import cn.gukeer.common.service.BasicService;
import cn.gukeer.common.utils.BeanUtil;
import cn.gukeer.common.utils.NumberConvertUtil;
import cn.gukeer.platform.persistence.dao.*;
import cn.gukeer.platform.persistence.entity.*;
import cn.gukeer.platform.service.DepartmentService;
import cn.gukeer.platform.service.TeacherService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by conn on 2016/8/8.
 */
@Service
public class TeacherServiceImpl extends BasicService implements TeacherService {

    @Autowired
    TeacherMapper teacherMapper;
    @Autowired
    A_TeacherExtensionMapper teacherExtensionMapper;

    @Autowired
    TitleMapper titleMapper;

    @Autowired
    DepartmentMapper departmentMapper;

    @Autowired
    DepartmentService departmentService;

    @Autowired
    CourseMapper courseMapper;

    @Override
    public PageInfo<Teacher> findAllList(int pageNum, int pageSize, String schoolId, String teacherName) {

        TeacherExample example = new TeacherExample();
        TeacherExample.Criteria criteria = example.createCriteria();

        criteria.andDelFlagEqualTo(0).andIsManageNotEqualTo(1);
        if (StringUtil.isNotEmpty(schoolId) && StringUtil.isEmpty(teacherName))
            criteria.andSchoolIdEqualTo(schoolId);
        if (StringUtil.isNotEmpty(teacherName))
            criteria.andNameLike("%" + teacherName + "%");


        example.setOrderByClause("create_date desc");

        PageHelper.startPage(pageNum, pageSize);
        List<Teacher> list = teacherMapper.selectByExample(example);
        PageInfo<Teacher> pageInfo = new PageInfo<Teacher>(list);

        return pageInfo;
    }

    @Override
    public List<Teacher> findAllTeacher(String schoolId) {
        TeacherExample teacherExample = new TeacherExample();
        teacherExample.createCriteria().andDelFlagEqualTo(0).andSchoolIdEqualTo(schoolId).andIsManageNotEqualTo(1);
        return teacherMapper.selectByExample(teacherExample);
    }

    @Override
    public Teacher findTeacherById(String id) {
        Teacher teacher = teacherMapper.selectByPrimaryKey(id);
        return teacher;
    }

    @Override
    public List<Teacher> findTeacherByTitleId(String titleId, String schoolId, String loginSchoolId, String teacherName) {
        TeacherExample example = new TeacherExample();
        TeacherExample.Criteria criteria = example.createCriteria();
        if (!(titleId == null || "".equals(titleId) || "0".equals(titleId))) {
            criteria.andTitleIdEqualTo(titleId);
        } else {
            criteria.andTitleIdIsNotNull().andTitleIdNotEqualTo("0");
        }
        if (StringUtil.isNotEmpty(teacherName)) {
            criteria.andNameLike("%" + teacherName + "%");
            schoolId = null;
        }
        if (StringUtil.isNotEmpty(schoolId))
            criteria.andSchoolIdEqualTo(schoolId);

        if (StringUtil.isNotEmpty(loginSchoolId))
            criteria.andSchoolIdNotEqualTo(loginSchoolId);//不查询当前机构的职工

        criteria.andDelFlagEqualTo(0);

        List<Teacher> teacherList = teacherMapper.selectByExample(example);
        return teacherList;
    }

    @Override
    public PageInfo<Map> findTeacherViewByTitleId(String titleId, String schoolId, String loginSchoolId,
                                                  String teacherName, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Map> res = teacherExtensionMapper.findTeacherViewByTitle(titleId, schoolId, loginSchoolId, "%" + teacherName + "%");
        PageInfo<Map> pageInfo = new PageInfo<Map>(res);

        return pageInfo;
    }

    @Override
    public List<Map<String, Object>> findTeacherViewByParams(Map<Object, Object> param) {
        return teacherExtensionMapper.selectTeacherViewByParam(param);
    }

    @Override
    public int updateTeacher(Teacher teacher) {
        int count = teacherMapper.updateByPrimaryKeySelective(teacher);
        return count;
    }

    @Override
    public int updateTeacherAllFiled(Teacher teacher) {
        return teacherMapper.updateByPrimaryKey(teacher);
    }

    @Override
    public int updateTeacherByCriteria(Teacher teacher, List<String> departmentIds, String schoolId) {
        TeacherExample teacherExample = new TeacherExample();
        teacherExample.createCriteria().andSchoolIdEqualTo(schoolId).andDepartmentIdIn(departmentIds);
        return teacherMapper.updateByExampleSelective(teacher, teacherExample);
    }

    @Override
    public int save(Teacher teacher) {
        int count = 0;
        if (null != teacher) {
            if (null != teacher.getId() && teacher.getId() != "") {
                count = teacherMapper.updateByPrimaryKeySelective(teacher);
            } else {
                teacher.setId(null);
                count = teacherMapper.insertSelective(teacher);
            }
        }

        return count;
    }


    @Override
    public int saveTeacherBackId(Teacher teacher) {
        return teacherMapper.insertSelective(teacher);
    }

    @Override
    public int insertTeacher(Teacher teacher) {
        int count = teacherMapper.insertSelective(teacher);
        return count;
    }

    @Override
    public PageInfo<Teacher> findListByParam(String schoolId, String departmentId, String name, int pageNum, int pageSize) {

        TeacherExample teacherExample = new TeacherExample();
        TeacherExample.Criteria criteria = teacherExample.createCriteria();
        criteria.andDelFlagEqualTo(0).andSchoolIdEqualTo(schoolId);

        List<String> allDepartIds = new ArrayList<String>();
        if (!departmentId.equals("") && NumberConvertUtil.convertS2I(departmentId) != 0) {
            //查询部门下子部门人员  递归
            allDepartIds.add(departmentId);
            allDepartIds.addAll(departmentService.getAllSonDepartment(allDepartIds, schoolId));
            criteria.andDepartmentIdIn(allDepartIds);
        }

        if (!name.equals("")) {
            criteria.andNameLike("%" + name + "%");
        }

        PageHelper.startPage(pageNum, pageSize);
        List<Teacher> teacherList = teacherMapper.selectByExample(teacherExample);
        PageInfo<Teacher> pageInfo = new PageInfo<Teacher>(teacherList);
        return pageInfo;
    }

    @Override
    public List<Department> findDepartmentBySchool(String schoolId) {
        //全部部门
        DepartmentExample example = new DepartmentExample();
        example.createCriteria().andDelFlagEqualTo(0).andSchoolIdEqualTo(schoolId);
        List<Department> departmentList = departmentMapper.selectByExample(example);
        return departmentList;
    }

    @Override
    public List<Title> findTitleList() {
        //全部岗位
        TitleExample titleExample = new TitleExample();
        titleExample.createCriteria().andDelFlagEqualTo(0);
        List<Title> titleList = titleMapper.selectByExample(titleExample);
        return titleList;
    }

    @Override
    public List<Title> selectTitleBySchoolId(String schoolId) {
        TitleExample titleExample = new TitleExample();
        titleExample.createCriteria().andDelFlagEqualTo(0).andSchoolIdEqualTo(schoolId);
        titleExample.setOrderByClause("px+0");//根据级别排序
        List<Title> titleList = titleMapper.selectByExample(titleExample);
        return titleList;
    }

    @Override
    public Title selectTitleById(String id) {
        return titleMapper.selectByPrimaryKey(id);
    }

    @Override
    public int saveTitle(Title title) {
        return titleMapper.insertSelective(title);
    }

    @Override
    public int updateTitle(Title title) {
        return titleMapper.updateByPrimaryKeySelective(title);
    }

    @Override
    public List<Teacher> findNoAccountTeacher(String schoolId) {
        TeacherExample example = new TeacherExample();
        example.createCriteria().andAccountIsNull().andDelFlagEqualTo(0).andSchoolIdEqualTo(schoolId).andIsManageNotEqualTo(1);
        List<Teacher> teacherList = teacherMapper.selectByExample(example);
        return teacherList;
    }

    @Override
    public List<Teacher> findHaveAccountTeacher(String schoolId) {
        TeacherExample example = new TeacherExample();
        example.createCriteria().andAccountIsNotNull().andDelFlagEqualTo(0).andSchoolIdEqualTo(schoolId).andIsManageNotEqualTo(1);
        List<Teacher> teacherList = teacherMapper.selectByExample(example);
        return teacherList;
    }

    @Override
    public PageInfo<Map> findTeacherViewList(String departmentId, Teacher teacher, int pageNum, int pageSize) {
        List<String> allListId = new ArrayList<String>();
        //递归查询：当前部门，子部门，子子部门。。。。
        if (StringUtil.isNotEmpty(departmentId)) {
            allListId.add(departmentId);
            allListId.addAll(departmentService.getAllSonDepartment(allListId, teacher.getSchoolId()));
        }

        PageHelper.startPage(pageNum, pageSize);
        List<Map> res = teacherExtensionMapper.findTeacherViewList(teacher.getSchoolId(), allListId, "%" + teacher.getName() + "%");
        PageInfo<Map> pageInfo = new PageInfo<Map>(res);
        return pageInfo;
    }

    @Override
    public PageInfo<Teacher> findTeacherByDepartmentId(String departmentId, Teacher teacher, int pageNum, int pageSize) {

        List<String> allListId = new ArrayList<String>();

        TeacherExample example = new TeacherExample();
        TeacherExample.Criteria criteria = example.createCriteria();
        criteria.andDelFlagEqualTo(0).andSchoolIdEqualTo(teacher.getSchoolId()).andIsManageNotEqualTo(1);

        if (!departmentId.equals("") && NumberConvertUtil.convertS2I(departmentId) != -1) {
            //递归查询：当前部门，子部门，子子部门。。。。
            allListId.add(departmentId);
            allListId.addAll(departmentService.getAllSonDepartment(allListId, teacher.getSchoolId()));

            criteria.andDepartmentIdIn(allListId);
        }
        //查找职工
        String teacherName = teacher.getName();
        if (StringUtil.isNotEmpty(teacherName)) {
            criteria.andNameLike("%" + teacherName + "%");
        }
        PageHelper.startPage(pageNum, pageSize);
        List<Teacher> teacherList = teacherMapper.selectByExample(example);
        PageInfo<Teacher> pageInfo = new PageInfo<Teacher>(teacherList);
        return pageInfo;
    }

    @Override
    public Map<Object, Object> getTeacherList(Map<String, String> param, boolean flag, String schoolId) {
        String teacherName = param.get("teacherName");
        try {
            teacherName = java.net.URLDecoder.decode(teacherName, "UTF-8");//解决非post访问的中文乱码问题。
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        Map<Object, Object> map = new HashMap<Object, Object>();
        TeacherExample teacherExample = new TeacherExample();
        TeacherExample.Criteria criteria = teacherExample.createCriteria();
        criteria.andSchoolIdEqualTo(schoolId).andDelFlagEqualTo(0).andIsManageNotEqualTo(1);
        teacherExample.setOrderByClause("create_date desc");
        if (!StringUtils.isEmpty(teacherName)) {
            criteria.andNameLike("%" + teacherName + "%");
        }
        int pageSize = 0;
        int pageNum = 0;
        if (flag) {
            pageSize = NumberConvertUtil.convertS2I(param.get("pageSizeHave")) == 0 ? 10 : NumberConvertUtil.convertS2I(param.get("pageSizeHave"));
            pageNum = NumberConvertUtil.convertS2I(param.get("pageNumHave"));
            criteria.andAccountIsNotNull();
        } else {
            pageSize = NumberConvertUtil.convertS2I(param.get("pageSizeNo")) == 0 ? 10 : NumberConvertUtil.convertS2I(param.get("pageSizeNo"));
            pageNum = NumberConvertUtil.convertS2I(param.get("pageNumNo"));
            criteria.andAccountIsNull();
        }

        PageHelper.startPage(pageNum, pageSize);
        List<Teacher> teacherList = teacherMapper.selectByExample(teacherExample);
        PageInfo<Teacher> pageInfo = new PageInfo<Teacher>(teacherList);
        map.put("teacherList", teacherList);
        map.put("pageInfo", pageInfo);
        map.put("teacherName", teacherName);

        return map;
    }

    @Override
    public List<Teacher> findTeacherLikeNameAndSchoolId(String name, String schoolId) {
        TeacherExample example = new TeacherExample();
        example.createCriteria().andNameLike("%" + name + "%").andSchoolIdEqualTo(schoolId).andDelFlagEqualTo(0).andIsManageNotEqualTo(1);

        List<Teacher> teacherList = teacherMapper.selectByExample(example);
        return teacherList;
    }

    @Override
    public int insertTeacherBatch(List<Teacher> teacherList) {
        int flag = teacherExtensionMapper.insertTeacherBatch(teacherList);
        return flag;
    }

    @Override
    public List<Teacher> selectTeacherNameLike(String name, String schoolId) {
        return teacherExtensionMapper.selectTeacherNameLike(name, schoolId);
    }

    @Override
    public List<Teacher> selectBatchTeachers(List<String> teacherList, String schoolId) {
        return teacherExtensionMapper.selectBatchTeachers(teacherList, schoolId);
    }

    @Override
    public PageInfo<Map> teacherListView(int pageNum, int pageSize, String currentSchoolId, String schoolId, String teacherName) {

        PageHelper.startPage(pageNum, pageSize);

        if (StringUtil.isEmpty(schoolId))
            return new PageInfo<Map>(new ArrayList<Map>());//若未选择学校返回空

        /*if (StringUtil.isNotEmpty(teacherName)) {
            //若选择学校但搜索姓名，查询父school_id的机构  AND v.`schoolId` &lt;&gt; #{currentSchoolId} AND v.`schoolParentId` = #{currentSchoolId}
            schoolId = null;
        }*/

        List<Map> list = teacherExtensionMapper.teacherListView(currentSchoolId, schoolId, "%" + teacherName + "%");
        PageInfo<Map> pageInfo = new PageInfo<Map>(list);

        return pageInfo;
    }


    public List<Teacher> findAllTeacherByShoolIAndSFBZR(String schoolId) {
        TeacherExample teacherExample = new TeacherExample();
        ArrayList<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        //1 班主任  2副班主任
        teacherExample.createCriteria().andDelFlagEqualTo(0).andSchoolIdEqualTo(schoolId).andSfbzrIn(list);
        return teacherMapper.selectByExample(teacherExample);
    }

    @Override
    public Map teacherReport(String loginSchoolId, List<School> schoolList) {
        return teacherExtensionMapper.teacherReport(loginSchoolId, schoolList);
    }

    @Override
    public Course findCourseById(String courseId) {
        return courseMapper.selectByPrimaryKey(courseId);
    }

    @Override
    public List<Course> findAllCourseBySchoolId(String schoolId) {
        CourseExample example = new CourseExample();
        example.createCriteria().andSchoolIdEqualTo(schoolId).andDelFlagEqualTo(0);
        return courseMapper.selectByExample(example);
    }

    @Override
    public int batchUpdateTeacher(List<String> teacherIdList, Teacher teacher) {
        Map<String, Object> map = BeanUtil.transBean2Map(teacher);
        map.put("idList",teacherIdList);
        return teacherExtensionMapper.batchUpdateTeacher(map);
    }


    public Teacher findTeacherByNoAndSchoolId(String schoolId, String courseTeacherNo) {
        TeacherExample example = new TeacherExample();
        example.createCriteria().andSchoolIdEqualTo(schoolId).andDelFlagEqualTo(0).andNoEqualTo(courseTeacherNo);
        List<Teacher> teacherList = teacherMapper.selectByExample(example);
        if (teacherList.size() > 0 && teacherList != null) {
            return teacherList.get(0);
        }
        return null;
    }
}
