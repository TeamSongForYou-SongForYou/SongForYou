package com.ssafy.gumid207.songbox;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.gumid207.customexception.MyListAlreadyExistException;
import com.ssafy.gumid207.customexception.MyListNotFoundException;
import com.ssafy.gumid207.customexception.MyRecordNotFoundException;
import com.ssafy.gumid207.customexception.MyRecordPermissionDeniedException;
import com.ssafy.gumid207.customexception.SongNotFoundException;
import com.ssafy.gumid207.customexception.UserNotFoundException;
import com.ssafy.gumid207.entity.File;
import com.ssafy.gumid207.entity.MyList;
import com.ssafy.gumid207.entity.MyRecord;
import com.ssafy.gumid207.entity.Song;
import com.ssafy.gumid207.entity.User;
import com.ssafy.gumid207.res.MyRecordResDto;
import com.ssafy.gumid207.s3.FileRepository;
import com.ssafy.gumid207.s3.S3FileService;
import com.ssafy.gumid207.song.SongRepository;
import com.ssafy.gumid207.user.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class SongBoxServiceImpl implements SongBoxService {

	private final UserRepository userRepo;
	private final SongRepository songRepo;
	private final MyListRepository myListRepo;
	private final MyRecordRepository myRecordRepo;
	private final FileRepository fileRepo;
	private final S3FileService s3serv;

	@Override
	public Boolean addMyList(Long userSeq, Long songSeq) throws Exception {
		User user = userRepo.findByUserSeq(userSeq).orElseThrow(() -> new UserNotFoundException("해당 유저를 찾을 수 없습니다."));
		Song song = songRepo.findBySongSeq(songSeq).orElseThrow(() -> new SongNotFoundException("해당 곡을 찾을 수 없습니다."));
		if (myListRepo.findByUserAndSong(user, song).isPresent()) {
			throw new MyListAlreadyExistException("이미 보관함에 담은 곡입니다.");
		}
		MyList myList = MyList.builder() //
				.user(user) //
				.song(song) //
				.build();
		myListRepo.save(myList);
		return true;
	}

	@Override
	public Boolean deleteMyList(Long userSeq, Long songSeq) throws Exception {
		User user = userRepo.findByUserSeq(userSeq).orElseThrow(() -> new UserNotFoundException("해당 유저를 찾을 수 없습니다."));
		Song song = songRepo.findBySongSeq(songSeq).orElseThrow(() -> new SongNotFoundException("해당 곡을 찾을 수 없습니다."));
		Optional<MyList> oMyList = myListRepo.findByUserAndSong(user, song);
		if (!oMyList.isPresent()) {
			throw new MyListNotFoundException("보관함에 곡이 없습니다.");
		}
		myListRepo.delete(oMyList.get());
		return true;
	}

	@Override
	public MyRecordResDto saveMySongRecord(Long userSeq, Long songSeq, MultipartFile recordFile) throws Exception {
		User user = userRepo.findByUserSeq(userSeq).orElseThrow(() -> new UserNotFoundException("해당 유저를 찾을 수 없습니다."));
		Song song = songRepo.findBySongSeq(songSeq).orElseThrow(() -> new SongNotFoundException("해당 곡을 찾을 수 없습니다."));

		File file = File.of(s3serv.upload(recordFile, userSeq.toString(), "songRecord", "mp3"));
		fileRepo.save(file);
		MyRecord myRecord = MyRecord.builder() //
				.user(user) //
				.song(song) //
				.file(file) //
				.build();
		myRecordRepo.save(myRecord);
		return MyRecordResDto.of(myRecord);
	}

	@Override
	public Boolean deleteMySongRecord(Long userSeq, Long myRecordSeq) throws Exception {
		User user = userRepo.findByUserSeq(userSeq).orElseThrow(() -> new UserNotFoundException("해당 유저를 찾을 수 없습니다."));
		MyRecord myRecord = myRecordRepo.findById(myRecordSeq).orElseThrow(() -> new MyRecordNotFoundException("해당 녹음 정보를 찾을 수 없습니다."));
		if (myRecord.getUser().getUserSeq() != user.getUserSeq()) {
			throw new MyRecordPermissionDeniedException("본인의 녹음만 삭제할 수 있습니다.");
		}
		myRecordRepo.delete(myRecord);

		return true;
	}

	@Override
	public List<MyRecordResDto> getMySongRecordList(Long userSeq) throws Exception {
		User user = userRepo.findByUserSeq(userSeq).orElseThrow(() -> new UserNotFoundException("해당 유저를 찾을 수 없습니다."));
		return myRecordRepo.findByUserOrderByMyRecordRegTimeDesc(user).stream().map( //
				(myRecord) -> //
				MyRecordResDto.of(myRecord) //
				) //
				.collect(Collectors.toList());
	}
}
