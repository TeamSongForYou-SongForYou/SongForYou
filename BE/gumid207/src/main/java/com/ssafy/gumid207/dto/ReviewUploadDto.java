package com.ssafy.gumid207.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ssafy.gumid207.customexception.ReviewUploadDtoIllegalParameterException;

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
public class ReviewUploadDto implements Serializable {

	@ApiParam(value = "노래방 이름", required = true)
	private String karaokeName;
	
	@ApiParam(value = "노래방 주소", required = true)
	private String karaokeAddress;
	
	@ApiParam(value = "노래방 가격, 시간/곡 수 등 타입")
	private String reviewPrice;

	@ApiParam(value = "결제 방식")
	private String reviewPayType;

	@ApiParam(value = "직원 유무")
	private String reviewEmployee;

	@ApiParam(value = "화장실")
	private String reviewToilet;

	@ApiParam(value = "청결", required = true)
	private Integer reviewCleanness;

	@ApiParam(value = "음질", required = true)
	private Integer reviewSoundQuality;

	@ApiParam(value = "리뷰 내용", required = true)
	private String reviewContent;
	
	@ApiParam(value = "등록 시간. (yyyy-MM-dd 00:00:00)")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime reviewRegTime;
	
	public void validate() throws Exception {
		if (karaokeName == null || //
			karaokeAddress == null || //
			reviewCleanness == null || //
			reviewSoundQuality == null ||
			reviewContent == null //
				) {
			throw new ReviewUploadDtoIllegalParameterException("필수 정보가 부족합니다.");
		}
	}

}