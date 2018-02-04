package cn.gukeer.classcard.controller;

import cn.gukeer.classcard.modelView.CheckAttendanceView;
import cn.gukeer.classcard.modelView.ClassCardNotifyView;
import cn.gukeer.classcard.modelView.ClassIntroductionView;
import cn.gukeer.classcard.persistence.entity.ClassCard;
import cn.gukeer.classcard.persistence.entity.ClassSpacePicture;
import cn.gukeer.classcard.persistence.entity.ClassSpaceVideo;
import cn.gukeer.classcard.persistence.entity.SchoolCulture;
import cn.gukeer.classcard.service.ClassCardNotifyService;
import cn.gukeer.classcard.service.ClassCardService;
import cn.gukeer.classcard.service.ClassSpacePictureService;
import cn.gukeer.classcard.service.ClassSpaceVideoService;
import cn.gukeer.classcard.util.ClassCardCommand;
import cn.gukeer.common.controller.BasicController;
import cn.gukeer.common.entity.ResultEntity;
import cn.gukeer.common.security.MD5Utils;
import cn.gukeer.common.utils.DateUtils;
import cn.gukeer.common.utils.HttpRequestUtil;
import cn.gukeer.common.utils.PropertiesUtil;
import cn.gukeer.common.utils.StringUtils;
import cn.gukeer.platform.common.CourseUtil;
import cn.gukeer.platform.modelView.*;
import cn.gukeer.platform.modelView.weather.HeWeather;
import cn.gukeer.platform.persistence.dao.A_GradeClassMapper;
import cn.gukeer.platform.persistence.entity.*;
import cn.gukeer.platform.service.*;
import cn.gukeer.platform.service.impl.CacheServiceImpl;
import cn.gukeer.sync.netty.classCardAttendance.ClassCardServer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.StringUtil;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.wulianedu.netty.server.ServerInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Controller;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.*;

/*
 * Created by alpha on 17-7-7.
*/

@Controller
@RequestMapping(value = "/api/classcard")
public class ApiClassCardController extends BasicController {

    public static final String PASSWORD = "vqATIY";
    public static final String CHECKATTENDANCE_INCREMENT = "0b924cb2958d7620";

    @Autowired
    SchoolService schoolService;

    @Autowired
    ClassCardNotifyService classCardNotifyService;

    @Autowired
    TeachTaskService teachTaskService;

    @Autowired
    TeacherService teacherService;

    @Autowired
    ClassCardService classCardService;

    @Autowired
    ClassSpacePictureService classSpacePictureService;

    @Autowired
    A_GradeClassMapper a_gradeClassMapper;

    @Autowired
    ClassService classService;

    @Autowired
    StudentService studentService;

    @Autowired
    CacheManager cacheManager;

    @Autowired
    ClassSpaceVideoService classSpaceVideoService;

