package cn.gukeer.platform.service;

import cn.gukeer.platform.persistence.entity.Department;
import cn.gukeer.platform.persistence.entity.Menu;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * Created by conn on 2016/8/8.
 */
public interface DepartmentService {

    Department findDepartmentById(String id);

    List<Department> findAllDepartment(String schoolId);

    List<String> getAllSonDepartment(List<String> ids, String schoolId);

    int updateDepartment(Department department);
}
