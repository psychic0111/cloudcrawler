<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="nav">
	<span style="font-size: 14px;" >搜索引擎数据采集监控信息</span>
</div>
<form id="engineControl" method="post" 	action="${pageContext.request.contextPath}/netParam/editParamDo.do">
	<table  style=" border:#c1c7cb 1px solid; margin-top:10px;"  cellpadding="0" cellspacing="0" width="100%;"  class=".tbody">
		<tr height="30px;" >
			<td class="TD_Bgcolor3" width="25%" style=" border-bottom:#c1c7cb 1px solid;border-right:#c1c7cb 1px solid;"  >
				<span class="txt_color2" style="margin-left: 25px;"><img src="${pageContext.request.contextPath}/images/star2.png" width="15" height="16" /></span><span class="label">监控搜索引擎: <a href="javascript:void(0)" onclick="toolTip(this,'当前可以采集的搜索引擎')">[?]</a></span>
			</td>
			<td class="TD_Bgcolor4" width="80%" style=" border-bottom:#c1c7cb 1px solid;padding-left:25px;" >
				<c:forEach items="${engineControl.engines}" var="engine" varStatus="number" >
					<span style="width:200px;margin-right:25px;">${engine.enginName }</span>
				</c:forEach>
			</td>
		</tr>
		<tr height="30px;" >
			<td class="TD_Bgcolor3" width="25%" style=" border-bottom:#c1c7cb 1px solid;border-right:#c1c7cb 1px solid;" >
				<span class="txt_color2" style="margin-left: 25px;"><img src="${pageContext.request.contextPath}/images/star2.png" width="15" height="16" /></span><span class="label">采&nbsp;&nbsp;集&nbsp;&nbsp;线&nbsp;&nbsp;程: <a href="javascript:void(0)" onclick="toolTip(this,'搜索引擎在采集数据的过程中，当前活动的线程数据')">[?]</a></span>
			</td>
			<td class="TD_Bgcolor4" width="80%" style=" border-bottom:#c1c7cb 1px solid;padding-left:25px;" >
				${engineControl.liveThread }
			</td>
		</tr>
		<tr height="30px;" >
			<td class="TD_Bgcolor1" width="25%" style=" border-bottom:#c1c7cb 1px solid;border-right:#c1c7cb 1px solid;" >
				<span class="txt_color2" style="margin-left: 25px;"><img src="${pageContext.request.contextPath}/images/star2.png" width="15" height="16" /></span><span class="label">全网采集状态: <a href="javascript:void(0)" onclick="toolTip(this,'搜索引擎是否开始采集数据')">[?]</a></span>
			</td>
			<td class="TD_Bgcolor1" id="webCrawlerStatus" width="80%" style=" border-bottom:#c1c7cb 1px solid;padding-left:25px;" >
				<!--<c:choose>  
				  <c:when test="${engineControl.start == true}">  
   						正在运行
 				 </c:when>  
				<c:otherwise>  
  				 		未启动 
				</c:otherwise>  
				</c:choose>  
			-->
			</td>
		</tr>
	</table>
	<div class="function2">
		<input id="startButton" style="display: none;" name="" type="button" class="botton_style3" value="启动采集" onclick="startEngineControl();" />
		<input id="endButton" style="display: none;" name="" type="button" class="botton_style2"	onclick="endEngineControl();" value="停止采集 " />
	</div>
</form>
