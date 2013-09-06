<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.xdtech.cloudsearch.util.AppConf"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<!--<meta http-equiv="X-UA-Compatible" content="IE=7"/>
--><meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>线点科技云采集产品</title>
<%@include file="/include/common.jsp" %>
<style type="text/css">
input.error {
    border: 1px dotted red;
}
label.error {
    color: red;
    font-style: italic;
}
</style>
<script type="text/javascript">
	function logout(){
		window.location='${pageContext.request.contextPath}/user/logout.do';
	}
	/**修改当前登录用户的密码*/
	function pwdeditFrm_submit(){
		AT.postFrm("pwdeditForm",function(data){
			closeDg();
			if(data=="true"){
				MSG.alert('修改密码成功!');
			}else{
				MSG.alert('修改密码失败!');
			}	
		},true);
	}
	
</script>
</head>
<body class="mainer_body">
<!--header Begin-->
<div class="header">
  <div class="left"><img src="${pageContext.request.contextPath}/<%= AppConf.get().get("system.logo")%>" height="50"/></div>
  <div class="right"><img src="${pageContext.request.contextPath}/images/user.png"/>欢迎【${user.username }】 <a href="#"> <img src="${pageContext.request.contextPath}/images/logoff.png"  onclick="logout()"/> </a> <a href="#" onclick="openDg('${pageContext.request.contextPath}/user/pwdedit.do','# 修改密码',550,120);"> <img src="${pageContext.request.contextPath}/images/pswedit.png" /> </a></div>
  <!--top_link Begin-->
    <!--<div class="top_link">
      <div class="show">模块切换</div>
      <div class="btn"><a href="#"><img src="${pageContext.request.contextPath}/images/arrow3.png" /></a></div>
      <div class="link_list" style="display:none;">
        <ul>
          <li><a href="#">链接一</a></li>
          <li><a href="#">链接二</a></li>
          <li><a href="#">链接三</a></li>
        </ul>
      </div>
    </div>
    --><!--top_link End--> 
  </div>
  <div class="clear"></div>
</div>

<!--header End--> 
<!--mainer_top Begin-->
<div class="mainer_top">
  <table border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td class="c1"><img src="${pageContext.request.contextPath}/images/main_c_left_top.png" width="5" height="5" /></td>
      <td class="c2"><img src="${pageContext.request.contextPath}/images/bg_t.png" width="5" height="5" /></td>
      <td class="c3"><img src="${pageContext.request.contextPath}/images/main_c_right_top.png" width="5" height="5" /></td>
    </tr>
  </table>
