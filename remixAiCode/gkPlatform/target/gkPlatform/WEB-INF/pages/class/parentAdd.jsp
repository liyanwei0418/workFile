<%@ include file="../common/common.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html language="en">
<head>
    <meta charset="utf-8">
    <title></title>
    <style>
        li > label {
            font-size: 13px;
            color: #666;
            width: 80px;
            display: inline-block;
            width: 150px;
            font-family: "Microsoft YaHei";
        }

        .dialogForm {
            width: 100%;
            margin-top: 20px;
            height: 80%;
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

            margin: 0;
            padding: 0;
            list-style: none;
            width: 100%;
            padding-bottom: 40px;
        }

        .dialogForm li {
            padding-top: 20px;
            padding-left: 40px;
        }
        li button{
            height: 27px;
            line-height: 27px;
            background: #54AB37;
            border: none;
            outline:none;
            border-radius: 2px;
            color: #fff;
            vertical-align: middle;
            padding: 0 10px;
            margin-left: 10px;
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
            border: 1px solid #a9a9a9;
            border-radius: 2px;
            padding-left: 5px;

        }

        .dialogForm input[type="radio"] {
            height: 14px;
            width: 14px;
            position: relative;
            top:2px;
        }

        .hasRadioButton {
            padding-right: 189px;
        }

        .hasRadioButton span {
            padding-right: 15px;
        }

        li > label {
            width: 105px;
        }

        input[type='text'], select {
            height: 28px;
            outline: none;
        }
    </style>
    <script type="text/javascript" src="${ctxStatic}/js/jquery-1.7.2.js"></script>
    <script>
        function queryStudent() {
            var id = $("input[name='stuNum']").val();
            $.ajax({
                type: "post",
                url: "${ctx}/class/student/info/" + id,
                dataType: "json",
                success: function (data) {
                    if (data != null) {
                        $("input[name='stuName']").val(data.xsxm);
                        $("input[name='stuInfo']").val(data.sectionName + data.schoolTypeName + data.nj + "年级" + data.className);
                    } else {
                        alert("无此学籍号相关信息");
                    }
                },
                error: function (e) {
                    alert(JSON.stringify(e));
                }
            });
        }

        function doSubmit() { //回调函数，在编辑和保存动作时，供openDialog调用提交表单。
            $("#inputForm").submit();
            return true;
        }
    </script>
</head>

<body>
<form action="${ctx}/class/parent/info/add/save" method="post" class="dialogForm" id="inputForm">
    <input type="text" name="prim" hidden value="${parentInfo.parentId}">
    <div>
        <ul>
            <li>
                <label>
                    <c:if test="${gukeer:emptyString(parentInfo)}">
                        <a class="red">*</a>
                    </c:if>
                    学籍号码：</label>
                <input type="text" name="stuNum" <c:if test="${gukeer:notEmptyString(parentInfo)}">readonly</c:if> value="${parentInfo.xh}"/>
                <c:if test="${gukeer:emptyString(parentInfo)}">
                    <button type="button" onclick="queryStudent()">查询</button>
                </c:if>
            </li>
            <li>
                <label>学生姓名：</label>
                <input type="text"  name="stuName" value="${parentInfo.xsxm}"/>
            </li>
            <li>
                <label>学生信息：</label>

                <c:if test="${gukeer:notEmptyString(parentInfo.nj)}">
                    <input type="text" name="stuInfo"
                           value="${parentInfo.sectionName}${parentInfo.schoolName}${parentInfo.nj}年级${parentInfo.className}"/>
                </c:if>
                <c:if test="${gukeer:emptyString(parentInfo)}">
                    <input type="text" name="stuInfo" value=""/>
                </c:if>

            </li>

            <li>
                <label><a class="red">*</a>家长姓名：</label>
                <input type="text" name="parentName" value="${parentInfo.parentName}"/>
            </li>
            <li>
                <label><a class="red">*</a>关系：</label>
                <select name="relation">
                    <option
                            <c:if test="${parentInfo.patriarch_flag == 1}">selected</c:if> value="1">父亲
                    </option>
                    <option
                            <c:if test="${parentInfo.patriarch_flag == 2}">selected</c:if> value="2">母亲
                    </option>
                    <option
                            <c:if test="${parentInfo.patriarch_flag == 3}">selected</c:if> value="3">其他
                    </option>
                </select>
            </li>
            <li>
                <label>家长职务：</label>
                <input type="text" value="${parentInfo.work}" name="work"/>
            </li>
            <li>
                <label>工作单位：</label>
                <input type="text" value="${parentInfo.work_at}" name="workAt"/>
            </li>
            <li>
                <label><a class="red">*</a>家长性别：</label>
                <select name="gender">
                    <option
                            <c:if test="${parentInfo.parentGender == 1}">selected</c:if> value="1">男
                    </option>
                    <option
                            <c:if test="${parentInfo.parentGender == 2}">selected</c:if> value="2">女
                    </option>
                </select>
            </li>
            <li>
                <label>是否为监护人：</label>
                <input type="radio"
                       <c:if test="${parentInfo.sfjhr == 1}">checked</c:if> name="guardian" value="1"/>是
                <input style="margin-left: 100px;" type="radio"
                       <c:if test="${parentInfo.sfjhr == 2}">checked</c:if> name="guardian" value="2"/>否
            </li>
            <li>
                <label><a class="red">*</a>联系电话：</label>
                <input type="text" value="${parentInfo.parentPhone}" name="parentPhone"/>
            </li>
            <li>
                <label style="width: 105px;">是否生活在一起：</label>

                   <input type="radio"
                          <c:if test="${parentInfo.sfyqsh == 1}">checked</c:if> name="lifeTogether" value="1"/>是

                    <input  style="margin-left: 100px;" type="radio"
                           <c:if test="${parentInfo.sfyqsh == 2}">checked</c:if> name="lifeTogether" value="2"/>否

            </li>

        </ul>

    </div>

</form>

</body>

</html>

