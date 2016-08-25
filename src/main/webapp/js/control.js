var controlFun = {
//	访问路径
	basePath : "",
//	播放器插件
	playAudio : "",
//	正在播放的歌元素<a>对象
	currentMusic : "",
//	文本区元素对象
	musicContext : "",
//	播放模式:1:顺序,2:单首,3:随机
	musicType : "1",
//	播放列表<ol>对象
	musicList : "",
//	从后台获得列表，然后执行read()
	init : function() {
				$.ajax({
					url : controlFun.basePath + "/refresh",
					type : "get",
					success : function(data) {
						for (var i = 0; i < data.length; i++) {
							controlFun.musicList.append("<li><a class='playList' id ='"+data[i].id+"' href='javascript:void(0)'>"+data[i].musicName+"</a></li>");
						}
						//给按钮和列表增加事件
						controlFun.ready();
						//默认初始播放第一首歌
						$(".playList").get(0).click();
					}
				})
			},
//	用于切歌以后再播放
	myPlay : function() {
				controlFun.playAudio.src = controlFun.basePath+"/getMusicStream?id="+controlFun.currentMusic.attr("id");
				controlFun.musicContext.html(controlFun.currentMusic.text());
				controlFun.playAudio.play();
			},
	ready : function() {
//		设置currentMusic对象
		$(".playList").click(function() {
			controlFun.currentMusic = $(this);
			controlFun.myPlay();
		})
		$("#btn-play").click(function() {
			controlFun.playAudio.play();
		})
		$("#btn-pre").click(function() {
			var temp = controlFun.currentMusic.parent().prev();
			if (temp.length!=0) {
				controlFun.currentMusic = temp.children();
			}
			controlFun.myPlay();
		})
		$("#btn-next").click(function() {
			if (controlFun.musicType == "1") {
				var temp = controlFun.currentMusic.parent().next();
			}else if(controlFun.musicType == "2"){
				var temp = controlFun.currentMusic.parent();
			}else if(controlFun.musicType == "3"){
				//从<ol>下的<li>中找到随机的一个元素对象，因为使用[]会把jquery对象转成js对象，所以需要再转回来
				var temp = $(controlFun.musicList.children()[parseInt((controlFun.musicList.children().length-1)* Math.random())]);
			}
			if (temp.length!=0) {
				controlFun.currentMusic = temp.children();
			}
			controlFun.myPlay();
		})
		$("#btn-pause").click(function() {
			controlFun.playAudio.pause();
		})
		controlFun.playAudio.ended = function() {
			$("#btn-next").click();
		}
		$(".musicType").click(function() {
			controlFun.musicType = $(this).attr("value");
		})
	}
	
};
