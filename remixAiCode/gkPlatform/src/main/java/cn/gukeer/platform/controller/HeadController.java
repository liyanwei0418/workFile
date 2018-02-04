package cn.gukeer.platform.controller;

import cn.gukeer.common.controller.BasicController;
import cn.gukeer.common.entity.ResultEntity;
import cn.gukeer.platform.persistence.entity.School;
import cn.gukeer.platform.service.SchoolService;
import com.github.pagehelper.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by pc-daisike on 2016/11/29.
 */
@Controller
@RequestMapping(value = "/head")
public class HeadController extends BasicController {

    @Autowired
    SchoolService schoolService;

    @ResponseBody
    @RequestMapping(value = "/getlogo")
    public ResultEntity getLogo(HttpServletRequest request, HttpServletResponse response) {
        StringBuffer tempPathString = request.getRequestURL();
        Integer start = "http://".length();
        String _end = "";
        try {
            Properties p = new Properties();
            InputStream inStream = new ClassPathResource("/db.properties").getInputStream();
            p.load(inStream);
            _end = p.getProperty("develop_path");
        } catch (Exception e1) {
            _end = "";
        }
        if (StringUtil.isEmpty(_end)) {
            _end = ".gukeyun.cn";
        }

        Integer end = tempPathString.indexOf(_end);
        String schoolUrl = "";
        if (end > 0 && end > start) {
            schoolUrl = tempPathString.substring(start, end);
            School school = schoolService.selectSchoolByWholeUrl(schoolUrl);
            if (school != null) {
                return ResultEntity.newResultEntity(school.getLogo(), "");
            }
        }
        return ResultEntity.newResultEntity("", "");
    }

    //天气缓存--redis作存储
    @ResponseBody
    @RequestMapping(value = "/heweather", method = RequestMethod.GET)
    public String getHeWeather(HttpServletRequest request) throws Exception {
        return schoolService.getWeather(request.getParameter("schoolId"));
    }


}
