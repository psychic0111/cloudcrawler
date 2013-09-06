<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions" %>
<script type="text/javascript" language="javascript">
	
	//添加任务
	function addFrm_submit(){
		var sel = $("#isAllSites").val();
		if(sel==1){
			var popmessage = true;
			$("input[name='siteCategories']").each(function(i){
				if($(this).attr('checked')=='checked'){
					popmessage = false;
				}
			});
			$("input[name='sites']").each(function(i){
				if($(this).attr('checked')=='checked'){
					popmessage = false;
				}
			});
			
			if(popmessage){
				MSG.alert('请选择分类或者选择站点!');
				return false;
			}
		}
		
		var endHour = $("#endHour").val();
		var startHour = $("#startHour").val();
		if(parseInt(startHour)>parseInt(endHour)){
			MSG.alert('结束时间应该晚于开始时间!');
			return false;
		}else if(parseInt(startHour) == parseInt(endHour)){
			var endMin = $("#endMinute").val();
			var startMin = $("#startMinute").val();
			if(parseInt(startMin) >= parseInt(endMin)){
				MSG.alert('结束时间应该晚于开始时间!');
				return false;
			}
		}
		
		AT.postFrm("taskForm",function(data){
			$("#iframepage").load("${pageContext.request.contextPath}/task/taskList.do?num="+Math.random(),iframeHeight);	//刷新内容列表
			closeDg();
		},true);
	}
	//修改任务
	function eidtFrm_submit(){
		var endHour = $("#endHour").val();
		var startHour = $("#startHour").val();
		if(parseInt(startHour)>parseInt(endHour)){
			MSG.alert('结束时间应该晚于开始时间!');
			return false;
		}else if(parseInt(startHour) == parseInt(endHour)){
			var endMin = $("#endMinute").val();
			var startMin = $("#startMinute").val();
			if(parseInt(startMin) >= parseInt(endMin)){
				MSG.alert('结束时间应该晚于开始时间!');
				return false;
			}
		}
		AT.postFrm("taskEditForm",function(data){
			$("#iframepage").load("${pageContext.request.contextPath}/task/taskList.do?num="+Math.random(),iframeHeight);	//刷新内容列表
			closeDg();
		},true);
	}
	//删除任务
	function delTask(){
		var $checked = checkAll();
		if($checked.length==0){
			MSG.alert('请选择要删除的任务!');
		}else{
			MSG.confirm('确认要删除选中的任务吗？',null,function(){
				AT.post("${pageContext.request.contextPath}/task/del.do",$checked.serialize(),function(data){
					AT.load("iframepage","${pageContext.request.contextPath}/task/taskList.do",iframeHeight);
				});
			},null);
		}
	}
	function doPage(ths,pageNo,pageSize){
		AT.load("iframepage","${pageContext.request.contextPath}/task/taskList.do?pageNo="+pageNo+"&pageSize="+pageSize+"&taskName="+$("#taskName").val(),iframeHeight);
	}
	function searchFrm_submit(){
		AT.postFrm("searchTask",function(data){
			$("#iframepage").html("");
			$("#iframepage").html(data);
			iframeHeight();
			closeDg();
		},true);
	}
	
	//启用监控词
function useTask(){
	$checked = $("[type=checkbox]:checked");
	if($checked.length==0){
		MSG.alert('请选择要启用的任务!');
	}else{
		MSG.confirm('确认要启用选中的任务吗？',null,function(){
			AT.post("${pageContext.request.contextPath}/task/use.do",$checked.serialize(),function(data){
				AT.load("iframepage","${pageContext.request.contextPath}/task/taskList.do?num="+Math.random(),iframeHeight);
			});
		},null);
	}
	
}
//禁用监控词
function noUseTask(){
	$checked = $("[type=checkbox]:checked");
	if($checked.length==0){
		MSG.alert('请选择要禁用的任务!');
	}else{
		MSG.confirm('确认要禁用选中的任务吗？',null,function(){
			AT.post("${pageContext.request.contextPath}/task/noUse.do",$checked.serialize(),function(data){
				AT.load("iframepage","${pageContext.request.contextPath}/task/taskList.do?num="+Math.random(),iframeHeight);
			});
		},null);
	}
}
$(function () {
	  $("*").each(function () {
	   $(this).keypress(function (e) {
	    var key = window.event ? e.keyCode : e.which;
	    if (key.toString() == "13") {
	     return false;
	    }
	   });
	  });
	 });
	 
