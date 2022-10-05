package com.ssafy.gumid207.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ssafy.gumid207.entity.File;

import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 직렬화 기능을 가진 User클래스
 */
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FileDto implements Serializable {

	private Long fileSeq;

	@ApiParam(value = "원본 파일 명")
	private String fileOriginalName;

	@ApiParam(value = "저장 파일 명")
	private String fileSavedName;

	@ApiParam(value = "파일 저장 경로")
	private String fileSavedPath;

	@ApiParam(value = "파일 타입")
	private String fileType;

	@ApiParam(value = "등록 시간. (yyyy-MM-dd 00:00:00)")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime fileRegTime;

	public static FileDto of(File file) {
		if (file == null) {
			return null;
		}
		return new FileDtoBuilder() //
				.fileSeq(file.getFileSeq()) //
				.fileOriginalName(file.getOriginalName()) //
				.fileSavedName(file.getSavedName()) //
				.fileSavedPath(file.getSavedPath()) //
				.fileType(file.getType()) //
				.fileRegTime(file.getRegTime()) //
				.build();
	}

}