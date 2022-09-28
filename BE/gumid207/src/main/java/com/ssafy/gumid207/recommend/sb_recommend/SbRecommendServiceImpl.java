package com.ssafy.gumid207.recommend.sb_recommend;

import java.net.URI;
import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.ssafy.gumid207.customexception.SimVoiceUserNotFoundException;
import com.ssafy.gumid207.customexception.SongNotFoundException;
import com.ssafy.gumid207.customexception.UserNotFoundException;
import com.ssafy.gumid207.dto.SongDto;
import com.ssafy.gumid207.entity.MyList;
import com.ssafy.gumid207.entity.SimData;
import com.ssafy.gumid207.entity.Song;
import com.ssafy.gumid207.entity.User;
import com.ssafy.gumid207.recommend.SimDataRepository;
import com.ssafy.gumid207.recommend.SimNode;
import com.ssafy.gumid207.recommend.SongStaticData;
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
public class SbRecommendServiceImpl implements SbRecommendService {

	private final UserRepository userRepo;
	private final MyListRepository myListRepo;
	private final MyRecordRepository myRecordRepo;
	private final SimDataRepository simRepo;
	private final SongRepository songRepo;
	private final SongDislikeRepository dislikeRepo;

	@Override
	public List<SongDto> getStatisticRecommend(Long userSeq, String genre, Integer age, String gender, String weather,
			Integer size) throws Exception {
//		User user = userRepo.findByUserSeq(userSeq).orElseThrow(() -> new UserNotFoundException("유저 정보를 찾을 수 없습니다."));
//		Set<Long> dislike = dislikeRepo.findByUser(user).stream().map((entity) -> entity.getSong().getSongSeq())
//				.collect(Collectors.toSet());
//		
//		List<Long> mySongNums = myRecordRepo.findTop1000ByOrderByMyRecordRegTimeDesc().stream().map((myRecord) -> myRecord.getSong().getSongSeq())
//				.collect(Collectors.toList());
//		Map<Long, Integer> counts = new 
		return null;
	}

	@Override
	public List<SongDto> getRandomRecommend(Long userSeq, Integer size) throws Exception {
		User user = userRepo.findByUserSeq(userSeq).orElseThrow(() -> new UserNotFoundException("유저 정보를 찾을 수 없습니다."));
		Set<Long> dislike = dislikeRepo.findByUser(user).stream().map((entity) -> entity.getSong().getSongSeq())
				.collect(Collectors.toSet());
		if (size == null || size <= 0) {
			size = 50;
		}
		List<Song> randomSongs = new ArrayList<>();
		Set<Long> picked = new HashSet<>();
		int limit = 10000;
		while(limit > 0 && randomSongs.size() < size) {
			limit--;
			Long randNum = (long)SongStaticData.random.nextInt(SongStaticData.songList.size());
//			System.out.println(randNum);
			if (picked.contains(randNum)) {
				continue;
			}
			picked.add(randNum);
			if (SongStaticData.songList.get(randNum.intValue()) != null) {
				randomSongs.add(SongStaticData.songList.get(randNum.intValue()));
			}
		}
		return randomSongs.stream().map((song) -> SongDto.of(song)).collect(Collectors.toList());
	}

}