function showSiteCategoryForm(){
	$("#taskForm").hide();
	$("#siteCategoryForm").show();
	$("#siteForm").hide();
}
function showTaskForm(){
	$("#taskForm").show();
	$("#siteCategoryForm").hide();
	$("#siteForm").hide();
}
function showSiteForm(){
	$("#siteForm").show();
	$("#taskForm").hide();
	$("#siteCategoryForm").hide();
}
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
	var html = "";
	if(str!=""){
		var array = str.split("|");
		for(var i = 0;i<array.length;i++){
			var sitecategory = array[i];
			var idandname = sitecategory.split(",");
			var sitecategoryid = idandname[0];
			var sitecategoryname = idandname[1];
			html += "<input type=\"checkbox\" checked=\"checked\" name=\"siteCategories\" id=\""+sitecategoryid+"\" value=\""+sitecategoryid+"\"/>"+sitecategoryname;
		}
	}
	$("#sitecategorynames").html(html);
	showTaskForm();
}
function selectSite(){
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
	if(str != ""){
		var sitenames = document.getElementById("sitenames");
		var array = str.split("|");
		var html = "";
		for(var i = 0;i<array.length;i++){
			var site = array[i];
			var idandname = site.split(",");
			var siteid = idandname[0];
			var sitename = idandname[1];
			html += "<input type=\"checkbox\" checked=\"checked\" name=\"sites\" id=\""+siteid+"\" value=\""+siteid+"\"/>"+sitename;
		}
		$("#sitenames").html(html);
	}else{
		$("#sitenames").html("");
	}
	
	showTaskForm();
}
</script>
<div class="title">
  <table border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td class="c1"><img src="${pageContext.request.contextPath}/images/title_c_left.png" width="3" height="34" /></td>
      <td class="c2"><img src="${pageContext.request.contextPath}/images/index.png"  /> 任务管理</td>
      <td class="cright"><img src="${pageContext.request.contextPath}/images/page.png" /><a class="link4" href="#">查看当前页操作说明</a></td>
      <td class="c3"><img src="${pageContext.request.contextPath}/images/title_c_right.png" width="3" height="34" /></td>
    </tr>
  </table>
</div>
<div class="lists">
	<div class="function">
	<form id="searchTask" class="indexForm" style="margin:0px;padding:0px;" action="${pageContext.request.contextPath}/task/taskList.do" method="post">
      <div class="left">
        <input  type="button" class="botton_style1" style="display:inline;" value="添加" onclick="openDg('${pageContext.request.contextPath}/task/add.do','+ 添加任务管理',600,100);" />
        <input  type="button" class="botton_style2" style="display:inline;" value="删除" onclick="delTask()"  />
      	<input  type="button" class="botton_style3" value="启用" onclick="useTask()"  />
        <input  type="button" class="botton_style3" value="禁用" onclick="noUseTask()"  />
      </div>
      <div class="right">
      	 <input type='hidden' name='pageNo'  id='_pageNo' value='${ pageResult.pageNo}'/>
      	 <input type='hidden' name='pageSize' id="_pageSize" value='${ pageResult.pageSize}'/>
      	 <span>任务名称：</span>
         <input id="taskName" type="text" name="taskName" style="width: 140px;display:inline;" value="${task.taskName }" class="inputTxt1" maxlength="100" />
         <input  type="button" class="botton_style3" style="display:inline;" value="检索" onclick="searchFrm_submit();" />
      </div>
      <div class="clear"></div>
     </form>
    </div>
	<div id="listDiv">
		<div class="table_list" id="iframepage">
			<jsp:include page="/module/task/task/list.jsp"></jsp:include>
		</div>
	</div>
</div>

