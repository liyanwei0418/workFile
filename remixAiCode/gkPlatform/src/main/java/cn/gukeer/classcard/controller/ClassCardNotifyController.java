package cn.gukeer.classcard.controller;

import cn.gukeer.classcard.modelView.ClassCardNotifyView;
import cn.gukeer.classcard.modelView.ClassCardView;
import cn.gukeer.classcard.persistence.entity.ClassCard;
import cn.gukeer.classcard.persistence.entity.ClassCardNotify;
import cn.gukeer.classcard.persistence.entity.ClassCardNotifyRef;
import cn.gukeer.classcard.service.*;
import cn.gukeer.common.controller.BasicController;
import cn.gukeer.common.entity.ResultEntity;
import cn.gukeer.common.utils.PrimaryKey;
import cn.gukeer.platform.common.UserRoleType;
import cn.gukeer.platform.persistence.dao.A_GradeClassMapper;
import cn.gukeer.platform.persistence.dao.ClassRoomMapper;
import cn.gukeer.platform.persistence.dao.RefClassRoomMapper;
import cn.gukeer.platform.persistence.entity.TeacherClass;
import cn.gukeer.platform.persistence.entity.User;
import cn.gukeer.platform.service.*;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by alpha on 18-1-16.
 */
@Controller
@RequestMapping(value = "/classcard")
public class ClassCardNotifyController extends BasicController{


    @Autowired
    ClassCardService classCardService;

    @Autowired
    TeachTaskService teachTaskService;

    @Autowired
    ClassCardNotifyService classCardNotifyService;

    @Autowired
    ClassCardNotifyRefService classCardNotifyRefService;

