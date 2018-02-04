<%@ page import="cn.gukeer.common.utils.FileUtils" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/gukeer.tld" prefix="gukeer"%>
<%@ taglib tagdir="/WEB-INF/tags/button" prefix="btn"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:set var="ctxStatic" value="${pageContext.request.contextPath}/assetsNew/old_resourse"/>
<c:set var="ctxSchool" value="${pageContext.request.contextPath}/assetsNew/school"/>
<c:set var="ctxStaticNew" value="${pageContext.request.contextPath}/assetsNew"/>
<c:set var="scheme" value="${pageContext.request.scheme}"/>
<c:set var="serverName" value="${pageContext.request.serverName}"/>
<c:set var="serverPort" value="${pageContext.request.serverPort}"/>
<c:set var="fullPath" value="${scheme}://${serverName}:${serverPort}${ctx}"/>
<c:set var="defaultHead" value="<%=FileUtils.DEFAULT_HEAD_PHOTO%>"/>