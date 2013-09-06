<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="xd" uri="http://www.xd-tech.com.cn/" %>
<table width="100%"  border="0" cellspacing="0" cellpadding="0">
   	<tr>
        <th width="5%;"><input name="checkbox" type="checkbox" id="checkbox" onclick='$("[type=checkbox]").attr("checked",!!$(this).attr("checked"))' /></th>
        <th width="5%;">编号</th>
        <th width="10%;">爬虫名称</th>
        <th width="10%;">爬虫代码</th>
        <th width="35%;">访问路径</th>
        <th width="20%;">爬虫类型</th>
        <th width="10%;">采集线程</th><!--
        <th width="10%;">下载线程</th>
        <th width="10%;">单站点线程数</th>
        --><th width="10%;">状态</th>
        <th width="5%;">操作</th>
     </tr>
    <c:forEach items="${list}" var="crawler" varStatus="number" >
    	<tr>
    		<td class="TD_Bgcolor1">
    			<input type="checkbox" name="id" value="${crawler.id}" />
    		</td>
    		<td class="TD_Bgcolor1">
    			${number.index+1}
    		</td>
    		<td class="TD_Bgcolor1" title="${crawler.name}">
    		  <c:set var="name" value="${crawler.name}"/>
	 			<c:choose>  
				  <c:when test="${fn:length(crawler.name) > 20}">  
   						  <span class="txt_color1" style="cursor:pointer;" onclick="openDg('${pageContext.request.contextPath}/crawler/edit.do?id=${crawler.id}','+ 修改爬虫',650,200);" ><c:out value="${fn:substring(crawler.name, 0, 20)}......" /> </span> 
 				 </c:when>  
					<c:otherwise>  
  				 	<span class="txt_color1" style="cursor:pointer;"  onclick="openDg('${pageContext.request.contextPath}/crawler/edit.do?id=${crawler.id}','+ 修改爬虫',650,200);" ><c:out value="${crawler.name}" /> </span> 
				</c:otherwise>  
				</c:choose>  
    		</td>
    		<td class="TD_Bgcolor1">
    			${crawler.code}
    		</td>
    		<td class="TD_Bgcolor1" >
    			<div align="left">${crawler.crawlerAddress}</div>
    		</td>
    		<td class="TD_Bgcolor1">
    			${crawler.crawlerType}
    		</td>
    		<td class="TD_Bgcolor1">
    			${crawler.crawlerThread}
    		</td>
    		<!--<td class="TD_Bgcolor1">
    			${crawler.downloadThread}
    		</td>
    		<td class="TD_Bgcolor1">
    			${crawler.siteThread}
    		</td>
    		-->
    		<td id="status_${crawler.id}" class="TD_Bgcolor1">
    		</td>
    		<td class="TD_Bgcolor1">
    		<c:if test="${crawler.status == 1}">
    			<c:choose>  
				  <c:when test="${crawler.crawlerStatus == 1}">  
   					<input id="dataStatusA_${data.id}" type="button" class="botton_style3"  value="启用" onclick="startCrawlerControl('${crawler.code}');" />
 				  </c:when>
 				  <c:when test="${crawler.crawlerStatus == 2}">  
   					<input id="dataStatusB_${data.id}" type="button" class="botton_style2"  value="停止" onclick="endCrawlerControl('${crawler.code}');"  />
 				  </c:when>
				</c:choose>  
			</c:if>
			<c:if test="${crawler.status == 0}">
				已禁用
			</c:if>
			
    		</td>
    	</tr>
    </c:forEach>	
</table>
<div class="pages">
	<xd:pager  pagerFunction="doPage" pagerStyle="cursor:pointer"  curPagerTheme="pages_cur" showTextPager="true"></xd:pager>
</div>
<input type="hidden" id="operateHidden" name="operateHidden" value = "${operate }"/>


