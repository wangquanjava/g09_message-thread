package com.osc.service;

import java.util.List;

import com.osc.domain.MusicEntity;

public interface MusicService {

	List<MusicEntity> refresh();

	MusicEntity getMusicEntity(MusicEntity musicEntity);

}
