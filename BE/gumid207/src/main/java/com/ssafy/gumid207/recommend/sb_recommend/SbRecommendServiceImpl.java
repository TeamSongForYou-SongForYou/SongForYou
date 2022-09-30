package com.ssafy.gumid207.recommend.sb_recommend;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.ssafy.gumid207.customexception.UserNotFoundException;
import com.ssafy.gumid207.dto.SongDto;
import com.ssafy.gumid207.entity.Song;
import com.ssafy.gumid207.entity.User;
import com.ssafy.gumid207.recommend.SimDataRepository;
import com.ssafy.gumid207.recommend.SongStaticData;
import com.ssafy.gumid207.res.RecommendListDto;
import com.ssafy.gumid207.res.RecommendListDto.Word;
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
	
	@Override
	public RecommendListDto getRecommendList(Integer listNum) throws Exception {
		RecommendListDto dto = new RecommendListDto();
		dto.setInfo(new ArrayList<>());
		dto.setSongList(new ArrayList<>());
		switch(listNum) {
		case 1:
			dto.getInfo().add(new Word("오늘",4));
			dto.getInfo().add(new Word("우리",12));
			dto.getInfo().add(new Word("기분",5));
			dto.getInfo().add(new Word("파도",8));
			dto.getInfo().add(new Word("바닷가",12));
			dto.getInfo().add(new Word("기억",5));
			dto.getInfo().add(new Word("여름밤",14));
			dto.getInfo().add(new Word("사랑",18));
			dto.getInfo().add(new Word("그대",6));
			dto.getInfo().add(new Word("우린",9));
			dto.getInfo().add(new Word("처음",5));
			dto.getInfo().add(new Word("한번",8));
			dto.getInfo().add(new Word("부산",7));
			dto.getInfo().add(new Word("바캉스",7));
			dto.getInfo().add(new Word("노래",6));
			dto.getInfo().add(new Word("생각",9));
			dto.getInfo().add(new Word("와리",4));
			dto.getInfo().add(new Word("혼자",5));
			dto.getInfo().add(new Word("여기",10));
			dto.getInfo().add(new Word("해변",4));
			dto.getInfo().add(new Word("바다",18));
			dto.getInfo().add(new Word("이제",4));
			dto.getInfo().add(new Word("다시",15));
			dto.getInfo().add(new Word("냉면",36));
			dto.getInfo().add(new Word("파란",6));
			dto.getInfo().add(new Word("그때",10));
			dto.getInfo().add(new Word("하늘",13));
			dto.getInfo().add(new Word("위로",6));
			dto.getInfo().add(new Word("지금",13));
			dto.getInfo().add(new Word("아래",5));
			dto.getInfo().add(new Word("너와나",4));
			dto.getInfo().add(new Word("바람",5));
			dto.getInfo().add(new Word("시간",4));
			dto.getInfo().add(new Word("마치",6));
			dto.getInfo().add(new Word("여수",10));
			dto.getInfo().add(new Word("비행기",11));
			dto.getInfo().add(new Word("훨훨",4));
			dto.getInfo().add(new Word("타고",7));
			dto.getInfo().add(new Word("동안",4));
			break;
		case 2:
			dto.getInfo().add(new Word("기억",13));
			dto.getInfo().add(new Word("추억",8));
			dto.getInfo().add(new Word("잊을",6));
			dto.getInfo().add(new Word("이별",7));
			dto.getInfo().add(new Word("혼자",9));
			dto.getInfo().add(new Word("마음",6));
			dto.getInfo().add(new Word("사랑",41));
			dto.getInfo().add(new Word("하나",8));
			dto.getInfo().add(new Word("내게",6));
			dto.getInfo().add(new Word("다시",16));
			dto.getInfo().add(new Word("나를",11));
			dto.getInfo().add(new Word("가슴",14));
			dto.getInfo().add(new Word("할까",7));
			dto.getInfo().add(new Word("그대",7));
			dto.getInfo().add(new Word("우리",12));
			dto.getInfo().add(new Word("그립고",12));
			dto.getInfo().add(new Word("그립다",6));
			dto.getInfo().add(new Word("눈물",13));
			dto.getInfo().add(new Word("바보",11));
			dto.getInfo().add(new Word("한다",6));
			dto.getInfo().add(new Word("있어",10));
			dto.getInfo().add(new Word("같은",7));
			dto.getInfo().add(new Word("잊고",6));
			dto.getInfo().add(new Word("없는",7));
			dto.getInfo().add(new Word("글자",9));
			dto.getInfo().add(new Word("으로",6));
			dto.getInfo().add(new Word("하고",6));
			dto.getInfo().add(new Word("제발",11));
			dto.getInfo().add(new Word("하늘",6));
			dto.getInfo().add(new Word("조차",6));
			dto.getInfo().add(new Word("모습",6));
			dto.getInfo().add(new Word("언젠가",7));
			dto.getInfo().add(new Word("그렇게",6));
			dto.getInfo().add(new Word("가지마",13));
			dto.getInfo().add(new Word("이제",6));
			dto.getInfo().add(new Word("...",6));
			dto.getInfo().add(new Word("많이",11));
			dto.getInfo().add(new Word("너무나",7));
			dto.getInfo().add(new Word("인해",10));
			dto.getInfo().add(new Word("앓고",7));
			break;
		case 3:
			dto.getInfo().add(new Word("계속",5));
			dto.getInfo().add(new Word("마음",7));
			dto.getInfo().add(new Word("지금",22));
			dto.getInfo().add(new Word("하나",9));
			dto.getInfo().add(new Word("그냥",7));
			dto.getInfo().add(new Word("사랑",33));
			dto.getInfo().add(new Word("얘기",6));
			dto.getInfo().add(new Word("내겐",5));
			dto.getInfo().add(new Word("모든",14));
			dto.getInfo().add(new Word("생각",16));
			dto.getInfo().add(new Word("대답",9));
			dto.getInfo().add(new Word("가슴",6));
			dto.getInfo().add(new Word("남자",16));
			dto.getInfo().add(new Word("이제",7));
			dto.getInfo().add(new Word("우리",14));
			dto.getInfo().add(new Word("연애",9));
			dto.getInfo().add(new Word("눈빛",5));
			dto.getInfo().add(new Word("정말",8));
			dto.getInfo().add(new Word("가지",5));
			dto.getInfo().add(new Word("해도",8));
			dto.getInfo().add(new Word("세상",6));
			dto.getInfo().add(new Word("제일",12));
			dto.getInfo().add(new Word("항상",6));
			dto.getInfo().add(new Word("노래",8));
			dto.getInfo().add(new Word("오직",16));
			dto.getInfo().add(new Word("문자",5));
			dto.getInfo().add(new Word("오늘",5));
			dto.getInfo().add(new Word("사람",5));
			dto.getInfo().add(new Word("느낌",8));
			dto.getInfo().add(new Word("순간",8));
			dto.getInfo().add(new Word("추억",5));
			dto.getInfo().add(new Word("그대",32));
			dto.getInfo().add(new Word("그녀",14));
			dto.getInfo().add(new Word("제발",5));
			dto.getInfo().add(new Word("심장",6));
			dto.getInfo().add(new Word("쿵쾅",12));
			break;
		}
		switch(listNum) {
		case 1:
			dto.getSongList().add(SongDto.of(SongStaticData.songList.get(1692)));
			dto.getSongList().add(SongDto.of(SongStaticData.songList.get(1785)));
			dto.getSongList().add(SongDto.of(SongStaticData.songList.get(1711)));
			dto.getSongList().add(SongDto.of(SongStaticData.songList.get(1807)));
			dto.getSongList().add(SongDto.of(SongStaticData.songList.get(1697)));
			dto.getSongList().add(SongDto.of(SongStaticData.songList.get(1513)));
			dto.getSongList().add(SongDto.of(SongStaticData.songList.get(2400)));
			dto.getSongList().add(SongDto.of(SongStaticData.songList.get(1488)));
			dto.getSongList().add(SongDto.of(SongStaticData.songList.get(3332)));
			dto.getSongList().add(SongDto.of(SongStaticData.songList.get(1458)));
			dto.getSongList().add(SongDto.of(SongStaticData.songList.get(1730)));
			break;
		case 2:
			dto.getSongList().add(SongDto.of(SongStaticData.songList.get(4221)));
			dto.getSongList().add(SongDto.of(SongStaticData.songList.get(5023)));
			dto.getSongList().add(SongDto.of(SongStaticData.songList.get(504)));
			dto.getSongList().add(SongDto.of(SongStaticData.songList.get(4453)));
			dto.getSongList().add(SongDto.of(SongStaticData.songList.get(4295)));
			dto.getSongList().add(SongDto.of(SongStaticData.songList.get(3299)));
			dto.getSongList().add(SongDto.of(SongStaticData.songList.get(4315)));
			dto.getSongList().add(SongDto.of(SongStaticData.songList.get(4349)));
			dto.getSongList().add(SongDto.of(SongStaticData.songList.get(3292)));
			dto.getSongList().add(SongDto.of(SongStaticData.songList.get(3273)));
			dto.getSongList().add(SongDto.of(SongStaticData.songList.get(4243)));
			dto.getSongList().add(SongDto.of(SongStaticData.songList.get(4581)));
			dto.getSongList().add(SongDto.of(SongStaticData.songList.get(4323)));
			dto.getSongList().add(SongDto.of(SongStaticData.songList.get(4503)));
			dto.getSongList().add(SongDto.of(SongStaticData.songList.get(3307)));
			dto.getSongList().add(SongDto.of(SongStaticData.songList.get(4634)));
			dto.getSongList().add(SongDto.of(SongStaticData.songList.get(3301)));
			dto.getSongList().add(SongDto.of(SongStaticData.songList.get(4291)));
			break;
		case 3:
			dto.getSongList().add(SongDto.of(SongStaticData.songList.get(4260)));
			dto.getSongList().add(SongDto.of(SongStaticData.songList.get(5055)));
			dto.getSongList().add(SongDto.of(SongStaticData.songList.get(4614)));
			dto.getSongList().add(SongDto.of(SongStaticData.songList.get(3275)));
			dto.getSongList().add(SongDto.of(SongStaticData.songList.get(2749)));
			dto.getSongList().add(SongDto.of(SongStaticData.songList.get(2731)));
			dto.getSongList().add(SongDto.of(SongStaticData.songList.get(5021)));
			dto.getSongList().add(SongDto.of(SongStaticData.songList.get(963)));
			dto.getSongList().add(SongDto.of(SongStaticData.songList.get(4230)));
			dto.getSongList().add(SongDto.of(SongStaticData.songList.get(1642)));
			dto.getSongList().add(SongDto.of(SongStaticData.songList.get(5145)));
			dto.getSongList().add(SongDto.of(SongStaticData.songList.get(4731)));
			dto.getSongList().add(SongDto.of(SongStaticData.songList.get(4185)));
			dto.getSongList().add(SongDto.of(SongStaticData.songList.get(661)));
			dto.getSongList().add(SongDto.of(SongStaticData.songList.get(679)));
			dto.getSongList().add(SongDto.of(SongStaticData.songList.get(5196)));
			dto.getSongList().add(SongDto.of(SongStaticData.songList.get(2171)));
			break;
		case 4:
			dto.getSongList().add(SongDto.of(SongStaticData.songList.get(1629)));
			dto.getSongList().add(SongDto.of(SongStaticData.songList.get(1494)));
			dto.getSongList().add(SongDto.of(SongStaticData.songList.get(1602)));
			dto.getSongList().add(SongDto.of(SongStaticData.songList.get(1500)));
			dto.getSongList().add(SongDto.of(SongStaticData.songList.get(2434)));
			dto.getSongList().add(SongDto.of(SongStaticData.songList.get(1427)));
			dto.getSongList().add(SongDto.of(SongStaticData.songList.get(1583)));
			dto.getSongList().add(SongDto.of(SongStaticData.songList.get(1631)));
			dto.getSongList().add(SongDto.of(SongStaticData.songList.get(1570)));
			dto.getSongList().add(SongDto.of(SongStaticData.songList.get(1484)));
			dto.getSongList().add(SongDto.of(SongStaticData.songList.get(1533)));
			dto.getSongList().add(SongDto.of(SongStaticData.songList.get(1501)));
			dto.getSongList().add(SongDto.of(SongStaticData.songList.get(1552)));
			dto.getSongList().add(SongDto.of(SongStaticData.songList.get(1893)));
			dto.getSongList().add(SongDto.of(SongStaticData.songList.get(3263)));
			break;
		case 5:
			dto.getSongList().add(SongDto.of(SongStaticData.songList.get(6934)));
			dto.getSongList().add(SongDto.of(SongStaticData.songList.get(4289)));
			dto.getSongList().add(SongDto.of(SongStaticData.songList.get(4343)));
			dto.getSongList().add(SongDto.of(SongStaticData.songList.get(554)));
			dto.getSongList().add(SongDto.of(SongStaticData.songList.get(2400)));
			dto.getSongList().add(SongDto.of(SongStaticData.songList.get(4553)));
			dto.getSongList().add(SongDto.of(SongStaticData.songList.get(2562)));
			dto.getSongList().add(SongDto.of(SongStaticData.songList.get(1478)));
			dto.getSongList().add(SongDto.of(SongStaticData.songList.get(4422)));
			dto.getSongList().add(SongDto.of(SongStaticData.songList.get(569)));
			dto.getSongList().add(SongDto.of(SongStaticData.songList.get(4536)));
			dto.getSongList().add(SongDto.of(SongStaticData.songList.get(4630)));
			dto.getSongList().add(SongDto.of(SongStaticData.songList.get(2655)));
			dto.getSongList().add(SongDto.of(SongStaticData.songList.get(5059)));
			dto.getSongList().add(SongDto.of(SongStaticData.songList.get(2487)));
			dto.getSongList().add(SongDto.of(SongStaticData.songList.get(4471)));
			dto.getSongList().add(SongDto.of(SongStaticData.songList.get(4593)));
		}
		Collections.shuffle(dto.getSongList());
		return dto;
	}

}
