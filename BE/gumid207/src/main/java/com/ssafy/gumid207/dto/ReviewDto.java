package com.ssafy.gumid207.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ssafy.gumid207.entity.Review;

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
public class ReviewDto implements Serializable {

	private Long reviewSeq;

	@ApiParam(value = "노래방 가격, 시간/곡 수 등 타입")
	private String reviewPrice;

	@ApiParam(value = "결제 방식")
	private String reviewPayType;

	@ApiParam(value = "직원 유무")
	private String reviewEmployee;

	@ApiParam(value = "화장실")
	private String reviewToilet;

	@ApiParam(value = "청결")
	private Integer reviewCleanness;

	@ApiParam(value = "음질")
	private Integer reviewSoundQuality;

	@ApiParam(value = "리뷰 내용")
	private String reviewContent;
	
	private FileDto reviewImage;
	
	@ApiParam(value = "등록 시간. (yyyy-MM-dd 00:00:00)")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime reviewRegTime;

	public static ReviewDto of(Review review) {
		if (review == null) {
			return null;
		}
		return new ReviewDtoBuilder() //
				.reviewSeq(review.getReviewSeq()) //
				.reviewPrice(review.getReviewPrice()) //
				.reviewPayType(review.getReviewPayType()) //
				.reviewEmployee(review.getReviewEmployee()) //
				.reviewToilet(review.getReviewToilet()) //
				.reviewCleanness(review.getReviewCleanness()) //
				.reviewSoundQuality(review.getReviewSoundQuality()) //
				.reviewContent(review.getReviewContent()) //
				.reviewRegTime(review.getReviewRegTime()) //
				.reviewImage(FileDto.of(review.getFileSeq())) //
				.build();
	}

}