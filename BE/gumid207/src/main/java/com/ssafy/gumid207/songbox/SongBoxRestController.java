package com.ssafy.gumid207.songbox;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.gumid207.dto.UserDto;
import com.ssafy.gumid207.entity.User;
import com.ssafy.gumid207.res.ResponseFrame;
import com.ssafy.gumid207.user.UserRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/song-box")
@RequiredArgsConstructor
@Slf4j
@Api(tags = "보관함 컨트롤러")
public class SongBoxRestController {
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
	
	private final SongBoxService songBoxServ;

	@ApiOperation(value = "녹음 파일을 서버에 저장하기")
	@PostMapping(value="/my-record/{songSeq}")
	public ResponseEntity<?> addMyList(@PathVariable Long songSeq) throws Exception {
		UserDto userDto = getLoginUser();
		Boolean result = songBoxServ.addMyList(userDto.getUserSeq(), songSeq);
		return new ResponseEntity<>(new ResponseFrame<>(true, result, 1, "보관함에 추가했습니다."), HttpStatus.OK);
	}
	

}
