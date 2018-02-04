<%@ page import="cn.gukeer.platform.common.ConstantUtil" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ include file="../common/common.jsp" %>
<c:set var="njList" value="<%=ConstantUtil.njList%>"/>
<c:set var="bjlxList" value="<%=ConstantUtil.bjlxList%>"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title></title>
    <style>
        .container {
            width: 380px;
            margin: 0 auto;
            padding-top: 10px;
            padding-left: 45px;
        }

        .row {
            margin-bottom: 10px;
            font-family: '微软雅黑';
            margin:15px 0;
        }

        label {
            display: inline-block;
            width: 20%;
            font: 12px '微软雅黑';
            text-align: right;
            color:#666;
        }

        input {
            margin: 0;
            padding: 0;
            width: 190px;
            padding:0;
            padding-left:5px;
            height: 28px;
            font-size: 14px;
            outline: none;
            border:1px solid #a9a9a9;
            border-radius: 2px;
        }

        b {
            font-size: 20px;
            color: #E51C23;
            position: relative;
            top:6px;
            left:10px;

        }

        select {
            width: 197px;
            height: 28px;
            font-size:14px !important;
            border-radius: 2px;
            font-size: 14px;
            color: #333;
            outline: none;
        }

        p {
            text-align: right;
            margin-top: 20px;
        }

        p a {
            color: #999;
            font-size: 12px;
            text-decoration: none;
            margin-right: 20px;
        }

        p button {
            color: #fff;
            background: #29B6F6;
            border: 1px solid #29B6F6;
            padding: 8px 20px;
            font-weight: bold;
        }
    </style>
    <script src="${ctxStaticNew}/js/jquery.min.js"></script>
    <script  src="${ctxStaticNew}/js/layer/layer.js"></script>
    <script src="${ctxStaticNew}/js/openDialog.js"></script>
    <script  src="${ctxStaticNew}/js/laydate.js"></script>

    <script>

        function doSubmit() {//回调函数，在编辑和保存动作时，供openDialog调用提交表单。
//            $("#inputForm").submit();
//            return true;
            var name = $("input[name='name']").val();
            var xd = $("select[name='xd']").val();
            var nj = $("select[name='nj']").val();
            if (name == '' || name.trim().length == 0) {
                layer.msg("名称不能为空");
                return false;
            }

            if (xd == '0') {
                layer.msg("学段不能为空");
                return false;
            }

            if (nj == '0' || nj == '-1') {
                layer.msg("年级不能为空");
                return false;
            }

            $.post($("form").attr('action'), {
                id: $("input[name='id']").val(),
                name: $("input[name='name']").val(),
                shortName: $("input[name='shortName']").val(),
                bh: $("input[name='bh']").val(),
                xq: $("select[name='xq']").val(),
                bjlx: $("select[name='bjlx']").val(),
                rxnd: $("select[name='rxnd']").val(),
                xd: $("select[name='xd']").val(),
                nj: $("select[name='nj']").val()
            }, function (retJson) {
                layer.msg(retJson.msg);
                setTimeout(function () {
                    window.parent.location.reload(true);
                }, 1000);
            });

        }
    </script>
