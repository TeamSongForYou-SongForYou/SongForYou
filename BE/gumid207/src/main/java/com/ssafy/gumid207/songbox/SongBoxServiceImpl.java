package com.ssafy.gumid207.songbox;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.gumid207.customexception.MyListAlreadyExistException;
import com.ssafy.gumid207.customexception.SongNotFoundException;
import com.ssafy.gumid207.customexception.UserNotFoundException;
import com.ssafy.gumid207.entity.MyList;
import com.ssafy.gumid207.entity.Song;
import com.ssafy.gumid207.entity.User;
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

}
