package com.ssafy.gumid207.song;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.gumid207.devutil.DevUtil;
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
@RequestMapping("/song")
@RequiredArgsConstructor
@Slf4j
@Api(tags = "노래 정보 컨트롤러")
public class SongRestController {
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
	
	private final SongService songServ;
	
	@ApiParam(value = "곡 정보 반환")
	@GetMapping(value = "/{songSeq}/detail")
	public ResponseEntity<?> getSongDetail(@PathVariable Long songSeq) throws Exception{
		return new ResponseEntity<>(new ResponseFrame<>(true, songServ.getSongDetail(songSeq), 1, "곡 정보를 반환합니다."), HttpStatus.OK);
	}
	
	@ApiParam(value = "곡 URL 반환")
	@GetMapping(value = "/{songSeq}/sound-file")
	public ResponseEntity<?> getSongSoundFileURL(@PathVariable Long songSeq) throws Exception{
		return new ResponseEntity<>(new ResponseFrame<>(true, songServ.getSongSoundFileURL(songSeq), 1, "곡 URL을 반환합니다."), HttpStatus.OK);
	}
	
	@ApiParam(value = "곡 싫어요 하기")
	@PostMapping(value = "/{songSeq}/dislike")
	public ResponseEntity<?> dislikeSong(@PathVariable Long songSeq) throws Exception{
		UserDto userDto = getLoginUser();
		return new ResponseEntity<>(new ResponseFrame<>(true, songServ.dislikeSong(userDto.getUserSeq(), songSeq), 1, "곡 싫어요를 합니다."), HttpStatus.OK);
	}
	
	@ApiParam(value = "곡 싫어요 해제하기")
	@DeleteMapping(value = "/{songSeq}/dislike")
	public ResponseEntity<?> deleteDislikeSong(@PathVariable Long songSeq) throws Exception{
		UserDto userDto = getLoginUser();
		return new ResponseEntity<>(new ResponseFrame<>(true, songServ.deleteDislikeSong(userDto.getUserSeq(), songSeq), 1, "곡 싫어요를 해제합니다."), HttpStatus.OK);
	}
	
	@ApiParam(value = "곡 이름 검색")
	@GetMapping(value = "/{songName}/search")
	public ResponseEntity<?> getSongListBySongName(@PathVariable String songName) throws Exception{
		List<SongDto> songDtoList = songServ.getSongListBySongName(songName);
		return new ResponseEntity<>(new ResponseFrame<>(true, songDtoList, songDtoList.size(), "곡 제목으로 검색합니다."), HttpStatus.OK);
	}
	
	

}
