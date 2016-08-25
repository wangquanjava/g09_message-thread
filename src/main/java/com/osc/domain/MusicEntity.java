package com.osc.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "playlist")
public class MusicEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private String musicName;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMusicName() {
		return musicName;
	}
	public void setMusicName(String musicName) {
		this.musicName = musicName;
	}
	public MusicEntity(Integer id, String musicName) {
		super();
		this.id = id;
		this.musicName = musicName;
	}
	public MusicEntity(String musicName) {
		super();
		this.musicName = musicName;
	}
	public MusicEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
