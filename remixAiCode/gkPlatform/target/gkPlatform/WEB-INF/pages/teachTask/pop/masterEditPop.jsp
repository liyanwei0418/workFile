<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../../common/common.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="${ctxStaticNew}/css/personnel.min.css"/>
    <link rel="stylesheet" type="text/css" href="${ctxStaticNew}/css/jquery.autocomplete.css"/>
    <script type="text/javascript" src="${ctxStaticNew}/js/jquery-1.7.2.js"></script>
    <script type="text/javascript" src="${ctxStaticNew}/js/layer/layer.js"></script>
    <script type="text/javascript" src="${ctxStaticNew}/js/openDialog.js"></script>
    <script type="text/javascript" src="${ctxStaticNew}/js/jquery.autocomplete.js"></script>
    <%--<script src="${ctxStaticNew}/js/dropDownList.js"></script>--%>

    <script>
        $(function () {
            var data = ${teacherList};
            $(".autoComplete").autocomplete(data, {
                minChars: 1,// 在触发autoComplete前用户至少需要输入的字符数.Default: 1，如果设为0，在输入框内双击或者删除输入框内内容时显示列表
                max: 100,//下拉显示项目的个数
                autoFill: false,//要不要在用户选择时自动将用户当前鼠标所在的值填入到input框. Default: false
                mustMatch: true,//如果设置为true,autoComplete只会允许匹配的结果出现在输入框,所有当用户输入的是非法字符时将会得不到下拉框.Default: false
                matchContains: true,
                formatItem: function (row, i, max) {
                    return row.name;

                },
                formatMatch: function (row, i, max) {
                    return row.name;
                },
                formatResult: function (row) {
                    return row.name;//+row.account.replace(/[^0-9]/ig,"");//取得账号里面的数字...
                }
            });

            $(".autoComplete").bind("input propertychange", function () {
                if ($(this).val().trim() == "") {
                    $(".completeTips").show();
                } else {
                    $(".completeTips").hide();
                }
            });
        })
    </script>
</head>
<style>
    * {
        padding: 0;
        margin: 0;
        box-sizing: border-box;
        font-family: "Microsoft YaHei", Arial, STXihei, '华文细黑', 'Microsoft YaHei', SimSun, '宋体', Heiti, '黑体', sans-serif;
    }

    span {
        display: inline-block;
    }

    .m-teacher, .completeTips {
        padding-left: 10px;
    }

    .popup-main {
        background: #fff;
        padding: 35px 0px 10px 25px;
        font-size: 13px !important;
        color: #525252 !important;
    }

    table td {
        font-size: 13px;
        padding: 10px 0;
    }

    table td > span:first-child {
        width: 88px;
        text-align: right;
    }

    table td > span:last-child {
        width: 150px;
        height: 28px;
        line-height: 28px;
        margin-left: 12px;
        padding-left: 10px;
    }

    input[type='text'] {
        width: 150px;
        height: 28px;
        line-height: 28px;
        margin-left: 10px;
        padding-left: 10px;
        outline: none;
        border: 1px solid #dadada;
        border-radius: 2px;
        color: #333;
        vertical-align: middle;

    }

    table td input[type='button'] {
        width: 60px;
        text-align: center;
        height: 28px;
        line-height: 28px;
        margin-left: 10px;
        outline: none;
        border: 1px solid #54ab37;
        border-radius: 2px;
        color: #fff;
        background: #54ab37;
        vertical-align: top;
    }

    .checkbox-containt {
        display: inline-block;
        width: 350px;
        vertical-align: top;
        margin-left: 10px;
    }

    .rows {
        margin-top: 10px;
    }

    #chooseWhoTell > span {
        margin-left: 10px;
        margin-bottom: 10px;
        border: 1px solid #54AB37;
        /*padding: 0 15px;*/
        height: 28px;
        width: 85px;
        text-align: center;
        line-height: 28px;
        display: inline-block;
    }
    #chooseWhoTells > span {
        margin-top: 0;
        display: inline-block;
        width: 88px !important;
        text-align: right;
    }
</style>

