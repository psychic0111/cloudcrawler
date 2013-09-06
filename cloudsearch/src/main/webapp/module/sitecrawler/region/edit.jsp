<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<script type="text/javascript" language="javascript">
$().ready(function() {
	$("#editRegionForm").validate({
			rules:{
				areaName:{required:true,minlength:2,maxlength:100,remote:{url:'${pageContext.request.contextPath}/region/checkRegion.do?id='+$("#id").val()}},
				sortNo:{required:true,digits:true,range:[1,99999] }
			},
			messages:{
				areaName:{remote:'该地区已存在!'}
			}
		}
	);
});
</script>
<form id="editRegionForm" method="post" 	action="${pageContext.request.contextPath}/region/editDo.do">
	<input type="hidden" name="_token" value="${token}">
	<input type="hidden" name="id" value="${region.id}" id="id"/>
	<input type="hidden" name="deleted" value="${region.deleted}" id="deleted"/>
	
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td class="TD_Bgcolor1">
				<div class="sub_table_list">
					<table border=0 cellpadding="0" cellspacing="0" width="100%;" class=".tbody">
						<tr height="30px;" >
							<td class="TD_Bgcolor3" width="25%" >
								<span class="label">父节点名称: <a href="javascript:void(0)" onclick="toolTip(this,'定义区域父节点，父节点是指该子节点的上一级节点')">[?]</a></span>
							</td>
							<td class="TD_Bgcolor4" width="80%" >
								<input id="parentName" type="text" name="parentName" readonly="readonly" style="width: 140px;" class="inputTxt1" maxlength="100" value="${region.parentName }">
								<input id="parentId" type="hidden" name="parentId" readonly="readonly" style="width: 140px;" class="inputTxt1" maxlength="100" value="${region.parentId}"/>
							</td>
						</tr>
						<tr height="30px;" >
							<td class="TD_Bgcolor3" width="25%" >
								<span class="txt_color2"><img src="${pageContext.request.contextPath}/images/star2.png" width="15" height="16" /></span><span class="label">名&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;称: <a href="javascript:void(0)" onclick="toolTip(this,'定义区域名称，为区域定义一个名称，在站点属性中选择网站区域时使用')">[?]</a></span>
							</td>
							<td class="TD_Bgcolor4" width="80%" >
								<input id="areaName" type="text" name="areaName" value="${region.areaName }" style="width: 140px;" class="inputTxt1" maxlength="100" />
							</td>
						</tr>
						<tr height="30px;" >
							<td class="TD_Bgcolor3" width="25%" >
								<span class="txt_color2"><img src="${pageContext.request.contextPath}/images/star2.png" width="15" height="16" /></span><span class="label">排&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;序: <a href="javascript:void(0)" onclick="toolTip(this,'定义区域排序字段，为区域列表显示时的排序依据')">[?]</a></span>
							</td>
							<td class="TD_Bgcolor4" width="80%" >
								<input id="sortNo" type="text" name="sortNo" value="${region.sortNo}" style="width: 140px;" class="inputTxt1" maxlength="100" />
							</td>
						</tr>
					</table>
				</div>
			</td>
		</tr>
	</table>
		<!--table_list End-->
		<!--function2 Begin-->
		<div class="function2">
			<input name="" type="button" class="botton_style3" value="保存" onclick="editFrm_submit();" />
			<input name="" type="button" class="botton_style3"	onclick="closeDg()" value="取消" />
		</div>
		<!--function2 End-->
		
</form>
