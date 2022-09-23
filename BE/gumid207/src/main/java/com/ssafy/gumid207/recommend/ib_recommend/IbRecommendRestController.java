package com.ssafy.gumid207.recommend.ib_recommend;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.gumid207.devutil.DevUtil;
import com.ssafy.gumid207.dto.UserDto;
import com.ssafy.gumid207.res.ResponseFrame;

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
	
	private final IbRecommendService ibRecommendServ;
	
	@ApiParam(value = "내가 저장한 곡들과 비슷한 노래 추천 리스트 받기")
	@GetMapping(value = "/my-list")
	public ResponseEntity<?> getUserSetting() throws Exception{
		UserDto userDto = DevUtil.getLoginUser();
		return new ResponseEntity<>(new ResponseFrame<>(true, ibRecommendServ.getMyListRecommend(userDto.getUserSeq(), null), 1, "보관함 기반 추천 정보를 반환합니다."), HttpStatus.OK);
	}
	
//	@ApiParam(value = "유저 환경정보 세팅 (넣은 정보만 변경, null인 정보는 유지)")
//	@PostMapping(value = "/{userSeq}")
//	public ResponseEntity<?> setUserSetting(@PathVariable Long userSeq, @RequestBody UserSettingDto userSettingDto) throws Exception{
//		return new ResponseEntity<>(new ResponseFrame<>(true, settingServ.setUserSetting(userSeq, userSettingDto), 1, "유저 환경설정에 성공했습니다."), HttpStatus.OK);
//	}
	
	

}
