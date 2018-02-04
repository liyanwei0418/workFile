package cn.gukeer.platform.controller;

import cn.gukeer.common.controller.BasicController;
import cn.gukeer.common.entity.ResultEntity;
import cn.gukeer.common.exception.ErrcodeException;
import cn.gukeer.common.utils.CnToPyUtils;
import cn.gukeer.common.utils.DateUtils;
import cn.gukeer.common.utils.NumberConvertUtil;
import cn.gukeer.common.utils.PrimaryKey;
import cn.gukeer.common.utils.excel.ImportExcel;
import cn.gukeer.platform.common.ConstantUtil;
import cn.gukeer.platform.common.KVEntity;
import cn.gukeer.platform.modelView.SchoolView;
import cn.gukeer.platform.modelView.importExport.IOStudentView;
import cn.gukeer.platform.persistence.entity.*;
import cn.gukeer.platform.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by conn on 2016/8/1.
 * student、patriarch
 */
@Controller
@RequestMapping(value = "/student")
public class
StudentController extends BasicController {

    @Autowired
    ClassService classService;
    @Autowired
    StudentService studentService;
    @Autowired
    SchoolService schoolService;
    @Autowired
    UserService userService;
    @Autowired
    PatriarchService patriarchService;
    
    @RequestMapping(value = "/index")
    public String index(HttpServletRequest request, Model model) {
        return "student/index";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(HttpServletRequest request, Model model) {
        String _id = getParamVal(request, "id");
        String focusNode = getParamVal(request, "focusNode");       //当前选中的节点
        String id = _id;
        String schoolId = getLoginUser().getSchoolId();
        SchoolView schoolview = new SchoolView();
        //用作选中判断的字符串形式xd
        String nowxd = ""; 
        //用作选中判断的字符串形式nj
        String nownj = "";      
        //String nowbj = "";      
        String nowxq = "";
        String[] judge = {"-1", "-1", "-1", "-1"};
        schoolview = classService.selectAndMakeTree(schoolId, judge,false);
        
        // 学生信息
        Student student = studentService.selectStudentById(id);
        // 班级list bjList
        List<GradeClass> bjList = schoolService.selectGradeClassBySchoolId(getLoginUser().getSchoolId());
        // 学段list xdList
        List<ClassSection> xdList = classService.getAllClassSectionBySchoolId(getLoginUser().getSchoolId());
        // 校区
        List<SchoolType> schoolTypeList = schoolService.selectSchoolTypeBySchoolId(getLoginUser().getSchoolId());
        // 家长信息
        Patriarch fpatriarch = patriarchService.findPatriarchByStudentId(id, 1);
        Patriarch mpatriarch = patriarchService.findPatriarchByStudentId(id, 2);
        //学生所处年级的所有班级
        List<GradeClass> currentNj = new ArrayList<GradeClass>();
        //所处学段的年级列表 key-value形式
        List<KVEntity> njvalue = new ArrayList<KVEntity>();
        //当前学段下所有年级列表
        List<Integer> nowNj = new ArrayList<Integer>();                 
        List<KVEntity> xqList = new ArrayList<KVEntity>();
        //选中节点或学生 xd,nj
        String xd = "";
        String nj = "0", bj = "0", xq = "0";
        //获取选中节点 xd,nj
        if (_id == "" || _id == null) {             
            int xdStart = focusNode.indexOf("section_");
            int xqStart = focusNode.indexOf("xq_");
            int njStart = focusNode.indexOf("nianji");
            if (xdStart != -1) {
                xd = xqStart != -1 ?
                        focusNode.substring(xdStart + "section_".length(), xqStart) :
                        focusNode.substring(xdStart + "section_".length());
            }
            if (xqStart != -1) {
                xq = njStart != -1 ?
                        focusNode.substring(xqStart + "xq_".length(), njStart) :
                        focusNode.substring(xqStart + "xq_".length());
            }
            if (njStart != -1)
                nj = focusNode.substring(njStart + "nianji".length());

            if (focusNode.indexOf("banji") >= 0) {
                bj = focusNode.substring("banji".length());
                for (GradeClass gradeClass : bjList) {
                    if (gradeClass.getId().equals(bj)) {
                        xd = gradeClass.getXd();
                        nj = gradeClass.getNj().toString();
                        xq = gradeClass.getXq();
                    }
                }
            }
        } else {                
            xd = student.getXd();
            nj = student.getNj().toString();
            bj = student.getClassId();

            for (GradeClass gradeClass : bjList) {
                if (gradeClass.getId().equals(bj)) {
                    xq = gradeClass.getXq();
                }
            }
        }

        //获取当前下拉菜单的所有默认选项
        if (!xd.equals("") && !xq.equals("0")) {
            for (GradeClass gradeClass : bjList) {
                if (gradeClass.getXd().equals(xd)) {
                    if (!nj.equals("0") && nj.equals(gradeClass.getNj().toString())) {
                        currentNj.add(gradeClass);          //当前年级下所有班级列表
                    }
                    nowNj.add(gradeClass.getNj());          //当前学段下所有年级列表
                }
            }

            if (nowNj.size() > 1) {                 //年级去重
                for (int i = 0; i < nowNj.size() - 1; i++) {
                    for (int j = i + 1; j < nowNj.size(); j++)
                        if (nowNj.get(i) == nowNj.get(j)) {
                            nowNj.remove(j);
                            j--;
                        }
                }
            }

            for (int k : nowNj) {           //将年级列表以 key-value形式保存
                String key = ConstantUtil.getGradeNj(k);
                String nst = "school_" + schoolId + "section_" + xd + "xq_" + xq + "nianji" + k;
                KVEntity map = new KVEntity(key, nst);
                njvalue.add(map);
            }
        }
        if (!xd.equals("")) {
            for (SchoolType schoolType : schoolTypeList) {      //校区key - value
                String typeKey = "";
                String typeValue = "";
                typeKey = schoolType.getName();
                typeValue = "school_" + schoolId + "section_" + xd + "xq_" + schoolType.getId();
                KVEntity kvEntity = new KVEntity(typeKey, typeValue);
                xqList.add(kvEntity);
            }
        }
        if (xd != "")        //如果有选中的xd
            nowxd = "school_" + schoolId + "section_" + xd;
        if (!xq.equals("0"))
            nowxq = nowxd + "xq_" + xq;
        if (!nj.equals("0"))        //如果有选中的nj
            nownj = nowxq + "nianji" + nj;

        if ("1".equals(request.getParameter("type"))) {
            //若type是1，只是查看人员信息，输入框全部disabled
            model.addAttribute("disabled", "disabled");
        }

        model.addAttribute("nowxd", nowxd);
        model.addAttribute("nowxq", nowxq);
        model.addAttribute("nownj", nownj);
        model.addAttribute("nowbj", bj);
        model.addAttribute("xqList", xqList);
        model.addAttribute("njvalue", njvalue);
        model.addAttribute("currentNj", currentNj);
        model.addAttribute("student", student);
        model.addAttribute("schoolview", schoolview);
        model.addAttribute("fpatriarch", fpatriarch);
        model.addAttribute("mpatriarch", mpatriarch);

        return "student/edit";
    }

    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResultEntity save(HttpServletRequest request, Model model) {

        try {
            String id = getParamVal(request, "id");
            String xsxm = getParamVal(request, "xsxm");
            String xszp = getParamVal(request, "xszp");
            String xmpy = getParamVal(request, "xmpy");
            String xsxb = getParamVal(request, "xsxb");
            String csrq = getParamVal(request, "csrq").replace("-", "");
            String gb = getParamVal(request, "gb");
            String mz = getParamVal(request, "mz");
            String xd = getParamVal(request, "xd");
            String nj = getParamVal(request, "nj");
            String bj = getParamVal(request, "bj");
            String xjh = getParamVal(request, "xjh");
            String xh = getParamVal(request, "xh");
            String jyid = getParamVal(request, "jyid");
            String qgxjh = getParamVal(request, "qgxjh");
            String yxzjlx = getParamVal(request, "yxzjlx");
            String yxzjh = getParamVal(request, "yxzjh");
            String status = getParamVal(request, "status");
            String zslb = getParamVal(request, "zslb");
            String rxrq = getParamVal(request, "rxrq").replace("-", "");
            String zzmm = getParamVal(request, "zzmm");
            String xslb = getParamVal(request, "xslb");
            String lydq = getParamVal(request, "lydq");
            String hkxz = getParamVal(request, "hkxz");
            String jg = getParamVal(request, "jg");
            String hkszd = getParamVal(request, "hkszd");
            String xjzd = getParamVal(request, "xjzd");
            String sfbshk = getParamVal(request, "sfbshk");

            if (xmpy == "" || xmpy == null) {          //学生拼音设置，为空时系统判断
                xmpy = CnToPyUtils.getPingYin(getParamVal(request, "xsxm"));
            }

            Student student = new Student();
            student.setXmpy(xmpy);
            student.setId(id);
            student.setSchoolId(getLoginUser().getSchoolId());
            student.setXsxm(xsxm);
            student.setXszp(xszp);
            student.setXsxb(NumberConvertUtil.convertS2I(xsxb));
            student.setCsrq(DateUtils.yyyyMMddToMillis(csrq));
            student.setGb(gb);
            student.setMz(mz);
            student.setXd(xd);
            student.setNj(NumberConvertUtil.convertS2I(nj));
            student.setClassId(bj);
            student.setXjh(xjh);
            student.setXh(xh);
            student.setJyid(jyid);
            student.setQgxjh(qgxjh);
            student.setYxzjlx(NumberConvertUtil.convertS2I(yxzjlx));
            student.setYxzjh(yxzjh);
            student.setJg(jg);
            student.setHkszd(hkszd);
            student.setXjzd(xjzd);
            if (status != null && status != "")
                student.setStatus(NumberConvertUtil.convertS2I(status));
            else student.setStatus(-1);
            student.setRxrq(DateUtils.yyyyMMddToMillis(rxrq));
            student.setZslb(NumberConvertUtil.convertS2I(zslb));
            student.setZzmm(NumberConvertUtil.convertS2I(zzmm));
            student.setXslb(NumberConvertUtil.convertS2I(xslb));
            student.setLydq(lydq);
            student.setHkxz(hkxz);
            student.setSfbshk(NumberConvertUtil.convertS2I(sfbshk));
            User user = getLoginUser();

            if (id != "") {
                student.setUpdateBy(user.getId());
                student.setUpdateDate(System.currentTimeMillis());
            } else {
                student.setCreateBy(user.getId());
                student.setCreateDate(System.currentTimeMillis());
            }
            String studentId = saveStudentAndPatriarch(student);
            // 保持父母信息
            if (studentId !="") {
                // 父亲
                String fId = getParamVal(request, "fid");
                String fname = getParamVal(request, "fname");
                String fphone = getParamVal(request, "fphone");
                String fzhiye = getParamVal(request, "fzhiye");
                String fdanwei = getParamVal(request, "fdanwei");
                String fjhr = getParamVal(request, "fjhr");
                String fyqsh = getParamVal(request, "fyqsh");
                if (!(StringUtils.isEmpty(fId) && StringUtils.isEmpty(fname) && StringUtils.isEmpty(fphone)
                        && StringUtils.isEmpty(fzhiye) && StringUtils.isEmpty(fdanwei)
                        && StringUtils.isEmpty(fjhr) && StringUtils.isEmpty(fyqsh))) {

                    Patriarch patriarch = new Patriarch();
                    patriarch.setId(fId);
                    patriarch.setName(fname);
                    patriarch.setPhone(fphone);
                    patriarch.setWork(fzhiye);
                    patriarch.setWorkAt(fdanwei);
                    patriarch.setSfjhr(NumberConvertUtil.convertS2I(fjhr));
                    patriarch.setSfyqsh(NumberConvertUtil.convertS2I(fyqsh));
                    patriarch.setPatriarchFlag(1);

                    int _fid = NumberConvertUtil.convertS2I(fId);
                    if (_fid > 0) {
                        Patriarch p = patriarchService.findPatriarchById(patriarch.getId());

                        patriarch.setStudentId(studentId);
                        patriarch.setUpdateBy(user.getId());
                        patriarch.setUpdateDate(System.currentTimeMillis());
                        patriarchService.updatePatriarch(patriarch);
                    } else {
                        patriarch.setId(PrimaryKey.get());
                        patriarch.setStudentId(studentId);
                        patriarch.setCreateBy(user.getId());
                        patriarch.setCreateDate(System.currentTimeMillis());
                        patriarchService.insertPatriarch(patriarch);
                    }

                }

                // 母亲
                String mId = getParamVal(request, "mid");
                String mname = getParamVal(request, "mname");
                String mphone = getParamVal(request, "mphone");
                String mzhiye = getParamVal(request, "mzhiye");
                String mdanwei = getParamVal(request, "mdanwei");
                String mjhr = getParamVal(request, "mjhr");
                String myqsh = getParamVal(request, "myqsh");
                if (!(StringUtils.isEmpty(mId) && StringUtils.isEmpty(mname) && StringUtils.isEmpty(mphone)
                        && StringUtils.isEmpty(mzhiye) && StringUtils.isEmpty(mdanwei)
                        && StringUtils.isEmpty(mjhr) && StringUtils.isEmpty(myqsh))) {
                    Patriarch patriarch = new Patriarch();
                    patriarch.setId(mId);
                    patriarch.setName(mname);
                    patriarch.setPhone(mphone);
                    patriarch.setWork(mzhiye);
                    patriarch.setWorkAt(mdanwei);
                    patriarch.setSfjhr(NumberConvertUtil.convertS2I(mjhr));
                    patriarch.setSfyqsh(NumberConvertUtil.convertS2I(myqsh));
                    patriarch.setPatriarchFlag(2);

                    int _mid = NumberConvertUtil.convertS2I(mId);
                    if (_mid > 0) {
                        Patriarch p = patriarchService.findPatriarchById(patriarch.getId());

                        patriarch.setStudentId(studentId);
                        patriarch.setUpdateBy(user.getId());
                        patriarch.setUpdateDate(System.currentTimeMillis());
                        patriarchService.updatePatriarch(patriarch);
                    } else {
                        patriarch.setId(PrimaryKey.get());
                        patriarch.setStudentId(studentId);
                        patriarch.setCreateBy(user.getId());
                        patriarch.setCreateDate(System.currentTimeMillis());
                        patriarchService.insertPatriarch(patriarch);
                    }
                }
            } else {
                throw new ErrcodeException("insert or modify student error");
            }

        } catch (Exception e) {
            logger.error("error: {}, cause: {}", e.getMessage(), e.getCause());
            throw new ErrcodeException("insert or modify student error, cause:" + e.getCause());
        }

        return ResultEntity.newResultEntity();
    }

    @Transactional
    private String saveStudentAndPatriarch(Student student) {

        String studentId = studentService.saveExtension(student);

        if (studentId == null) {
            throw new ErrcodeException("save student msg error");
        }

        return studentId;
    }

    @ResponseBody
    @RequestMapping(value = "/import", method = RequestMethod.POST)
    public ResultEntity importExcel(@RequestParam(value = "file") MultipartFile file, RedirectAttributes redirectAttributes) {
        try {
            ImportExcel importExcel = new ImportExcel(file, 0, 0);
            List<IOStudentView> list = importExcel.getDataList(IOStudentView.class, 1);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultEntity.newResultEntity();
    }

    @ResponseBody
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ResultEntity studentdelete(HttpServletRequest request, Model model) {
        String studentId = getParamVal(request, "studentId");
        studentService.changeDelFlag(studentId);
        int isSuccess=userService.deleteUserById(studentId);
        if(isSuccess == 1) {
        	return ResultEntity.newResultEntity("成功", "");
        } else {
        	return ResultEntity.newResultEntity("失败", "");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/multiDelete", method = RequestMethod.POST)
    public ResultEntity multiDelete(HttpServletRequest request, Model model) {
        String students = getParamVal(request, "students");
        String[] studentId = students.split(",");
        for (int i = 0; i < studentId.length; i++) {
            String id = studentId[i];
            if (id != "") {
                studentService.changeDelFlag(id);
            }
        }
        return ResultEntity.newResultEntity("成功", "");
    }

}

