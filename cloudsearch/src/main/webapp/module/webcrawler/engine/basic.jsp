<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions" %>
<table border=0 cellpadding="0" cellspacing="0" width="100%;" class=".tbody">
	<tr height="30px;" >
		<td class="TD_Bgcolor3" width="15%" style="padding-left:0px;">
			<span class="txt_color2"><img src="${pageContext.request.contextPath}/images/star2.png" width="15" height="16" /></span><span class="label">搜索引擎名称: <a href="javascript:void(0)" onclick="toolTip(this,'定义搜索引擎名称，根据您要采集的搜索引擎的名称或内容定义')">[?]</a></span>
		</td>
		<td class="TD_Bgcolor4" width="35%" >
			<input id="enginName" type="text" name="enginName" value="${engine.enginName }" style="width: 150px;" class="inputTxt1"  />
		</td>
		<td class="TD_Bgcolor3" width="15%" >
			<span class="txt_color2"><img src="${pageContext.request.contextPath}/images/star2.png" width="15" height="16" /></span><span class="label">代&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码: <a href="javascript:void(0)" onclick="toolTip(this,'定义搜索引擎编码，为数据交互是使用')">[?]</a></span>
		</td>
		<td class="TD_Bgcolor4" width="35%" >
			<input id="enginCode" type="text" name="enginCode" value="${engine.enginCode }" style="width: 150px;" class="inputTxt1"  />
		</td>
	</tr>
	<tr height="30px;" >
		<td class="TD_Bgcolor3" >
			<span class="txt_color2"><img src="${pageContext.request.contextPath}/images/star2.png" width="15" height="16" /></span><span class="label">访&nbsp;&nbsp;问&nbsp;&nbsp;地&nbsp;&nbsp;址: <a href="javascript:void(0)" onclick="toolTip(this,'定义搜索引擎入口地址，根据该入口地址中规则产生新的地址和详细地址')">[?]</a></span>
		</td>
		<td class="TD_Bgcolor4"  colspan="3" >
			<input id="pageUrl" type="text" name="pageUrl" value="${engine.pageUrl}" style="width: 400px;" class="inputTxt1"  />
		</td>
	</tr>
	<tr height="30px;" >
		<td class="TD_Bgcolor3" width="15%" >
			<span class="label">内&nbsp;&nbsp;容&nbsp;&nbsp;类&nbsp;&nbsp;型: <a href="javascript:void(0)" onclick="toolTip(this,'定义站点类型，根据目标网站的类型对站点进行分类型')">[?]</a></span>
		</td>
		<td class="TD_Bgcolor4" width="35%" >
			<select id="contentType" class="inputTxt1" style="width: 150px;"  name="contentType" >
				<option value="0" <c:if test="${engine.contentType==0 }">selected="selected"</c:if>>新闻</option>
				<option value="1" <c:if test="${engine.contentType==1 }">selected="selected"</c:if>>论坛</option>
				<option value="2" <c:if test="${engine.contentType==2 }">selected="selected"</c:if>>博客</option>
				<option value="3" <c:if test="${engine.contentType==3 }">selected="selected"</c:if>>其他</option>
			</select>
		</td>
		<td class="TD_Bgcolor3" width="15%" >
			<span class="label">媒&nbsp;&nbsp;体&nbsp;&nbsp;类&nbsp;&nbsp;型: <a href="javascript:void(0)" onclick="toolTip(this,'定义站点媒体类型，站点媒体类型是指目标网站的媒体类型')">[?]</a></span>
		</td>
		<td class="TD_Bgcolor4" width="35%" >
			<select id="mediaType" class="inputTxt1" style="width: 150px;"  name="mediaType" >
				<c:forEach items="${mediaTypes}" var="mediaType" varStatus="status">
					<option value = "${mediaType }" <c:if test="${fn:indexOf(engine.mediaType,mediaType)>-1}">selected="selected"</c:if> >${mediaType }</option>
				</c:forEach>
			</select>
		</td>
	</tr>
	<tr height="30px;" >
		<td class="TD_Bgcolor3" width="15%" >
			<span class="label">是&nbsp;&nbsp;否&nbsp;&nbsp;启&nbsp;&nbsp;用: <a href="javascript:void(0)" onclick="toolTip(this,'定义搜索引擎是否启用，指该搜索引擎是否可用，若启用，则该搜索引擎执行；否则，该搜索引擎不执行')">[?]</a></span>
		</td>
		<td class="TD_Bgcolor4" width="35%" >
			<select id="isUse" class="inputTxt1" style="width: 150px;"  name="isUse" >
				<option value="1" <c:if test="${engine.isUse==1 }">selected="selected"</c:if>>启用</option>
				<option value="0" <c:if test="${engine.isUse==0 }">selected="selected"</c:if>>禁用</option>
			</select>
		</td>
		<td class="TD_Bgcolor3" width="15%" >
			<img src="${pageContext.request.contextPath}/images/star2.png" width="15" height="16" /><span class="label">间&nbsp;&nbsp;隔&nbsp;&nbsp;时&nbsp;&nbsp;间: <a href="javascript:void(0)" onclick="toolTip(this,'定义该搜索引擎在数据采集过程中的发生频率')">[?]</a></span>
		</td>
		<td class="TD_Bgcolor4" width="35%" >
			<input id="timeInterval" type="text" name="timeInterval" value="${engine.timeInterval }" style="width: 80px;" class="inputTxt1" maxlength="100" />
			<select id="timeIntervalType" name="timeIntervalType"  class="inputTxt1" style="width: 60px;height:25px;">
				<option value="1" <c:if test="${engine.timeIntervalType==1 }">selected="selected"</c:if>>小时</option>
				<option value="2" <c:if test="${engine.timeIntervalType==2 }">selected="selected"</c:if>>分钟</option>
			</select>
		</td>
	</tr>
	<tr height="30px;" >
		<td class="TD_Bgcolor3" width="15%" valign="top" >
			<img src="${pageContext.request.contextPath}/images/star2.png" width="15" height="16" /><span class="label">抓&nbsp;&nbsp;取&nbsp;&nbsp;页&nbsp;&nbsp;数: <a href="javascript:void(0)" onclick="toolTip(this,'定义该搜索引擎在采集数据的过程中，采集该搜索引擎的前多少页')">[?]</a></span>
		</td>
		<td class="TD_Bgcolor4" width="35%" >
			<input id="pages" type="text" name="pages" value="${engine.pages}" style="width: 150px;" class="inputTxt1"  />
		</td>
		<td class="TD_Bgcolor3" width="15%" >
			<span class="label">文章正文来源: <a href="javascript:void(0)" onclick="toolTip(this,'定义该搜索引擎在采集数据的过程中，是否进入详细页面进行数据采集')">[?]</a></span>
		</td>
		<td class="TD_Bgcolor4" width="35%" >
			<select id="isVisiteUrl" class="inputTxt1" style="width: 150px;"  name="isVisiteUrl" >
				<option value="1" <c:if test="${engine.isVisiteUrl==1 }">selected="selected"</c:if>>来源页面详细内容</option>
				<option value="0" <c:if test="${engine.isVisiteUrl==0 }">selected="selected"</c:if>>来源搜索引擎内容</option>
			</select>
		</td>
	</tr>
	<tr height="30px;" >
		<td class="TD_Bgcolor3" width="15%" >
			<span class="label">代&nbsp;&nbsp;理&nbsp;&nbsp;服&nbsp;&nbsp;务: <a href="javascript:void(0)" onclick="toolTip(this,'定义该搜索引擎在采集数据的过程中，如果出现IP被封的情况下，采用哪一个IP在进行采集')">[?]</a></span>
		</td>
		<td class="TD_Bgcolor4" width="35%" >
			<select id="proxyId" class="inputTxt1" style="width: 150px;"  name="proxyId" >
				<option>请选择...</option>
				<c:forEach items="${proxys}" var="proxy" varStatus="status">
					<option value = "${proxy.id }" <c:if test="${engine.proxyId==proxy.id}">selected="selected"</c:if> >${proxy.name }</option>
				</c:forEach>
			</select>
		</td>
		<td class="TD_Bgcolor3" width="15%" >
			<span class="label">优&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;先&nbsp;&nbsp;&nbsp;&nbsp;级: <a href="javascript:void(0)" onclick="toolTip(this,'定义该所搜索引擎的优先级，优先级高的先采集')">[?]</a></span>
		</td>
		<td class="TD_Bgcolor4" width="35%" >
			<select id="crawlerLevel" class="inputTxt1" style="width: 150px;"  name="crawlerLevel" >
				<option value="低" <c:if test="${engine.crawlerLevel=='低' }">selected="selected"</c:if>>低</option>
				<option value="中" <c:if test="${engine.crawlerLevel=='中'}">selected="selected"</c:if>>中</option>
				<option value="高" <c:if test="${engine.crawlerLevel=='高' }">selected="selected"</c:if>>高</option>
			</select>
		</td>
	</tr>
</table>
