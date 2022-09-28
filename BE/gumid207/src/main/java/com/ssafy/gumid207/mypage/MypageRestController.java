package com.ssafy.gumid207.mypage;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.gumid207.customexception.CustomIllegalParameterException;
import com.ssafy.gumid207.dto.FileDto;
import com.ssafy.gumid207.dto.UserDto;
import com.ssafy.gumid207.entity.User;
import com.ssafy.gumid207.jwt.SecurityUtil;
import com.ssafy.gumid207.res.ResponseFrame;
import com.ssafy.gumid207.res.UserResDto;
import com.ssafy.gumid207.user.UserRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/my-page")
@RequiredArgsConstructor
@Slf4j
@Api(tags = "마이페이지 컨트롤러")
public class MypageRestController {
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
	
	private final MypageService mypageServ;

	@ApiOperation(value = "녹음 파일을 서버에 저장하기")
	@PostMapping(value="/{userSeq}/record",consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE})
	public ResponseEntity<?> saveMyRecord(@PathVariable Long userSeq,
			@RequestPart(value = "recordFile", required = true) MultipartFile recordFile) throws Exception {
		UserDto userDto = getLoginUser();
		if (userSeq != userDto.getUserSeq()) {
			throw new CustomIllegalParameterException("로그인 정보가 올바르지 않습니다.");
		}
		Boolean result = mypageServ.saveMyRecord(userDto.getUserSeq(), recordFile);
		return new ResponseEntity<>(new ResponseFrame<>(true, result, 1, "사용자 음성 정보를 저장했습니다."), HttpStatus.OK);
	}
	
	@ApiOperation(value = "닉네임 변경하기")
	@PostMapping(value="/{userSeq}/nick-name")
	public ResponseEntity<?> changeNickNmae(@PathVariable Long userSeq, //
			@RequestParam String userNickName) throws Exception {
		UserDto userDto = getLoginUser();
		if (userSeq != userDto.getUserSeq()) {
			throw new CustomIllegalParameterException("자신의 닉네임만 변경할 수 있습니다.");
		}
		Boolean result = mypageServ.changeNickName(userSeq, userNickName);
		return new ResponseEntity<>(new ResponseFrame<>(true, result, 1, "닉네임을 변경했습니다."), HttpStatus.OK);
	}
	
	@ApiOperation(value = "프로필 사진 변경하기")
	@PostMapping(value="/{userSeq}/profile-img",consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE})
	public ResponseEntity<?> changeProfileImg(@PathVariable Long userSeq,
			@RequestPart(value = "profileImg", required = true) MultipartFile recordFile) throws Exception {
		UserDto userDto = getLoginUser();
		if (userSeq != userDto.getUserSeq()) {
			throw new CustomIllegalParameterException("자신의 프로필 사진만 변경할 수 있습니다.");
		}
		FileDto fileDto = mypageServ.changeProfileImg(userSeq, recordFile);
		return new ResponseEntity<>(new ResponseFrame<>(true, fileDto, 1, "프로플 사진을 변경했습니다."), HttpStatus.OK);
	}
	
	@ApiOperation(value = "프로필 사진 조회하기")
	@GetMapping(value="/{userSeq}/profile-img")
	public ResponseEntity<?> changeProfileImg(@PathVariable Long userSeq) throws Exception {
		FileDto fileDto = mypageServ.getProfileImg(userSeq);
		return new ResponseEntity<>(new ResponseFrame<>(true, fileDto, 1, "프로플 사진을 조회했습니다."), HttpStatus.OK);
	}
	
	@ApiOperation(value = "회원 정보 조회하기")
	@GetMapping(value="/{userSeq}")
	public ResponseEntity<?> getUserInfo(@PathVariable Long userSeq) throws Exception {
		UserResDto userResDto = mypageServ.getUserInfo(userSeq);
		return new ResponseEntity<>(new ResponseFrame<>(true, userResDto, 1, "프로플 사진을 조회했습니다."), HttpStatus.OK);
	}

}
