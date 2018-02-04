<%@ include file="../common/headerXf.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>学生账号管理</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <link rel="stylesheet" href="${ctxStaticNew}/css/schoolRollManage.min.css"/>

    <script>
        /* 初始化分页 */
        $(function () {
            <c:if test="${pageInfoHave.pages != 0}">
            $("#fenY2").createPage({
                pageCount:${pageInfoHave.pages},//总页数
                current:${pageInfoHave.pageNum},//当前页面
                backFn: function (p) {
                    var name = $("input[name='studentname']").val();
                    if (name != null && name != "") {
                        window.location.href = "${ctx}/class/stuaccount/index?pageNumNo=" + p + "&pageSize=10&studentname=" + encodeURI(encodeURI(name));
                    }
                    window.location.href = "${ctx}/class/stuaccount/index?pageNumHave=" + p + "&pageSize=10&studentname=" + encodeURI(encodeURI(name));
                }
            });
            $('#fenY2goto').click(function () {
                var p = $('#fenY2go').val();
                if (p <= 0 || p >${pageInfoHave.pages}) {
                    layer.msg("请输入正确的页码")
                } else {
                    var name = $("input[name='studentname']").val();
                    window.location.href = "${ctx}/class/stuaccount/index?pageNumHave=" + p + "&pageSize=10&studentname=" + encodeURI(encodeURI(name));
                }
            })
            </c:if>

            <c:if test="${pageInfoNo.pages!=0}">
            $("#fenY1").createPage({
                pageCount:${pageInfoNo.pages},//总页数
                current:${pageInfoNo.pageNum},//当前页面
                backFn: function (p) {
                    var name = $("input[name='studentname']").val();
                    if (name != null && name != "") {
                        window.location.href = "${ctx}/class/stuaccount/index?pageNumNo=" + p + "&pageSize=10&whichPage=1&studentname=" + encodeURI(encodeURI(name));
                    }
                    else {
                        window.location.href = "${ctx}/class/stuaccount/index?pageNumNo=" + p + "&pageSize=10&whichPage=1&studentname=" + encodeURI(encodeURI(name));
                    }
                }
            });

            $('#fenY1goto').click(function () {
                var p = $('#fenY1go').val();
                if (p <= 0 || p >${pageInfoNo.pages}) {
                    layer.msg("请输入正确的页码")
                } else {
                    var name = $("input[name='studentname']").val();
                    window.location.href = "${ctx}/class/stuaccount/index?pageNumNo=" + p + "&pageSize=10&whichPage=1&studentname=" + encodeURI(encodeURI(name));
                }
            })
            </c:if>

            if (${gukeer:notEmptyString(whichPage)}) {
                $('.stu-num-manage-menu a').removeClass('active');
                $("#wsc").addClass('active');
                var data = $("#wsc").attr('data');
                var sections = $('#' + data);
                $('#' + sections[0].id).show();
                $('#' + sections[0].id).siblings('section').hide();
            } else {
                $('.stu-num-manage-menu a').removeClass('active');
                $("#ysc").addClass('active');
                var data = $("#ysc").attr('data');
                var sections = $('#' + data);
                $('#' + sections[0].id).show();
                $('#' + sections[0].id).siblings('section').hide();
            }

            /*查询搜索*/
            $(".summitButton").click(function () {
                var name = $("input[name='studentname']").val();
                window.location.href = "${ctx}/class/stuaccount/index?studentname=" + encodeURI(encodeURI(name));
            });


        })

    </script>
</head>
<body>

<%@ include file="../common/sonHead/xuejiHead.jsp" %>

