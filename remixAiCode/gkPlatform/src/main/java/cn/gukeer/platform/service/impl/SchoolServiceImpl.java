package cn.gukeer.platform.service.impl;

import cn.gukeer.common.service.BasicService;
import cn.gukeer.common.tld.GukeerStringUtil;
import cn.gukeer.common.utils.*;
import cn.gukeer.platform.modelView.AreaSchoolView;
import cn.gukeer.platform.persistence.dao.*;
import cn.gukeer.platform.persistence.entity.*;
import cn.gukeer.platform.service.SchoolService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

/**
 * Created by conn on 2016/8/6.
 */
@Service
public class SchoolServiceImpl extends BasicService implements SchoolService {

    @Autowired
    CacheManager cacheManager;

    @Autowired
    SchoolMapper schoolMapper;
    @Autowired
    A_SchoolExtensionMapper schoolExtensionMapper;

    @Autowired
    GradeClassMapper classMapper;

    @Autowired
    ClassSectionMapper sectionMapper;

    @Autowired
    SchoolTypeMapper schoolTypeMapper;

    @Autowired
    SchoolAppMapper schoolAppMapper;

    @Autowired
    LogMapper logMapper;

    @Autowired
    ConfigMapper configMapper;

    @Autowired
    WeatherMapper weatherMapper;

/*    @Autowired
    JedisPool jedisPool;*/

    @Override
    public PageInfo<School> selectAllList(int pageNum, int pageSize) {

        SchoolExample example = new SchoolExample();
        example.createCriteria().andDelFlagEqualTo(0);
        example.setOrderByClause("create_date desc");

        List<School> list = schoolMapper.selectByExample(example);
        PageInfo<School> pageInfo = new PageInfo<School>(list);

        return pageInfo;
    }

    @Override
    public List<School> selectAllList() {

        SchoolExample example = new SchoolExample();
        example.createCriteria().andDelFlagEqualTo(0);
        example.setOrderByClause("create_date desc");

        List<School> list = schoolMapper.selectByExample(example);
        return list;
    }

    @Override
    public School selectSchoolById(String id) {
        School school = schoolMapper.selectByPrimaryKey(id);
        return school;
    }

    @Override
    public int delete(String id) {
        SchoolExample example = new SchoolExample();
        example.createCriteria().andIdEqualTo(id);
        School s = new School();
        s.setDelFlag(1);
        int num = schoolMapper.updateByExampleSelective(s, example);
        return num;
    }

    @Override
    public int saveSchoolSettingInfo(School school) {

        int count = 0;
        if (null == school.getId() || school.getId() == "") {
            count = schoolMapper.insertSelective(school);
        } else {
            SchoolExample example = new SchoolExample();
            example.createCriteria().andIdEqualTo(school.getId());
            count = schoolMapper.updateByExampleSelective(school, example);
        }
        return count;
    }

    @Override
    public int saveSchoolType(SchoolType schoolType) {

        int count = 0;
        if (null == schoolType.getId() || schoolType.getId() == "") {
            schoolType.setId(PrimaryKey.get());
            count = schoolTypeMapper.insertSelective(schoolType);
        } else {
            SchoolTypeExample example = new SchoolTypeExample();
            example.createCriteria().andIdEqualTo(schoolType.getId());
            count = schoolTypeMapper.updateByExampleSelective(schoolType, example);
        }
        return count;
    }

    @Override
    @CacheEvict(value = "schoolInfo", key = "#url")
    public int saveAndClearSchoolcache(School school, String url) {
        if (null == school.getId() || school.getId() == "") {
            return schoolMapper.insertSelective(school);
        } else {
            SchoolExample example = new SchoolExample();
            example.createCriteria().andIdEqualTo(school.getId());
            return schoolMapper.updateByExampleSelective(school, example);
        }
    }

    @Override
    public int saveSchoolBackId(School school) {
        return schoolExtensionMapper.insertSchoolBackId(school);
    }

    @Override
    public SchoolType selectSchoolTypeById(String id) {
        SchoolType schoolType = schoolTypeMapper.selectByPrimaryKey(id);
        return schoolType;
    }

    @Override
    public List<GradeClass> selectGradeClassBySchoolId(String schoolId) {

        GradeClassExample example = new GradeClassExample();
        example.createCriteria().andSchoolIdEqualTo(schoolId).andDelFlagEqualTo(0);

        List<GradeClass> list = classMapper.selectByExample(example);

        return list;
    }

