package com.ssafy.gumid207.recommend.ib_recommend;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.ssafy.gumid207.customexception.UserNotFoundException;
import com.ssafy.gumid207.entity.SimData;
import com.ssafy.gumid207.entity.Song;
import com.ssafy.gumid207.entity.User;
import com.ssafy.gumid207.song.SongDislikeRepository;
import com.ssafy.gumid207.song.SongRepository;
import com.ssafy.gumid207.songbox.MyListRepository;
import com.ssafy.gumid207.user.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class IbRecommendServiceImpl implements IbRecommendService {
	class SimNode {
		Long songNum;
		Double sim;

		public SimNode(Long songNum, Double sim) {
			this.songNum = songNum;
			this.sim = sim;
		}
	}

	private final UserRepository userRepo;
	private final MyListRepository myListRepository;
	private final SimDataRepository simRepo;
	private final SongRepository songRepo;
	private final SongDislikeRepository dislikeRepo;

	public static Map<Long, List<SimData>> simMap;
	public static List<Song> songList;
	public static Random random;

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

	@Override
	public List<Song> getMyListRecommend(Long userSeq, Integer size) throws Exception {
		if (size == null || size == 0) {
			size = 30;
		}
		User user = userRepo.findByUserSeq(userSeq).orElseThrow(() -> new UserNotFoundException("유저 정보를 찾을 수 없습니다."));
		Set<Long> dislike = dislikeRepo.findByUser(user).stream().map((entity) -> entity.getSong().getSongSeq()).collect(Collectors.toSet());
		List<Long> mySongNums = myListRepository.findByUser(user).stream()
				.map((mylist) -> mylist.getSong().getSongSeq()).collect(Collectors.toList());
		Map<Long, SimNode> simResultMap = new HashMap<>();
		for (Long songNum : mySongNums) {
			if (!simMap.containsKey(songNum)) {
				simMap.put(songNum, simRepo.findAllBySimDataMysong(songNum));
			}
			for (SimData simData : simMap.get(songNum)) {
				if (random.nextInt(100) < 20) {
					continue;
				}
				Long key = simData.getSimDataTargetsong();
				SimNode node = simResultMap.getOrDefault(key, new SimNode(key, 0.0));
				simResultMap.put(key, node);
				node.sim = 1 - (1 - node.sim) * (1 - simData.getSimDataSimilarity() * (1 - random.nextDouble() * 0.3));
			}
		}
		List<SimNode> simNodeList = new ArrayList<>(simResultMap.values());
		simNodeList.sort((a, b) -> a.sim < b.sim ? 1 : a.sim > b.sim ? -1 : 0);
		List<Song> results = new ArrayList<>();
		for(int i = 0; i < simNodeList.size() && results.size() < size; i++) {
			if (dislike.contains(simNodeList.get(i).songNum)) {
				continue;
			}
			results.add(songList.get(simNodeList.get(i).songNum.intValue()));
		}
		return results;
	}

}
