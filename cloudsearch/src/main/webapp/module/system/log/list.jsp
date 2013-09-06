<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="xd" uri="http://www.xd-tech.com.cn/" %> 
	<div class="table_list" id="iframepage">
	<table width="100%"  border="0" cellspacing="0" cellpadding="0" style="table-layout:fixed;">
		 <tr>
          <th width="15%">操作项</th>
          <th width="10%">操作类型</th>
          <th width="*%">操作说明</th>
          <th width="10%">操作人</th>
          <th width="15%">操作时间</th>
		</tr>
		<c:forEach items="${list}" var="log" varStatus="number" >
			<tr>
				<td class="TD_Bgcolor1" >
					${log.commond}&nbsp;
				</td>
				<td class="TD_Bgcolor1">
					${log.action}&nbsp;
				</td>
				<td class="TD_Bgcolor1" title="${log.description}">
					${log.description}&nbsp;
				</td>
				<td class="TD_Bgcolor1">
					${log.actionUser}&nbsp;
				</td>
				<td class="TD_Bgcolor1">
					<fmt:formatDate value ="${log.actiontime}" pattern="yyyy-MM-dd HH:mm" />&nbsp;
				</td>
			</tr>
		</c:forEach>	
		</table>
		<div class="pages">
			<xd:pager  pagerFunction="doPage" pagerStyle="cursor:pointer"  curPagerTheme="pages_cur" showTextPager="true"></xd:pager>
		</div>
	   </div>


