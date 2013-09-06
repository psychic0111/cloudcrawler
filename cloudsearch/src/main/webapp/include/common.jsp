<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/css.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/xdcss.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.0.min.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/js/plugins/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/plugins/jquery.metadata.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/plugins/jquery.validate.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/plugins/messages_zh.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/plugins/additional-methods.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/dialog.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/addMethod.js"></script>

<link rel="STYLESHEET" type="text/css" href="${pageContext.request.contextPath}/js/dhtmlxTree/codebase/dhtmlxtree.css"/>
<script src="${pageContext.request.contextPath}/js/dhtmlxTree/codebase/dhtmlxcommon.js"></script>
<script src="${pageContext.request.contextPath}/js/dhtmlxTree/codebase/dhtmlxtree.js"></script>

<script type="text/javascript"  >
	var dialogDivShadow_width;
	var dialogDivShadow_height;
	var dialogTemplate=null;
	/** 页面调整大小时，改变页面的宽度 */
	window.onresize=function(){
		iframeHeight();
		if($("#dialog").is(":visible")){
			changeDivPosition($("#dialogDivShadow"),dialogDivShadow_width,dialogDivShadow_height);
		}
		try{
			changeAlertPosition();
		}catch(e){}
	}
	/** 改变iframe的高度 */
	var iframeHeight=function() {
		var ifamepage=document.getElementById("iframepage");
		if(ifamepage){
			ifamepage.style.height = document.documentElement.clientHeight - 305 + "px";
		} 
	}
	
	/** BANNER导航  */
	var f_nav_path;
	function navto(ths,path){
		var navId = path.substring(path.indexOf("id=") + 3);
		if(navId == 'index'){
			window.location = '${pageContext.request.contextPath}/user/toindex.do';	
		}
		f_nav_path=$(ths).text();
		$.get(path,function(data){
			var navcontent=[];
			$(data).find("region").each(function(i){
				//创建 submenu
				$submenu=$("<div class='submenu'></div>");
				$t_img=$("<img src='${pageContext.request.contextPath}/images/"+$(this).attr("img")+"'/>");
				$title =$("<li>").addClass("title");
				$title.append($t_img);
				$title.append($(this).attr('text'));
				$ul = $("<ul>").append($title);
	
				//创建连接
				var $links = $(this).children('link');
				$links.each(function(i){
					$img = $("<img src='${pageContext.request.contextPath}/images/arrow.png'>");
					var ahtml="<a href='javascript:;'>"+$(this).attr('text')+"</a>";
					$li=$("<li style='cursor:pointer;' id='"+$(this).attr('name')+"' onclick='linkto(this,\""+$(this).text()+"\")'>").append($img).append($(ahtml)).addClass("other");
					$ul.append($li);
				});
				$submenu.append($ul);
				navcontent.push($submenu);
			});
			$(".mainer_left").html(navcontent);
			iframeHeight();
		},'xml');
	}
	/** 左侧MENU链接  */
	function linkto(ths,path){
		var $link = $(ths);
		var $li =$link;
		//修改菜单样式
		$(".submenu").find(".cur").removeClass("cur").addClass("other");
		$li.removeClass("other").addClass("cur");
		//修改导航路径
		$("#f_nav_path").text(f_nav_path);
		$("#s_nav_path").empty();
		$("#s_nav_path").append("&nbsp;>&nbsp;");
		$("#s_nav_path").append($li.parent().children().first().text());
		$("#s_nav_path").append("&nbsp;>&nbsp;");
		$("#c_nav_path").text($link.text());
		var url="";
		if(path.substring(0,1)=="/"){
			url = "${pageContext.request.contextPath}"+path;
		}else{
			url = "${pageContext.request.contextPath}/"+path;
		}
		$("#mainContent").load(url,iframeHeight);
	}
	
	function redirectTo(navPath, linkId, path, linkPath){
		f_nav_path=navPath;
		$.get(path,function(data){
			var navcontent=[];
			$(data).find("region").each(function(i){
				//创建 submenu
				$submenu=$("<div class='submenu'></div>");
				$t_img=$("<img src='${pageContext.request.contextPath}/images/"+$(this).attr("img")+"'/>");
				$title =$("<li>").addClass("title");
				$title.append($t_img);
				$title.append($(this).attr('text'));
				$ul = $("<ul>").append($title);
	
				//创建连接
				var $links = $(this).children('link');
				$links.each(function(i){
					$img = $("<img src='${pageContext.request.contextPath}/images/arrow.png'>");
					var ahtml="<a href='javascript:;'>"+$(this).attr('text')+"</a>";
					$li=$("<li style='cursor:pointer;' id='"+$(this).attr('name')+"' onclick='linkto(this,\""+$(this).text()+"\")'>").append($img).append($(ahtml)).addClass("other");
					$ul.append($li);
				});
				$submenu.append($ul);
				navcontent.push($submenu);
			});
			$(".mainer_left").html(navcontent);
			iframeHeight();
			linkto(document.getElementById(linkId), linkPath);
		},'xml');
		//$("#"+linkId).click();
	}
	
	/** 打开Dialog */
	function openDg(url,title,width,height){
		if(dialogTemplate==null){
			dialogTemplate=$("#dialog").html();
		}
		if($.trim($("#dialog").html())==""){
			$("#dialog").html(dialogTemplate);
		}
		dialogDivTitleShowMouseEvent();//注册拖拽事件
		if(width){
			$("#dialogDivShadow").width(width);
		}else{
			$("#dialogDivShadow").width(600);
		}
		dialogDivShadow_width=width;
		dialogDivShadow_height=height;
		AT.get(url,function(data){
			$("#procontent").html(data);
			if(title){
				$("#dialogDivTitleShow").html(title);
			}
			changeDivPosition($("#dialogDivShadow"),width,height);
			$("#dialog").show();
			var totalLength=$("#dialogDivTitle").width();
			var closeLength=$("#dialogDivTitleClose").width();
			$("#dialogDivTitleShow").css("width",(totalLength-2*closeLength)+"px");
		});
	}
 
	/**修改Dialog的坐标*/
	function changeDivPosition(div,twidth,theight){
		var $div=$(div);
		var MyDiv_w = $div.width();
        var MyDiv_h = $div.height();
        if(theight){
        	MyDiv_h=theight;
        }
        
        MyDiv_w = parseInt(MyDiv_w);
        MyDiv_h = parseInt(MyDiv_h+theight/4);
        var width = $.XDPageSize().Width;
        var height = $.XDPageSize().Height;
        var left = $.XDScrollPosition().Left;
        var top = $.XDScrollPosition().Top;
        var Div_topposition = top + (height / 2) - (MyDiv_h / 2)-246;//计算位置并修正顶部导航的高度
        var Div_leftposition = left + (width / 2) - (MyDiv_w / 2)-50;//计算位置并修正左边菜单的宽度
        $div.css("position", "absolute");
        $div.css("left", Div_leftposition + "px");
        $div.css("top", Div_topposition + "px");
	}
	
	/** 关闭Dialog */
	function closeDg(){
		$("#dialog").hide();
		$("#dialog").html("");
	}
	/**给弹出层注册拖拽事件*/
	function dialogDivTitleShowMouseEvent(){
		$("#dialogDivTitleShow").mousedown(function(e){
			try{
				$(this).css("cursor","move");
				var offset = $(this).offset();
				//鼠标在div上的边距  
				var x = e.pageX - offset.left;
				var y = e.pageY - offset.top;  
				$(document).bind("mousemove",function(ev){  
					$("#dialogDivTitleShow").stop();//加上这个之后  
					var _x = ev.pageX - x;//获得X轴方向移动的值  
					var _y = ev.pageY - y;//获得Y轴方向移动的值  
					$("#dialogDivShadow").css("left",(_x)+"px");
					$("#dialogDivShadow").css("top",(_y-110)+"px");//修正header高度
				});
			}catch(e){
			}
			$(document).bind("selectstart",function(ev){return false;});
		});
	       
		$(document).mouseup(function(){  
			try{
				$("#dialogDivTitleShow").css("cursor","default"); 
				$(this).unbind("mousemove");
			}catch(e){
			}
			$(document).unbind("selectstart");
		});
	}
	
	/** 设置验证属性为 validate*/
	//$.metadata.setType("attr", "validate");

	/**信息提示*/
	function toolTip(ths,tipMsg){
		
		var top = $(ths).offset().top-10;
		var left = $(ths).offset().left+10;
		var tip=$("<div style='border:1px solid; background-color: #FFFFCC; position: absolute; dispaly:inline; z-index:10000; width:200px;'></div>").text(tipMsg).offset({'top':top,'left':left});
		$("body:first").append(tip);
		$(ths).mouseout(function(){
			tip.remove();
		});
	}

	/**返回页面的pageNo,没有pageNo域时，返回1*/
	var pageNo=function(){
		cpage=$("input[name=pageNo]").val();
		if(cpage){
			return cpage;
		}
		return 1;
	}

	/**选中所有的列表数据*/
	var checkAll=function() {
		return $("[type=checkbox][name!='checkbox']:checked");
	}

	/** 选择所有*/
	function selectAll(ths){
		$("[type=checkbox]").attr("checked",!!$(ths).attr("checked"))
	}

	/**
	 * 进行ajax请求的类
	 * 
	 * @return
	 */
	var AT = new AjaxTool();
	function AjaxTool() {
		/**
		 * get方式提交数据
		 */
		this.get = function(url, func, showMask) {
			if(showMask == 'undefined' || showMask == null){
				showMask = true;
			}
			if (url.indexOf("?") > 0) {
				url += "&_rad=" + new Date().getTime();
			} else {
				url += "?_rad=" + new Date().getTime();
			}
			if(showMask){
				$("#progressBar").show();
			}
			var options = {
				async : true,
				cache : false,
				url : url,
				type : "get",
				success : function(data, textStatus) {
					func(data);
					$("#progressBar").hide();
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					$("#progressBar").hide();
					MSG.alert("请求出错！");
				}
			};
			$.ajax(options);
		}

		this.load = function(divID,url,func) {
			if (url.indexOf("?") > 0) {
				url += "&_rad=" + new Date().getTime();
			} else {
				url += "?_rad=" + new Date().getTime();
			}
			$("#progressBar").show();
			var options = {
				async : true,
				cache : false,
				url : url,
				type : "get",
				success : function(data, textStatus) {
					$("#"+divID).html(data);
					func();
					$("#progressBar").hide();
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					$("#progressBar").hide();
					MSG.alert("请求出错！");
				}
			};
			$.ajax(options);
		}

		/**
		 * post方式提交
		 * 
		 * @return
		 */
		this.post = function(url, dataParam, func) {
			if (url.indexOf("?") > 0) {
				url += "&_rad=" + new Date().getTime();
			} else {
				url += "?_rad=" + new Date().getTime();
			}
			$("#progressBar").show();
			var options = {
				async : true,
				cache : false,
				data : dataParam,
				url : url,
				type : "POST",
				success : function(data, textStatus) {
					func(data);
					$("#progressBar").hide();
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					$("#progressBar").hide();
					MSG.alert("请求出错！");
				}
			};
			$.ajax(options);
		}

		/**
		 * 提交表单
		 * 
		 * @return
		 */
		this.postFrm = function(formId, func,isValid) {
			var $form = $("#"+formId);
			var valid=isValid?$form.valid():true;
			if (valid) {
				$("#progressBar").show();
				var param = $form.serializeArray();
				var url=$form.attr("action");
				var options = {
						async : true,
						cache : false,
						data : param,
						url : url,
						type : "POST",
						success : function(data, textStatus) {
							func(data);
							$("#progressBar").hide();
						},
						error : function(XMLHttpRequest, textStatus, errorThrown) {
							alert(textStatus);
							alert(errorThrown);
							$("#progressBar").hide();
							alert("请求出错！");
						}
				};
				$.ajax(options);
			}
			return false;
		}
	}


	/**消息处理*/
	var MSG = new Message();
	function Message() {
		var xddialog=new xdDialog('${pageContext.request.contextPath}');
		this.alert=function(msg,title,width,OKFuncName){
			xddialog.alert(msg,title,width,OKFuncName);
		};
		this.confirm=function(message,title,okfunc,cancelfuncUser){
			xddialog.confirm(message,title,okfunc,cancelfuncUser);
		}
	}
	
</script> 