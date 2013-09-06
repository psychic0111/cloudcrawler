<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="xd" uri="http://www.xd-tech.com.cn/" %>
<table width="100%"  border="0" cellspacing="0" cellpadding="0">
     	<tr>
          <th width="10%;"><input name="checkbox" type="checkbox" id="checkbox" onclick='$("[type=checkbox]").attr("checked",!!$(this).attr("checked"))' /></th>
          <th width="10%;">编号</th>
          <th width="20%;">名称</th>
          <th width="20%;">IP地址</th>
          <th width="20%;">端口号</th>
          <th width="10%;">操作人</th>
       </tr>
       <c:forEach items="${list}" var="siteProxy" varStatus="number" >
       	<tr>
       		<td class="TD_Bgcolor1">
       			<input type="checkbox" name="id" value="${siteProxy.id}" />
       		</td>
       		<td class="TD_Bgcolor1">
       			${number.index+1}
       		</td>
       		<td class="TD_Bgcolor1" title="${siteProxy.name}">
       		  <c:set var="name" value="${siteProxy.name}"/>
				 <c:choose>  
  					  <c:when test="${fn:length(siteProxy.name) > 20}">  
      						  <span class="txt_color1" style="cursor:pointer;" onclick="openDg('${pageContext.request.contextPath}/siteProxy/edit.do?id=${siteProxy.id}','+ 修改代理',600,100);" ><c:out value="${fn:substring(siteProxy.name, 0, 20)}......" /> </span> 
    				 </c:when>  
   					<c:otherwise>  
     				 	<span class="txt_color1" style="cursor:pointer;"  onclick="openDg('${pageContext.request.contextPath}/siteProxy/edit.do?id=${siteProxy.id}','+ 修改代理',600,100);"><c:out value="${siteProxy.name}" /> </span> 
  					</c:otherwise>  
				</c:choose>  
       		</td>
       		<td class="TD_Bgcolor1">
       			${siteProxy.proxyIp}
       		</td>
       		<td class="TD_Bgcolor1">
       			${siteProxy.proxyPort}
       		</td>
       		<td class="TD_Bgcolor1">
       			${siteProxy.operateUser}
       		</td>
       	</tr>
       </c:forEach>	
</table>
<div class="pages">
	<xd:pager  pagerFunction="doPage" pagerStyle="cursor:pointer"  curPagerTheme="pages_cur" showTextPager="true"></xd:pager>
</div>
