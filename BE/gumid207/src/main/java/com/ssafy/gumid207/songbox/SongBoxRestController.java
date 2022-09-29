package com.ssafy.gumid207.songbox;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.gumid207.dto.MyListDto;
import com.ssafy.gumid207.dto.UserDto;
import com.ssafy.gumid207.entity.User;
import com.ssafy.gumid207.jwt.SecurityUtil;
import com.ssafy.gumid207.res.MyRecordResDto;
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
	
	private final SongBoxService songBoxServ;

	@ApiOperation(value = "노래 보관함에 추가")
	@PostMapping(value="/my-box/{songSeq}")
	public ResponseEntity<?> addMyList(@PathVariable Long songSeq) throws Exception {
		UserDto userDto = getLoginUser();
		Boolean result = songBoxServ.addMyList(userDto.getUserSeq(), songSeq);
		return new ResponseEntity<>(new ResponseFrame<>(true, result, 1, "보관함에 추가했습니다."), HttpStatus.OK);
	}
	
	@ApiOperation(value = "노래 보관함에서 삭제")
	@DeleteMapping(value="/my-box/{songSeq}")
	public ResponseEntity<?> deleteMyList(@PathVariable Long songSeq) throws Exception {
		UserDto userDto = getLoginUser();
		Boolean result = songBoxServ.deleteMyList(userDto.getUserSeq(), songSeq);
		return new ResponseEntity<>(new ResponseFrame<>(true, result, 1, "보관함에서 삭제했습니다."), HttpStatus.OK);
	}

	@ApiOperation(value = "노래 보관함 목록 받아오기")
	@GetMapping(value="/my-box")
	public ResponseEntity<?> getMyList() throws Exception {
		UserDto userDto = getLoginUser();
		List<MyListDto> results = songBoxServ.getMyList(userDto.getUserSeq());
		return new ResponseEntity<>(new ResponseFrame<>(true, results, results.size(), "내 보관함 목록을 가져왔습니다."), HttpStatus.OK);
	}

	@ApiOperation(value = "곡 녹음 저장")
	@PostMapping(value="/my-record")
	public ResponseEntity<?> saveMySongRecord(
			@RequestPart(name = "reviewUploadDto", required = true) String reviewUploadString,
			@RequestPart(required = true) MultipartFile recordFile) throws Exception {
		Long songSeq = 9l;
		UserDto userDto = getLoginUser();
		MyRecordResDto result = songBoxServ.saveMySongRecord(userDto.getUserSeq(), songSeq, imgFile);
		return new ResponseEntity<>(new ResponseFrame<>(true, result, 1, "녹음파일을 저장했습니다."), HttpStatus.OK);
	}
	
	@ApiOperation(value = "녹음 파일 삭제")
	@DeleteMapping(value="/my-record/{myRecordSeq}")
	public ResponseEntity<?> deleteMySongRecord(@PathVariable Long myRecordSeq) throws Exception {
		UserDto userDto = getLoginUser();
		Boolean result = songBoxServ.deleteMySongRecord(userDto.getUserSeq(), myRecordSeq);
		return new ResponseEntity<>(new ResponseFrame<>(true, result, 1, "녹음파일을 삭제했습니다."), HttpStatus.OK);
	}

	@ApiOperation(value = "곡 녹음 목록 받아오기")
	@GetMapping(value="/my-record")
	public ResponseEntity<?> getMySongRecordList() throws Exception {
		UserDto userDto = getLoginUser();
		List<MyRecordResDto> results = songBoxServ.getMySongRecordList(userDto.getUserSeq());
		return new ResponseEntity<>(new ResponseFrame<>(true, results, results.size(), "녹음 목록을 가져왔습니다."), HttpStatus.OK);
	}
	

}
