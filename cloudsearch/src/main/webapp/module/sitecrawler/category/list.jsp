<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="xd" uri="http://www.xd-tech.com.cn/" %>
<table width="100%"  border="0" cellspacing="0" cellpadding="0">
	<tr>
	    <th width="10%;"><input name="checkbox" type="checkbox" id="checkbox" onclick='$("[type=checkbox]").attr("checked",!!$(this).attr("checked"))' /></th>
	    <th width="10%;">编号</th>
	    <th width="30%;">分类名称</th>
	    <th width="10%;">分类下站点数量</th>
	    <th width="20%;">操作人</th>
	    <th width="20%;">操作时间</th>
	    
 	</tr>
    <c:forEach items="${list}" var="siteCategory" varStatus="number" >
  	<tr>
  		<td class="TD_Bgcolor1">
  			<input type="checkbox" name="id" value="${siteCategory.id}" />
  		</td>
  		<td class="TD_Bgcolor1">
  			${number.index+1}
  		</td>
  		<td class="TD_Bgcolor1" title="${siteCategory.name}">
  		<c:set var="name" value="${siteCategory.name}"/>
		<c:choose>  
		  	<c:when test="${fn:length(siteCategory.name) > 10}">  
			    <span class="txt_color1" style="cursor:pointer;" onclick="openDg('${pageContext.request.contextPath}/siteCategory/edit.do?id=${siteCategory.id}','+ 修改站点分类',600,100);"><c:out value="${fn:substring(siteCategory.name, 0, 10)}......" /> </span> 
			</c:when>  
			<c:otherwise>  
		 		<span class="txt_color1" style="cursor:pointer;"  onclick="openDg('${pageContext.request.contextPath}/siteCategory/edit.do?id=${siteCategory.id}','+ 修改站点分类',600,100);"><c:out value="${siteCategory.name}" /> </span> 
			</c:otherwise>  
		</c:choose>  
  		</td>
  		<td class="TD_Bgcolor1">
  			${siteCategory.countSite}
  		</td>
  		<td class="TD_Bgcolor1">
  			${siteCategory.operateUser}
  		</td>
  		<td class="TD_Bgcolor1">
  			<fmt:formatDate value="${siteCategory.operateTime}" pattern="yyyy-MM-dd HH:mm"/>
  		</td>
  		
  	</tr>
  	</c:forEach>	
</table>
<div class="pages">
	<xd:pager  pagerFunction="doPage" pagerStyle="cursor:pointer"  curPagerTheme="pages_cur" showTextPager="true"></xd:pager>
</div>
