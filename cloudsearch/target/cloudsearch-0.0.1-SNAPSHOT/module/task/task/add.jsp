<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions" %>
<script type="text/javascript" language="javascript">
$().ready(function() {
	$("#taskForm").validate({
			rules:{
				taskName:{required:true,minlength:2,maxlength:15,remote:{url:'${pageContext.request.contextPath}/task/checkTask.do'}},
				click:{required:true,digits:true,min:1}
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
<form id="taskForm" method="post" 	action="${pageContext.request.contextPath}/task/doAdd.do">
	<input type="hidden" name="_token" value="${token}">
	<table border=0 cellpadding="0" cellspacing="0" width="100%;"  class=".tbody">
		<tr height="30px;" >
			<td class="TD_Bgcolor3" width="30%" >
				<span class="txt_color2"><img src="${pageContext.request.contextPath}/images/star2.png" width="15" height="16" /></span><span class="label">任&nbsp;&nbsp;&nbsp;&nbsp;务&nbsp;&nbsp;&nbsp;&nbsp;名&nbsp;&nbsp;&nbsp;&nbsp;称: <a href="javascript:void(0)" onclick="toolTip(this,'任务的名称')">[?]</a></span>
			</td>
			<td class="TD_Bgcolor4" width="70%" >
				<input id="taskName" type="text" name="taskName" style="width: 210px;" class="inputTxt1" maxlength="100" />
			</td>
		</tr>
		<tr height="30px;" >
			<td class="TD_Bgcolor3" width="30%" >
				<span class="txt_color2"><img src="${pageContext.request.contextPath}/images/star2.png" width="15" height="16" /></span><span class="label">开&nbsp;&nbsp;&nbsp;&nbsp;始&nbsp;&nbsp;&nbsp;&nbsp;时&nbsp;&nbsp;&nbsp;&nbsp;间: <a href="javascript:void(0)" onclick="toolTip(this,'任务开始执行的时间')">[?]</a></span>
			</td>
			<td class="TD_Bgcolor4" width="70%" >
				<select id="startHour" name="startHour" class="inputTxt1" style="width: 90px;height:30px;">
					<option value="0">0</option>
					<option value="1">1</option>
					<option value="2">2</option>
					<option value="3">3</option>
					<option value="4">4</option>
					<option value="5">5</option>
					<option value="6">6</option>
					<option value="7">7</option>
					<option value="8">8</option>
					<option value="9">9</option>
					<option value="10">10</option>
					<option value="11">11</option>
					<option value="12">12</option>
					<option value="13">13</option>
					<option value="14">14</option>
					<option value="15">15</option>
					<option value="16">16</option>
					<option value="17">17</option>
					<option value="18">18</option>
					<option value="19">19</option>
					<option value="20">20</option>
					<option value="21">21</option>
					<option value="22">22</option>
					<option value="23">23</option>
				</select>
				点
				<select id="startMinute" name="startMinute" class="inputTxt1" style="width: 90px;height:30px;">
					<option value="0">0</option>
					<option value="1">1</option>
					<option value="2">2</option>
					<option value="3">3</option>
					<option value="4">4</option>
					<option value="5">5</option>
					<option value="6">6</option>
					<option value="7">7</option>
					<option value="8">8</option>
					<option value="9">9</option>
					<option value="10">10</option>
					<option value="11">11</option>
					<option value="12">12</option>
					<option value="13">13</option>
					<option value="14">14</option>
					<option value="15">15</option>
					<option value="16">16</option>
					<option value="17">17</option>
					<option value="18">18</option>
					<option value="19">19</option>
					<option value="20">20</option>
					<option value="21">21</option>
					<option value="22">22</option>
					<option value="23">23</option>
					<option value="24">24</option>
					<option value="25">25</option>
					<option value="26">26</option>
					<option value="27">27</option>
					<option value="28">28</option>
					<option value="29">29</option>
					<option value="30">30</option>
					<option value="31">31</option>
					<option value="32">32</option>
					<option value="32">32</option>
					<option value="34">34</option>
					<option value="35">35</option>
					<option value="36">36</option>
					<option value="37">37</option>
					<option value="38">38</option>
					<option value="39">39</option>
					<option value="40">40</option>
					<option value="41">41</option>
					<option value="42">42</option>
					<option value="43">43</option>
					<option value="44">44</option>
					<option value="45">45</option>
					<option value="46">46</option>
					<option value="47">47</option>
					<option value="48">48</option>
					<option value="49">49</option>
					<option value="50">50</option>
					<option value="51">51</option>
					<option value="52">52</option>
					<option value="53">53</option>
					<option value="54">54</option>
					<option value="55">55</option>
					<option value="56">56</option>
					<option value="57">57</option>
					<option value="58">58</option>
					<option value="59">59</option>
				</select>
				分
			</td>
		</tr>
		<tr height="30px;" >
			<td class="TD_Bgcolor3" width="30%" >
				<span class="txt_color2"><img src="${pageContext.request.contextPath}/images/star2.png" width="15" height="16" /></span><span class="label">结&nbsp;&nbsp;&nbsp;&nbsp;束&nbsp;&nbsp;&nbsp;&nbsp;时&nbsp;&nbsp;&nbsp;&nbsp;间: <a href="javascript:void(0)" onclick="toolTip(this,'任务结束执行的时间')">[?]</a></span>
			</td>
			<td class="TD_Bgcolor4" width="70%" >
				<select id="endHour" name="endHour" class="inputTxt1" style="width: 90px;height:30px;">
					<option value="0">0</option>
					<option value="1">1</option>
					<option value="2">2</option>
					<option value="3">3</option>
					<option value="4">4</option>
					<option value="5">5</option>
					<option value="6">6</option>
					<option value="7">7</option>
					<option value="8">8</option>
					<option value="9">9</option>
					<option value="10">10</option>
					<option value="11">11</option>
					<option value="12">12</option>
					<option value="13">13</option>
					<option value="14">14</option>
					<option value="15">15</option>
					<option value="16">16</option>
					<option value="17">17</option>
					<option value="18">18</option>
					<option value="19">19</option>
					<option value="20">20</option>
					<option value="21">21</option>
					<option value="22">22</option>
					<option value="23" selected="selected">23</option>
				</select>
				点
				<select id="endMinute" name="endMinute" class="inputTxt1" style="width: 90px;height:30px;">
					<option value="0">0</option>
					<option value="1">1</option>
					<option value="2">2</option>
					<option value="3">3</option>
					<option value="4">4</option>
					<option value="5">5</option>
					<option value="6">6</option>
					<option value="7">7</option>
					<option value="8">8</option>
					<option value="9">9</option>
					<option value="10">10</option>
					<option value="11">11</option>
					<option value="12">12</option>
					<option value="13">13</option>
					<option value="14">14</option>
					<option value="15">15</option>
					<option value="16">16</option>
					<option value="17">17</option>
					<option value="18">18</option>
					<option value="19">19</option>
					<option value="20">20</option>
					<option value="21">21</option>
					<option value="22">22</option>
					<option value="23">23</option>
					<option value="24">24</option>
					<option value="25">25</option>
					<option value="26">26</option>
					<option value="27">27</option>
					<option value="28">28</option>
					<option value="29">29</option>
					<option value="30">30</option>
					<option value="31">31</option>
					<option value="32">32</option>
					<option value="32">32</option>
					<option value="34">34</option>
					<option value="35">35</option>
					<option value="36">36</option>
					<option value="37">37</option>
					<option value="38">38</option>
					<option value="39">39</option>
					<option value="40">40</option>
					<option value="41">41</option>
					<option value="42">42</option>
					<option value="43">43</option>
					<option value="44">44</option>
					<option value="45">45</option>
					<option value="46">46</option>
					<option value="47">47</option>
					<option value="48">48</option>
					<option value="49">49</option>
					<option value="50">50</option>
					<option value="51">51</option>
					<option value="52">52</option>
					<option value="53">53</option>
					<option value="54">54</option>
					<option value="55">55</option>
					<option value="56">56</option>
					<option value="57">57</option>
					<option value="58">58</option>
					<option value="59" selected="selected">59</option>
				</select>
				分
			</td>
		</tr>
		<tr height="30px;" >
			<td class="TD_Bgcolor3" width="30%" >
				<span class="txt_color2"><img src="${pageContext.request.contextPath}/images/star2.png" width="15" height="16" /></span><span class="label">执&nbsp;&nbsp;&nbsp;&nbsp;行&nbsp;&nbsp;&nbsp;&nbsp;频&nbsp;&nbsp;&nbsp;&nbsp;率: <a href="javascript:void(0)" onclick="toolTip(this,'任务执行的频率')">[?]</a></span>
			</td>
			<td class="TD_Bgcolor4" width="70%" >
				<input id="click" type="text" name="click" style="width: 100px;" value="1" class="inputTxt1" maxlength="100" />
				<select id="clickType" name="clickType" class="inputTxt1" style="width: 100px;height:25px;">
					<option value="1" selected="selected">小时</option>
					<option value="2">分钟</option>
				</select>
			</td>
		</tr>
		<tr height="30px;">
			<td class="TD_Bgcolor3" width="30%" >
				<span class="txt_color2"><img src="${pageContext.request.contextPath}/images/star2.png" width="15" height="16" /></span><span class="label">是&nbsp;&nbsp;&nbsp;&nbsp;否&nbsp;&nbsp;&nbsp;&nbsp;启&nbsp;&nbsp;&nbsp;&nbsp;用: <a href="javascript:void(0)" onclick="toolTip(this,'任务是否启用')">[?]</a></span>
			</td>
			<td class="TD_Bgcolor4" width="70%" >
				<select id="isUse" name="isUse" class="inputTxt1" style="width: 140px;height:30px;">
					<option value="1">启用</option>
					<option value="0">禁用</option>
				</select>
			</td>
		</tr>
		<tr height="30px;">
			<td class="TD_Bgcolor3" width="30%" >
				<span class="txt_color2"><img src="${pageContext.request.contextPath}/images/star2.png" width="15" height="16" /></span><span class="label">采&nbsp;&nbsp;&nbsp;&nbsp;集&nbsp;&nbsp;&nbsp;&nbsp;目&nbsp;&nbsp;&nbsp;&nbsp;标: <a href="javascript:void(0)" onclick="toolTip(this,'任务执行目标')">[?]</a></span>
			</td>
			<td class="TD_Bgcolor4" width="70%" >
				<select id="isAllSites" name="isAllSites" class="inputTxt1" onchange="changeBlock(this);" style="width: 140px;height:30px;">
					<option value="0">采集所有站点</option>
					<option value="1">采集指定站点</option>
				</select>
			</td>
		</tr>
		<tr height="30px;" class="taskHide">
			<td class="TD_Bgcolor3" width="28%" >
				<span class="txt_color2"><img src="${pageContext.request.contextPath}/images/star2.png" width="15" height="16" /></span><span class="label">采集分类下的站点: <a href="javascript:void(0)" onclick="toolTip(this,'任务采集的站点分类')">[?]</a></span>
			</td>
			<td class="TD_Bgcolor4" width="70%" >
				<div id = "sitecategorynames" style="display:inline;"></div>
				<input  type="button"  class="botton_style3" style="display:inline;" value="选择分类" onclick="showSiteCategoryForm()" />
			</td>
		</tr>
		<tr height="30px;" class="taskHide">
			<td class="TD_Bgcolor3" width="30%" >
				<span class="txt_color2"><img src="${pageContext.request.contextPath}/images/star2.png" width="15" height="16" /></span><span class="label">采&nbsp;&nbsp;&nbsp;&nbsp;集&nbsp;&nbsp;&nbsp;&nbsp;站&nbsp;&nbsp;&nbsp;&nbsp;点: <a href="javascript:void(0)" onclick="toolTip(this,'任务采集的站点')">[?]</a></span>
			</td>
			<td class="TD_Bgcolor4" width="70%" >
				<div id = "sitenames" style="display:inline;"></div>
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
	<table border=0 cellpadding="0" cellspacing="0" width="100%;"  class=".tbody">
		<c:forEach items="${siteCategoryList}" var="siteCategory" varStatus="number" >
			<c:if test="${number.index % 4 == 0}">
				<tr>
			</c:if>
				<td class="TD_Bgcolor4" width="150px;">
					<c:if test="${siteCategory.id != ''}">
						<input type="checkbox" name="siteName" id="${siteCategory.id}" value="${siteCategory.id},${siteCategory.name}"/>${siteCategory.name}
					</c:if>
				</td>
			<c:if test="${number.index % 4 == 3}">
				</tr>
			</c:if>
		</c:forEach>
	</table>
	<div class="function2">
		<input id="selAllSiteCateogory" name="" type="button" class="botton_style3" value="全选" onclick='checkAllSiteName();'/>
		<input name="" type="button" class="botton_style3" value="确定" onclick="siteCategory()" />
		<input name="" type="button" class="botton_style3"	onclick="showTaskForm();" value="取消" />
	</div>
</form>
<form id="siteForm" style="display: none;" method="post" action="${pageContext.request.contextPath}/task/doAdd.do">
<table border=0 cellpadding="0" cellspacing="0" width="100%;"  class=".tbody">
	<tr>
		<td class="TD_Bgcolor4" width="150px;">
			<div id="menu_reeboxbox_tree" class="left">
			</div>
		</td>
	</tr>
</table>
<div class="function2">
	<input type="button" class="botton_style3" value="保存" onclick="selectSite()" />
	<input type="button" class="botton_style3"	onclick="showTaskForm();" value="取消" />
</div>
</form>


<script type="text/javascript">
var isAllSites = '${isAllSites}';
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
	function menu_tonclick(id){
	}
</script>
