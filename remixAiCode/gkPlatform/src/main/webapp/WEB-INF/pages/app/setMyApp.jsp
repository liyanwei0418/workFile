<%@ include file="../common/common.jsp" %>
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
    <script type="text/javascript" src="${ctxStatic}/js/layer/layer.js"></script>
    <script type="text/javascript" src="${ctxStatic}/js/openDialog.js"></script>
    <style>
        .container{font-size:14px;width:660px;margin:0 auto;padding:30px 15px 15px 15px;}
        .container>div>ul{padding:20px 0;overflow:hidden;list-style:none;}
        .container>div>ul li{position:relative;width:156px;height:70px;float:left;padding:15px;margin:0 15px;margin-bottom:20px;box-shadow: 0 5px 15px #aaaaaa;}
        .container>div>ul li img{float:left;vertical-align: top;align:absbottom}
        .container>div>ul li div{float:right;padding:0 10px;}
        .container>div>p{padding:5px 15px;font-size:16px;color:#777;font-weight:bold;border-left:3px solid #1ab39c;}
        .container>div>ul li div b{display:inline-block;width:72px;height:12px;background:url(${ctxStatic}/image/star.png) no-repeat center center;}
        .container>div>ul li div p{margin:5px 0;font-size: 12px;color:#999;}
        .container>div>ul li div h4{margin:5px 0;}
        ul.add li i{cursor:pointer;background:url(${ctxStatic}/image/add.png) no-repeat center center;display:inline-block;width:20px;height:20px;position:absolute;top:-6px;right:-6px;}
        ul.remove li i{cursor:pointer;background:url(${ctxStatic}/image/remove.png) no-repeat center center;display:inline-block;width:20px;height:20px;position:absolute;top:-6px;right:-6px;}
    </style>
</head>
<body>
<div class="container">
    <div>
        <p>已添加</p>
        <ul>
            <c:forEach items="${defaultAppList}" var="defaultApp">
                <li>
                    <img src="${ctx}/${defaultApp.iconUrl}"/>
                    <div>
                        <h4>${defaultApp.name}</h4>
                        <p>办公管理</p>
                        <b></b>
                    </div>
                    <i></i>
                </li>
            </c:forEach>
        </ul>
        <ul class="remove">
            <c:forEach items="${myAppList}" var="myApp">
                <li>
                    <img src="${ctx}/${myApp.iconUrl}"/>
                    <div>
                        <h4>${myApp.name}</h4>
                        <p>办公管理</p>
                        <b></b>
                    </div>
                    <i class="rightTopIcoDel" flag="${myApp.id}"></i>
                </li>
            </c:forEach>
        </ul>
    </div>
    <div>
        <p>未添加</p>
        <ul class="add">
            <c:forEach items="${otherAppList}" var="otherApp">
            <li>
                <img src="${ctx}/${otherApp.iconUrl}"/>
                <div>
                    <h4>${otherApp.name}</h4>
                    <p>互动成长</p>
                    <b></b>
                </div>
                <i class="rightTopIcoAdd" flag="${otherApp.id}"></i>
            </li>
            </c:forEach>
        </ul>
    </div>

 </div>

</body>
<script>

    $(function() {
        $(".rightTopIcoDel").click(function () {
            var id = $(".rightTopIcoDel").attr("flag");
            delMyApp(this,id);
            setTimeout(function(){window.location.reload();}, 100);
        });
        $(".rightTopIcoAdd").click(function () {
            var id = $(".rightTopIcoAdd").attr("flag");
            saveMyApp(this,id);
            setTimeout(function(){window.location.reload();}, 100);
        })


    })
    function saveMyApp(btn, id) {
                var _id = id;
                $.ajax({
                url: '${ctx}/app/savemyapp',
                type: 'POST',
                data: {appId: _id},
                async: true,
                dataType: "json",
                success: function (data) {
                if (!data) {
                alertTips('300px', '150px', '提示', '应用失败,请刷新后重试!');
                return;
                }
                    /*var append =
                $(".app-ul").append($(hadLi));*/
                $(btn).parents("li").remove();

                },
                error: function () {
                alert("请求失败");
                }
                });
    }
    function delMyApp(btn,id){
                var _id = id;
                $.ajax({
                url: '${ctx}/app/delmyapp',
                type: 'POST',
                data: {appId: _id},
                async: true,
                dataType: "json",
                success: function (data) {
                if (!data) {
                alertTips('300px', '150px', '提示', '应用失败,请刷新后重试!');
                return;
                }
                /*var append =
                $(".app-ul").append($(hadLi));*/
                $(btn).parents("li").remove();

                },
                error: function () {
                alert("请求失败");
                }
                });
    }
</script>
</html>