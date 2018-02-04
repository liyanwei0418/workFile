<%@ include file="../common/headerXf.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>平台管理</title>

    <link rel="stylesheet" href="${ctxStaticNew}/css/personnel.min.css"/>
</head>

<style>
    td span{
        color: #54ab37;
    }

    .pushapp{
        background: url("${ctxSchool}/images/icon_push.png") no-repeat left 3px;
    }
    .onlineapp{
        background: url("${ctxSchool}/images/icon_line.png") no-repeat left 3px;
    }
    .resetpwd{
        background: url("${ctxSchool}/images/icon_resetPwd.png") no-repeat left 3px;
    }
</style>
<body>

<%@ include file="../common/sonHead/schoolHead.jsp" %>

<main class="container" id="ry-manage">
    <div class="search-box">
        <div class="roll-operation">
            <button class="roll-add" onclick="openDialog('机构新增','${ctx}/school/edit','650px','600px');">新增</button>
        </div>
        <div class="roll-research" style="float: right;">
            <button class="summitButton"></button>
            <input type="hidden" id="searchHidden" value="">
            <input class="searchInput" name="zhiGong" type="text" value="${name}" placeholder="请输入机构名称"/>
        </div>
    </div>
    <div>
        <table class="check">
            <thead>
            <tr>
                <th width="6%">机构名称</th>
                <th width="6%">机构标识</th>
                <th width="6%">所属平台</th>
                <th width="6%">url</th>
                <th width="4%">管理员</th>
                <th width="18%">操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${schoolList}" var="school">
                <tr>
                    <td>${school.schoolName}</td>
                    <td>${school.shortFlag}</td>
                    <td>${school.parentId}</td>
                    <td>${school.deployUrl}</td>
                    <td>${school.managerName}</td>
                    <td>
                        <span onclick="openDialog('机构修改','${ctx}/school/edit?id=${school.id}','750px','750px');">编辑</span>
                        <span class="resetpwd" onclick="alertTips(400,222,'重置密码','确定要重置管理员密码吗，重置密码默认为6个0！','resetPassword(\'${school.id}\')')">重置密码</span>
                        <%--<span class="pushapp" onclick="openDialog('推送应用','${ctx}/school/app/authorization?schoolId=${school.id}&appStatus=1','600px','550px');">推送应用</span>--%>
                        <span class="onlineapp" onclick="openDialog('上线应用','${ctx}/school/app/authorization?schoolId=${school.id}&appStatus=2','600px','550px');">上线应用</span>
                        <span onclick="alertTips(440,222,'删除机构','确定要删除当前机构吗，删除后，相关的信息也会删除,确定吗？','schoolDelete(\'${school.id}\')')">删除</span>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <!--分页按钮组  -->
        <div class="fenye" style="width:100%;">
            <div class="fenYDetail">每页显示${pageInfo.pageSize}条，共${pageInfo.pages}页，共${pageInfo.total}条记录</div>
            <div class="fenY" id="fenY">
            </div>
            <%--<div class="fenY2" id="fenY2">--%>
        </div>
    </div>
</main>

<script type="text/javascript">
    activeMenuSchool("roll-manage-menu",0);
    $(function() {
        $(".fenY").createPage({
            pageCount:${pageInfo.pages},//总页数
            current:${pageInfo.pageNum},//当前页面
            backFn:function(p){
                window.location.href = "${ctx}/school/index?pageNum="+p+"&pageSize=10";
            }
        });

        /*查询搜索*/
        $(".summitButton").click(function () {
            var name=$("input[name='zhiGong']").val();
            window.location.href="${ctx}/school/index?name="+encodeURI(encodeURI(name));


        });
    });
    //重置密码
    function  resetPassword(id) {
        closeAlertDiv();
        $.post("${ctx}/school/admin/update",{
            id:id,
        },function(retJson){
            window.location.href="${ctx}/school/index";
        },"json");
    }

    function  schoolDelete(id) {
        closeAlertDiv();
        $.post("${ctx}/school/delete",{
            id:id,
        },function(retJson){
            window.location.href="${ctx}/school/index";
        },"json");

    }

    function schoolDetils(obj,id) {
        $.ajax({
            type: "POST",
            url: "${ctx}/school/details",
            data: {id:id},
            dataType: "json",
            success: function (result) {
                layer.tips(JSON.stringify(result), obj, {
                    tips: [3, '#1ab394']
                });
            },
            error:function(response) {
                alert("error");
            }

        });
    }

</script>
</body>
</html>
