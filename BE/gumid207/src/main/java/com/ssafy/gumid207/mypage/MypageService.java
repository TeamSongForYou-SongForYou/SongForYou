package com.ssafy.gumid207.mypage;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface MypageService {
	Boolean saveMyRecord(Long userSeq, MultipartFile recordFile) throws Exception;

}