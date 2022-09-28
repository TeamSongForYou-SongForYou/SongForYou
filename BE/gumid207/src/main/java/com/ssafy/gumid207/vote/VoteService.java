package com.ssafy.gumid207.vote;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.gumid207.dto.ReviewDto;
import com.ssafy.gumid207.dto.ReviewUploadDto;
import com.ssafy.gumid207.res.CompetitionResDto;
import com.ssafy.gumid207.res.MyRecordResDto;

@Service
public interface VoteService {

	List<CompetitionResDto> getBeforeStartList() throws Exception;

	Boolean addCompetition(Long songSeq, LocalDateTime startDateTime, Integer days) throws Exception;

}