<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="xd" uri="http://www.xd-tech.com.cn/" %>
<div class="function">
    <form id="searchTemplateCategory" name="searchTemplateCategory" class="indexForm"  action="${pageContext.request.contextPath}/templateCategory/templateCategoryContentList.do" method="post">
    <input type="hidden" name="parentId" id="parentId" value="${templateCategory.parentId}" />
       <div class="left">
         <input id="addButton" type="button" class="botton_style1" value="添加" onclick="openDg('${pageContext.request.contextPath}/templateCategory/add.do?parentId=${templateCategory.parentId}','+ 添加模板分类',600,100);" />
         <script>
         	if(currentLevel == 3){
         		$("#addButton").attr("disabled",true); 
         	}else{
         		$("#addButton").attr("disabled",false); 
         	}
	       </script>
         <input  type="button" class="botton_style2" value="删除" onclick="delTemplateCategory()"  />
       </div>
       <div class="right">
      	  <span>名称：</span>
          <input id="name" type="text" name="name" style="width: 140px;display:inline;" value="${templateCategory.name}" class="inputTxt1"/>
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
    <c:forEach items="${list}" var="templateCategory" varStatus="number" >
    	<tr>
    		<td class="TD_Bgcolor1">
    			<input type="checkbox" name="id" value="${templateCategory.id}" />
    		</td>
    		<td class="TD_Bgcolor1">
    			${number.index+1}
    		</td>
    		<td class="TD_Bgcolor1" title="${templateCategory.name}">
    		  <c:set var="name" value="${templateCategory.name}"/>
	 			<c:choose>  
					 <c:when test="${fn:length(templateCategory.name) > 10}">  
   						  <span class="txt_color1" style="cursor:pointer;" onclick="openDg('${pageContext.request.contextPath}/templateCategory/edit.do?id=${templateCategory.id}','+ 修改模板分类',600,100);" ><c:out value="${fn:substring(templateCategory.name, 0, 10)}......" /> </span> 
	 				 </c:when>  
					<c:otherwise>  
	  				 	<span class="txt_color1" style="cursor:pointer;"  onclick="openDg('${pageContext.request.contextPath}/templateCategory/edit.do?id=${templateCategory.id}','+ 修改模板分类',600,100);" ><c:out value="${templateCategory.name}" /> </span> 
					</c:otherwise>  
				</c:choose>  
    		</td>
    	</tr>
    </c:forEach>	
</table>
<div class="pages">
	<xd:pager  pagerFunction="doPage" pagerStyle="cursor:pointer"  curPagerTheme="pages_cur" showTextPager="true"></xd:pager>
</div>

