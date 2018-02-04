<%@ include file="../common/common.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="en">
<head lang="en">
    <meta charset="UTF-8">
    <title></title>
    <style>
        .container {
            width: 400px;
            margin: 0 auto;
            padding-top: 16px;
            font: 13px '微软雅黑';
        }

        ul {
            margin: 0;
            padding: 0;
            list-style: none;
        }

        li {
            margin: 15px 0;
            padding-left: 30px;
        }

        b {
            font-size: 20px;
            color: #E51C23;
            position: absolute;
            right:110px;
           line-height: 32px;
        }

        span {
            display: inline-block;
            width: 20%;
            text-align: right;
            color: #666;
        }

        input {
            width: 50%;
            height: 28px;
            padding: 0 ;
            padding-left:5px;
            border:1px solid #a9a9a9;
            border-radius: 2px;
            outline:none;
        }

        select {
            width: 53%;
            height: 30px;
            border-radius: 2px;
            color: #333;
        }

        textarea {
            vertical-align: -450%;
        }
    </style>
    <script src="${ctxStaticNew}/js/jquery.min.js"></script>
    <script  src="${ctxStaticNew}/js/layer/layer.js"></script>
    <script src="${ctxStaticNew}/js/openDialog.js"></script>
    <script>
        function doSubmit() {//回调函数，在编辑和保存动作时，供openDialog调用提交表单。

            var name = $("input[name='name']").val();
            var limitAge = $("input[name='limitAge']").val();
            var sectionYear = $("input[name='sectionYear']").val();
            if (name == '' || name.trim().length == 0) {
                layer.msg("名称不能为空！");
                return false;
            }


            if (sectionYear == '' || sectionYear.trim().length == 0) {
                layer.msg("年制不能为空！");
                return false;
            }

            if (sectionYear < 1 || sectionYear > 9) {
                layer.msg("年制超出1~9的范围！");
                return false;
            }

            $.post($("form").attr('action'), {
                id: $("input[name='id']").val(),
                name: name,
                shortName: $("input[name='shortName']").val(),
                limitAge: $("input[name='limitAge']").val(),
                sectionYear: $("input[name='sectionYear']").val(),
                remarks: $("input[name='remarks']").val()
            }, function (retJson) {
                if (retJson.code == '0') {
                    <%--window.location.replace("${ctx}/class/xueDuan/view");--%>
                    layer.msg("保存成功");
                    setTimeout(function () {
                        window.parent.location.reload(true);
                    }, 1000);
                } else {
                    layer.msg(retJson.msg);
                }
            });

        }
    </script>
</head>
<body>
<form action="${ctx}/class/xueDuan/save">
    <input type="hidden" name="id" value="${classSection.id}">
    <div class="container">
        <ul>
            <li>
                <b>*</b><span>学段名称：</span>
                <input type="text" name="name" value="${classSection.name}"
                        <c:if test="${classSection.name == '小学' || classSection.name == '初中' || classSection.name == '高中'}">
                            disabled
                        </c:if>
                />
            </li>
            <li>
                <span>学段简称：</span>
                <input type="text" name="shortName" value="${classSection.shortName}"/>
            </li>
            <li>
                <span>入学年龄：</span>
                <input type="text" name="limitAge"
                       value="<c:if test='${classSection.limitAge!=0}'>${classSection.limitAge}</c:if>"/>
            </li>
            <li>
                <b>*</b><span>学制：</span>
                <input type="text" name="sectionYear" value="${classSection.sectionYear}" placeholder="输入数值请在1~9范围内"/>
            </li>
            <li>
                <span>备注：</span>
                <input type="text" name="remarks" value="${classSection.remarks}"/>
            </li>
        </ul>

    </div>
</form>
</body>
</html>