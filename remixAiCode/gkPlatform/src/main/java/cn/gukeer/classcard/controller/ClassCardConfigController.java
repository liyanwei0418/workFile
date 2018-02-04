package cn.gukeer.classcard.controller;

import cn.gukeer.classcard.modelView.ClassCardConfigScreenOffView;
import cn.gukeer.classcard.modelView.ClassCardConfigView;
import cn.gukeer.classcard.modelView.ClassCardView;
import cn.gukeer.classcard.persistence.entity.*;
import cn.gukeer.classcard.service.*;
import cn.gukeer.classcard.util.ClassCardCommand;
import cn.gukeer.common.controller.BasicController;
import cn.gukeer.common.entity.ResultEntity;
import cn.gukeer.common.utils.DateUtils;
import cn.gukeer.common.utils.PrimaryKey;
import cn.gukeer.platform.common.UserRoleType;
import cn.gukeer.platform.persistence.dao.A_GradeClassMapper;
import cn.gukeer.platform.persistence.dao.ClassRoomMapper;
import cn.gukeer.platform.persistence.dao.RefClassRoomMapper;
import cn.gukeer.platform.persistence.entity.User;
import cn.gukeer.platform.service.*;
import cn.gukeer.platform.service.impl.CacheServiceImpl;
import cn.gukeer.sync.netty.classCardAttendance.ClassCardServer;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.wulianedu.netty.server.ServerInstance;
import org.apache.commons.lang.StringUtils;
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
import java.util.*;

/**
 * Created by alpha on 18-1-16.
 */
@Controller
@RequestMapping(value = "/classcard")
public class ClassCardConfigController extends BasicController {

    @Autowired
    ClassCardService classCardService;

    @Autowired
    ClassCardConfigService classCardConfigService;

    @Autowired
    ClassCardConfigScreenOffRefService cccsorService;

    @Autowired
    ClassCardConfigRefService classCardConfigRefService;

    @Autowired
    CacheManager cacheManager;

    /**
     * 开关机配置
     */
    @RequestMapping(value = "/config/index")
    public String configIndex(HttpServletRequest request, Model model) {
        int pageNum = getPageNum(request);
        int pageSize = getPageSize(request);

        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();

        PageInfo<ClassCardConfigView> pageInfo = new PageInfo<>();
        if (subject.isAuthenticated()) {
            if (subject.hasRole(UserRoleType.ROLE_CLASSCARDADMIN) || subject.hasRole(UserRoleType.ROLE_SCHOOLADMIN)) {
                pageInfo = classCardConfigService.findAllConfigBySchoolId(user.getSchoolId(), pageNum, pageSize);
            } else {
                pageInfo = null;
            }
        }
        List<ClassCardConfigView> resultList = classCardConfigService.transforConfig(pageInfo.getList());
        pageInfo.setList(resultList);
        model.addAttribute("pageInfo", pageInfo);

        return "classCard/config/index";
    }

    @RequestMapping(value = "/config/edit")
    public String configEdit(HttpServletRequest request, Model model) {
        String classCardConfigId = getParamVal(request, "id");
        String option = getParamVal(request, "option");

        if (!"".equals(classCardConfigId) && classCardConfigId != null) {

            Subject subject = SecurityUtils.getSubject();
            User user = (User) subject.getPrincipal();

            if (subject.isAuthenticated()) {
                if (subject.hasRole(UserRoleType.ROLE_CLASSCARDADMIN) || subject.hasRole(UserRoleType.ROLE_SCHOOLADMIN)) {
                    ClassCardConfigView classCardConfigView = classCardConfigService.findOneClassCardConfigById(classCardConfigId);
                    classCardConfigView = classCardConfigService.transforConfigOne(classCardConfigView);
                    List<ClassCardConfigScreenOffView> cccsoViewList = cccsorService.findListClassCardScreenOffByCCCId(classCardConfigId);
                    String checkedIds = classCardConfigRefService.findClassCardConfigCheckedIds(classCardConfigId, user.getSchoolId());
                    model.addAttribute("classCardConfigScreen", cccsoViewList);
                    model.addAttribute("classCardConfig", classCardConfigView);
                    model.addAttribute("checkedIds", checkedIds);
                    model.addAttribute("option", option);
                }
            }
        }
        return "classCard/config/edit";
    }


