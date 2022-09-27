package com.ssafy.gumid207.songbox;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.gumid207.res.MyRecordResDto;

@Service
public interface SongBoxService {

	Boolean addMyList(Long userSeq, Long songSeq) throws Exception;

	Boolean deleteMyList(Long userSeq, Long songSeq) throws Exception;

	MyRecordResDto saveMySongRecord(Long userSeq, Long songSeq, MultipartFile recordFile) throws Exception;

	Boolean deleteMySongRecord(Long userSeq, Long myRecordSeq) throws Exception;

}