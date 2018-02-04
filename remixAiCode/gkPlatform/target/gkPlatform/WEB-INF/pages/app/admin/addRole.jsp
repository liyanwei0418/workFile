<%@ include file="../../common/common.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <META http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="Content-Language" content="en"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>角色分配</title>
    <link href="${ctxStatic}/css/pageDivide.css" rel="stylesheet" type="text/css"/>
    <link href="${ctxStatic}/css/style.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${ctxStatic}/js/jquery-1.7.2.js"></script>
    <script type="text/javascript" src="${ctxStatic}/js/main.js"></script>
    <script type="text/javascript" src="${ctxStatic}/js/pageDevide.js"></script>
    <script type="text/javascript" src="${ctxStatic}/js/layer/layer.js"></script>
    <script type="text/javascript" src="${ctxStatic}/js/openDialog.js"></script>
    <style>
        .app-ul {
            text-align: center;
            height: 70px;
            text-decoration-line: none;
        }

        .app-li {
            display: inline-block;
            width: 33%;
            float: left;
            border-radius: 50%;
        }
    </style>
    <script type="text/javascript">
        function doSubmit() {//回调函数，在编辑和保存动作时，供openDialog调用提交表单。
            return true;
        }
    </script>
</head>
<body>
<div class="addDiv">
    <span>已分配角色</span>
    <ul class="app-ul">
        <c:forEach items="${roleList}" var="role" varStatus="num">
            <li class="app-li">${role.name}</li>
        </c:forEach>
    </ul>
</div>
<div class="delDiv">
    <span>未分配角色</span>
    <table>
        <tr>
            <td>角色名称</td>
            <td>操作</td>
        </tr>
        <c:forEach items="${allRoleList}" var="all">
            <tr>
                <td>${all.name}</td>
                <td>
                    <input type="button" value="添加" onclick="addRoleToApp(this,'${all.id}','${all.name}')">
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
<script>
    function addRoleToApp(btn, id,name) {
        var _id = id;
        var _name = name;
        $.ajax({
            url: '${ctx}/app/approle/add/save',
            type: 'POST',
            data: {roleId: _id, appId:${appId}},
            async: true,
            dataType: "json",
            success: function (data) {
                if (!data) {
                    alertTips('300px', '150px', '提示', '分配角色失败,请刷新后重试!');
                    return;
                }
                var hadLi = "<li class='app-li'>"+_name+"</li>";
                $(".app-ul").append($(hadLi));
                $(btn).parent("td").parent("tr").remove();
            },
            error: function () {
                alert("请求失败");
            }
        });
    }
</script>
</html>

