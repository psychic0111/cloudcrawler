<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions" %>
<script type="text/javascript" language="javascript">
$().ready(function() {
	$("#taskForm").validate({
			rules:{
				taskName:{required:true,minlength:2,maxlength:15,remote:{url:'${pageContext.request.contextPath}/task/checkTask.do?id=${task.id}'}},
				click:{required:true,digits:true}
			},
			messages:{
				taskName:{remote:'该任务名称已存在!'}
			}
		}
	);
});
function selectSiteCategory(){
	window.open('${pageContext.request.contextPath}/task/add.jsp');
}
</script>
<form id="taskForm" method="post" 	action="${pageContext.request.contextPath}/task/editDo.do">
	<input type="hidden" name="_token" value="${token}">
	<input type="hidden" name="id" value="${task.id}" id="id"/>
	<table border=0 cellpadding="0" cellspacing="0" width="100%;"  class=".tbody">
		<tr height="30px;" >
			<td class="TD_Bgcolor3" width="30%" >
				<span class="txt_color2"><img src="${pageContext.request.contextPath}/images/star2.png" width="15" height="16" /></span><span class="label">任&nbsp;&nbsp;&nbsp;&nbsp;务&nbsp;&nbsp;&nbsp;&nbsp;名&nbsp;&nbsp;&nbsp;&nbsp;称: <a href="javascript:void(0)" onclick="toolTip(this,'定义任务名称，可以根据该任务的功能或者执行时间进行定义')">[?]</a></span>
			</td>
			<td class="TD_Bgcolor4" width="70%" >
				<input id="taskName" type="text" name="taskName" value = "${task.taskName }" style="width: 210px;" class="inputTxt1" maxlength="100" />
			</td>
		</tr>
		<tr height="30px;" >
			<td class="TD_Bgcolor3" width="30%" >
				<span class="txt_color2"><img src="${pageContext.request.contextPath}/images/star2.png" width="15" height="16" /></span><span class="label">执&nbsp;&nbsp;&nbsp;&nbsp;行&nbsp;&nbsp;&nbsp;&nbsp;频&nbsp;&nbsp;&nbsp;&nbsp;率: <a href="javascript:void(0)" onclick="toolTip(this,'定义任务执行频率，指该任务在那些时间段内进行执行')">[?]</a></span>
			</td>
			<td class="TD_Bgcolor4" width="70%" >
				<select id="execType" name="execType" class="inputTxt1" onchange="weekShow();" style="width: 200px;height:25px;">
					<option value="1" <c:if test="${task.execType == 1}">selected="selected"</c:if> >每天</option>
					<option value="2" <c:if test="${task.execType == 2}">selected="selected"</c:if>>每周</option>
				</select>
			</td>
		</tr>
		<tr id="weekShow" height="30px;" <c:if test="${task.execType == 1}">style="display: none;"</c:if> >
			<td class="TD_Bgcolor3" width="30%" >
				<span class="txt_color2"><img src="${pageContext.request.contextPath}/images/star2.png" width="15" height="16" /></span><span class="label">星&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;期: <a href="javascript:void(0)" onclick="toolTip(this,'定义任务时间，指该任务每周几进行执行')">[?]</a></span>
			</td>
			<td class="TD_Bgcolor4" width="30%" >
				<input type="checkbox" id = "monday" <c:if test="${fn:indexOf(task.weekDays,'2')>-1}"> checked="checked"</c:if> name = "weekDays" value = "2"/>一
				<input type="checkbox" id = "tuesday" <c:if test="${fn:indexOf(task.weekDays,'3')>-1}"> checked="checked"</c:if> name = "weekDays" value = "3"/>二
				<input type="checkbox" id = "thirsday" <c:if test="${fn:indexOf(task.weekDays,'4')>-1}"> checked="checked"</c:if> name = "weekDays" value = "4"/>三
				<input type="checkbox" id = "fourday" <c:if test="${fn:indexOf(task.weekDays,'5')>-1}"> checked="checked"</c:if> name = "weekDays" value = "5"/>四
				<input type="checkbox" id = "friday" <c:if test="${fn:indexOf(task.weekDays,'6')>-1}"> checked="checked"</c:if> name = "weekDays" value = "6"/>五
				<input type="checkbox" id = "saturday" <c:if test="${fn:indexOf(task.weekDays,'7')>-1}"> checked="checked"</c:if> name = "weekDays" value = "7"/>六
				<input type="checkbox" id = "sunday" <c:if test="${fn:indexOf(task.weekDays,'1')>-1}"> checked="checked"</c:if> name = "weekDays" value = "1"/>日
			</td>
		</tr>
		<tr height="30px;" >
			<td class="TD_Bgcolor3" width="30%" >
				<span class="txt_color2"><img src="${pageContext.request.contextPath}/images/star2.png" width="15" height="16" /></span><span class="label">开&nbsp;&nbsp;&nbsp;&nbsp;始&nbsp;&nbsp;&nbsp;&nbsp;时&nbsp;&nbsp;&nbsp;&nbsp;间: <a href="javascript:void(0)" onclick="toolTip(this,'定义任务开始执行时间，指该任务执行的开始时间')">[?]</a></span>
			</td>
			<td class="TD_Bgcolor4" width="70%" >
				<select id="startHour" name="startHour" class="inputTxt1" style="width: 90px;height:30px;">
					<c:forEach items="${hours}" var="hour" varStatus="number" >
						<option value="${hour }" <c:if test="${task.startHour== hour}">selected="selected"</c:if>>${hour }</option>
					</c:forEach>
				</select>
				点
				<select id="startMinute" name="startMinute" class="inputTxt1" style="width: 90px;height:30px;">
					<c:forEach items="${minutes}" var="minute" varStatus="number" >
						<option value="${minute }" <c:if test="${task.startMinute== minute}">selected="selected"</c:if>>${minute }</option>
					</c:forEach>
				</select>
				分
			</td>
		</tr>
		<tr height="30px;" >
			<td class="TD_Bgcolor3" width="30%" >
				<span class="txt_color2"><img src="${pageContext.request.contextPath}/images/star2.png" width="15" height="16" /></span><span class="label">结&nbsp;&nbsp;&nbsp;&nbsp;束&nbsp;&nbsp;&nbsp;&nbsp;时&nbsp;&nbsp;&nbsp;&nbsp;间: <a href="javascript:void(0)" onclick="toolTip(this,'定义任务结束执行时间，指该任务执行的结束时间')">[?]</a></span>
			</td>
			<td class="TD_Bgcolor4" width="70%" >
				<select id="endHour" name="endHour" class="inputTxt1" style="width: 90px;height:30px;">
					<c:forEach items="${hours}" var="hour" varStatus="number" >
						<option value="${hour }" <c:if test="${task.endHour== hour}">selected="selected"</c:if>>${hour }</option>
					</c:forEach>
				</select>
				点
				<select id="endMinute" name="endMinute" class="inputTxt1" style="width: 90px;height:30px;">
					<c:forEach items="${minutes}" var="minute" varStatus="number" >
						<option value="${minute }" <c:if test="${task.endMinute== minute}">selected="selected"</c:if>>${minute }</option>
					</c:forEach>
				</select>
				分
			</td>
		</tr>
		<tr height="30px;" >
			<td class="TD_Bgcolor3" width="30%" >
				<span class="txt_color2"><img src="${pageContext.request.contextPath}/images/star2.png" width="15" height="16" /></span><span class="label">执&nbsp;&nbsp;&nbsp;&nbsp;行&nbsp;&nbsp;&nbsp;&nbsp;频&nbsp;&nbsp;&nbsp;&nbsp;率: <a href="javascript:void(0)" onclick="toolTip(this,'定义任务执行频率，指该任务每个多长时间执行一次')">[?]</a></span>
			</td>
			<td class="TD_Bgcolor4" width="70%" >
				<input id="click" type="text" value = "${task.click}" name="click" style="width: 100px;" class="inputTxt1" maxlength="100" />
				<select id="clickType" name="clickType" class="inputTxt1" style="width: 100px;height:25px;">
					<option value="1" <c:if test="${task.clickType== 1}">selected="selected"</c:if>>小时</option>
					<option value="2" <c:if test="${task.clickType== 2}">selected="selected"</c:if>>分钟</option>
				</select>
			</td>
		</tr>
		<tr height="30px;">
			<td class="TD_Bgcolor3" width="30%" >
				<span class="txt_color2"><img src="${pageContext.request.contextPath}/images/star2.png" width="15" height="16" /></span><span class="label">是&nbsp;&nbsp;&nbsp;&nbsp;否&nbsp;&nbsp;&nbsp;&nbsp;启&nbsp;&nbsp;&nbsp;&nbsp;用: <a href="javascript:void(0)" onclick="toolTip(this,'定义任务是否启用，指该任务是否可用，若启用，则该任务执行；否则，该任务不执行')">[?]</a></span>
			</td>
			<td class="TD_Bgcolor4" width="70%" >
				<select id="isUse" name="isUse" class="inputTxt1" style="width: 140px;height:30px;">
					<option value="1" <c:if test="${task.isUse== 1}">selected="selected"</c:if>>启用</option>
					<option value="0" <c:if test="${task.isUse== 0}">selected="selected"</c:if>>禁用</option>
				</select>
			</td>
		</tr>
		<tr height="30px;">
			<td class="TD_Bgcolor3" width="30%" >
				<span class="txt_color2"><img src="${pageContext.request.contextPath}/images/star2.png" width="15" height="16" /></span><span class="label">采&nbsp;&nbsp;&nbsp;&nbsp;集&nbsp;&nbsp;&nbsp;&nbsp;目&nbsp;&nbsp;&nbsp;&nbsp;标: <a href="javascript:void(0)" onclick="toolTip(this,'定义任务采集目标，指该任务针对那些站点起作用')">[?]</a></span>
			</td>
			<td class="TD_Bgcolor4" width="70%" >
				<select id="isAllSites" name="isAllSites" class="inputTxt1" onchange="changeBlock(this);" style="width: 140px;height:30px;">
					<option value="0" <c:if test="${task.isAllSites== 0}">selected="selected"</c:if>>采集所有站点</option>
					<option value="1" <c:if test="${task.isAllSites== 1}">selected="selected"</c:if>>采集指定站点</option>
				</select>
			</td>
		</tr>
		<tr height="30px;" class="taskHide">
			<td class="TD_Bgcolor3" width="28%" >
				<span class="txt_color2"><img src="${pageContext.request.contextPath}/images/star2.png" width="15" height="16" /></span><span class="label">采集分类下的站点: <a href="javascript:void(0)" onclick="toolTip(this,'定义任务采集分类，该任务下那些分类下的站点可以被执行')">[?]</a></span>
			</td>
			<td class="TD_Bgcolor4" width="70%" >
				<div id = "sitecategorynames" style="display:inline;">
					<c:forEach items="${selectSiteCategoryList}" var="siteCategory" varStatus="number" >
						<input type="checkbox" checked="checked" name="siteCategories" id="${siteCategory.id }" value="${siteCategory.id }"/>${siteCategory.name}
					</c:forEach>
				</div>
				<input  type="button"  class="botton_style3" style="display:inline;" value="选择分类" onclick="showSiteCategoryForm()" />
			</td>
		</tr>
		<tr height="30px;" class="taskHide">
			<td class="TD_Bgcolor3" width="30%" >
				<span class="txt_color2"><img src="${pageContext.request.contextPath}/images/star2.png" width="15" height="16" /></span><span class="label">采&nbsp;&nbsp;&nbsp;&nbsp;集&nbsp;&nbsp;&nbsp;&nbsp;站&nbsp;&nbsp;&nbsp;&nbsp;点: <a href="javascript:void(0)" onclick="toolTip(this,'定义任务采集站点，指该任务下那些站点可以被执行')">[?]</a></span>
			</td>
			<td class="TD_Bgcolor4" width="70%" >
				<div id = "sitenames" style="display:inline;">
					<c:forEach items="${siteList}" var="site" varStatus="number" >
						<input type="checkbox" checked="checked" name="sites" id="${site.id }" value="${site.id }"/>${site.name}
					</c:forEach>
				</div>
				<input  type="button" class="botton_style3" style="display:inline;" value="选择站点" onclick="showSiteForm()" />
			</td>
		</tr>
	</table>
	<div class="function2">
		<input name="" type="button" class="botton_style3" value="保存" onclick="addFrm_submit()" />
		<input name="" type="button" class="botton_style3"	onclick="closeDg()" value="取消" />
	</div>
