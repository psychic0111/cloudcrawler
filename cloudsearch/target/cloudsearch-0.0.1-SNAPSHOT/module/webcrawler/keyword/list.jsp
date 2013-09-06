<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="xd" uri="http://www.xd-tech.com.cn/" %>
<table width="100%"  border="0" cellspacing="0" cellpadding="0">
    <tr>
       <th width="5%;"><input name="checkbox" type="checkbox" id="checkbox" onclick='$("[type=checkbox]").attr("checked",!!$(this).attr("checked"))' /></th>
       <th width="5%;">编号</th>
       <th width="25%;">名称</th>
       <th width="40%;">监控词</th>
       <th width="10%;">是否启用</th>
       <th width="15%;">操作人</th>
    </tr>
    <c:forEach items="${list}" var="keyword" varStatus="number" >
    	<tr>
    		<td class="TD_Bgcolor1">
    			<input type="checkbox" name="id" value="${keyword.id}" />
    		</td>
    		<td class="TD_Bgcolor1">
    			${number.index+1}
    		</td>
    		<td class="TD_Bgcolor1" title="${keyword.name}">
    		  <c:set var="name" value="${keyword.name}"/>
	 			<c:choose>  
				 <c:when test="${fn:length(keyword.name) > 10}">  
					  <span class="txt_color1" style="cursor:pointer;" onclick="openDg('${pageContext.request.contextPath}/keyword/edit.do?id=${keyword.id}','+ 修改监控词',600,100);"><c:out value="${fn:substring(keyword.name, 0, 10)}......" /> </span> 
 				 </c:when>  
					<c:otherwise>  
  				 	<span class="txt_color1" style="cursor:pointer;"  onclick="openDg('${pageContext.request.contextPath}/keyword/edit.do?id=${keyword.id}','+ 修改监控词',600,100);"><c:out value="${keyword.name}" /> </span> 
				</c:otherwise>  
				</c:choose>  
    		</td>
    		<td class="TD_Bgcolor1" style="text-align:left;">
    			<c:choose>  
				 <c:when test="${fn:length(keyword.words) > 20}">  
						  <c:out value="${fn:substring(keyword.words, 0, 20)}......" /> 
 				 </c:when>  
				 <c:otherwise>  
  				 	<c:out value="${keyword.words}" />
				 </c:otherwise>  
				</c:choose>  
    		</td>
    		<td class="TD_Bgcolor1">
    			 <c:choose>  
					 <c:when test="${keyword.isUse == 1}">  
	 				 	启用
	 				 </c:when>  
					 <c:otherwise>
						禁用
					 </c:otherwise>  
				 </c:choose>  
    		</td>
    		<td class="TD_Bgcolor1">
    			<c:out value="${keyword.username}" />
    		</td>
    	</tr>
    </c:forEach>	
</table>
<div class="pages">
	<xd:pager  pagerFunction="doPage" pagerStyle="cursor:pointer"  curPagerTheme="pages_cur" showTextPager="true"></xd:pager>
</div>
