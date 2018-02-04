<%@ include file="../common/common.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html language="en">
<head>
    <meta charset="utf-8">
    <title></title>
    <style>
        label {
            font-size: 13px;
            color: #666;
        }

        .dialogForm {
            width: 100%;
            margin-top: 20px;
        }

        .dialogForm > div {
            width: 100%;
            height: auto;
        }

        .red {
            color: red;
            font-size: 15px;
            margin-right: 7px;
        }

        .dialogLeft {
            width: 50%;
            height: 100%;
            float: left;
        }

        .dialogRight {
            background: aqua;
            width: 50%;
            height: 100%;
            float: right;
        }

        .dialogForm ul {
            text-align: center;
            margin: 0;
            padding: 0;
            list-style: none;
            width: 100%;
        }

        .dialogForm li {
            padding-top: 20px;

        }

        .dialogForm > div > ul > li > label {
            text-align: right;
        }

        .dialogLeft li {
            text-align: right;
            padding-right: 5%;
        }

        .dialogForm input, select {
            height: 23px;
            width: 190px;
            border-radius: 3px;
            border: 1px solid #a9a9a9;
            padding-left: 5px;
        }

        .dialogForm input[type="radio"] {
            height: 17px;
            width: 20px;
        }

        .hasRadioButton {
            padding-right: 189px;
        }

        .hasRadioButton span {
            padding-right: 15px;
        }

        select {
            border-radius: 2px;
        }

        select，input {
            outline: none;
            height: 28px;
        }

    </style>
    <link rel="stylesheet" type="text/css" href="${ctxStaticNew}/css/jquery.autocomplete.css"/>
    <script type="text/javascript" src="${ctxStatic}/js/jquery-1.7.2.js"></script>
    <script type="text/javascript" src="${ctxStatic}/js/jquery.autocomplete.js"></script>
    <script>

        /*$(function () {
            var data =${teacherList};
            $("#autoComplete").autocomplete(data, {
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
        });*/

        function doSubmit() { //回调函数，在编辑和保存动作时，供openDialog调用提交表单。
           if ($("input[name='departName']").val().length>6){
               alert("部门名称长度超过6位");
               return false;
           }

            $("#inputForm").submit();
            return true;
        }
    </script>
</head>

<body>
<form action="${ctx}/renshi/rsbumen/add/save" method="post" class="dialogForm" id="inputForm">
    <div>
        <ul>
            <li>
                <label>部门名称：</label>
                <input type="" name="departName" value="${totalDepart.name}"/>
                <input name="currentDepartId" type="hidden" value="${totalDepart.id}"/>
            </li>
            <li>
                <label>&nbsp;部门编号：</label>
                <input type="" name="departNo" value="${totalDepart.no}"/>
            </li>
            <%--<li>
                <label>&nbsp;部门排序：</label>
                <input type="" name="departOrder"  value="" />
            </li>--%>
          <%--  <li>
                <label>&nbsp;部门领导：</label>
                <input type="" name="departManager" id="autoComplete" value="${totalDepart.masterName}"/>
                <input name="departManagerId" type="hidden" id="personId" value="${totalDepart.masterId}"/>
            </li>--%>
            <li>
                <label>&nbsp;所属部门：</label>
                <select name="departParent">
                    <option value="0">无</option>
                    <c:forEach items="${departList}" var="depart">
                        <option value="${depart.id}"
                                <c:if test="${depart.id == totalDepart.parentId}">selected</c:if> >${depart.name}</option>
                    </c:forEach>

                </select>
            </li>
            <%--<li class="hasRadioButton">
                <label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;有效性：</label>
                <input type="radio" name="delFlag"   value="0" />
                <span>
                    是
                </span>
                <input type="radio" name="delFlag"  value="1" />
                <span>
                    否
                </span>

            </li>--%>
        </ul>

    </div>

</form>

</body>

</html>

