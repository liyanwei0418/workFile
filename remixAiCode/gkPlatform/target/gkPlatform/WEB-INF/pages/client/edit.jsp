<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
</head>
<body>

    <form:form method="post" commandName="client">
        <form:hidden path="id"/>
        <form:hidden path="clientId"/>
        <form:hidden path="clientSecret"/>

        <div class="form-group">
            <form:label path="clientName">客户端名：</form:label>
            <form:input path="clientName"/>
        </div>

        <form:button>${op}</form:button>

    </form:form>


</body>
</html>