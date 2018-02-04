<%@ include file="../common/headerXf.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>平台管理</title>

    <link rel="stylesheet" href="${ctxStaticNew}/css/personnel.min.css"/>
</head>
<style>
    td span {
        color: #54ab37;
    }
    i{
        font-style: normal;
        display: inline-block;
        max-width: 200px;
        overflow: hidden;
        text-overflow: ellipsis;
        white-space: nowrap;
    }
</style>


<body>

<%@ include file="../common/sonHead/schoolHead.jsp" %>
<main class="container" id="ry-manage">
    <div>
        <table class="check">
            <thead>
            <tr>
                <th width="15%">类型</th>
                <%--<th width="10%">创建者</th>--%>
                <th width="15%">创建时间</th>
                <th width="15%">操作IP地址</th>
                <th width="15%">用户代理</th>
                <th width="15%">请求URL</th>
                <th width="10%">方式</th>
                <th width="10%">参数</th>
                <th width="5%">异常</th>
            </tr>
            </thead>
            <tbody>

                <c:forEach items="${pageInfo.list}" var="log">
                    <tr>
                        <td>
                            <c:if test="${log.type == '1'}">接入日志</c:if>
                            <c:if test="${log.type == '2'}">异常日志</c:if>
                        </td>
                        <%--<td>${log.createBy}</td>--%>
                        <td>${gukeer:millsToyyyyMMddHHmmss(log.createDate)}</td>
                        <td>${log.remoteAddr}</td>
                        <td><i>${log.userAgent}</i></td>
                        <td><i>${log.requestUri}</i></td>
                        <td>${log.method}</td>
                        <td>
                            <c:if test="${gukeer:notEmptyString(log.params)}">
                                <a style="cursor: pointer;color: #54ab37;text-decoration: none" onclick="window.open('${ctx}/school/log/detail?id=${log.id}&type=params')">详情</a>
                            </c:if>
                        </td>
                        <td>
                            <c:if test="${gukeer:notEmptyString(log.exception)}">
                                <a style="cursor: pointer;color: #fd3a47;text-decoration: none" onclick="window.open('${ctx}/school/log/detail?id=${log.id}&type=exception')">详情</a>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>

            </tbody>
        </table>
        <div class="fenye" style="width:100%;">
            <div class="fenYDetail">每页显示${pageInfo.pageSize}条，共${pageInfo.pages}页，共${pageInfo.total}条记录</div>
            <div class="fenY" id="fenY">
            </div>
            <%--<div class="fenY2" id="fenY2">--%>
        </div>
    </div>

</main>
<script type="text/javascript">
    $(function () {
        activeMenuSchool("roll-manage-menu",4);
        $(".fenY").createPage({
            pageCount:${pageInfo.pages},//总页数
            current:${pageInfo.pageNum},//当前页面
            backFn: function (p) {
                window.location.href = "${ctx}/school/log?pageNum=" + p + "&pageSize=10";
            }
        });
    });

    function logParams(msg){
        layer.open({
            title: '参数详情',
            content: msg,
        });
    };
</script>
</body>
</html>