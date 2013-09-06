/**
 * 用于生成下拉框
 * 
 * @author zhangjianbing@msn.com
 */
var divSelect = new divSelect();
function divSelect() {
	var first = {
		name : '请选择...',
		value : ''
	};
	var datas = new Array();
	var selectFunction;
	var site = "";
	var width = 100;

	/** 初始化* */
	this.init = function() {
		first = {
			name : '请选择...',
			value : ''
		};
		datas = new Array();
		selectFunction;
		site = "";
		width = 100;
	}

	/** 第一行显示的数据** */
	this.setFirstData = function(data) {
		if (data) {
			first = data;
		}
	}

	/** 站点* */
	this.setSite = function(path) {
		site = path;
	}

	/** 选中之后的回到函数* */
	this.setSelectFunction = function(func) {
		selectFunction = func;
	}

	/** *添加数据* */
	this.addData = function(data) {
		datas[datas.length] = data;
	}

	/** *添加一行数据* */
	this.fixTr = function(data) {
		var content = "<tr xdvalue='"
				+ data.value
				+ "' xdname='"
				+ data.name
				+ "' onclick='divSelect.select(this);' onmouseover='divSelect.overTr(this)' onmouseout='divSelect.outTr(this)' style='cursor: pointer;'>"
				+ "<td>" + data.name + "</td></tr>";
		return content;
	}

	/** *渲染数据** */
	this.render = function(div, width) {
		var inputwidth = width - 20;
		var divContentp = "<div class='selctDiv' thevalue='"
				+ first.value
				+ "' showname='"
				+ first.name
				+ "'><div class ='box' style='width:"
				+ width
				+ "px'><div style='float:left'><input type='text' value='"
				+ first.name
				+ "' style='width:"
				+ inputwidth
				+ "px;border:0px;cursor:pointer'  class='showname' readonly='readonly' onclick='divSelect.textSelect(this)'/></div><div style='float:right;margin-top: 1px'><img alt='' src='"
				+ site
				+ "/admin/image/arrow.png'/></div></div><div style='position:absolute;background-color:#FFFFFF;z-index:1000;display:none;'  class='slctValue'><table style='font-size: 12px;border:solid 1px #cad9ea;border-collapse: collapse;' width='100%'><tbody>";
		divContentp = divContentp + this.fixTr(first);
		for ( var i = 0; i < datas.length; i++) {
			divContentp = divContentp + this.fixTr(datas[i]);
		}
		divContentp = divContentp + "</tbody></table></div></div>";
		$(div).html(divContentp);
	}

	/** *生成下拉列表div*** */
	this.textSelect = function(obj) {
		var $div = $(obj).parent().parent().parent();
		var selectVal = $div.attr("thevalue");
		var height = $(obj).css("height");
		var width = $(obj).css("width");
		var top = $(obj).position().top;
		var left = $(obj).position().left;
		var sltWidth = $(obj).parent().parent().css("width");
		var $selectDiv = $(obj).parent().parent().parent().find(
				"div.slctValue:first");
		if ($selectDiv.css("display") == "block"
				|| $selectDiv.css("display") == "BLOCK") {
			$selectDiv.hide();
		} else {
			$selectDiv.css("left", left - 1);
			$selectDiv.css("top", top + 18);
			$selectDiv.css("width", sltWidth);
			$selectDiv.find("tr").each(function() {
				var $tr = $(this);
				if ($tr.attr("xdvalue") == $div.attr("thevalue")) {
					$tr.addClass("trSlt");
				} else {
					$tr.removeClass("trOver");
					$tr.removeClass("trSlt");
					$tr.addClass("trOut");
				}
			});
			$selectDiv.show();
		}
	}

	/** 选择一行数据* */
	this.select = function(obj) {
		var $div = $(obj).parent().parent().parent().parent();
		$div.attr("thevalue", $(obj).attr("xdvalue"));
		$div.attr("showname", $(obj).attr("xdname"));
		$div.find(".showname").each(function() {
			$(this).val($(obj).attr("xdname"));
		});
		$(obj).parent().parent().parent().hide();
		if (selectFunction) {
			eval(selectFunction + "('" + $(obj).attr("xdvalue") + "','"
					+ $(obj).attr("xdname") + "')");
		}
	}

	/** 鼠标在选择行上时修该改行样式** */
	this.overTr = function(obj) {
		$(obj).removeClass("trOut");
		$(obj).addClass("trOver");
	}

	/** 鼠标移出选择行是改变行样式** */
	this.outTr = function(obj) {
		$(obj).removeClass("trOver");
		$(obj).addClass("trOut");
	}

	/** 获取数据** */
	this.getVal = function(div) {
		var $div = $(div).find(".selctDiv:first");
		return {name:$div.attr("showname"),value:$div.attr("thevalue")};
	}
}