    /**
     * 通知公告首页
     *
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/notify/index")
    public String classCardNotifyIndex(HttpServletRequest request, Model model) {

        String title = getParamVal(request, "title");
        try {
            title = java.net.URLDecoder.decode(title, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String type = getParamVal(request, "type");
        int pageNum = getPageNum(request);
        int pageSize = getPageSize(request);

        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();

        PageInfo<ClassCardNotifyView> pageInfo = new PageInfo<>();
        if (subject.isAuthenticated()) {
            if (subject.hasRole(UserRoleType.ROLE_CLASSCARDADMIN) || subject.hasRole(UserRoleType.ROLE_SCHOOLADMIN)) {
                pageInfo = classCardNotifyService.findAllNotify(title, "".equals(type) ? -1 : Integer.parseInt(type), user.getSchoolId(), pageNum, pageSize);
            } else if (subject.hasRole(UserRoleType.ROLE_HEADTEACHER)) {
                String teacherId = user.getRefId();
                //老师的班级(可能多个)
                List<TeacherClass> teacherClasses = teachTaskService.findAllByTeacherId(teacherId);
                List<String> classIds = new ArrayList<>();
                if (teacherClasses != null && teacherClasses.size() > 0) {
                    for (TeacherClass tc : teacherClasses) {
                        classIds.add(tc.getClassId());
                    }
                }
                //获得班级对应的班牌
                List<ClassCard> classCardList = classCardService.findClassCardByClassIds(classIds);
                //班牌id的list
                List<String> classCardIds = new ArrayList<>();
                if (classCardList != null && classCardList.size() > 0) {
                    for (ClassCard cc : classCardList) {
                        classCardIds.add(cc.getId());
                    }
                }
                //班牌和通知公告中间表
                List<ClassCardNotifyRef> allByClassCardIds = classCardNotifyRefService.findAllByClassCardIds(classCardIds);
                List<String> notifyIds = new ArrayList<>();
                if (allByClassCardIds != null && allByClassCardIds.size() > 0) {
                    for (ClassCardNotifyRef cr : allByClassCardIds) {
                        notifyIds.add(cr.getClassCardNotifyId());
                    }
                }
                pageInfo = classCardNotifyService.findById(title, notifyIds, pageNum, pageSize);
            }
        }
        List<ClassCardNotifyView> resultList = classCardNotifyService.transforNotifyView(pageInfo.getList());
        pageInfo.setList(resultList);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("title", title);
        model.addAttribute("type", type);

        return "classCard/notify/index";
    }

    @RequestMapping(value = "/notify/edit")
    public String notifyEdit(HttpServletRequest request, Model model) {
        String disabled = getParamVal(request, "disabled");
        String id = getParamVal(request, "id");
        if (!"".equals(id)) {
            ClassCardNotify classCardNotify = classCardNotifyService.findById(id);
            model.addAttribute("classCardNotify", classCardNotify);
            List<ClassCardNotifyRef> classCardNotifyRefs = classCardNotifyRefService.findAllByNotifyId(id);
            String checkedIds = "";
            if (classCardNotifyRefs != null && classCardNotifyRefs.size() > 0) {
                for (ClassCardNotifyRef ref : classCardNotifyRefs) {
                    checkedIds += ref.getClassCardId() + ",";
                }
            }
            model.addAttribute("checkedIds", checkedIds);
        }
        model.addAttribute("disabled", disabled);
        return "classCard/notify/edit";
    }

    @ResponseBody
    @RequestMapping(value = "/notify/save")
    public ResultEntity saveClassCardNotify(HttpServletRequest request, Model model) {
        String title = getParamVal(request, "title");
        String type = getParamVal(request, "type");
        String content = getParamVal(request, "content");

        ResultEntity resultEntity = ResultEntity.newErrEntity();
        if ("".equals(title) || "".equals(type) || "".equals(content)) {
            return resultEntity;
        }
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();

        String refId = PrimaryKey.get();
        ClassCardNotify classCardNotify = new ClassCardNotify();
        classCardNotify.setId(refId);
        classCardNotify.setTitle(title);
        classCardNotify.setType(Integer.parseInt(type));
        classCardNotify.setContent(content);
        classCardNotify.setCreateBy(user.getId());
        classCardNotify.setCreateDate(System.currentTimeMillis());

        String checkedIds = getParamVal(request, "checkedIds");
        int count = 0;
        boolean flag = false;
        try {
            count = classCardNotifyService.insertClassCardNotify(classCardNotify);
            flag = classCardNotifyRefService.insertClassCardNotifyRef(checkedIds, refId, user.getSchoolId(), user.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (count != 0 && flag) {
            resultEntity = ResultEntity.newResultEntity();
        }
        return resultEntity;
    }

    @ResponseBody
    @RequestMapping(value = "/notify/multidelete")
    public ResultEntity deleteNotify(HttpServletRequest request, Model model) {
        String notifyId = getParamVal(request, "notifyId");
        ResultEntity resultEntity = ResultEntity.newErrEntity();
        if (!"".equals(notifyId)) {
            int notifyCount = 0;
            String[] notifyIdArr = notifyId.split(",");
            if (notifyIdArr != null && notifyIdArr.length > 0) {
                try {
                    notifyCount = classCardNotifyService.deleteClassCardNotify(notifyIdArr);
                    classCardNotifyRefService.deleteRefByNotifyId(notifyIdArr);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (notifyCount == notifyIdArr.length) {
                    resultEntity = ResultEntity.newResultEntity();
                }
            }
        }
        return resultEntity;
    }

    //发布通知选择设备
    @RequestMapping(value = "/chooseclasscard")
    public String chooseClassCard(HttpServletRequest request, Model model) {
        String checkedIds = getParamVal(request, "checkedIds");
        String disabled = getParamVal(request, "disabled");

        Map<String, Map<String, List<ClassCardView>>> resultMap = classCardService.selectEquipmentForNotify();
        JsonObject returnData = new JsonParser().parse(new Gson().toJson(resultMap)).getAsJsonObject();
        model.addAttribute("returnData", returnData);
        model.addAttribute("checkedIds", checkedIds);
        model.addAttribute("disabled", disabled);
        return "classCard/notify/chooseclasscard";
    }

}
