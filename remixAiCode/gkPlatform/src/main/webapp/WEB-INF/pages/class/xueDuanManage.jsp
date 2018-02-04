<%@ include file="../common/headerXf.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>学段管理</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <link rel="stylesheet" href="${ctxStaticNew}/css/schoolRollManage.min.css"/>
</head>
<body>

<%@ include file="../common/sonHead/xuejiHead.jsp" %>

<div class="row" id="phase-manage">
    <main class="container">
        <div class="row">
            <button onclick="openDialog('新增','${ctx}/class/xueDuan/edit?id=','450px','400px');">新增</button>
        </div>
        <div class="row">
            <table>
                <style>
                    table th:nth-child(2),table td:nth-child(2){text-align:left !important;}
                </style>
                <thead>
                <tr>
                    <th width="5%">序号</th>
                    <th width="10%">学段名称</th>
                    <th width="8%">学段简称</th>
                    <th width="8%">入学年龄</th>
                    <th width="8%">学制</th>
                    <th width="15%">状态</th>
                    <th width="20%">备注</th>
                    <th width="15%">操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${pageInfo.list}" var="classSection" varStatus="status">
                    <tr>
                        <td>
                                ${status.index+1}
                        </td>
                        <td>
                                ${classSection.name}
                        </td>
                        <td>
                                ${classSection.shortName}
                        </td>
                        <td>
                            <c:if test="${classSection.limitAge!=0}">${classSection.limitAge}</c:if>
                        </td>
                        <td>
                                ${classSection.sectionYear}
                        </td>
                        <td>
                            <c:if test="${classSection.delFlag==0}">
                                已启用
                            </c:if>
                            <c:if test="${classSection.delFlag==1}">
                                已停用
                            </c:if>
                        </td>
                        <td>
                                ${classSection.remarks}
                        </td>
                        <td>
                <span class="edit"
                      onclick="openDialog('编辑','${ctx}/class/xueDuan/edit?id=${classSection.id}','450px','400px');">编辑</span>


                            <c:choose>
                                <c:when test="${classSection.name != '小学' && classSection.name != '初中' && classSection.name != '高中'}">
                                    <a onclick="alertTips('400px','222px','删除学段','确定要删除${classSection.name}吗？删除前请确定该学段下无班级','xueduanDelete(\'${classSection.id}\')')"><span
                                            class="delete">删除</span> </a>
                                </c:when>
                                <c:otherwise>
                                    <c:if test="${classSection.delFlag==0}">
                                        <a onclick="alertTips('400px','222px','停用学段','确定要停用${classSection.name}吗？停用前请确定该学段下无班级','xueduanDelete(\'${classSection.id}\')')"><span
                                                class="stop-use">停用</span> </a>
                                    </c:if>
                                    <c:if test="${classSection.delFlag==1}">
                                        <a onclick="alertTips('400px','222px','启用学段','确定要启用${classSection.name}吗？','xueduanDelete(\'${classSection.id}\')')"><span
                                                class="start-use">启用</span> </a>
                                    </c:if>
                                </c:otherwise>
                            </c:choose>


                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </main>
</div>
<script>
    activeMenu("sectionManMenu",0);

    function xueduanDelete(id) {
        closeAlertDiv();

        $.post("${ctx}/class/xueduan/del", {
            id: id
        }, function (retJson) {
            layer.msg(retJson.msg);
        }, "json");

        setTimeout(function () {
            window.location.reload();
        }, 1000);//删除

    }
</script>
</body>
</html>