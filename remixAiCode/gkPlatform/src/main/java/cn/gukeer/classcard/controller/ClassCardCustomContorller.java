package cn.gukeer.classcard.controller;

import cn.gukeer.classcard.modelView.ClassCardCustomPageView;
import cn.gukeer.classcard.persistence.entity.ClassCard;
import cn.gukeer.classcard.persistence.entity.ClassCardCustomContentRef;
import cn.gukeer.classcard.persistence.entity.ClassCardCustomPage;
import cn.gukeer.classcard.persistence.entity.ClassCardCustomTemplate;
import cn.gukeer.classcard.service.*;
import cn.gukeer.classcard.util.ClassCardCommand;
import cn.gukeer.common.controller.BasicController;
import cn.gukeer.common.entity.ResultEntity;
import cn.gukeer.common.utils.DateUtils;
import cn.gukeer.common.utils.PrimaryKey;
import cn.gukeer.common.utils.StringUtils;
import cn.gukeer.platform.common.UserRoleType;
import cn.gukeer.platform.persistence.entity.User;
import cn.gukeer.platform.service.impl.CacheServiceImpl;
import cn.gukeer.sync.netty.classCardAttendance.ClassCardServer;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.wulianedu.netty.server.ServerInstance;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by leeyh on 18-1-16.
 */
@Controller
@RequestMapping(value = "/classcard/custom")
public class ClassCardCustomContorller extends BasicController{

    @Autowired
    ClassCardService classCardService;
    @Autowired
    ClassCardCustomPageService pageService;
    @Autowired
    ClassCardCustomRefService customRefService;
    @Autowired
    ClassCardCustomTemplateService templateService;
    @Autowired
    ClassCardCustomContentRefService contentRefService;
    @Autowired
    CacheManager cacheManager;

