package com.ssafy.gumid207.songbox;

import org.springframework.stereotype.Service;

import com.ssafy.gumid207.dto.SongDto;

@Service
public interface SongBoxService {

	SongDto getUserSetting(Long userSeq) throws Exception;

//	SongDto setUserSetting(Long userSeq, SongDto settingDto) throws Exception;

}