    @Override
    public List<SchoolType> selectSchoolTypeBySchoolId(String schoolId) {

        SchoolTypeExample example = new SchoolTypeExample();
        example.setOrderByClause("sort");
        example.createCriteria().andSchoolIdEqualTo(schoolId).andDelFlagEqualTo(0);

        List<SchoolType> list = schoolTypeMapper.selectByExample(example);

        return list;
    }

    @Override
    public List<SchoolApp> findSchoolAppById(String schoolId, String _id) {
        SchoolAppExample schoolAppExample = new SchoolAppExample();
        schoolAppExample.createCriteria().andSchoolIdEqualTo(schoolId).andAppIdEqualTo(_id);
        List<SchoolApp> shcoolAppLsit = schoolAppMapper.selectByExample(schoolAppExample);
        return shcoolAppLsit;
    }

    @Override
    public List<Map<String, Object>> selectTeacherByParam(Map<Object, Object> param) {
        return schoolExtensionMapper.selectSchoolViewById(param);
    }

    @Override
    @Cacheable(value = "schoolInfo", key = "#schoolUrl")
    public School selectSchoolByWholeUrl(String schoolUrl) {
        if (schoolUrl != "") {
            SchoolExample schoolExample = new SchoolExample();
            schoolExample.createCriteria().andDeployUrlEqualTo(schoolUrl).andDelFlagEqualTo(0);
            List<School> schoolList = schoolMapper.selectByExample(schoolExample);
            if (schoolList.size() == 1) {
                return schoolList.get(0);
            } else {
                return null;
            }
        } else return null;
    }

    @Override
    public PageInfo<LogWithBLOBs> selectLog(Integer pageNum, Integer pageSize) {
        LogExample example = new LogExample();
        example.createCriteria();
        example.setOrderByClause("create_date Desc");
        PageHelper.startPage(pageNum, pageSize);


        Page<LogWithBLOBs> page = (Page<LogWithBLOBs>) logMapper.selectByExampleWithBLOBs(example);
        PageInfo<LogWithBLOBs> pageInfo = new PageInfo<LogWithBLOBs>(page);

        return pageInfo;
    }