</head>
<body>
<form action="${ctx}/class/banji/save" id="inputForm" method="post">
    <input type="hidden" name="id" value="${gradeClass.id}">
    <div class="container">

        <div class="row">
            <label>班级名称：</label>
            <input type="text" name="name" value="${gradeClass.name}"/><b>*</b>
        </div>
        <div class="row">
            <label>班级简称：</label>
            <input type="text" name="shortName" value="${gradeClass.shortName}"/>
        </div>
        <div class="row">
            <label>班号：</label>
            <input type="text" name="bh" value="${gradeClass.bh}"/>
        </div>
        <div class="row">
            <label>校区：</label>
            <select name="xq">
                <c:forEach items="${schoolTypes}" var="schoolType">
                    <option value="${schoolType.id}" <c:if test="${focusXq==schoolType.id}">selected</c:if>>
                            ${schoolType.name}</option>
                </c:forEach>
            </select><b>*</b>
        </div>
        <div class="row">
            <label>班级类型：</label>
            <select id="bjlx" name="bjlx">

                <c:forEach items="${bjlxList}" var="bjlx">
                    <option value="${bjlx.key}"
                            <c:if test="${gradeClass.bjlx == bjlx.key}">selected</c:if>>${bjlx.value}</option>
                </c:forEach>
                <option value=""
                <%--<c:if test="${gukeer:emptyString(gradeClass.bjlx) && gukeer:notEmptyString(gradeClass)}"> selected </c:if>--%>
                <%--<c:if test="${gukeer:emptyString(gradeClass)}"> hidden </c:if> >--%>
                <c:choose>
                        <c:when test="${gukeer:emptyString(gradeClass.bjlx) && gukeer:notEmptyString(gradeClass)}">
                            selected
                        </c:when>
                <c:otherwise>
                        hidden
                </c:otherwise>
                </c:choose>>
                </option>
            </select>
        </div>
        <div class="row">
            <label>入学年度：</label>
            <%--<input type="text" name="rxnd" id="rxnd" class="laydate-icon" style="width: 60%"--%>
            <%--<c:if test="${gukeer:notEmptyString(gradeClass.rxnd)}"> value="${gukeer:millsToyyyyMMdd(gradeClass.rxnd)}" </c:if>>--%>
            <select id="rxnd" name="rxnd">
                <c:forEach items="${rxndList}" var="rxnd">
                    <option value="${rxnd}"
                            <c:if test="${gradeClass.rxnd == rxnd}">selected</c:if> >${rxnd}</option>
                </c:forEach>

                <option value=""
                <%--<c:if test="${gukeer:emptyString(gradeClass.rxnd) && gukeer:notEmptyString(gradeClass)}"> selected </c:if>--%>
                <%--<c:if test="${gukeer:emptyString(gradeClass)}"> hidden </c:if> > --%>
                <c:choose>
                        <c:when test="${gukeer:emptyString(gradeClass.rxnd) && gukeer:notEmptyString(gradeClass)}">
                            selected
                        </c:when>
                <c:otherwise>
                        hidden
                </c:otherwise>
                </c:choose>>
                </option>
            </select>
        </div>

        <div class="row">
            <label>学段：</label>
            <select name="xd" id="xd">
                <option value="0">请选择学段</option>
                <c:forEach items="${xdList}" var="xd">
                    <option value="${xd.id}" <c:if test="${focusXd == xd.id}">selected</c:if>>${xd.name}</option>
                </c:forEach>
            </select><b>*</b>
        </div>
        <div class="row">
            <label>年级：</label>
            <select name="nj" id="nj">
                <option value="0">请选择年级</option>
                <c:if test="${sectionYear!=0}">
                    <c:forEach items="${njList}" var="nj" varStatus="s">
                        <c:if test="${s.index<sectionYear}">
                            <option value="${nj.key}"
                                    <c:if test="${focusNj == nj.key}">selected</c:if>>${nj.value}</option>
                        </c:if>
                    </c:forEach>
                </c:if>
            </select><b>*</b>
        </div>
    </div>
</form>
<script>
    !function () {
        laydate.skin('molv');
        laydate({elem: '#rxnd'})
    }();
    zNodes1 =
            [
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

                </c:forEach>
                </c:forEach>
                </c:forEach>

            ];
    $("#xd").change(function () {
        $("#nj").empty();
        $("#nj").append("<option value='-1'>请选择年级</option>");


        for (var i = 0; i < zNodes1.length; i++) {
            var node = zNodes1[i];
            var xdstart = node.pId.indexOf("section_");
            var xqstart = node.pId.indexOf("xq_");
            if (xdstart >= 0 && xqstart < 0)
                if (node.pId.substring(xdstart + "section_".length) == $("#xd").val()) {
                    for (var j = 0; j < zNodes1.length; j++) {
                        var node2 = zNodes1[j];
                        if (node2.pId == node.id) {
                            var njstart = node2.id.indexOf("nianji") + "nianji".length;
                            var key = node2.id.substring(njstart);
                            html = "<option value='" + key + "'  >" + rp(key) + "年级" + "</option>";
                            $("#nj").append(html);
                        }
                    }
                    break;
                }
        }
    });

    var cnum = ['零', '一', '二', '三', '四', '五', '六', '七', '八', '九'];
    function rp(n) {           //数字转换汉字
        var s = '';
        n = '' + n; // 数字转为字符串
        for (var i = 0; i < n.length; i++) {
            s += cnum[parseInt(n.charAt(i))];
        }
        return s;
    }
</script>
</body>
</html>