</div>
<!--mainer_top End--> 
<!--mainer Begin-->
<div class="mainer" id="mainer"  > 
  <!--mainer Begin-->
  <div class="menus">
    <table border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td class="c_begin"><img src="${pageContext.request.contextPath}/images/menu_c_left.png" width="3" height="45" /></td>
        <td class="c_td"><img src="${pageContext.request.contextPath}/images/home.png"/><a href="javascript:void(0)" onclick="navto(this,'${pageContext.request.contextPath}/nav/navTo.do?id=index')">首页</a></td>
        <td class="c_td"><img src="${pageContext.request.contextPath}/images/index.png"/><a href="javascript:void(0)" onclick="navto(this,'${pageContext.request.contextPath}/nav/navTo.do?id=siteMgr')">站点采集</a></td>
        <td class="c_td"><img src="${pageContext.request.contextPath}/images/collect.png"/><a href="javascript:void(0)" onclick="navto(this,'${pageContext.request.contextPath}/nav/navTo.do?id=dataCollect')">全网采集</a></td>
        <!--<td class="c_td"><img src="${pageContext.request.contextPath}/images/data.png"/><a href="javascript:void(0)" onclick="navto(this,'${pageContext.request.contextPath}/nav/navTo.do?id=dataETL')">微博采集</a></td>
        --><td class="c_td"><img src="${pageContext.request.contextPath}/images/mag.png"/><a href="javascript:void(0)" onclick="navto(this,'${pageContext.request.contextPath}/nav/navTo.do?id=crawlerMgr')">爬虫管理</a></td>
        <td class="c_td"><img src="${pageContext.request.contextPath}/images/task.png"/><a href="javascript:void(0)" onclick="navto(this,'${pageContext.request.contextPath}/nav/navTo.do?id=taksMagr')">任务管理</a></td>
        <td><img src="${pageContext.request.contextPath}/images/sys.png"/><a href="javascript:void(0)" onclick="navto(this,'${pageContext.request.contextPath}/nav/navTo.do?id=sysAdmin')">系统管理</a></td>
        <td class="c_end"><img src="${pageContext.request.contextPath}/images/menu_c_right.png" width="3" height="45" /></td>
      </tr>
    </table>
  </div>
  <!--menus End--> 
  <!--mainer_content Begin-->
  <div class="mainer_content" > 
    <!--mainer_left Begin-->
    <div class="mainer_left">
      <div class="submenu">
	      <ul>
		      <li class="title"><img src="${pageContext.request.contextPath}/images/index.png"/>您常用的功能</li>
		      <li class="other" style="cursor:pointer;" id="site_manager" onclick="linkto(this,'/site/site.do')"><img src="${pageContext.request.contextPath}/images/arrow.png"/><a href="javascript:void(0)">站点管理</a></li>
		      <li class="other" style="cursor:pointer;" onclick="linkto(this,'/keyword/keyword.do')"><img src="${pageContext.request.contextPath}/images/arrow.png"  /><a href="javascript:void(0)">监控词管理</a></li>
		      <li class="other" style="cursor:pointer;" onclick="linkto(this,'/crawler/crawler.do')"><img src="${pageContext.request.contextPath}/images/arrow.png"  /><a href="javascript:void(0)">爬虫管理</a></li>
		      <li class="other" style="cursor:pointer;" onclick="linkto(this,'/task/task.do')"><img src="${pageContext.request.contextPath}/images/arrow.png"  /><a href="javascript:void(0)">任务管理</a></li>
		      <li class="other" style="cursor:pointer;" onclick="linkto(this,'/crawlerlog/crawlerlog.do')"><img src="${pageContext.request.contextPath}/images/arrow.png"  /><a href="javascript:void(0)">采集日志管理</a></li>
	      </ul>
      </div>
    </div>
    <!--mainer_left End--> 
    <!--mainer_right Begin-->
    <div class="mainer_right"> 
      <!--nav Begin-->
      <div class="nav">
        <div class="left"><strong>你的位置：</strong><span id="f_nav_path">首页 </span><span id="s_nav_path"></span><span id="c_nav_path" class="txt_color1"></span></div>
        <div class="right" style="display: none;"><img src="${pageContext.request.contextPath}/images/warning.png" /> 设置发生改变，请重启您的服务。</div>
        <div class="clear"></div>
      </div>
      <!--nav End--> 
        <!--Frame Begin-->
      <div class="Frame" id="mainFrame">
      	<div id="mainContent">
			<jsp:include page="/indextemp.jsp"></jsp:include>
        </div>
        <div class="float_frame" id="dialog" style="display:none;z-index:1002" >
			<div class="shadow" id="dialogDivShadow" style="z-index:1002;">
				<div class="contents" id="dialogDivContents" style="z-index:1002">
					<div class="float_frame_title"  id="dialogDivTitle" style="z-index:1002">
						<div class="left" id="dialogDivTitleShow" style="z-index:1002"></div>
						<div class="right" id="dialogDivTitleClose" style="z-index:1002">
							<a href="javascript:;"><img id="img_closeDg" src="${pageContext.request.contextPath}/images/cancel.png" onclick="closeDg()" width="16" height="16" /> </a>
						</div>
						<div class="clear"></div>
					</div>
					<div class="float_frame_lists" style="width:100%" style="z-index:1002">
						<div class="table_list3" style="z-index:1002">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td class="TD_Bgcolor1">
										<div class="sub_table_list" id="procontent" style="z-index:1002">
											
										</div>
									</td>
								</tr>
							</table>
						</div>
		  			</div>
				</div>
			</div>
		</div>
      </div>
      <!--Frame End--> 
    </div>
    <!--mainer_right End--> 
    
  </div>
  <!--mainer_content End--> 
  
</div>
<!--mainer End--> 
<!-- ajax提交时的等待框 -->
		<div id="progressBar" style="display:none;">
			<style>
				.progressBar { border:solid 2px #86a5ad; background:#FFF url(${pageContext.request.contextPath}/images/progressBar_m.gif) no-repeat 10px 10px; display:block; width:148px; height:28px; position:fixed; top:50%; left:50%; margin-left:-74px; margin-top:-14px; padding:10px 10px 10px 50px; text-align:left; line-height:27px; font-weight:bold; position:absolute; z-index:2001;}
 			</style>
			<div class="progressBar">数据加载中，请稍等...</div>
		</div>
<!--footer Begin-->
<div class="footer"> 
  <!--mainer_bottom Begin-->
  <div class="mainer_bottom">
    <table border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td  class="c1"><img src="${pageContext.request.contextPath}/images/main_c_left_bottom.png" width="5" height="5" /></td>
        <td class="c2" style="text-align:center"><img src="${pageContext.request.contextPath}/images/bg_t.png" width="3" height="5" /></td>
        <td class="c3"><img src="${pageContext.request.contextPath}/images/main_c_right_bottom.png" width="5" height="5" /></td>
      </tr>
    </table>
  </div>
  <!--mainer_bottom End-->
  <div class="txts">Copyright © 2005 - 2013. All Rights Reserved &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;线点科技 &nbsp;&nbsp;&nbsp;版权所有</div>
</div>
<!--footer End-->
<script type="text/javascript" language="javascript">   
iframeHeight()
</script>
</body>
</html>

