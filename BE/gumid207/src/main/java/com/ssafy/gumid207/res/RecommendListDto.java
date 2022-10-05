package com.ssafy.gumid207.res;

import java.io.Serializable;
import java.util.List;

import com.ssafy.gumid207.dto.SongDto;

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
public class RecommendListDto implements Serializable {
	public static class Word{
		public String word;
		public Integer count;
		public Word(String word, Integer count) {
			this.word = word;
			this.count = count;
		}
	}
	private List<Word> info;
	private List<SongDto> songList;

//	public static UserResDto of(User user) {
//		if (user == null) {
//			return null;
//		}
//		return new UserResDtoBuilder() //
//				.userSeq(user.getUserSeq()) //
//				.userPoint(user.getPoint()) //
//				.userNickName(user.getNickName()) //
//				.userBirthday(user.getBirthday()) //
//				.userGender(user.getGender()) //
//				.userRegTime(user.getRegTime()) //
//				.profileImgDto(FileDto.of(user.getProfileImgSeq())) //
//				.build();
//	}

}