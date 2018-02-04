<%@ page import="cn.gukeer.platform.common.ConstantUtil" %>
<%@ include file="../../../common/headerXf.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>自定义界面</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <link rel="stylesheet" href="${ctxStaticNew}/css/classCard.min.css"/>
    <link type="text/css" rel="stylesheet" href="${ctxStaticNew}/css/jeDate-test.css">
    <link type="text/css" rel="stylesheet" href="${ctxStaticNew}/css/jedate.css">
    <script type="text/javascript" src="${ctxStaticNew}/js/jquery.jedate.js"></script>
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

<%@ include file="../../../common/sonHead/classCardHead.jsp" %>

<main class="container">
    <!--班牌自定义显示界面-->
    <div class="row" id="stu-manage">
        <main class="col-xs-9">
            <div class="search-box">
                <div class="roll-operation">
                    <button class="roll-add" onclick="addTemplate()">新增</button>
                    <button class="roll-delete" onclick="ifChoose()">删除</button>
                </div>
            </div>
            <div>
                <table>
                    <thead>
                    <tr>
                        <th width="3%"><input type="checkbox" id="choseAll"/></th>
                        <th width="5%">序号</th>
                        <th width="15%">名称</th>
                        <th width="15%">描述</th>
                        <th width="10%">共享</th>
                        <th width="10%">状态</th>
                        <th width="20%">操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${pageInfo.list}" var="templateView" varStatus="status">
                        <tr>
                            <td>
                                <input name="chose" class="chose" type="checkbox" value="${templateView.id}"/>
                            </td>
                            <td>
                                    ${status.index + 1 +(pageInfo.pageNum-1)*10}
                            </td>
                            <td>
                                    ${templateView.name}
                            </td>
                            <td>
                                    ${templateView.detailed}
                            </td>
                            <td>
                                    ${templateView.shareFlag}
                            </td>
                            <td>
                                <c:if test="${templateView.status == 0}">
                                    编辑中
                                </c:if>
                                <c:if test="${templateView.status == 1}">
                                    发布中
                                </c:if>
                            </td>
                            <td>
                                <c:if test="${templateView.status == 0}">
                                    <%--editPage('${templateView.id}','')//templateDelete('${templateView.id}')//publishPage('${templateView.id}')--%>
                                    <span class="td-edit" onclick="">编辑</span>
                                    <span class="td-delete" style="padding-left: 20px;cursor: pointer;margin-right: 20px" onclick="alertTips('400px','200px','删除界面','确定删除该界面吗？','')">删除</span>
                                    <span class="" onclick="">发布</span>
                                </c:if>
                                <c:if test="${templateView.status == 1}">
                                    <%--editPage('${templateView.id}','disabled')//cancelRelease('${templateView.id}')--%>
                                    <span class="checksee" onclick="">查看</span>
                                    <span class="" onclick="alertTips('400px','200px','取消发布','确定取消该界面的发布吗？','')">取消发布</span>
                                </c:if>
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
                </div>
            </div>
        </main>
    </div>
</main>

<form id="submit-form" method="get" action="${ctx}/classcard/custom/template/index">
    <%--<input type="hidden" name="ueditorPics" value="">
    分页查询的其他字段
    条件查询的其他字段
    ajax提交--%>
    <input type="hidden" name="pageNum" value="${pageInfo.pageNum}">
</form>

<script>
    activeMenu("base",4);

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

    function templateDelete(id) {
        closeAlertDiv();
        $.post("${ctx}/classcard/custom/template/multidelete", {
            pageId: id+","
        }, function (retJson) {
            if (retJson.code == 0)
                layer.msg('删除成功');
            else layer.msg("删除失败");
        }, "json");

        setTimeout(function () {
            window.location.reload();
        }, 700);//删除

    }

    function ifChoose() {
        if ($('input:checkbox[name=chose]:checked').length > 0) {
            alertTips('400px', '200px', '删除界面', '确定要删除选中的' + $('input:checkbox[name=chose]:checked').length + '个界面吗？', 'choose()')
        }
        else {
            layer.msg("请选择界面")
        }
    }
    function choose() {
        closeAlertDiv();
        var spCodesTemp = "";
        $('input:checkbox[name=chose]:checked').each(function (i) {
            spCodesTemp += ($(this).val() + ",");
        });
        $.post("${ctx}/classcard/custom/template/multidelete", {
            pageId: spCodesTemp
        }, function (retJson) {
            if (retJson.code == 0)
                layer.msg('删除成功');
            else layer.msg("删除失败");
        }, "json");

        setTimeout(function () {
            window.location.reload();
        }, 1000);//删除
    }

    function addCustom(){
        //window.location.href = "${ctx}/classcard/custom/createPageByTemplate";
        //openDialogWithoutReload('选择模板','${ctx}/classcard/custom/selectTemplate','50%','70%',false);
    }

    function editPage(pageId,option) {
        //window.location.href = "${ctx}/classcard/custom/template/edit?pageId="+pageId+"&option="+option;
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

