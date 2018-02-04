<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../../common/common.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
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

    .modify, .delete {
        padding-left: 20px;
        cursor: pointer;
    }

    .modify {
        background: url("../../../../assetsNew/images/modify.png") no-repeat left;
        margin-right: 25px;
    }

    .delete {
        background: url("../../../../assetsNew/images/icon-delete.png") no-repeat left;
    }
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
                    <td width="40%">课程类型</td>
                    <td>操作</td>
                </tr>
                </thead>
                <tbody id="tttbody">
                <c:forEach items="${pageInfo.list}" var="type" varStatus="status">
                    <form action="${ctx}/teach/task/course/type/update?type=update&&id=${type.id}" method="post"
                          class="typeUpdateForm">
                        <tr>
                            <td>${status.count}</td>
                            <td class="categoryTd${status.index+1+(pageInfo.pageNum-1)*10} span-p">
                                <input class="" name="name" style="outline: none;height: 30px;"/>
                                <span class="showInfo hs">${type.name}</span>
                            </td>
                            <td>
                                <span data-url="${ctx}/teach/task/course/type/update/one?typeId=${type.id}"
                                      class="modifyOne"
                                    <%--onclick="update('${status.index+1+(pageInfo.pageNum-1)*10}','${type.id}','${type.name}')"--%>>修改</span>
                                <span class="deleOne"
                                      onclick="alertTips('400px','200px','删除课程类型','确定要删除${type.name}课程吗？','deleteSure(\'${type.id}\')')"> 删除</span>
                            </td>
                        </tr>
                    </form>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </section>
</main>
</body>
<script>

    $("#tttbody input").hide();
    //增加
    $('.addnew').on('click', function () {
        $('.span-p span').removeClass('hs');
        $('.tttable').append('<tr><td></td><td class="span-p"><input type="text" style="outline: none;height: 30px;" class="focuss" name="saveOne"><span class="showInfo hs"></span></td><td><span class="saveOne" style="margin-right: 20px;cursor: pointer">保存</span><span class="deleOne" style="cursor: pointer">删除</span></td></tr>');
        $('.hs').hide();
        changeIndex();
    });

    //    保存
    $('#tttbody').on('click', '.saveOne', function () {
        if ($('input[name=saveOne]').length > 1) {
            webToast("请保存上一条数据后再添加下一条数据", "top", 2300);
        }
        var oneCourse = $(this).parent().prev().children('input').val();
        if (oneCourse == ""||oneCourse == null) {
            layer.msg("名称不能为空");
            return false;
        }
        $(this).parents('tr').children().eq(1).children('input').hide();
        $(this).parents('tr').children().eq(1).children('span').show().text($(this).parents('tr').children().eq(1).children('input').val());

        $.post("${ctx}/teach/task/course/type/update/one", {
            oneCourse: oneCourse
        }, function (data) {
            if (data.code == 0) {
                layer.msg("创建成功");
                $(".saveOne").removeClass('saveOne').addClass('modifyOne');
                setTimeout(function () {
                    parent.location.reload();
                }, 5000);
                /*刷新父级页面,延迟保证页面刷新的时候数据已经更新完毕*/
                setTimeout(function () {
                    top.layer.close(index)
                }, 5000);
            } else {
                layer.msg("创建失败");
                setTimeout(function () {
                    parent.location.reload();
                }, 5000);
                /*刷新父级页面,延迟保证页面刷新的时候数据已经更新完毕*/
                setTimeout(function () {
                    top.layer.close()
                }, 5000);
            }
        })

        $(this).text('修改');
        changeIndex();
    });


    //    编辑
    $('#tttbody').on('click', '.modifyOne', function () {
        $(this).parents('tr').children().eq(1).children('span').hide();
        $(this).parents('tr').children().eq(1).children('input').show().val($(this).parents('tr').children().eq(1).children('span').text());
        var dataUrl = $(".modifyOne").attr('data-url');
        console.log($(this));
        console.log($(this).parent().prev().children('input').val());
        var oneCourse = $(this).parent().prev().children('input').val();
//                $(this).parents('td').siblings().children('input').val();
        if (oneCourse == "") {
            webToast("名称不能为空", "top", 2300);
            return;
        }
        $(this).text('修改');
        $(this).removeClass('saveOne').addClass('modifyOneagain');
        $(this).removeClass('modifyOne');
        changeIndex();
    })



    //增加一个
    function saveOne(em) {
        console.log($(em).parent().prev().children('input').val());
        var oneCourse = $(em).parent().prev().children('input').val();
        $.post("${ctx}/teach/task/course/type/add/one", {
            oneCourse: oneCourse
        }, function (data) {

        })
    }


        $('#tttbody').on('click', '.modifyOneagain', function () {
            var dataUrl = $(".modifyOneagain").attr('data-url');
            var oneCourse = $(this).parent().prev().children('input').val();
            $.post(dataUrl, {
                oneCourse: oneCourse,
            }, function (data) {
                if (data.code == 0) {
                    webToast(data.msg, "top", 2300);
                    $(".saveOne").removeClass('saveOne').addClass('modifyOne');
                    setTimeout(function () {
                        parent.location.reload();
                    }, 5000);
                    /*刷新父级页面,延迟保证页面刷新的时候数据已经更新完毕*/
                    setTimeout(function () {
                        top.layer.close(index)
                    }, 5000);
                } else {
                    webToast(data.msg, "top", 5000);
                    setTimeout(function () {
                        parent.location.reload();
                    }, 5000);
                    /*刷新父级页面,延迟保证页面刷新的时候数据已经更新完毕*/
                    setTimeout(function () {
                        top.layer.close()
                    }, 5000);
                }
            })

        })



        function changeIndex() {
            $('#tttbody tr').each(function (a, b) {
                var c = a + 1;
                $(b).children().eq(0).text(c);
            });
        }

        function deleteSure(id) {
            closeAlertDiv();
            $.post("${ctx}/teach/task/course/type/update", {
                id: id,
                type: "delete"
            }, function (retJson) {

            });

            setTimeout(function () {
                layer.msg('删除成功', {
                    time: 2000 //2秒关闭（如果不配置，默认是3秒）
                }, function () {
                    window.location.reload();
                    parent.location.reload();
                });
            }, 300)

        }


</script>
</html>
