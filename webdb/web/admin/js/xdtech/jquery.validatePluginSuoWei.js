//用户名验证
jQuery.validator.addMethod("userCode", function(value, element) {
	var userCode=/^[0-9a-zA-Z_]{1,}$/;
    if(!userCode.test(value)){
   	 	alertMsg.info(element.title+"录入格式有误!");
    }
    return this.optional(element) || (userCode.test(value));
}, "用户名格式错误");
//IP地址验证
 jQuery.validator.addMethod("ipAddressCode", function(value, element) {
     var ip = /^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$/;
     if(!(ip.test(value) && (RegExp.$1 < 256 && RegExp.$2 < 256 && RegExp.$3 < 256 && RegExp.$4 < 256))){
    	 alertMsg.info(element.title+"录入格式有误!");
     }
     return this.optional(element) || (ip.test(value) && (RegExp.$1 < 256 && RegExp.$2 < 256 && RegExp.$3 < 256 && RegExp.$4 < 256));
 }, "服务器Ip地址格式错误");
 
//数字的验证
 jQuery.validator.addMethod("ftpportCode", function(value, element) {
     var num = /^([0-9]+)$/;
     if(!num.test(value)){
    	 alertMsg.info(element.title+"录入格式有误!");
     }
     return this.optional(element) || (num.test(value));
 }, "服务器端口号只能为数字(0-9)");
 
//路径验证
 jQuery.validator.addMethod("filedirCode", function(value, element) {
     var patrn=/^(\/)/i; 
     if(!patrn.test(value)){
    	 alertMsg.info(element.title+"录入格式有误,要以/开始!");
     }
     return this.optional(element) || (patrn.test(value));
 }, "文件路径格式错误,要以'/'开始!");
 
 //禁止输入汉字
 jQuery.validator.addMethod("tbExtractTemplateCode", function(value, element) {
     var patrn=/^[\u4E00-\u9FA0]+$/; 
     if(patrn.test(value)){
    	 alertMsg.info(element.title+"不能为中文!");
     }
     return this.optional(element) || (patrn.test(value));
 }, "模板编号不能为中文!");
 


