<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="xd" uri="http://www.xd-tech.com.cn/" %>
<table width="100%"  border="0" cellspacing="0" cellpadding="0" style="table-layout:fixed;">
	 <tr>
	  	<th width="5%" class="TD_Bgcolor1"><input name="checkbox" type="checkbox" id="checkbox" onclick='selectedAll(this,"id")' /></th>
        <th width="15%">用户名</th>
        <th width="30%">说明</th>
        <th width="30%">创建人</th>
        <th width="20%">最近登陆时间</th>
	</tr>
	<c:forEach items="${list}" var="user" varStatus="number" >
		<tr>
			<td class="TD_Bgcolor1">
				<c:if test="${user.username ne 'admin'}">
					<input type="checkbox" name="id" value="${user.id}" />
				</c:if>&nbsp;
			</td>
			<td class="TD_Bgcolor1">
				<span class="txt_color1" style="cursor:pointer;" <c:if test="${user.username ne 'admin'}"> onclick="openDg('${pageContext.request.contextPath}/user/edit.do?id=${user.id}','#修改用户')" </c:if> >${user.username}</span>
			</td>
			<td class="TD_Bgcolor1" title="${user.description}">
				${user.description}&nbsp;
			</td>
			<td class="TD_Bgcolor1">
				${user.lastUpdateUser}&nbsp;
			</td>
			<td class="TD_Bgcolor1">
				<fmt:formatDate value ="${user.lastlogintime}" pattern="yyyy-MM-dd HH:mm" />&nbsp;
			</td>
		</tr>
	</c:forEach>
</table>
<div class="pages">
	<xd:pager  pagerFunction="doPage" pagerStyle="cursor:pointer"  curPagerTheme="pages_cur" showTextPager="true"></xd:pager>
</div>
