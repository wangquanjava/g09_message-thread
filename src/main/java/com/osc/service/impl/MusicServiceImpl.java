package com.osc.service.impl;

import java.io.File;
import java.io.FilenameFilter;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.osc.domain.MusicEntity;
import com.osc.mapper.MusicMapper;
import com.osc.service.MusicService;
@Service
public class MusicServiceImpl implements MusicService{
	@Value("${env.musicDir}")
	private String musicDir;
	
	@Value("${env.musicFilter}")
	private String musicFilter;
	
	@Autowired
	private MusicMapper musicMapper;
	
	public List<MusicEntity> refresh() {
		scan();
		
		List<MusicEntity> list = this.musicMapper.select(new MusicEntity());
		return list;
	}
	
	public void scan(){
		File[] listFiles = new File(musicDir).listFiles(new FilenameFilter() {
			public boolean accept(File dir, String name) {
				String string = name.substring(name.lastIndexOf(".")+1);
				if (musicFilter.contains(string)) {
					return true;
				}
				return false;
			}
		});
		MusicEntity musicEntity = null;
		for (File file : listFiles) {
			musicEntity = new MusicEntity(file.getName());
			MusicEntity selectOne = this.musicMapper.selectOne(musicEntity);
			if (selectOne==null) {
				this.musicMapper.insert(musicEntity);
			}
		}
	}

	public MusicEntity getMusicEntity(MusicEntity musicEntity) {
		return this.musicMapper.selectOne(musicEntity);
	}

}
