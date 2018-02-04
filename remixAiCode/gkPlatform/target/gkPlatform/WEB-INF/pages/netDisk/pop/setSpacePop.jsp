<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../../common/common.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="${ctxStaticNew}/css/personnel.min.css"/>
    <link rel="stylesheet" type="text/css" href="${ctxStaticNew}/css/jquery.autocomplete.css"/>
    <script type="text/javascript" src="${ctxStaticNew}/js/jquery-1.7.2.js"></script>
    <script type="text/javascript" src="${ctxStaticNew}/js/layer/layer.js"></script>
    <script type="text/javascript" src="${ctxStaticNew}/js/openDialog.js"></script>
    <script type="text/javascript" src="${ctxStaticNew}/js/jquery.autocomplete.js"></script>
</head>
<style>
    * {
        padding: 0;
        margin: 0;
        box-sizing: border-box;
        font-family: "Microsoft YaHei", Arial, STXihei, '华文细黑', 'Microsoft YaHei', SimSun, '宋体', Heiti, '黑体', sans-serif;
    }

    span {
        display: inline-block;
    }

    .m-teacher, .completeTips {
        padding-left: 10px;
    }

    .popup-main {
        background: #fff;
        padding: 35px 0px 10px 25px;
        font-size: 13px !important;
        color: #525252 !important;
    }

    table td {
        font-size: 13px;
        padding: 10px 0;
    }

    table td > span:first-child {
        width: 88px;
        text-align: right;
    }

    table td > span:last-child {
        width: 150px;
        height: 28px;
        line-height: 28px;
        margin-left: 12px;
        padding-left: 10px;
    }

    input[type='text'] {
        width: 150px;
        height: 28px;
        line-height: 28px;
        margin-left: 10px;
        padding-left: 10px;
        outline: none;
        border: 1px solid #dadada;
        border-radius: 2px;
        color: #333;
        vertical-align: middle;

    }

    table td input[type='button'] {
        width: 60px;
        text-align: center;
        height: 28px;
        line-height: 28px;
        margin-left: 10px;
        outline: none;
        border: 1px solid #54ab37;
        border-radius: 2px;
        color: #fff;
        background: #54ab37;
        vertical-align: top;
    }

    .checkbox-containt {
        display: inline-block;
        width: 350px;
        vertical-align: top;
        margin-left: 10px;
    }

    .rows {
        margin-top: 10px;
    }

    #chooseWhoTell > span {
        margin-left: 10px;
        margin-bottom: 10px;
        border: 1px solid #54AB37;
        /*padding: 0 15px;*/
        height: 28px;
        width: 85px;
        text-align: center;
        line-height: 28px;
        display: inline-block;
    }
    #chooseWhoTells > span {
        margin-top: 0;
        display: inline-block;
        width: 88px !important;
        text-align: right;
    }
</style>

<body>
    <input type="hidden" class="total" value="${total}"/>
    <input type="hidden" class="token" value="${token}"/>
    <input type="hidden" class="email" value="${email}"/>
    <table>
        <tr>
            <td><span>用户名称:</span><span>${email}</span></td>
        </tr>
        <tr>
            <td>
                <div class="row" style="display: inline-block;">
                    <input type = 'text'  class="space" placeholder="请填写大于0的整数"/>MB
                </div>
            </td>
        </tr>
    </table>

</body>
<script>

    function doSubmit() { //回调函数，在编辑和保存动作时，供openDialog调用提交表单。
        if (true) {
            var token = $(".token").val();
            var email = $(".email").val();
            var space = $(".space").val();

            $.post("${ctx}/net/disk/set/space", {
                token: token,
                email: email,
                space:space
            }, function (data) {
                setTimeout(function () {
                    parent.location.reload();
                }, 400);
                /*刷新父级页面,延迟保证页面刷新的时候数据已经更新完毕*/
                setTimeout(function () {
                    top.layer.close()
                }, 300);
            })
//            $("#courseEdit").submit();
            return true;
        } else {
            layer.msg("输入有误！");
            return false;
        }
    }
</script>
</html>
