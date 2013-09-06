
function xdDialog(site){
	var website=site;
	/**
	 * alert弹出框
	 * @param message 提示信息
	 * @param title 弹出框的标题
	 * @param width 弹出框的宽度
	 * @param okfuncUser 点击确定按钮时需要执行的函数对象
	 */
	this.alert=function(message,title,width,okfuncUser){
		if($("#dialog_float_frame2")[0]){
			closeXDAlert();
		}
		if(!title||$.trim(title)==""){
			title="提示信息";
		}
		var widthstyle="";
		if(width&&width!=""){
			widthstyle="style='width:"+width+"px'";
		}
		/*点击确定按钮需要执行的方法*/
		var okfunc=function(){
			if("function"==(typeof okfuncUser)){
				okfuncUser();
			}
			$("#dialog_float_frame2").remove();
		}
		var alertHtml="";
		alertHtml=alertHtml+"<div class='float_frame2' id='dialog_float_frame2' style='z-index:9999999999999;'>";
		alertHtml=alertHtml+"	<div class='shadow W2' id='dialog_float_frame2_alertshadow' "+widthstyle+">";
		alertHtml=alertHtml+"	  <div class='contents'> ";
		alertHtml=alertHtml+"		<div class='float_frame_title'>";
		alertHtml=alertHtml+"		  <div class='left'>"+title+"</div>";
		alertHtml=alertHtml+"		  <div class='right'><a href='javascript:;' id='close_icon'><img src='"+website+"/images/cancel.png' width='16' height='16' /></a></div>";
		alertHtml=alertHtml+"		  <div class='clear'></div>";
		alertHtml=alertHtml+"		</div>";
		alertHtml=alertHtml+"		<div class='float_frame_lists'>";
		alertHtml=alertHtml+"		  <table width='100%' border='0' cellspacing='0' cellpadding='2'>";
		alertHtml=alertHtml+"			<tr>";
		alertHtml=alertHtml+"			  <td align='left' class='Height1'>&nbsp;&nbsp;<img src='"+website+"/images/warning.png' width='16' height='14'/><strong>"+message+"</strong></td>";
		alertHtml=alertHtml+"			</tr>";
		alertHtml=alertHtml+"			<tr>";
		alertHtml=alertHtml+"			  <td align='center'><input name='input9' id='dialog_float_frame2_ok_button' type='button' class='botton_style3' value='确定'/></td>";
		alertHtml=alertHtml+"			</tr>";
		alertHtml=alertHtml+"		  </table>";
		alertHtml=alertHtml+"		</div>";
		alertHtml=alertHtml+"	  </div>";
		alertHtml=alertHtml+"	</div>";
		alertHtml=alertHtml+"</div>";
		$("#mainFrame").append($(alertHtml));
		$("#dialog_float_frame2_ok_button").focus();
		$("#dialog_float_frame2_ok_button").bind("click",okfunc);
		$("#close_icon").bind("click",function(){
			$("#dialog_float_frame2").remove();
		});
	}
	
	/**
	 * confirm弹出框
	 * @param message 提示信息
	 * @param title 标题
	 * @param okfuncUser 点击确定时需要执行的函数对象
	 * @param cancelfuncUser 点击取消时需要执行的函数对象
	 */
	this.confirm=function(message,title,okfuncUser,cancelfuncUser){
		if($("#dialog_float_frame2")[0]){
			closeXDAlert();
		}
		if(!title||$.trim(title)==""){
			title="提示信息";
		}
		if("function"!=(typeof okfunc)&&okfunc){
			alert("第三个参数需要传入一个function对象或者null！");
			return;
		}
		var okfunc=function(){
			if("function"==(typeof okfuncUser)){
				okfuncUser();
			}
			$("#dialog_float_frame2").remove();
		}
		var cancelfunc=function(){
			if("function"==(typeof cancelfuncUser)){
				cancelfuncUser();
			}
			$("#dialog_float_frame2").remove();
		}
		var confirmHTML="";
		confirmHTML=confirmHTML+"<div class='float_frame2' id='dialog_float_frame2'>";
		confirmHTML=confirmHTML+"	<div class='shadow W1'>";
		confirmHTML=confirmHTML+"	  <div class='contents'> ";
		confirmHTML=confirmHTML+"		<div class='float_frame_title'>";
		confirmHTML=confirmHTML+"		  <div class='left'>"+title+"</div>";
		confirmHTML=confirmHTML+"		  <div class='right'><a href='javascript:;' id='close_icon'><img src='"+website+"/images/cancel.png' width='16' height='16' /></a></div>";
		confirmHTML=confirmHTML+"		  <div class='clear'></div>";
		confirmHTML=confirmHTML+"		</div>";
		confirmHTML=confirmHTML+"		<div class='float_frame_lists'>";
		confirmHTML=confirmHTML+"		  <table width='100%' border='0' cellspacing='0' cellpadding='2'>";
		confirmHTML=confirmHTML+"			<tr>";
		confirmHTML=confirmHTML+"			  <td align='left' class='Height1'>&nbsp;&nbsp;<img src='"+website+"/images/warning.png' width='16' height='14' /> <strong>"+message+"</strong></td>";
		confirmHTML=confirmHTML+"			</tr>";
		confirmHTML=confirmHTML+"			<tr>";
		confirmHTML=confirmHTML+"			  <td align='center'>";
		confirmHTML=confirmHTML+"				<input name='input9' type='button' class='botton_style3' value='确定' id='dialog_float_frame2_confirm_ok'/>";
		confirmHTML=confirmHTML+"				<input name='input9' type='button' class='botton_style2' value='取消' id='dialog_float_frame2_confirm_cancel'/></td>";
		confirmHTML=confirmHTML+"			</tr>";
		confirmHTML=confirmHTML+"		  </table>";
		confirmHTML=confirmHTML+"		</div>";
		confirmHTML=confirmHTML+"	  </div>";
		confirmHTML=confirmHTML+"	</div>";
		confirmHTML=confirmHTML+"</div>";
		$("#mainFrame").append($(confirmHTML));
		$("#dialog_float_frame2_confirm_ok").bind("click",okfunc);
		$("#dialog_float_frame2_confirm_cancel").bind("click",cancelfunc);
		$("#dialog_float_frame2_confirm_ok").focus();
		$("#close_icon").bind("click",function(){
			$("#dialog_float_frame2").remove();
		});
	}
}
