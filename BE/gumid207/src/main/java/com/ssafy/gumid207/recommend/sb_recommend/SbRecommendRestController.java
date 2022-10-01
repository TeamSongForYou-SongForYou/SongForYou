package com.ssafy.gumid207.recommend.sb_recommend;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.gumid207.dto.SongDto;
import com.ssafy.gumid207.dto.UserDto;
import com.ssafy.gumid207.entity.User;
import com.ssafy.gumid207.jwt.SecurityUtil;
import com.ssafy.gumid207.res.ResponseFrame;
import com.ssafy.gumid207.user.UserRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/sb-recommend")
@RequiredArgsConstructor
@Slf4j
@Api(tags = "통계 기반 추천 컨트롤러")
public class SbRecommendRestController {
	private final UserRepository userRepo;

	public UserDto getLoginUser() {
		User user = null;
		try {
			user = userRepo.findById(SecurityUtil.getCurrentMemberId()).get();
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
	
	private final SbRecommendService sbRecommendServ;

	@ApiParam(value = "통계 기반 추천받기")
	@GetMapping(value = "/list")
	public ResponseEntity<?> getStatisticRecommend( //
			@RequestParam(required = false) String genre, //
			@RequestParam(required = false) Integer age, //
			@RequestParam(required = false) String gender, //
			@RequestParam(required = false) String weather //
			) throws Exception {
		UserDto userDto = getLoginUser();
		
		List<SongDto> songDtoList = sbRecommendServ.getRandomRecommend(userDto.getUserSeq(), null);
		return new ResponseEntity<>(new ResponseFrame<>(true, songDtoList, songDtoList.size(), "미완성이라 랜덤으로 줍니다. 구현시 참고해주세요."),
				HttpStatus.OK);
	}
	
	@ApiParam(value = "랜덤 노래 추천받기")
	@GetMapping(value = "/random-list")
	public ResponseEntity<?> getRandomRecommend() throws Exception {
		UserDto userDto = getLoginUser();
		
		List<SongDto> songDtoList = sbRecommendServ.getRandomRecommend(userDto.getUserSeq(), null);

		return new ResponseEntity<>(new ResponseFrame<>(true, songDtoList, songDtoList.size(), "랜덤한 리스트를 반환합니다."),
				HttpStatus.OK);
	}
	
	@ApiParam(value = "추천 리스트 받기")
	@GetMapping(value = "/{listNum}/recommend-list")
	public ResponseEntity<?> getRecommendList(@PathVariable Integer listNum) throws Exception {

		return new ResponseEntity<>(new ResponseFrame<>(true, sbRecommendServ.getRecommendList(listNum), 1, "지정 리스트를 반환합니다."),
				HttpStatus.OK);
	}
	@ApiParam(value = "통계 기반 추천받기")
	@GetMapping(value = "/{weatherNum}/weather")
	public ResponseEntity<?> getWeatherRecommend(@PathVariable Integer weatherNum) throws Exception {
		
		Integer temp = 0;
		switch(weatherNum) {
		case 0:
			temp = 3;
			break;
		case 1:
		case 5:
		case 6:
			temp = 1;
			break;
		case 2:
		case 3:
		case 7:
			temp = 2;
			break;
		}
		
		List<SongDto> songDtoList = sbRecommendServ.getWeatherList(temp);
		
		return new ResponseEntity<>(new ResponseFrame<>(true, songDtoList, songDtoList.size(), "날씨 기반 리스트 반환."),
				HttpStatus.OK);
	}

}
