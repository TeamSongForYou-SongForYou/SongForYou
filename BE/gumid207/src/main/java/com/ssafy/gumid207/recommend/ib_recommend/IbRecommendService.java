package com.ssafy.gumid207.recommend.ib_recommend;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.gumid207.entity.Song;

@Service
public interface IbRecommendService {
	List<Song> getMyListRecommend(Long userSeq, Integer size) throws Exception;

}
