<%@ include file="../common/headerXf.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>家长账号管理</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <link rel="stylesheet" href="${ctxStaticNew}/css/schoolRollManage.min.css"/>
</head>
<body>

<%@ include file="../common/sonHead/xuejiHead.jsp" %>

<div class="row" id="parent-num-manage">
    <main class="container">
        <div class="parent-num-manage-menu">
            <ul>
                <li><a href="#" data="generated" id="ysc">已生成</a></li>
                <li><a href="#" data="not-generate" id="wsc">未生成</a></li>
            </ul>
        </div>
        <section id="generated" class="row">
            <div class="row">
                <span style="color: #fd3a47;line-height: 30px;" >提示：重置密码默认为${password}</span>
                <button class="summitButton"></button>
                <input type="text" name="studentname" placeholder="请输入家长姓名" value="${studentname}"/>
            </div>
            <div class="row">
                <table>
                    <style>
                        table th:nth-child(1), table td:nth-child(1) {
                            text-align: center;
                        }
                        table td{height:39px;}
                    </style>
                    <thead>
                    <tr>
                        <th width="5%">序号</th>
                        <th width="10%">家长姓名</th>
                        <th width="15%">用户名</th>
                        <th width="5%">性别</th>
                        <th width="15%">学生姓名</th>
                        <th width="15%">学生学籍号</th>
                        <th width="25%">电话号码</th>
                        <th width="20%">操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${studentListHave}" var="student" varStatus="status">
                        <c:if test="${gukeer:notEmptyString(student.parAccount)}">
                            <tr>
                                <td>
                                        ${status.index + 1+(pageInfoHave.pageNum-1)*10}
                                </td>
                                <td>${student.parentName}</td>
                                <td>${student.parAccount}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${student.parentGender==1}">
                                            男
                                        </c:when>
                                        <c:when test="${student.parentGender==2}">
                                            女
                                        </c:when>
                                        <c:otherwise>

                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td>${student.xsxm}</td>
                                <td>${student.xh}</td>
                                <td>${student.parentPhone}</td>
                                <td>
                                    <span class="reset"
                                          onclick="alertTips(400,202,'重置密码','确定要重置密码吗，重置的密码为${password}?','resetPassword(\'${student.parentId}\',3)')">
                                        重置密码</span>

                                </td>
                            </tr>
                        </c:if>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <%--<div class="fenye">--%>
                <%--<div class="fenY" id="fenY2">--%>
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
                <button onclick="alertTips(500,210,'生成账号','确定将所有未生成账号的家长进行生成账号的操作？','createSure()')">生成账号</button>
                <%--<span>系统默认生成账号为JZ+学生姓名全拼，重名人员账号全拼后带有数字区别，生成账号的密码默认为${password}--%>
                <span>系统默认生成账号为家长姓名全拼+@学校标识，重名人员账号全拼后带有数字区别，生成账号的密码默认为${password}
                </span>
            </div>
            <div class="row">
                <table>
                    <thead>
                    <tr>
                        <th width="8%">序号</th>
                        <th width="12%">家长姓名</th>
                        <th width="15%">用户名</th>
                        <th width="8%">性别</th>
                        <th width="15%">学生姓名</th>
                        <th width="20%">学生学籍号</th>
                        <th width="22%">电话号码</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${studentListNo}" var="student" varStatus="status">
                        <c:if test="${gukeer:emptyString(student.parAccount)}">
                            <tr>
                                <td>
                                        ${status.index + 1+(pageInfoNo.pageNum-1)*10}
                                </td>
                                <td>${student.parentName}</td>
                                <td>${student.parAccount}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${student.parentGender==1}">
                                            男
                                        </c:when>
                                        <c:when test="${student.parentGender==2}">
                                            女
                                        </c:when>
                                        <c:otherwise>

                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td>${student.xsxm}</td>
                                <td>${student.xh}</td>
                                <td>${student.parentPhone}</td>
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
                <c:if test="${gukeer:notEmptyString(pageInfoNo.pages)}">
                    <div class="fenyDetail">共${pageInfoNo.total}条记录，本页${pageInfoNo.size}条</div>
                </c:if>
                <div class="fenY" id="fenY1">
                    <%--<input type="text"/>--%>
                </div>
            </div>
        </section>
    </main>
</div>

<script>

    activeMenu("parAccMenu",0);
    /* 初始化分页 */
    $(function () {
        <c:if test="${pageInfoHave.pages != 0}">
        $("#fenY2").createPage({
            pageCount:${pageInfoHave.pages},//总页数
            current:${pageInfoHave.pageNum},//当前页面
            backFn: function (p) {
                var name = $("input[name='studentname']").val();
                window.location.href = "${ctx}/class/paraccount/index?pageNumHave=" + p + "&pageSize=10&studentname="+encodeURI(encodeURI(name));
            }
        });
        $('#fenY2goto').click(function () {
            var p = $('#fenY2go').val();
            if (p <= 0 || p >${pageInfoHave.pages}) {
                layer.msg("请输入正确的页码")
            } else {
                var name = $("input[name='studentname']").val();
                window.location.href = "${ctx}/class/paraccount/index?pageNumHave=" + p + "&pageSize=10&studentname="+encodeURI(encodeURI(name));
            }
        });

        </c:if>
        <c:if test="${pageInfoNo.pages != 0}">
        $("#fenY1").createPage({
            pageCount:${pageInfoNo.pages},//总页数
            current:${pageInfoNo.pageNum},//当前页面
            backFn: function (p) {
                var name = $("input[name='studentname']").val();
                window.location.href = "${ctx}/class/paraccount/index?pageNumNo=" + p + "&pageSize=20&whichPage=1&studentname="+encodeURI(encodeURI(name));
            }
        });


        $('#fenY1goto').click(function () {
            var p = $('#fenY1go').val();
            if (p <= 0 || p >${pageInfoNo.pages}) {
                layer.msg("请输入正确的页码")
            } else {
                var name = $("input[name='studentname']").val();
                window.location.href = "${ctx}/class/paraccount/index?pageNumNo=" + p + "&pageSize=20&whichPage=1&studentname="+encodeURI(encodeURI(name));
            }
        });
        </c:if>
        if(${gukeer:notEmptyString(whichPage)}){
            $('.parent-num-manage-menu a').removeClass('active');
            $("#wsc").addClass('active');
            var data = $("#wsc").attr('data');
            var sections = $('#' + data);
            $('#' + sections[0].id).show();
            $('#' + sections[0].id).siblings('section').hide();
        }else{
            $('.parent-num-manage-menu a').removeClass('active');
            $("#ysc").addClass('active');
            var data = $("#ysc").attr('data');
            var sections = $('#' + data);
            $('#' + sections[0].id).show();
            $('#' + sections[0].id).siblings('section').hide();
        }
        /*查询搜索*/
        $(".summitButton").click(function () {
            var name = $("input[name='studentname']").val();
            window.location.href = "${ctx}/class/paraccount/index?studentname=" + encodeURI(encodeURI(name));
        });



    });
    function createSure() {
        closeAlertDiv();
        layer.msg('生成中', {icon: 16, time: 100000});

        $.post("${ctx}/class/generator/patriarch", {
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
        layer.msg("重置成功");
    }

</script>
</body>
</html>
