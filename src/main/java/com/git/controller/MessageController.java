package com.git.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.git.domain.ResultVO;
import com.git.message.MessageUtil;

/*
 * 这是使用三个线程来实现发送短信的功能
 */
@Controller
public class MessageController {
	
	/**
	 * 发送controller
	 * @return
	 */
	@RequestMapping(value = "refresh",method = RequestMethod.GET)
	public ResponseEntity<ResultVO> refresh(){
		MessageUtil.sendMessage("这里是短信内容");
		return ResponseEntity.status(HttpStatus.OK).body(new ResultVO("发送成功"));
	}
}
