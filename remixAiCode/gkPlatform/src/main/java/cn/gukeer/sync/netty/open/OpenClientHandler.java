package cn.gukeer.sync.netty.open;

import cn.gukeer.common.security.MD5Utils;
import cn.gukeer.common.utils.syncdata.LdapUtils;
import cn.gukeer.platform.persistence.dao.AppMapper;
import cn.gukeer.platform.persistence.dao.MonitorMapper;
import cn.gukeer.platform.persistence.entity.*;
import cn.gukeer.platform.persistence.entity.App;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

//@Component
public class OpenClientHandler extends ChannelInboundHandlerAdapter {
    private static final Logger logger = Logger.getLogger(OpenClientHandler.class.getName());
    public static Properties prop = LdapUtils.getProperties("/syncData.properties");
    public static final String PASSWORD = prop.getProperty("password");
    String appIds = "";

    private  MonitorMapper monitorMapper;
    private  AppMapper appMapper;

    public MonitorMapper getMonitorMapper() {
        return monitorMapper;
    }

    public void setMonitorMapper(MonitorMapper monitorMapper) {
        this.monitorMapper = monitorMapper;
    }

    public AppMapper getAppMapper() {
        return appMapper;
    }

    public void setAppMapper(AppMapper appMapper) {
        this.appMapper = appMapper;
    }

    public OpenClientHandler(MonitorMapper monitorMapper, AppMapper appMapper) {
        this.monitorMapper = monitorMapper;
        this.appMapper = appMapper;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception {
        if (msg instanceof String) {
            System.out.println("客户端接受的消息: " + msg);
            ctx.writeAndFlush("长连接，等待消息中......");
//            ctx.writeAndFlush(ctx.channel());
        } else if (msg instanceof Map) {
            try {
                String refPlatformId = "";
                Map params = (Map) msg;
                String appStr = params.get("app").toString();
                String time = params.get("time").toString();
                String random = params.get("random").toString();
                String security = (String) params.get("security");
                StringBuffer paramString = new StringBuffer();
                refPlatformId = (String) params.get("refPlatformId");
                List<Monitor> monitorList = selectMonitor(monitorMapper);
                int index = 0;
                if (monitorList.size() > 0) {
                    index++;
                    for (Monitor monitor : monitorList) {
                        StringBuffer buffer = new StringBuffer();
                        buffer.append(monitor.getClientId()).append(time).append(random).append(PASSWORD);
                        String thisToken = MD5Utils.md5(buffer.toString());

                        if (!thisToken.equals(security)) {
                            ctx.writeAndFlush("传输密钥不正确");
                        } else {
                            net.sf.json.JSONObject jsonObject = net.sf.json.JSONObject.fromObject(params.get("app").toString());
                            cn.gukeer.platform.persistence.entity.App app = new cn.gukeer.platform.persistence.entity.App();
                            app.setId(jsonObject.getString("id"));
                            app.setName(jsonObject.getString("name"));
                            app.setIconUrl(jsonObject.getString("logo"));
                            app.setWebUrl(jsonObject.getString("appUrl"));
                            app.setPicUrl(jsonObject.getString("appScreenshot"));
//            app.setAppStatus( 1);//已上线
                            app.setIsDefault(Integer.valueOf(jsonObject.getString("isFree")));
                            app.setCreateDate(Long.valueOf(jsonObject.getString("createDate")));
                            app.setRemarks(jsonObject.getString("appAbstruct"));
                            app.setAppStatus(Integer.parseInt(jsonObject.getString("checkStatus")));
                            app.setDelFlag(0);//显示
                            app.setSfczxmz(0);//推送过来的应用静态资源文件不存在项目中
                            String[] targetUsers = jsonObject.getString("targetUser").split(",");
                            String targetUser = "";
                            if (targetUsers.length > 0) {
                                for (int i = 0; i < targetUsers.length; i++) {
                                    if (targetUsers[i].equals("教师")) {
                                        if (i == 0) {
                                            targetUser = "1";
                                        } else {
                                            targetUser = targetUser + "," + "1";
                                        }
                                    } else if (targetUsers[i].equals("学生")) {
                                        if (i == 0) {
                                            targetUser = "2";
                                        } else {
                                            targetUser = targetUser + "," + "2";
                                        }
                                    } else if (targetUsers[i].equals("家长")) {
                                        if (i == 0) {
                                            targetUser = "3";
                                        } else {
                                            targetUser = targetUser + "," + "3";
                                        }
                                    }
                                }
                            }
                            app.setTargetUser(targetUser);
                            //应用类别
                            if (jsonObject.getString("category").equals("0")) {
                                app.setCategory("1");
                            } else if (jsonObject.getString("category").equals("1")) {
                                app.setCategory("2");
                            }
                            app.setDevelopers(params.get("companName").toString());
                            app.setCreateBy(params.get("developer").toString());
                            int count = saveApp(app);
                            if (count == 1) {
                                if (index != 1) {
                                    refPlatformId += "," + params.get("refPlatformId");
                                }
                                if (index == 1) {
                                    refPlatformId = (String) params.get("refPlatformId");
                                }
                            } else {

                            }
                        }
                    }
                }
                ctx.writeAndFlush(refPlatformId);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        logger.warning("unexpected exception from downstream:" + cause.getMessage());
        ctx.close();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("正在连接... ");
        super.channelActive(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("连接关闭! ");
        super.channelInactive(ctx);
    }

    public List<Monitor> selectMonitor(MonitorMapper monitorMapper) {
        MonitorExample example = new MonitorExample();
        example.createCriteria().andClientIdIsNotNull();
        List<Monitor> monitorList = monitorMapper.selectByExample(example);
        return monitorList;
    }

    public int saveApp(App app) {
        int count = 0;
        App findApp = appMapper.selectByPrimaryKey(app.getId());
        if (null == findApp) {
            count = appMapper.insertSelective(app);
        } else {
            count = appMapper.updateByPrimaryKeySelective(app);
        }
        return count;
    }

}