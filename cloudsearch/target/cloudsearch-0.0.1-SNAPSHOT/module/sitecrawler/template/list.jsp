<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="xd" uri="http://www.xd-tech.com.cn/" %>
<div class="function">
	<form id="searchTemplate" class="indexForm"  action="${pageContext.request.contextPath}/template/templateContentList.do" method="post">
       <input type="hidden" name="isRoot" id="isRoot" value="0" />
       <div class="left">
         <input  type="button" class="botton_style1" value="添加" onclick="openDg('${pageContext.request.contextPath}/template/add.do?categoryId=${categoryId }','+ 添加模板',600,200);" />
         <input  type="button" class="botton_style2" value="删除" onclick="delTemplate();"  />
       </div>
       <div class="right">
               	<span style="display:inline;">所属分类：</span>
              		<select id="fristLevel" class="inputTxt1" style="width: 120px;"  name="categoryId" >
					<option value="">请选择...</option>
					<c:forEach items="${fristCategory}" var="tc" varStatus="status">
						<option value = "${tc.id }" <c:if test="${status.index%2==0}">style="background: #ffffff;"</c:if> <c:if test="${status.index%2==0}">style="background: #d5dbdf;"</c:if>  <c:if test="${fn:indexOf(template.categoryId,tc.id)>-1}">selected="selected"</c:if> >${tc.name }</option>
						<c:forEach items="${tc.childList}" var="child" >
							<option value = "${child.id }" <c:if test="${status.index%2==0}">style="background: #ffffff;"</c:if> <c:if test="${status.index%2==0}">style="background: #d5dbdf;"</c:if>  <c:if test="${fn:indexOf(template.categoryId,child.id)>-1}">selected="selected"</c:if> >&nbsp;&nbsp;&nbsp;${child.name }</option>
						</c:forEach>
					</c:forEach>
				</select>
        			<span style="display:inline;">模板名称：</span><input id="name" type="text" name="name" style="width: 100px;display:inline;" value="${template.name}" class="inputTxt1" maxlength="100" />
               	<input  type="button" class="botton_style3" style="display:inline;" value="检索" onclick="searchFrm_submit();" />
         </div>
       <div class="clear"></div>
      </form>
    </div>
	<table width="100%"  border="0" cellspacing="0" cellpadding="0">
       	<tr>
            <th width="10%;"><input name="checkbox" type="checkbox" id="checkbox" onclick='$("[type=checkbox]").attr("checked",!!$(this).attr("checked"))' /></th>
            <th width="10%;">编号</th>
            <th width="20%;">模板名称</th>
            <th width="20%;">所属分类</th>
            <th width="20%;">地址匹配</th>
            <th width="10%;">站点数</th>
            <th width="10%;">操作人</th>
         </tr>
         <c:forEach items="${list}" var="template" varStatus="number" >
         	<tr>
         		<td class="TD_Bgcolor1">
         			<input type="checkbox" name="id" value="${template.id}" />
         		</td>
         		<td class="TD_Bgcolor1">
         			${number.index+1}
         		</td>
         		<td class="TD_Bgcolor1" title="${template.name}">
         		  <c:set var="name" value="${template.name}"/>
 					 <c:choose>  
    				 <c:when test="${fn:length(template.name) > 20}">  
        				<span class="txt_color1" style="cursor:pointer;" onclick="openDg('${pageContext.request.contextPath}/template/edit.do?id=${template.id}','+ 修改模板',600,200);" ><c:out value="${fn:substring(template.name, 0, 20)}......" /> </span> 
      				 </c:when>  
    				<c:otherwise>  
      				 	<span class="txt_color1" style="cursor:pointer;"  onclick="openDg('${pageContext.request.contextPath}/template/edit.do?id=${template.id}','+ 修改模板',600,200);" ><c:out value="${template.name}" /> </span> 
   					</c:otherwise>  
  				</c:choose>  
         		</td>
         		<td class="TD_Bgcolor1">
         			${template.categoryName}
         		</td>
         		<td class="TD_Bgcolor1" >	
         			<c:choose>
       				<c:when test="${fn:length(template.urlRule) >15}">
       					<c:out value="${fn:substring(template.urlRule, 0 ,15)}..."></c:out>
       				</c:when>
         			<c:otherwise>
         				<c:out value="${template.urlRule}"></c:out>
         			</c:otherwise>
         			</c:choose>
         		</td>
         		<td class="TD_Bgcolor1">	
         			${template.sites}
         		</td>
         		<td class="TD_Bgcolor1">
         			${template.operateUser}
         		</td>
         	</tr>
         </c:forEach>	
</table>
<div class="pages">
	<xd:pager  pagerFunction="doPage" pagerStyle="cursor:pointer"  curPagerTheme="pages_cur" showTextPager="true"></xd:pager>
</div>
