package com.ssafy.gumid207.s3;

import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.ssafy.gumid207.dto.FileDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Component
public class S3FileServiceImpl implements S3FileService {
	private final AmazonS3Client amazonS3Client;
	
	@Value("${cloud.aws.s3.bucket}")
    public String bucket;  // S3 버킷 이름
	
	/**
	 * 
	 * @param multipartFile 저장될 파일 
	 * @param savedPath 저장될 파일의 엣지까지의 디렉토리
	 * @return
	 * @throws IOException
	 */
	@Override
	public FileDto upload(MultipartFile multipartFile, String OriginalFileName, String savedPath, String format) throws Exception{
		String savedFileName = UUID.randomUUID().toString() ;
		
		long size = multipartFile.getSize(); // 파일 크기
		
		ObjectMetadata objectMetaData = new ObjectMetadata();
		objectMetaData.setContentType(multipartFile.getContentType());
		objectMetaData.setContentLength(size);
		
		// S3에 업로드
		amazonS3Client.putObject(
			new PutObjectRequest(bucket, savedPath + "/" + savedFileName + "." + format, multipartFile.getInputStream(), objectMetaData)
				.withCannedAcl(CannedAccessControlList.PublicRead)
		);
		
		String fileSavedPath = amazonS3Client.getUrl(bucket,savedPath+ "/" +savedFileName + "." + format).toString(); // 접근가능한 URL 가져오기

		log.info("S3 버켓({}) <={}",bucket,fileSavedPath);
		return FileDto.builder()//
				.fileOriginalName(OriginalFileName) //
				.fileSavedName(savedFileName) //
				.fileType(format) //
				.fileSavedPath(fileSavedPath) //
				.build(); //
    }
}
