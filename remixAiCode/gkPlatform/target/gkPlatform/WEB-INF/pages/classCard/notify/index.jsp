<%@ include file="../../common/headerXf.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>通知管理</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <link rel="stylesheet" href="${ctxStaticNew}/css/classCard.min.css"/>
</head>
<body>
<style>
    main .col-xs-9{
        width: 100% !important;
        padding-left: 15px !important;
        border: none;
    }
    .nav-menu li a:hover{
        color:#fff !important;
    }
    .layui-layer-iframe{
        min-width: 825px !important;
    }
    main.col-xs-9 .search-box .roll-operation button.roll-delete{
        background: #fc4c71;
    }

</style>

<%@ include file="../../common/sonHead/classCardHead.jsp" %>

<main class="container">
    <!--班牌管理-->
    <div class="row" id="stu-manage">
        <main class="col-xs-9">
            <div class="search-box">
                <div class="roll-operation">
                    <select class="notifyType">
                        <shiro:hasAnyRoles name="classCardAdmin,schoolAdmin">
                            <option value="-1" <c:if test="${type == -1 || type==''}">selected</c:if>>所有通知</option>
                            <option value="2" <c:if test="${type == 2}">selected</c:if>>校园通知</option>
                        </shiro:hasAnyRoles>
                        <option value="1" <c:if test="${type == 1}">selected</c:if>>班级通知</option>

                    </select>
                    <button class="roll-add"
                            onclick="openDialogWithClosingDelete('新增','${ctx}/classcard/notify/edit','50%','70%');">新增
                    </button>
                    <button class="roll-delete" onclick="ifChoose()">删除</button>
                </div>
                <div class="roll-research">
                    <button type="button" class="summitButton" onclick="subForm()"></button>
                    <input type="text" placeholder="请输入标题" name="" value="${title}" class="searchInput"/>
                </div>
            </div>
            <div>
                <table>
                    <thead>
                    <tr>
                        <th width="3%"><input type="checkbox" id="choseAll"/></th>
                        <th width="3%">序号</th>
                        <th width="30%">标题</th>
                        <th width="10%">发布时间</th>
                        <th width="6%">发布人</th>
                        <th width="10%">操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${pageInfo.list}" var="notifyView" varStatus="status">
                        <tr>
                            <td>
                                <input name="chose" class="chose" type="checkbox" value="${notifyView.id}"/>
                            </td>
                            <td>
                                    ${status.index + 1 +(pageInfo.pageNum-1)*10}
                            </td>
                            <td>
                                    ${notifyView.title}
                            </td>
                            <td>
                                    ${notifyView.publishTime}
                            </td>
                            <td>
                                    ${notifyView.creatorName}
                            </td>
                            <td>
                    <span class="checksee"
                          onclick="openDialogView('查看','${ctx}/classcard/notify/edit?disabled=disabled&id=${notifyView.id}','50%','70%');">查看</span>
                                <span class="td-delete" onclick="alertTips('400px','200px','删除通知','确定要删除该通知吗？','notifyDelete(\'${notifyView.id}\')')"> 删除 </span>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <div class="fenye">
                <c:if test="${gukeer:notEmptyString(pageInfo.pages)}">
                    <div class="fenyDetail">共${pageInfo.total}条记录，本页${pageInfo.size}条</div>
                </c:if>
                <div class="fenY" id="fenY">
                    <%--<input type="text"/>--%>
                </div>
            </div>
        </main>
    </div>

</main>

<%--<input type="hidden" name="ueditorPics" value="">--%>
<form id="submit-form" method="get" action="${ctx}/classcard/notify/index">
<input type="hidden" name="pageNum" value="${pageInfo.pageNum}">
<input type="hidden" name="title" value="${title}">
<input type="hidden" name="type" value="${type}">
</form>

<script>
    activeMenu("all",1);

    function subForm() {
        var title = $(".searchInput").val();
        var type= $('.notifyType').val();
        //encodeURI(encodeURI(title))
        $("input[name='title']").val(encodeURI(title));
        $("input[name='type']").val(type);
        $("form").submit();
    };

    <c:if test="${pageInfo.pages != 0}">
    $(".fenY").createPage({
        pageCount:${pageInfo.pages},//总页数
        current:${pageInfo.pageNum},//当前页面
        backFn: function (p) {
            $("input[name='pageNum']").val(p);
            subForm();
        }
    });
    $('.gotoPage').click(function () {
        var p = $('.go').val();
        if (p <= 0 || p >${pageInfo.pages}) {
            layer.msg("请输入正确的页码")
        } else {
            $("input[name='pageNum']").val(p);
            subForm();
        }

    })

    </c:if>


    $(".notifyType").change(function () {
        $("input[name='type']").val($(".notifyType").val());
        var title = $(".searchInput").val();
        $("input[name='title']").val(title);
        subForm();
    });

    function notifyDelete(id) {
        closeAlertDiv();
        $.post("${ctx}/classcard/notify/multidelete", {
            notifyId: id+","
        }, function (retJson) {
            if (retJson.code == 0)
                layer.msg('删除成功');
            else layer.msg("删除失败");
        }, "json");

        setTimeout(function () {
            window.location.reload();
        }, 1000);//删除

    }

    function ifChoose() {
        if ($('input:checkbox[name=chose]:checked').length > 0) {
            alertTips('400px', '200px', '删除通知', '确定要删除选中的' + $('input:checkbox[name=chose]:checked').length + '个通知吗？', 'choose()')
        }
        else {
            layer.msg("请选择通知")
        }
    }
    function choose() {
        closeAlertDiv();
        var spCodesTemp = "";
        $('input:checkbox[name=chose]:checked').each(function (i) {
            if (0 == i) {
                spCodesTemp = $(this).val();
            } else {
                spCodesTemp += ("," + $(this).val());
            }
        });
        $.post("${ctx}/classcard/notify/multidelete", {
            notifyId: spCodesTemp
        }, function (retJson) {
            if (retJson.code == 0)
                layer.msg('删除成功');
            else layer.msg("删除失败");
        }, "json");

        setTimeout(function () {
            window.location.reload();
        }, 1000);//删除
    }


    var allBtn = $('#choseAll');
    var normalBtn = $('.chose');
    //    console.log(allBtn.checked);
    $(allBtn).click(function () {
        if (this.checked == true) {
            for (var i = 0; i < normalBtn.length; i++) {
                normalBtn[i].checked = true;
            }
        } else {
            for (var i = 0; i < normalBtn.length; i++) {
                normalBtn[i].checked = false;
            }
        }
    })

   /* function setUeditorPics(ueditorPics){
        $("input[name='ueditorPics']")
    }*/

</script>
</body>
</html>

