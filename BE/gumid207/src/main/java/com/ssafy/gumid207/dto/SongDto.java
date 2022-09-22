package com.ssafy.gumid207.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ssafy.gumid207.entity.Song;

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
public class SongDto implements Serializable {

	private Long songSeq;

	@ApiParam(value = "곡 이름")
	private String songTitle;

	@ApiParam(value = "가수")
	private String songArtistName;

	@ApiParam(value = "장르")
	private String songGenre;

	@ApiParam(value = "유튜브 조회수")
	private Integer songYoutubeView;

	@ApiParam(value = "썸네일 URL")
	private String songThumbnailUrl;

	@ApiParam(value = "유튜브 영상 URL")
	private String songYoutubeUrl;

	@ApiParam(value = "등록 시간. (yyyy-MM-dd 00:00:00)")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime songRegTime;

	public static SongDto of(Song song) {
		if (song == null) {
			return null;
		}
		return new SongDtoBuilder() //
				.songSeq(song.getSongSeq()) //
				.songArtistName(song.getArtistName()) //
				.songGenre(song.getGenre()) //
				.songYoutubeUrl(song.getYoutubeUrl()) //
				.songYoutubeView(song.getYoutubeView()) //
				.songThumbnailUrl(song.getThumbnailUrl()) //
				.songRegTime(song.getRegTime()) //
				.build();
	}

}