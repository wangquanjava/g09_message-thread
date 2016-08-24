package com.osc.controller;

import java.io.FileInputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MusicController {
	@RequestMapping(value="getMusicStream",method=RequestMethod.GET)
	public void getMusicStream(HttpServletRequest req,HttpServletResponse resp){
		try {
			FileInputStream fileInputStream = new FileInputStream(req.getSession().getServletContext().getRealPath("1.mp3"));
			IOUtils.copy(fileInputStream, resp.getOutputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
