var clickIp = "lan_group"; 
$(function(){

	$("#send").on('click',function(){
		send();
	});
	startWebSocket();
	$(document).keydown(function(e){
		if(e.keyCode == 13){
			$("#send").click();
		}
	});
	loadProperties();
	//选择的聊天好友
	$(document).on("click",function(e){
		var $clickTarget = $(e.target).closest(".data_id");
		var name = $clickTarget.attr('id');
		var ip = $clickTarget.attr('ip');
		if(!ip) return;
		console.log(name,ip);
		$("#chatFriend").html(name);
		clickIp = ip;
	});
});

function loadProperties(){
	jQuery.i18n.properties({
		name:'ip_search_name',
		path:'resources/',
		mode:'map',
		callback:function(e){
			var map = $.i18n.map;
			var i = 0;
			for(var key in map ){
				var friends = '<div id='+map[key]+' ip='+key+' class="data_id">'+
					'<div class="chat_item slide-left ng-scope top" >'+
						'<div class="ext">'+
							'<p class="attr ng-binding"></p>'+
						'</div>'+
						'<div class="avatar">'+
							'<img class="img" src="Img/'+(++i)+'.jpg" >'+
						'</div>'+
						'<div class="info">'+
							'<h3 class="nickname">'+
								'<span class="nickname_text ng-binding">'+map[key]+'</span>'+
							'</h3>'+
						'</div>'+
					'</div>'+
				'</div>';
				$("#friendLists").append(friends);
			}
		}
	});
}


var ws = null;
function startWebSocket(){
		var path = "ws://localhost:8080/webSocket3/websocket";
		var ip = global.ip;
		if('WebSocket' in window){
			if(ip == null){
				ws = new WebSocket(path);
			}else{
				ws = new WebSocket(path + "/" + ip);
			}
		}else if('MozWebSocket' in window){
			ws = new MozWebSocket(path);
		}else{
			alert("not support");
		}

		ws.onerror=function(){
			setMessageInnerHTML("error");
		};
		
		ws.onopen = function(){
			var username = document.getElementById('username').value;
			setMessageInnerHTML( username + "@~连接聊天室！");
		};
		ws.onmessage = function(event){
			setMessageInnerHTML(event.data);
		};
		ws.onclose = function(){
			console.log("ws状态："+ws.readyState);
			var username = document.getElementById('username').value;
			setMessageInnerHTML(username + "@~退出聊天室！");
			console.log("ws状态："+ws.readyState);
		};
		window.onbeforeunload = function(){
			ws.close();
		};
	} 

function setMessageInnerHTML(memberMsg){
	//1.昵称,2.聊天内容,3.头像
//	var memberMsg =["昵称",memberMsg,3];
	var memberMsg = memberMsg.split("@~");
	if(memberMsg[2] == undefined || memberMsg[2] == null){
		memberMsg[2] = 3;
	}
	var leftmsg='<div class="ng-scope">'+
			        '<div class="clearfix" >'+
						'<div style="overflow: hidden;" >'+
						    '<div class="message ng-scope you" >'+
					        	'<div class="message_system ng-scope">'+
					        		'<div class="content ng-binding ng-scope">'+(getNowFormatDate())+'</div>'+
								'</div>'+
								'<img class="avatar" src="Img/'+memberMsg[2]+'.jpg" title="" >'+
								'<div class="content">'+
										'<h4 class="nickname ng-binding ng-scope">'+memberMsg[0]+'</h4>'+
										'<div class="bubble js_message_bubble ng-scope bubble_default left" >'+
										'<div class="bubble_cont ng-scope" >'+
											'<div class="plain">'+
						                        '<pre class="js_message_plain ng-binding" >'+memberMsg[1]+'</pre>'+
						                    '</div>'+
						                '</div>'+
						            '</div>'+
						        '</div>'+
						    '</div>'+
						'</div>'+
					'</div>'+
				'</div>';
	
	$("#msgInner").append(leftmsg);
	//滚动条置底
	var totalHeright = 0;
	$("#msgInner > .ng-scope").each(function(i){
		totalHeright += $(this).outerHeight(true);
	});
	$("#scrollTop").scrollTop(totalHeright);
}
function closeWebSocket(){
	console.log("ws状态：" + ws.readyState);
	ws.close();
}
//webSocket连接服务器
function openWebSocket(){
	if(ws.readyState == 3){
		startWebSocket();
	}else{
		alert("已连接服务器，请不要重复连接！");
	}
}
function send(){
	var username = document.getElementById('username').value;
	var message = $("#editArea").html();
	if($.trim(message) != ""){
		ws.send( username + "~@" + message + "~@" + clickIp);
		$("#editArea").html("");
	}else{
		$("#editArea").html("");
		$("#editArea").attr("placeholder","聊天内容不能为空！");
	}
}
function getNowFormatDate(){
	var myDate = new Date();
	return myDate.toLocaleDateString();
}
