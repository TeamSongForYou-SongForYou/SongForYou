package com.ssafy.gumid207.recommend.ib_recommend;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.gumid207.dto.SongDto;

@Service
public interface IbRecommendService {
	List<SongDto> getMyListRecommend(Long userSeq, Integer size) throws Exception;
	List<SongDto> getMyRecordRecommend(Long userSeq, Integer datelimit, Integer size) throws Exception;

}
