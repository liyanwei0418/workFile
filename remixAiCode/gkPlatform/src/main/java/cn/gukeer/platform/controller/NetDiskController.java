package cn.gukeer.platform.controller;

import cn.gukeer.common.controller.BasicController;
import cn.gukeer.common.entity.ResultEntity;
import cn.gukeer.common.security.AESencryptor;
import cn.gukeer.common.security.MD5Utils;
import cn.gukeer.common.tld.GukeerStringUtil;
import cn.gukeer.common.utils.FileUtils;
import cn.gukeer.common.utils.GsonUtil;
import cn.gukeer.common.utils.PropertiesUtil;
import cn.gukeer.common.utils.RedisUtil;
import cn.gukeer.common.utils.excel.ExportExcel;
import cn.gukeer.common.utils.excel.ImportExcel;
import cn.gukeer.platform.common.RedisKeyUtil;
import cn.gukeer.platform.modelView.importExport.IOCDiskTeacher;
import cn.gukeer.platform.persistence.entity.*;
import cn.gukeer.platform.service.RoleService;
import cn.gukeer.platform.service.TeacherService;
import cn.gukeer.platform.service.UserService;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.StringUtil;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import okhttp3.*;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;

import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by LL on 2017/8/17.
 */

@Controller
@RequestMapping(value = "/net/disk")
public class NetDiskController extends BasicController {
    @Autowired
    TeacherService teacherService;

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @Resource
    JedisPool jedisPool;

    Properties properties = PropertiesUtil.getProperties("api.properties");
    String seaFileServer = (String) properties.get("seaFileServer");


