package cn.gukeer.common.shiro.filter;

import cn.gukeer.platform.common.SchoolMsg;
import cn.gukeer.platform.persistence.dao.SchoolMapper;
import cn.gukeer.platform.persistence.entity.School;
import cn.gukeer.platform.persistence.entity.SchoolExample;
import com.github.pagehelper.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Created by pc-daisike on 2016/11/3.
 */
public class CheckPathFilter implements Filter {
    @Autowired
    SchoolMapper schoolMapper;

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpreq = (HttpServletRequest) req;
        StringBuffer tempPathString = httpreq.getRequestURL();

        //截取url
        Integer start = 0;
        if (tempPathString.indexOf("http://") >= 0) {
            start = "http://".length();
        } else {
            start = tempPathString.indexOf("/") + 2;
        }

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
        }

        //单例存储数据
        SchoolMsg.ifReset();    //判断单例创建时间以此重置单例
        if (schoolUrl != "" && !SchoolMsg.ifHaveSchool(schoolUrl)) {    //当url不为空，且单例中不存在该url信息时，添加数据
            SchoolExample example = new SchoolExample();
            example.createCriteria().andDelFlagEqualTo(0).andDeployUrlEqualTo(schoolUrl);
            List<School> schoolList = schoolMapper.selectByExample(example);
            if (schoolList.size() > 0 && schoolList.size() < 2) {   //如果存在该学校的数据，且学校间url不重复
                School school = schoolList.get(0);
                String logo = school.getLogo();
                String bgPicture = school.getBgPicture();

                Map<String, String> msgMap = new HashMap<String, String>();
                msgMap.put("logo", logo);
                msgMap.put("bgPicture", bgPicture);

                HttpServletRequest httpServletRequest = (HttpServletRequest) req;
                HttpSession session = httpServletRequest.getSession(true);
                session.setAttribute("logoSession", logo);

                Map<String, Map<String, String>> urlMap = new HashMap<String, Map<String, String>>();
                urlMap.put(schoolUrl, msgMap);

                SchoolMsg schoolMsg = SchoolMsg.getInstance();
                schoolMsg.addMsg(urlMap);
            }
        }

        if(SchoolMsg.ifHaveSchool(schoolUrl)){
            HttpServletRequest httpServletRequest = (HttpServletRequest) req;
            HttpSession session = httpServletRequest.getSession(true);
            if (session.getAttribute("logoSession") == null
                    || session.getAttribute("logoSession").toString() =="") {
                session.setAttribute("logoSession", SchoolMsg.getMsg(schoolUrl).get("logo"));
            }
        }

        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {
    }

}
