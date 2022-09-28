package com.ssafy.gumid207.mypage;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.gumid207.dto.FileDto;
import com.ssafy.gumid207.res.UserResDto;

@Service
public interface MypageService {
	Boolean saveMyRecord(Long userSeq, MultipartFile recordFile) throws Exception;

	Boolean changeNickName(Long userSeq, String userNickName) throws Exception;

	FileDto changeProfileImg(Long userSeq, MultipartFile profileImg) throws Exception;

	FileDto getProfileImg(Long userSeq) throws Exception;

	UserResDto getUserInfo(Long userSeq) throws Exception;

}