<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="xd" uri="http://www.xd-tech.com.cn/" %>
<%--
日志管理-添加
@author WangWei
2013-08-08
--%>
<table width="100%"  border="0" cellspacing="0" cellpadding="0">
    <tr>
       <th width="5%;">编号</th>
       <th width="35%;">地址</th>
       <th width="20%;">站点名称</th>
       <th width="5%;">状态</th>
       <th width="20%;">爬取时间</th>
    </tr>
    <c:forEach items="${list}" var="crawlerlog" varStatus="number" >
    	<tr>
    		<td class="TD_Bgcolor1">
    			${number.index+1}
    		</td>
	        <td align="left" class="TD_Bgcolor1">
    			${crawlerlog.url}
    		</td>
	        <td align="left" class="TD_Bgcolor1">
    			${crawlerlog.siteName}
    		</td>
	        <td class="TD_Bgcolor1">
	        	<c:if test="${crawlerlog.status == 1}">成功</c:if>
    			<c:if test="${crawlerlog.status != 1}">失败</c:if>
    		</td>
	        <td class="TD_Bgcolor1">
	        	<fmt:formatDate value ="${crawlerlog.crawlerTime}" pattern="yyyy-MM-dd HH:mm" />
    		</td>
    	</tr>
    </c:forEach>	
</table>
<div class="pages">
	<xd:pager  pagerFunction="doPage" pagerStyle="cursor:pointer"  curPagerTheme="pages_cur" showTextPager="true"></xd:pager>
</div>
