<%@ page import="cn.gukeer.platform.common.ConstantUtil" %>
<%@ page import="cn.gukeer.common.utils.FileUtils" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ include file="../common/common.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="njList" value="<%=ConstantUtil.njList%>"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title></title>
    <script src="${ctxStatic}/js/jquery-1.7.2.js"></script>
    <script src="${ctxStatic}/js/laydate/laydate.js"></script>
    <script src="${ctxStatic}/upload/h5upload.js"></script>
    <script src="${ctxStatic}/js/layer/layer.js" type="text/javascript" charset="utf-8"></script>
    <script src="${ctxStatic}/js/openDialog.js" type="text/javascript" charset="utf-8"></script>
    <style>
        .container {
            width: 800px;
            margin: 0 auto;
            /*padding-top: 30px;*/
            font: 12px '微软雅黑';
        }

        .container > h3 {
            font-size: 16px;
            font-weight: normal;
            color: #54AB37;
            margin: 20px 0;
            padding: 0px 0 0px 8px;
            border-left: 3px solid #54AB37;
        }

        .container > h3 button {
            float: right;
            color: #fff;
            border-radius: 2px;
            background: #54AB37;
            border: 1px solid #54AB37;
            width: 80px;
            height: 35px;
            font-weight: bold
        }

        .stuMsg {
            overflow: hidden;
        }

        ul {
            margin: 0;
            padding: 0;
            list-style: none;
        }

        .left {
            float: left;
            width: 50%;
        }

        .right {
            float: right;
            width: 50%;
        }

        .stuMsg span {
            display: inline-block;
            width: 36%;
            text-align: right;
        }

        .stuMsg input[type=text] {
            width: 190px;
            height: 26px;
            padding:0;
            padding-left:5px;
            border-radius:2px;
            border:1px solid #a9a9a9;
            outline:none;
        }

        .stuMsg input[type=radio] {
            margin: 0 20px;
        }

        .stuMsg input[class=laydate-icon] {
            width: 190px;
            border: 1px solid #a9a9a9;
        }

        .stuMsg label {
            margin-right: 61px;
        }

        ul li {
            position: relative;
            margin: 15px 0;
        }
        li span:first-child{
            color: #666;
        }
        b {
            font-size: 20px;
            color: #E51C23;
            position: absolute;
            top: 4px;
            right: 36px;
        }

        .radio b {
            top: -3px;
        }

        .stuMsg select {
            font-size: 14px;
            color: #333;
            width: 197px;
            height: 28px;
            padding-left: 5px;
            border: 1px solid #a9a9a9;
            border-radius: 2px;
            outline: none;
        }

        .uploading {
            display: inline-block;
            vertical-align: -600%;
            width: 60%;
            text-align: center;
        }

        .uploading p {
            width: 90px;
            height: 86px;
            background: url('${ctxStatic}/image/image.png');
            margin: 0;
            margin-left: 104px;
        }

        .uploading button {
            margin-top: 10px;
            padding: 5px 20px;
            color: #fff;
            background: #54AB37;
            border: 1px solid #54AB37;
            font-weight: bold;
        }

        .parentMsg P {
            FONT-SIZE: 14PX;
            color: #666;
            padding-left:11px;
        }

        .parentMsg ul {
            overflow: hidden;
            box-sizing: border-box;
        }

        .parentMsg ul li {
            float: left;
            width: 50%;
            margin: 15px 0
        }

        .parentMsg ul li span {

            display: inline-block;
            width: 36%;
            text-align: right;
        }

        .parentMsg input[type=text] {
            height: 28px;
            padding:0;
            padding-left:5px;
            width:190px;
            border:1px solid #a9a9a9;
            border-radius: 2px;
            outline: none;
        }

        .parentMsg select {
            width: 190px;
            height: 28px;
            border-radius: 4px;
            outline: none;
        }

        .container table {
            border-collapse: collapse
        }

        .container table th, .container table td {
            border: 1px solid #ddd;
            padding: 10px 0;
        }

        .container table th {
            background: #eee;
        }

        .addContent {
            font-size: 16px;
            color: #999;
            padding-left: 15px;
        }
    </style>
    <script>

        function doSubmit() {//回调函数，在编辑和保存动作时，供openDialog调用提交表单。

            var name = $("input[name='xsxm']").val();
            var xd = $("select[id='xueduan']").val();
            var nj = $("select[id='nianji']").val();
            var bj = $("select[id='banji']").val();

            if (name == '' || name.trim().length == 0) {
                layer.msg("学生姓名不能为空！");
                return false;
            }
            if (xd == null || xd == '') {
                layer.msg("学段不能为空");
                return false;
            }
            if (nj == '0' || nj == '-1' || nj ==null) {
                layer.msg("年级不能为空");
                return false;
            }
            if (bj == '0' || bj == '-1' || bj == null) {
                layer.msg("班级不能为空");
                return false;
            }
            xd = xd.substring(xd.indexOf("section_") + 8);
            nj = nj.substring(nj.indexOf("nianji") + 6);
            bj = bj.substring("banji".length);


            $.post($("form").attr('action'), {
                id: $("input[name='id']").val(),
                xsxm: name,
                xmpy: $("input[name='xmpy']").val(),
                xszp: $("input[name='headUrl']").val(),
                xsxb: $("#xsxb").val(),
                csrq: $("input[name='csrq']").val(),
                gb: $("input[name='gb']").val(),
                mz: $("input[name='mz']").val(),
                xd: xd,
                nj: nj,
                bj: bj,
//                xjh: $("input[name='
// ']").val(),
                xh: $("input[name='xh']").val(),
                jyid: $("input[name='jyid']").val(),
//                qgxjh: $("input[name='qgxjh']").val(),
                yxzjlx: $("#yxzjlx").val(),
                yxzjh: $("input[name='yxzjh']").val(),
                status: $("#status").val(),
                zslb: $("#zslb").val(),
                rxrq: $("input[name='rxrq']").val(),
                zzmm: $("#zzmm").val(),
                xslb: $("#xslb").val(),
                lydq: $("#lydq").val(),
                hkxz: $("#hkxz").val(),
                jg: $("input[name='jg']").val(),
                hkszd: $("input[name='hkszd']").val(),
                xjzd: $("input[name='xjzd']").val(),
                sfbshk: $("#sfbshk").val(),
                fid: $("input[name='fid']").val(),
                fname: $("input[name='fname']").val(),
                fphone: $("input[name='fphone']").val(),
                fzhiye: $("input[name='fzhiye']").val(),
                fdanwei: $("input[name='fdanwei']").val(),
                fjhr: $("#fjhr").is(':checked') ? 1 : 0,
                fyqsh: $("#fyqsh").is(':checked') ? 1 : 0,
                mid: $("input[name='mid']").val(),
                mname: $("input[name='mname']").val(),
                mphone: $("input[name='mphone']").val(),
                mzhiye: $("input[name='mzhiye']").val(),
                mdanwei: $("input[name='mdanwei']").val(),
                mjhr: $("#mjhr").is(':checked') ? 1 : 0,
                myqsh: $("#myqsh").is(':checked') ? 1 : 0,
            }, function (retJson) {
                if (retJson.code == '0') {
                    <%--window.location.replace("${ctx}/student/index");--%>
                    window.parent.location.reload(true);
                } else {
                    layer.msg(retJson.msg);
                }
            });
            return true;
        }
    </script>
