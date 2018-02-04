package cn.gukeer.platform.controller;

import cn.gukeer.common.controller.BasicController;
import cn.gukeer.common.entity.ResultEntity;
import cn.gukeer.common.security.MD5Utils;
import cn.gukeer.common.utils.syncdata.LdapUtils;
import cn.gukeer.platform.persistence.entity.Monitor;
import cn.gukeer.platform.service.MonitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by admin on 2017/2/21.
 */
@Controller
@RequestMapping( value = "/monitor")
public class MonitorController extends BasicController {
    @Autowired
    MonitorService monitorService;

    public static Properties prop = LdapUtils.getProperties("/syncData.properties");
    public static final String PASSWORD = prop.getProperty("password");

    @ResponseBody
    @RequestMapping( value = "/save", method = RequestMethod.POST)
    public ResultEntity initClientId(HttpServletRequest request){
        try {
            Map params = transToMAP(request.getParameterMap());
            String checksum = params.get("security").toString();
            String id = params.get("id").toString();
            String clientId = params.get("clientId").toString();
            String time = params.get("time").toString();
            String random = params.get("random").toString();
            String openUrl = params.get("openurl").toString();

            StringBuffer paramString = new StringBuffer();
            paramString.append( clientId).append( time).append( random).append(PASSWORD);
            String token = MD5Utils.md5( paramString.toString());

            if ( !token.equals(checksum)){
                return ResultEntity.newErrEntity("传入数据有误");
            }

            Monitor monitor = new Monitor();
            monitor.setId( id);
            monitor.setClientId( clientId);
            monitor.setOpenUrl( openUrl);
            int count = monitorService.saveMonitor( monitor);
            if ( count >0){
                return ResultEntity.newResultEntity("初始化成功");
            }
            return ResultEntity.newResultEntity();
        } catch(Exception e) {
            e.printStackTrace();
            return ResultEntity.newErrEntity("");
        }

    }
}
