<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions" %>
<table border=0 cellpadding="0"  cellspacing="0" width="100%;" class=".tbody">
	
	<tr height="30px;" >
		<td class="TD_Bgcolor3" width="25%" >
			<span class="label">网&nbsp;站&nbsp;编&nbsp;码: <a href="javascript:void(0)" onclick="toolTip(this,'选择站点编码，站点编码是指目标网站使用的编码，如果不确定本站点的编码请不要选择')">[?]</a></span>
		</td>
		<td class="TD_Bgcolor4" width="80%" >
			<select id="htmlCode" class="inputTxt1" style="width: 185px;"  name="htmlCode" >
				<option></option>
				<c:forEach items="${htmlCodes}" var="htmlCode" varStatus="status">
					<option value = "${htmlCode }" <c:if test="${fn:indexOf(site.htmlCode,htmlCode)>-1}">selected="selected"</c:if> >${htmlCode }</option>
				</c:forEach>
			</select>
		</td>
	</tr>
	<tr height="30px;" >
		<td class="TD_Bgcolor3" width="25%" >
			<span class="label">区&nbsp;域&nbsp;类&nbsp;型: <a href="javascript:void(0)" onclick="toolTip(this,'选择站点区域类型，站点区域类型是指目标网站的内容所在的区域和网站类型')">[?]</a></span>
		</td>
		<td class="TD_Bgcolor4" width="80%" >
			<select id="areaType" class="inputTxt1" style="width: 185px;"  name="areaType" >
				<option></option>
				<c:forEach items="${areaTypes}" var="areaType" varStatus="status">
					<option value = "${areaType }" <c:if test="${fn:indexOf(site.areaType,areaType)>-1}">selected="selected"</c:if> >${areaType }</option>
				</c:forEach>
			</select>
		</td>
	</tr>
	<tr height="30px;" >
		<td class="TD_Bgcolor3" width="25%" >
			<span class="label">媒&nbsp;体&nbsp;类&nbsp;型: <a href="javascript:void(0)" onclick="toolTip(this,'选择站点媒体类型，站点媒体类型是指目标网站的媒体类型')">[?]</a></span>
		</td>
		<td class="TD_Bgcolor4" width="80%" >
			<select id="mediaType" class="inputTxt1" style="width: 185px;"  name="mediaType" >
				<c:forEach items="${mediaTypes}" var="mediaType" varStatus="status">
					<option value = "${mediaType }" <c:if test="${fn:indexOf(site.mediaType,mediaType)>-1}">selected="selected"</c:if> >${mediaType }</option>
				</c:forEach>
			</select>
		</td>
	</tr>
	<tr height="30px;" >
		<td class="TD_Bgcolor3" width="25%" >
			<span class="label">网&nbsp;站&nbsp;区&nbsp;域: <a href="javascript:void(0)" onclick="toolTip(this,'定义站点网站所属区域，网站所属区域是指目标网站所在的国家，省，市，地区')">[?]</a></span>
		</td>
		<td class="TD_Bgcolor4" width="80%" >
			<select id="contryId" class="inputTxt1" style="width: 120px;"  onchange="changeProvince($(this).val());" name="contryId" >
				<c:forEach items="${countrys}" var="cr" varStatus="status">
					<option value = "${cr.id }" <c:if test="${site.contryId == cr.id}">selected="selected"</c:if> >${cr.areaName }</option>
				</c:forEach>
			</select>
	      			<select id="provinceId" class="inputTxt1" style="width: 90px;" onchange="changeCity($(this).val());" name="provinceId" >
				<option></option>
				<c:forEach items="${provinces}" var="ctr" varStatus="status">
					<option value = "${ctr.id }" <c:if test="${site.provinceId == ctr.id}">selected="selected"</c:if> >${ctr.areaName }</option>
				</c:forEach>
			</select>
			<select id="cityId" class="inputTxt1" style="width: 90px;" onchange="changeArea($(this).val());" name="cityId" >
				<option></option>
				<c:forEach items="${citys}" var="t" varStatus="status">
					<option value = "${t.id }" <c:if test="${site.cityId==t.id}">selected="selected"</c:if>>${t.areaName}</option>
				</c:forEach>
			</select>
			<select id="areaId" class="inputTxt1" style="width: 90px;" name="areaId" >
				<option></option>
				<c:forEach items="${areas}" var="t" varStatus="status">
					<option value = "${t.id }" <c:if test="${site.areaId==t.id}">selected="selected"</c:if>>${t.areaName}</option>
				</c:forEach>
			</select>
		</td>
	</tr>
	<tr height="30px;" >
		<td class="TD_Bgcolor3" width="25%" >
			<span class="label">代理服务器: <a href="javascript:void(0)" onclick="toolTip(this,'定义站点代理服务器，代理服务器主要是解决网络连接问题，一是使用代理IP进行采集数据，防止IP被封以后无法采集；二是目标网站是国外的网站时，需要配置代理服务器')">[?]</a></span>
		</td>
		<td class="TD_Bgcolor4" width="80%" >
			<select id="proxyId" class="inputTxt1" style="width: 185px;"  name="proxyId" >
				<option></option>
				<c:forEach items="${proxys}" var="proxy" varStatus="status">
					<option value = "${proxy.id }" <c:if test="${site.proxyId==proxy.id}">selected="selected"</c:if> >${proxy.name }</option>
				</c:forEach>
			</select>
		</td>
	</tr>
	<!--<tr height="30px;" style="display: none">
		<td class="TD_Bgcolor3" width="25%" >
			<span class="label">地&nbsp;址&nbsp;插&nbsp;件: <a href="javascript:void(0)" onclick="toolTip(this,'针对特殊的网站，需要采用特殊的配置方法，即插件')">[?]</a></span>
		</td>
		<td class="TD_Bgcolor4" width="80%" >
			<select id="sitePluginId" class="inputTxt1" style="width: 185px;"  name="sitePluginId" >
				<option></option>
				<c:forEach items="${plugins}" var="plugin" varStatus="status">
					<option value = "${plugin.id }" <c:if test="${site.sitePluginId==plugin.id}">selected="selected"</c:if> >${plugin.pluginName }</option>
				</c:forEach>
			</select>
		</td>
	</tr>
