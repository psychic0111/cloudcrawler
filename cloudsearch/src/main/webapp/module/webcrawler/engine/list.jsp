<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="xd" uri="http://www.xd-tech.com.cn/" %>
<table width="100%"  border="0" cellspacing="0" cellpadding="0">
   	<tr>
        <th width="5%;"><input name="checkbox" type="checkbox" id="checkbox" onclick='$("[type=checkbox]").attr("checked",!!$(this).attr("checked"))' /></th>
        <th width="5%;">编号</th>
        <th width="10%;">搜索引擎</th>
        <th width="10%;">代码</th>
        <th width="10%;">内容类型</th>
        <th width="10%;">媒体类型</th>
        <th width="10%;">是否启用</th>
        <th width="10%;">抓取页数</th>
        <th width="10%;">间隔时间</th>
        <th width="10%;">操作</th>
     </tr>
    <c:forEach items="${list}" var="engine" varStatus="number" >
    	<tr>
    		<td class="TD_Bgcolor1">
    			<input type="checkbox" name="id" value="${engine.id}" />
    		</td>
    		<td class="TD_Bgcolor1">
    			${number.index+1}
    		</td>
    		<td class="TD_Bgcolor1" title="${engine.enginName}">
    		  <c:set var="name" value="${engine.enginName}"/>
	 			<c:choose>  
					<c:when test="${fn:length(engine.enginName) > 20}">  
	   					<span class="txt_color1" style="cursor:pointer;" onclick="openDg('${pageContext.request.contextPath}/engine/edit.do?id=${engine.id}','+ 修改搜索引擎',850,200);" ><c:out value="${fn:substring(engine.enginName, 0, 20)}......" /> </span> 
	 				</c:when>  
					<c:otherwise>  
	  				 	<span class="txt_color1" style="cursor:pointer;"  onclick="openDg('${pageContext.request.contextPath}/engine/edit.do?id=${engine.id}','+ 修改搜索引擎',850,200);" ><c:out value="${engine.enginName}" /> </span> 
					</c:otherwise>  
				</c:choose>  
    		</td>
    		<td class="TD_Bgcolor1">
    			${engine.enginCode}
    		</td>
    		<td class="TD_Bgcolor1">
    			${engine.contentTypeDesc}
    		</td>
    		<td class="TD_Bgcolor1">
    			${engine.mediaType}
    		</td>
    		<td class="TD_Bgcolor1">
    			<c:choose>  
					 <c:when test="${engine.isUse == 1}">  
	 				 	启用
	 				 </c:when>  
					<c:otherwise>
						禁用
					</c:otherwise>  
				</c:choose>  
    		</td>
    		<td class="TD_Bgcolor1">
    			${engine.pages}
    		</td>
    		<td class="TD_Bgcolor1">
    			<c:choose> 
			  	<c:when test="${engine.timeIntervalType == 1}">  
			          ${engine.timeInterval}&nbsp;&nbsp;&nbsp;小时 
				</c:when>  
				<c:otherwise>  
			 		<c:choose> 
			  			<c:when test="${engine.timeInterval % 60 == 0 ||  engine.timeInterval % 60 == 30}">  
			  				${engine.timeInterval / 60}&nbsp;&nbsp;&nbsp;小时 
			  			</c:when>
			  			<c:otherwise>  
					 		${engine.timeInterval }&nbsp;&nbsp;&nbsp;分钟 
						</c:otherwise> 
			  		</c:choose>
				</c:otherwise>  
			</c:choose>  
    		</td>
    		<td class="TD_Bgcolor1">
    			<input  type="button" class="botton_style1"  value="测试" onclick="openDg('${pageContext.request.contextPath}/engine/testEngine.do','+ 测试',550,100);" />
    		</td>
    	</tr>
    </c:forEach>	
</table>
<div class="pages">
	<xd:pager  pagerFunction="doPage" pagerStyle="cursor:pointer"  curPagerTheme="pages_cur" showTextPager="true"></xd:pager>
</div>