    @Override
    public LogWithBLOBs selectLogById(String id) {
        return logMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageInfo<Config> selectConfig(Integer pageNum, Integer pageSize) {
        ConfigExample example = new ConfigExample();
        example.createCriteria().andDelFlagEqualTo(0);
        example.setOrderByClause("param_type");
        PageHelper.startPage(pageNum, pageSize);
        Page<Config> page = (Page<Config>) configMapper.selectByExample(example);
        PageInfo<Config> pageInfo = new PageInfo<Config>(page);
        return pageInfo;
    }

    @Override
    public Config selectConfigById(String id) {
        return configMapper.selectByPrimaryKey(id);
    }

    @Override
    public void saveConfig(Config config) {
        if (config.getId() != null && config.getId() != "") {
            configMapper.updateByPrimaryKeySelective(config);
        } else {
            configMapper.insertSelective(config);
        }
    }

    @Override
    public List<Config> selectConfigByType(String type) {
        ConfigExample configExample = new ConfigExample();
        configExample.createCriteria().andDelFlagEqualTo(0).andParamTypeEqualTo(type);
        return configMapper.selectByExample(configExample);
    }

    @Override
    public List<School> getSonSchoolList(String schoolId) {
        return schoolExtensionMapper.getSonSchoolList(schoolId);
    }

    @Override
    public List<Weather> selectWeatherByCity(String city, Long time) {

        Long beginTime = time - 1000 * 60 * 60L;//查询日期前一个小时,最后面的l 是字母L 不是数字1
        Long endTime = time + 1000 * 60 * 60L;//查询日期的后一个小时，最后的是l 是字母L 不是数字1

        WeatherExample example = new WeatherExample();
        example.createCriteria().andCityEqualTo(city).andQueryDateBetween(String.valueOf(beginTime), String.valueOf(endTime));//例如 查北京天气，当前8点，则返回数据库里北京 当前日期 7-9点之间的天气数据

        List<Weather> res = weatherMapper.selectByExample(example);

        return res;
    }

    @Override
    public int saveWeather(Weather weather) {

        WeatherExample example = new WeatherExample();
        example.createCriteria().andCityEqualTo(weather.getCity());

        weatherMapper.deleteByExample(example);//每次新增城市天气之前把这个城市的历史天气给删除了

        return weatherMapper.insertSelective(weather);
    }

    //天气缓存，mysql缓存方式
  /*  @Override
    public String getWeather(String schoolId) {

        if (StringUtils.isEmpty(schoolId)) {
            //未传机构id，是定时器查询，调用接口来查询并存入到数据库中
        } else {
            School school = this.selectSchoolById(schoolId);
            String address = school.getXz().split(",")[1];
            //传机构id，是人为发起天气调用，查询数据库
            List<Weather> res = this.selectWeatherByCity(address, System.currentTimeMillis());
            if (res.size() > 0) {
                //查询到天气数据，不再调用接口
                return res.get(0).getContent();
            } else {
                //未查询到天气数据，调用接口查询
                Object queryRes = getApiWeather(address);

                if (GukeerStringUtil.isNullOrEmpty(queryRes))
                    return null;

                Weather weather = new Weather();
                weather.setId(PrimaryKey.get());
                weather.setCity(address);
                weather.setContent(queryRes.toString());
                weather.setQueryDate(String.valueOf(System.currentTimeMillis()));

                this.saveWeather(weather);

                return queryRes.toString();
            }
        }
        return null;
    }*/


  //天气缓存，redis缓存方式
    @Override
    public String getWeather(String schoolId) {

//        Jedis redis = jedisPool.getResource();

        if (StringUtils.isEmpty(schoolId)) {
            //未传机构id，是定时器查询，调用接口来查询并存入到数据库中
        } else {
            School school = this.selectSchoolById(schoolId);
            String address = school.getXz().split(",")[1];
            //传机构id，是人为发起天气调用，查询缓存中是否有该城市的天气信息
//            String res = RedisUtil.find("platform_weather_" + CnToPyUtils.getPingYin(address), redis);
            //Object res = CacheUtils.get("platform_weather_" + CnToPyUtils.getPingYin(address));
            Object res = new CacheServiceImpl(cacheManager,"action-cache").getCacheByKey("platform_weather_" + CnToPyUtils.getPingYin(address));
            if (!GukeerStringUtil.isNullOrEmpty(res)) {
                //缓存中有该城市天气数据，不再调用接口
                return res.toString();
            } else {
                //未查询到天气数据，调用接口查询
                Object queryRes = getApiWeather(address);

                if (GukeerStringUtil.isNullOrEmpty(queryRes))
                    return null;

                String key = "platform_weather_" + CnToPyUtils.getPingYin(address);
                try {
                  /*  RedisUtil.add(key, queryRes.toString(), redis);
                    RedisUtil.setExpire(key, 60*120, redis);
                    RedisUtil.returnResource(redis);*/
                  new CacheServiceImpl(cacheManager,"action-cache").addCache(key,queryRes.toString());
//                  CacheUtils.put(key,queryRes.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return queryRes.toString();
            }
        }
        return null;
    }

    public static Object getApiWeather(String city) {
        Properties pro = PropertiesUtil.getProperties("api.properties");
        String key = pro.getProperty("weather.key");
        String url = pro.getProperty("weather.url");

        Map param = new HashMap();
        param.put("city", city);
        param.put("key", key);

        String res = null;
        try {
            res = HttpRequestUtil.post(url, null, null, param);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (StringUtils.isEmpty(res))
            return null;

        JSONObject object = JSONObject.fromObject(res);
        JSONArray array = JSONArray.fromObject(object.get("HeWeather5").toString());

        if (array.length() > 0)
            return array.get(0);
        else
            return null;
    }

    public List<AreaSchoolView> formatList(List<Map> listParam) {
        Map<Object, List> schoolMap = new HashMap<Object, List>();
        for (Map depart : listParam) {
            if (schoolMap.containsKey(depart.get("schoolId"))) {
                schoolMap.get(depart.get("schoolId")).add(depart);
            } else {
                List<Map> list = new ArrayList<>();
                list.add(depart);
                schoolMap.put(depart.get("schoolId"), list);
            }
        }
        Set set = schoolMap.keySet();
        List<AreaSchoolView> res = new ArrayList<>();
        for (Object key : set) {
            List<Map> list = schoolMap.get(key);
            if (list.size() == 0)
                continue;
            AreaSchoolView view = new AreaSchoolView();
            Map list0 = list.get(0);
            view.setId(list0.get("schoolId").toString());
            view.setName(list0.get("schoolName").toString());
            view.setPid(list0.get("schoolPar").toString());
            view.setList(list);
            res.add(view);
        }
        return res;
    }
}
