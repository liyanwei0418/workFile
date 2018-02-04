package cn.gukeer.platform.service.impl;

import cn.gukeer.common.service.BasicService;
import cn.gukeer.common.utils.PrimaryKey;
import cn.gukeer.platform.persistence.dao.DepartmentMapper;
import cn.gukeer.platform.persistence.entity.Department;
import cn.gukeer.platform.persistence.entity.DepartmentExample;
import cn.gukeer.platform.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by GW on 2016/9/10.
 */
@Service
public class DepartmentServiceImpl extends BasicService implements DepartmentService {

    @Autowired
    DepartmentMapper departmentMapper;

    @Override
    public Department findDepartmentById(String id) {
        return departmentMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Department> findAllDepartment(String schoolId) {
        DepartmentExample departmentExample = new DepartmentExample();
        departmentExample.createCriteria().andDelFlagEqualTo(0).andSchoolIdEqualTo(schoolId);
        List<Department> departmentList = departmentMapper.selectByExample(departmentExample);
        return departmentList;
    }

    @Override
    //递归查找部门以及子部门信息 返回所有子部门的id。List集合
    public List<String> getAllSonDepartment(List<String> ids, String schoolId) {
        List<Department> departmentList = new ArrayList<Department>();
        List<String> departIdList = new ArrayList<String>();

        DepartmentExample example = new DepartmentExample();
        example.createCriteria().andDelFlagEqualTo(0).andSchoolIdEqualTo(schoolId).andParentIdIn(ids);
        departmentList = departmentMapper.selectByExample(example);
        if (departmentList.size() > 0) {
            for (Department departMent : departmentList) {
                departIdList.add(departMent.getId());
            }
            departIdList.addAll(this.getAllSonDepartment(departIdList, schoolId));
        }
        return departIdList;
    }

    @Override
    public int updateDepartment(Department department) {
        int count = 0;
        if (!StringUtils.isEmpty(department.getId())) {
            count = departmentMapper.updateByPrimaryKeySelective(department);
        } else {

            department.setId(PrimaryKey.get());
            count = departmentMapper.insertSelective(department);
        }
        return count;
    }

}
