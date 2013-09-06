<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="xd" uri="http://www.xd-tech.com.cn/" %>
<table width="100%"  border="0" cellspacing="0" cellpadding="0">
	<tr>
	    <th width="5%;"><input name="checkbox" type="checkbox" id="checkbox" onclick='$("[type=checkbox]").attr("checked",!!$(this).attr("checked"))' /></th>
	    <th width="10%;">编号</th>
	    <th width="25%;">任务名称</th>
	    <th width="10%;">是否启用</th>
	    <th width="25%;">执行频率</th>
	    <th width="20%;">创建人</th>
 	</tr>
    <c:forEach items="${list}" var="task" varStatus="number" >
  	<tr>
  		<td class="TD_Bgcolor1">
  			<input type="checkbox" name="id" value="${task.id}" />
  		</td>
  		<td class="TD_Bgcolor1">
  			${number.index+1}
  		</td>
  		<td class="TD_Bgcolor1" title="${task.taskName}">
  		<c:set var="name" value="${task.taskName}"/>
		<c:choose>  
		  	<c:when test="${fn:length(task.taskName) > 10}">  
			    <span class="txt_color1" style="cursor:pointer;" onclick="openDg('${pageContext.request.contextPath}/task/edit.do?id=${task.id}','+ 修改任务',600,100);"><c:out value="${fn:substring(task.taskName, 0, 10)}......" /> </span> 
			</c:when>  
			<c:otherwise>  
		 		<span class="txt_color1" style="cursor:pointer;"  onclick="openDg('${pageContext.request.contextPath}/task/edit.do?id=${task.id}','+ 修改任务',600,100);"><c:out value="${task.taskName}" /> </span> 
			</c:otherwise>  
		</c:choose>  
  		</td>
  		<td class="TD_Bgcolor1">
  			<c:choose>  
			  	<c:when test="${task.isUse == 1}">  
				          启用 
				</c:when>  
				<c:otherwise>  
			 		禁用 
				</c:otherwise>  
			</c:choose>  
  		</td>
  		<td class="TD_Bgcolor1" align="left">
  			<c:choose> 
			  	<c:when test="${task.clickType == 1}">  
			          ${task.click}&nbsp;&nbsp;&nbsp;小时 
				</c:when>  
				<c:otherwise>  
			 		<c:choose> 
			  			<c:when test="${task.click % 60 == 0 ||  task.click % 60 == 30}">  
			  				${task.click / 60}&nbsp;&nbsp;&nbsp;小时 
			  			</c:when>
			  			<c:otherwise>  
					 		${task.click }&nbsp;&nbsp;&nbsp;分钟 
						</c:otherwise> 
			  		</c:choose>
				</c:otherwise>  
			</c:choose>  
  		</td>
  		<td class="TD_Bgcolor1">
  			${task.operateUser}
  		</td>
  	</tr>
  	</c:forEach>	
</table>
<div class="pages">
	<xd:pager  pagerFunction="doPage" pagerStyle="cursor:pointer"  curPagerTheme="pages_cur" showTextPager="true"></xd:pager>
</div>
