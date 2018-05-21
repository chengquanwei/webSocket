<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh_CN"><head>
<%
String path = request.getContextPath();   
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String address = request.getLocalAddr();
String remoteAddr = request.getRemoteAddr();
%>

<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<style type="text/css">@charset "UTF-8";[ng\:cloak],[ng-cloak],[data-ng-cloak],[x-ng-cloak],.ng-cloak,.x-ng-cloak,.ng-hide{display:none !important;}ng\:form{display:block;}.ng-animate-block-transitions{transition:0s all!important;-webkit-transition:0s all!important;}.ng-hide-add-active,.ng-hide-remove{display:block!important;}</style>
    <title>chat</title>
    <meta name="keywords" content="chat">
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script type="text/javascript">if(window.top!== window.self){top.location=self.location;}</script>
	<link rel="stylesheet" href="css/chat.css">
</head>
<body class="ng-scope ng-isolate-scope loaded">
    <div class="main">
        <div class="main_inner" >
            <div class="panel">
				<div class="header">
					<div class="avatar">
						<img class="img" src="Img/1.jpg">
					</div>
					<div class="info">
						<h3 class="nickname">
							<span class="display_name ng-binding">以手筑城</span>
						</h3>
					</div>
				</div>
				<div class="search_bar" id="search_bar">
					<i class="web_wechat_search"></i>
					<input class="frm_search ng-isolate-scope ng-pristine ng-valid" placeholder="搜索" type="text">
				</div>

				<div class="tab" >
				   
				</div>
				<div class="nav_view ng-scope" style="visibility: visible; width: auto;">
					<div class="scroll-wrapper chat_list scrollbar-dynamic" style="position: relative;">
						<div class="chat_list scrollbar-dynamic scroll-content scroll-scrolly_visible" style="margin-bottom: -17px; margin-right: -17px; height: 494px;">
							<div id="friendLists" class="ng-scope">
								<!-- 好友列表start -->
								<div id="聊天室" ip="lan_group" class="data_id">
									<div class="chat_item slide-left ng-scope top" >
										<div class="ext">
											<p class="attr ng-binding"></p>
										</div>
										<div class="avatar">
											<img class="img" src="Img/4.jpg" >
										</div>
										<div class="info">
											<h3 class="nickname">
												<span class="nickname_text ng-binding">局域网群聊</span>
											</h3>
										</div>
									</div>
								</div><!-- end -->
							</div>
						</div>
					</div>
				</div>
			</div>
         <div style="height:100%;" class="ng-scope">
			<div id="chatArea" class="box chat ng-scope chatRoom">
				<!-- 表头 -->
				<div class="box_hd">
					<div id="chatRoomMembersWrap"></div>
					<div class="title_wrap">
						<div class="title poi">
							<a id="chatFriend" class="title_name ng-binding" >聊天室</a>
							<span class="title_count ng-binding ng-scope"></span>
						</div>
					</div>
				</div>
	<!-- ------------------------start------------------------------------------------------------------------------------------ -->
				<div class="scroll-wrapper box_bd chat_bd scrollbar-dynamic" style="position: absolute;">
					<div id="scrollTop" class="box_bd chat_bd scrollbar-dynamic  scroll-scrolly_visible"  style="margin-bottom: 0px; margin-right: 0px; height: 369px;">  <!-- scroll-content -->
						<div class="ng-scope">
						<div id ="msgInner" class="top-placeholder ng-scope" style="height: 0px;"></div>
							
			        		
			        		<div class="bottom-placeholder ng-scope" style="height: 0px;"></div>
			        	</div>
						<div id="prerender" style="visibility: hidden;position: absolute;width: 100%;top: 333px;height: 0;padding: 0 19px;box-sizing: border-box;margin-left: -19px;overflow: hidden;"></div>
			    	</div>
			    </div>
<!-- ------------------end-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- -->						
				<div class="box_ft ng-scope">
					<div class="toolbar" id="tool_bar">
					</div>
					<div class="content ng-isolate-scope" >
						<pre id="editArea" class="flex edit_area ng-isolate-scope ng-pristine ng-valid" contenteditable="true"></pre>
					</div>
					<div class="action">
						<span class="desc ng-scope">按下Ctrl+Enter换行</span>
						<a id="send" class="btn btn_send">发送</a>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<div style="display: none;">
	<input id="username" type="hidden" value=<%= remoteAddr %> >
</div>
<script type="text/javascript" src="<%=basePath%>js/jquery-1.11.3.js"></script>
<script type="text/javascript" src="<%=basePath%>js/jquery.i18n.properties-min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/chat.js" ></script>
<script type="text/javascript">
	var global={
			"ip":"<%=remoteAddr%>"
		};
</script>
</body>
</html>
