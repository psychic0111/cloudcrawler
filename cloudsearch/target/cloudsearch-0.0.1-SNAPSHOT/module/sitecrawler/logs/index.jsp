<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions" %>
<%--
日志管理-添加
@author WangWei
2013-08-08
--%>
<script type="text/javascript" language="javascript">
function doPage(ths,pageNo,pageSize){
	AT.load("iframepage","${pageContext.request.contextPath}/crawlerlog/crawlerlogList.do?pageNo="+pageNo+"&pageSize="+pageSize+"&url="+$("#url").val()+"&crawlerCode="+$("#crawlerCode").val()+"&startTime="+$("#startTime").val()+"&endTime="+$("#endTime").val(),iframeHeight);
}
function _pageFrmsubmit(){
	AT.postFrm("searchCrawlerLog",function(data){
		$("#iframepage").html("");
		$("#iframepage").html(data);
		iframeHeight();
		closeDg();
	},true);
}
</script>
<div class="title">
   <table border="0" cellspacing="0" cellpadding="0">
     <tr>
       <td class="c1"><img src="${pageContext.request.contextPath}/images/title_c_left.png" width="3" height="34" /></td>
       <td class="c2"><img src="${pageContext.request.contextPath}/images/sys.png"  /> 日志管理</td>
       <td class="c3"><img src="${pageContext.request.contextPath}/images/title_c_right.png" width="3" height="34" /></td>
     </tr>
   </table>
</div>
<div class="lists">
     <form id="searchCrawlerLog" class="indexForm"  action="${pageContext.request.contextPath}/crawlerlog/crawlerlogList.do" method="post">
	     <div class="function">
	        <div class="left">
	        </div>
	   		<div class="right">
	   			URL：
	   			<input id="url" type="text" name="url" value="${crawlerlog.url }" style="width: 200px;" class="inputTxt1" maxlength="100" />
	   			爬虫：
	   			<select id="crawlerCode" class="inputTxt1" style="width: 120px;"  name="crawlerCode" >
					<option value="">请选择...</option>
					<c:forEach items="${crawlers}" var="crawler" varStatus="status">
						<option value = "${crawler.code }" <c:if test="${fn:indexOf(crawlerlog.code,crawler.code)>-1}">selected="selected"</c:if> >${crawler.name }</option>
					</c:forEach>
				</select>
				开始时间：
				<script>
				</script>
	   			<input id="startTime"  name="startTime" style="width:115px;" value="${crawlerlog.startTime}"  type="text"  readonly="readonly" onclick="WdatePicker();" class="inputTxt1"/>
	            <img onclick="WdatePicker({el:'startTime'})" src="${pageContext.request.contextPath}/js/My97DatePicker/skin/datePicker.gif" width="22" height="30" > 
	   			结束时间：
        		<input id="endTime" name="endTime" type="text"  value="${crawlerlog.endTime}" style="width:115px;" readonly="readonly" onclick="WdatePicker();" class="inputTxt1"/>
	            <img onclick="WdatePicker({el:'endTime'})" src="${pageContext.request.contextPath}/js/My97DatePicker/skin/datePicker.gif" width="22" height="30" >
        		<input  type="button" class="botton_style3 siteButton" style="display:inline;" value="检索" onclick="_pageFrmsubmit();" />
	      	</div>
	        <div class="clear"></div>
	     </div>
     </form>
   	 <div class="table_list" id="iframepage">
   		<jsp:include page="/module/sitecrawler/logs/list.jsp"></jsp:include>
     </div>
</div>
