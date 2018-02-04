package cn.gukeer.sync.netDiskAccount;

import cn.gukeer.common.security.AESencryptor;
import cn.gukeer.common.utils.PrimaryKey;
import cn.gukeer.common.utils.PropertiesUtil;
import cn.gukeer.platform.common.SchoolAppStatus;
import cn.gukeer.platform.persistence.dao.*;
import cn.gukeer.platform.persistence.entity.*;
import com.google.gson.Gson;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 *
 * @author LL
 * @date 2017/11/16
 */
@Service
public class CreateNetAccount {
    @Autowired
    AppMapper appMapper;

    @Autowired
    SchoolAppMapper schoolAppMapper;

    @Autowired
    SyncTeacherMapper syncTeacherMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    NetDiskAccountMapper netDiskAccountMapper;

    Properties properties = PropertiesUtil.getProperties("api.properties");
    String seaFileSever = (String) properties.get("seaFileServer");

    public void createNetAccount() {
        AppExample appExample = new AppExample();
        appExample.createCriteria().andAppStatusEqualTo(1).andDelFlagEqualTo(0).andNameEqualTo("教师网盘");
        List<App> appList = appMapper.selectByExample(appExample);
        App app = null;
        if (null != appList && appList.size() > 0) {
            app = appList.get(0);
        }

        List<SchoolApp> schoolApps = new ArrayList<>();
        if (null!=app) {
            String appId = app.getId();
            SchoolAppExample schoolAppExample = new SchoolAppExample();
            schoolAppExample.createCriteria().andAppIdEqualTo(appId).andAppStatusEqualTo(SchoolAppStatus.ONLINE.getStatenum());
            schoolApps = schoolAppMapper.selectByExample(schoolAppExample);
        }

        List<SyncTeacher> syncTeachers = new ArrayList<>();
        List<String> schoolIds = new ArrayList<>();
        if (null != schoolApps && schoolApps.size() > 0) {
            for (SchoolApp schoolApp : schoolApps) {
                schoolIds.add(schoolApp.getSchoolId());
                String schoolId = schoolApp.getSchoolId();
                SyncTeacherExample syncTeacherExample = new SyncTeacherExample();
                syncTeacherExample.createCriteria().andDelFlagEqualTo(0).andSchoolIdEqualTo(schoolId).andAccountIsNotNull().andEventEqualTo("INSERT");
                syncTeacherExample.or(syncTeacherExample.createCriteria().andEventEqualTo("DELETE"));
                syncTeachers = syncTeacherMapper.selectByExample(syncTeacherExample);
            }
        }

        List<NetDiskAccount> netDiskAccounts =new ArrayList<>();
        if (schoolIds!=null&&schoolIds.size()>0){
            NetDiskAccountExample netDiskAccountExample = new NetDiskAccountExample();
            netDiskAccountExample.createCriteria().andSchoolIdIn(schoolIds);
            netDiskAccounts = netDiskAccountMapper.selectByExample(netDiskAccountExample);
        }

        List<String> teacherIds = new ArrayList<>();
        List<SyncTeacher> adminSyncTeachers = new ArrayList<>();
        List<User> users = new ArrayList<>();
        if (null != syncTeachers && syncTeachers.size() > 0) {
            for (SyncTeacher syncTeacher : syncTeachers) {
                teacherIds.add(syncTeacher.getSyncId());
                if (syncTeacher.getAccount().contains("admin")) {
                    adminSyncTeachers.add(syncTeacher);
                }
            }

            UserExample userExample = new UserExample();
            userExample.createCriteria().andDelFlagEqualTo(0).andRefIdIn(teacherIds);
            users = userMapper.selectByExample(userExample);

            for (SyncTeacher adminSyncTeacher : adminSyncTeachers) {
                String adminAccount = adminSyncTeacher.getAccount();
                String adminId = adminSyncTeacher.getSyncId();
                UserExample adminuserExample = new UserExample();
                adminuserExample.createCriteria().andDelFlagEqualTo(0).andRefIdIn(teacherIds);
                List<User> adminUsers = userMapper.selectByExample(adminuserExample);
                User adminUser = new User();
                String adminPassword = "";
                if (adminUsers != null && adminUsers.size() > 0) {
                    adminUser = adminUsers.get(0);
                    adminPassword = AESencryptor.decryptCBCPKCS5Padding(adminUser.getPassword());
                }

                String token = acuireToken(adminAccount + ".com", adminPassword);
                List<NetDiskAccount> oneAccount = new ArrayList<>();
                for (SyncTeacher syncTeacher : syncTeachers) {
                    if (adminUser.getSchoolId().equals(syncTeacher.getSchoolId())) {
                        String event = syncTeacher.getEvent();
                        String account = syncTeacher.getAccount();
                        for (User user : users) {
                            NetDiskAccount netDiskAccount = new NetDiskAccount();
                            netDiskAccount.setId(PrimaryKey.get());
                            netDiskAccount.setSchoolId(adminUser.getSchoolId());
                            netDiskAccount.setDelFlag(0);
                            netDiskAccount.setEmail(account+",com");
                            netDiskAccount.setCreaterEmail(adminAccount+".com");
                            netDiskAccount.setCreaterToken(token);
                            netDiskAccount.setInsertTime(System.currentTimeMillis());
                            String password = user.getPassword();
                            oneAccount.add(netDiskAccount);
                            if (netDiskAccounts.contains(oneAccount)){
                                continue;
                            }

                            if (user.getRefId().equals(syncTeacher.getSyncId())) {
                                password = AESencryptor.decryptCBCPKCS5Padding(password);
                            }
                            if (event.equals("INSERT")) {
                                String rst = createAccount(password,token,seaFileSever,account+",com");
                                if (rst.equals("success")){
                                    netDiskAccountMapper.insertSelective(netDiskAccount);
                                }
                            } else {
                                delAccount(account+",com",token);
                            }
                        }
                    }else {
                        continue;
                    }
                }
            }
        }
    }


