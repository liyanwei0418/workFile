<%@ include file="../common/headerXf.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>教师云盘</title>
    <link rel="stylesheet" href="${ctxStaticNew}/css/personnel.min.css"/>
</head>
<body>
<%@ include file="../common/sonHead/netDiskHead.jsp" %>
<main class="container" id="ry-manage">
    <div class="search-box">
        <div class="roll-research">
            </select>
        </div>
        <div class="roll-operation">
            <button class="roll-add" onclick="openDialog('新增','${ctx}/net/disk/add/account/pop','700px','550px')">
                添加
            </button>
            <%--<button class="roll-delete" onclick="alertTips(400,200,'删除','确定要删除选中项吗？','sure()')">删除</button>--%>
            <button class="roll-import"
                    onclick="openDialog('导入数据','${ctx}/class/fileImport?url=${ctx}/net/disk/teacher/import','500px','350px')">
                导入
            </button>

            <button class="roll-import" onclick="window.location.href='${ctx}/net/disk/teacher/info/download'">下载模板
            </button>
        </div>
        <div style="float:right;"><span>本校云盘共</span><span class="total">${totalSpace}GB</span><%--,<span>已经使用</span><span class="usage"></span>,占<span class="percentage"></span>--%>
        </div>
    </div>
    <div>
        <table class="check">
            <thead>
            <tr>
                <th width="5%"><input class="rsCheck headerCheck" type="checkbox"/></th>
                <th width="20%">用户姓名</th>
                <th width="20%">已用空间/空间</th>
                <%--<th width="20%">创建时间</th>--%>
                <th width="20%">操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${accountInfoList}" var="account" varStatus="status">
                <tr>
                    <td><input class="rsCheck" name="lanmuCheck" id="" type="checkbox"/></td>
                    <td>${account.email}</td>
                    <td><c:if test="${account.strUsage!=''}">${account.strUsage}/</c:if>${account.strTotal}</td>
                        <%--<td>${account.strCreateTime}</td>--%>
                    <td>
                        <span onclick="openDialog('设置空间','${ctx}/net/disk/set/space/pop?total=${account.total}&email=${account.email}&token=${account.token}','300px','300px')">设置空间</span>
                        <span onclick="alertTips(400,202,'删除云盘账号','确定要删除该账号吗?','deleteSure(\'${account.email}\')')">删除</span>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <div class="fenye" style="width:100%;">
        <div class="fenYDetail">共${total}条记录，本页${currentPageCount}条</div>
        <div class="fenY2" id="fenY2">
        </div>
    </div>
</main>
<script>


    //    function size(size,flag) {
    //        var strSize = "";
    //        var tem = 0;
    //        if (size>=1000*1000*1000){
    //            tem = Math.ceil(size/(1000*1000*1000));
    //            strSize = tem+"GB"
    //        }else if(size<(1000*1000*1000)&&size>=(1000*1000)){
    //            tem = Math.ceil(size/(1000*1000));
    //            strSize=tem+"MB";
    //        } else {
    //            tem = Math.ceil( size/1000);
    //            strSize =tem+"kb"
    //        }
    //        return strSize;
    //    }
//    activeMenu("quji",1);
    /* 初始化分页 */
    activeMenu("lmmenu", 0);
    $(function () {

        $(".fenY2").createPage({
            pageCount: '${totalPage}',//总页数
            current: '${currentPage}',//当前页面
            backFn: function (p) {
                console.log(p);
                window.location.href = "${ctx}/net/disk/manage/index?pageNum=" + p + "&pageSize=10";
            }
        });

        $('#fenY2goto').click(function () {
            var p = $('#fenY2go').val();
            if (p <= 0 || p >'${totalPage}') {
                layer.msg("请输入正确的页码")
            } else {
                window.location.href = "${ctx}/net/disk/manage/index?pageNum=" + p + "&pageSize=10";
            }

        })
        <%--$(function () {--%>
        <%--var totalSize = '${sessionScope.totalSize}';--%>
        <%--var usage = '${sessionScope.usage}';--%>
        <%--var strTotal = size(totalSize);--%>
        <%--var strUsage = size(usage);--%>
        <%--var percentage = parseFloat(((usage/totalSize)*100).toFixed(2))+"%";--%>

        <%--$(".total").text(strTotal);--%>
        <%--$(".usage").text(strUsage);--%>
        <%--$(".percentage").text(percentage);--%>
        <%--})--%>

        $("select").change(function () {
            var cycleSemester = "";
            var cycleYear = $("select[name='cycleYear']").val();

            console.log($(this).attr("name"));
            if ($(this).attr("name") == "cycleYear") {
                cycleSemester = "";
            } else {
                cycleSemester = $("select[name='cycleSemester']").val()
            }

            window.location.href = "${ctx}/teach/task/room/index?cycleYear=" + cycleYear + "&cycleSemester=" + cycleSemester;
        });


        $(".headerCheck").on("click", function () {
            if (this.checked == true) {
                $("input[type='checkbox']").prop("checked", true);
            } else {
                $("input[type='checkbox']").prop("checked", false);
            }
        });
    });

    //删除用户
    function deleteSure(email) {
        closeAlertDiv();
        $.post("${ctx}/net/disk/delete/account", {
            email: email,
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

    function importCallBack(res) {
        layer.closeAll();
        layer.confirm(res.msg, {
            btn: ['下载失败列表', '关闭'] //按钮
        }, function () {
            var form = $("<form>");//定义一个form表单
            form.attr("style", "display:none");
            form.attr("target", "");
            form.attr("method", "post");
            form.attr("action", "${ctx}/teach/task/room/error/export");
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
