jQuery.validator.addMethod("firstNotNumber", function(value, element) {       
    var length = value.length;   
    var mobile =/^[a-zA-Z\u4e00-\u9fa5|_]{1}/;   
    return this.optional(element) || ( mobile.test(value));       
}, "不能以数字或除\"_\"以外的特殊符开头");

jQuery.validator.addMethod("firstIsletter", function(value, element) {       
   var length = value.length;
   var mobile =/^[a-zA-Z]{1}/;   	
   return this.optional(element) || ( mobile.test(value));       
}, "只能以字母开头");

jQuery.validator.addMethod("equalPwd", function(value, element,param) {       
	   return this.optional(element) || (value == $(param).val());       
	}, "输入的原始密码不正确");
