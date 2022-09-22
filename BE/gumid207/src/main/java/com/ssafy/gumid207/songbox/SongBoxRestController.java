package com.ssafy.gumid207.songbox;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.gumid207.res.ResponseFrame;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/songbox")
@RequiredArgsConstructor
@Slf4j
@Api(tags = "보관함 컨트롤러")
public class SongBoxRestController {
	
	private final SongBoxService songBoxServ;
	
	@ApiParam(value = "곡 정보 반환")
	@GetMapping(value = "/{songSeq}")
	public ResponseEntity<?> getUserSetting(@PathVariable Long songSeq) throws Exception{
		return new ResponseEntity<>(new ResponseFrame<>(true, songBoxServ.getUserSetting(songSeq), 1, "곡 정보를 반환합니다."), HttpStatus.OK);
	}
	
//	@ApiParam(value = "유저 환경정보 세팅 (넣은 정보만 변경, null인 정보는 유지)")
//	@PostMapping(value = "/{userSeq}")
//	public ResponseEntity<?> setUserSetting(@PathVariable Long userSeq, @RequestBody UserSettingDto userSettingDto) throws Exception{
//		return new ResponseEntity<>(new ResponseFrame<>(true, settingServ.setUserSetting(userSeq, userSettingDto), 1, "유저 환경설정에 성공했습니다."), HttpStatus.OK);
//	}
	
	

}