<body>
<form action="${ctx}/teach/task/master/update?type=update&&id=${classView.classId}" id="courseEdit" method="post">
    <input type="hidden" value="${bzrView.cycleId}" class="cycleId">
    <input type="hidden" class="classId" value="${bzrView.classId}"/>
    <input type="hidden" class="teacherIdFromHoutai" value="${bzrView.teacherId}"/>
    <input type="hidden" class="deputyIdsFromHoutai" value="${bzrView.deputyIds}"/>
    <table>
        <tr>
            <td><span>年级班级:</span><span>${bzrView.xdName}${bzrView.nj}年级${bzrView.className}</span></td>
        </tr>
        <tr>
            <td><span>班主任:</span>
                <div class="row" style="display: inline-block;">
                    <input type = 'text' class="autoComplete m-teacher" name="teacherName" value="${bzrView.masterName}"/><span
                        class="completeTips">请输入系统中存在的信息！</span>
                    <input type="hidden" name="teacherId" id="personId"/>
                </div>
            </td>
        </tr>
        <tr>

            <td><%--<span>副班主任:</span>--%>
                <div class="row">
                    <p id="chooseWhoTells">
                        <span>副班主任:</span>
                        <span id="chooseWhoTell" style="width: auto !important;"
                              onclick="openDialog('选择副班主任','${ctx}/notify/chooseperson/show?chooseIds=','860px','620px');">

                        <c:if test="${bzrView.deputymasterName !='' &&bzrView.deputymasterName !=null}">
                            <input type="text" value="${bzrView.deputymasterName}" width="100%">
                        </c:if>
                        <input type="button" value="更改"/></span>
                        <input type="hidden" id="whichDepartMent" name="bumens"/>
                    </p>
                </div>
            </td>
        </tr>
    </table>
    <div class="suggest" id="search-suggest" style="display: none">
        <ul id="search-result">
            <li></li>
        </ul>
    </div>
</form>

</body>
<script>

    function chooseResult(depratmentIds, depratmentNames) {
        var names = "";
        var departNames = depratmentNames.split(",");
        for (var i = 0; i < departNames.length; i++) {
            if (departNames[i].trim() != "") {
                names += "<span class='receive'>" + departNames[i] + "</span>";
            }
        }
        $("#chooseWhoTell").html(names + " <input type='button' value='更改'>");
        $("#whichDepartMent").val(depratmentIds);

        $("#chooseWhoTell").attr("onclick", "openDialog('选择人员','${ctx}/notify/chooseperson/show?chooseIds=" + depratmentIds + "','950px','620px')");

    }

    <%--var dropDownList = new DropDownList(--%>
            <%--document.getElementById("my_input"),--%>
            <%--${teacherList},--%>
            <%--{--%>
                <%--dropdownTrigger: [{eventName: 'keyup'}],--%>
                <%--onSelect: function (data) {--%>
                    <%--document.getElementById("my_input").value = data.name;--%>
                <%--}--%>
            <%--}--%>
    <%--);--%>


    <%--$('.checkbox-containt').on('click', '.trAdd', function () {--%>
        <%--$('<div class="row rows" style="display: inline-block;"><input class="autoComplete m-teacher" name="teacherName" value="${master}"/><span class="completeTips">请输入系统中存在的信息！</span> <input type="hidden" name="teacherId" class="personId"/><i class="trAdd"style="display: inline-block;width:20px;height:20px;font-style:normal;line-height: 20px;text-align: center;color:#fff;background: green;border-radius: 50%;top:10px;left:70px">+</i> <i class="trDecrease"style="display: inline-block;width:20px;height:20px;font-style:normal;line-height: 20px;text-align: center;color:#fff;background: red;border-radius: 50%;top:10px;left:70px">-</i> </div>').appendTo($(".checkbox-containt"));--%>
    <%--})--%>

    <%--$('.checkbox-containt').on('click', '.trDecrease', function () {--%>
        <%--$(this).parents('div').remove();--%>
    <%--})--%>

//    var strPath = window.document.location.pathname;
//    var postPath = strPath.substring(0, strPath.substr(1).indexOf('/') + 1);
    function doSubmit() { //回调函数，在编辑和保存动作时，供openDialog调用提交表单。
        if (true) {
            var cycleId = $(".cycleId").val();
            var cycleYear = $(".cycleYear").find("option:selected").val();
            var cycleSemester = $(".cycleSemester").find("option:selected").val();
            var classId = $(".classId").val();
            var chooseIds = $("#whichDepartMent").val();
            var teacherIdFromHouTai = $(".teacherIdFromHoutai").val();
            var cycleId = $(".cycleId").val()
            var deputyIds = $(".deputyIdsFromHoutai").val();
//            $('input[type=checkbox]:checked').each(function (i) {
//                var teacherId = $(this).val();
//                tempTeacherId += "," + teacherId;
//            })
            var teacherId = $("#personId").val();
            $.post("${ctx}/teach/task/master/edit", {
                chooseIds: chooseIds,
                teacherId: teacherId,
                teacherIdFromHouTai: teacherIdFromHouTai,
                classId: classId,
                cycleId: cycleId,
                deputyIds:deputyIds
            }, function (data) {
                setTimeout(function () {
                    parent.location.reload();
                }, 400);
                /*刷新父级页面,延迟保证页面刷新的时候数据已经更新完毕*/
                setTimeout(function () {
                    top.layer.close()
                }, 300);
            })
//            $("#courseEdit").submit();
            return true;
        } else {
            layer.msg("输入有误！");
            return false;
        }
    }
</script>
</html>
