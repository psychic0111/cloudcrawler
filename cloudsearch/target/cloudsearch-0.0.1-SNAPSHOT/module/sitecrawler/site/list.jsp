<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="xd" uri="http://www.xd-tech.com.cn/" %>
<table width="100%"  border="0" cellspacing="0" cellpadding="0">
   	<tr>
        <th width="10%;"><input name="checkbox" type="checkbox" id="checkbox" onclick='$("[type=checkbox]").attr("checked",!!$(this).attr("checked"))' /></th>
        <th width="10%;">编号</th>
        <th width="20%;">站点名称</th>
        <th width="10%;">所属分类</th>
        <th width="10%;">类别</th>
        <th width="10%;">状态</th>
        <th width="10%;">操作人</th>
        <th width="20%;">操作时间</th>
     </tr>
    <c:forEach items="${list}" var="site" varStatus="number" >
    	<tr>
    		<td class="TD_Bgcolor1">
    			<input type="checkbox" name="id" value="${site.id}" />
    		</td>
    		<td class="TD_Bgcolor1">
    			${number.index+1}
    		</td>
    		<td class="TD_Bgcolor1" title="${site.name}">
    		  <c:set var="name" value="${site.name}"/>
	 			<c:choose>  
				  <c:when test="${fn:length(site.name) > 20}">  
   						  <span class="txt_color1" style="cursor:pointer;" onclick="openDg('${pageContext.request.contextPath}/site/edit.do?id=${site.id}','+ 修改站点',600,200);" ><c:out value="${fn:substring(site.name, 0, 20)}......" /> </span> 
 				 </c:when>  
					<c:otherwise>  
  				 	<span class="txt_color1" style="cursor:pointer;"  onclick="openDg('${pageContext.request.contextPath}/site/edit.do?id=${site.id}','+ 修改站点',600,200);" ><c:out value="${site.name}" /> </span> 
				</c:otherwise>  
				</c:choose>  
    		</td>
    		<td class="TD_Bgcolor1">
    			${site.siteCategoryName}
    		</td>
    		<td class="TD_Bgcolor1">
    			<c:choose>  
					 <c:when test="${site.webType == 0}">  
	  				 	新闻
	  				 </c:when>
	  				 <c:when test="${site.webType == 1}">  
	  				 	论坛
	  				 </c:when> 
	  				 <c:when test="${site.webType == 2}">  
	  				 	博客
	  				 </c:when>   
						<c:otherwise>
							其他
					</c:otherwise>  
				</c:choose>
    		</td>
    		<td class="TD_Bgcolor1">
    			<c:choose>  
					<c:when test="${site.statue == 1}">  
	  				 	启用
	  				</c:when>  
					<c:otherwise>
					<span style="color: red" >禁用</span>
					</c:otherwise>  
				</c:choose>
    		</td>
    		<td class="TD_Bgcolor1">
    			${site.operateUser}
    		</td>
    		<td class="TD_Bgcolor1">
    			<fmt:formatDate value ="${site.operateTime}" pattern="yyyy-MM-dd HH:mm" />&nbsp;
    		</td>
    	</tr>
    </c:forEach>	
</table>
<div class="pages">
	<xd:pager  pagerFunction="doPage" pagerStyle="cursor:pointer"  curPagerTheme="pages_cur" showTextPager="true"></xd:pager>
</div>

