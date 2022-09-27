package com.ssafy.gumid207.recommend;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.ssafy.gumid207.entity.SimData;
import com.ssafy.gumid207.entity.Song;
import com.ssafy.gumid207.song.SongRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SongStaticData {
	
	public static Map<Long, List<SimData>> simMap;
	public static List<Song> songList;
	public static Random random;

	private final SongRepository songRepo;

	@PostConstruct
	public void init() {
		simMap = new HashMap<>();
		random = new Random();
		List<Song> songs = songRepo.findAllByOrderBySongSeq();
		songList = new ArrayList<>();
		for (Song song : songs) {
			while (songList.size() < song.getSongSeq()) {
				songList.add(null);
			}
			songList.add(song);
		}
	}

}
