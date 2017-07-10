Agile.alert = function(message, closeCallBack, timeout) {
	$.blockUI.opener = "alert";
	$.blockUI({
		message : "<h3>" + message + "</h3>",
		css : {
			border : 'none',
			padding : '10px',
			width : '250px',
			top : ($(document).height() - 100) / 2 + 'px',
			left : ($(document).width() - 250) / 2 + 'px',
			backgroundColor : '#000',
			'-webkit-border-radius' : '5px',
			'-moz-border-radius' : '5px',
			opacity : 0.8,
			color : '#fff'
		},
		overlayCSS : {
			backgroundColor : '#fff',
			opacity : 0
		},
		timeout : timeout ? timeout : 2000,
		onOverlayClick : $.unblockUI,
		onUnblock : function() {
			if (closeCallBack) {
				closeCallBack.call();
			}
		},
		ignoreIfBlocked : false
	});

}