    /**
     * url: http://serverIp:port/platform/api/classcard/heweather<br>
     *
     * @param request security: app端加密过的密文  time： app端请求时系统时间 random:app生成的随机数
     * @return
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     */
    @ResponseBody
    @RequestMapping(value = "/heweather")
    public ResultEntity weatherApi(HttpServletRequest request) {
        String checksum = getParamVal(request, "security");
        String macId = getParamVal(request, "macId");
        if ("".equals(macId)) {
            return ResultEntity.newErrEntity("mac地址为空");
        }
        String time = getParamVal(request, "time");
        String random = getParamVal(request, "random");

        if ("".equals(macId)) {
            return ResultEntity.newErrEntity("mac地址为空");
        }
        StringBuffer paramString = new StringBuffer();
        paramString.append(macId).append(time).append(random).append(PASSWORD);
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        String token = null;
        try {
            token = Base64Utils.encodeToString(md5.digest(paramString.toString().getBytes("utf-8")));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (!token.equals(checksum)) {
            return ResultEntity.newErrEntity("传入数据有误");
        }

        Map<String, String> returnMap = new HashMap<>();
        ClassCard classCard = classCardService.selectClassCardByMacId(macId);
        if (classCard != null && StringUtils.isNotEmpty(classCard.getSchoolId())) {
            if ("".equals(classCard.getSchoolId())) {
                return ResultEntity.newErrEntity("学校ID为空");
            } else {
                String weatherStr = schoolService.getWeather(classCard.getSchoolId());
                Gson gson = new Gson();
                HeWeather heWeather = gson.fromJson(weatherStr,
                        new TypeToken<HeWeather>() {
                        }.getType());

                if (heWeather != null) {
                    returnMap.put("txt_d", heWeather.getDaily_forecast().get(0).getCond().getTxt_d());
                    returnMap.put("code_d", heWeather.getDaily_forecast().get(0).getCond().getCode_d());
                    returnMap.put("max", heWeather.getDaily_forecast().get(0).getTmp().getMax());
                    returnMap.put("min", heWeather.getDaily_forecast().get(0).getTmp().getMin());
                    returnMap.put("hum", heWeather.getDaily_forecast().get(0).getHum());
                }
            }
        }
        return ResultEntity.newResultEntity(ResultEntity.OK_CODE, ResultEntity.OK_MSG, returnMap);
    }


    /**
     * 以周为单位 课表数据推送的方法
     * <p>
     * url: http://serverIp:port/platform/api/classcard/teach/table<br>
     *
     * @param request security: app端加密过的密文  time： app端请求时系统时间 random:app生成的随机数
     * @return
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     */
    @ResponseBody
    @RequestMapping(value = "/teach/table")
    public ResultEntity teachTableApi(HttpServletRequest request) {

        // String schoolId = getParamVal(request, "schoolId");
        String checksum = getParamVal(request, "security").replace(" ", "+");
//        System.out.println(getParamVal(request, "security").replaceAll(" ", "+"));
        String macId = getParamVal(request, "macId");
        String time = getParamVal(request, "time");
        String random = getParamVal(request, "random");

        StringBuffer paramString = new StringBuffer();
        paramString.append(macId).append(time).append(random).append(PASSWORD);
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        String token = null;
        try {
            token = Base64Utils.encodeToString(md5.digest(paramString.toString().getBytes("utf-8")));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (!token.equals(checksum)) {
            return ResultEntity.newErrEntity("传入数据有误");
        }

        Map<String, List<Map<Integer, TeachTableView>>> mapMap = new LinkedHashMap<>();
        if ("".equals(macId)) {
            return ResultEntity.newErrEntity("ｍａｃ地址为空");
        } else {

            //根据classCardId拿到classId
            ClassCard classCard = classCardService.selectClassCardByMacId(macId);
            if (classCard == null) {
                return ResultEntity.newErrEntity("未找到mac对应的班牌");
            }

            if (StringUtils.isEmpty(classCard.getClassId())) {
                return ResultEntity.newErrEntity("班牌未绑定班级信息");
            }
            String classId = classCard.getClassId();
            GradeClass gradeClass = classService.selectClassById(classId);

            //根据学校Id查询最近的教学周期，获取开学时间
            TeachCycle teachCycle = new TeachCycle();
            if (gradeClass != null) {
                teachCycle = getLatestTeachCycle(gradeClass.getSchoolId());
            }
            Long termBeginTime = 0L;
            String cycleId = "";
            if (teachCycle.getId() != null) {
                termBeginTime = teachCycle.getTermBeginTime();
                cycleId = teachCycle.getId();
            }

            Long currenttime = System.currentTimeMillis();

            Integer currentWeek = 1;
            if (currenttime > termBeginTime) {
                //获取今天是星期几
                Integer weekofoneday = Calendar.getInstance().get(Calendar.DAY_OF_WEEK) - 1;
                if (weekofoneday == 0) {
                    weekofoneday = 7;
                }

                //获取开学日期是星期几
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(termBeginTime);
                int startweekofoneday = calendar.get(Calendar.DAY_OF_WEEK) - 1;
                if (startweekofoneday == 0) {
                    startweekofoneday = 7;
                }

                Long zhengshu = (currenttime - termBeginTime) / 1000 / 60 / 60 / 24 - weekofoneday + 1;
                System.out.println(zhengshu);
                if (zhengshu % 7 > 0) {
                    currentWeek = (int) (zhengshu / 7 + 2);
                } else {
                    currentWeek = (int) (zhengshu / 7 + 2);
                }
            }
            DailyHour dailyHour = teachTaskService.fingDailyHourByCycleIdAndClassId(cycleId, classId);
            Integer totalHour = 0;
            List<Integer> integers = new ArrayList<>();
            if (dailyHour != null) {
                totalHour = dailyHour.getSwks() + dailyHour.getXwks();
                for (int i = 0; i < totalHour; i++) {
                    integers.add(i);
                }
            }
            //获取所有课表数据
            //String schoolId, Integer currentweek,Integer pageNum,Integer pageSize,String classId,List<Integer> nodeList,String cycleId
            PageInfo<TeachTableView> pageInfo = teachTaskService.findTeachTableByCurrentCycleWeekAndSchoolId(gradeClass.getSchoolId(), currentWeek, 0, 0, classId, integers, cycleId);

            //数字和字符串星期几之间的转换
            List<TeachTableView> teachTableViews = pageInfo.getList();

            if (teachTableViews != null && teachTableViews.size() > 0) {

                //总节数
                List<Integer> totalNodeList = new ArrayList<>();
                for (int n = 0; n < totalHour; n++) {
                    totalNodeList.add(n);
                }
                if (dailyHour != null) {

                    Integer skts = dailyHour.getSkts();
                    for (int k = 1; k <= skts; k++) {
                        Map<Integer, TeachTableView> map = null;
                        List<Map<Integer, TeachTableView>> teachTableViewMaps = new ArrayList<>();
                        //实际存在的节数
                        List<Integer> totalExistNodeList = new ArrayList<>();
                        List<TeachTableView> totalExistTeachViewList = new ArrayList<>();
                        for (TeachTableView teachTableView : teachTableViews) {
                            if (k == Integer.parseInt(teachTableView.getWeekDay())) {
                                totalExistTeachViewList.add(teachTableView);
                            }
                        }
                        for (int m = 0; m < totalExistTeachViewList.size(); m++) {
                            String node = totalExistTeachViewList.get(m).getNode();
                            totalExistNodeList.add(Integer.parseInt(node));
                        }

                        //不存在的节数
                        List<Integer> unExist = new ArrayList<Integer>();
                        for (Integer l : totalNodeList) {
                            if (!totalExistNodeList.contains(l)) {
                                unExist.add(l);
                            }
                        }

                        if (unExist.size() > 0) {
                            for (Integer oneUnExist : unExist) {
                                map = new TreeMap<>();
                                map.put(oneUnExist, new TeachTableView());
                                teachTableViewMaps.add(map);
                            }
                        }

                        for (int i = 0; i < totalHour; i++) {
                            int index = 1;
                            for (TeachTableView teachTableView : totalExistTeachViewList) {
                                if (k == Integer.parseInt(teachTableView.getWeekDay())) {
                                    map = new TreeMap<>();
                                    if (i == Integer.parseInt(teachTableView.getNode())) {
                                        teachTableView.setWeekend(String.valueOf(currentWeek));
                                        map.put(Integer.parseInt(teachTableView.getNode()), teachTableView);
                                        teachTableViewMaps.add(map);
                                    }

                                    if (index == totalExistTeachViewList.size()) {
                                        Collections.sort(teachTableViewMaps, new Comparator<Map<Integer, TeachTableView>>() {
                                            @Override
                                            public int compare(Map<Integer, TeachTableView> o1, Map<Integer, TeachTableView> o2) {
                                                if (o1.keySet().iterator().next() - o2.keySet().iterator().next() > 0) {
                                                    return 1;
                                                } else {
                                                    return -1;
                                                }
                                            }
                                        });


                                        if (teachTableViewMaps.size() > 10) {
                                            return ResultEntity.newErrEntity("课节大于10了");
                                        }


                                        if (k == 1) {
                                            mapMap.put("monday", teachTableViewMaps);
                                        }
                                        if (k == 2) {
                                            mapMap.put("tuesday", teachTableViewMaps);
                                        }
                                        if (k == 3) {
                                            mapMap.put("wednesday", teachTableViewMaps);
                                        }
                                        if (k == 4) {
                                            mapMap.put("thursday", teachTableViewMaps);
                                        }
                                        if (k == 5) {
                                            mapMap.put("friday", teachTableViewMaps);
                                        }
                                    }
                                    index++;
                                }
                            }
                        }
                    }
                }
            }
        }
        return ResultEntity.newResultEntity(ResultEntity.OK_CODE, ResultEntity.OK_MSG, mapMap);
    }

    /**
     * url: http://serverIp:port/platform/api/classcard/notify<br>
     * 通知公告
     *
     * @param request security: app端加密过的密文  time： app端请求时系统时间 random:app生成的随机数
     * @return
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     */
    @ResponseBody
    @RequestMapping(value = "/notify")
    public ResultEntity notifyApi(HttpServletRequest request) {

        String checksum = getParamVal(request, "security");
        String macId = getParamVal(request, "macId");
        String time = getParamVal(request, "time");
        String random = getParamVal(request, "random");

        StringBuffer paramString = new StringBuffer();
        paramString.append(macId).append(time).append(random).append(PASSWORD);
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        String token = null;
        try {
            token = Base64Utils.encodeToString(md5.digest(paramString.toString().getBytes("utf-8")));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (!token.equals(checksum)) {
            return ResultEntity.newErrEntity("传入数据有误");
        }
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        System.out.println(s.format(DateUtils.getFirstOfCurrentMonth(new Date())));
//        System.out.println(s.format(DateUtils.getLastOfCurrentMonth(new Date())));

        Map<String, List<ClassCardNotifyView>> returnMap = new HashMap<>();
        if ("".equals(macId)) {
            return ResultEntity.newErrEntity("ｍａｃ地址为空");
        } else {
            ClassCard classCard = classCardService.selectClassCardByMacId(macId);
            if (null != classCard) {
                List<ClassCardNotifyView> allViews = classCardNotifyService.findAllNotifyViewByClassCardId(classCard.getId());
                if (allViews == null || allViews.size() <= 0) {
                    returnMap.put("emptyNotify", allViews);
                } else {
                    String flag = getParamVal(request, "flag");
                    //如果传入flag 为select_all 则查询结果不分组
                    if ("select_all".equals(flag)) {
                        List<ClassCardNotifyView> allNotifys = new ArrayList<>();
                        for (ClassCardNotifyView cv : allViews) {
                            cv = classCardNotifyService.transfornotifyDate(cv);
                            allNotifys.add(cv);
                        }
                        returnMap.put("allNotifys", allNotifys);
                    } else {
                        List<ClassCardNotifyView> schoolNotifys = new ArrayList<>();
                        List<ClassCardNotifyView> classNotifys = new ArrayList<>();
                        Date firstDay = DateUtils.getFirstOfCurrentMonth(new Date());
                        Date lastDay = DateUtils.getLastOfCurrentMonth(new Date());
                        for (ClassCardNotifyView cv : allViews) {
                            cv = classCardNotifyService.transfornotifyDate(cv);
                            Calendar c = Calendar.getInstance();
                            c.setTime(new Date(cv.getCreateDate()));
                            c.add(Calendar.MONTH, 1);
                            if (cv.getType() == 2 && System.currentTimeMillis() <= c.getTime().getTime()) {
                                schoolNotifys.add(cv);
                            } else if (cv.getType() == 1 && System.currentTimeMillis() <= c.getTime().getTime()) {
                                classNotifys.add(cv);
                            }
                        }
                        returnMap.put("schoolNotifys", schoolNotifys);
                        returnMap.put("classNotifys", classNotifys);
                    }
                }
            }
        }
        return ResultEntity.newResultEntity(ResultEntity.OK_CODE, ResultEntity.OK_MSG, returnMap);
    }

    /**
     * url: http://serverIp:port/platform/api/classcard/classspace/introduction<br>
     * 班级空间－班级简介
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/classspace/introduction")
    public ResultEntity classSpaceIntroduction(HttpServletRequest request) {

        String checksum = getParamVal(request, "security");
        String macId = getParamVal(request, "macId");
        if ("".equals(macId)) {
            return ResultEntity.newErrEntity("mac地址为空");
        }
        String time = getParamVal(request, "time");
        String random = getParamVal(request, "random");

        StringBuffer paramString = new StringBuffer();
        paramString.append(macId).append(time).append(random).append(PASSWORD);
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        String token = null;
        try {
            token = Base64Utils.encodeToString(md5.digest(paramString.toString().getBytes("utf-8")));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (!token.equals(checksum)) {
            return ResultEntity.newErrEntity("传入数据有误");
        }
        ClassCard classCard = classCardService.selectClassCardByMacId(macId);
        ClassIntroductionView classIntroductionView = new ClassIntroductionView();
        if (null != classCard) {
            classIntroductionView = classCardService.selectViewByclassCardId(classCard.getId());
            int serverPort = request.getServerPort();
            String serverName = request.getServerName();
            //   /platform/video/1501300700321.mp4
           /* if (classIntroductionView != null && StringUtils.isNotEmpty(classIntroductionView.getDisplayInformationUrl())) {
                classIntroductionView.setDisplayInformationUrl("http://" + serverName + ":" + serverPort + "/platform/file/pic/show?picPath=" + classIntroductionView.getDisplayInformationUrl());
            }*/
            if (null != classIntroductionView) {
                if (StringUtils.isNotEmpty(classIntroductionView.getThumbnailUrl())) {
                    classIntroductionView.setThumbnailUrl("http://" + serverName + ":" + serverPort + "/platform/file/pic/show?picPath=" + classIntroductionView.getThumbnailUrl());
                } else if (StringUtils.isNotBlank(classIntroductionView.getPicUrl())) {
                    classIntroductionView.setPicUrl("http://" + serverName + ":" + serverPort + "/platform/file/pic/show?picPath=" + classIntroductionView.getPicUrl());
                } else {
                    classIntroductionView.setVideoUrl("http://" + serverName + ":" + serverPort + "/platform/file/play?" + classIntroductionView.getVideoUrl());
                }

            }
        }
        return ResultEntity.newResultEntity(classIntroductionView);
    }

    /**
     * url: http://serverIp:port/platform/api/classcard/classspace/growth/collection<br>
     * 班级空间－成长足迹合集
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/classspace/growth/collection")
    public ResultEntity growthCollection(HttpServletRequest request) {

        String checksum = getParamVal(request, "security");
        String macId = getParamVal(request, "macId");
        if ("".equals(macId)) {
            return ResultEntity.newErrEntity("mac地址为空");
        }
        String time = getParamVal(request, "time");
        String random = getParamVal(request, "random");

        StringBuffer paramString = new StringBuffer();
        paramString.append(macId).append(time).append(random).append(PASSWORD);
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        String token = null;
        try {
            token = Base64Utils.encodeToString(md5.digest(paramString.toString().getBytes("utf-8")));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (!token.equals(checksum)) {
            return ResultEntity.newErrEntity("传入数据有误");
        }
        ClassCard classCard = classCardService.selectClassCardByMacId(macId);
        Map<String, Object> retMap = new HashMap<>();
        if (null != classCard) {
            List<ClassSpacePicture> pictureCollectionList = combinationFullpath_list(classSpacePictureService.findPicByClassCardIdAndPid(classCard.getId(), "-1"), request);

            Map<String, List<ClassSpacePicture>> twoPics = new HashMap<>();
            if (pictureCollectionList != null && pictureCollectionList.size() > 0) {
                for (ClassSpacePicture cp : pictureCollectionList) {
                    List<ClassSpacePicture> pictures = combinationFullpath_list(classSpacePictureService.findPicByClassCardIdAndPid(classCard.getId(), cp.getId()), request);
                    List<ClassSpacePicture> retPic = new ArrayList<>();
                    if (pictures.size() >= 2) {
                        retPic.add(pictures.get(0));
                        retPic.add(pictures.get(1));
                    } else if (pictures.size() == 1) {
                        retPic.add(pictures.get(0));
                    }
                    twoPics.put(cp.getPicTitle(), retPic);
                }
            }
            retMap.put("collections", pictureCollectionList);
            retMap.put("twoPic", twoPics);
        }
        return ResultEntity.newResultEntity(retMap);
    }

    /**
     * url: http://serverIp:port/platform/api/classcard/classspace/growth/detail<br>
     * 班级空间－成长足迹合集内容
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/classspace/growth/detail")
    public ResultEntity growthDetil(HttpServletRequest request) {

        String checksum = getParamVal(request, "security");
        String macId = getParamVal(request, "macId");
        if ("".equals(macId)) {
            return ResultEntity.newErrEntity("mac地址为空");
        }
        String time = getParamVal(request, "time");
        String random = getParamVal(request, "random");

        StringBuffer paramString = new StringBuffer();
        paramString.append(macId).append(time).append(random).append(PASSWORD);
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        String token = null;
        try {
            token = Base64Utils.encodeToString(md5.digest(paramString.toString().getBytes("utf-8")));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (!token.equals(checksum)) {
            return ResultEntity.newErrEntity("传入数据有误");
        }
        String collectionId = getParamVal(request, "collectionId");
        if (StringUtils.isNotEmpty(collectionId)) {
            ClassCard classCard = classCardService.selectClassCardByMacId(macId);
            if (null != classCard) {
                List<ClassSpacePicture> pictures = combinationFullpath_list(classSpacePictureService.findPicByClassCardIdAndPid(classCard.getId(), collectionId), request);
                return ResultEntity.newResultEntity(pictures);
            }
        }
        return null;
    }

    /**
     * url: http://serverIp:port/platform/api/classcard/classspace/baige<br>
     * 班级空间－百舸争流
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/classspace/baige")
    public ResultEntity baigezhengliu(HttpServletRequest request) {
        String checksum = getParamVal(request, "security");
        String macId = getParamVal(request, "macId");
        if ("".equals(macId)) {
            return ResultEntity.newErrEntity("mac地址为空");
        }
        String time = getParamVal(request, "time");
        String random = getParamVal(request, "random");

        StringBuffer paramString = new StringBuffer();
        paramString.append(macId).append(time).append(random).append(PASSWORD);
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        String token = null;
        try {
            token = Base64Utils.encodeToString(md5.digest(paramString.toString().getBytes("utf-8")));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (!token.equals(checksum)) {
            return ResultEntity.newErrEntity("传入数据有误");
        }
        ClassCard classCard = classCardService.selectClassCardByMacId(macId);
        if (null != classCard) {
            List<ClassSpacePicture> pictures = combinationFullpath_list(classSpacePictureService.findPicByClassCardIdAndPid(classCard.getId(), "bgzl"), request);
            return ResultEntity.newResultEntity(pictures);
        }
        return null;
    }

    /**
     * url: http://serverIp:port/platform/api/classcard/classspace/baige<br>
     * 班级空间－活动掠影
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/classspace/activemoment")
    public ResultEntity activemoment(HttpServletRequest request) {
        String checksum = getParamVal(request, "security");
        String macId = getParamVal(request, "macId");
        if ("".equals(macId)) {
            return ResultEntity.newErrEntity("ｍａｃ地址为空");
        }
        String time = getParamVal(request, "time");
        String random = getParamVal(request, "random");

        StringBuffer paramString = new StringBuffer();
        paramString.append(macId).append(time).append(random).append(PASSWORD);
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        String token = null;
        try {
            token = Base64Utils.encodeToString(md5.digest(paramString.toString().getBytes("utf-8")));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (!token.equals(checksum)) {
            return ResultEntity.newErrEntity("传入数据有误");
        }
        ClassCard classCard = classCardService.selectClassCardByMacId(macId);
        if (null != classCard) {
            List<ClassSpacePicture> pictures = combinationFullpath_list(classSpacePictureService.findPicByClassCardIdAndPid(classCard.getId(), "hdly"), request);
            return ResultEntity.newResultEntity(pictures);
        }
        return null;
    }

    /**
     * url: http://serverIp:port/platform/api/classcard/classspace/index/threepics<br>
     * 班级空间－首页合集３张照片
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/classspace/index/threepics")
    public ResultEntity indexThreePics(HttpServletRequest request) {
        String macId = getParamVal(request, "macId");
        if ("".equals(macId)) {
            return ResultEntity.newErrEntity("mac地址为空");
        }
        ClassCard classCard = classCardService.selectClassCardByMacId(macId);
        String classId = "";
        if (null != classCard && classCard.getClassId() != null) {
            classId = classCard.getClassId();
        }
        if ("".equals(classId)) {
            return ResultEntity.newErrEntity("该班牌未绑定班级");
        }
        String time = getParamVal(request, "time");
        String random = getParamVal(request, "random");
        String checksum = getParamVal(request, "security");
        StringBuffer paramString = new StringBuffer();
        paramString.append(macId).append(time).append(random).append(PASSWORD);
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        String token = null;
        try {
            token = Base64Utils.encodeToString(md5.digest(paramString.toString().getBytes("utf-8")));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (!token.equals(checksum)) {
            return ResultEntity.newErrEntity("传入数据有误");
        }
        List<ClassSpacePicture> pictures = combinationFullpath_list(classSpacePictureService.findThreePicForIndex(classId), request);
        return ResultEntity.newResultEntity(pictures);
    }

    /**
     * url: http://serverIp:port/platform/api/classcard/getclass<br>
     * 根据ｍａｃ地址取得班级信息
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getclass")
    public ResultEntity getClassByMac(HttpServletRequest request) {
        String macId = getParamVal(request, "macId");
        if ("".equals(macId)) {
            return ResultEntity.newErrEntity("mac地址为空");
        }
        String checksum = getParamVal(request, "security");
        String time = getParamVal(request, "time");
        String random = getParamVal(request, "random");

        StringBuffer paramString = new StringBuffer();
        paramString.append(macId).append(time).append(random).append(PASSWORD);
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        String token = null;
        try {
            token = Base64Utils.encodeToString(md5.digest(paramString.toString().getBytes("utf-8")));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (!token.equals(checksum)) {
            return ResultEntity.newErrEntity("传入数据有误");
        }

        ClassCard classCard = classCardService.selectClassCardByMacId(macId);
        GradeClass_view gradeClassExtention = new GradeClass_view();
        List<Student> students = new ArrayList<>();
        Map<String, Object> resultMap = new HashMap<>();
        if (classCard != null && StringUtils.isNotEmpty(classCard.getClassId())) {
            gradeClassExtention = a_gradeClassMapper.findByClassId(classCard.getClassId());
            gradeClassExtention.setClassCardShowName(classCard.getClassroom());
            if (StringUtils.isNotEmpty(classCard.getSchoolId())) {
                students = studentService.selectStudentByclassId(classCard.getSchoolId(), classCard.getClassId());
            }
        }

        resultMap.put("classInfo", gradeClassExtention);
        resultMap.put("students", students);

        return ResultEntity.newResultEntity(resultMap);
    }

    /**
     * 当天课表
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/today-schedule")
    public ResultEntity todaySchedule(HttpServletRequest request) {
        String checksum = getParamVal(request, "security").replace(" ", "+");
//        System.out.println(getParamVal(request, "security").replaceAll(" ", "+"));
        String macId = getParamVal(request, "macId");
        String time = getParamVal(request, "time");
        String random = getParamVal(request, "random");

        StringBuffer paramString = new StringBuffer();
        paramString.append(macId).append(time).append(random).append(PASSWORD);
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        String token = null;
        try {
            token = Base64Utils.encodeToString(md5.digest(paramString.toString().getBytes("utf-8")));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (!token.equals(checksum)) {
            return ResultEntity.newErrEntity("传入数据有误");
        }

        if ("".equals(macId)) {
            return ResultEntity.newErrEntity("ｍａｃ地址为空");
        } else {

            //根据classCardId拿到classId
            ClassCard classCard = classCardService.selectClassCardByMacId(macId);
            if (null == classCard) {
                return ResultEntity.newErrEntity("未找到mac对应的班牌");
            }

            if (StringUtils.isEmpty(classCard.getClassId())) {
                return ResultEntity.newErrEntity("班牌未绑定班级信息");
            }

            List<TeachTableView> currentTeachTable = CourseUtil.todaySchedule(macId);

            for (TeachTableView tv : currentTeachTable) {
                tv.setCourseStartTime(new SimpleDateFormat("HH:mm:ss").format(new Date(tv.getStartTime())));
                tv.setCourseEndTime(new SimpleDateFormat("HH:mm:ss").format(new Date(tv.getEndTime())));
                //教师头像
                if (StringUtils.isNotEmpty(tv.getTeacherId())) {
                    Teacher teacher = teacherService.findTeacherById(tv.getTeacherId());
                    if (teacher != null) {
                        ///platform/file/pic/show?picPath=images/default_tou.png
                        int serverPort = request.getServerPort();
                        String serverName = request.getServerName();
                        //教师头像
                        if (StringUtils.isNotEmpty(teacher.getHeadUrl())) {
                            tv.setTeacherHeadImg("http://" + serverName + ":" + serverPort + "/platform/file/pic/show?picPath=" + teacher.getHeadUrl());
                        } else {
                            tv.setTeacherHeadImg("http://" + serverName + ":" + serverPort + "/platform/file/pic/show?picPath=images/default_tou.png");
                        }
                    }
                } else {
                    tv.setTeacherHeadImg("");
                }
            }
            return ResultEntity.newResultEntity(currentTeachTable);
        }
    }


    /**
     * 实时考勤
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/checkattendance/increment", method = RequestMethod.POST)
    public ResultEntity increment(HttpServletRequest request) {

        String data = getParamVal(request, "data");

        System.out.println("===================[" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis()) + "]==================data is :" + data);
        if (StringUtils.isEmpty(data)) {
            return ResultEntity.newErrEntity("data is null");
        }

        JsonParser jsonParser = new JsonParser();
        JsonElement element = jsonParser.parse(data);
        JsonObject jsonObject = element.getAsJsonObject();
        String roomId = jsonObject.get("roomId").getAsString();

        String r = jsonObject.get("r").getAsString();
        String date = jsonObject.get("date").getAsString();
        String sign = jsonObject.get("sign").getAsString();
        StringBuilder paramString = new StringBuilder();
        paramString.append(date).append(r).append(CHECKATTENDANCE_INCREMENT);
        String token = MD5Utils.md5(paramString.toString());

        if (!sign.equals(token)) {
            return ResultEntity.newErrEntity("Illegal signature");
        }

        Object JsonData = jsonObject.get("data");
        if (null == JsonData) {
            return ResultEntity.newErrEntity("data is null");
        }

        RefClassRoom refClassRoom = teachTaskService.findClassRoomByRoomId(roomId);
        if (StringUtils.isEmpty(refClassRoom.getGradeClass())) {
            return ResultEntity.newErrEntity();
        }
        GradeClass gradeClass = classService.selectClassById(refClassRoom.getGradeClass());
        if (null == gradeClass) {
            return ResultEntity.newErrEntity();
        }

        ClassCard classCard = classCardService.selectClassCardByTeachClassRoomId(roomId);
        if (null == classCard) {
            return ResultEntity.newErrEntity();
        }

        CheckAttendanceView view = new CheckAttendanceView();
        view.setDate(jsonObject.get("date").getAsLong());
        view.setLessonNo(jsonObject.get("lessonNo").getAsInt());
        view.setRoomId(jsonObject.get("roomId").getAsString());
        view.setWeekendNo(jsonObject.get("weekendNo").getAsInt());

        view.setStudentStatus(jsonArrayToList(jsonObject.get("data").getAsJsonArray()));
        //推送考勤增量信息
        ServerInstance instance = ClassCardServer.getInstance();
        CacheServiceImpl cacheService = new CacheServiceImpl(cacheManager, "classcard-cache");

        String macFormate = classCard.getMacId().replace(":", "_").toUpperCase();
//        String macFormate ="22_22_33_24_4D_91";
        System.out.println("===================[" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis()) + "]==================FORMATMAC  IS" + macFormate);

        //管道连通,可推送数据
        if (instance.channelStatus(macFormate)) {
            System.out.println("===================[" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis()) + "]==================Channel connectivity");
            //最新的数据
            List<CheckAttendanceView> dataList = new ArrayList();
            dataList.add(view);

            Map<String,Object> attdenceMap=new HashMap<>();
            attdenceMap.put("command", ClassCardCommand.CLASSCARD_ATTENDANCE);
            attdenceMap.put("data",dataList);
            instance.sendMessage(macFormate, new Gson().toJson(attdenceMap));
            System.out.println("===============Data sent successfully=============" + new Gson().toJson(attdenceMap));
        }
        //推送失败放到缓存
        else {
            System.out.println("===================[" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis()) + "]==================Channel unconnected");
            Object obj = cacheService.getCacheByKey(macFormate);
            if (null != obj) {
                List<CheckAttendanceView> oldList = (ArrayList<CheckAttendanceView>) obj;
                oldList.add(view);
                cacheService.addCache(macFormate, oldList);
            } else {
                List<CheckAttendanceView> addList = new ArrayList<CheckAttendanceView>();
                addList.add(view);
                cacheService.addCache(macFormate, addList);
            }
        }
        return ResultEntity.newResultEntity();
    }

    /**
     * url: http://serverIp:port/platform/api/classcard/checkattendance/count<br>
     * 考勤统计
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/checkattendance/count")
    public ResultEntity checkAttendanceCount(HttpServletRequest request) throws IOException {
        String checksum = getParamVal(request, "security");
        String macId = getParamVal(request, "macId");
        if ("".equals(macId)) {
            return ResultEntity.newErrEntity("mac地址为空");
        }
        String time = getParamVal(request, "time");
        String random = getParamVal(request, "random");

        StringBuffer paramString = new StringBuffer();
        paramString.append(macId).append(time).append(random).append(PASSWORD);
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        String token = null;
        try {
            token = Base64Utils.encodeToString(md5.digest(paramString.toString().getBytes("utf-8")));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (!token.equals(checksum)) {
            return ResultEntity.newErrEntity("传入数据有误");
        }
        ClassCard classCard = classCardService.selectClassCardByMacId(macId);
        if (classCard == null) {
            return ResultEntity.newErrEntity("该mac未绑定班牌");
        }
        if (StringUtils.isEmpty(classCard.getTeachClassRoomId())) {
            return ResultEntity.newErrEntity("班牌未绑定教室");
        }
        String roomId = classCard.getTeachClassRoomId();
        String schoolId = classCard.getSchoolId();
      /*  String schoolId="23";
        String roomId="49e451e64e6b454c23f0038d67eb7d21";*/

        Properties pro = PropertiesUtil.getProperties("api.properties");
        String url = pro.getProperty("smartRing.url");
        StringBuilder stringBuilder = new StringBuilder(url + "/classcard/get/classtable");
        if (!"".equals(roomId) && !"".equals(schoolId)) {
            Map param = new HashMap();
            param.put("roomId", roomId);
            param.put("schoolId", schoolId);
            long current = System.currentTimeMillis();
            //零点时间戳
            long zero = current / (1000 * 3600 * 24) * (1000 * 3600 * 24) - TimeZone.getDefault().getRawOffset();
            param.put("dayTime", zero);
            String resultStr = HttpRequestUtil.get(stringBuilder.toString(), null, param);

            if (StringUtils.isEmpty(resultStr)) {
                return ResultEntity.newErrEntity("手环返回结果为空");
            }

            JsonObject jsonObject = new JsonParser().parse(resultStr).getAsJsonObject();
            int code = jsonObject.get("code").getAsInt();
            if (code == -1) {
                return ResultEntity.newErrEntity(jsonObject.get("msg").getAsString());
            }
            JsonArray jsonArray = jsonObject.get("data").getAsJsonArray();
            List<Map<String, Object>> mapList = new ArrayList<>();
            for (JsonElement element : jsonArray) {
                JsonObject asJsonObject = element.getAsJsonObject();
                Map<String, Object> retMap = new HashMap<String, Object>(16);
                retMap.put("fact", asJsonObject.get("fact").getAsInt());
                retMap.put("late", asJsonObject.get("late").getAsInt());
                retMap.put("queQin", asJsonObject.get("queQin").getAsInt());
                retMap.put("countStudent", asJsonObject.get("countStudent").getAsInt());
                retMap.put("qingJia", asJsonObject.get("qingJia").getAsInt());
                retMap.put("zhangchang", asJsonObject.get("zhangchang").getAsInt());
                retMap.put("leaveEarly", asJsonObject.get("leaveEarly").getAsInt());
                retMap.put("kejie", asJsonObject.get("kejie").getAsInt());
                mapList.add(retMap);
            }

            return ResultEntity.newResultEntity(mapList);
        }
        return null;
    }

