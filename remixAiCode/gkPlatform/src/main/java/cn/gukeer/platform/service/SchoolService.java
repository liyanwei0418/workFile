package cn.gukeer.platform.service;

import cn.gukeer.platform.persistence.entity.*;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * Created by conn on 2016/8/6.
 */
public interface SchoolService {

    PageInfo<School> selectAllList(int pageNum, int pageSize);

    List<School> selectAllList();

    School selectSchoolById(String id);

    int delete(String id);

    int saveSchoolSettingInfo(School school);

    List<GradeClass> selectGradeClassBySchoolId(String schoolId);

    List<SchoolType> selectSchoolTypeBySchoolId(String schoolId);

    int saveSchoolType(SchoolType schoolType);

    int saveAndClearSchoolcache(School school, String url);

    int saveSchoolBackId(School school);

    SchoolType selectSchoolTypeById(String id);

    List<SchoolApp> findSchoolAppById(String schoolId, String _id);

    List<Map<String, Object>> selectTeacherByParam(Map<Object, Object> param);

    PageInfo<LogWithBLOBs> selectLog(Integer pageNum, Integer pageSize);

    LogWithBLOBs selectLogById(String id);

    PageInfo<Config> selectConfig(Integer pageNum, Integer pageSize);

    Config selectConfigById(String id);

    void saveConfig(Config config);

    List<Config> selectConfigByType(String type);

    School selectSchoolByWholeUrl(String schoolUrl);

    List<School> getSonSchoolList(String schoolId);

    List<Weather> selectWeatherByCity(String city, Long time);

    int saveWeather(Weather weather);

    String getWeather(String schoolId);

}
