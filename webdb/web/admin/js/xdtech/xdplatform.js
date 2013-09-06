var AT = new AjaxTool();
var XDUI = new UIUtil();
var XDMSG = new XDMessage();
var XDT = new XdTool();
var treeUtil = new PaltformDhtmlxTree();
var xdcrawlertimer;
var xddbcrawlertimer;
var statuestimer;
/**
 * 进行ajax请求的类
 * 
 * @return
 */
function AjaxTool() {
	/**
	 * get方式提交数据
	 */
	this.get = function(url, func) {
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
				func(data);
				$("#progressBar").hide();
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$("#progressBar").hide();
				alertMsg.info("请求出错！");
			}
		};
		$.ajax(options);
	}

	/**
	 * post方式提交
	 * 
	 * @return
	 */
	this.post = function(url, cdata, func, dataParam) {
		if (url.indexOf("?") > 0) {
			url += "&_rad=" + new Date().getTime();
		} else {
			url += "?_rad=" + new Date().getTime();
		}
		$("#progressBar").show();
		var opts = "";
		if (dataParam == null&& cdata instanceof Array) {
			for ( var i = 0; i < cdata.length; i++) {
				if (opts.length > 0) {
					opts = opts + ",";
				}
				opts = opts + "'" + cdata[i].name + "':'"
						+ cdata[i].value.replace(/\\/g, "\\\\") + "'";
			}
			dataParam = eval("({" + opts + "})");
		} else if (cdata instanceof jQuery) {
			dataParam = cdata;
		}
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
				alertMsg.info("请求出错！");
			}
		};
		$.ajax(options);
	}

	/**
	 * 提交表单
	 * 
	 * @return
	 */
	this.postFrm = function(form, func) {
		var $form = $(form);
		if ($form.valid()) {
			$("#progressBar").show();
			var param = $form.serializeArray();
			$.post($form.attr("action"), param, function(data) {
				func(data);
				$("#progressBar").hide();
			});
		}
		return false;
	}
	/**
	 * 刷新分页区的div
	 * 
	 * @param pageFormId
	 * @param listDivId
	 * @return
	 */
	this.freshPageList = function(pageFormId, listDivId, initF) {
		this.postFrm($("#" + pageFormId)[0], function(data) {
			$("#" + listDivId).html(data);
			if (initF == null || initF) {
				$("#" + listDivId).initUI(listDivId);
			}
		});
	}
}

function XDMessage() {
	this.confirm = function(message, okFun, cFun) {
		alertMsg.confirm(message, {
			okCall : okFun,
			cancelCall : cFun
		});
	}

	this.error = function(message) {
		alertMsg.error(message);
	}

	this.info = function(message) {
		alertMsg.info(message)
	}
}

/**
 * 跟样式有关的js
 * 
 * @return
 */
