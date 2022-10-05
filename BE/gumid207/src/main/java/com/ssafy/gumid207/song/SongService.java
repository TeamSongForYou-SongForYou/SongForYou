package com.ssafy.gumid207.song;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.gumid207.dto.LyricsDto;
import com.ssafy.gumid207.dto.SongDto;

@Service
public interface SongService {

	SongDto getSongDetail(Long songSeq) throws Exception;
	String getSongSoundFileURL(Long songSeq) throws Exception;
	Boolean dislikeSong(Long userSeq, Long songSeq) throws Exception;
	Boolean deleteDislikeSong(Long userSeq, Long songSeq) throws Exception;
	List<SongDto> getSongListBySongName(String songName) throws Exception;
	LyricsDto getSongLyrics(Long songSeq) throws Exception;

}
