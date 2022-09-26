package com.ssafy.gumid207.recommend.ib_recommend;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.gumid207.devutil.DevUtil;
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
@RequestMapping("/ib-recommend")
@RequiredArgsConstructor
@Slf4j
@Api(tags = "노래 유사도 기반 추천 컨트롤러")
public class IbRecommendRestController {
	private final UserRepository userRepo;
	
	public UserDto getLoginUser() {
		User user = null;
		try {
			user = userRepo.findById(3l).get();
		}
		catch (Exception e) {			
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

	private final IbRecommendService ibRecommendServ;

	@ApiParam(value = "내가 저장한 곡들과 비슷한 노래 추천 리스트 받기")
	@GetMapping(value = "/my-list")
	public ResponseEntity<?> getMyListRecommend() throws Exception {
		UserDto userDto = getLoginUser();
		List<SongDto> songDtoList = ibRecommendServ.getMyListRecommend(userDto.getUserSeq(), null);
		return new ResponseEntity<>(new ResponseFrame<>(true, songDtoList, songDtoList.size(), "보관함 기반 추천 정보를 반환합니다."),
				HttpStatus.OK);
	}

	@ApiParam(value = "최근 부른 곡들과 비슷한 노래 추천 리스트 받기")
	@GetMapping(value = "/my-record")
	public Object getMyRecordRecommend(@RequestParam(required = false) Integer datelimit) throws Exception {
		UserDto userDto = getLoginUser();
		List<SongDto> songDtoList = ibRecommendServ.getMyRecordRecommend(userDto.getUserSeq(), datelimit, null);
		return new ResponseEntity<>(
				new ResponseFrame<>(true, songDtoList, songDtoList.size(), "최근 녹음곡 기반 추천 정보를 반환합니다."), HttpStatus.OK);
	}

//	@ApiParam(value = "유저 환경정보 세팅 (넣은 정보만 변경, null인 정보는 유지)")
//	@PostMapping(value = "/{userSeq}")
//	public ResponseEntity<?> setUserSetting(@PathVariable Long userSeq, @RequestBody UserSettingDto userSettingDto) throws Exception{
//		return new ResponseEntity<>(new ResponseFrame<>(true, settingServ.setUserSetting(userSeq, userSettingDto), 1, "유저 환경설정에 성공했습니다."), HttpStatus.OK);
//	}

}