function UIUtil() {
	/**
	 * 渲染表格样式
	 * 
	 * @param boxId
	 *            表格的父节点ID，如果不输入，则按照document算
	 * @return
	 */
	this.initTable = function(boxId) {
		var $p = document.getElementById(boxId) || document;
		$("table.table", $p).jTable();
		$('table.list', $p).cssTable();
		$($p).find("[layoutH]").layoutH();
	}

	/**
	 * 渲染弹出框
	 * 
	 * @param boxId
	 * @return
	 */
	this.initDialog = function(boxId) {
		var $p = document.getElementById(boxId) || document;
		$("a[target=dialog]", $p)
				.each(
						function() {
							$(this)
									.click(
											function(event) {
												var $this = $(this);
												var title = $this.attr("title")
														|| $this.text();
												var rel = $this.attr("rel")
														|| "_blank";
												var options = {};
												var w = $this.attr("width");
												var h = $this.attr("height");
												if (w)
													options.width = w;
												if (h)
													options.height = h;
												options.max = eval($this
														.attr("max")
														|| "false");
												options.mask = eval($this
														.attr("mask")
														|| "false");
												options.maxable = eval($this
														.attr("maxable")
														|| "true");
												options.minable = eval($this
														.attr("minable")
														|| "true");
												options.fresh = eval($this
														.attr("fresh")
														|| "true");
												options.resizable = eval($this
														.attr("resizable")
														|| "true");
												options.drawable = eval($this
														.attr("drawable")
														|| "true");
												options.close = eval($this
														.attr("close")
														|| "");
												options.param = $this
														.attr("param")
														|| "";

												var url = unescape(
														$this.attr("href"))
														.replaceTmById(
																$(event.target)
																		.parents(
																				".unitBox:first"));
												DWZ.debug(url);
												if (!url.isFinishedTm()) {
													alertMsg
															.error($this
																	.attr("warn")
																	|| DWZ
																			.msg("alertSelectMsg"));
													return false;
												}
												$.pdialog.open(url, rel, title,
														options);
												return false;
											});
						});
	}

	/**
	 * 渲染分页UI
	 * 
	 * @param boxId
	 * @return
	 */
	this.initPageDiv = function(boxId) {
		var $p = document;
		var box = document.getElementById(boxId);
		if (box) {
			$p = box;
		}

		$("div.panelBar li, div.panelBar", $p).hoverClass("hover");
		$("div.pagination", $p).each(function() {
			var $this = $(this);
			$this.pagination( {
				targetType : $this.attr("targetType"),
				rel : $this.attr("rel"),
				totalCount : $this.attr("totalCount"),
				numPerPage : $this.attr("numPerPage"),
				pageNumShown : $this.attr("pageNumShown"),
				currentPage : $this.attr("currentPage"),
				xdfunc : $this.attr("xdfunc")
			});
		});
	}

	this.initValidateForm = function(boxId) {
		var $p = document.getElementById(boxId) || document;
		$("form.required-validate", $p).each(function() {
			$(this).validate( {
				focusInvalid : false,
				focusCleanup : true,
				errorElement : "span",
				ignore : ".ignore",
				invalidHandler : function(form, validator) {
					var errors = validator.numberOfInvalids();
					if (errors) {
						var message = DWZ.msg("validateFormError", [ errors ]);
						alertMsg.error(message);
					}
				}
			});
		});
	}

	this.initPage = function(boxId) {
		initUI(document.getElementById(boxId));
	}
	
	this.changeDivHeight = function(divId, plus) {
		if (plus == null) {
			plus = 360;
		}
		var height = $(window).height() - plus;
		$("#" + divId).height(height);
	}
	this.initEditor=function(boxId){
		var $p = document.getElementById(boxId) || document;
		$("textarea.editor", $p).each(function(){
			var $this = $(this);
			var op = {html5Upload:false, skin: 'vista',tools: $this.attr("tools") || 'full'};
			var upAttrs = [
				["upLinkUrl","upLinkExt","zip,rar,txt"],
				["upImgUrl","upImgExt","jpg,jpeg,gif,png"],
				["upFlashUrl","upFlashExt","swf"],
				["upMediaUrl","upMediaExt","avi"]
			];
			
			$(upAttrs).each(function(i){
				var urlAttr = upAttrs[i][0];
				var extAttr = upAttrs[i][1];
				if ($this.attr(urlAttr)) {
					op[urlAttr] = $this.attr(urlAttr);
					op[extAttr] = $this.attr(extAttr) || upAttrs[i][2];
				}
			});
			$this.xheditor(op);
		});
	}
}

/**
 * 公用的帮助方法
 * 
 * @return
 */
function XdTool() {
	this.checkAll = function(checkBox, checkName) {
		$(document.getElementsByName(checkName)).each(function() {
			this.checked = checkBox.checked;
		});
	}
	/**
	 * 为当前tab页的url增加参数
	 * 
	 * @param data
	 * @return
	 */
	this.addParamtsToURL = function(data) {
		var curTab = (navTab._getTabs().eq(navTab._currentIndex));
		var url = curTab.attr("initurl");
		if (!url || url == "") {
			url = curTab.attr("url");
			curTab.attr('initurl', url);// 保存最开始的url
		}
		if (url.indexOf('?') != -1) {
			url += '&' + data;
		} else {
			url += '?' + data;
		}
		curTab.attr('url', url);
	}
}

/**
 * 树操作类
 * 
 * @return
 */
