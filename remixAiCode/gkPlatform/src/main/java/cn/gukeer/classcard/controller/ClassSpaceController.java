package cn.gukeer.classcard.controller;

import cn.gukeer.classcard.modelView.ClassIntroductionView;
import cn.gukeer.classcard.persistence.entity.ClassCard;
import cn.gukeer.classcard.persistence.entity.ClassIntroduction;
import cn.gukeer.classcard.persistence.entity.ClassSpacePicture;
import cn.gukeer.classcard.persistence.entity.ClassSpaceVideo;
import cn.gukeer.classcard.service.*;
import cn.gukeer.common.controller.BasicController;
import cn.gukeer.common.entity.ResultEntity;
import cn.gukeer.common.utils.FileUtils;
import cn.gukeer.common.utils.NumberConvertUtil;
import cn.gukeer.common.utils.PrimaryKey;
import cn.gukeer.platform.modelView.SchoolView;
import cn.gukeer.platform.persistence.entity.User;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.StringUtil;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by alpha on 18-1-16.
 */
@Controller
@RequestMapping(value = "/classcard")
public class ClassSpaceController extends BasicController {

    @Autowired
    ClassCardService classCardService;

    @Autowired
    ClassIntroductionService classIntroductionService;

    @Autowired
    ClassSpacePictureService classSpacePictureService;

    @Autowired
    ClassSpaceVideoService classSpaceVideoService;

    @RequestMapping(value = "/introduction/index", method = RequestMethod.GET)
    public String ClassIntroductionIndex(HttpServletRequest request, Model model) {

        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        String schoolId = user.getSchoolId();
        String appid = getParamVal(request, "appId");
        SchoolView schoolview = new SchoolView();
        String[] judge = {"", "", "", ""};
        schoolview = classCardService.selectAndMakeTree(schoolId, judge, true);

        String classCardId = getParamVal(request, "classCardId");
        if (!"".equals(classCardId)) {
            ClassIntroductionView classIntroductionView = classCardService.selectViewByclassCardId(classCardId);
            model.addAttribute("classIntroductionView", classIntroductionView);
        }
        model.addAttribute("pageInfo", null);
        model.addAttribute("appId", appid);
        model.addAttribute("schoolview", schoolview);
        model.addAttribute("focusNode", "school_" + schoolId);
        model.addAttribute("schoolId", schoolId);

        List searchParam = new ArrayList();
        searchParam.add(schoolId);
        searchParam.add("");
        searchParam.add("");
        searchParam.add(0);
        searchParam.add(0);
        searchParam.add(-1);
        searchParam.add("");
        model.addAttribute("searchParam", searchParam);
        if (StringUtil.isNotEmpty(getParamVal(request, "appId")))
            request.getSession().setAttribute("xueJId", appid);
        return "classCard/classspace/introductionindex";
    }

    @RequestMapping(value = "/introduction/index", method = RequestMethod.POST)
    public String ClassIntroductionIndexPost(HttpServletRequest request, Model model, String nodeList) {
        String _cId = getParamVal(request, "classId");
        String _sId = getParamVal(request, "schoolId");
        String _xd = getParamVal(request, "xd");
        String _xq = getParamVal(request, "xq");
        String _nj = getParamVal(request, "nj");
        String nowfocus = getParamVal(request, "focusNode");
        String schoolId = getLoginUser().getSchoolId();

        String xd;
        int xq, nj;
        String sid;
        String cid;
        if (_cId != null && _cId != "")
            cid = _cId;
        else cid = "";
        if (_sId != null && _sId != "")
            sid = _sId;
        else sid = "";
        if (_xd != null && _xd != "")
            xd = _xd;
        else xd = "";
        if (_xq != null && _xq != "")
            xq = NumberConvertUtil.convertS2I(_xq);
        else xq = 0;
        if (_nj != null && _nj != "")
            nj = NumberConvertUtil.convertS2I(_nj);
        else nj = 0;

        SchoolView schoolview = new SchoolView();
        String[] judge = {cid, xd, String.valueOf(xq), String.valueOf(nj)};
        if (nodeList == null || nodeList == "") {                 //可能性为0~
            schoolview = classCardService.selectAndMakeTree(schoolId, judge, true);
            model.addAttribute("schoolview", schoolview);
        } else {
            model.addAttribute("nodeList", nodeList);
        }

        String classCardId = getParamVal(request, "classCardId");
        if (!"".equals(classCardId)) {
            ClassIntroductionView classIntroductionView = classCardService.selectViewByclassCardId(classCardId);
            model.addAttribute("classIntroductionView", classIntroductionView);
            model.addAttribute("classCardId", classCardId);
        }
        model.addAttribute("classId", cid);
        model.addAttribute("schoolId", sid);
        model.addAttribute("focusNode", nowfocus);
        model.addAttribute("flag", "edit");
        model.addAttribute("FileUtils", new FileUtils());
        return "classCard/classspace/introductionindex";
    }

