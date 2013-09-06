<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions" %>
<%@include file="/include/common.jsp" %>
<div class="lists">
	<div class="function">
    </div>
</div>
<table border=0 cellpadding="0" cellspacing="0" width="100%;"  class=".tbody">
	<c:forEach items="${siteCategoryList}" var="siteCategory" varStatus="number" >
		<input type="checkbox" name="siteName" id="${siteCategory.id}" value="${siteCategory.id},${siteCategory.name}"/>${siteCategory.name}
	</c:forEach>
</table>
<br/>
<div class="function2">
	<input name="aa" id="button1"  type="button" class="botton_style3" value="全选" onclick='checkAllSiteName(true);'/>
	<input name="" type="button" class="botton_style3" value="反选" onclick='checkAllSiteName(false);'/>
	<input name="" type="button" class="botton_style3" value="确定" onclick="siteCategory()" />
	<input name="" type="button" class="botton_style3"	onclick="window.close()" value="取消" />
</div>
<script type="text/javascript" language="javascript">
	function siteCategory(){
		var checkboxs = document.getElementsByName("siteName");
		var str = "";
		for(var i = 0;i<checkboxs.length;i++){
			if(checkboxs[i].checked == true){
				if(str != ""){
					str += "|";
				}
				str += checkboxs[i].value;
			}
		}
		window.returnValue = str;  
		window.close();
	}
	function checkAllSiteName(checked){
		var checkboxs = document.getElementsByName("siteName");
		for(var i = 0;i<checkboxs.length;i++){
			checkboxs[i].checked = checked;
		}
	}
</script>