    @Transactional
    @ResponseBody
    @RequestMapping(value = "/config/save", method = RequestMethod.POST)
    public ResultEntity saveClassCardConfig(HttpServletRequest request, Model model) {
        String id = getParamVal(request, "id");
        String name = getParamVal(request, "name");
        String startDate = getParamVal(request, "startDate");
        String endDate = getParamVal(request, "endDate");
        String week = getParamVal(request, "week");
        String startTime = getParamVal(request, "startTime");
        String endTime = getParamVal(request, "endTime");
        String screenOffWeek = getParamVal(request, "screenOffWeek");

        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        if (subject.isAuthenticated()) {
            if (!subject.hasRole(UserRoleType.ROLE_SCHOOLADMIN)) {
                return ResultEntity.newErrEntity("当前用户权限不足");
            }
        }
        if ("".equals(name) || "".equals(startDate) || "".equals(endDate) || "".equals(week)) {
            return ResultEntity.newErrEntity("参数填写不正确");
        }

        String screenOffTimeList = getParamVal(request, "screenOffTimeList");
        String classCard = getParamVal(request, "classCardList");

        if (!StringUtils.isNotEmpty(screenOffTimeList)) {
            return ResultEntity.newErrEntity("未配置息屏时间或配置错误");
        }
        if (!StringUtils.isNotEmpty(classCard)) {
            return ResultEntity.newErrEntity("未配置终端设备或配置错误");
        }

        //保存息屏时间数组，key存开始，value存结束
        HashMap<String, String> screenOffTimeMap = new HashMap<>();
        String[] timeArray = screenOffTimeList.split(";");
        for (int i = 0; i < timeArray.length; i++) {
            String[] temp = timeArray[i].split(",", -2);
            if ("undefined".equals(temp[0]) || "undefined".equals(temp[1])) {
                continue;
            }
            if ("".equals(temp[0]) && "".equals(temp[1])) {
                continue;
            }
            if ("".equals(temp[0]) || "".equals(temp[1])) {
                return ResultEntity.newErrEntity("息屏时间配置错误");
            }
            screenOffTimeMap.put(temp[0], temp[1]);
        }
        if (screenOffTimeMap.isEmpty()) {
            return ResultEntity.newErrEntity("息屏时间配置不能为空");
        }

        //保存执行此配置的班牌
        List<String> classCardList = new ArrayList<>();
        String[] classCards = classCard.split(",");
        Collections.addAll(classCardList, classCards);

        if (classCardList == null || screenOffTimeMap == null) {
            return ResultEntity.newErrEntity("时间段/终端设备,配置错误");
        }

        //时间格式进行转换，保存时间戳
        Long startDateLong = null;
        Long endDateLong = null;
        Long startTimeDLong = null;
        Long endTimeLong = null;
        try {
            startDateLong = DateUtils.stringToTimestamp(startDate, "yyyy-MM-dd");
            endDateLong = DateUtils.stringToTimestamp(endDate, "yyyy-MM-dd");
            startTimeDLong = DateUtils.stringToTimestamp(startTime, "HH:mm");
            endTimeLong = DateUtils.stringToTimestamp(endTime, "HH:mm");
        } catch (Exception e) {
            e.printStackTrace();
            return ResultEntity.newErrEntity("时间格式错误");
        }

        ClassCardConfig classCardConfig = new ClassCardConfig();
        classCardConfig.setName(name);
        classCardConfig.setWeek(week);
        classCardConfig.setStartDate(startDateLong);
        classCardConfig.setEndDate(endDateLong);
        classCardConfig.setStartTime(startTimeDLong);
        classCardConfig.setEndTime(endTimeLong);

        ServerInstance instance = ClassCardServer.getInstance();
        CacheServiceImpl cacheService = new CacheServiceImpl(cacheManager, "classcardcommand-cache");

        int count = 0;
        if ("".equals(id) || id == null) {
            String refId = PrimaryKey.get();
            classCardConfig.setId(refId);
            classCardConfig.setCreateBy(user.getId());
            classCardConfig.setCreateDate(System.currentTimeMillis());
            //insert
            try {
                count += classCardConfigService.insertClassCardNotify(classCardConfig);
                count += cccsorService.insertClassCardConfigScreenRef(refId, screenOffWeek, screenOffTimeMap);
                count += classCardConfigRefService.insertClassCardConfigRef(refId, user.getSchoolId(), classCardList);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            classCardConfig.setId(id);
            classCardConfig.setUpdateBy(user.getId());
            classCardConfig.setUpdateDate(System.currentTimeMillis());
            //udpate
            try {
                count += classCardConfigService.updateClassCardConfig(classCardConfig);
                count += cccsorService.updateClassCardConfigScreenRef(id, screenOffWeek, screenOffTimeMap);
                count += classCardConfigRefService.updateClassCardConfigRef(id, user.getSchoolId(), classCardList);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //判断条件需要修改，不严谨
        ResultEntity resultEntity = ResultEntity.newErrEntity("保存失败");
        if (count >= 0) {
            resultEntity = ResultEntity.newResultEntity("删除成功");

            if (null != classCardList && classCardList.size() > 0) {
                for (String classcardId : classCardList) {
                    ClassCard card = classCardService.findClassCardByClassCardId(classcardId);
                    String macFormate = card.getMacId().replace(":", "_").toUpperCase();

                    Map<String, Object> configMap = new HashMap<>();
                    configMap.put("command", ClassCardCommand.CONFIG_SWITCHGEAR);
                    Map<String, Object> dataMap = new HashMap<>();
                    dataMap.put("configName", name);
                    dataMap.put("startDateLong", DateUtils.millsToyyyyMMdd(startDateLong));
                    dataMap.put("endDateLong", DateUtils.millsToyyyyMMdd(endDateLong));
                    dataMap.put("startTimeDLong", DateUtils.formatTime(new Date(startTimeDLong)));
                    dataMap.put("endTimeLong", DateUtils.formatTime(new Date(endTimeLong)));
                    dataMap.put("week", week);

                    //息屏亮屏数据
                    List<Map<String, Object>> screenList = new ArrayList<>();
                    for (Map.Entry<String, String> entry : screenOffTimeMap.entrySet()) {
                        try {
                            Map<String, Object> screenMap = new HashMap<>();
                            screenMap.put("screenOnTime", entry.getKey());
                            screenMap.put("screenOffTime", entry.getValue());
                            screenList.add(screenMap);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    dataMap.put("screenOffTimeMap", screenList);
                    dataMap.put("screenOffWeek", screenOffWeek);
                    configMap.put("data", dataMap);

                    //管道连通,可推送数据
                    if (instance.channelStatus(macFormate)) {
                        System.out.println("===================[" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis()) + "]==================Channel connectivity");
                        instance.sendMessage(macFormate, new Gson().toJson(configMap));
                        System.out.println("===============Data sent successfully=============" + new Gson().toJson(configMap));
                        //推送失败放到缓存
                    } else {
                        System.out.println("===================[" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis()) + "]==================Channel unconnected");
                        cacheService.addCache(macFormate + "_" + ClassCardCommand.CONFIG_SWITCHGEAR, configMap);
                    }
                }
            }
        }
        return resultEntity;
    }

    @Transactional
    @ResponseBody
    @RequestMapping(value = "/config/multidelete")
    public ResultEntity deleteConfig(HttpServletRequest request, Model model) {
        String configId = getParamVal(request, "configId");
        ResultEntity resultEntity = ResultEntity.newErrEntity("删除失败");

        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();

        if (subject.isAuthenticated()) {
            if (!subject.hasRole(UserRoleType.ROLE_SCHOOLADMIN)) {
                return ResultEntity.newErrEntity("当前用户权限不足");
            }
        }

        if (!"".equals(configId) && configId != null) {
            int configCount = 0;
            String[] configIdArr = configId.split(",");
            if (configIdArr != null) {
                //逻辑删除？还是真的删除？  注意删除的顺序
                configCount += classCardConfigRefService.deleteClassCardConfigRef(configIdArr, user.getSchoolId());
                configCount += cccsorService.deleteClassCardConfigScreenRef(configIdArr);
                configCount += classCardConfigService.deleteClassCardConfig(configIdArr);
                try {
                    ServerInstance instance = ClassCardServer.getInstance();
                    CacheServiceImpl cacheService = new CacheServiceImpl(cacheManager, "classcardcommand-cache");

                    //遍历推送命令给删除配置的班牌
                    for (String cid : configIdArr) {
                        List<ClassCardConfigRef> configRefs = classCardConfigRefService.findRefByConfigId(cid);
                        for (ClassCardConfigRef ref : configRefs) {
                            if (StringUtils.isNotBlank(ref.getClassCardId())) {
                                ClassCard card = classCardService.findClassCardByClassCardId(ref.getClassCardId());
                                String macFormate = card.getMacId().replace(":", "_").toUpperCase();
                                //推送的数据
                                Map<String, Object> delConfigMap = new HashMap<>();
                                delConfigMap.put("command", ClassCardCommand.CONFIG_SWITCHGEAR);
                                delConfigMap.put("data", "");
                                //管道连通,可推送数据
                                if (instance.channelStatus(macFormate)) {
                                    System.out.println("===================[" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis()) + "]==================Channel connectivity");
                                    instance.sendMessage(macFormate, new Gson().toJson(delConfigMap));
                                    System.out.println("===============Data sent successfully=============" + new Gson().toJson(delConfigMap));
                                    //推送失败放到缓存
                                } else {
                                    System.out.println("===================[" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis()) + "]==================Channel unconnected");
                                    cacheService.addCache(macFormate + "_" + ClassCardCommand.CONFIG_SWITCHGEAR, delConfigMap);
                                }
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //判断条件不严谨
                if (configCount >= 3 * configIdArr.length) {
                    resultEntity = ResultEntity.newResultEntity("删除成功");
                }
            }
        }
        return resultEntity;
    }

    //与添加班牌选择设备的那个接口是相同的，调用同一个service
    @RequestMapping(value = "/config/chooseclasscardConfig")
    public String chooseConfigClassCard(HttpServletRequest request, Model model) {
        String checkedIds = getParamVal(request, "checkedIds");
        String disabled = getParamVal(request, "disabled");
        Map<String, Map<String, List<ClassCardView>>> resultMap = classCardService.selectEquipmentForNotify();
        JsonObject returnData = new JsonParser().parse(new Gson().toJson(resultMap)).getAsJsonObject();
        model.addAttribute("returnData", returnData);
        model.addAttribute("checkedIds", checkedIds);
        model.addAttribute("disabled", disabled);
        return "classCard/config/chooseclasscardConfig";
    }
}
