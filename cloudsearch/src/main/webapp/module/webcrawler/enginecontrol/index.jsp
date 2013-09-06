<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions" %>
<script type="text/javascript" language="javascript">
//开始搜索引擎
function startEngineControl(){
	$("#iframepage").load("${pageContext.request.contextPath}/engineControl/start.do?num="+Math.random(),iframeHeight);
}
//停止搜索引擎
function endEngineControl(){
	$("#iframepage").load("${pageContext.request.contextPath}/engineControl/end.do?num="+Math.random(),iframeHeight);
}
</script>
<div class="title">
  <table border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td class="c1"><img src="${pageContext.request.contextPath}/images/title_c_left.png" width="3" height="34" /></td>
      <td class="c2"><img src="${pageContext.request.contextPath}/images/collect.png"  /> 全网监控</td>
      <td class="cright"><img src="${pageContext.request.contextPath}/images/page.png" /><a class="link4" href="#">查看当前页操作说明</a></td>
      <td class="c3"><img src="${pageContext.request.contextPath}/images/title_c_right.png" width="3" height="34" /></td>
    </tr>
  </table>
</div>
<div class="lists">
	<div  id="iframepage">
		<jsp:include page="/module/webcrawler/enginecontrol/engineControl.jsp"></jsp:include>
	</div>
</div>
<iframe id="webCrawlerUploadIframeId" src="${pageContext.request.contextPath}/module/webcrawler/enginecontrol/time.jsp" name="webCrawlerUploadIframeId" frameborder="0" width="100%;" height="100%;" style="display:none;"></iframe>
