<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions" %>
<script type="text/javascript" language="javascript">
//修改网络参数配置
function updateNetParam(){
	AT.postFrm("netParamForm",function(data){
		$("#iframepage").load("${pageContext.request.contextPath}/netParam/netParamSuccess.do?num="+Math.random(),iframeHeight);
	},true);
}
function clickCancel(){
	$("#iframepage").load("${pageContext.request.contextPath}/netParam/netParamSuccess.do?num="+Math.random(),iframeHeight);
}
</script>
<div class="title">
  <table border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td class="c1"><img src="${pageContext.request.contextPath}/images/title_c_left.png" width="3" height="34" /></td>
      <td class="c2"><img src="${pageContext.request.contextPath}/images/index.png"  /> 网络连接参数</td>
      <td class="c3"><img src="${pageContext.request.contextPath}/images/title_c_right.png" width="3" height="34" /></td>
    </tr>
  </table>
</div>
<div class="lists">
		<div  id="iframepage">
			<jsp:include page="/module/sitecrawler/netParam/edit.jsp"></jsp:include>
		</div>
</div>
