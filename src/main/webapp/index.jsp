<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<% 
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/bootstrap.css" />
<script src="${pageContext.request.contextPath}/js/jquery.js"
	type="text/javascript" charset="utf-8"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.js"
	type="text/javascript" charset="utf-8"></script>
<script src="${pageContext.request.contextPath}/js/control.js"
	type="text/javascript" charset="utf-8"></script>
</head>
<body>
	<div class="container">
		<div class="row">
			<!--播放列表 -->
			<div class="col-md-4">
				<ol id="m-list">
				</ol>
			</div>

			<!--播放器窗口 -->
			<div class="col-md-4">
				<!--状态 -->
				<div>
					正在播放: <strong id="musicContext"></strong>
				</div>

				<!--播放器条 -->
				<audio id="audio" controls="controls"></audio>
				
				<!--控制按钮 -->
				<div class="btn-group" id="ctrl-area">
					<button id="btn-play" class="btn btn-success">播放</button>
					<button id="btn-pre" class="btn btn-success">上一首</button>
					<button id="btn-next" class="btn btn-success">下一首</button>
					<button id="btn-pause" class="btn btn-success">暂停</button>

					<!-- Single button -->
					<div class="btn-group">
						<button type="button" class="btn btn-primary dropdown-toggle"
							data-toggle="dropdown">
							播放模式 <span class="caret"></span>
						</button>
						<ul class="dropdown-menu" role="menu">
							<li><a href="#" class="musicType" id="btn-order" value="1"> 顺序播放 </a></li>
							<li><a href="#" class="musicType" id="btn-loop" value="2"> 单曲循环 </a></li>
							<li><a href="#" class="musicType" id="btn-random" value="3"> 随机播放 </a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
	$(function() {
		var param = {
			basePath : "<%=basePath %>",
			playAudio : $("#audio").get(0),
			musicContext : $("#musicContext"),
			musicList : $("#m-list")
		}
		controlFun = $.extend(controlFun,param);
		
		controlFun.init();
	})
		
		
	</script>
</body>
</html>