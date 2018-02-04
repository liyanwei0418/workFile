<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../../common/common.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>教室类型</title>
    <script type="text/javascript" src="${ctxStaticNew}/js/jquery-1.7.2.js"></script>
    <script type="text/javascript" src="${ctxStaticNew}/js/jquery.min.js"></script>
    <script type="text/javascript" src="${ctxStaticNew}/js/layer/layer.js"></script>
    <script type="text/javascript" src="${ctxStaticNew}/js/openDialog.js"></script>
    <script type="text/javascript" src="${ctxStaticNew}/js/pageDevide.js"></script>
    <script src="${ctxStaticNew}/js/alertPopShow.js"></script>
</head>
<style>
    * {
        margin: 0;
        padding: 0;
    }

    .popup-main {
        padding: 0 20px;
    }

    .popup-main p {
        padding: 20px 0 15px 0;
    }

    .popup-main button {
        width: 65px;
        height: 30px;
        line-height: 26px;
        outline: none;
        margin-right: 15px;
        border-radius: 2px;
        cursor: pointer;
        background: #54ab37;
        color: #fff;
        border: 1px solid #54ab37;
    }

    table {
        font-size: 13px !important;
        border-collapse: collapse;
    }

    table tbody {
        height: 240px;
        overflow-y: scroll;
    }

    table td {
        border: 1px solid #dadada;
        padding-left: 12px;
        padding-right: 12px;
    }

    table td:first-child {
        width: 15%;
    }

    table td:last-child {
        width: 45%;
    }

    thead tr {
        height: 35px;
        line-height: 35px;
        background: #e7eaec;
        color: #525252;
    }

    tbody tr {
        height: 35px;
        line-height: 35px;
    }

    .modifyOne, .deleone, .saveOne, .modifyOneagain{
        cursor: pointer;
    }

    .modifyOne, .saveOne , .modifyOneagain{
        color: #54ab37;
        margin-right: 25px;
    }

    .deleOne {
        color: #ff0000;
    }

    .alertDivHeader {
        height: 42px;
        line-height: 42px;
        padding-left: 20px;
        font-size: 13px;
        color: #525252;
        background-color: #F8F8F8;
    }
    .alertDivContent{
        padding: 30px 0 0 20px;
        font-size: 13px;
    }
    .alertButtons{
        padding: 10px ;
        text-align: right;
        overflow:hidden;
    }
    .alertButtons>input[type="button"]{
        float: right;
        margin:0 5px;
        margin-top: 10%;
        text-align: center;
        height: 28px;
        width: 60px;
        border-radius:2px;
        outline: none;
        border: none;
        border-color: #4898d5;
        background-color: #2e8ded;
        color: #FFFFFF;
    }
    .alertButtons>input[type="button"]:first-child{background: #f1f1f1;color:#333;border:1px solid #dedede;}
</style>
<body>
<main class="container" id="zh-manage">
    <div class="stu-num-manage-menu">
    </div>
    <section id="generated" class="row">
        <div class="popup-main">
            <p>
                <button class="addnew">新增</button>
            </p>
            <input type="hidden" class="add">
            <table cellspacing="0" cellpadding="0" width="100%" class="tttable">
                <thead>
                <tr>
                    <td width="5%">序号</td>
                    <td width="40%">教室类型</td>
                    <td>操作</td>
                </tr>
                </thead>
                <tbody id="roomTypetbody">
                <c:forEach items="${roomTypeList}" var="roomType" varStatus="status">
                    <input type="hidden" value="${cycleId}" name="id" value="${roomType.id}">
                    <tr>
                        <td>${status.count}</td>
                        <td class="categoryTd${status.count} span-p">
                            <input class="" name="name" style="outline: none;height: 30px;"/>
                            <span class="showInfo hs">${roomType.name}</span>
                        </td>
                        <td>
                                <span data-url="${ctx}/teach/task/room/type/add?id=${roomType.id}"
                                      class="modifyOne"
                                    <%--onclick="update('${status.index+1+(pageInfo.pageNum-1)*10}','${type.id}','${type.name}')"--%>>修改</span>
                            <span class="deleOne"
                                  onclick="alertTips('400px','200px','删除教室类型','确定要删除${roomType.name}吗？','deleteSure(\'${roomType.id}\')')"> 删除</span>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </section>
</main>
</body>
<script>

    $("#roomTypetbody input").hide();

    //    增加
    $('.addnew').on('click', function () {
        if ($('input[name="saveOne"]').length > 0) {
            layer.msg("请保存上一条数据后再添加下一条数据");
            return;
        }
        $('.span-p span').removeClass('hs');
        $('.tttable').append('<tr><td></td><td class="span-p"><input type="text" style="outline: none;height: 30px;" class="focuss" name="saveOne"><span class="showInfo hs"></span></td><td><span class="saveOne">保存</span>&nbsp;<span class="deleOne"onclick="alertTips("400px","200px","删除教室类型","确定要删除${roomType.name}吗？","deleteSure(\'${roomType.id}\')")"> 删除</span></td></tr>');
        $('.hs').hide();
        changeIndex();

    });

    //    保存
    $('#roomTypetbody').on('click', '.saveOne', function () {

        $(this).parents('tr').children().eq(1).children('span').show().text($(this).parents('tr').children().eq(1).children('input').val());
        console.log($(this));
        console.log($(this).parent().prev().children('input').val());
        var oneRoomType = $(this).parent().prev().children('input').val();
        if (oneRoomType == "") {
            layer.msg("请填写教室类型");
            return;
        }
        $(this).parents('tr').children().eq(1).children('input').hide();
        $(".saveOne").removeClass('saveOne').addClass('modifyOne');
        $('input[name="saveOne"]').attr("name","modifyOne");
        $.post("${ctx}/teach/task/room/type/add", {
            name: oneRoomType
        }, function (data) {
            layer.msg("操作成功");
            window.location.reload();
//            window.parent.location.reload();
//            setTimeout(function () {
//                top.layer.close()
//            }, 2000);
        })
//        layer.msg("创建成功");
        $(this).text('修改');
        changeIndex();
    });

    //    删除
//    $('#roomTypetbody').on('click', '.deleOne', function () {
//        $(this).parents('tr').remove();
//        changeIndex();
//    });

    //    编辑
    $('#roomTypetbody').on('click', '.modifyOne', function () {

        $(this).parents('tr').children().eq(1).children('input').show().val($(this).parents('tr').children().eq(1).children('span').text());
        var dataUrl = $(".modifyOne").attr('data-url');
        console.log($(this));
        console.log($(this).parent().prev().children('input').val());
        var oneCourse = $(this).parent().prev().children('input').val();
//                $(this).parents('td').siblings().children('input').val();
        if (oneCourse == "") {
            ("名称不能为空");
            return;
        }
        $(this).parents('tr').children().eq(1).children('span').hide();
        $(this).removeClass('saveOne').addClass('modifyOneagain');
        $(this).text('保存');
        $(this).removeClass('modifyOne');
        changeIndex();
    })

    function update(index, id, name) {
        $(".forUpadateInput" + index).attr("type", "text");
        $(".forUpadateInput" + index).attr("value", name);
        $(".categoryTd" + index).html('');
        console.log($(".editSpan" + index));
    }

    //增加一个
    function saveOne(em) {
        console.log($(em).parent().prev().children('input').val());
        var oneRoomType = $(em).parent().prev().children('input').val();
        $.post("${ctx}/teach/task/room/type/add", {
            name: oneRoomType
        }, function (data) {
        })
        setTimeout("javascript:window.location.reload()",2100);
    }


    $('#roomTypetbody').on('click', '.modifyOneagain', function () {
        var dataUrl = $(".modifyOneagain").attr('data-url');
        var oneCourse = $(this).parent().prev().children('input').val();
        if (oneCourse == "") {
            layer.msg("教室类型不能为空");
            return;
        }
        $.post(dataUrl, {
            name: oneCourse,
        }, function () {
            $(".saveOne").removeClass('saveOne').addClass('modifyOne');
            window.location.reload();
//            window.parent.location.reload();
        });
        layer.msg("操作成功");
        setTimeout("javascript:window.location.reload()",2100);
        $(this).text('修改');
    })

    function changeIndex() {
        $('#roomTypetbody tr').each(function (a, b) {
            var c = a + 1;
            $(b).children().eq(0).text(c);
        });
    }

    function deleteSure(id) {
        closeAlertDiv();
        $.post("${ctx}/teach/task/room/type/add", {
            id: id,
            type: "delete"
        }, function (retJson) {
        });
        setTimeout("javascript:window.location.reload()",2100);
    }
</script>
</html>
