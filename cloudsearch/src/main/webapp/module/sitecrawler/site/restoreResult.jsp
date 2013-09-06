<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/include/common.jsp" %>
<script>
	alert('${resultInfo}');
	MSG.alert('${resultInfo}');
	window.parent.uploadSuccess();
</script>


