package com.ssafy.gumid207.recommend.sb_recommend;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.gumid207.dto.SongDto;
import com.ssafy.gumid207.res.RecommendListDto;

@Service
public interface SbRecommendService {

	List<SongDto> getStatisticRecommend(Long userSeq, String genre, Integer age, String gender, String weather,
			Integer size) throws Exception;

	List<SongDto> getRandomRecommend(Long userSeq, Integer size) throws Exception;

	RecommendListDto getRecommendList(Integer listNum) throws Exception;

}
