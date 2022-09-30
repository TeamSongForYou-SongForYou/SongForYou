package com.ssafy.gumid207.dto;

import java.io.Serializable;

import com.ssafy.gumid207.entity.Lyrics;

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
public class LyricsDto implements Serializable {

	private Long lyricsSeq;

	@ApiParam(value = "곡 이름")
	private String lyrics;

	public static LyricsDto of(Lyrics lyrics) {
		if (lyrics == null) {
			return new LyricsDtoBuilder() //
					.lyrics("가사 정보가 없습니다") //
					.build(); //
		}
		return new LyricsDtoBuilder() //
				.lyricsSeq(lyrics.getLyricsSeq()) //
				.lyrics(lyrics.getLyrics()) //
				.build();
	}

}