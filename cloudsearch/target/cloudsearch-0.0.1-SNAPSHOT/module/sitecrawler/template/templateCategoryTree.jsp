<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div id="menu_reeboxbox_tree" class="left" style="width:20%;">
</div>
<script>
	//访问左边的树
	var parentName = "父节点";
	var parentId = "0";
	var menutree=new dhtmlXTreeObject("menu_reeboxbox_tree","100%","100%",0);
	menutree.setSkin('csh_dhx_skyblue');
	menutree.setImagePath("${pageContext.request.contextPath}/js/dhtmlxTree/codebase/imgs/csh_dhx_skyblue/");
	menutree.loadXML("${pageContext.request.contextPath}/templateCategory/treeXml.do?selectedId=${selectedId}");
	menutree.setOnClickHandler(menu_tonclick);

	var menu_parentId = "0";
	function remAttr(){ 
            $("#addButton").attr("disabled",false);           //去除disabled属性
    }
    function addAttr(){ 
            $("#addButton").attr("disabled",true);           //添加disabled属性
    }
	function menu_tonclick(id){
		parentName = menutree.getItemText(id);
		parentId = id;
		currentLevel = menutree.getLevel(id);
		//获取选中节点的父ID
		var paramValue = id;
		var isRoot = 0;
		if(currentLevel == 1){
			isRoot = 1;
		}else if(currentLevel == 3){
			//paramValue = menutree.getParentId(id) +"," + paramValue;
		}
		var url="${pageContext.request.contextPath}/template/templateContentList.do?categoryId="+paramValue+"&isRoot="+isRoot;
		AT.load("iframepage",url,iframeHeight);
	}
</script>