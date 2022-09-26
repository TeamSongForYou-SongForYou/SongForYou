package com.ssafy.gumid207.recommend.ub_recommend;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.gumid207.dto.SongDto;
import com.ssafy.gumid207.dto.UserDto;
import com.ssafy.gumid207.entity.User;
import com.ssafy.gumid207.res.ResponseFrame;
import com.ssafy.gumid207.user.UserRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/ub-recommend")
@RequiredArgsConstructor
@Slf4j
@Api(tags = "사용자 음성 유사도 기반 추천 컨트롤러")
public class UbRecommendRestController {
	private final UserRepository userRepo;

	public UserDto getLoginUser() {
		User user = null;
		try {
			user = userRepo.findById(3l).get();
		} catch (Exception e) {
			user = new User();
			user.setBirthday(1996);
			user.setGender("male");
			user.setId("anfidthtn");
			user.setNickName("무량소수");
			user.setPoint(1000);
			user.setPass("1234");
			user.setRegTime(LocalDateTime.now());
		}

		return UserDto.of(user);
	}

	private final UbRecommendService ubRecommendServ;
	
	@ApiParam(value = "내 목소리와 비슷한 유저 기반 추천받기")
	@GetMapping(value = "/my-sound")
	public ResponseEntity<?> getMyVoiceRecommend() throws Exception {
		UserDto userDto = getLoginUser();
		List<SongDto> songDtoList = ubRecommendServ.getMyVoiceRecommend(userDto.getUserSeq(), null);
		return new ResponseEntity<>(new ResponseFrame<>(true, songDtoList, songDtoList.size(), "내 목소리 기반 추천 정보를 반환합니다."),
				HttpStatus.OK);
	}

//	@ApiParam(value = "내가 저장한 곡들과 비슷한 노래 추천 리스트 받기")
//	@GetMapping(value = "/my-list")
//	public ResponseEntity<?> getMyListRecommend() throws Exception {
//		UserDto userDto = getLoginUser();
//		List<SongDto> songDtoList = ibRecommendServ.getMyListRecommend(userDto.getUserSeq(), null);
//		return new ResponseEntity<>(new ResponseFrame<>(true, songDtoList, songDtoList.size(), "보관함 기반 추천 정보를 반환합니다."),
//				HttpStatus.OK);
//	}

}
