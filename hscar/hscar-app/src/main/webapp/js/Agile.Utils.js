var Agile;
if (!Agile) {
	Agile = new Object();
}
if (!Agile.Utils) {
	Agile.Utils = new Object();
}

Agile.Utils.ns = function(path) {
	var arr = path.split(".");
	var ns = "";
	for (var i = 0; i < arr.length; i++) {
		if (i > 0) {
			ns += ".";
		}
		ns += arr[i];
		eval("if(typeof(" + ns + ")=='undefined')" + ns + " = new Object();");
	}
};

Agile.Utils.convertVal = function(val) {
	if (val == "0") {
		return "0";
	}
	return (!val) ? "" : (val + "").replace(/(^\s*)|(\s*$)/g, "");
};

Agile.Utils.trim = function(val) {
	if (!val) {
		return "";
	}
	return (val + "").replace(/(^\s*)|(\s*$)/g, "");
};

Agile.Utils.isImage = function(path) {
	var typeArr = [ ".jpg", ".jpeg", ".gif", ".bmp", ".png" ];
	return Agile.Utils.isEndWith(path, typeArr);
};

Agile.Utils.isExcel2003 = function(path) {
	var typeArr = [ ".xls" ];
	return Agile.Utils.isEndWith(path, typeArr);
};

Agile.Utils.isEndWith = function(path, typeArr) {
	var type = path.substring(path.lastIndexOf("."));
	type = type.toLowerCase();
	return (typeArr.join("").indexOf(type) != -1);
};

Agile.Utils.checkAllBox = function(obj, name) {
	$("input[:checkbox][type='checkbox'][name^='" + name + "']").attr(
			"checked", obj.checked);
};

// 从当前下拉框列表中返回中文描述 [OPTION数组，下拉框值]
Agile.Utils.getSelectText = function(optId, optVal) {
	var elements = $("#" + optId).find("option");
	var optText = "";
	$.each(elements, function(index, element) {
		if (element.value == optVal) {
			optText = element.text;// 获得中文描述
			return false;// break;
		}
	});
	return optText;
};

Agile.Utils.getCheckedObj = function(name) {
	var arr = $("input:checked[:checkbox][type='checkbox'][name^='" + name
			+ "']");
	if (arr.length != 1) {
		alert("请先选中一条记录");
		return false;
	}
	return arr[0].value;
};

Agile.Utils.emptyForm = function(formObj, ignoreTypeArr) {
	if (!ignoreTypeArr) {
		ignoreTypeArr = new Array();
	}
	ignoreTypeArr[ignoreTypeArr.length] = "button";
	ignoreTypeArr[ignoreTypeArr.length] = "submit";
	ignoreTypeArr[ignoreTypeArr.length] = "reset";
	ignoreTypeArr[ignoreTypeArr.length] = "hidden";
	var findElementType = "input,select,textarea";// 表单中查找的元素标签
	var elements = formObj.find(findElementType);
	$.each(elements, function(index, element) {
		if (jQuery.inArray(element.type, ignoreTypeArr) != -1) {
			return true;// continue;
		}
		if (element.type == "check" || element.type == "radio"
				|| element.type == "checkbox") {
			element.checked = false;
		} else {
			if (element.type.indexOf("select") != -1) {
				element.selectedIndex = 0;
			} else {
				$(element).val("");
			}
		}
	});
};

Agile.Utils.readonlyForm = function(formObj, ignoreTypeArr) {
	if (!ignoreTypeArr) {
		ignoreTypeArr = new Array();
	}
	ignoreTypeArr[ignoreTypeArr.length] = "button";
	ignoreTypeArr[ignoreTypeArr.length] = "submit";
	ignoreTypeArr[ignoreTypeArr.length] = "reset";
	var findElementType = "input,select,textarea";// 表单中查找的元素标签
	var elements = formObj.find(findElementType);
	$.each(elements, function(index, element) {
		if (jQuery.inArray(element.type, ignoreTypeArr) != -1) {
			return true;// continue;
		}
		element.readonly = true;
		element.disabled = true;
	});
};

Agile.Utils.enableForm = function(formObj, ignoreIdsArr) {
	if (!ignoreIdsArr) {
		ignoreIdsArr = new Array();
	}
	var ignoreTypeArr = new Array();
	ignoreTypeArr[ignoreTypeArr.length] = "button";
	ignoreTypeArr[ignoreTypeArr.length] = "submit";
	ignoreTypeArr[ignoreTypeArr.length] = "reset";
	var findElementType = "input,select,textarea";// 表单中查找的元素标签
	var elements = formObj.find(findElementType);
	$.each(elements, function(index, element) {
		if (jQuery.inArray(element.type, ignoreTypeArr) != -1) {
			return true;// continue;
		}
		if (jQuery.inArray(element.id, ignoreIdsArr) != -1) {
			return true;// continue;
		}
		element.readonly = false;
		element.disabled = false;
	});
};

