<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ include file="../common/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html">
    <title></title>
    <style>
        body {
            font-family: "Roboto", "Noto Sans CJK SC", "Nato Sans CJK TC", "Nato Sans CJK JP", "Nato Sans CJK KR", -apple-system, ".SFNSText-Regular", "Helvetica Neue", "PingFang SC", "Hiragino Sans GB", "Microsoft YaHei", "WenQuanYi Zen Hei", Arial, sans-serif;
            font-size: 14px;
            color: #101010;
        }

        .container {
            width: 300px;
            margin: 0 auto;
            margin-top: 30px;
        }

        .row {
            margin-bottom: 20px;:
        }

        .row p, .row label {
            width: 30%;
            display: inline-block;
            text-align: right;
        }

        span, select {
            margin-left: 15px;
        }

        select {
            padding: 0 6px;
            width: 190px;
            height: 28px;
            border-radius: 2px;
            font-family: "Microsoft YaHei", "WenQuanYi Zen Hei", Arial, sans-serif;
            outline: none;
        }


    </style>
    <script type="text/javascript" src="${ctxStatic}/js/jquery.js"></script>
    <script type="">
        function doSubmit() {//回调函数，在编辑和保存动作时，供openDialog调用提交表单。
            $("#inputForm").submit();
            return true;
        }
    </script>
</head>
<body>
<div class="container">

    <form action="${ctx}/renshi/rsbumen/save" id="inputForm">
        <input hidden name="teacherId" value="${teacher.id}"/>
        <div class="row">
            <p>姓名：</p>
            <span>${teacher.name}</span>
        </div>
        <div class="row">
            <label>部门名称：</label>
            <select name="departName">
                <option value="0">无</option>
                <c:forEach items="${departmentList}" var="department">
                    <option value="${department.id}"
                            <c:if test="${teacher.departmentId==department.id}">selected</c:if> >${department.name}</option>
                </c:forEach>
            </select>
        </div>
    </form>

</div>
</body>
</html>