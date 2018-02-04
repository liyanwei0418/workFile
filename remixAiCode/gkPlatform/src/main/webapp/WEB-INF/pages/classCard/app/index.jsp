<%@ include file="../../common/headerXf.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>模块管理</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <link rel="stylesheet" href="${ctxStaticNew}/css/classCard.min.css"/>
</head>
<body>
<style>
    main .col-xs-9 {
        width: 100% !important;
        padding-left: 15px !important;
        border: none;
    }

    .nav-menu li a:hover {
        color: #fff !important;
    }

    /*.layui-layer-iframe {*/
        /*min-width: 825px !important;*/
    /*}*/

    main.col-xs-9 .search-box .roll-operation button.roll-delete {
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
                    <%-- <select class="notifyType">
                         <shiro:hasAnyRoles name="classCardAdmin,schoolAdmin">
                             <option value="-1" <c:if test="${type == -1 || type==''}">selected</c:if>>所有通知</option>
                             <option value="2" <c:if test="${type == 2}">selected</c:if>>校园通知</option>
                         </shiro:hasAnyRoles>
                         <option value="1" <c:if test="${type == 1}">selected</c:if>>班级通知</option>

                     </select>--%>
                    <button class="roll-add"
                            onclick="openDialog('新增','${ctx}/model/edit','40%','40%');">新增
                    </button>
                    <button class="roll-delete" onclick="ifChoose()">删除</button>
                    <button class="roll-add" onclick="ifBatchChoose()">批量分配</button>
                </div>
                <div class="roll-research">
                    <button type="button" class="summitButton" onclick="subForm()"></button>
                    <input type="text" placeholder="请输入模块名称" name="" value="${appName}" class="searchInput"/>
                </div>
            </div>
            <div>
                <table>
                    <thead>
                    <tr>
                        <th width="3%"><input type="checkbox" id="choseAll"/></th>
                        <th width="3%">序号</th>
                        <th width="10%">模块名称</th>
                        <th width="10%">上传时间</th>
                        <th width="6%">上传人员</th>
                        <th width="10%">操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${pageInfo.list}" var="appView" varStatus="status">
                        <tr>
                            <td>
                                <input name="chose" class="chose" type="checkbox" value="${appView.id}"/>
                            </td>
                            <td>
                                    ${status.index + 1 +(pageInfo.pageNum-1)*10}
                            </td>
                            <td>
                                    ${appView.appName}
                            </td>
                            <td>
                                    ${appView.uploadTime}
                            </td>
                            <td>
                                    ${appView.uploadPersion}
                            </td>
                            <td>
                    <span class="checksee"
                          onclick="openDialog('编辑','${ctx}/model/edit?appId=${appView.id}','40%','40%');">编辑</span>
                                <span class="td-delete"
                                      onclick="alertTips('400px','200px','删除模块','确定要删除该模块吗？','notifyDelete(\'${appView.id}\')')"> 删除 </span>
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
<form id="submit-form" method="get" action="${ctx}/model/index">
    <input type="hidden" name="pageNum" value="${pageInfo.pageNum}">
    <input type="hidden" name="appName" value="${appName}">
</form>

<script>

    activeMenu("base", 3);

    function subForm() {
        var appName = $(".searchInput").val();
        $("input[name='appName']").val(encodeURI(appName));
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

    });

    </c:if>


    function notifyDelete(id) {
        closeAlertDiv();
        $.post("${ctx}/model/multidelete", {
            appIds: id + ","
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
            layer.msg("请选择模块")
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
        $.post("${ctx}/model/multidelete", {
            appIds: spCodesTemp
        }, function (retJson) {
            if (retJson.code == 0)
                layer.msg('删除成功');
            else layer.msg("删除失败");
        }, "json");

        setTimeout(function () {
            window.location.reload();
        }, 1000);//删除
    }
    function ifBatchChoose() {
        if ($('input:checkbox[name=chose]:checked').length > 0) {
            //alertTips('400px', '200px', '删除通知', '确定要删除选中的' + $('input:checkbox[name=chose]:checked').length + '个通知吗？', 'choose()')
            var tmpAppIds = "";
            $('input:checkbox[name=chose]:checked').each(function (i) {
                if (0 == i) {
                    tmpAppIds = $(this).val();
                } else {
                    tmpAppIds += ("," + $(this).val());
                }
            });
            openDialog('编辑','${ctx}/model/chooseclasscard?appIds='+tmpAppIds,'50%','70%');
        }
        else {
            layer.msg("请选择模块")
        }
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

