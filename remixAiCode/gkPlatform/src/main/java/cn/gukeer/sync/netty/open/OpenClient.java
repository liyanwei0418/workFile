package cn.gukeer.sync.netty.open;

import cn.gukeer.common.security.MD5Utils;
import cn.gukeer.common.utils.PropertiesUtil;
import cn.gukeer.platform.persistence.dao.AppMapper;
import cn.gukeer.platform.persistence.dao.CourseMapper;
import cn.gukeer.platform.persistence.dao.MonitorMapper;
import cn.gukeer.platform.persistence.entity.App;
import cn.gukeer.platform.persistence.entity.Monitor;
import cn.gukeer.platform.persistence.entity.MonitorExample;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wulianedu.netty.client.ClientInstance;
import com.wulianedu.netty.listener.MessageListener;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Map;
import java.util.Properties;

public class OpenClient implements MessageListener {
    Properties properties = PropertiesUtil.getProperties("api.properties");
    String netty_open_ip = (String) properties.get("netty_open_ip");
    Integer netty_open_port = Integer.parseInt((String) properties.get("netty_open_port"));
    String open_platfrom_identity=(String) properties.get("open_platfrom_identity");
    private MonitorMapper monitorMapper;
    AppMapper appMapper;

    public OpenClient(MonitorMapper monitorMapper, AppMapper appMapper) {
        this.monitorMapper = monitorMapper;
        this.appMapper = appMapper;
    }

    ClientInstance clientInstance;
    public void startClient() {
        clientInstance = new ClientInstance(this, open_platfrom_identity, netty_open_ip, netty_open_port);
        clientInstance.connect();

        if (clientInstance.channelStatus()) {
            clientInstance.sendMessage(open_platfrom_identity);
        }
    }

    @Override
    public void onMessageReceive(String msg) {
        System.out.println("客户端接收到消息=====" + msg);
        if ((Map) JSON.parseObject(msg) instanceof Map) {
            try {
                String refPlatformId = "";
                Map params = (Map) JSON.parseObject(msg);
                List<Monitor> monitorList = selectMonitor(monitorMapper);
                if (monitorList.size() > 0) {
                    for (Monitor monitor : monitorList) {
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
                        String refPlatfromId = params.get("refPlatfromId").toString();
                        int count = saveApp(app);
                        if (count > 0) {
                            if (clientInstance.channelStatus()) {
                                clientInstance.sendMessage(refPlatfromId);
                            }
//                            clientInstance.sendMessage(refPlatfromId);
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

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

//    @Override
//    public void run() {
//        //配置客户端NIO线程组
//        EventLoopGroup group = new NioEventLoopGroup();
//        try {
//            Bootstrap b = new Bootstrap();
//            b.group(group).channel(NioSocketChannel.class)
//                    .option(ChannelOption.TCP_NODELAY, true)
//                    .handler(new ChannelInitializer<SocketChannel>() {
//                        @Override
//                        protected void initChannel(SocketChannel ch) throws Exception {
//                            ch.pipeline()
//                                    .addLast(new ObjectDecoder(1024 * 1024
//                                            , ClassResolvers.cacheDisabled(//禁止对类加载器进行缓存
//                                            this.getClass().getClassLoader())))
//                                    .addLast(new ObjectEncoder())
//                                    .addLast(new OpenClientHandler(monitorMapper, appMapper));//对实现了Serializable的POJO对象进行编码
//                        }
//                    });
//            //发起异步连接操作
//            ChannelFuture future = null;
//            try {
//                future = b.connect(netty_open_ip, netty_open_port).sync();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            //等待客户端链路关闭
//            try {
//                future.channel().closeFuture().sync();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println("aaaaaa");
//        } finally {
//            try {
////                Thread.sleep(4000);
////                run();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//    }
}