</form>
<form id="siteCategoryForm" style="display: none;" method="post" 	action="${pageContext.request.contextPath}/task/doAdd.do">
<div style="height: 500px;overflow:auto;">
	<table border=0 cellpadding="0" cellspacing="0" width="100%;"  class=".tbody">
		<c:forEach items="${siteCategoryList}" var="siteCategory" varStatus="number" >
			<c:if test="${number.index % 4 == 0}">
				<tr>
			</c:if>
				<td class="TD_Bgcolor4" width="150px;">
					<c:if test="${siteCategory.id != ''}">
						<input type="checkbox" name="siteName" id="${siteCategory.id}" value="${siteCategory.id},${siteCategory.name}" <c:if test="${ siteCategory.checked==true}">checked</c:if>/>${siteCategory.name}
					</c:if>
				</td>
			<c:if test="${number.index % 4 == 3}">
				</tr>
			</c:if>
		</c:forEach>
	</table>
	</div>
	<div class="function2">
		<input id="selAllSiteCateogory" name="" type="button" class="botton_style3" value="全选" onclick='checkAllSiteName();'/>
		<input name="" type="button" class="botton_style3" value="确定" onclick="siteCategory()" />
		<input name="" type="button" class="botton_style3"	onclick="showTaskForm();" value="取消" />
	</div>
