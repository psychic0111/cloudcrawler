<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="xd" uri="http://www.xd-tech.com.cn/" %>
	<script type="text/javascript" language="javascript">
	//添加用户
	function addFrm_submit(){
		AT.postFrm("addUserForm",function(data){
			AT.load("iframepage","${pageContext.request.contextPath}/user/list.do",iframeHeight);
			closeDg();
		},true);
	}

	//修改用户
	function editFrm_submit(){
		AT.postFrm("editUserForm",function(data){
			AT.load("iframepage","${pageContext.request.contextPath}/user/list.do",iframeHeight);	
			closeDg();
		},true);
	}

	//删除用户
	function delUser(){
		$checked = checkAll();
		if($checked.length==0){
			MSG.alert('请选择要删除的用户!');
		}else{
			MSG.confirm('确认要删除选中的用户吗？',null,function(){
				AT.post("${pageContext.request.contextPath}/user/del.do",$checked.serialize(),function(data){
					AT.load("iframepage","${pageContext.request.contextPath}/user/list.do",iframeHeight);
				});
			},null);
		}
	}

	function doPage(ths,pageNo,pageSize){
		AT.load("iframepage","${pageContext.request.contextPath}/user/list.do?pageNo="+pageNo+"&pageSize="+pageSize,iframeHeight);
	}
	</script>
  <div class="title">
      <table border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td class="c1"><img src="${pageContext.request.contextPath}/images/title_c_left.png" width="3" height="34" /></td>
          <td class="c2"><img src="${pageContext.request.contextPath}/images/sys.png"  /> 用户管理</td>
          <td class="c3"><img src="${pageContext.request.contextPath}/images/title_c_right.png" width="3" height="34" /></td>
        </tr>
      </table>
  </div>
   
<div class="lists">
	<div class="function">
		<div class="left">
			<input type="button" class="botton_style1" value="添加" onclick="openDg('${pageContext.request.contextPath}/user/add.do','+ 添加用户',550,120)" />
			<input type="button" class="botton_style2" value="删除" onclick="delUser()"  />
		</div>
		<div class="clear"></div>
	</div>
	<div id="listDiv">
		<div class="table_list" id="iframepage">
			<jsp:include page="/module/system/user/list.jsp"></jsp:include>
		</div>
	</div>
</div>