</head>
<body>

<form action="${ctx}/student/save">
    <input name="id" type="hidden" value="${student.id}">
    <input name="fid" type="hidden" value="${fpatriarch.id}">
    <input name="mid" type="hidden" value="${mpatriarch.id}">
    <div class="container">
        <h3>学生信息</h3>
        <div class="stuMsg">
            <ul class="left">
                <li>
                    <span>姓名：</span>
                    <input ${disabled} type="text" name="xsxm" value="${student.xsxm}"/>
                    <b>*</b>
                </li>
                <li class="radio">
                    <span>性别：</span>
                    <select ${disabled} id="xsxb">
                        <option value="-1"></option>
                        <option value="1" <c:if test="${student.xsxb == 1}">selected</c:if>>男</option>
                        <option value="2" <c:if test="${student.xsxb == 2}">selected</c:if>>女</option>
                    </select>
                </li>
                <li>
                    <span>出生日期：</span>
                    <input ${disabled} type="text" name="csrq"
                    <%--value="${student.csrq}" --%>
                            <c:if test="${gukeer:notEmptyString(student.csrq)}"> value="${gukeer:millsToyyyyMMdd(student.csrq)}" </c:if>
                           id="birthDate" class="laydate-icon"/>
                </li>
                <li>
                    <span>国别：</span>
                    <input ${disabled} type="text" name="gb" value="${student.gb}"/>
                </li>
                <li>
                    <span>民族：</span>
                    <input ${disabled} type="text" name="mz" value="${student.mz}"/>
                </li>
                <li>
                    <span>学段：</span>
                    <select ${disabled} id="xueduan" name="xueduan">
                        <option value="0">请选择学段</option>
                        <c:forEach items="${schoolview.sections}" var="xd">
                            <option value="${xd.id}"
                                    <c:if test="${nowxd eq xd.id}">selected</c:if>  > ${xd.name}</option>
                        </c:forEach>
                    </select><b>*</b>
                </li>
                <li>
                    <span>校区：</span>
                    <select ${disabled} id="xq" name="xq">
                        <option value="0">请选择校区</option>
                        <c:forEach items="${xqList}" var="xq">
                            <option value="${xq.value}"
                                    <c:if test="${nowxq eq xq.value}">selected</c:if>  > ${xq.key}</option>
                        </c:forEach>
                    </select><b>*</b>
                </li>
                <li>
                    <span>年级：</span>
                    <select ${disabled} id="nianji" name="nianji">
                        <c:if test="${gukeer:emptyString(nowxd) || gukeer:emptyString(nownj)}">
                            <option value="0">请选择年级</option>
                        </c:if>

                        <c:if test="${gukeer:notEmptyString(nowxd)}">
                            <c:forEach items="${njvalue}" var="nvalue">
                                <option value=${nvalue.value}
                                                <c:if test="${nownj eq nvalue.value}">selected</c:if> >
                                        ${nvalue.key}
                                </option>
                            </c:forEach>
                        </c:if>
                    </select><b>*</b>
                </li>
                <li>
                    <span>班级：</span>
                    <select ${disabled} id="banji" name="banji">
                        <c:if test="${nowbj == '0'}">
                            <option value="0">请选择班级</option>
                        </c:if>

                        <c:if test="${gukeer:notEmptyString(nownj)}">
                            <c:forEach items="${currentNj}" var="bj">
                                <option value="banji${bj.id}" <c:if test="${nowbj==bj.id}">selected</c:if>>
                                        ${bj.name}
                                </option>
                            </c:forEach>
                        </c:if>
                    </select><b>*</b>
                </li>
               <%-- <li>
                    <span>学生学籍号：</span>
                    <input type="text" name="xjh" value="${student.xjh}"/>
                </li>--%>
                <li>
                    <span>学籍号：</span>
                    <input ${disabled} type="text" name="xh" value="${student.xh}"/><b>*</b>
                </li>
                <li>
                    <span>教育ID号：</span>
                    <input ${disabled} type="text" name="jyid" value="${student.jyid}"/>
                </li>
              <%--  <li>
                    <span>全国学籍号：</span>
                    <input type="text" name="qgxjh" value="${student.qgxjh}"/>
                </li>--%>
                <li>
                    <span>有效证件类型：</span>
                    <select ${disabled} id="yxzjlx">
                        <option value="-1"></option>
                        <option value="1" <c:if test="${student.yxzjlx == 1}">selected</c:if>>身份证</option>
                        <option value="2" <c:if test="${student.yxzjlx == 2}">selected</c:if>>护照</option>
                    </select>
                </li>
                <li>
                    <span>有效证件号：</span>
                    <input ${disabled} type="text" name="yxzjh" value="${student.yxzjh}"/>
                </li>
                <li>
                    <span>是否按本市户口学生对待：</span>
                    <select ${disabled} id="sfbshk">
                        <option value="-1"></option>
                        <option value="1" <c:if test="${student.sfbshk == 1}">selected</c:if>>是</option>
                        <option value="2" <c:if test="${student.sfbshk == 2}">selected</c:if>>否</option>
                    </select>
                </li>
            </ul>
            <ul class="right">
                <li style="height: 120px;">
                    <style>
                        .container ul.right li:first-child{margin:0;}
                        .container ul.right{padding-top:10px;}
                        .conrainer ul.right li:first-child ul{width:80px;height:80px;display:inline-block;}
                        .container ul.right li:first-child ul li{margin:0;margin-left:50px;width:100%;height:100%;border:1px solid #ddd;}
                        .container ul.right li:first-child ul li .removeBtn{position:absolute;left:72.5px;top:-7px;
                        z-index:999;width:15px;height:15px;background: #f00;cursor: pointer;
                        color:#fff;line-height:10px;border-radius:50%;}
                    </style>
                    <span style="vertical-align: top;">学生照片：</span>
                    <input type="file" id="file-input" style="display: none" accept="image/*">
                    <input type="hidden" name="headUrl" id="headUrl" value="${student.xszp}">


                    <ul class="img-list-icon" style="width:80px;height:80px;display:inline-block">
                        <li style="">

                            <c:if test="${gukeer:notEmptyString(student.xszp)}">
                                <c:if test="${gukeer:emptyString(disabled)}">
                                    <div class="removeBtn" onclick="rmPic(this)">—</div>
                                </c:if>
                                <img src="${ctx}/file/pic/show?picPath=${student.xszp}" data-url="${student.xszp}"
                                     width="100%" height="100%" id="head_url">
                            </c:if>
                            <c:if test="${gukeer:emptyString(student.xszp)}">
                                <img src="${ctx}/file/pic/show?picPath=${defaultHead}" data-url="${student.xszp}"
                                     width="100%" height="100%" id="head_url">
                            </c:if>

                        </li>
                    </ul>
                    <c:if test="${gukeer:emptyString(disabled)}">
                        <div id="iconUpload" class="uploading" style="width:150px;height:30px;color:#fff;border:1px solid #54AB37;background: #54AB37;border-radius: 4px;cursor:pointer;position: relative;left: -65px;top:-65px;">
                            <a  style="display: inline-block;height:100%;line-height: 30px; ">上传照片</a>
                        </div>
                    </c:if>
                    <c:if test="${gukeer:notEmptyString(disabled)}">

                    </c:if>

                </li>
                <li>
                    <span>拼音：</span>
                    <input ${disabled} type="text" name="xmpy" value="${student.xmpy}"/>
                </li>
                <li>
                    <span>在校状态：</span>
                    <select ${disabled} id="status">
                        <option value="0" <c:if test="${student.status == 0}">selected</c:if>>在籍在校</option>
                        <option value="1" <c:if test="${student.status == 1}">selected</c:if>>在籍离校</option>
                        <option value="2" <c:if test="${student.status == 2}">selected</c:if>>在校不在籍</option>
                        <option value="3" <c:if test="${student.status == 3}">selected</c:if>>不在籍不在校</option>
                        <option value=""
                        <c:choose>
                            <c:when test="${gukeer:emptyString(student.status) && gukeer:notEmptyString(student)}">
                                selected
                            </c:when>
                            <c:otherwise>
                                hidden
                            </c:otherwise>
                        </c:choose>
                        ></option>
                    </select>
                </li>
                <li>
                    <span>招生类别：</span>
                    <select ${disabled} id="zslb">
                        <option value="-1"></option>
                        <option value="0" <c:if test="${student.zslb == 0}">selected</c:if>>普通入学</option>
                        <option value="1" <c:if test="${student.zslb == 1}">selected</c:if>>民族班</option>
                        <option value="2" <c:if test="${student.zslb == 2}">selected</c:if>>体育特招</option>
                        <option value="3" <c:if test="${student.zslb == 3}">selected</c:if>>外校转入</option>
                        <option value="4" <c:if test="${student.zslb == 4}">selected</c:if>>恢复入学资格</option>
                        <option value="5" <c:if test="${student.zslb == 5}">selected</c:if>>外籍</option>
                        <option value="6" <c:if test="${student.zslb == 6}">selected</c:if>>其他</option>
                    </select>
                </li>
                <li>
                    <span>入学时间：</span>
                    <input ${disabled} type="text" name="rxrq" id="enterDate"
                    <%--value="${student.rxrq}"--%>
                            <c:if test="${gukeer:notEmptyString(student.rxrq)}">
                                value="${gukeer:millsToyyyyMMdd(student.rxrq)}"
                            </c:if>
                           class="laydate-icon"/>
                </li>
                <li>
                    <span>政治面貌：</span>
                    <select ${disabled} id="zzmm">
                        <option value="-1"></option>
                        <option value="0" <c:if test="${student.zzmm == 0}">selected</c:if>>群众</option>
                        <option value="1" <c:if test="${student.zzmm == 1}">selected</c:if>>团员</option>
                        <option value="2" <c:if test="${student.zzmm == 2}">selected</c:if>>党员</option>
                    </select>
                </li>
                <li>
                    <span>学生类别：</span>
                    <select ${disabled} id="xslb">
                        <option value="-1"></option>
                        <option value="0" <c:if test="${student.xslb == 0}">selected</c:if>>普通学生</option>
                        <option value="1" <c:if test="${student.xslb == 1}">selected</c:if>>随班就读生</option>
                        <option value="2" <c:if test="${student.xslb == 2}">selected</c:if>>残障学生</option>
                        <option value="3" <c:if test="${student.xslb == 3}">selected</c:if>>其他</option>
                    </select>
                </li>
                <li>
                    <span>来源地区：</span>
                    <select ${disabled} id="lydq">
                        <option value="-1"></option>
                        <option value="0" <c:if test="${student.lydq == 0}">selected</c:if>>区县内</option>
                        <option value="1" <c:if test="${student.lydq == 1}">selected</c:if>>省市内</option>
                        <option value="2" <c:if test="${student.lydq == 2}">selected</c:if>>省市外</option>
                    </select>
                </li>
                <li>
                    <span>户口性质：</span>
                    <select ${disabled} id="hkxz">
                        <option value="-1"></option>
                        <option value="0" <c:if test="${student.hkxz == 0}">selected</c:if>>农村</option>
                        <option value="1" <c:if test="${student.hkxz == 1}">selected</c:if>>城镇</option>
                        <option value="2" <c:if test="${student.hkxz == 2}">selected</c:if>>其他</option>
                    </select>
                </li>
                <li>
                    <span>籍贯：</span>
                    <input ${disabled} type="text" name="jg" value="${student.jg}"/>
                </li>
                <li>
                    <span>户口所在地：</span>
                    <input ${disabled} type="text" name="hkszd" value="${student.hkszd}"/>
                </li>
                <li>
                    <span>现居住地：</span>
                    <input ${disabled} type="text" name="xjzd" value="${student.xjzd}"/>
                </li>
            </ul>
        </div>
        <h3>学生家长信息</h3>
        <div class="parentMsg">
            <p>父亲</p>
            <div class="fqMsg">
                <ul>
                    <li>
                        <span>姓名：</span>
                        <input ${disabled} type="text" name="fname" value="${fpatriarch.name}"/>
                    </li>
                    <li>
                        <span>联系电话：</span>
                        <input ${disabled} type="text" name="fphone" value="${fpatriarch.phone}"/>
                    </li>
                    <li>
                        <span>职务或职业：</span>
                        <input ${disabled} type="text" name="fzhiye" value="${fpatriarch.work}">
                    </li>
                    <li>
                        <span>工作单位：</span>
                        <input ${disabled} type="text" name="fdanwei" value="${fpatriarch.workAt}"/>
                    </li>
                </ul>
                <ul>
                    <li>
                        <span>是否监护人：</span>
                        <input ${disabled} type="checkbox" <c:if
                                test="${empty fpatriarch.sfjhr || fpatriarch.sfjhr == 1}"> checked </c:if> id="fjhr"/>
                    </li>
                    <li>
                        <span>是否生活在一起：</span>
                        <input ${disabled} type="checkbox" <c:if
                                test="${empty fpatriarch.sfyqsh || fpatriarch.sfyqsh == 1}"> checked </c:if>
                               id="fyqsh"/>
                    </li>
                </ul>
            </div>
            <p>母亲</p>
            <div class="fqMsg">
                <ul>
                    <li>
                        <span>姓名：</span>
                        <input ${disabled} type="text" name="mname" value="${mpatriarch.name}"/>
                    </li>
                    <li>
                        <span>联系电话：</span>
                        <input ${disabled} type="text" name="mphone" value="${mpatriarch.phone}"/>
                    </li>
                    <li>
                        <span>职务或职业：</span>
                        <input ${disabled} type="text" name="mzhiye" value="${mpatriarch.work}"/>
                    </li>
                    <li>
                        <span>工作单位：</span>
                        <input ${disabled} type="text" name="mdanwei" value="${mpatriarch.workAt}"/>
                    </li>
                </ul>
                <ul>
                    <li>
                        <span>是否监护人：</span>
                        <input ${disabled} type="checkbox" <c:if
                                test="${empty mpatriarch.sfjhr || mpatriarch.sfjhr == 1}"> checked </c:if> id="mjhr"/>
                    </li>
                    <li>
                        <span>是否生活在一起：</span>
                        <input ${disabled} type="checkbox" <c:if
                                test="${empty mpatriarch.sfyqsh || mpatriarch.sfyqsh == 1}"> checked </c:if>
                               id="myqsh"/>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</form>
<script>
    !function () {
        laydate.skin('molv');
        laydate({elem: '#birthDate'})
        laydate({elem: '#enterDate'})
    }();
    function toXd(_xd) {
        var xd;
        xd = _xd.substring(_xd.length - 1);
        return xd;
    }
    ;
    $(function () {
        zNodes0 = [
            {
                id: "${schoolview.id}",
                pId: "${schoolview.pid}",
                name: "${schoolview.name}",
                open: true
            },
            <c:forEach items='${schoolview.sections}' var='sections'>
            {
                id: "${sections.id}",
                pId: "${sections.pid}",
                name: "${sections.name}",
                open: ${sections.open}
            },
            <c:forEach items='${sections.schoolTypeView}' var='schoolTypeView'>
            {
                id: "${schoolTypeView.id}",
                pId: "${schoolTypeView.pid}",
                name: "${schoolTypeView.name}",
                open: ${schoolTypeView.open}
            },
            <c:forEach items='${schoolTypeView.njview}' var='njview'>
            {
                id: "${njview.tid}",
                pId: "${njview.pid}",
                name: "${njview.njname}",
                open: ${njview.open}
            },
            <c:forEach items='${njview.banjiview}' var='banJiView'>
            {
                id: "${banJiView.id}",
                pId: "${banJiView.pid}",
                name: "${banJiView.name}",
                open: ${banJiView.open}
            },
            </c:forEach>
            </c:forEach>
            </c:forEach>
            </c:forEach>

        ];
    });

    $("#xueduan").change(function () {
        var arr = new Array();
        $("#xq").empty();
        $("#nianji").empty();
        $("#banji").empty();
        $("#xq").append("<option value='-1'>请选择校区</option>");
        $("#nianji").append("<option value='-1'>请选择年级</option>");
        $("#banji").append("<option value='-1'>请选择班级</option>");
        <%--if (${which==0}) {--%>
        for (var i = 0; i < zNodes0.length; i++) {
            var node = zNodes0[i];
            if (node.pId === $("#xueduan").val()) {
                html = "<option value='" + node.id + "'  >" + node.name + "</option>"
                $("#xq").append(html);
            }
        }
    });
    $("#xq").change(function () {
        var arr = new Array();
        $("#nianji").empty();
        $("#banji").empty();
        $("#nianji").append("<option value='-1'>请选择年级</option>");
        $("#banji").append("<option value='-1'>请选择班级</option>");
        <%--if (${which==0}) {--%>
        for (var i = 0; i < zNodes0.length; i++) {
            var node = zNodes0[i];
            if (node.pId === $("#xq").val()) {
                html = "<option value='" + node.id + "'  >" + node.name + "</option>"
                $("#nianji").append(html);
            }
        }
    });


    $("#nianji").change(function () {
        var arr = new Array();
        $("#banji").empty();
        $("#banji").append("<option value='-1'>请选择班级</option>");
        for (var i = 0; i < zNodes0.length; i++) {
            var node = zNodes0[i];
            if (node.pId == $("#nianji").val()) {
                html = "<option value='" + node.id + "'  >" + node.name + "</option>"
                $("#banji").append(html);
            }
        }

    });


    $(document).ready(function () {
        var iconChoose = document.getElementById("file-input");
        $("#iconUpload").on("click", function () {
            iconChoose.click();
        }).on("touchstart", function () {
            $(this).addClass("touch")
        }).on("touchend", function () {
            $(this).removeClass("touch")
        });
        iconChoose.onchange = function () {
            if (!this.files.length) return;
            var files = Array.prototype.slice.call(this.files);
            if (files.length > 9) {
                alert("最多同时只可上传9张图片");
                return;
            }
            files.forEach(function (file, i) {
                if (!/\/(?:jpeg|png|gif)/i.test(file.type)) return;
                var reader = new FileReader();
                var li = document.createElement("li");
                //          获取图片大小
                var size = file.size / 1024 > 1024 ? (~~(10 * file.size / 1024 / 1024)) / 10 + "MB" : ~~(file.size / 1024) + "KB";
                li.innerHTML = '<div class="removeBtn" onclick="rmPic(this)" style="position:absolute;left:72.5px;top:-7px;' +
                        'z-index:999;width:15px;height:15px;background: #f00;' +
                        'color:#fff;line-height: 10px;border-radius: 50%;">—</div> ' ;
                        /*+
                        '<div class="progress" style="position:absolute;top:20px;"><span></span>' +
                        '</div><div class="size" style="position:absolute;top:40px;">' + size + '</div>';*/
                $(".img-list-icon").children().remove();
                $(".img-list-icon").append($(li));
                reader.onload = function () {
                    var result = this.result;
                    var img = document.createElement("img");
                    $(img).attr("width", "100%");
                    $(img).attr("height", "100%");
                    $(img).attr("data-url", "");
                    $(img).attr("class", "pictures");
                    img.src = result;
                    $(li).append(img);
                    //如果图片大小小于100kb，则直接上传
                    if (result.length <= maxsize) {
                        img = null;
                        uploads(result, file.type, $(li), file.name);
                        return;
                    }
                    //      图片加载完毕之后进行压缩，然后上传
                    if (img.complete) {
                        callback();
                    } else {
                        img.onload = callback;
                    }
                    function callback() {
                        var data = compress(img);
                        uploads(data, file.type, $(li), file.name);
                        img = null;
                    }
                };
                reader.readAsDataURL(file);
            })
        };

        function uploads(basestr, type, $li, name) {
            var text = window.atob(basestr.split(",")[1]);
            var buffer = new Uint8Array(text.length);
            var pecent = 0, loop = null;
            for (var i = 0; i < text.length; i++) {
                buffer[i] = text.charCodeAt(i);
            }
            var blob = getBlob([buffer], type);
            var xhr = new XMLHttpRequest();
            var formdata = getFormData();
            formdata.append('file', blob);
            formdata.append("imgName", name);
            formdata.append("imgPath", '<%=FileUtils.USER_HEADERS_PATH%>');
            xhr.open('post', '${ctx}/file/upload');
            xhr.onreadystatechange = function () {
                if (xhr.readyState == 4 && xhr.status == 200) {
                    var imageData = JSON.parse(xhr.responseText);
                    var text = imageData.data.imgRequestUrl ? '上传成功' : '上传失败';
                    clearInterval(loop);
                    //当收到该消息时上传完毕
                    $li.find(".progress span").animate({'width': "100%"}, pecent < 95 ? 200 : 0, function () {
                        $(this).html(text);
                    });
                    $("#headUrl").val(imageData.data.imgRequestUrl);
                    $("#head_url").attr("data-url", imageData.data.imgRequestUrl);
                }
            };
            //数据发送进度，前50%展示该进度
            xhr.upload.addEventListener('progress', function (e) {
                if (loop) return;
                mockProgress();
            }, false);
            //数据后50%用模拟进度
            function mockProgress() {
                if (loop) return;
                loop = setInterval(function () {
                    pecent++;
                    $li.find(".progress span").css('width', pecent + "%");
                    if (pecent == 100) {
                        clearInterval(loop);
                    }
                }, 50)
            }

            xhr.send(formdata);
        }
    });
    function rmPic(obj) {
        if (confirm("确定要删除这张图片吗?")) {
            var o = $(obj).parent("li");
            $(obj).parent("li").children().remove();
            o.append('<img src="${ctx}/file/pic/show?picPath=${defaultHead}" data-url="${student.xszp}"width="100%" height="100%" id="head_url">');
            document.getElementById("headUrl").value = "";
        }
    }
</script>
</body>
</html>