<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions" %>
<script type="text/javascript">
$().ready(function() {
	$("#addSiteForm").validate({
			rules:{
				name:{required:true,minlength:2,maxlength:50,remote:{url:"${pageContext.request.contextPath}/site/checkSite.do?id="+$("#id").val()}},
				inUrl:{required:true,url:true,maxlength:500},
				addressRule:{maxlength:200},
				urlReg:{required:true,maxlength:200}
			},
			messages:{
				name:{remote:'该站点已存在!'}
			}
		}
	);
});
</script>
<table border=0 cellpadding="0" cellspacing="0" width="100%;" class=".tbody">
	<tr height="30px;" >
			<td class="TD_Bgcolor3" width="25%" >
				<span class="txt_color2"><img src="${pageContext.request.contextPath}/images/star2.png" width="15" height="16" /></span><span class="label">站点名称: <a href="javascript:void(0)" onclick="toolTip(this,'定义站点名称，根据目标网站的名称或者内容来定义站点名称')">[?]</a></span>
		</td>
		<td class="TD_Bgcolor4" width="80%" >
			<input id="nameSiteId" type="text" name="name" value="${site.name }" style="width: 257px;" class="inputTxt1" maxlength="100" />
		</td>
	</tr>
	<tr height="30px;" >
		<td class="TD_Bgcolor3" width="25%" >
			<span class="txt_color2"><img src="${pageContext.request.contextPath}/images/star2.png" width="15" height="16" /></span><span class="label">站点分类: <a href="javascript:void(0)" onclick="toolTip(this,'选择站点分类，根据目标网站的内容定义本站点所属分类')">[?]</a></span>
		</td>
		<td class="TD_Bgcolor4" width="80%" >
			<select id="siteCategoryId" class="inputTxt1" style="width: 264px;"  name="siteCategoryId" >
				<c:forEach items="${siteCategory}" var="sc" varStatus="status">
					<option value = "${sc.id }" <c:if test="${fn:indexOf(site.siteCategoryId,sc.id)>-1}">selected="selected"</c:if> >${sc.name }</option>
				</c:forEach>
			</select>
		</td>
	</tr>
	<tr height="30px;" >
		<td class="TD_Bgcolor3" width="25%" >
			<span class="label">站点类型: <a href="javascript:void(0)" onclick="toolTip(this,'选择站点类型，为目标网站选择所属类型')">[?]</a></span>
		</td>
		<td class="TD_Bgcolor4" width="80%" >
			<input id="webType0" type="radio" name="webType" value="0" <c:if test="${site.webType==0 }">checked="checked"</c:if>/>新闻
			<input id="webType1" type="radio" name="webType" value="1" <c:if test="${site.webType==1 }">checked="checked"</c:if>/>论坛
			<input id="webType2" type="radio" name="webType" value="2" <c:if test="${site.webType==2 }">checked="checked"</c:if>/>博客
			<input id="webType3" type="radio" name="webType" value="3" <c:if test="${site.webType==3 }">checked="checked"</c:if>/>其他
		</td>
	</tr>
	<tr height="30px;" >
		<td class="TD_Bgcolor3" width="25%" >
			<span class="label">采集深度: <a href="javascript:void(0)" onclick="toolTip(this,'定义本站点采集深度，入口地址深度为0，由入口地址解析到的地址深度为1，由深度为一的地址解析到的地址深度为2，依次类推')">[?]</a></span>
		</td>
		<td class="TD_Bgcolor4" width="80%" >
			<select id="crawlerDeep" class="inputTxt1" style="width: 264px;"  name="crawlerDeep" >
				<option value="1" <c:if test="${site.crawlerDeep==1 }">selected="selected"</c:if>>1</option>
				<option value="2" <c:if test="${site.crawlerDeep==2 }">selected="selected"</c:if>>2</option>
				<option value="3" <c:if test="${site.crawlerDeep==3 }">selected="selected"</c:if>>3</option>
				<option value="4" <c:if test="${site.crawlerDeep==4 }">selected="selected"</c:if>>4</option>
				<option value="5" <c:if test="${site.crawlerDeep==5 }">selected="selected"</c:if>>5</option>
			</select>
		</td>
	</tr>
	<tr height="30px;" >
		<td class="TD_Bgcolor3" width="25%" >
			<span class="label">优&nbsp;&nbsp;先&nbsp;级: <a href="javascript:void(0)" onclick="toolTip(this,'定义本站点优先级，爬虫在采集数据的过程中，优先级高的站点先采集，优先级低的站点后采集')">[?]</a></span>
		</td>
		<td class="TD_Bgcolor4" width="80%" >
			<input id="level0" type="radio" name="level" value="0" <c:if test="${site.level=='0' }">checked="checked"</c:if>/>高
			<input id="level1" type="radio" name="level" value="1" <c:if test="${site.level=='1' }">checked="checked"</c:if>/>中
			<input id="level2" type="radio" name="level" value="2" <c:if test="${site.level=='2' }">checked="checked"</c:if>/>低
		</td>
	</tr>
	<tr height="30px;" >
			<td class="TD_Bgcolor3" width="25%" >
				<span class="txt_color2"><img src="${pageContext.request.contextPath}/images/star2.png" width="15" height="16" /></span><span class="label">采集入口地址: <a href="javascript:void(0)" onclick="toolTip(this,'定义站点采集入口地址，根据目标网站的访问地址定义入口地址，一般为该网站的列表访问地址')">[?]</a></span>
		</td>
		<td class="TD_Bgcolor4" width="80%" >
			<input id="inUrl" type="text" name="inUrl" value="${site.inUrl}" style="width: 257px;" class="inputTxt1" maxlength="100" />
		</td>
	</tr>
	<tr height="30px;" >
		<td class="TD_Bgcolor3" width="25%" valign="top" >
			<span class="label">动态入口地址: <a href="javascript:void(0)" onclick="toolTip(this,'定义站点动态规则，动态入口规则根据目标网站的一些规律（分页，类型）来产生一个或多个入口地址')">[?]</a></span>
		</td>
		<td class="TD_Bgcolor4" width="80%" >
			<div id="leftDiv" style="float:left;display:inline;width:280px;"><textarea id="dynamicRule" name="dynamicRule" rows="5" cols="29" class="textarea" >${site.dynamicRule }</textarea></div>
			<div id="rightDiv" style="float:left;display:inline;">
				<div id="topDiv">
					<a href="#" onclick="openTestResult();" style="color: #97B859; size: 14px;" >测试</a>
					<a href="${pageContext.request.contextPath}/module/sitecrawler/site/help.html" style="color: #97B859; size: 14px;"  target="_blank">帮助</a>
				</div>
				<div id="bottomDiv">
					<span>根据此规则会产生新的<br>入口地址</span>
					<br>
					<span>多个地址间以换行分隔</span>
				</div>
			</div>
		</td>
	</tr>
	<tr height="30px;" >
		<td class="TD_Bgcolor3" width="25%" >
				<span class="label">采集地址区域: <a href="javascript:void(0)" onclick="toolTip(this,'定义本站点采集地址区域，采集地址区域是为了精确从目标网站上采集页面而设置的属性，即指定要采集的页面url列表在入口地址页面所在区域，通常是该区域的html标签和属性选择器或正则表达式')">[?]</a></span>
		</td>
		<td class="TD_Bgcolor4" width="80%" >
			<input id="addressRule" type="text" name="addressRule" value="${site.addressRule}" style="width: 257px;" class="inputTxt1" maxlength="100" />
		</td>
	</tr>
	<tr height="30px;" >
		<td class="TD_Bgcolor3" width="25%" >
			<span class="txt_color2"><img src="${pageContext.request.contextPath}/images/star2.png" width="15" height="16" /></span><span class="label">采集地址规则: <a href="javascript:void(0)" onclick="toolTip(this,'定义站点采集地址规则，采集地址规则是指爬虫采集采集页面时用来筛选要采集的地址url规则，对于不符合该采集规则的地址将不予采集')">[?]</a></span>
		</td>
		<td class="TD_Bgcolor4" width="80%" >
			<input id="urlReg" type="text" name="urlReg" value="${site.urlReg }" style="width: 257px;" class="inputTxt1" maxlength="100" />&nbsp;&nbsp;多个规则之间用空格分隔
		</td>
	</tr>
	
	<tr height="30px;" >
		<td class="TD_Bgcolor3" width="25%" >
			<span class="label">采集模板: <a href="javascript:void(0)" onclick="toolTip(this,'选择站点采集模板，采集模板是指爬虫在采集数据的过程中，对当前地址采集的数据采用对应的模板进行解析，模板可以选择多个')">[?]</a></span>
		</td>
		<td class="TD_Bgcolor4" width="80%" >
			<select id="fristLevel" class="inputTxt1" style="width: 130px;" onchange="changeTemplate($(this).val());"  name="categoryId" >
				<option value=""></option>
				<c:forEach items="${fristCategory}" var="tc" varStatus="status">
					
					<option value = "${tc.id }" <c:if test="${status.index%2==0}">style="background: #ffffff;"</c:if> <c:if test="${status.index%2==0}">style="background: #d5dbdf;"</c:if>  <c:if test="${fn:indexOf(template.categoryId,tc.id)>-1}">selected="selected"</c:if> >${tc.name }</option>
					<c:forEach items="${tc.childList}" var="child" >
						<option value = "${child.id }" <c:if test="${status.index%2==0}">style="background: #ffffff;"</c:if> <c:if test="${status.index%2==0}">style="background: #d5dbdf;"</c:if>  <c:if test="${fn:indexOf(template.categoryId,child.id)>-1}">selected="selected"</c:if> >&nbsp;&nbsp;&nbsp;${child.name }</option>
					</c:forEach>
				</c:forEach>
			</select>
			
			<select id="templateIds" class="inputTxt1" style="width: 130px;" onchange="templateChange($(this).val(),$(this).find('option:selected').text());" name="templateIds" >
				<option value=""></option>
				<c:forEach items="${templates}" var="t" varStatus="status">
					<option value = "${t.id }" <c:if test="${fn:indexOf(site.templateIds,t.id)>-1}">selected="selected"</c:if>>${t.name }</option>
				</c:forEach>
			</select>
			<input type="button" class="botton_style3" value="自定义模板" onclick="changeTab($('#gatherTemplate'),'${pageContext.request.contextPath}/field/list.do?nested=true',3)"/>
		</td>
	</tr>
	<tr id="templates" height="30px;" <c:if test="${site.templateIds =='' }">style="display:none;"</c:if> >
		<td class="TD_Bgcolor3" width="25%" >
			<span class="label">已有模板: <a href="javascript:void(0)" onclick="toolTip(this,'站点已有模板，已经应用于本站点的模板')">[?]</a></span>
		</td>
		<td class="TD_Bgcolor4" width="80%" >
			<div id="templatesDiv">
				<c:forEach items="${site.templates}" var="t" varStatus="status">
					<input id="templateIds" type="checkbox" name="templateIds" value="${t.id }" checked="checked"/>${t.name }
				</c:forEach>
			</div>
		</td>
	</tr>
	
	<tr height="30px;" >
		<td class="TD_Bgcolor3" width="25%" >
			<span class="label">站点状态: <a href="javascript:void(0)" onclick="toolTip(this,'定义站点状态，站点状态是指爬虫在采集数据的过程中，是否需要采集该站点，如果该属性的值为启用，则采集；否则，不予采集')">[?]</a></span>
		</td>
		<td class="TD_Bgcolor4" width="80%" >
			<input id="statue1" type="radio" name="statue" value="1" <c:if test="${site.statue==1 }">checked="checked"</c:if>/>启用
			<input id="statue0" type="radio" name="statue" value="0" <c:if test="${site.statue==0 }">checked="checked"</c:if>/>禁用
		</td>
	</tr>
