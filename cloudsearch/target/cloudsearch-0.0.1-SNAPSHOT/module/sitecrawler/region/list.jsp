<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="xd" uri="http://www.xd-tech.com.cn/" %>

<div class="function">
	<form id="searchRegion" class="indexForm"  action="${pageContext.request.contextPath}/region/regionListTree.do" method="post">
	<input type="hidden" name="id" id="id" value="${region.id}" />
	  <div class="left">
	    <input  type="button" class="botton_style1" value="添加" onclick="openDg('${pageContext.request.contextPath}/region/add.do','+ 添加区域',600,100);" />
	    <input  type="button" class="botton_style2" value="删除" onclick="delRegion()"  />
	  </div>
	  <div class="right">
	  	 <span>名称：</span>
	     <input id="areaName" type="text" name="areaName" style="width: 140px;display:inline;" value="${region.areaName}" class="inputTxt1" maxlength="100" />
	     <input  type="button" class="botton_style3" style="display:inline;" value="检索" onclick="searchFrm_submit();" />
	  </div>
	  <div class="clear"></div>
   </form>
</div>
<table width="100%"  border="0" cellspacing="0" cellpadding="0">
      	<tr>
           <th width="10%;"><input name="checkbox" type="checkbox" id="checkbox" onclick='$("[type=checkbox]").attr("checked",!!$(this).attr("checked"))' /></th>
        <th width="10%;">编号</th>
        <th width="30%;">名称</th>
     </tr>
     <c:forEach items="${list}" var="region" varStatus="number" >
     	<tr>
     		<td class="TD_Bgcolor1">
     			<input type="checkbox" name="id" value="${region.id}" />
     		</td>
     		<td class="TD_Bgcolor1">
     			${number.index+1}
     		</td>
     		<td class="TD_Bgcolor1" title="${region.areaName}">
     		  <c:set var="name" value="${region.areaName}"/>
		 		<c:choose>  
					 <c:when test="${fn:length(region.areaName) > 10}">  
   						  <span class="txt_color1" style="cursor:pointer;" onclick="openDg('${pageContext.request.contextPath}/region/edit.do?id=${region.id}','+ 修改区域',600,100);" ><c:out value="${fn:substring(region.areaName, 0, 10)}......" /> </span> 
  				 	</c:when>  
 					<c:otherwise>  
   				 		<span class="txt_color1" style="cursor:pointer;"  onclick="openDg('${pageContext.request.contextPath}/region/edit.do?id=${region.id}','+ 修改区域',600,100);" ><c:out value="${region.areaName}" /> </span> 
					</c:otherwise>  
				</c:choose>  
     		</td>
     	</tr>
     </c:forEach>	
</table>
<div class="pages">
	<xd:pager  pagerFunction="doPage" pagerStyle="cursor:pointer"  curPagerTheme="pages_cur" showTextPager="true"></xd:pager>
</div>