function PaltformDhtmlxTree() {
	/**
	 * 获取树对象
	 * 
	 * @param divId：树显示的divID
	 * @param dragAble：是否可以拖拽
	 * @return
	 */
	this.getTree = function(divId, dragAble, checkbox) {
		var tree = dhtmlXTreeFromHTML(divId, checkbox);
		if (!dragAble) {
			tree.enableDragAndDrop('temporary_disabled');
		}
		return tree;
	}

	/**
	 * 注册点击事件
	 * 
	 * @param tree
	 * @param func
	 * @return
	 */
	this.click = function(tree, func) {
		tree.setOnClickHandler(func);
	}

	/**
	 * 注册拖拽事件
	 * 
	 * @param tree
	 * @param func
	 * @return
	 */
	this.drag = function(tree, func) {
		tree.setDragHandler(func);
	}

	/**
	 * 删除节点
	 * 
	 * @param tree
	 * @param itemId
	 * @param parentSelect：删除节点后树是否选中被删除节点的父节点
	 * @return
	 */
	this.deleteItem = function(tree, itemId, parentSelect) {
		tree.deleteItem(itemId, parentSelect);
	}

	/**
	 * 获得用户自定义属性
	 * 
	 * @param tree
	 * @param itemId
	 * @param name
	 * @return
	 */
	this.getUserData = function(tree, itemId, name) {
		return tree.getUserData(itemId, name);
	}

	/**
	 * 选中一个节点
	 * 
	 * @param tree
	 * @param itemId
	 * @param mode
	 * @param preserve
	 * @return
	 */
	this.selectItem = function(tree, itemId, mode, preserve) {
		tree.selectItem(itemId, mode, preserve);
	}

	/**
	 * 注册checked事件
	 * 
	 * @param tree
	 * @param func
	 * @return
	 */
	this.check = function(tree, useDefaultFunc, func) {
		if (useDefaultFunc) {
			tree.setOnCheckHandler(function(itemId, state) {
				var tempId = itemId;
				if (state == 0) {
					tree.setSubChecked(itemId, 0);
				}
				while (tempId != "1" && tempId != "0" && tempId != "") {
					tempId = tree.getParentId(tempId);
					if (state == 1) {
						tree.setCheck(tempId, state);
					}
				}
				if (window.event) {
					if (window.event.shiftKey) {
						tree.setSubChecked(itemId, state);
					}
				}
			});
		} else {
			tree.setOnCheckHandler(func);
		}
	}
}


/**
 * 拼接分页信息
 * @divId 分页所在的div的ID
 * @func 分页调用的js
 * @curPage 当前的页码
 * @numPerPage 页面尺寸
 * @total 总数
 * @author zhangjianbing@msn.com
 **/
function fixPageDiv(divId,func,curPage,numPerPage,total){
	var showCount=20;//显示多少页，必须是一个偶数
	var pageCount = (total % numPerPage == 0 ? total / numPerPage : Math.ceil(total / numPerPage));//总页数
	var prePge=curPage-1;//上一页页码
	var next=curPage*numPerPage<total?curPage+1:curPage;//下一页页码
	var contentDiv="";
	if(total>0){
		var prePageContent="";//上一页的div
		if(prePge>0){
			prePageContent="<div xdpagenum='"+prePge+"' onclick='"+func+"(this)'>上一页</div>";
		}

		var pageNumDivContent="";
		var preCount=showCount/2;//当前页前方显示数量
		var nexCount=preCount-1;//当前页后方显示数量
		var startPage=nexCount;//开始页码
		var endPage=curPage+pageCount;//结束页码
		for(var i=1;i<=preCount;i++){
			if(curPage==1){
				startPage=curPage;
				break;
			}else{
				if(curPage-i<=0){
					break;
				}else{
					startPage=curPage-i;
				}
			}
		}
		for(var i=1;i<=nexCount;i++){
			if(curPage==pageCount){
				endPage=curPage;
				break;
			}else{
				if(curPage+i<=pageCount){
					endPage=curPage+i;
				}else{
					break;
				}
			}
		}
		for(var i=startPage;i<=endPage;i++){
			var termContent="";
			if(i==curPage){
				termContent="<div xdpagenum='"+i+"' onclick='"+func+"(this)' class='current'>"+i+"</div>";
			}else{
				termContent="<div xdpagenum='"+i+"' onclick='"+func+"(this)'>"+i+"</div>";
			}
			pageNumDivContent+=termContent;
		}
		
		var nextPageContent="";//下一页的div
		if(curPage*numPerPage<total){
			nextPageContent="<div xdpagenum='"+next+"' onclick='"+func+"(this)'>下一页</div>";
		}
		contentDiv=prePageContent+pageNumDivContent+nextPageContent;
	}
	$("#"+divId).html(contentDiv);
}



/**
 * 关闭当前对话框
 * @return
 */
function xdCloseDialog(){
	$.pdialog.closeCurrent();
}

/**
 * 关闭当前页签
 * @return
 */
function xdCloseTab(){
	navTab.closeCurrentTab();
}


function openthefilepath(id){
	var path=$("#"+id+"_filepath").val();
	var url="${pageContext.request.contextPath}/admin/viewordownloadfile.dhtml?path="+encodeURIComponent(path)+"&_rad="+new Date();
	window.open(url);
}