</form>
<form id="siteForm" style="display: none;" method="post" action="${pageContext.request.contextPath}/task/doAdd.do">
<div style="height: 500px;overflow:auto;">
<table border=0 cellpadding="0" cellspacing="0" width="100%;"  class=".tbody">
	<tr>
		<td class="TD_Bgcolor4" width="150px;">
			<div id="menu_reeboxbox_tree" class="left">
			</div>
		</td>
	</tr>
</table>
</div>
<div class="function2">
	<input type="button" class="botton_style3" value="保存" onclick="selectSite()" />
	<input type="button" class="botton_style3"	onclick="showTaskForm();" value="取消" />
</div>
</form>
<script type="text/javascript">
function weekShow(){
	var show = $("#execType").val();
	if(show == "1"){
		$("#weekShow").hide();
	}else{
		$("#weekShow").show();
	}
}
</script>
<script type="text/javascript">
var isAllSites = '${task.isAllSites}';
if (isAllSites == "0"){
	$(".taskHide").hide();
}else if(isAllSites == "1"){
	$(".taskHide").show();
}else{
	$(".taskHide").hide();
}
function changeBlock(sel){
	var sel = $(sel).val();
	if(sel == 0){
		$(".taskHide").hide();
	}else{
		$(".taskHide").show();
	}
}


var menutree=new dhtmlXTreeObject("menu_reeboxbox_tree","100%","100%",0);
menutree.setSkin('csh_dhx_skyblue');
menutree.setImagePath("${pageContext.request.contextPath}/js/dhtmlxTree/codebase/imgs/csh_dhx_skyblue/");
menutree.loadXML("${pageContext.request.contextPath}/task/treeXml.do?sites=${task.sites}");
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
function menu_tonclick(id){
}
var checked = true;
function checkAllSiteName(){
	if($("#selAllSiteCateogory").val() == '全选'){
		$("#selAllSiteCateogory").val("全不选");
	}else{
		$("#selAllSiteCateogory").val("全选");
	}
	var checkboxs = document.getElementsByName("siteName");
	for(var i = 0;i<checkboxs.length;i++){
		checkboxs[i].checked = checked;
	}
	checked = !checked;
}

</script>