    /**
     * 自定义主页面
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String indexCustom(HttpServletRequest request, Model model) {
        int pageNum = getPageNum(request);
        int pageSize = getPageSize(request);

        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();

        PageInfo<ClassCardCustomPageView> pageInfo = new PageInfo<>();
        if(subject.isAuthenticated()) {
            if (subject.hasRole(UserRoleType.ROLE_SCHOOLADMIN)) {
                pageInfo = pageService.findAllPageBySchoolId(user.getSchoolId(), pageNum, pageSize);
            } else {
                pageInfo = null;
            }
        }
        List<ClassCardCustomPageView> pageViewList = pageService.transformPage(pageInfo.getList());
        pageInfo.setList(pageViewList);
        model.addAttribute("pageInfo",pageInfo);
        return "/classCard/custom/index";
    }

    /**
     * 新增page的时候选择模板
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/selectTemplate")
    public String selectTemplate(HttpServletRequest request, Model model) {
        //注意：以后模板会有很多，需要添加分页
        int pageNum = 1;
        int pageSize = -1;
        List<ClassCardCustomTemplate> templateList = templateService.findAllTemplateBySchoolId(getLoginUser().getSchoolId(),pageNum,pageSize).getList();
        model.addAttribute("templateList",templateList);
        return "/classCard/custom/template";
    }

    /**
     * 选择好模板后跳转创建page界面
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/createPageByTemplate")
    public ResultEntity createPageByTemplate(HttpServletRequest request) {
        String templateId = getParamVal(request,"templateId");
        ResultEntity resultEntity = ResultEntity.newErrEntity("选择错误");
        String filePath = "/classcard/custom/templatePageReturn?templateId=" + templateId;
        if (StringUtils.isNotEmpty(templateId)) {
            resultEntity = ResultEntity.newResultEntity(filePath);
        }
        return resultEntity;
    }
    @RequestMapping(value = "/templatePageReturn")
    public String pageReturn(HttpServletRequest request,Model model) {
        String templateId = getParamVal(request,"templateId");
        if (StringUtils.isEmpty(templateId)) {
            //防止templateId为空出现空指针异常
            return "/classCard/custom/index";
        }
        ClassCardCustomTemplate template = templateService.findOneById(templateId);
        model.addAttribute("pageName",template.getName());
        model.addAttribute("templateId",templateId);
        model.addAttribute("templateFilePath",template.getFilePath());
        return "/classCard/custom/template/pageEditor";
    }

    @RequestMapping(value = "/richEditor")
    public String richEditor(HttpServletRequest request, Model model) {
        String name = getParamVal(request,"name");
        model.addAttribute("name",name);
        return "/classCard/custom/template/richEditor";
    }

    /**
     * 修改已经保存的数据，数据回填
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/edit")
    public String editCustom(HttpServletRequest request, Model model) {
        String pageId = getParamVal(request,"pageId");
        String option = getParamVal(request,"option");
        ClassCardCustomPageView customPage = pageService.findViewOneById(pageId);
        ClassCardCustomTemplate customTemplate = templateService.findOneById(customPage.getTemplateId());
        //没有对查询出来的template进行判断，是否为空，若为空跳转模板页面会出现找不到模板页的错误。
        List<ClassCardCustomContentRef> contentRefs = contentRefService.findContentByPageId(pageId);
        //模板的每块内容的名字和值都是固定的，这样做和不灵活，目前想不到更好的办法？
        /*int i = 1;
        for (ClassCardCustomContentRef content : contentRefs) {
            model.addAttribute("name" + i,content.getName());
            model.addAttribute("value" + i,content.getValue());
            i++;
        }*/
        //为什么不返回customPage对象(~-v~-),无关紧要懒得改了。
        model.addAttribute("templateId", customPage.getTemplateId());
        model.addAttribute("pageId",customPage.getId());
        model.addAttribute("pageName",customPage.getName());
        model.addAttribute("loopTime",customPage.getStartTimeView()+"---"+customPage.getEndTimeView());
        model.addAttribute("loopMark", customPage.getLoopMark());
        model.addAttribute("loopDate",customPage.getLoopDate());
        model.addAttribute("pageContent",contentRefs.get(0).getValue());
        model.addAttribute("option",option);
        model.addAttribute("templateFilePath",customTemplate.getFilePath());
        return "/classCard/custom/template/pageEditor";
    }

    /**
     * 保存自定义界面
     * @param request
     * @return
     */
    @ResponseBody
    @Transactional
    @RequestMapping(value = "/save" , method = RequestMethod.POST)
    public ResultEntity saveCustom(HttpServletRequest request) {
        ResultEntity resultEntity = ResultEntity.newErrEntity("保存失败");
        String option = getParamVal(request,"option");
        if ("disabled".equals(option)) {
            return ResultEntity.newErrEntity("已发布，不可编辑");
        }
        String pageName = getParamVal(request,"pageName");
        String templateId = getParamVal(request,"templateId");

        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        if (subject.isAuthenticated()) {
            if (!subject.hasRole(UserRoleType.ROLE_SCHOOLADMIN)) {
                return ResultEntity.newErrEntity("当前用户权限不足");
            }
        }
        //对参数需要做一些判断
        String names = getParamVal(request,"names");
        String contents = getParamVal(request,"contents");
        if (names.split(",",-1).length != contents.split("##@@",-1).length) {
            return ResultEntity.newErrEntity("内容与模板不匹配，保存失败");
        }
        ClassCardCustomPage customPage = new ClassCardCustomPage();
        customPage.setName(pageName);
        customPage.setTemplateId(templateId);
        customPage.setSchoolId(user.getSchoolId());

        String pageId = getParamVal(request,"pageId");
        int resTotal = 0;
        if(StringUtils.isEmpty(pageId)) {
            //insert
            pageId = PrimaryKey.get();
            customPage.setId(pageId);
            customPage.setCreateBy(user.getId());
            customPage.setCreateDate(System.currentTimeMillis());
            resTotal += pageService.insertPage(customPage);
            resTotal += contentRefService.insertContent(pageId,names,contents);
        } else {
            //update
            customPage.setId(pageId);
            customPage.setUpdateBy(user.getId());
            customPage.setUpdateDate(System.currentTimeMillis());
            resTotal += pageService.updatePage(customPage);
            resTotal += contentRefService.updateContent(pageId,names,contents);
        }
        if (resTotal != 0) {
            resultEntity = ResultEntity.newResultEntity("保存成功");
        }
        return resultEntity;
    }

    @Transactional
    @ResponseBody
    @RequestMapping(value = "/multidelete")
    public ResultEntity deleteCustom(HttpServletRequest request) {
        String customPageIds = getParamVal(request, "pageId");
        ResultEntity resultEntity = ResultEntity.newErrEntity("删除失败");

        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        if (subject.isAuthenticated()) {
            if (!subject.hasRole(UserRoleType.ROLE_SCHOOLADMIN)) {
                return ResultEntity.newErrEntity("当前用户权限不足");
            }
        }

        if (StringUtils.isNotEmpty(customPageIds)) {
            int resultCount = 0;
            String[] pageIds = customPageIds.split(",");
            if (pageIds.length > 0) {
                for (String pageId : pageIds) {
                    try {
                        resultCount += contentRefService.deleteCustomContentById(pageId);
                        resultCount += customRefService.deleteCustomRefById(pageId);
                        resultCount += pageService.deletePageById(pageId);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                if (resultCount >= pageIds.length) {
                    resultEntity = ResultEntity.newResultEntity("删除成功");
                }
            }
            resultEntity.newErrEntity("请选择需要删除的页面");
        }
        return resultEntity;
    }


    @RequestMapping(value = "/publishPage")
    public String publishPage(HttpServletRequest request, Model model) {
        String pageId = getParamVal(request,"pageId");
        String option = getParamVal(request,"option");
        ClassCardCustomPageView customPageView = pageService.findViewOneById(pageId);
        String checkedIds = customRefService.findCheckIds(pageId);
        model.addAttribute("customPage",customPageView);
        model.addAttribute("option",option);
        model.addAttribute("checkedIds",checkedIds);
        return "classCard/custom/publish";
    }

    @ResponseBody
    @Transactional
    @RequestMapping(value = "/publish",method = RequestMethod.POST)
    public ResultEntity publish(HttpServletRequest request) {
        ResultEntity resultEntity;
        String pageId = getParamVal(request,"pageId");
        String startTime = getParamVal(request,"startTime");
        String endTime = getParamVal(request,"endTime");
        String loopMark = getParamVal(request,"loopMark");
        String loopDate = getParamVal(request,"loopDate");
        String classCardList = getParamVal(request,"classCardList");
        String sfm = "HH:mm";
        Long releaseStartTime,releaseEndTime;
        try {
            releaseStartTime = DateUtils.stringToTimestamp(startTime,sfm);
            releaseEndTime = DateUtils.stringToTimestamp(endTime,sfm);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultEntity.newErrEntity("发布时间格式错误");
        }
        //32为一个设备的uuid的长度
        if (StringUtils.isEmpty(classCardList) || classCardList.length()<32) {
            return ResultEntity.newErrEntity("未选择发布设备");
        }
        if ("W".equals(loopMark) || "M".equals(loopMark)) {
            if (StringUtils.isEmpty(loopDate)) {
                return ResultEntity.newErrEntity("未选择循环时间");
            }
        }
        ClassCardCustomPage customPage = new ClassCardCustomPage();
        customPage.setId(pageId);
        customPage.setStartTime(releaseStartTime);
        customPage.setEndTime(releaseEndTime);
        customPage.setLoopMark(loopMark);
        customPage.setLoopDate(loopDate);
        //参数classcardList,LoopMark,Loopdate，startTime,endTime进行发布设备时间段是否重复的判断
        Boolean verifyFlag = pageService.verifyReleaseDate(classCardList,loopMark,loopDate,releaseStartTime,releaseEndTime);
        int count = customRefService.updatePageRelease(classCardList,pageId);
        if (verifyFlag) {
            if (count != 0) {
                //修改page的status状态，0未发布，1已发布
                customPage.setStatus(1);
                resultEntity = ResultEntity.newResultEntity("发布成功");
                //添加班牌设备推送
                try {
                    ServerInstance instance = ClassCardServer.getInstance();
                    CacheServiceImpl cacheService = new CacheServiceImpl(cacheManager, "classcardcommand-cache");
                    //多个设备循环依次推送
                    String[] classcards = classCardList.split(",");
                    ClassCardCustomContentRef pageContent = contentRefService.findContentByPageId(pageId).get(0);
                    if (pageContent != null) {
                        for (String classcardId:classcards) {
                            //获取准备推送的设备，获取mac地址
                            ClassCard classCard = classCardService.findClassCardByClassCardId(classcardId);
                            if (classCard == null) {
                                continue;
                            } else {
                                String macFormate = classCard.getMacId().replace(":","_").toUpperCase();
                                //用map保存需要推送的数据
                                Map<String,Object> customPageMap = new HashMap<>();
                                customPageMap.put("command", ClassCardCommand.CUSTOM_PUBLISH);
                                customPageMap.put("name",customPage.getName());
                                customPageMap.put("startTime",customPage.getStartTime());
                                customPageMap.put("endTime",customPage.getEndTime());
                                customPageMap.put("loopMark",customPage.getLoopMark());
                                customPageMap.put("loopDate",customPage.getLoopDate());
                                //用页面的id作为版本的标识，如果是一个页面不同时间段发布在同一个设备发布会出现问题。问题出现在取消发布的时候不能唯一标识。。
                                customPageMap.put("versionId",customPage.getId());
                                customPageMap.put("content",pageContent.getValue());
                                customPageMap.put("systemTime",System.currentTimeMillis());
                                List<Map<String,Object>> customPageList = new LinkedList<>();
                                customPageList.add(customPageMap);
                                //管道通，直接推送
                                if (instance.channelStatus(macFormate)) {
                                    System.out.println("===================[" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis()) + "]==================Channel connectivity");
                                    instance.sendMessage(macFormate,new Gson().toJson(customPageList));
                                    System.out.println("===============Data sent successfully=============" + new Gson().toJson(customPageList));
                                } else {
                                    //推送失败，放入缓冲
                                    System.out.println("===================[" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis()) + "]==================Channel unconnected");
                                    Object obj = cacheService.getCacheByKey(macFormate + "_" + ClassCardCommand.CUSTOM_PUBLISH);
                                    if (obj != null) {
                                        customPageList = (List<Map<String, Object>>) obj;
                                        customPageList.add(customPageMap);
                                    }
                                    cacheService.addCache(macFormate + "_" + ClassCardCommand.CUSTOM_PUBLISH, customPageList);
                                }
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                resultEntity = ResultEntity.newErrEntity("发布设备或者页面选择错误");
            }
        } else {
            resultEntity = ResultEntity.newErrEntity("发布设备时间段重叠");
        }
        pageService.updatePage(customPage);
        return resultEntity;
    }

    @ResponseBody
    @Transactional
    @RequestMapping(value = "/unpublish")
    public ResultEntity unpublish(HttpServletRequest request) {
        ResultEntity resultEntity = ResultEntity.newErrEntity("取消发布失败");
        int count = 0;
        String pageId = getParamVal(request,"pageId");
        //需要删除发布的设备吗？在时间段重复的判断中只验证已发布的page的时间段。暂时不删除设备在下次发布时候能直接回填
//        count += customRefService.deleteCustomRefById(pageId);
        //修改page的状态
        ClassCardCustomPage customPage = new ClassCardCustomPage();
        customPage.setId(pageId);
        customPage.setStatus(0);
        customPage.setUpdateDate(System.currentTimeMillis());
        customPage.setUpdateBy(getLoginUser().getId());
        count += pageService.updatePage(customPage);
        if (count >= 1) {
            resultEntity = ResultEntity.newResultEntity("取消发布成功");
            //添加班牌设备推送
            try {
                ServerInstance instance = ClassCardServer.getInstance();
                CacheServiceImpl cacheService = new CacheServiceImpl(cacheManager, "classcardcommand-cache");
                //多个设备循环依次推送
                String classCardList = customRefService.findCheckIds(pageId);
                String[] classcardIds = classCardList.split(",");
                for (String classcardId : classcardIds) {
                    ClassCard classCard = classCardService.findClassCardByClassCardId(classcardId);
                    if (classCard == null) {
                        continue;
                    }
                    String macFormate = classCard.getMacId().replace(":","_").toUpperCase();
                    Map<String,Object> customPageMap = new HashMap<>();
                    customPageMap.put("command", ClassCardCommand.CUSTOM_UNPUBLISH);
                    customPageMap.put("versionId",pageId);
                    customPageMap.put("systemTime",System.currentTimeMillis());
                    List<Map<String,Object>> customPageList = new LinkedList<>();
                    customPageList.add(customPageMap);
                    if (instance.channelStatus(macFormate)) {
                        System.out.println("===================[" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis()) + "]==================Channel connectivity");
                        instance.sendMessage(macFormate,new Gson().toJson(customPageList));
                        System.out.println("===============Data sent successfully=============" + new Gson().toJson(customPageList));
                    } else {
                        System.out.println("===================[" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis()) + "]==================Channel unconnected");
                        Object obj = cacheService.getCacheByKey(macFormate + "_" + ClassCardCommand.CUSTOM_PUBLISH);
                        if (obj != null) {
                            customPageList = (List<Map<String, Object>>) obj;
                            boolean flag = true;
                            //循环判断取消发布的pageId是否在已发布的缓冲中存在。
                            for (Map<String, Object> map : customPageList) {
                                if (pageId.equals(map.get("versionId"))) {
                                    customPageList.remove(map);
                                    flag = false;
                                    break;
                                }
                            }
                            if (flag) {
                                customPageList.add(customPageMap);
                            }
                        }
                        cacheService.addCache(macFormate + "_" + ClassCardCommand.CUSTOM_PUBLISH, customPageList);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return resultEntity;
    }

    @RequestMapping(value = "/template/index")
    public String templateIndex(HttpServletRequest request, Model model) {
        int pageNum = getPageNum(request);
        int pageSize = getPageSize(request);

        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();

        PageInfo<ClassCardCustomTemplate> pageInfo = new PageInfo<>();
        if(subject.isAuthenticated()) {
            if (subject.hasRole(UserRoleType.ROLE_SCHOOLADMIN)) {
                pageService.findAllPageBySchoolId(user.getSchoolId(), pageNum, pageSize);
                pageInfo = templateService.findAllTemplateBySchoolId(user.getSchoolId(),pageNum,pageSize);
            } else {
                pageInfo = null;
            }
        }
        model.addAttribute("pageInfo",pageInfo);
        return "/classCard/custom/template/index";
    }

    /**
     * 编辑创建的自定义界面进行预览
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/preview")
    public String preview(HttpServletRequest request, Model model) {
        String pageId = request.getParameter("pageId");
        return "/classCard/custom/template/preview";
    }
}
