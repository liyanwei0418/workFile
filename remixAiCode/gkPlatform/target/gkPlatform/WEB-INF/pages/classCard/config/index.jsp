<%@ include file="../../common/headerXf.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>班牌配置</title>
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
    <!--班牌配置-->
    <div class="row" id="stu-manage">
        <main class="col-xs-9">
            <div class="search-box">
                <div class="roll-operation">

                    <button class="roll-add" onclick="addConfig()">新增</button>
                    <button class="roll-delete" onclick="ifChoose()">删除</button>
                </div>
                <%--<div class="roll-research">
                    <button type="button" class="summitButton" onclick="subForm()"></button>
                    <input type="text" placeholder="请输入标题" name="" value="${title}" class="searchInput"/>
                </div>--%>
            </div>
            <div>
                <table>
                    <thead>
                    <tr>
                        <th width="3%"><input type="checkbox" id="choseAll"/></th>
                        <th width="5%">序号</th>
                        <th width="20%">名称</th>
                        <th width="20%">适用时间</th>
                        <th width="10%">开机时间</th>
                        <th width="10%">关机时间</th>
                        <th width="10%">操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${pageInfo.list}" var="configView" varStatus="status">
                        <tr>
                            <td>
                                <input name="chose" class="chose" type="checkbox" value="${configView.id}"/>
                            </td>
                            <td>
                                    ${status.index + 1 +(pageInfo.pageNum-1)*10}
                            </td>
                            <td>
                                    ${configView.name}
                            </td>
                            <td>
                                    ${configView.startDateView} --- ${configView.endDateView}
                            </td>
                            <td>
                                    ${configView.startTimeView}
                            </td>
                            <td>
                                    ${configView.endTimeView}
                            </td>
                            <td>
                    <span class="checksee"
                          onclick="openDialogWithoutReload('编辑','${ctx}/classcard/config/edit?option=edit&id=${configView.id}','50%','70%',false);">编辑</span>
                                <span class="td-delete" onclick="alertTips('400px','200px','删除配置','确定要删除该配置吗？','configDelete(\'${configView.id}\')')"> 删除 </span>
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
<form id="submit-form" method="get" action="${ctx}/classcard/config/index">
<input type="hidden" name="pageNum" value="${pageInfo.pageNum}">
</form>

<script>
    activeMenu("base",2);

    function subForm() {
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
    function configDelete(id) {
        closeAlertDiv();
        $.post("${ctx}/classcard/config/multidelete", {
            configId: id
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
            alertTips('400px', '200px', '删除配置', '确定要删除选中的' + $('input:checkbox[name=chose]:checked').length + '个配置吗？', 'choose()')
        }
        else {
            layer.msg("请选择配置")
        }
    }
    function choose() {
        closeAlertDiv();
        var spCodesTemp = "";
        $('input:checkbox[name=chose]:checked').each(function (i) {
            spCodesTemp += $(this).val() + ",";
        });
        $.post("${ctx}/classcard/config/multidelete", {
            configId: spCodesTemp
        }, function (retJson) {
            if (retJson.code == 0)
                layer.msg('删除成功');
            else layer.msg("删除失败");
        }, "json");

        setTimeout(function () {
            window.location.reload();
        }, 1000);//删除
    }

    function addConfig(){
        openDialogWithoutReload('添加配置','${ctx}/classcard/config/edit?option=addconfig','50%','70%',false);
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

</script>
</body>
</html>

