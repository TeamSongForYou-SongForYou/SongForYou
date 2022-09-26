package com.ssafy.gumid207.songbox;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface SongBoxService {

	Boolean addMyList(Long userSeq, Long songSeq) throws Exception;

}