package com.ssafy.gumid207.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ssafy.gumid207.entity.Vote;

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
public class VoteDto implements Serializable {

	private Long voteSeq;

	@ApiParam(value = "음성/영상")
	private String voteFileType;
	
	@ApiParam(value = "제목")
	private String voteItemTitle;
	
	@ApiParam(value = "득표수")
	private Integer votePoint;
	
	@ApiParam(value = "등록 시간. (yyyy-MM-dd 00:00:00)")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime voteRegTime;

	public static VoteDto of(Vote vote) {
		if (vote == null) {
			return null;
		}
		return new VoteDtoBuilder() //
				.voteSeq(vote.getVoteSeq()) //
				.voteItemTitle(vote.getVoteItemTitle()) //
				.votePoint(vote.getVotePoint()) //
				.voteFileType(vote.getVoteFileType()) //
				.voteRegTime(vote.getVoteRegTime()) //
				.build();
	}

}