<div class="row" id="stu-num-manage">
    <main class="container">
        <div class="stu-num-manage-menu">
            <ul>
                <li><a href="#" data="generated" id="ysc">已生成</a></li>
                <li><a href="#" data="not-generate" id="wsc">未生成</a></li>
            </ul>
        </div>
        <section id="generated" class="row">
            <div class="row">
                <span style="color: #fd3a47;">提示：重置密码默认为${password}</span>
                <button class="summitButton"></button>
                <input type="text" placeholder="请输入学生姓名" class="searchInput" name="studentname" value="${studentname}"/>
            </div>
            <div class="row">

                <table>
                    <thead>
                    <style>
                        table th:nth-child(2), table td:nth-child(2) {
                            text-align: left !important;
                        }
                    </style>
                    <tr>
                        <th width="5%">序号</th>
                        <th width="10%">姓名</th>
                        <th width="15%">用户名</th>
                        <th width="8%">性别</th>
                        <th width="20%">学籍号</th>
                        <th width="15%">操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    </tr>
                    <c:forEach items="${studentListHave}" var="student" varStatus="status">
                        <c:if test="${gukeer:notEmptyString(student.account)}">
                            <tr>
                                <td>
                                        ${status.index + 1+(pageInfoHave.pageNum-1)*10}
                                </td>
                                <td>${student.xsxm}</td>
                                <td>${student.account}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${student.xsxb==1}">
                                            男
                                        </c:when>
                                        <c:when test="${student.xsxb==2}">
                                            女
                                        </c:when>
                                        <c:otherwise>

                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <%--<td>${student.xjh}</td>--%>
                                <td>${student.xh}</td>
                                <td>
                                    <span class="reset"
                                          onclick="alertTips(400,202,'重置密码','确定要重置密码吗，重置的密码为${password}?','resetPassword(\'${student.id}\',2)')">重置密码</span>
                                </td>
                            </tr>
                        </c:if>
                    </c:forEach>

                    </tbody>
                </table>
            </div>
            <%--<div class="fenye">--%>
                <%----%>
                <%--<div class="fenY" id="fenY2">--%>
                    <%--&lt;%&ndash;<input type="text"/>&ndash;%&gt;--%>
                <%--</div>--%>
            <%--</div>--%>
            <div class="fenye">

                <div class="fenyDetail">共${pageInfo.total}条记录，本页${pageInfo.size}条</div>

                <div class="fenY" id="fenY2">
                    <%--<input type="text"/>--%>
                </div>
            </div>
        </section>
        <section id="not-generate" class="row">
            <div class="row">
                <button onclick="alertTips('500px','210px','生成账号','确定将所有未生成账号的学生进行生成账号的操作？','createSure()')">生成账号
                </button>
                <span>系统默认生成账号为学生姓名全拼+@学校标识，重名人员账号全拼后带有数字区别，生成账号的密码默认为${password}
                </span>
            </div>
            <div class="row">
                <table>
                    <thead>
                    <tr>
                        <th width="10%">序号</th>
                        <th width="20%">姓名</th>
                        <th width="15%">性别</th>
                        <th width="25%">学籍号</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${studentListNo}" var="student" varStatus="status">
                        <c:if test="${gukeer:emptyString(student.account)}">
                            <tr>
                                <td>
                                        ${status.index + 1+(pageInfoNo.pageNum-1)*10}
                                </td>
                                <td>${student.xsxm}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${student.xsxb==1}">
                                            男
                                        </c:when>
                                        <c:when test="${student.xsxb==2}">
                                            女
                                        </c:when>
                                        <c:otherwise>

                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td>${student.xh}</td>
                            </tr>
                        </c:if>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <style>.fenye {
                padding: 0 15px;
            }</style>


            <div class="fenye">

                <div class="fenyDetail">共${pageInfo.total}条记录，本页${pageInfo.size}条</div>

                <div class="fenY" id="fenY1">
                    <%--<input type="text"/>--%>
                </div>
            </div>
        </section>
    </main>
</div>

<script type="text/javascript">
    activeMenu("stuAccMenu",0);

    function createSure() {
        closeAlertDiv();
        layer.msg('生成中', {icon: 16, time: 100000});
        $.post("${ctx}/class/generator/stu", {
            password: '${password}',
        }, function (retJson) {
            setTimeout(function () {
                layer.msg(retJson.msg);
                setTimeout(function () {
                    window.location.reload();
                }, 1000);
            }, 200 *${studentListNo.size()});
        });
    }


    function resetPassword(id, sf) {
        $.post("${ctx}/class/resetPassword", {
                    id: id,
                    sf: sf,
                    password: '${password}',
                },
                function (retJson) {

                }, "json");
        closeAlertDiv();
        setTimeout(function () {
            layer.msg("重置成功");
//                window.location.reload();
        }, 200);
    }

</script>
</body>
</html>