--></table>
<script type="text/javascript">
//根据国家获取省
function changeProvince(countryId)   
{   
	var url = '${pageContext.request.contextPath}/site/getRegionByParentId.do?parentId='+countryId;
    AT.post(url,null,function(data){
         if (data != null && data != "" &&  data != undefined)   
         {   
	         $("#provinceId").empty();  
	         $("#cityId").empty();  
	         $("#areaId").empty();   
	         // 转换成json对象   
	         var option = "";
	         option += "<option></option>";
             // 循环组装下拉框选项   
	         $.each(data, function(k, v)   
	         {   
         		option += "<option value=\"" + v.id + "\">" + v.areaName + "</option>";
	         }); 
	         $("#provinceId").append(option); 
         }else{
	         $("#provinceId").empty(); 
	         $("#cityId").empty();  
	         $("#areaId").empty();   
         }
    });
}
//根据省获取市
function changeCity(provinceId)   
{   
	var url = '${pageContext.request.contextPath}/site/getRegionByParentId.do?parentId='+provinceId;
    AT.post(url,null,function(data){
         if (data != null && data != "" &&  data != undefined)   
         {   
	         $("#cityId").empty();  
	         $("#areaId").empty();   
	         // 转换成json对象   
	         var option = "";
	         option += "<option></option>";
             // 循环组装下拉框选项   
	         $.each(data, function(k, v)   
	         {   
         		option += "<option value=\"" + v.id + "\">" + v.areaName + "</option>";
	         }); 
	         $("#cityId").append(option); 
         }else{
	         $("#cityId").empty(); 
	         $("#areaId").empty();   
         }
    });
}
//根据市获取地区
function changeArea(provinceId)   
{   
	var url = '${pageContext.request.contextPath}/site/getRegionByParentId.do?parentId='+provinceId;
    AT.post(url,null,function(data){
         if (data != null && data != "" &&  data != undefined)   
         {   
	         $("#areaId").empty();   
	         // 转换成json对象   
	         var option = "";
	         option += "<option></option>";
             // 循环组装下拉框选项   
	         $.each(data, function(k, v)   
	         {   
         		option += "<option value=\"" + v.id + "\">" + v.areaName + "</option>";
	         }); 
	         $("#areaId").append(option); 
         }else{
	         $("#areaId").empty(); 
         }
    });
}
</script>