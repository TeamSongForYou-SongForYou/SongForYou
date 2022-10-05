package com.ssafy.gumid207.recommend.ub_recommend;

import java.net.URI;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.ssafy.gumid207.customexception.SimVoiceUserNotFoundException;
import com.ssafy.gumid207.customexception.UserNotFoundException;
import com.ssafy.gumid207.dto.SongDto;
import com.ssafy.gumid207.entity.MyList;
import com.ssafy.gumid207.entity.Song;
import com.ssafy.gumid207.entity.User;
import com.ssafy.gumid207.recommend.SimDataRepository;
import com.ssafy.gumid207.song.SongDislikeRepository;
import com.ssafy.gumid207.song.SongRepository;
import com.ssafy.gumid207.songbox.MyListRepository;
import com.ssafy.gumid207.songbox.MyRecordRepository;
import com.ssafy.gumid207.user.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class UbRecommendServiceImpl implements UbRecommendService {
	class SimNode {
		Long songNum;
		Double sim;

		public SimNode(Long songNum, Double sim) {
			this.songNum = songNum;
			this.sim = sim;
		}
	}

	private final UserRepository userRepo;
	private final MyListRepository myListRepo;
	private final MyRecordRepository myRecordRepo;
	private final SimDataRepository simRepo;
	private final SongRepository songRepo;
	private final SongDislikeRepository dislikeRepo;

	public static List<Song> songList;
	public static Random random;

	@PostConstruct
	public void init() {
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

	@Override
	public List<SongDto> getMyVoiceRecommend(Long userSeq, Integer size) throws Exception {
		User user = userRepo.findByUserSeq(userSeq).orElseThrow(() -> new UserNotFoundException("유저 정보를 찾을 수 없습니다."));
		List<Long> simUser = getSimUser(userSeq, null);
		Set<Long> dislike = dislikeRepo.findByUser(user).stream().map((entity) -> entity.getSong().getSongSeq())
				.collect(Collectors.toSet());
		return getRecommendFromParams(dislike, simUser, size);
	}

	public List<Long> getSimUser(Long userSeq, Integer limit) throws Exception {
		if (limit == null || limit <= 0) {
			limit = 10;
		}
		URI uri = UriComponentsBuilder //
				.fromUriString("http://j7d207.p.ssafy.io:8000") //
				.path("/api/v1/my-page/" + userSeq + "/similarlist/10/") //
				.encode(Charset.defaultCharset()) //
				.build() //
				.toUri(); //

		RestTemplate restTemplate = new RestTemplate();
		List<Long> userList = new ArrayList<>();

		try {
			String temp = restTemplate.getForObject(uri, String.class);
			JSONObject jsonObj = new JSONObject(temp);
			JSONArray jsonArray = (JSONArray) jsonObj.get("data");
			for(int i = 0; i < jsonArray.length(); i++) {
				Long simUser = jsonArray.getLong(i);
				if (simUser == userSeq) {
					continue;
				}
				userList.add(simUser);
			}
		} catch (Exception e) {
			System.out.println();
		} finally {
			if (userList.size() == 0) {
				throw new SimVoiceUserNotFoundException("녹음 기록이 없거나, 목소리가 비슷한 유저가 없습니다.");
			}
		}
		return userList;
	}

	public List<SongDto> getRecommendFromParams(Set<Long> dislike, List<Long> userSeqs, Integer size) throws Exception {
		if (size == null || size <= 0) {
			size = 50;
		}
		Set<Long> songNums = new HashSet<>();
		for (Long userSeq : userSeqs) {
			try {
				for (MyList myList : myListRepo.findByUser(userRepo.findById(userSeq).get())) {
					if (random.nextInt(100) < 30) {
						continue;
					}
					Long songSeq = myList.getSong().getSongSeq();
					if (!dislike.contains(songSeq)) {
						songNums.add(myList.getSong().getSongSeq());
					}
				}
			} catch (Exception e) {
			}
		}
		List<Long> songNumsList = new ArrayList<>(songNums);
		Collections.shuffle(songNumsList);
		List<Song> results = new ArrayList<>();
		for(Long idx : songNumsList) {
			results.add(songList.get(idx.intValue()));
		}
		return results.stream().map((song) -> SongDto.of(song)).collect(Collectors.toList());
	}

}