var datepicker_CurrentInput;
Agile.Utils.datepicker = function(objId, dateFormat) {
	var obj = null;
	if (objId instanceof jQuery) {
		obj = objId;
	} else if (jQuery.type(objId) === "string") {
		obj = $("#" + objId);
	} else {
		obj = $(objId);
	}
	obj.datepicker({
		changeMonth : true,
		changeYear : true,
		showButtonPanel : true,
		closeText : '清除',
		beforeShow : function(input, inst) {
			datepicker_CurrentInput = input;
		}
	});
	$(".ui-datepicker-close").live("click", function() {
		datepicker_CurrentInput.value = "";
	});
}

Agile.Utils.datetimepicker = function(objId, timeFormat) {
	var obj = null;
	if (objId instanceof jQuery) {
		obj = objId;
	} else if (jQuery.type(objId) === "string") {
		obj = $("#" + objId);
	} else {
		obj = $(objId);
	}
	obj.datetimepicker({
		controlType : 'select',
		timeFormat : timeFormat,
		changeMonth : true,
		changeYear : true,
		showButtonPanel : true,
		closeText : '清除',
		beforeShow : function(input, inst) {
			datepicker_CurrentInput = input;
		}
	});
	$(".ui-datepicker-close").live("click", function() {
		datepicker_CurrentInput.value = "";
	});
}

Agile.Utils.getCalendarProperty = function(dateFormat) {
	if (!dateFormat) {
		dateFormat = "yyyy-MM-dd hh:mm:ss";
	}
	var needTime = (dateFormat.length > "yyyy-MM-dd".length) ? true : false;
	var property = {
		divId : "demoCalendar",// 日历控件最外层DIV的ID
		needTime : needTime,// 是否需要显示精确到秒的时间选择器，即输出时间中是否需要精确到小时：分：秒 默认为FALSE可不填
		yearRange : [ 1930, 2030 ],// 可选年份的范围,数组第一个为开始年份，第二个为结束年份,如[1970,2030],可不填
		week : [ '日', '一', '二', '三', '四', '五', '六' ],// 数组，设定了周日至周六的显示格式,可不填
		month : [ '一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月',
				'十一月', '十二月' ],// 数组，设定了12个月份的显示格式,可不填
		format : dateFormat
	/* 设定日期的输出格式,可不填 */
	};
	return property;
};

Agile.Utils.alert = function(message, dialogcloseCallBack, title) {
	var b = $('<div><p><span class="ui-icon ui-icon-alert" style="float: left; margin: 0 7px 20px 0;"></span>'
			+ message + '</p></div>');
	if (title) {
		b.attr('title', title);
	} else {
		b.attr('title', '网页消息');
	}
	b.attr('top', ($(window).height() - b.height()) / 2);
	b.attr('left', ($(window).width() - b.width()) / 2);
	b.dialog({
		resizable : false,
		height : 180,
		modal : true,
		show : {
			delay : 100
		},
		buttons : [ {
			text : "确定",
			click : function() {
				$(this).dialog("close");
			}
		} ]
	});
	b.on("dialogclose", function(event, ui) {
		if (dialogcloseCallBack) {
			dialogcloseCallBack.call(b, b);
		}
	});
};

Agile.Utils.confirm = function(message, okCallback, cancelCallback, title) {
	var b = $('<div><p><span class="ui-icon ui-icon-alert" style="float: left; margin: 0 7px 20px 0;"></span>'
			+ message + '</p></div>');
	if (title) {
		b.attr('title', title);
	} else {
		b.attr('title', '网页消息');
	}
	b.dialog({
		resizable : false,
		height : 140,
		modal : true,
		show : {
			delay : 100
		},
		buttons : [ {
			text : "确定",
			click : function() {
				if (okCallback) {
					okCallback.call(b, b);
				}
				$(this).dialog("close");
			}
		}, {
			text : "取消",
			click : function() {
				if (cancelCallback) {
					cancelCallback.call(b, b);
				}
				$(this).dialog("close");
			}
		} ]
	});
};

/**
 * http://IP:端口
 */
Agile.Utils.origin = function() {
	var origin = window.document.location.origin;
	return origin;
}

/**
 * "http:"
 */
Agile.Utils.protocol = function() {
	var protocol = window.document.location.protocol;
	return protocol;
}

/**
 * "IP:端口"
 */
Agile.Utils.host = function() {
	var host = window.document.location.host;
	return host;
}

/**
 * "IP"
 */
Agile.Utils.hostname = function() {
	var hostname = window.document.location.hostname;
	return hostname;
}

/**
 * "端口"
 */
Agile.Utils.port = function() {
	var port = window.document.location.port;
	return port;
}

/**
 * "项目名称"
 */
Agile.Utils.projectName = function() {
	var pathName = window.document.location.pathname;
	var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
	return projectName;
}
