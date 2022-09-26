package com.ssafy.gumid207.recommend.ub_recommend;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.gumid207.dto.SongDto;

@Service
public interface UbRecommendService {

	List<SongDto> getMyVoiceRecommend(Long userSeq, Integer size) throws Exception;

}
