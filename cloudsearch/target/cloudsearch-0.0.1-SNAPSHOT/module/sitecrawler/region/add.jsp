<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<script type="text/javascript" language="javascript">
$().ready(function() {
	$("#addRegionForm").validate({
			rules:{
				areaName:{required:true,minlength:2,maxlength:50,remote:{url:'${pageContext.request.contextPath}/region/checkRegion.do?parentId='+$("#parentId").val()}},
				sortNo:{required:true,digits:true,range:[1,9999] }
			},
			messages:{
				areaName:{remote:'该地区已存在!'}
			}
		}
	);
});
</script>
<form id="addRegionForm" method="post" 	action="${pageContext.request.contextPath}/region/doAdd.do">
	<input type="hidden" name="_token" value="${token}">
	<table border=0 cellpadding="0" cellspacing="0" width="100%;" class=".tbody">
		<tr height="30px;" >
			<td class="TD_Bgcolor3" width="25%" >
				<span class="label">父节点名称: <a href="javascript:void(0)" onclick="toolTip(this,'添加完成以后，添加的节点将成为此节点的子节点')">[?]</a></span>
			</td>
			<td class="TD_Bgcolor4" width="80%" >
				<input id="parentName" type="text" name="parentName" readonly="readonly" style="width: 140px;" class="inputTxt1" maxlength="100" />
				<input id="parentId" type="hidden" name="parentId" readonly="readonly" style="width: 140px;" class="inputTxt1" maxlength="100" />
				<script>
					document.getElementById("parentName").value = parentName;
					document.getElementById("parentId").value = parentId;
				</script>
			</td>
		</tr>
		<tr height="30px;" >
			<td class="TD_Bgcolor3" width="25%" >
				<span class="txt_color2"><img src="${pageContext.request.contextPath}/images/star2.png" width="15" height="16" /></span><span class="label">名&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;称: <a href="javascript:void(0)" onclick="toolTip(this,'例如：河北省')">[?]</a></span>
			</td>
			<td class="TD_Bgcolor4" width="80%" >
				<input id="areaName" type="text" name="areaName" style="width: 140px;" class="inputTxt1" maxlength="100" />
			</td>
		</tr>
		<tr height="30px;" >
			<td class="TD_Bgcolor3" width="25%" >
				<span class="txt_color2"><img src="${pageContext.request.contextPath}/images/star2.png" width="15" height="16" /></span><span class="label">排&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;序: <a href="javascript:void(0)" onclick="toolTip(this,'例如：整数1,2,3')">[?]</a></span>
			</td>
			<td class="TD_Bgcolor4" width="80%" >
				<input id="sortNo" type="text" name="sortNo" style="width: 140px;" class="inputTxt1" maxlength="100" />
			</td>
		</tr>
	</table>
<div class="function2">
	<input name="" type="button" class="botton_style3" value="保存" onclick="addFrm_submit();" />
	<input name="" type="button" class="botton_style3"	onclick="closeDg()" value="取消" />
</div>
</form>
