<%@ include file="../common/common.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" %>


    学年：
    <select name="cycleYear" class="cycleYear">
        <c:forEach items="${yearList}" var="year">
            <option name="cycleYear"
                    <c:if test="${cycleYear ==year}">selected</c:if> value="${year}">${year}</option>
        </c:forEach>
    </select>
    学期：
    <select name="cycleSemester" class="cycleSemester">
        <c:forEach items="${semesterList}" var="cycle">
            <option name="cycleSemester"
                    <c:if test="${cycleSemester ==cycle.cycleSemester}">selected</c:if> value="${cycle.cycleSemester}">${cycle.cycleSemester}
            </option>
        </c:forEach>

        <%--<option name="cycleSemester"--%>
        <%--<c:if test="${cycleSemester ==1}">selected</c:if> value="1">1--%>
        <%--</option>--%>
        <%--<option name="cycleSemester"--%>
        <%--<c:if test="${cycleSemester ==2}">selected</c:if> value="2">2--%>
        <%--</option>--%>
    </select>
