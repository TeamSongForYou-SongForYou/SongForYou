package com.ssafy.gumid207.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ssafy.gumid207.entity.Dislike;
import com.ssafy.gumid207.entity.Karaoke;

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
public class KaraokeDto implements Serializable {

	private Long karaokeSeq;
	
	@ApiParam(value = "노래방 이름")
	private String karaokeName;

	@ApiParam(value = "노래방 주소")
	private String karaokeAddress;

	@ApiParam(value = "등록 시간. (yyyy-MM-dd 00:00:00)")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime karaokeRegTime;

	public static KaraokeDto of(Karaoke karaoke) {
		if (karaoke == null) {
			return null;
		}
		return new KaraokeDtoBuilder() //
				.karaokeSeq(karaoke.getKaraokeSeq()) //
				.karaokeName(karaoke.getKaraokeName()) //
				.karaokeAddress(karaoke.getKaraokeAddress()) //
				.karaokeRegTime(karaoke.getKaraokeRegTime()) //
				.build();
	}

}