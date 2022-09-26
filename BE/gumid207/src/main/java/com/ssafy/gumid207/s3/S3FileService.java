package com.ssafy.gumid207.s3;

import org.springframework.web.multipart.MultipartFile;

import com.ssafy.gumid207.dto.FileDto;

public interface S3FileService {

	/**
	 * 
	 * @param multipartFile 저장될 파일 
	 * @param savedPath 저장될 파일의 엣지까지의 디렉토리
	 * @return
	 * @throws IOException
	 */
	FileDto upload(MultipartFile multipartFile, String OriginalFileName, String savedPath, String format)
			throws Exception;

}
