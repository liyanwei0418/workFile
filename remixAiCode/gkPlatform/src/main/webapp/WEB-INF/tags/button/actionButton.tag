<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute name="url" type="java.lang.String" required="true"%>
<%@ attribute name="showName" type="java.lang.String" required="true"%>
<button class="btn" style="background: #1AB394;color: #ffffff" onclick="jumpTo()" data-toggle="tooltip" data-placement="top">
	${showName }
</button>
<script type="text/javascript">
function jumpTo(){
	window.location.href = ${url};
}
</script>