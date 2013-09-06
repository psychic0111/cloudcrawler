<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div id="menu_reeboxbox_tree" class="left" style="width:200px;height:500px;overflow:auto;">
</div>
<script>
	//访问左边的树
	var parentName = "父节点";
	var parentId = "0";
	var menutree=new dhtmlXTreeObject("menu_reeboxbox_tree","100%","100%",0);
	menutree.setSkin('csh_dhx_skyblue');
	menutree.setImagePath("${pageContext.request.contextPath}/js/dhtmlxTree/codebase/imgs/csh_dhx_skyblue/");
	menutree.loadXML("${pageContext.request.contextPath}/region/treeXml.do?selectedId=${selectedId}");
	menutree.setOnClickHandler(menu_tonclick);
	var menu_parentId = "0";
	function menu_tonclick(id){
		parentName = menutree.getItemText(id);
		parentId = id;
		var url="${pageContext.request.contextPath}/region/regionListTree.do?id="+id;
		AT.load("iframepage",url,iframeHeight);
	}
	
</script>