<%@ include file="../../common/common.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <META http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="Content-Language" content="en"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>应用管理</title>
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

        .app-li img {
            border-radius: 50%;
            width: 70px;
            height: 70px;
            cursor: pointer;
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
    <span>已分配应用</span>
    <ul class="app-ul">
        <c:forEach items="${appList}" var="app">
            <li class="app-li" onclick="minus(this,'${app.id}','${ctx}/${app.iconUrl}','${app.name}')">
                <img class="app-minus" onmouseenter="minusImage(this)" onmouseleave="leave(this,'${ctx}/${app.iconUrl}')" src="${ctx}/${app.iconUrl}" alt="${app.name}"></li>
        </c:forEach>
    </ul>
</div>
<div class="delDiv">
    <span>未分配应用</span>
    <ul class="app-ul">
        <c:forEach items="${allList}" var="all">
            <li class="app-li"><img class="app-plus" src="${ctx}/${all.iconUrl}" alt="${all.name}"></li>
        </c:forEach>
    </ul>
</div>
</body>
<script>
    function minusImage(_img) {
        $(_img).css("border", "1px solid red");
        $(_img).attr("src", "${ctxStatic}/image/minus.png");
    }
    function leave(_img, url) {
        var _url = url;
        $(_img).css("border", "none");
        $(_img).attr("src", _url);
    }
    function minus(li, id, url, name) {
        var _id = id;
        var _url = url;
        var _name = name;
        $.ajax({
            url: '${ctx}/app/delete/save',
            type: 'POST',
            data: {id: _id, sid:${sid}},
            async: true,
            dataType: "json",
            success: function (data) {
                if (!data) {
                    alertTips('300px', '150px', '提示', '删除应用失败,请刷新后重试!');
                    return;
                }
                var list = "<li class='app-li'><img class='app-plus' src='" + _url + "' alt='" + _name + "'></li>"
                $(".delDiv ul").append($(list));
                $(li).remove();
            },
            error: function () {
                alert("请求失败");
            }
        });
    }
</script>
</html>