</table>
<script type="text/javascript">
function changeTemplate(tcid){   
	var url = '${pageContext.request.contextPath}/template/getTemplateByTCId.do?tcid='+tcid;
    AT.post(url,null,function(data){
         if (data != null && data != "" &&  data != undefined)   
         {   
	         $("#templateIds").empty();   
	         // 转换成json对象   
	         var option = "";
	         option += "<option></option>";
             // 循环组装下拉框选项   
	         $.each(data, function(k, v)   
	         {   
         		option += "<option value=\"" + v.id + "\">" + v.name + "</option>";
	         }); 
	         $("#templateIds").append(option); 
         }else{
	         $("#templateIds").empty(); 
         }
    });
}
function templateChange(tid,tname){
	if(""!=tid){
		var add = true;
		$("input[name='templateIds']").each(function(i){
			if($(this).val()==tid){
				add=false;
			}
		});
		if(add){
			$("#templates").show();
			$("#templatesDiv").append("<input id=\"templateIds\" type=\"checkbox\" name=\"templateIds\" value=\""+tid+"\" checked=\"checked\"/>"+tname+"");
		}
	}
}
function openTestResult(){
	var param = $("#dynamicRule").val();
	if("" == param){
		MSG.alert('动态地址不能为空！');
		return;
	}
	$("#rule").val($("#dynamicRule").val());
	var url = "${pageContext.request.contextPath}/site/test.do?rule="+param+"&num="+Math.random();
	AT.postFrm ("testForm",function(data){
		var html = "<table id='urlTable' width='100%;' id='urlsTable'  style='border:1px #97B859 solid;' cellpadding='0' cellspacing='0' ><tr height='45px;' style='background-color:#eee;'><td colspan='2' align='left' style='text-align:left;'><b>&nbsp;&nbsp;&nbsp;根据动态地址规则产生的新地址如下：</b></td></tr>";
		for(var i = 0;i<data.length;i++){
			html += "<tr height='25px;'><td class='bottomTable' style='text-align:left;' >&nbsp;&nbsp;&nbsp;<a href='"+data[i]+"' target='_blank'>"+data[i]+"</a></td></tr>";
		}
		html += "</table>";
		$("#urlDivTable").html('');
		$("#urlDivTable").html(html);
		$("#allTable").hide();
		$("#testForm").show();
		$("#img_closeDg").removeAttr("onclick");
		$("#img_closeDg").bind("click",function(){
			ruledyClose();
			$("#img_closeDg").bind("click",function(){
				closeDg();
			});
		});
	},false);
	
}

function ruledyClose(){
	$("#allTable").show();
	$("#testForm").hide();
}
</script>