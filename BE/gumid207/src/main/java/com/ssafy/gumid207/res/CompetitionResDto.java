package com.ssafy.gumid207.res;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ssafy.gumid207.dto.SongDto;
import com.ssafy.gumid207.entity.Competition;

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
public class CompetitionResDto implements Serializable {

	private Long competitionSeq;
	
	private SongDto songDto;

	@ApiParam(value = "참가 종료일. (yyyy-MM-dd 00:00:00)")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime competitionExpiryDate;

	@ApiParam(value = "투표 종료일. (yyyy-MM-dd 00:00:00)")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime competitionEndDate;

	@ApiParam(value = "등록 시간. (yyyy-MM-dd 00:00:00)")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime competitionRegTime;

	public static CompetitionResDto of(Competition competition) {
		if (competition == null) {
			return null;
		}
		return new CompetitionResDtoBuilder() //
				.competitionSeq(competition.getCompetitionSeq()) //
				.competitionExpiryDate(competition.getCompetitionExpiryDate()) //
				.competitionEndDate(competition.getCompetitionEndDate()) //
				.songDto(SongDto.of(competition.getSong())) //
				.build();
	}

}