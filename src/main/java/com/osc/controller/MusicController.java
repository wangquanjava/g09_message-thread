package com.osc.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.osc.domain.MusicEntity;
import com.osc.service.MusicService;

/*
 * 总结：
 *    1.这里的第一个controller就是返回歌曲，但是传输需要一定的时间，如果还未传输晚，前台就切歌，后台就会认为客户端下线，报Software caused connection abort: socket write error的错
 *    2.不过并不影响使用，所以这里第一个就不再把错误写出来了
 *    
 */
@Controller
public class MusicController {
	@Value("${env.musicDir}")
	private String musicDir;
	@Autowired
	private MusicService musicService;
	
	
	/**
	 * 返回歌
	 * @param musicEntity
	 */
	@RequestMapping(value="getMusicStream",method=RequestMethod.GET)
	public void getMusicStream(HttpServletRequest req,HttpServletResponse resp,MusicEntity musicEntity){
		FileInputStream fileInputStream = null;
		try {
			musicEntity = this.musicService.getMusicEntity(musicEntity);
			File file = new File(this.musicDir+"/"+musicEntity.getMusicName());
			fileInputStream = new FileInputStream(file);
			
			//如果这里没有拷贝完，前台就切歌，这里就会报错，所以后面不打印错误，但是要把读流释放，才能释放资源
			IOUtils.copy(fileInputStream, resp.getOutputStream());
			
			//TODO 增加断点续传
		} catch (Exception e) {
		} finally {
			try {
				if (fileInputStream!=null) {
					fileInputStream.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	
	/**
	 * 刷新数据库，并返回歌单
	 * @return
	 */
	@RequestMapping(value = "refresh",method = RequestMethod.GET)
	public ResponseEntity<List<MusicEntity>> refresh(){
		try {
			List<MusicEntity> list = this.musicService.refresh();
			return ResponseEntity.status(200).body(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.status(500).body(null);
		
	}
}
