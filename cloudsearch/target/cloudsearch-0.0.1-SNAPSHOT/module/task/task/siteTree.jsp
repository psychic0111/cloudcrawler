<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions" %>
<link rel="STYLESHEET" type="text/css" href="${pageContext.request.contextPath}/js/dhtmlxTree/codebase/dhtmlxtree.css"/>
<script src="${pageContext.request.contextPath}/js/dhtmlxTree/codebase/dhtmlxcommon.js"></script>
<script src="${pageContext.request.contextPath}/js/dhtmlxTree/codebase/dhtmlxtree.js"></script>
<%@include file="/include/common.jsp" %>
</head>
<body style="text-align: left" >
<div id="menu_reeboxbox_tree" class="left">
</div>
<br/>
<br/>
<div class="function2">
	<input type="button" class="botton_style3" value="保存" onclick="checkedSite()" />
	<input type="button" class="botton_style3"	onclick="window.close()" value="取消" />
</div>
<script>
	//访问左边的树
	var menutree=new dhtmlXTreeObject("menu_reeboxbox_tree","100%","100%",0);
	menutree.setSkin('csh_dhx_skyblue');
	menutree.setImagePath("${pageContext.request.contextPath}/js/dhtmlxTree/codebase/imgs/csh_dhx_skyblue/");
	menutree.loadXML("${pageContext.request.contextPath}/task/treeXml.do");
	menutree.setCheck(menu_tonclick);
	menutree.setOnCheckHandler(check_click);
	menutree.enableCheckBoxes(true);
	function check_click(id){
		var childrenstring = menutree.getAllSubItems(id);
		var children = childrenstring.split(",");
		for(var i = 0;i<children.length;i++){
			menutree.setCheck(children[i],menutree.isItemChecked(id));
		}
	}
	function checkedSite(){
		var idstring = menutree.getAllChecked();
		var str = "";
		if("" != idstring){
			var ids = idstring.split(",");
			for(var i = 0;i<ids.length;i++){
				if(menutree.getLevel(ids[i]) != 1){
					if(str != ""){
						str += "|";
					}
					str += ids[i]+","+menutree.getItemText(ids[i]);
				}
			}
		}
		window.returnValue = str;  
		window.close();
	}
	function menu_tonclick(id){
	}
</script>
</body>
</html>