    /**
     * url: http://serverIp:port/platform/api/classcard/checkattendance/detail<br>
     * 考勤详细
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/checkattendance/detail")
    public ResultEntity checkAttendanceDetail(HttpServletRequest request) throws IOException {
        ResultEntity resultEntity = ResultEntity.newErrEntity();
        String checksum = getParamVal(request, "security");
        String macId = getParamVal(request, "macId");
        if ("".equals(macId)) {
            return ResultEntity.newErrEntity("mac地址为空");
        }
        String time = getParamVal(request, "time");
        String random = getParamVal(request, "random");

        StringBuffer paramString = new StringBuffer();
        paramString.append(macId).append(time).append(random).append(PASSWORD);
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        String token = null;
        try {
            token = Base64Utils.encodeToString(md5.digest(paramString.toString().getBytes("utf-8")));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (!token.equals(checksum)) {
            return ResultEntity.newErrEntity("传入数据有误");
        }
        ClassCard classCard = classCardService.selectClassCardByMacId(macId);

        /*String schoolId = "";
        String courseId = getParamVal(request, "courseId");
        if (classCard != null && !"".equals(classCard.getSchoolId())) {
            schoolId = classCard.getSchoolId();
        }*/

        Properties pro = PropertiesUtil.getProperties("api.properties");
        String url = pro.getProperty("smartRing.url");
        StringBuilder stringBuilder = new StringBuilder(url + "/classcard/get/staticinfo");
        String weekDay = getParamVal(request, "weekDay");
        String node = getParamVal(request, "node");
        if (StringUtils.isNotEmpty(classCard.getClassId()) && StringUtils.isNotEmpty(weekDay) && StringUtils.isNotEmpty(node)) {
            Map param = new HashMap(16);
            //param.put("courseId", courseId);
            //param.put("schoolId", schoolId);
            long current = System.currentTimeMillis();
            //零点时间戳
            long zero = current / (1000 * 3600 * 24) * (1000 * 3600 * 24) - TimeZone.getDefault().getRawOffset();
            StringBuilder sb = new StringBuilder();

            sb.append(classCard.getClassId()).append(zero).append(weekDay).append(",").append(node);
            param.put("courseId", sb.toString());
            String retStr = HttpRequestUtil.get(stringBuilder.toString(), null, param);
            if (StringUtils.isEmpty(retStr)) {
                return ResultEntity.newErrEntity("手环返回数据为空");
            }

            JsonObject jsonObject = new JsonParser().parse(retStr).getAsJsonObject();
            int code = jsonObject.get("code").getAsInt();
            if (code == -1) {
                return ResultEntity.newErrEntity(jsonObject.get("msg").getAsString());
            }

            JsonArray jsonArray = jsonObject.get("data").getAsJsonArray();
            List<Map<String, Object>> mapList = new ArrayList<>();
            for (JsonElement element : jsonArray) {
                JsonObject asJsonObject = element.getAsJsonObject();
                Map<String, Object> map = new HashMap<>(16);
                map.put("studentId", asJsonObject.get("studentId").getAsString());
                map.put("xsxm", asJsonObject.get("xsxm").getAsString());
                map.put("status", asJsonObject.get("status").getAsInt());
                mapList.add(map);
            }
            resultEntity = ResultEntity.newResultEntity(mapList);
        }
        return resultEntity;
    }


    /**
     * url: http://serverIp:port/platform/api/classcard/schoolculture
     * 校园文化
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/schoolculture")
    public ResultEntity schoolCulture(HttpServletRequest request) {
        String checksum = getParamVal(request, "security");
        String macId = getParamVal(request, "macId");
        if ("".equals(macId)) {
            return ResultEntity.newErrEntity("mac地址为空");
        }
        String time = getParamVal(request, "time");
        String random = getParamVal(request, "random");

        StringBuffer paramString = new StringBuffer();
        paramString.append(macId).append(time).append(random).append(PASSWORD);
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        String token = null;
        try {
            token = Base64Utils.encodeToString(md5.digest(paramString.toString().getBytes("utf-8")));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (!token.equals(checksum)) {
            return ResultEntity.newErrEntity("传入数据有误");
        }
        ClassCard classCard = classCardService.selectClassCardByMacId(macId);
        if (null == classCard || StringUtils.isBlank(classCard.getSchoolId())) {
            return ResultEntity.newErrEntity("班牌信息有误");
        }

        SchoolCulture schoolCulture = classCardService.findSchoolCultureBySchoolId(classCard.getSchoolId());
        Map<String, Object> retMap = new HashMap<>();

        School school = schoolService.selectSchoolById(classCard.getSchoolId());
        retMap.put("schoolName", school.getName());
        retMap.put("introduction", schoolCulture.getIntroduction());

        if (StringUtils.isNotBlank(schoolCulture.getSchoolBadgePicId())) {
            ClassSpacePicture schoolBadgePic = classSpacePictureService.findPicById(schoolCulture.getSchoolBadgePicId());
            List<ClassSpacePicture> pictures = new ArrayList<>();
            pictures.add(schoolBadgePic);
            List<ClassSpacePicture> retPictures = combinationFullpath_list(pictures, request);
            retMap.put("schoolBadgePic", retPictures);
        }
        if (StringUtils.isNotBlank(schoolCulture.getVideoId())) {
            int serverPort = request.getServerPort();
            String serverName = request.getServerName();
            ClassSpaceVideo video = classSpaceVideoService.findById(schoolCulture.getVideoId());
            String playUrl = "http://" + serverName + ":" + serverPort + "/platform/file/play?" + video.getVideoUrl();
            video.setVideoUrl(playUrl);
            retMap.put("video", video);
        }
        if (StringUtils.isNotBlank(schoolCulture.getSchoolPicId())) {
            ClassSpacePicture schoolPic = classSpacePictureService.findPicById(schoolCulture.getSchoolPicId());
            List<ClassSpacePicture> pictures = classSpacePictureService.findPicByPid(schoolPic.getId());
            List<ClassSpacePicture> retPictures = combinationFullpath_list(pictures, request);
            retMap.put("schoolPics", retPictures);
        }

        return ResultEntity.newResultEntity(retMap);
    }


    /**
     * url: http://serverIp:port/platform/api/classcard/classsport
     * 运动-班级运动
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/classsport")
    public ResultEntity classSport(HttpServletRequest request) {

        String checksum = getParamVal(request, "security");
        String macId = getParamVal(request, "macId");
        if ("".equals(macId)) {
            return ResultEntity.newErrEntity("mac地址为空");
        }
        String time = getParamVal(request, "time");
        String random = getParamVal(request, "random");

        StringBuffer paramString = new StringBuffer();
        paramString.append(macId).append(time).append(random).append(PASSWORD);
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        String token = null;
        try {
            token = Base64Utils.encodeToString(md5.digest(paramString.toString().getBytes("utf-8")));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (!token.equals(checksum)) {
            return ResultEntity.newErrEntity("传入数据有误");
        }
        Properties pro = PropertiesUtil.getProperties("api.properties");
        String url = pro.getProperty("smartRing.url");
        StringBuilder stringBuilder = new StringBuilder(url + "/classcard/class/sport/statics");

        ClassCard classCard = classCardService.selectClassCardByMacId(macId);
        Map param = new HashMap();
        /*param.put("schoolId", "b14e6ff4d716906e7db294699bc8ba54");
        param.put("classId", "04cf25e799d1b0f62488798398da8ddd");*/
        param.put("schoolId", classCard.getSchoolId());
        param.put("classId", classCard.getClassId());
        try {
            String retStr = HttpRequestUtil.get(stringBuilder.toString(), null, param);
            if (StringUtils.isNotBlank(retStr)) {
                ObjectMapper mapper = new ObjectMapper();
                Map map = mapper.readValue(retStr, Map.class);
                if (null != map) {
                    return ResultEntity.newResultEntity(map.get("data") != null ? map.get("data") : "");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResultEntity.newErrEntity();
    }

    /**
     * 运动-年级排行
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "classsport/ranking")
    public ResultEntity classsportRanking(HttpServletRequest request) {
        String checksum = getParamVal(request, "security");
        String macId = getParamVal(request, "macId");
        if ("".equals(macId)) {
            return ResultEntity.newErrEntity("mac地址为空");
        }
        String time = getParamVal(request, "time");
        String random = getParamVal(request, "random");

        StringBuffer paramString = new StringBuffer();
        paramString.append(macId).append(time).append(random).append(PASSWORD);
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        String token = null;
        try {
            token = Base64Utils.encodeToString(md5.digest(paramString.toString().getBytes("utf-8")));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (!token.equals(checksum)) {
            return ResultEntity.newErrEntity("传入数据有误");
        }
        Properties pro = PropertiesUtil.getProperties("api.properties");
        String url = pro.getProperty("smartRing.url");
        StringBuilder stringBuilder = new StringBuilder(url + "/classcard/grade/sport/stastic");

        ClassCard classCard = classCardService.selectClassCardByMacId(macId);
        Map param = new HashMap();
        param.put("schoolId", classCard.getSchoolId());
        param.put("classId", classCard.getClassId());
        /*param.put("schoolId", "b14e6ff4d716906e7db294699bc8ba54");
        param.put("classId", "04cf25e799d1b0f62488798398da8ddd");*/
        try {
            String retStr = HttpRequestUtil.get(stringBuilder.toString(), null, param);
            if (StringUtils.isNotBlank(retStr)) {
                ObjectMapper mapper = new ObjectMapper();
                Map map = mapper.readValue(retStr, Map.class);
                if (null != map) {
                    return ResultEntity.newResultEntity(map.get("data") != null ? map.get("data") : "");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResultEntity.newErrEntity();
    }

    /**
     * 运动-班级周度
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "classsport/week")
    public ResultEntity classSportWeek(HttpServletRequest request) {
        String checksum = getParamVal(request, "security");
        String macId = getParamVal(request, "macId");
        if ("".equals(macId)) {
            return ResultEntity.newErrEntity("mac地址为空");
        }
        String time = getParamVal(request, "time");
        String random = getParamVal(request, "random");

        StringBuffer paramString = new StringBuffer();
        paramString.append(macId).append(time).append(random).append(PASSWORD);
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        String token = null;
        try {
            token = Base64Utils.encodeToString(md5.digest(paramString.toString().getBytes("utf-8")));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (!token.equals(checksum)) {
            return ResultEntity.newErrEntity("传入数据有误");
        }
        Properties pro = PropertiesUtil.getProperties("api.properties");
        String url = pro.getProperty("smartRing.url");
        StringBuilder stringBuilder = new StringBuilder(url + "/classcard/class/week");

        ClassCard classCard = classCardService.selectClassCardByMacId(macId);
        Map param = new HashMap();
        param.put("schoolId", classCard.getSchoolId());
        param.put("classId", classCard.getClassId());
        /*param.put("schoolId", "b14e6ff4d716906e7db294699bc8ba54");
        param.put("classId", "04cf25e799d1b0f62488798398da8ddd");*/
        try {
            String retStr = HttpRequestUtil.get(stringBuilder.toString(), null, param);
            if (StringUtils.isNotBlank(retStr)) {
                ObjectMapper mapper = new ObjectMapper();
                Map map = mapper.readValue(retStr, Map.class);
                if (null != map) {
                    return ResultEntity.newResultEntity(map.get("data") != null ? map.get("data") : "");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ResultEntity.newResultEntity();
    }

    /**
     * 运动-个人信息
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "classsport/studentinfo")
    public ResultEntity classSportStudentInfo(HttpServletRequest request) {
        String checksum = getParamVal(request, "security");
        String macId = getParamVal(request, "macId");
        if ("".equals(macId)) {
            return ResultEntity.newErrEntity("mac地址为空");
        }
        String time = getParamVal(request, "time");
        String random = getParamVal(request, "random");

        StringBuffer paramString = new StringBuffer();
        paramString.append(macId).append(time).append(random).append(PASSWORD);
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        String token = null;
        try {
            token = Base64Utils.encodeToString(md5.digest(paramString.toString().getBytes("utf-8")));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (!token.equals(checksum)) {
            return ResultEntity.newErrEntity("传入数据有误");
        }
        Properties pro = PropertiesUtil.getProperties("api.properties");
        String url = pro.getProperty("smartRing.url");
        StringBuilder stringBuilder = new StringBuilder(url + "/classcard/class/sport/personal/info");
        String studentId = getParamVal(request, "studentId");

        Map param = new HashMap();
        param.put("studentId", studentId);
        try {
            String retStr = HttpRequestUtil.get(stringBuilder.toString(), null, param);
            if (StringUtils.isNotBlank(retStr)) {

               /* JsonParser jsonParser = new JsonParser();
                JsonElement jsonElement = jsonParser.parse(retStr);
                JsonObject jsonObject = jsonElement.getAsJsonObject();
                JsonObject data = jsonObject.get("data").getAsJsonObject();

                JsonArray jsonArray = data.get("weekSport").getAsJsonArray();
                JsonArray retArray = new JsonArray();
                for (JsonElement element : jsonArray) {
                    JsonObject weekSport = element.getAsJsonObject();
                    long timeTemp = weekSport.get("timeTemp").getAsLong();
                    long value = weekSport.get("value").getAsLong();
                    JsonObject retObject = new JsonObject();
                    retObject.addProperty("timeTemp", new SimpleDateFormat("yyyy-MM-dd").format(new Date(timeTemp)));
                    retObject.addProperty("value", value);
                    retArray.add(retObject);
                }
                data.add("weekSport", retArray);*/
                ObjectMapper mapper = new ObjectMapper();
                Map map = mapper.readValue(retStr, Map.class);
                if (null != map && map.get("data") != null) {
                    return ResultEntity.newResultEntity(map.get("data") != null ? map.get("data") : "");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResultEntity.newResultEntity();
    }


    public List<Map<String, String>> jsonArrayToList(JsonArray jsonArray) {
        List<Map<String, String>> retListMap = new ArrayList<Map<String, String>>();
        for (JsonElement element : jsonArray) {
            JsonObject asJsonObject = element.getAsJsonObject();
            Map<String, String> map = new HashMap<>(16);
            map.put("studentId", asJsonObject.get("studentId").getAsString());
            map.put("status", asJsonObject.get("status").getAsString());
            retListMap.add(map);
        }
        return retListMap;
    }

    public List<ClassSpacePicture> combinationFullpath_list(List<ClassSpacePicture> classSpacePictures, HttpServletRequest request) {
        List<ClassSpacePicture> cps = new ArrayList<>();
        if (null != classSpacePictures && classSpacePictures.size() > 0) {
            for (ClassSpacePicture cp : classSpacePictures) {
                ClassSpacePicture classSpacePicture = new ClassSpacePicture();
                classSpacePicture.setId(cp.getId());
                classSpacePicture.setPicName(cp.getPicName());
                classSpacePicture.setPid(cp.getPid());
                classSpacePicture.setClassId(cp.getClassId());
                //http://localhost:8080/platform/file/pic/show?picPath=classcard/classspace/20170
                //request.getSession().getServletContext().getRealPath("/assetsNew/images/uploading-icon.png");
                int serverPort = request.getServerPort();
                String serverName = request.getServerName();
                String picurl = "";
                if (StringUtil.isNotEmpty(cp.getThumbnailUrl())) {
                    picurl = cp.getThumbnailUrl();
                } else {
                    picurl = cp.getPicUrl();
                }
                classSpacePicture.setPicUrl("http://" + serverName + ":" + serverPort + "/platform/file/pic/show?picPath=" + picurl);
                classSpacePicture.setPicTitle(cp.getPicTitle());
                cps.add(classSpacePicture);
            }
        }
        return cps;
    }

    public ClassSpacePicture combinationFullpath(ClassSpacePicture cp, HttpServletRequest request) {
        ClassSpacePicture classSpacePicture = new ClassSpacePicture();
        if (null != cp) {
            classSpacePicture.setId(cp.getId());
            classSpacePicture.setPicName(cp.getPicName());
            classSpacePicture.setPid(cp.getPid());
            classSpacePicture.setClassId(cp.getClassId());
            int serverPort = request.getServerPort();
            String serverName = request.getServerName();
            classSpacePicture.setPicUrl("http://" + serverName + ":" + serverPort + "/platform/file/pic/show?picPath=" + cp.getPicUrl());
            classSpacePicture.setPicTitle(cp.getPicTitle());
        }
        return classSpacePicture;
    }

    public static Map sortMap(Map oldMap) {
        ArrayList<Map.Entry<Integer, TeachTableView>> list = new ArrayList<Map.Entry<Integer, TeachTableView>>(oldMap.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<Integer, TeachTableView>>() {

            @Override
            public int compare(Map.Entry<Integer, TeachTableView> arg0,
                               Map.Entry<Integer, TeachTableView> arg1) {
                return arg0.getKey() - arg1.getKey();
            }
        });
        return oldMap;
    }

    public TeachCycle getLatestTeachCycle(String schoolId) {
        List<TeachCycle> cycleList = teachTaskService.getCycleList(schoolId);
        TeachCycle cycleLatest = new TeachCycle();
        if (cycleList != null && cycleList.size() > 0) {
            cycleLatest = cycleList.get(0);
        }
        return cycleLatest;
    }


}
