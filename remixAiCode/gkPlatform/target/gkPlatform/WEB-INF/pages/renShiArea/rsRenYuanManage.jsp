<%@ include file="../common/headerXf.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>区级人事管理</title>
    <link rel="stylesheet" href="${ctxStaticNew}/css/personnel.min.css"/>
</head>
<body>

<%@ include file="../common/sonHead/qujiRenShiHead.jsp" %>

<main class="container" id="ry-manage">
    <div class="search-box">
        <div class="roll-operation">
            <button class="roll-add" onclick="openDialog('新增职工','${ctx}/renshi/renyuan/edit','900px','650px')">新增
            </button>
            <button class="roll-import" onclick="openDialog('导入数据','${ctx}/renshi/moban/import','500px','350px')">导入
            </button>
            <button class="roll-export" onclick="exportStu()">导出</button>
            <button class="roll-delete" onclick="alertTips(400,200,'删除','确定要删除选中项吗？','sure()')">删除</button>
            <button class="roll-import" onclick="window.location.href='${ctx}/renshi/moban/download'">下载模板</button>
        </div>
        <div class="roll-research" style="float: right;">
            <button class="summitButton"></button>
            <input type="hidden" id="searchHidden" value="${teacherName}">
            <input class="searchInput" name="zhiGong" type="text" placeholder="请输入职工姓名"/>
        </div>
    </div>
    <div>
        <table class="check">
            <style>
                table th:first-child, table td:first-child {
                    text-align: center;
                }
            </style>
            <thead>
            <tr>
                <th width="2%"><input class="rsCheck headerCheck" type="checkbox"/></th>
                <th width="4%" style="text-align: center">序号</th>
                <th width="6%">姓名</th>
                <th width="4%">性别</th>
                <th width="9%">教职工编号</th>
                <th width="9%">办公室电话</th>
                <th width="10%">电话</th>
                <th width="14%">操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${teacherList}" var="teacher" varStatus="status">
                <tr>
                    <td><input class="rsCheck" name="lanmuCheck" id="${teacher.id}" type="checkbox"/></td>
                    <td style="text-align: center">${status.index+1+(pageInfo.pageNum-1)*10}</td>
                    <td>${teacher.name}</td>
                    <td>
                        <c:choose>
                            <c:when test="${teacher.gender==1}">
                                男
                            </c:when>
                            <c:when test="${teacher.gender==2}">
                                女
                            </c:when>
                            <c:otherwise>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>${teacher.no}</td>
                    <td>${teacher.workPhone}</td>
                    <td>${teacher.mobile}</td>
                    <td>
                        <span onclick="openDialog('修改信息','${ctx}/renshi/renyuan/edit?id=${teacher.id}','900px','600px')">编辑</span>
                        <span onclick="alertTips(400,202,'删除人员','确定要删除${teacher.name}吗?','deleteSure(\'${teacher.id}\')')">删除</span>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <div class="fenye">
        <c:if test="${gukeer:notEmptyString(pageInfo.pages)}">
            <div class="fenYDetail">共${pageInfo.total}条记录，本页${pageInfo.size}条</div>
        </c:if>
        <div class="fenY" id="fenY">
        </div>
    </div>
</main>
<script>
    activeMenu("quji", 1);
    /* 初始化分页 */
    $(function () {
        //搜索框关键字回显
        $("input[name='zhiGong']").val($("#searchHidden").val());
        <c:if test="">

        </c:if>
        $(".fenY").createPage({
            pageCount:${pageInfo.pages},//总页数
            current:${pageInfo.pageNum},//当前页面
            backFn: function (p) {
                var name = $("#searchHidden").val();
                window.location.href = "${ctx}/area/renyuan/index?pageNum=" + p + "&teacherName=" + encodeURI(encodeURI(name));

            }
        });

        var name;
        $(".summitButton").click(function () {
            name = $("input[name='zhiGong']").val();
            window.location.href = "${ctx}/area/renyuan/index?teacherName=" + encodeURI(encodeURI(name));
        });


        $(".headerCheck").on("click", function () {
            if (this.checked == true) {
                $("input[type='checkbox']").prop("checked", true);
            } else {
                $("input[type='checkbox']").prop("checked", false);
            }
        });

        $(".gotoPage").click(function () {
            name = $("input[name='zhiGong']").val();
            var pageNum = $(".go").val();
            if (pageNum <= 0 || pageNum >${pageInfo.pages}) {
                layer.msg("请输入正确的页码")
            } else {
                window.location.href = "${ctx}/area/renyuan/index?pageNum=" + $(".go").val() + "&teacherName=" + encodeURI(encodeURI(name));
            }
        });

    });

    //删除用户
    function deleteSure(id) {
        closeAlertDiv();
        $.post("${ctx}/renshi/renyuan/delete", {
            id: id,
        }, function (retJson) {
        }, "json");
        setTimeout(function () {
            layer.msg('删除成功', {
                time: 2000 //2秒关闭（如果不配置，默认是3秒）
            }, function () {
                window.location.reload();
            });
        }, 300)
    }

    function sure() {
        //closeAlertDiv();
        var howManyDelay = 0;
        var spCodesTemp = "";
        $("input[name='lanmuCheck']:checked").each(function (i) {
            if (0 == i) {
                spCodesTemp = $(this).attr("id");
            } else {
                spCodesTemp += ("," + $(this).attr("id"));
            }
            howManyDelay++;
        });
        deleteSure(spCodesTemp);
    }

    function exportStu() {
        var spCodesTemp = "";
        $("input:checkbox[name='lanmuCheck']:checked").each(function (i) {
            if (0 == i) {
                spCodesTemp = $(this).attr("id");
            } else {
                spCodesTemp += ("," + $(this).attr("id"));
            }
        });
        if ($("input:checkbox[name='lanmuCheck']:checked").length > 0) {
            openDialog('导出' + $('input:checkbox[name=lanmuCheck]:checked').length + '位教师数据', '${ctx}/renshi/moban/export?teachers=' + spCodesTemp, '1000px', '800px');
        }
        else {
            //layer.msg("请选择教师");
            var teacherName = $("input[name='zhiGong']").val();
            openDialog('导出${pageInfo.total}位教师数据', '${ctx}/renshi/moban/export?teacherName=' + encodeURI(encodeURI(teacherName)), '1000px', '800px');
        }
    }

    function importCallBack(res) {
        layer.closeAll();
        layer.confirm(res.msg, {
            btn: ['下载失败列表', '关闭'] //按钮
        }, function () {
            var form = $("<form>");//定义一个form表单
            form.attr("style", "display:none");
            form.attr("target", "");
            form.attr("method", "post");
            form.attr("action", "${ctx}/renshi/teacher/error/export");
            var input1 = $("<input>");
            input1.attr("type", "hidden");
            input1.attr("name", "msg");
            input1.attr("value", JSON.stringify(res.errorList));
            $("body").append(form);//将表单放置在web中
            form.append(input1);
            form.submit();//表单提交
            return false;
        }, function () {
            window.location.reload(true);
        });
    }
</script>
</body>
</html>
