package cn.gukeer.platform.controller;

import cn.gukeer.common.controller.BasicController;
import cn.gukeer.platform.modelView.TeacherView;
import cn.gukeer.platform.persistence.dao.DepartmentMapper;
import cn.gukeer.platform.persistence.dao.TeacherMapper;
import cn.gukeer.platform.persistence.entity.Department;
import cn.gukeer.platform.persistence.entity.Title;
import cn.gukeer.platform.persistence.entity.Teacher;
import cn.gukeer.platform.service.TeacherService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * 通讯录
 * @author zjy
 */
@Controller
@RequestMapping(value = "/contact")
public class ContactController extends BasicController {

    @Autowired
    TeacherService teacherService;
    @Autowired
    DepartmentMapper departmentMapper;
    @Autowired
    TeacherMapper teacherMapper;

    /**
     * 通讯录-页面
     * @return
     */
    @RequestMapping(value = "/contact/index")
    public String contactIndex(HttpServletRequest request, Model model) {
        int pageNum = getPageNum(request);
        int pageSize = getPageSize(request);
        String schoolId=getLoginUser().getSchoolId();

        String departmentId=getParamVal(request, "departmentId");
        String name=getParamVal(request, "name");
        try {
            name=java.net.URLDecoder.decode(name, "UTF-8");//解决非post访问的中文乱码问题。
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        //全部部门
        List<Department> departmentList=teacherService.findDepartmentBySchool(schoolId);

        //全部岗位
        List<Title> titleList=teacherService.findTitleList();


        PageInfo<Teacher> pageInfo = teacherService.findListByParam(schoolId,departmentId,name,pageNum, pageSize);
        List<Teacher> teacherList = pageInfo.getList();

        List<TeacherView> teacherViewList=new ArrayList<TeacherView>();
        for (Teacher teacher:teacherList){
            TeacherView teacherView=new TeacherView();
            teacherView.setName(teacher.getName());
            teacherView.setPhone(teacher.getPhone());
            teacherView.setMobile(teacher.getMobile());
            teacherView.setEmail(teacher.getEmail());
            teacherView.setHeadUrl(teacher.getHeadUrl());
            for(int i = 0; i < departmentList.size(); i++) {
                if (teacher.getDepartmentId()==departmentList.get(i).getId()) {
                    teacherView.setDepartName(departmentList.get(i).getName());
                    break;
                }
            }
            for(int j = 0; j < titleList.size(); j++) {
                if (teacher.getTitleId() == titleList.get(j).getId()) {
                    teacherView.setTitleName(titleList.get(j).getMc());
                }
            }
            teacherViewList.add(teacherView);

        }

        int size = teacherViewList.size();

        model.addAttribute("departmentId",departmentId);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("teacherViewList", teacherViewList);
        model.addAttribute("name", name);//保留前台查询存入的参数
        model.addAttribute("departmentList", departmentList);
        model.addAttribute("size", size);
        return "contact/tongXunLu";
    }



}