    /*
    * 点击网盘的时候验证的方法
    * */
    @ResponseBody
    @RequestMapping(value = "/verify")
    public ResultEntity netDiskIndex() {
        User user = getLoginUser();
        String email = user.getUsername() + ".com";

        Jedis jedis = jedisPool.getResource();
        String token = "";
        try {
            //获取token
            String tokenPfrefix = RedisKeyUtil.netDiskKeyTokenPrefix + email;
            token = acuireToken(email, tokenPfrefix);

            if (StringUtils.isNotEmpty(token)) {
                //获取资料库Id
                String repositoryPfefix = RedisKeyUtil.netDiskKeyRepositoryPrefix + email;
                String reposituoryId = acquireRepository(token, repositoryPfefix);
                if (StringUtils.isNotEmpty(reposituoryId)) {
                    String recyclePrefix = RedisKeyUtil.netDiskKeyRecyclePrefix + email;
                    if (StringUtils.isEmpty(RedisUtil.find(RedisKeyUtil.netDiskKeyRecyclePrefix + email, jedis))) {
                        String url = seaFileServer + "api2/repos/" + reposituoryId + "/dir/?p=/回收站";
                        mkdirMethod(token, url);
                        RedisUtil.add(recyclePrefix, email + "recycle", jedis);
                    }
                }
            }
            RedisUtil.returnResource(jedis);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (StringUtils.isEmpty(token)) {
            return ResultEntity.newErrEntity("您尚未开通云盘账号，请联系管理员");
        } else {
            return ResultEntity.newResultEntity("net/disk/index");
        }
    }

    /*
   * 获取资料库Id的方法
   * */
    private String acquireRepository(String token, String repositoryPrefix) {
        Jedis resource = jedisPool.getResource();
        String repositoryId = RedisUtil.find(repositoryPrefix, resource);
        if (StringUtils.isNotEmpty(repositoryId)) {
            RedisUtil.returnResource(resource);
            return repositoryId;
        }

        Response response = null;
        String repository = "";
        try {
            OkHttpClient httpClient = new OkHttpClient();
            Request requestRepository = new Request.Builder()
                    .header("Authorization", "Token " + token)
                    .header("Accept", "application/json; indent=4")
                    .url(seaFileServer + "api2/repos/")
                    .build();
            response = httpClient.newCall(requestRepository).execute();
            if (response.isSuccessful()) {
                repository = response.body().string();
            }

            Gson gson = new Gson();
            List<Map> mapList = gson.fromJson(repository, new TypeToken<List<Map>>() {
            }.getType());

            if (mapList != null && mapList.size() > 0) {
                Map map6 = mapList.get(0);
                repositoryId = (String) map6.get("id");
                RedisUtil.add(repositoryPrefix, repositoryId, resource);
                RedisUtil.returnResource(resource);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return repositoryId;
    }

    @RequiresPermissions("net:disk:view")
    @RequestMapping(value = "/index")
    public String netDiskIndex(HttpServletRequest request, Model model) throws Exception {
        //根据登录用户拼接邮箱
        User user = getLoginUser();
        String email = user.getUsername() + ".com";

        //通过邮箱获取token和repositoryId
        Jedis jedis = jedisPool.getResource();
        String token = RedisUtil.find(RedisKeyUtil.netDiskKeyTokenPrefix + email, jedis);
        String repositoryId = RedisUtil.find(RedisKeyUtil.netDiskKeyRepositoryPrefix + email, jedis);
        Gson gson = new Gson();
        String allFile = "";

        if (StringUtils.isNotEmpty(token)) {
            model.addAttribute("token", token);
            Map<String, Object> map = acauireAccountInfo(email);
            if (null != map) {
                request.getSession().setAttribute("totalSize", map.get("totalSize"));
                request.getSession().setAttribute("usage", map.get("usage"));
            }

            if (StringUtils.isNotEmpty(repositoryId)) {
                model.addAttribute("repositoryId", repositoryId);
                if (StringUtils.isNotEmpty(repositoryId)) {
                    String totalFileUrl = seaFileServer + "api2/repos/" + repositoryId + "/dir/?p=/";
                    model.addAttribute("totalFileUrl", totalFileUrl);
                    //根目录下的所有文件和文件夹
                    List<FileFromSeafileEntity> fileFromSeafileEntityList = getAllFiles(token, totalFileUrl);
                    model.addAttribute("fileFromSeafileEntityList", fileFromSeafileEntityList);
                }
            }
        }
        if (StringUtil.isNotEmpty(getParamVal(request, "appId"))) {
            request.getSession().setAttribute("netDiskId", getParamVal(request, "appId"));
        }
        return "netDisk/index";
    }

    /*
    *     查看某个文件夹下的所有文件夹和文件的方法
    **/
    @ResponseBody
    @RequestMapping(value = "/dir/fils")
    public Map dirFiles(HttpServletRequest request) throws ParseException {
        String url = null;
        Map map = new HashMap<>();
        request.setAttribute("repositoryId",RedisUtil.find(RedisKeyUtil.netDiskKeyRepositoryPrefix+getLoginUser().getUsername()+".com",jedisPool.getResource()));
        try {
            url = URLDecoder.decode(getParamVal(request, "url"), "UTF-8");
            String token = getParamVal(request, "token");

            List<FileFromSeafileEntity> fileFromSeafileEntityList = getAllFiles(token, url);
            map.put("fileFromSeafileEntityList", fileFromSeafileEntityList);
            map.put("url", url);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return map;
    }

    /*
    * 遍历得到的一个文件夹下的所有文件夹和文件，改变对象的时间格式的方法
    * */
    private List<FileFromSeafileEntity> convertDate(List<FileFromSeafileEntity> fileFromSeafileEntityList) {
        if (null != fileFromSeafileEntityList && fileFromSeafileEntityList.size() > 0) {
            for (FileFromSeafileEntity fileFromSeafileEntity : fileFromSeafileEntityList) {
                fileFromSeafileEntity.getName();
                Long fileSize = fileFromSeafileEntity.getSize();
                if (fileSize != null) {
                    String fileSizeStr = fileSize / 1024 + "kb";
                    fileFromSeafileEntity.setSizeStr(fileSizeStr);
                    Long mtime = fileFromSeafileEntity.getMtime();
                    try {
                        fileFromSeafileEntity.setTime(longToString(mtime, "yyyy-MM-dd"));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return fileFromSeafileEntityList;
    }

    //条件查询文件
    @ResponseBody
    @RequestMapping(value = "/all/files", method = RequestMethod.POST)
    public Map allFiles(HttpServletRequest request) throws ParseException {
        Map map = new HashedMap();
        String repositoryId = getParamVal(request, "repositoryId");
        String token = getParamVal(request, "token");
        String netDiskRepositoryUrl = seaFileServer + "api2/repos/";
        String allDirectoryUrl = netDiskRepositoryUrl + repositoryId + "/dir/?p=/&t=d&recursive=1";
        List<FileFromSeafileEntity> allFiles = new ArrayList<>();
        String shouyeAllFilesUrl = netDiskRepositoryUrl + repositoryId + "/dir/?p=/";

        List<FileFromSeafileEntity> shouYeAllFileList =getAllFiles(token, shouyeAllFilesUrl);
        if (shouYeAllFileList.size() > 0) {
            for (FileFromSeafileEntity fileFromSeafileEntity : shouYeAllFileList) {
                if (fileFromSeafileEntity.getType().equals("dir")) {
                    continue;
                } else {
                    allFiles.add(fileFromSeafileEntity);
                }
            }
        }
        //获取到所有的目录
        List<FileFromSeafileEntity> directoryList =getAllFiles(token, allDirectoryUrl);
        //循环拿到的所有的目录，从而拿到所有的文件
        if (directoryList.size() > 0) {
            for (FileFromSeafileEntity fileFromSeafileEntity : directoryList) {
                String _oneDirectoryUrl = "";
                String directName = "";
                if (fileFromSeafileEntity.getName() != null) {
                    try {
                        directName = URLDecoder.decode(fileFromSeafileEntity.getName(), "UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
                if (fileFromSeafileEntity.getParent_dir() != null && fileFromSeafileEntity.getName() != null) {
                    if (fileFromSeafileEntity.getParent_dir().equals("/")) {
                        _oneDirectoryUrl = netDiskRepositoryUrl + repositoryId + "/dir/?p=" + fileFromSeafileEntity.getParent_dir() + directName;
                    } else {
                        _oneDirectoryUrl = netDiskRepositoryUrl + repositoryId + "/dir/?p=" + fileFromSeafileEntity.getParent_dir() + "/" + directName;
                    }

                } else if (fileFromSeafileEntity.getName() == null) {
                    _oneDirectoryUrl = netDiskRepositoryUrl + repositoryId + "/dir/?p=/";
                }
                //拿到一个目录下的所有文件
                List<FileFromSeafileEntity> oneDirectoryAllFiles = new ArrayList<>();
                if (oneDirectoryAllFiles.size()>0) {
                    oneDirectoryAllFiles = getAllFiles(token, _oneDirectoryUrl);
                    for (FileFromSeafileEntity oneDirectoryFile : oneDirectoryAllFiles) {
                        if (oneDirectoryFile.getType().equals("dir")) {
                            continue;
                        }
                        allFiles.add(oneDirectoryFile);
                    }
                }
            }

            //云盘中的所有文件
            List<FileFromSeafileEntity> images = new ArrayList<>();
            List<FileFromSeafileEntity> documents = new ArrayList<>();
            List<FileFromSeafileEntity> audios = new ArrayList<>();
            List<FileFromSeafileEntity> videos = new ArrayList<>();
            List<FileFromSeafileEntity> others = new ArrayList<>();
            List<FileFromSeafileEntity> shares = new ArrayList<>();

            if (allFiles.size() > 0) {

                for (FileFromSeafileEntity fileFromSeafileEntity : allFiles) {

                    Long fileSize = fileFromSeafileEntity.getSize();
                    if (fileSize != null) {
                        String fileSizeStr = fileSize / 1024 + "kb";
                        fileFromSeafileEntity.setSizeStr(fileSizeStr);
                        Long mtime = fileFromSeafileEntity.getMtime();
                        fileFromSeafileEntity.setTime(longToString(mtime, "yyyy-MM-dd"));
                    }


                    if (fileFromSeafileEntity.getName() != null) {
                        //全部文档
                        if (fileFromSeafileEntity.getName().toLowerCase().contains(".doc")
                                || fileFromSeafileEntity.getName().toLowerCase().contains(".txt")
                                || fileFromSeafileEntity.getName().toLowerCase().contains(".pdf")
                                || fileFromSeafileEntity.getName().toLowerCase().contains(".docx")) {
                            documents.add(fileFromSeafileEntity);
                        } else if (fileFromSeafileEntity.getName().toLowerCase().contains(".bmp")
                                || fileFromSeafileEntity.getName().toLowerCase().contains(".jpg")
                                || fileFromSeafileEntity.getName().toLowerCase().contains(".jpeg")
                                || fileFromSeafileEntity.getName().toLowerCase().contains(".png")
                                || fileFromSeafileEntity.getName().toLowerCase().contains(".gif")) {
                            images.add(fileFromSeafileEntity);
                        } else if (fileFromSeafileEntity.getName().toLowerCase().contains(".avi")
                                || fileFromSeafileEntity.getName().toLowerCase().contains(".rmvb")
                                || fileFromSeafileEntity.getName().toLowerCase().contains(".rm")
                                || fileFromSeafileEntity.getName().toLowerCase().contains(".asf")
                                || fileFromSeafileEntity.getName().toLowerCase().contains(".divx")
                                || fileFromSeafileEntity.getName().toLowerCase().contains(".mpg")
                                || fileFromSeafileEntity.getName().toLowerCase().contains(".mpeg")
                                || fileFromSeafileEntity.getName().toLowerCase().contains(".mpe")
                                || fileFromSeafileEntity.getName().toLowerCase().contains(".wmv")
                                || fileFromSeafileEntity.getName().toLowerCase().contains(".mp4")
                                || fileFromSeafileEntity.getName().toLowerCase().contains(".mkv")
                                || fileFromSeafileEntity.getName().toLowerCase().contains(".vob")) {
                            videos.add(fileFromSeafileEntity);
                        } else if (fileFromSeafileEntity.getName().toLowerCase().contains(".cd")
                                || fileFromSeafileEntity.getName().toLowerCase().contains(".ogg")
                                || fileFromSeafileEntity.getName().toLowerCase().contains(".mp3")
                                || fileFromSeafileEntity.getName().toLowerCase().contains(".wma")
                                || fileFromSeafileEntity.getName().toLowerCase().contains(".wav")
                                || fileFromSeafileEntity.getName().toLowerCase().contains(".mp3pro")
                                || fileFromSeafileEntity.getName().toLowerCase().contains(".rm")
                                || fileFromSeafileEntity.getName().toLowerCase().contains(".real")
                                || fileFromSeafileEntity.getName().toLowerCase().contains(".ape")
                                || fileFromSeafileEntity.getName().toLowerCase().contains(".module")
                                || fileFromSeafileEntity.getName().toLowerCase().contains(".midi")
                                || fileFromSeafileEntity.getName().toLowerCase().contains(".vqf")) {
                            audios.add(fileFromSeafileEntity);
                        } else {
                            others.add(fileFromSeafileEntity);
                        }
                    }
                }
            }
            map.put("images", images);
            map.put("audios", audios);
            map.put("videos", videos);
            map.put("documents", documents);
            map.put("others", others);
            map.put("shares", shares);
            map.put("allFiles", allFiles);
        }
        return map;
    }

    //获取上传文件的路径
    public String acquireUploadUrl(String netDiskRepositoryUrl, String token, String repositoryId, String accept) {
        String acquireBeforeUrl = netDiskRepositoryUrl + repositoryId + "/upload-link";
        String rst = "";
        try {
            OkHttpClient httpClient = new OkHttpClient();
            Request request = new Request.Builder()
                    .header("Authorization", "Token " + token)
                    .url(acquireBeforeUrl)
                    .build();

            Response response = httpClient.newCall(request).execute();
            if (response.isSuccessful()) {
                rst = response.body().string();
                System.out.println("rst: " + rst);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rst;
    }

    @RequestMapping(value = "/upload/pop", method = RequestMethod.GET)
    public String moBan2Import(HttpServletRequest request, Model model) {
        String submitUrl = getParamVal(request, "url");
        String token = getParamVal(request, "token");
        String repositoryId = getParamVal(request, "repositoryId");

        model.addAttribute("url", submitUrl);
        model.addAttribute("token", token);
        model.addAttribute("repositoryId", repositoryId);

        return "netDisk/pop/upload";
    }

    protected List<MultipartFile> getMultipartFileList(
            HttpServletRequest request) {
        List<MultipartFile> files = new ArrayList<MultipartFile>();
        try {
            CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
                    request.getSession().getServletContext());
            if (request instanceof MultipartHttpServletRequest) {
                // 将request变成多部分request
                MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
                Iterator<String> iter = multiRequest.getFileNames();
                if (multipartResolver.isMultipart(request) && iter.hasNext()) {
                    // 获取multiRequest 中所有的文件名
                    while (iter.hasNext()) {
                        List<MultipartFile> fileRows = multiRequest
                                .getFiles(iter.next().toString());
                        if (fileRows != null && fileRows.size() != 0) {
                            for (MultipartFile file : fileRows) {
                                if (file != null && !file.isEmpty()) {
                                    files.add(file);
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception ex) {
            logger.info("解析MultipartRequest错误", ex);
        }
        return files;
    }

    //上传文件
    @ResponseBody
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public ResultEntity uploads(HttpServletRequest request) throws Exception {

        String netDiskRepositoryUrl = seaFileServer + "api2/repos/";
        List<MultipartFile> fileList = getMultipartFileList(request);
        String token = getParamVal(request, "token");
        String repositoryId = getParamVal(request, "repositoryId");
        String _directoryPath = getParamVal(request, "directotypath");

        if (_directoryPath == "") {
            _directoryPath = "/";
        }

        //获取文件上传地址
        String fullPath = FileUtils.VFS_ROOT_PATH;
        String uploadUrl = acquireUploadUrl(netDiskRepositoryUrl, token, repositoryId, "");

        File dir = new File(fullPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        if (fileList != null && fileList.size() > 0) {
            for (MultipartFile file : fileList) {
                InputStream inputStream = file.getInputStream();
                File newFile = new File(fullPath + file.getOriginalFilename());
                FileOutputStream fos = new FileOutputStream(newFile);

                byte[] b = new byte[1024];
                int nRead = 0;
                while ((nRead = inputStream.read(b)) != -1) {
                    fos.write(b, 0, nRead);
                }

                try {
                    OkHttpClient client = new OkHttpClient();
                    MultipartBody.Builder builder = new MultipartBody.Builder();

                    builder.setType(MultipartBody.FORM)
                            .addFormDataPart("parent_dir", _directoryPath)
                            .addFormDataPart("relative_path", "vgf")
                            .addFormDataPart("file", newFile.getName(), RequestBody.create(null, newFile));

                    Request requestr = new Request.Builder()
                            .header("Authorization", "Token " + token)
                            .url(uploadUrl.substring(1, uploadUrl.length() - 1))
                            .post(builder.build())
                            .build();

                    Response response = client.newCall(requestr).execute();
                    if (response.isSuccessful()) {
                        String rst = response.body().string();
                        System.out.println("rst: " + rst);
                    }
                } catch (Exception e) {
                    logger.error("上传文件失败", e);
                } finally {
                    fos.flush();
                    inputStream.close();
                }
            }
        }

        return ResultEntity.newResultEntity();
    }


    //移动弹窗
    @RequestMapping(value = "/move/pop", method = RequestMethod.GET)
    public String movePop(HttpServletRequest request, Model model) throws ParseException {
        String repositoryId = getParamVal(request, "repositoryId");
        String token = getParamVal(request, "token");
        String flag = getParamVal(request, "flag");
        String netDiskRepositoryUrl = seaFileServer + "api2/repos/";

        String allDirectoryUrl = netDiskRepositoryUrl + repositoryId + "/dir/?p=/&t=d&recursive=1";
//        String allDirectory = getAllFiles(token, allDirectoryUrl);
//        List<FileFromSeafileEntity> allFiles = new ArrayList<>();
//        Gson gson = new Gson();
//        List<FileFromSeafileEntity> shouYeAllFileList = gson.fromJson(allDirectory, new TypeToken<List<FileFromSeafileEntity>>() {
//        }.getType());

//        if (shouYeAllFileList.size() > 0) {
//            for (FileFromSeafileEntity fileFromSeafileEntity : shouYeAllFileList) {
//                if (fileFromSeafileEntity.getType().equals("dir")) {
//                    continue;
//                } else {
//                    allFiles.add(fileFromSeafileEntity);
//                }
//            }
//        }
        //获取到所有的目录
        List<FileFromSeafileEntity> directoryList = getAllFiles(token, allDirectoryUrl);

        model.addAttribute("flag", flag);
        model.addAttribute("repositoryId", repositoryId);
        model.addAttribute("token", token);
        model.addAttribute("directoryList", directoryList);

        return "netDisk/pop/movepop";
    }


    //移动、复制
    @ResponseBody
    @RequestMapping(value = "/move", method = RequestMethod.POST)
    public ResultEntity move(HttpServletRequest request, Model model) throws ParseException {
        String email = getLoginUser().getUsername()+".com";
        Jedis resource = jedisPool.getResource();
        String token = RedisUtil.find(RedisKeyUtil.netDiskKeyTokenPrefix + email, resource);
        String repositoryId =  RedisUtil.find(RedisKeyUtil.netDiskKeyRepositoryPrefix + email, resource);

        //当前要移动的文件或者文件夹的路径
        String directoryPath = getParamVal(request, "directoryPath");
        //目的地文件夹的名称 比如要移动到根目录下的/a/b的b文件夹，则dst_dir为/b
        String dst_dir = getParamVal(request, "parent_dir");
        //区分移动还是复制的标识
        String operation = getParamVal(request, "flag");
        //当前要移动文件夹或者问价的数组{"fileName": encodeURI(fileName), "fileType": fileType} 前端格式
        String arr = getParamVal(request, "arr");
        try {
            dst_dir = URLDecoder.decode(dst_dir, "UTF-8");
            directoryPath = URLDecoder.decode(directoryPath, "UTF-8");
        } catch (Exception e) {

        }

        Gson gson = new Gson();
        List<SelectedFile> selectedFiles = gson.fromJson(arr, new TypeToken<List<SelectedFile>>() {
        }.getType());
        String message = "";
        if (selectedFiles != null && selectedFiles.size() > 0) {
            for (SelectedFile selectedFile : selectedFiles) {
                String fileName = selectedFile.getFileName();
                String fileType = selectedFile.getFileType();


                String netDiskRepositoryUrl = seaFileServer + "api2/repos/";
                String moveUrl = "";
                if (fileName != null) {
                    if (fileType.equals("dir")) {
                        moveUrl = netDiskRepositoryUrl + repositoryId + "/fileops/" + operation + "/?p=/" + directoryPath;
                    } else {
                        moveUrl = netDiskRepositoryUrl + repositoryId + "/file/?p=/" + directoryPath + fileName;
                    }
                    try {
                        if (dst_dir.equals("/我的文件夹")) {
                            dst_dir = "/";
                        }

                        FormBody.Builder params = new FormBody.Builder();
                        params.add("file_names", fileName);
                        params.add("dst_dir", dst_dir + "/");
                        params.add("dst_repo", repositoryId);
                        params.add("operation", operation);

                        OkHttpClient httpClient = new OkHttpClient();
                        Request requestHttp = new Request.Builder()
                                .header("Authorization", "Token " + token)
                                .header("Accept", " application/json; charset=utf-8; indent=4")
                                .post(params.build())
                                .url(moveUrl)
                                .build();

                        Response response = httpClient.newCall(requestHttp).execute();
                        String rst = response.body().string();
                        message = response.message();
                        System.out.println("rst: " + rst);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        if (message.equals("OK")) {
            return ResultEntity.newResultEntity();
        } else {
            return ResultEntity.newErrEntity();
        }

    }


    //文件下载
    @ResponseBody
    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public ResultEntity download(HttpServletRequest request) throws Exception {
        User user = getLoginUser();
        Map map = new HashMap<>();

        String repositoryId = getParamVal(request, "repositoryId");
        String token = getParamVal(request, "token");
        String directoryPath = getParamVal(request, "directoryPath");

        String seafileDownload = (String) properties.get("seafileDownload");
        String arr = getParamVal(request, "arr");
        Gson gson = new Gson();
        List<SelectedFile> selectedFiles = gson.fromJson(arr, new TypeToken<List<SelectedFile>>() {
        }.getType());
        List<String> downloadUrls = new ArrayList<>();
        if (selectedFiles != null && selectedFiles.size() > 0) {
            for (SelectedFile selectedFile : selectedFiles) {
                String fileName = selectedFile.getFileName();
                String fileType = selectedFile.getFileType();

                String uploadUrl = "";
                String rst = "";
                OkHttpClient httpClient = new OkHttpClient();
                FormBody.Builder params = new FormBody.Builder();

                if (fileType.equals("dir")) {
                    try {
                        //第一步：获取zip_token
                        String downloadUrlZip = seaFileServer + "api/v2.1/" + "repos/" + repositoryId + "/zip-task/?parent_dir=/" + directoryPath + "&dirents=" + fileName;
                        Request requestZip = new Request.Builder()
                                .header("Authorization", "Token " + token)
                                .header("Accept", " application/json;charset=utf-8;indent=4")
                                .url(downloadUrlZip)
                                .build();
                        Response responseZip = httpClient.newCall(requestZip).execute();
                        String rstZipToken = responseZip.body().string();

                        Map mapZipToken = new Gson().fromJson(rstZipToken, Map.class);
                        logger.info("rstZip-token: " + rstZipToken);

                        //第二步：Query Task Progress
                        Request requestHttp = new Request.Builder()
                                .header("Authorization", "Token " + token)
                                .header("Accept", " application/json; charset=utf-8; indent=4")
                                .url(seaFileServer + "api/v2.1/" + "query-zip-progress/?token=" + mapZipToken.get("zip_token"))
                                .build();

                        Response response = httpClient.newCall(requestHttp).execute();
                        rst = response.body().string();
                        logger.info("Query Task Progress: " + rst);
                        Map rstMap = new Gson().fromJson(rst, Map.class);

                        //第三步：拼接下载地址
                        if (rstMap.get("zipped").equals(rstMap.get("total"))) {
                            downloadUrls.add(seafileDownload + mapZipToken.get("zip_token"));
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    uploadUrl = seaFileServer + "api2/repos/" + repositoryId + "/file/?p=/" + directoryPath + fileName;
                    try {
                        Request requestHttp = new Request.Builder()
                                .header("Authorization", "Token " + token)
                                .header("Accept", "application/json; indent=4")
                                .url(uploadUrl + "&reuse=1")
                                .build();

                        Response response = httpClient.newCall(requestHttp).execute();
                        rst = response.body().string();
                        downloadUrls.add(rst.substring(1, rst.length() - 1));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return ResultEntity.newResultEntity(downloadUrls);
    }


    //新建文件夹
    @ResponseBody
    @RequestMapping(value = "/mkdir", method = RequestMethod.POST)
    public ResultEntity mkdir(HttpServletRequest request) throws Exception {
        String reposiroryId = getParamVal(request, "repositoryId");
        String _directoryPath = getParamVal(request, "directoryPath");

        User loginUser = getLoginUser();
        String email = loginUser.getUsername() + ".com";
        String token = RedisUtil.find(RedisKeyUtil.netDiskKeyTokenPrefix + email, jedisPool.getResource());
        if (StringUtils.isEmpty(token)) {
            return ResultEntity.newErrEntity("云盘中账号信息不存在");
        }

        String _folderName = getParamVal(request, "folderName");
        String fileName = URLDecoder.decode(_folderName, "UTF-8");
        String directoryPath = URLDecoder.decode(_directoryPath, "UTF-8");
        String url = seaFileServer + "api2/repos/" + reposiroryId + "/dir/?p=/" + directoryPath + "/" + fileName;
        String rst = mkdirMethod(token, url);
        if (fileName.equals("回收站")) {
            return ResultEntity.newResultEntity("recycle");
        }
        return ResultEntity.newResultEntity(rst);
    }

    private String mkdirMethod(String token, String url) {
        String rst = "";
        try {
            FormBody.Builder params = new FormBody.Builder();
            params.add("operation", "mkdir");
            OkHttpClient httpClient = new OkHttpClient();
            Request requesthttp = new Request.Builder()
                    .header("Authorization", "Token " + token)
                    .header("Accept", " application/json;charset=utf-8;indent=4")
                    .post(params.build())
                    .url(url)
                    .build();
            Response response = httpClient.newCall(requesthttp).execute();
            if (response.isSuccessful()) {
                rst = response.body().string();
                System.out.println("rst: " + rst);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rst;
    }

    //复制
    @ResponseBody
    @RequestMapping(value = "/copy", method = RequestMethod.GET)
    public ResultEntity copy(HttpServletRequest request, Model model) throws ParseException {
        String repositoryId = getParamVal(request, "repositoryId");
        String token = getParamVal(request, "token");
        String directoryPath = getParamVal(request, "directoryPath");
        String _parent_dir = getParamVal(request, "parent_dir");
        String _fileName = getParamVal(request, "fileName");
        String parent_dir = "";
        String fileName = "";
        String fileCopyUrl = "";
        String netDiskRepositoryUrl = seaFileServer + "api2/repos/";
        try {
            parent_dir = URLDecoder.decode(_parent_dir, "UTF-8");
            fileName = URLDecoder.decode(_fileName, "UTF-8");
            fileCopyUrl = netDiskRepositoryUrl + repositoryId + "/file/?p=/" + directoryPath + fileName;

            FormBody.Builder params = new FormBody.Builder();
            params.add("dst_repo", repositoryId);
            params.add("dst_dir", parent_dir);
            params.add("operation", "copy");

            OkHttpClient httpClient = new OkHttpClient();
            Request requestCopyFile = new Request.Builder()
                    .header("Authorization", "Token " + token)
                    .header("Accept", " application/json;charset=utf-8;indent=4")
                    .post(params.build())
                    .url(fileCopyUrl)
                    .build();

            Response response = httpClient.newCall(requestCopyFile).execute();
            String rst = response.body().toString();
            System.out.println(rst);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultEntity.newResultEntity();
    }

    //重命名
    @ResponseBody
    @RequestMapping(value = "/rename", method = RequestMethod.POST)
    public ResultEntity rename(HttpServletRequest request, Model model) throws Exception {
        String repositoryId = getParamVal(request, "repositoryId");
        String token = getParamVal(request, "token");
        String _directoryPath = getParamVal(request, "directoryPath");
        String _newName = getParamVal(request, "newName");
        String fileType = getParamVal(request, "fileType");
        String _oldName = getParamVal(request, "oldName");

        String directoryPath = "";
        String oldName = "";
        String newName = "";
        try {
            oldName = URLDecoder.decode(_oldName, "UTF-8");
            directoryPath = URLDecoder.decode(_directoryPath, "UTF-8");
            newName = URLDecoder.decode(_newName, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        String netDiskRepositoryUrl = seaFileServer + "api2/repos/";

        String renameUrl = "";
        if (StringUtils.isNotEmpty(fileType) && fileType.equals("dir")) {
            renameUrl = netDiskRepositoryUrl + repositoryId + "/dir/?p=/" + directoryPath + "/" + oldName;

        } else if (StringUtils.isNotEmpty(fileType) && fileType.equals("file")) {
            renameUrl = netDiskRepositoryUrl + repositoryId + "/file/?p=/" + directoryPath + "/" + oldName;
        }
        try {
            FormBody.Builder params = new FormBody.Builder();
            params.add("operation", "rename");
            params.add("newname", newName);
            OkHttpClient httpClient = new OkHttpClient();

            Request requestRenameFile = new Request.Builder()
                    .header("Authorization", "Token " + token)
                    .header("Accept", " application/json;charset=utf-8;indent=4")
                    .post(params.build())
                    .url(renameUrl)
                    .build();

            Response response = httpClient.newCall(requestRenameFile).execute();
            if (response.isSuccessful()) {
                String rst = response.body().string();
                System.out.println("rst: " + rst);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultEntity.newResultEntity();
    }

    //分享
    @RequestMapping(value = "/share")
    public String choosePerson(HttpServletRequest request, Model model) {
        String choosesId = getParamVal(request, "chooseIds");
        model.addAttribute("choosesId", choosesId);
        return "net/disk/choosePerson";
    }

    /**
     * 将返回结果转化为String
     *
     * @param entity
     * @return
     * @throws Exception
     */
    private String getRespString(HttpEntity entity) throws Exception {
        if (entity == null) {
            return null;
        }
        InputStream is = entity.getContent();
        StringBuffer strBuf = new StringBuffer();
        byte[] buffer = new byte[4096];
        int r = 0;
        while ((r = is.read(buffer)) > 0) {
            strBuf.append(new String(buffer, 0, r, "UTF-8"));
        }
        return strBuf.toString();
    }

    private void setUploadParams(MultipartEntityBuilder multipartEntityBuilder,
                                 Map<String, String> params) {
        if (params != null && params.size() > 0) {
            Set<String> keys = params.keySet();
            for (String key : keys) {
                multipartEntityBuilder
                        .addPart(key, new StringBody(params.get(key),
                                ContentType.TEXT_PLAIN));
            }
        }
    }


    //文件删除
    @ResponseBody
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public ResultEntity delete(HttpServletRequest request) throws Exception {
        String repositoryId = getParamVal(request, "repositoryId");
        String token = getParamVal(request, "token");
        String directoryPath = URLDecoder.decode(getParamVal(request, "directoryPath"), "UTF-8");
        String arr = URLDecoder.decode(getParamVal(request, "arr"), "UTF-8");

        Gson gson = new Gson();
        List<SelectedFile> selectedFiles = gson.fromJson(arr, new TypeToken<List<SelectedFile>>() {
        }.getType());

        if (selectedFiles != null && selectedFiles.size() > 0) {
            for (SelectedFile selectedFile : selectedFiles) {
                String fileName = selectedFile.getFileName();
                String fileType = selectedFile.getFileType();
                String rst = "";

                OkHttpClient httpClient = new OkHttpClient();
                FormBody.Builder params = new FormBody.Builder();
                if (fileType.equals("dir")) {
                    try {
                        Request requestDelDir = new Request.Builder()
                                .header("Authorization", "Token " + token)
                                .header("Accept", " application/json;charset=utf-8;indent=4")
                                .delete(params.build())
                                .url(seaFileServer + "api2/repos/" + repositoryId + "/dir/?p=/" + directoryPath + fileName)
                                .build();
                        Response response = httpClient.newCall(requestDelDir).execute();
                        if (response.isSuccessful()) {
                            rst = response.body().string();
                            System.out.println("rst: " + rst);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        Request requestDelFile = new Request.Builder()
                                .header("Authorization", "Token " + token)
                                .header("Accept", " application/json;charset=utf-8;indent=4")
                                .delete(params.build())
                                .url(seaFileServer + "api2/repos/" + repositoryId + "/file/?p=/" + directoryPath + fileName)
                                .build();
                        Response response = httpClient.newCall(requestDelFile).execute();
                        if (response.isSuccessful()) {
                            rst = response.body().string();
                            System.out.println("rst: " + rst);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return ResultEntity.newResultEntity();
    }


    // long类型转换为String类型
// currentTime要转换的long类型的时间
// formatType要转换的string类型的时间格式
    public static String longToString(long currentTime, String formatType)
            throws ParseException {
        Date date = longToDate(currentTime, formatType); // long类型转成Date类型
        String strTime = dateToString(date, formatType); // date类型转成String
        return strTime;
    }


    // string类型转换为date类型
// strTime要转换的string类型的时间，formatType要转换的格式yyyy-MM-dd HH:mm:ss//yyyy年MM月dd日
// HH时mm分ss秒，
// strTime的时间格式必须要与formatType的时间格式相同
    public static Date stringToDate(String strTime, String formatType)
            throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat(formatType);
        Date date = null;
        date = formatter.parse(strTime);
        return date;
    }

    // long转换为Date类型
// currentTime要转换的long类型的时间
// formatType要转换的时间格式yyyy-MM-dd HH:mm:ss//yyyy年MM月dd日 HH时mm分ss秒
    public static Date longToDate(long currentTime, String formatType)
            throws ParseException {
        Date dateOld = new Date(currentTime); // 根据long类型的毫秒数生命一个date类型的时间
        String sDateTime = dateToString(dateOld, formatType); // 把date类型的时间转换为string
        Date date = stringToDate(sDateTime, formatType); // 把String类型转换为Date类型
        return date;
    }

    // date类型转换为String类型
// formatType格式为yyyy-MM-dd HH:mm:ss//yyyy年MM月dd日 HH时mm分ss秒
// data Date类型的时间
    public static String dateToString(Date data, String formatType) {
        return new SimpleDateFormat(formatType).format(data);
    }


    //移动弹窗
    @RequiresPermissions("net:disk:manage")
    @RequestMapping(value = "/manage/index", method = RequestMethod.GET)
    public String manageIndex(HttpServletRequest request, Model model) throws Exception {
        //获取参数
        int pageNum = getPageNum(request);
        int pageSize = getPageSize(request);

        //通过登录用户获取登录网盘的账号
        User user = getLoginUser();
        String password = AESencryptor.decryptCBCPKCS5Padding(user.getPassword());

        //拼接网盘账号
        String email = user.getUsername() + ".com";

        //在session中获取第一次点击网盘时获取的token
        String token = (String) request.getSession().getAttribute("token");

        //获取学校的后缀标识
        String loginUserAccount = user.getUsername();
        String[] arr = loginUserAccount.split("@");
        String identity = "";
        if (arr != null && arr.length > 0) {
            identity = arr[1];
        }

        //根据当前登录用户获取所有的账号信息
        OkHttpClient httpClient = new OkHttpClient();
        Request requestAccountInfo = new Request.Builder()
                .header("Authorization", "Token " + token)
                .header("Accept", "application/json; indent=4")
                .header("start", "-1")
                .header("limit", "-1")
                .header("scope", "NONE")
                .url(seaFileServer + "api2/accounts/")
                .build();

        //拿到所有的账号，处理返回结果
        Response response = httpClient.newCall(requestAccountInfo).execute();
        String rst = response.body().string();
        System.out.println("rst to string==================" + rst);
        String message = response.message();
        if (message.equals("UNAUTHORIZED") || message.equals("FORBIDDEN")) {
            return "netDisk/manageIndex";
        }
        Gson gson = new Gson();
        List<SeaFileAllAccount> _allAccounts = gson.fromJson(rst, new TypeToken<List<SeaFileAllAccount>>() {
        }.getType());//返回的是所有的网盘账号
        List<SeaFileAllAccount> allAccounts = new ArrayList<>();//这个List存放的是和平台亲爱登陆者一个学校的网盘账户
        if (null != _allAccounts && _allAccounts.size() > 0) {
            for (SeaFileAllAccount seaFileAllAccount : _allAccounts) {
                String email1 = seaFileAllAccount.getEmail();
                if (seaFileAllAccount.getEmail().equals(email)) {
                    continue;
                }
                if (seaFileAllAccount.getEmail().contains(".cn")) {
                    continue;
                }
                if (email1.contains(identity)) {
                    allAccounts.add(seaFileAllAccount);
                }
            }
        }

        List<SeaFileAccountInfo> accountInfoList = new ArrayList<>(); //这个list用于存放每一页中的网盘账号的详细信息
        List<SeaFileAllAccount> allAccountsOnePageAll = new ArrayList<>();  //这个list用于存放每一页中的所有值

        if (allAccounts != null && allAccounts.size() > 0) {
            model.addAttribute("totalSpace", allAccounts.size() * 2);
            if (pageNum == 0) {
                pageNum = 1;
            }
            if (allAccounts.size() > pageSize && allAccounts.size() > pageSize * pageNum) {
                allAccountsOnePageAll = allAccounts.subList(10 * (pageNum - 1), pageSize * pageNum);
            } else {
                allAccountsOnePageAll = allAccounts.subList(10 * (pageNum - 1), allAccounts.size());
            }

            if (allAccountsOnePageAll.size() == 0) {
                return "netDisk/manageIndex";
            }
            for (SeaFileAllAccount seaFileOneAccount : allAccountsOnePageAll) {
                Map<String, Object> map = acauireAccountInfo(seaFileOneAccount.getEmail());
                if (null == map) {
                    continue;
                }

                if (map.size() > 0) {
                    SeaFileAccountInfo seaFileAccountInfo = new SeaFileAccountInfo();
                    String oneEmail = seaFileOneAccount.getEmail();
                    if (StringUtils.isNotEmpty(identity) && oneEmail.contains(identity)) {
                        seaFileAccountInfo.setEmail(seaFileOneAccount.getEmail());
                        seaFileAccountInfo.setToken((String) map.get("token"));
                        seaFileAccountInfo.setTotal((Double) map.get("totalSize"));
                        seaFileAccountInfo.setUsage((Double) map.get("usage"));
                        seaFileAccountInfo.setStrTotal((String) map.get("totalSizeStr"));
                        seaFileAccountInfo.setStrUsage((String) map.get("usageStr"));
                        seaFileAccountInfo.setStrCreateTime((String) map.get("createTimeStr"));
                        accountInfoList.add(seaFileAccountInfo);
                    }
                }
            }


            if (allAccountsOnePageAll.size() == 10) {
                model.addAttribute("currentPageCount", 10);
            } else {
                if (null != allAccountsOnePageAll && allAccountsOnePageAll.size() > 0) {
                    model.addAttribute("currentPageCount", allAccountsOnePageAll.size());
                }
            }


            model.addAttribute("totalPage", (int) Math.ceil(allAccounts.size() / 10f));
            System.out.println((int) Math.ceil(allAccounts.size() / 10f));

            model.addAttribute("total", allAccounts.size());
        }

        model.addAttribute("currentPage", pageNum);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("accountInfoList", accountInfoList);
        return "netDisk/manageIndex";
    }

    //云盘模板
    @RequestMapping(value = "/teacher/info/download")
    public void exportTableTemplate(HttpServletResponse response) {
        try {
            String fileName = "云盘导入模板.xlsx";
            String anno = "注释：教师编号一项可不填，但若有重名则为必填项\n";
            new ExportExcel(false, "云盘导入模板", IOCDiskTeacher.class, 2, anno, 1).setDataList(new ArrayList()).write(response, fileName).dispose();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //添加账号pop
    @RequestMapping(value = "/add/account/pop")
    public String addAccountPop(HttpServletRequest request, Model model) {
        List<Teacher> _teacherList = teacherService.findAllTeacher(getLoginUser().getSchoolId());
        JSON teacherList = (JSON) JSON.toJSON(_teacherList);
        model.addAttribute("teacherList", teacherList);
        return "netDisk/pop/addAccountPop";
    }

    //添加账号
    @ResponseBody
    @RequestMapping(value = "/add/account")
    public ResultEntity addAccount(HttpServletRequest request, Model model) {
        String teacehrId = getParamVal(request, "teacherId");
        String token = (String) request.getSession().getAttribute("token");
        String password = AESencryptor.decryptCBCPKCS5Padding(getLoginUser().getPassword());
        Teacher teacher = new Teacher();
        String email = "";
        if (StringUtils.isNotEmpty(teacehrId)) {
            teacher = teacherService.findTeacherById(teacehrId);
            email = teacher.getAccount() + ".com";
        }

        try {
            createAccount(token, seaFileServer, email);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultEntity.newResultEntity("");
    }

    @RequestMapping(value = "/set/space/pop")
    public String setSpacePop(HttpServletRequest request, Model model) {
        String email = getParamVal(request, "email");
        String total = getParamVal(request, "total");
        String token = getParamVal(request, "token");

        model.addAttribute("token", token);
        model.addAttribute("total", total);
        model.addAttribute("email", email);
        return "netDisk/pop/setSpacePop";
    }

    @ResponseBody
    @RequestMapping(value = "/set/space")
    public ResultEntity setSpace(HttpServletRequest request, Model model) {
        String email = getParamVal(request, "email");

        User user = getLoginUser();

        String token = getParamVal(request, "token");
        String space = getParamVal(request, "space");

        try {
            FormBody.Builder params = new FormBody.Builder();
            params.add("is_staff", "true");//是否设置成管理员（ture为设置为管理员，false为普通用户）
            params.add("storage", space);//设置空间大小
            OkHttpClient httpClient = new OkHttpClient();
            Request requesta = new Request.Builder()
                    .header("Authorization", "Token " + token)
                    .header("Accept", " application/json; indent=4")
                    .put(params.build())
                    .url(seaFileServer + "api2/accounts/" + email + "/")
                    .build();
            Response response = httpClient.newCall(requesta).execute();
            String rst = response.body().string();
            System.out.println(rst);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultEntity.newResultEntity("");
    }

    //下载导入失败的教师云盘列表
    @RequestMapping(value = "/teacher/info/error/export", method = RequestMethod.POST)
    public void errorTable(HttpServletRequest request, HttpServletResponse response) {
        try {
            String fileName = "云盘错误信息列表.xlsx";
            String anno = "注释：教师编号一项可不填，但若有重名则为必填项\n";
            String msg = getParamVal(request, "msg");
            JsonArray jsonArray = new JsonParser().parse(msg).getAsJsonArray();
            List<IOCDiskTeacher> exportFile = new ArrayList<IOCDiskTeacher>();
            for (JsonElement jsonElement : jsonArray) {
                IOCDiskTeacher importBundling = GsonUtil.fromJson(jsonElement.getAsJsonObject(), IOCDiskTeacher.class);
                exportFile.add(importBundling);
            }
            new ExportExcel(false, "云盘错误信息列表", IOCDiskTeacher.class, 2, anno, 1).setDataList(exportFile).write(response, fileName).dispose();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/delete/account")
    public String delAccount(HttpServletRequest request) {
        String email = getParamVal(request, "email");
        String token = (String) request.getSession().getAttribute("token");


        OkHttpClient httpClient = new OkHttpClient();
        Request requestToken = new Request.Builder()
                .delete()
                .header("Authorization", "Token " + token)
                .url(seaFileServer + "/api2/accounts/" + email + "/")
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

    /*
   *    获取token的方法
   * */
    public String acuireToken(String email, String tokenPrefix) {
        String token = "";
        Jedis resource = jedisPool.getResource();
        token = RedisUtil.find(tokenPrefix, resource);
        if (StringUtils.isNotEmpty(token)) {
            RedisUtil.returnResource(resource);
            return token;
        }
        OkHttpClient httpClient = new OkHttpClient();
        FormBody.Builder params = new FormBody.Builder();
        params.add("username", email);
        params.add("password", createPassword(email));

        Request requestToken = new Request.Builder()
                .post(params.build())
                .url(seaFileServer + "/api2/auth-token/")
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
                RedisUtil.add(tokenPrefix, token, resource);
                RedisUtil.returnResource(resource);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return token;
    }

    //获取所有的目录 或者文件
    public List<FileFromSeafileEntity> getAllFiles(String token, String url) {
        OkHttpClient httpClient = new OkHttpClient();
        Request requestAccountInfo = new Request.Builder()
                .header("Authorization", "Token " + token)
                .header("Accept", "application/json; indent=4")
                .url(url)
                .build();

        Response response = null;
        String rst = "";
        List<FileFromSeafileEntity> fileFromSeafileEntityList = null;
        try {
            response = httpClient.newCall(requestAccountInfo).execute();
            rst = response.body().string();
            if (StringUtils.isNotEmpty(rst)) {
                Gson gson = new Gson();
                fileFromSeafileEntityList = gson.fromJson(rst, new TypeToken<List<FileFromSeafileEntity>>() {
                }.getType());
                fileFromSeafileEntityList = convertDate(fileFromSeafileEntityList);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileFromSeafileEntityList;
    }


    /*
    *    获取账户信息的方法
    * */
    public Map<String, Object> acauireAccountInfo(String email) {
        Map<String, Object> map = new HashedMap();
        String token = acuireToken(email, RedisKeyUtil.netDiskKeyTokenPrefix + email);

        if (StringUtils.isEmpty(token)) {
            return null;
        }
        OkHttpClient httpClient = new OkHttpClient();
        Request requestAccountInfo = new Request.Builder()
                .header("Authorization", "Token " + token)
                .header("Accept", "application/json; indent=4")
                .url(seaFileServer + "api2/account/info")
                .build();

        Response response = null;
        Gson gson = new Gson();
        try {
            response = httpClient.newCall(requestAccountInfo).execute();
            String rst = response.body().string();

            Map accountInfoMap = gson.fromJson(rst, Map.class);
            Double totalSize = (Double) accountInfoMap.get("total");
            Double usage = (Double) accountInfoMap.get("usage");

            String totalSizeStr = (String) sizeConvert(totalSize);
            String usageStr = (String) sizeConvert(usage);
            String createTimeStr = "";
            map.put("usage", usage);
            map.put("totalSize", totalSize);
            map.put("token", token);
            map.put("createTimeStr", createTimeStr);
            map.put("totalSizeStr", totalSizeStr);
            map.put("usageStr", usageStr);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            response.close();
        }

        return map;
    }

    public String sizeConvert(Double size) {
        String sizeStr = "";
        Double temp = 0.0;
        if (size >= 1000 * 1000 * 1000) {
            temp = Math.ceil(size / (1000 * 1000 * 1000));
            sizeStr = temp + "GB";
        } else if (size < (1000 * 1000 * 1000) && size >= (1000 * 1000)) {
            temp = Math.ceil(size / (1000 * 1000));
            sizeStr = temp + "MB";
        } else {
            temp = Math.ceil(size / 1000);
            sizeStr = temp + "kb";
        }
        return sizeStr;
    }

    //教师账号导入
    @ResponseBody
    @RequestMapping(value = "/teacher/import")
    public ResponseEntity importMaster(@RequestParam(value = "file") MultipartFile file) throws Exception {
        User user = getLoginUser();
        String loginUserEmail = user.getUsername() + ".com";
        String token = RedisUtil.find(RedisKeyUtil.netDiskKeyTokenPrefix + loginUserEmail, jedisPool.getResource());

        String schoolId = user.getSchoolId();
        Long begin = System.currentTimeMillis();
        List<IOCDiskTeacher> errIocDiskTeacher = new ArrayList<IOCDiskTeacher>();
        List<IOCDiskTeacher> correctIocDiskTeecher = new ArrayList<IOCDiskTeacher>();
        IOCDiskTeacher errorIOCDiskTeacher = new IOCDiskTeacher();
        List<IOCDiskTeacher> importItem = new ArrayList<IOCDiskTeacher>();

        List<Teacher> teacherLlist = teacherService.findAllTeacher(schoolId);
        Map<String, Teacher> teacherMap = new HashedMap();
        Map<String, Teacher> teacherMapName = new HashedMap();
        for (Teacher teacher : teacherLlist) {
            teacherMapName.put(teacher.getName() + schoolId, teacher);
            teacherMap.put(teacher.getNo() + schoolId, teacher);
        }

        ImportExcel importExcel = new ImportExcel(file, 2, 0);
        List<IOCDiskTeacher> list = importExcel.getDataList(IOCDiskTeacher.class);
        Map res = new HashMap();

        for (IOCDiskTeacher iocDiskTeacher : list) {
            errorIOCDiskTeacher = iocDiskTeacher;
            try {
                String teacherAccount = "";
                String email = "";
                //中英文符号的替换
                String account = iocDiskTeacher.getAccount();
                if (StringUtils.isNotEmpty(account)) {
                    Teacher teacher = teacherMap.get(account + getLoginUser().getSchoolId());
                    if (teacher != null) {
                        teacherAccount = teacher.getAccount();
                    }
                    if (teacherAccount != null) {
                        email = teacherAccount + ".com";
                        createAccount(token, seaFileServer, email);
                        correctIocDiskTeecher.add(iocDiskTeacher);
                    } else {
                        errIocDiskTeacher.add(errorIOCDiskTeacher);
                        continue;
                    }
                } else {
                    Teacher teacher = teacherMapName.get(iocDiskTeacher.getName() + schoolId);
                    if (teacher != null) {
                        teacherAccount = teacher.getAccount();
                    }
                    if (teacherAccount != null) {
                        email = teacherAccount + ".com";
                        String rst = createAccount(token, seaFileServer, email);
                        if (StringUtils.isNotEmpty(rst)) {
                            correctIocDiskTeecher.add(iocDiskTeacher);
                        } else {
                            errIocDiskTeacher.add(errorIOCDiskTeacher);
                        }
                    } else {
                        errIocDiskTeacher.add(errorIOCDiskTeacher);
                        continue;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                errIocDiskTeacher.add(errorIOCDiskTeacher);
                continue;
            }
        }
        Long end = System.currentTimeMillis();

        res.put("msg", "导入完成，共" + importItem.size() + "条成功，" + errIocDiskTeacher.size() + "条失败,耗时" + (end - begin) / 1000 + "秒");
        res.put("errorList", errIocDiskTeacher);
        return new ResponseEntity("", HttpStatus.OK);
    }

    public String createAccount(String token, String serverUrl, String email) {
        Jedis jedis = jedisPool.getResource();
        String redisEmailKey = RedisKeyUtil.netDiskKeyEmailPrefix + email;
        String redisEmailValue = RedisUtil.find(redisEmailKey, jedis);

        //redis中如果存在这样的email，说明该网盘用户已经存在，无需再次创建，直接结束该方法的调用
        if (StringUtils.isNotEmpty(redisEmailValue)) {
            RedisUtil.returnResource(jedis);
            return "";
        }

        FormBody.Builder params = new FormBody.Builder();
        params.add("is_staf", "False");
        params.add("is_active ", "True");
        String password = createPassword(email);
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
            //第一步：创建账号
            response = httpClient.newCall(requestCreat).execute();
            if (response.isSuccessful()) {
                rst = response.body().string();
                RedisUtil.add(redisEmailKey, email, jedis);
                RedisUtil.returnResource(jedis);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rst;
    }

    @RequiresPermissions("net:disk:role")
    @RequestMapping(value = "/rolefp/index")
    public String rsRoleFpIndex(HttpServletRequest request, Model model) {
        int pageSize = getPageSize(request);
        int pageNum = getPageNum(request);
        String roleId = getParamVal(request, "roleId");
        User loginUser = getLoginUser();

        HttpSession session = request.getSession();
        Object appId = session.getAttribute("netDiskId");

        List<Role> roleList = new ArrayList();
        if (!GukeerStringUtil.isNullOrEmpty(appId))
            roleList = roleService.findRoleByAppId(appId.toString());//。。。。人事管理

        List<String> idList = new ArrayList<String>();
        for (Role role : roleList) {
            idList.add(role.getId());
        }

        if (roleId.equals("") && roleList.size() > 0) {
            roleId = roleList.get(0).getId().toString();
        }

        if (roleList.size() > 0) {
            PageInfo<Teacher> pageInfo = userService.findRoleTeacherList(roleId, loginUser.getSchoolId(), pageSize, pageNum);
            model.addAttribute("teacherList", pageInfo.getList());
            model.addAttribute("pageInfo", pageInfo);
        }
        model.addAttribute("roleList", roleList);
        model.addAttribute("currentRole", roleId);
        return "netDisk/roleFp";
    }

    public String createPassword(String email) {
        String password = MD5Utils.md5(email);
        return password;
    }

    public static void main(String[] agrs) {
        System.out.println(MD5Utils.md5("admin@team.com"));
    }
}