    @ResponseBody
    @RequestMapping(value = "/introduction/save")
    public ResultEntity saveIntroduction(HttpServletRequest request) {
        String sendWord = getParamVal(request, "sendWord");
        String classBackbone = getParamVal(request, "classBackbone");
        String classIntroduction = getParamVal(request, "classIntroduction");
        String displayInformationUrl = getParamVal(request, "url");
        String displayInformationName = getParamVal(request, "displayInformationName");
        boolean thumbnail = Boolean.parseBoolean(getParamVal(request, "thumbnail"));
        String classCardId = getParamVal(request, "classCardId");
        String classId = getParamVal(request, "classId");
        String id = getParamVal(request, "id");
        String fileCategory = getParamVal(request, "fileCategory");

        if (StringUtils.isNotEmpty(id)) {
            ClassIntroduction ci = classIntroductionService.findClassIntroductionById(id);
            String rootPath = FileUtils.VFS_ROOT_PATH;
            //删除图片
            if (null != ci && StringUtils.isNotEmpty(ci.getPictureId())) {
                ClassSpacePicture csp = classSpacePictureService.findPicById(ci.getPictureId());
                if (null != csp && !csp.getPicUrl().equals(displayInformationUrl)) {
                    if (StringUtils.isNotEmpty(csp.getPicUrl())) {
                        new File(rootPath + csp.getPicUrl()).delete();
                    }
                    if (StringUtils.isNotEmpty(csp.getThumbnailUrl())) {
                        new File(rootPath + csp.getThumbnailUrl()).delete();
                    }
                }
                classSpacePictureService.deletePicture(ci.getPictureId());
            }
            //删除视频
            if (null != ci && StringUtils.isNotBlank(ci.getVideoId())) {
                ClassSpaceVideo csv = classSpaceVideoService.findById(ci.getVideoId());
                if (null != csv && !csv.getVideoUrl().equals(displayInformationUrl)) {
                    if (StringUtils.isNotEmpty(csv.getVideoUrl())) {
                        new File(rootPath + csv.getVideoUrl()).delete();
                    }
                }
                classSpaceVideoService.deleteVideo(ci.getVideoId());
            }
        }

        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        ClassCard classCard = classCardService.selectClassCardById(classCardId);
        if (null == classCard || StringUtils.isBlank(classCard.getClassId())) {
            return ResultEntity.newErrEntity("班牌数据错误");
        }

        ClassIntroduction classIntroduction1 = new ClassIntroduction();
        classIntroduction1.setId(id);
        classIntroduction1.setSendWord(sendWord);
        classIntroduction1.setClassBackbone(classBackbone);
        classIntroduction1.setClassIntroduction(classIntroduction);
        classIntroduction1.setClassCardId(classCardId);
        classIntroduction1.setClassId(classCard.getClassId());

        if (StringUtils.isNotEmpty(displayInformationUrl) && StringUtils.isNotEmpty(displayInformationName)) {

            if ("pic".equals(fileCategory)) {
                ClassSpacePicture picture = new ClassSpacePicture();
                picture.setId(PrimaryKey.get());
                picture.setCreateDate(System.currentTimeMillis());
                picture.setCreateBy(user.getId());
                picture.setClassId(classCard.getClassId());
                picture.setClassCardId(classCardId);
                picture.setPicUrl(displayInformationUrl);
                picture.setPicName(displayInformationName);
                picture.setSchoolId(getLoginUser().getSchoolId());
                if (thumbnail) {
                    picture.setThumbnailUrl(classSpacePictureService.getThumbnail(displayInformationUrl));
                }
                try {
                    classSpacePictureService.insertPictrue(picture);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                classIntroduction1.setPictureId(picture.getId());
                classIntroduction1.setVideoId("");

            } else if ("video".equals(fileCategory)) {
                ClassSpaceVideo video = new ClassSpaceVideo();
                video.setId(PrimaryKey.get());
                video.setCreateDate(System.currentTimeMillis());
                video.setCreateBy(user.getId());
                video.setClassId(classCard.getClassId());
                video.setClassCardId(classCardId);
                video.setVideoUrl(displayInformationUrl);
                video.setVideoName(displayInformationName);
                try {
                    classSpaceVideoService.insertVideo(video);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                classIntroduction1.setVideoId(video.getId());
                classIntroduction1.setPictureId("");
            }
        }
        try {
            if (!"".equals(id)) {
                classIntroduction1.setUpdateDate(System.currentTimeMillis());
                classIntroduction1.setUpdateBy(user.getId());
                classIntroductionService.updateClassIntroduction(classIntroduction1);

            } else {
                classIntroduction1.setId(PrimaryKey.get());
                classIntroduction1.setCreateBy(user.getId());
                classIntroduction1.setCreateDate(System.currentTimeMillis());
                classIntroductionService.insertClassIntroduction(classIntroduction1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultEntity.newResultEntity();
    }

    //查询图片
    @ResponseBody
    @RequestMapping(value = "/classspace/pic/show")
    public ResultEntity findClassSpacePic(HttpServletRequest request) {
        String pid = getParamVal(request, "pid");
        String classCardId = getParamVal(request, "classCardId");
        int pageNum = getPageNum(request);
        int pageSize = 12;

        if (!"".equals(pid) && !"".equals(classCardId)) {
            PageInfo<ClassSpacePicture> spacePicturePageInfo = classSpacePictureService.findPicByClassCardIdAndPid(classCardId,
                    pid, pageNum, pageSize);
            Map<String, Object> retMap = new HashMap<>();
            retMap.put("pid", pid);
            retMap.put("pageInfo", spacePicturePageInfo);
            return ResultEntity.newResultEntity(retMap);
        }
        return null;
    }

    //成长足迹新增合集
    @RequestMapping(value = "/growth/collection/edit")
    public String eidtGrowthCollection(HttpServletRequest request, Model model) {
        String classCardId = getParamVal(request, "classCardId");
        String _index = getParamVal(request, "_index");
        model.addAttribute("classCardId", classCardId);
        model.addAttribute("_index", _index);
        return "classCard/classspace/growthadd";
    }

    //百舸争流上传
    @RequestMapping(value = "/baige/edit")
    public String eidtBaige(HttpServletRequest request, Model model) {
        String classCardId = getParamVal(request, "classCardId");
        String _index = getParamVal(request, "_index");
        model.addAttribute("classCardId", classCardId);
        model.addAttribute("_index", _index);
        return "classCard/classspace/baigeadd";
    }

    //活动掠影上传
    @RequestMapping(value = "/active/edit")
    public String editActive(HttpServletRequest request, Model model) {
        String classCardId = getParamVal(request, "classCardId");
        String _index = getParamVal(request, "_index");
        model.addAttribute("classCardId", classCardId);
        model.addAttribute("_index", _index);
        return "classCard/classspace/activeadd";
    }

    //保存多个照片和合集
    @ResponseBody
    @RequestMapping(value = "/growth/collection/save")
    public ResultEntity saveGrowthCollection(HttpServletRequest request, Model model) {
        String picTitle = getParamVal(request, "picTitle");
        String classCardId = getParamVal(request, "classCardId");
        if ("".equals(classCardId)) {
            return ResultEntity.newErrEntity();
        }
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();

        String pid = getParamVal(request, "pid");
        ClassCard classCard = classCardService.selectClassCardById(classCardId);
        //成长足迹集合
        String collectionId = "";
        if ("-1".equals(pid)) {
            ClassSpacePicture pictureCollection = new ClassSpacePicture();
            collectionId = PrimaryKey.get();
            pictureCollection.setId(collectionId);
            pictureCollection.setPicTitle(picTitle);
            pictureCollection.setCreateBy(user.getId());
            pictureCollection.setCreateDate(System.currentTimeMillis());
            pictureCollection.setClassCardId(classCardId);
            pictureCollection.setClassId(classCard.getClassId());
            pictureCollection.setPid("-1");
            pictureCollection.setSchoolId(getLoginUser().getSchoolId());
            try {
                classSpacePictureService.insertPictrue(pictureCollection);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        String url_name = getParamVal(request, "url_name");
        JsonArray url_nameArray = new JsonArray();
        if (!"".equals(url_name)) {
            url_nameArray = new JsonParser().parse(url_name).getAsJsonArray();
        }
        //合集里面照片的数量限制为50
        /*if (!"-1".equals(pid) && !"hdly".equals(pid) && !"bgzl".equals(pid)) {
            List<ClassSpacePicture> picsList = classSpacePictureService.findPicByClassCardIdAndPid(classCardId, pid);
            if (picsList != null && picsList.size() >= 50) {
                return ResultEntity.newResultEntity(ResultEntity.ERR_CODE, "上传失败", url_nameArray.size() > 0 ? url_nameArray.get(0).getAsJsonObject().get("url").getAsString() : null);
            }
        }*/

        if (url_nameArray.size() > 0) {
            //图片入库
            for (int i = 0; i < url_nameArray.size(); i++) {
                JsonObject jsonObject = url_nameArray.get(i).getAsJsonObject();
                //集合封面
                if (i == 0 && "-1".equals(pid)) {
                    ClassSpacePicture cp = classSpacePictureService.findPicById(collectionId);
                    cp.setPicUrl(jsonObject.get("url").getAsString());
                    try {
                        classSpacePictureService.updatPicture(cp);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                ClassSpacePicture classSpacePicture = new ClassSpacePicture();
                classSpacePicture.setId(PrimaryKey.get());
                classSpacePicture.setPicName(jsonObject.get("name").getAsString());
                classSpacePicture.setPid("".equals(collectionId) ? pid : collectionId);
                classSpacePicture.setClassId(classCard.getClassId());
                classSpacePicture.setClassCardId(classCardId);
                classSpacePicture.setCreateDate(System.currentTimeMillis());
                classSpacePicture.setCreateBy(user.getId());
                classSpacePicture.setSchoolId(user.getSchoolId());
                String url = jsonObject.get("url").getAsString();
                classSpacePicture.setPicUrl(url);
                //缩略图
                if (jsonObject.get("thumbnail").getAsBoolean()) {
                    classSpacePicture.setThumbnailUrl(classSpacePictureService.getThumbnail(url));
                } else {
                    classSpacePicture.setThumbnailUrl("");
                }
                if (!"-1".equals(pid) && url_nameArray.size() > 1) {
                    int tmp = url_nameArray.size() - i;
                    classSpacePicture.setPicTitle(picTitle + "(" + tmp + ")");
                } else {
                    classSpacePicture.setPicTitle(picTitle);
                }
                try {
                    classSpacePictureService.insertPictrue(classSpacePicture);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (!"-1".equals(pid) && !"hdly".equals(pid) && !"bgzl".equals(pid)) {
                    //新增时更新封面
                    List<ClassSpacePicture> classSpacePictures = classSpacePictureService.findPicByClassCardIdAndPid(classCardId, pid);
                    ClassSpacePicture classSpacePicture2 = classSpacePictureService.findPicById(pid);
                    try {
                        if (classSpacePictures != null && classSpacePictures.size() > 0) {
                            classSpacePicture2.setPicUrl(classSpacePictures.get(classSpacePictures.size() - 1).getPicUrl());
                            classSpacePictureService.updatPicture(classSpacePicture2);
                        } else {
                            classSpacePicture.setPicUrl("");
                            classSpacePictureService.updatPicture(classSpacePicture2);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            return ResultEntity.newResultEntity("保存成功");
        }
        return null;
    }

    //成长足迹合集详细
    @RequestMapping(value = "/growth/collectiondetail")
    public String collectiondetail(HttpServletRequest request, Model model) {
        String collectionId = getParamVal(request, "collectionId");
        String classCardId = getParamVal(request, "classCardId");
        int pageNum = getPageNum(request);
        int pageSize = 18;

        PageInfo<ClassSpacePicture> picturePageInfo = classSpacePictureService.findPicByClassCardIdAndPid(classCardId, collectionId, pageNum, pageSize);
        model.addAttribute("pageInfo", picturePageInfo);
        model.addAttribute("classCardId", classCardId);
        model.addAttribute("collectionId", collectionId);
        return "classCard/classspace/growthcollectiondetail";
    }

    //保存单个照片
    @ResponseBody
    @RequestMapping(value = "picture/save")
    public ResultEntity savePicture(HttpServletRequest request) {
        String picTitle = getParamVal(request, "picTitle");
        if ("".equals(picTitle)) {
            return ResultEntity.newErrEntity("请输入标题或名称");
        }
        String classCardId = getParamVal(request, "classCardId");
        if ("".equals(classCardId)) {
            return ResultEntity.newErrEntity();
        }
        String id = getParamVal(request, "id");
        String pid = getParamVal(request, "pid");
        String pic_name = getParamVal(request, "pic_name");
        String pic_url = getParamVal(request, "pic_url");
        boolean thumbnail_url = Boolean.parseBoolean(getParamVal(request, "thumbnail_url"));


        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();

        if ("-1".equals(pid) && !"".equals(id)) {
            ClassSpacePicture cp = classSpacePictureService.findPicById(id);
            cp.setUpdateBy(user.getId());
            cp.setUpdateDate(System.currentTimeMillis());
            cp.setPicTitle(picTitle);
            try {
                classSpacePictureService.updatPicture(cp);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        } else {
            ClassCard classCard = classCardService.selectClassCardById(classCardId);
            ClassSpacePicture classSpacePicture = new ClassSpacePicture();
            classSpacePicture.setId(PrimaryKey.get());
            classSpacePicture.setPicName(pic_name);
            classSpacePicture.setPicTitle(picTitle);
            classSpacePicture.setPid(pid);
            classSpacePicture.setClassId(classCard.getClassId());
            classSpacePicture.setClassCardId(classCardId);
            classSpacePicture.setCreateBy(user.getId());
            classSpacePicture.setCreateDate(System.currentTimeMillis());
            classSpacePicture.setPicUrl(pic_url);
            classSpacePicture.setSchoolId(user.getSchoolId());
            if (thumbnail_url) {
                classSpacePicture.setThumbnailUrl(classSpacePictureService.getThumbnail(pic_url));
            } else {
                classSpacePicture.setThumbnailUrl("");
            }
            try {
                classSpacePictureService.insertPictrue(classSpacePicture);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return ResultEntity.newResultEntity("保存成功");
        }
    }

    //删除照片
    @ResponseBody
    @RequestMapping(value = "picture/multidelete")
    public ResultEntity deletePicture(HttpServletRequest request) {

        String classCardId = getParamVal(request, "classCardId");
        if ("".equals(classCardId)) {
            return ResultEntity.newErrEntity("classCardId is null");
        }
        String pid = getParamVal(request, "pid");
        String id_urls = getParamVal(request, "id_urls");
        if (!"-1".equals(pid) && "".equals(id_urls)) {
            return ResultEntity.newErrEntity("删除失败");
        }
        JsonArray idUrlArray = new JsonArray();
        if ("-1".equals(pid)) {
            String collectionId = getParamVal(request, "collectionId");
            try {
                classSpacePictureService.deletePicture(collectionId);
            } catch (Exception e) {
                e.printStackTrace();
            }

            List<ClassSpacePicture> pictures = classSpacePictureService.findPicByClassCardIdAndPid(classCardId, collectionId);
            if (pictures != null && pictures.size() > 0) {
                StringBuilder stringBuilder = new StringBuilder();
                for (ClassSpacePicture cp : pictures) {
                    JsonObject jsonObject = new JsonObject();
                    jsonObject.addProperty("id", cp.getId());
                    jsonObject.addProperty("url", cp.getPicUrl());
                    idUrlArray.add(jsonObject);
                }
            }
        } else {
            if (!"".equals(id_urls)) {
                idUrlArray = new JsonParser().parse(id_urls).getAsJsonArray();
            }
        }

        String collectionId = "";
        if (idUrlArray.size() > 0) {
            String realPath = FileUtils.VFS_ROOT_PATH;
            for (JsonElement jsonElement : idUrlArray) {
                try {
                    JsonObject jsonObject = jsonElement.getAsJsonObject();
                    if (!"-1".equals(jsonObject.get("id").getAsString())) {
                        classSpacePictureService.deletePicture(jsonObject.get("id").getAsString());
                        //flag为collectionDetail时表示删除的是合集中的照片,拿到集合的id 为封面重新赋值
                        String flag = getParamVal(request, "flag");
                        if ("collectionDetail".equals(flag)) {
                            collectionId = pid;
                        }
                    }
                    //分割出文件名
                    new File(realPath + jsonObject.get("url").getAsString()).delete();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        //合集id不为空时更新集合封面
        if (!"".equals(collectionId)) {
            List<ClassSpacePicture> classSpacePictures = classSpacePictureService.findPicByClassCardIdAndPid(classCardId, collectionId);
            ClassSpacePicture classSpacePicture = classSpacePictureService.findPicById(collectionId);
            try {
                if (classSpacePictures != null && classSpacePictures.size() > 0) {
                    classSpacePicture.setPicUrl(classSpacePictures.get(classSpacePictures.size() - 1).getPicUrl());
                    classSpacePictureService.updatPicture(classSpacePicture);
                } else {
                    classSpacePicture.setPicUrl("");
                    classSpacePictureService.updatPicture(classSpacePicture);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return ResultEntity.newResultEntity("删除成功");
    }


}
