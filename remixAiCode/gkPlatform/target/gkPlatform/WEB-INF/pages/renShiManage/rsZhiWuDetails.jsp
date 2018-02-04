<%@ include file="../common/common.jsp" %>
<%@ include file="../common/headerMenu.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <META http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="Content-Language" content="en"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>职务信息</title>
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/css/fenBan.css"/>
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/css/pageDivide.css"/>
    <style>
        #backSpanB:hover {
            cursor: hand;
        }
    </style>
    <script type="text/javascript" src="${ctxStatic}/js/layer/layer.js"></script>
    <script type="text/javascript" src="${ctxStatic}/js/fenBan.js"></script>
    <script type="text/javascript" src="${ctxStatic}/js/pageDevide.js"></script>
    <script src="${ctxStatic}/js/buttons.js" type="text/javascript" charset="utf-8"></script>
    <script src="${ctxStatic}/js/openDialog.js" type="text/javascript"></script>
    <script type="text/javascript">
        /* 初始化分页 */
        $(function () {
            $(".fenY").createPage({
                pageCount:${pageInfo.pages},//总页数
                current:${pageInfo.pageNum},//当前页面
                backFn: function (p) {
                    window.location.href = "${ctx}/notify/zhiwu/index?pageNum=" + p + "&pageSize=10";
                }
            });

            $(".headerCheck").on("click", function () {
                if (this.checked == true) {
                    $("input[type='checkbox']").prop("checked", true);
                } else {
                    $("input[type='checkbox']").prop("checked", false);
                }
            });
        })


    </script>
</head>

<body class="thisBody">
<div style="height:78px;width:1200px;margin:0px auto">
    <div style="z-index:99;position:fixed;margin:0px 0px">


        <div class="app_store_headerMenu fenBan-header">

            <div class="appStoreLeft fenBanLeft">
                <img src="${ctxStatic}/image/fenban/fenban.png"/>&nbsp;
                <a style="line-height: 49px;font-size:24px;color: #1AB394;">人事管理</a>
            </div>
            <div class="rsMenuRight">
                <ul>
                    <shiro:hasPermission name="renShi:role:view">
                        <li onclick="window.location.href='${ctx}/renshi/rolefp/index'">角色分配</li>
                    </shiro:hasPermission>
                    <shiro:hasPermission name="renShi:zhiwu:view">
                        <li onclick="window.location.href='${ctx}/renshi/zhiwu/index'" class="active">职务信息</li>
                    </shiro:hasPermission>
                    <shiro:hasPermission name="renShi:bumen:view">
                        <li onclick="window.location.href='${ctx}/renshi/bumen/index'">部门管理</li>
                    </shiro:hasPermission>
                    <shiro:hasPermission name="renshi:account:view">
                        <li onclick="window.location.href='${ctx}/renshi/account/index'">账号管理</li>
                    </shiro:hasPermission>
                    <shiro:hasPermission name="renShi:renYuan:view">
                        <li onclick="window.location.href='${ctx}/renshi/renyuan/index'">人员管理</li>
                    </shiro:hasPermission>
                </ul>
            </div>
        </div>
    </div>
</div>

<div class="mainContain fenbanMain fenbanStep">
    <div class="forBeauty" style="background:#ffffff;">
        <div class="rsTableHeader" style="height: 20px !important;">
            <div class="">
                <input class="rsButton" style="position: relative;float: right;margin-top: -5px;margin-right: 14px"
                       onclick="openDialog('新增人员','${ctx}/renshi/title/teacher/add?titleId='+${title.id},'600px','400px');"
                       type="button" name="" value=" 新增 "/>
                <%--<input class="rsButton delete" onclick="alertTips(400,200,'删除','确定要删除选中项吗？','sure()')" type="button" style="float: none;color: #fff;" value=" 删除 " />--%>
                <span onclick="location.href='javascript:history.go(-1);'" id="backSpanB"
                      style="font-size: 18px;color: #1AB394;">
							返回
						</span>

                <%--<div style="color: #999999;font-size: 16px;float: right;margin-right: 30px;">
                    <input class="searchInput" type="text" placeholder=" 请输入职务名称" value="" /> <!--高级搜索-->
                    <input style="opacity: 0;position: relative;margin-left: -40px;height:30px;" type="button" name="" id="" value="搜索"/>
                </div>
--%>
            </div>
        </div>
        <div class="rsTable" style="margin-top: 44px">
            <table>
                <tr class="headerTh">
                    <%--<th width="3%"><input class="rsCheck headerCheck" type="checkbox"/></th>--%>
                    <th width="5%">序号</th>
                    <th width="10%">姓名</th>
                    <%--<th width="5%">职务级别</th>--%>
                    <th width="15%">编号</th>
                    <th width="20%">联系电话</th>
                    <th width="20%">手机电话</th>
                    <th width="20%">邮箱</th>
                    <th width="10%">操作</th>

                </tr>
                <c:forEach items="${teacherList}" var="teacher" varStatus="status">
                    <tr>
                            <%--<td><input class="rsCheck" name="lanmuCheck" id="" type="checkbox"/></td>--%>
                            <%--<td>${title.id}</td>--%>
                        <td>${status.index+1}</td>
                        <td>${teacher.name}</td>
                            <%--<td>${title.jb}</td>--%>
                        <td>${teacher.no}</td>
                        <td>${teacher.phone}</td>
                        <td>${teacher.mobile}</td>
                        <td>${teacher.email}</td>
                        <td class="actionA">
                                <%--<a  onclick="openDialog('编辑职务','${ctx}/renshi/title/add?titleId=${title.id}','600px','400px');">编辑</a>&nbsp;|&nbsp;--%>
                                <%--<a  onclick="zhiwuDetails(${title.id})">相关人员</a>&nbsp;|&nbsp;--%>
                            <a class="deleteA"
                               onclick="alertTips(400,222,'移除职位','确定要移除${teacher.name}的${currentTitle.mc}职务吗?','deleteSure(\'${teacher.id}\')')">移除</a>
                        </td>
                    </tr>
                </c:forEach>

            </table>
            <div class="fenY" id="fenY">
            </div>
        </div>
    </div>
</div>

<script>

    //删除该教师职务
    function deleteSure(id) {
        closeAlertDiv();
        $.post
        ("${ctx}/renshi/title/teacher/delete", {
            id: id,
        }, function (retJson) {
        }, "json");
        setTimeout(function () {
            window.location.reload();
        }, 300)
    }

    /*删除栏目下信息确定*/
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

        setTimeout(function () {
            window.location.reload();
        }, 100 * howManyDelay);//删除的数据越多，延时要越长。否则：刷新页面的时候，数据还没删完..

    }
</script>
</body>


</html>

