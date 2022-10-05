package com.ssafy.gumid207.mypage;

import java.net.URI;
import java.nio.charset.Charset;
import java.util.Optional;

import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import com.ssafy.gumid207.customexception.CustomAlreadyExistException;
import com.ssafy.gumid207.customexception.CustomIllegalParameterException;
import com.ssafy.gumid207.customexception.UserNotFoundException;
import com.ssafy.gumid207.dto.FileDto;
import com.ssafy.gumid207.entity.File;
import com.ssafy.gumid207.entity.User;
import com.ssafy.gumid207.res.UserResDto;
import com.ssafy.gumid207.s3.FileRepository;
import com.ssafy.gumid207.s3.S3FileService;
import com.ssafy.gumid207.song.SongRepository;
import com.ssafy.gumid207.user.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class MypageServiceImpl implements MypageService {

	private final UserRepository userRepo;
	private final SongRepository songRepo;
	private final FileRepository fileRepo;
	private final S3FileService s3serv;

	@Override
	public Boolean saveMyRecord(Long userSeq, MultipartFile recordFile) throws Exception {
		User user = userRepo.findByUserSeq(userSeq).orElseThrow(() -> new UserNotFoundException("해당 유저를 찾을 수 없습니다."));
		FileDto fileDto = s3serv.upload(recordFile, userSeq.toString(), "voice", "mp3");
		URI uri = UriComponentsBuilder //
				.fromUriString("http://j7d207.p.ssafy.io:8000") //
				.path("/api/v1/my-page/" + userSeq + "/record/") //
				.encode(Charset.defaultCharset()) //
				.build() //
				.toUri(); //
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		JSONObject jsonObject = new JSONObject();
//		jsonObject.put("url",
//				"https://songforyou.s3.ap-northeast-2.amazonaws.com/voice/13803e04-e376-45a2-abd4-bb73c748667a.mp3");
		jsonObject.put("url", fileDto.getFileSavedPath());
		HttpEntity<String> request = new HttpEntity<String>(jsonObject.toString(), headers);
		restTemplate.postForObject(uri, request, Object.class);

		return true;
	}
	
	@Override
	public Boolean changeNickName(Long userSeq, String userNickName) throws Exception {
		User user = userRepo.findById(userSeq).orElseThrow(() -> new UserNotFoundException("해당 유저를 찾을 수 없습니다."));
		Optional<User> nickCheck = userRepo.findByNickName(userNickName);
		if (nickCheck.isPresent()) {
			throw new CustomAlreadyExistException("이미 존재하는 닉네임입니다.");
		}
		user.setNickName(userNickName);
		userRepo.save(user);
		return true;
	}
	
	@Override
	public FileDto changeProfileImg(Long userSeq, MultipartFile profileImg) throws Exception {
		User user = userRepo.findById(userSeq).orElseThrow(() -> new UserNotFoundException("해당 유저를 찾을 수 없습니다."));
		if (!profileImg.getContentType().contains("image")) {
			throw new CustomIllegalParameterException("프로필 사진은 이미지만 가능합니다.");
		}
		File file = File.of(s3serv.upload(profileImg, profileImg.getOriginalFilename(), "profile", "png"));
		fileRepo.save(file);
		user.setProfileImgSeq(file);
		userRepo.save(user);
		return FileDto.of(file);
	}
	
	@Override
	public FileDto getProfileImg(Long userSeq) throws Exception {
		User user = userRepo.findById(userSeq).orElseThrow(() -> new UserNotFoundException("해당 유저를 찾을 수 없습니다."));
		return FileDto.of(user.getProfileImgSeq());
	}
	
	@Override
	public UserResDto getUserInfo(Long userSeq) throws Exception {
		User user = userRepo.findById(userSeq).orElseThrow(() -> new UserNotFoundException("해당 유저를 찾을 수 없습니다."));
		return UserResDto.of(user);
	}
	
}