    public String createAccount(String password, String token, String serverUrl, String email) {

        FormBody.Builder params = new FormBody.Builder();
        params.add("is_staf", "False");
        params.add("is_active ", "True");
        params.add("password", password);

        OkHttpClient httpClient = new OkHttpClient();
        Request requestCreat = new Request.Builder()
                .header("Authorization", "Token " + token)
                .header("Accept", "application/json; indent=4")
                .put(params.build())
                .url(serverUrl + "api2/accounts/" + email + "/")
                .build();
        Response response = null;
        String rst = "";
        try {
            response = httpClient.newCall(requestCreat).execute();
            if (response.isSuccessful()) {
                rst = response.body().string();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rst;
    }


    /*
 *    获取token的方法
 * */
    public String acuireToken(String email, String password) {
        String token = "";
        Properties properties = PropertiesUtil.getProperties("api.properties");
        String seaFileSever = (String) properties.get("seaFileServer");

        OkHttpClient httpClient = new OkHttpClient();
        FormBody.Builder params = new FormBody.Builder();
        params.add("username", email);
        params.add("password", password);

        Request requestToken = new Request.Builder()
                .post(params.build())
                .url(seaFileSever + "/api2/auth-token/")
                .build();
        String jsonToken = "";
        Gson gson = new Gson();
        try {
            Response responseToken = httpClient.newCall(requestToken).execute();
            if (responseToken.isSuccessful()) {
                jsonToken = responseToken.body().string();
                Map tokenMap = gson.fromJson(jsonToken, Map.class);
                token = (String) tokenMap.get("token");
                System.out.println("rst: " + responseToken);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return token;
    }

    public String delAccount(String email,String token) {

        Properties properties = PropertiesUtil.getProperties("api.properties");
        String seaFileSever = (String) properties.get("seaFileServer");

        OkHttpClient httpClient = new OkHttpClient();
        Request requestToken = new Request.Builder()
                .delete()
                .header("Authorization", "Token " + token)
                .url(seaFileSever + "/api2/accounts/" + email + "/")
                .build();
        String rst = "";
        try {
            Response responseToken = httpClient.newCall(requestToken).execute();
            if (responseToken.isSuccessful()) {
                rst = responseToken.body().string();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
