package cn.gukeer.sync.service.pull;

import cn.gukeer.common.utils.*;
import cn.gukeer.platform.persistence.dao.SchoolMapper;
import cn.gukeer.platform.persistence.entity.School;
import cn.gukeer.platform.persistence.entity.SchoolExample;
import cn.gukeer.platform.persistence.entity.TeachTable;
import cn.gukeer.platform.service.TeachTaskService;
import cn.gukeer.sync.dataDefinition.QueryData;
import cn.gukeer.sync.dataDefinition.QueryRules;
import cn.gukeer.sync.dataDefinition.ScheduleWeekData;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.google.gson.Gson;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


/**
 * Created by Administrator on 2017/8/9.
 */
@Service
public class QuerySchedule {

    private static Log logger = LogFactory.getLog(QuerySchedule.class);

    private static String agent;

    private static String accessKey;

    private static String objectKey;

    private static String exchangeUrl;

    private static Properties properties;

    private static List<Map<String, Object>> listAll;

    @Autowired
    TeachTaskService teachTaskService;

    @Autowired
    SchoolMapper schoolMapper;

    static {
        properties = PropertiesUtil.getProperties("/syncData.properties");

        agent = properties.getProperty("sync.receive.agentq");
        accessKey = properties.getProperty("sync.receive.accesskeyq");
        objectKey = properties.getProperty("sync.receive.objectKey");
        exchangeUrl = properties.getProperty("sync.receive.exchangeUrl");
    }

    /**
     * 申请token令牌，token有效时间为一小时，应用可以自行选择是否对改token进行缓存
     *
     * @return
     */
    public String getAuthToken() {
        Map<String, String> params = new HashMap<String, String>();
        params.put("agent", agent);
        //生成随机数
        String rand = RandomStringUtils.randomAlphabetic(8);
        params.put("rand", rand);
        //生成sign,md5(agent+accessKey+rand)
        params.put("sign", MD5Util.go(agent + accessKey + rand));
        Map<String, Object> res = null;
        String result = null;
        //申请token令牌
        try {
            result = HttpClientUtil.postContent(exchangeUrl + "/auth/token", "utf-8", params);
            res = JSONObject.parseObject(result);
        } catch (Exception e) {
            logger.error(String.format("申请token失败,返回结果：%s", result));
            return null;
        }
        if (!MapUtils.getBooleanValue(res, "success")) {
            logger.info(String.format("申请token失败,失败信息：%s", MapUtils.getString(res, "msg")));
            return null;
        }
        return MapUtils.getString(res, "token");
    }

    /**
     * 根据token令牌和数据标识获取数据真实请求地址
     *
     * @param token
     * @param objectKey
     * @return
     */
    public String getQueryUrl(String token, String objectKey) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("objectKey", objectKey);
        params.put("token", token);

        Map<String, Object> res = null;
        String result = null;
        try {
            result = HttpClientUtil.postContent(exchangeUrl + "/auth/getRequestUrl", "utf-8", params);
            //解析返回的json
            res = JSONObject.parseObject(result, Map.class);
        } catch (Exception e) {
            logger.error(String.format("获取[%s]真实请求地址失败,返回结果：%s", objectKey, result));
            return null;
        }
        if (!MapUtils.getBooleanValue(res, "success")) {
            logger.error(String.format("获取[%s]真实请求地址失败,错误信息：%s", objectKey, MapUtils.getString(res, "msg")));
            return null;
        }
        return MapUtils.getString(res, "url");
    }


    /**
     * 查询排课结果
     */
    public void querySchedule() {
        listAll = new ArrayList<>();

        //申请token令牌
        String token = getAuthToken();
        if (StringUtils.isBlank(token)) {
            logger.error("token获取失败");
            return;
        }
        //根据token令牌和数据标识获取组织结构数据真实请求地址(真实请求地址可能发生变化，所以是动态获取)
        String queryUrl = getQueryUrl(token, objectKey);
        if (StringUtils.isBlank(queryUrl)) {
            logger.error(String.format("获取对象 %s 查询url为空", objectKey));
            return;
        }

        //查询参数parmas
        Map<String, String> params = new HashMap<String, String>();
        params.put("objectKey", objectKey);
        params.put("token", token);

        int page = 1;
        int limit = 1000;

        List<School> schoolList = getSchoolList();

        List<Map<String, Object>> res = getSchedule(schoolList, queryUrl, params, page, limit);
        while (res.size() > 0) {
            listAll.addAll(res);
            page++;//全查
            res = getSchedule(schoolList, queryUrl, params, page, limit);
        }

        translateSchedule(listAll);
    }


    /*
    * 查询课表，需要全量获取
    * */
    public List<Map<String, Object>> getSchedule(List<School> schoolList, String queryUrl, Map<String, String> params, int page, int limit) {
        QueryData queryData = new QueryData();
        queryData.setGroupOp("or");
        queryData.setPage(page);
        queryData.setLimit(limit);

        List<QueryRules> ruleList = new ArrayList<>();
        for (School school : schoolList) {
            QueryRules rules = new QueryRules();
            rules.setField("schoolId");
            rules.setOp("eq");
            rules.setData(school.getId());

            ruleList.add(rules);
        }
        queryData.setRules(ruleList);

        params.put("query", new Gson().toJson(queryData));
        String result = HttpClientUtil.postContent(queryUrl, "utf-8", params);
        Map<String, Object> res = MapUtils.EMPTY_MAP;
        List<Map<String, Object>> list = new ArrayList<>();
        try {
            res = JSONObject.parseObject(result, new TypeReference<Map<String, Object>>() {
            });
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        if (MapUtils.getBooleanValue(res, "success")) {
            list = (List<Map<String, Object>>) res.get("dataList");
        }
        return list;
    }


    /*
    * 转换得到的课表数据
    * */
    public void translateSchedule(List<Map<String, Object>> scheduleList) {
        Gson gson = GsonUtil.noneIntDouble();
        List<ScheduleWeekData> viewList = gson.fromJson(gson.toJson(scheduleList),
                new com.google.gson.reflect.TypeToken<List<ScheduleWeekData>>() {
                }.getType());

        List<TeachTable> tableList = new ArrayList<>();

        List<String> tableIdList = new ArrayList<>();
        for (ScheduleWeekData data : viewList) {
            TeachTable table = new TeachTable();

            table.setId(data.getId());//课表id作为主键
            table.setClassId(data.getClassId());
            table.setCourseId(data.getCourseId());
            table.setTeacherId(data.getTeacherId());
            table.setTableId(data.getTableId());
            table.setClassRoomId(data.getClassRoomId());
            table.setWeekend(Integer.valueOf(data.getWeekend()));
            table.setSchoolId(data.getSchoolId());

            tableIdList.add(data.getId());
            tableList.add(table);
        }

        List<TeachTable> exist = teachTaskService.findTableListByIdList(tableIdList);
        tableList.removeAll(exist);//初始化拉取的时候，存在的课表不再拉去

        if (tableList.size() > 0)
            teachTaskService.batchInsertTeachTable(tableList);
    }

    /*
    * 查询当前平台学校列表，查询这些学校的课表
    * */
    public List<School> getSchoolList() {
        SchoolExample example = new SchoolExample();
        example.createCriteria().andDelFlagEqualTo(0);

        List<School> res = schoolMapper.selectByExample(example);

        return res;
    }


}
