package com.ssafy.gumid207.song;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.ssafy.gumid207.customexception.DislikeNotFoundException;
import com.ssafy.gumid207.customexception.SongNotFoundException;
import com.ssafy.gumid207.customexception.UserNotFoundException;
import com.ssafy.gumid207.dto.SongDto;
import com.ssafy.gumid207.entity.Dislike;
import com.ssafy.gumid207.entity.Song;
import com.ssafy.gumid207.entity.User;
import com.ssafy.gumid207.user.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class SongServiceImpl implements SongService {
	private final UserRepository userRepo;
	private final SongRepository songRepo;
	private final SongDislikeRepository dislikeRepo;

	@Override
	public SongDto getSongDetail(Long songSeq) throws Exception {
		Song song = songRepo.findBySongSeq(songSeq).orElse( //
				Song.builder() //
						.songSeq(-1l) //
						.build());
		return SongDto.of(song);
	}

	@Override
	public String getSongSoundFileURL(Long songSeq) throws Exception {
		Song song = songRepo.findBySongSeq(songSeq).orElseThrow(() -> new SongNotFoundException("해당 곡을 찾을 수 없습니다."));
		return song.getYoutubeUrl();
	}

	@Override
	public Boolean dislikeSong(Long userSeq, Long songSeq) throws Exception {
		User user = userRepo.findByUserSeq(songSeq).orElseThrow(() -> new UserNotFoundException("해당 유저를 찾을 수 없습니다."));
		Song song = songRepo.findBySongSeq(songSeq).orElseThrow(() -> new SongNotFoundException("해당 곡을 찾을 수 없습니다."));
		Dislike dislike = Dislike.builder() //
				.user(user) //
				.song(song) //
				.build();
		dislikeRepo.save(dislike);
		return true;
	}
	
	@Override
	public Boolean deleteDislikeSong(Long userSeq, Long songSeq) throws Exception {
		User user = userRepo.findByUserSeq(songSeq).orElseThrow(() -> new UserNotFoundException("해당 유저를 찾을 수 없습니다."));
		Song song = songRepo.findBySongSeq(songSeq).orElseThrow(() -> new SongNotFoundException("해당 곡을 찾을 수 없습니다."));
		Dislike dislike = dislikeRepo.findByUserAndSong(user, song).orElseThrow(() -> new DislikeNotFoundException("해당 싫어요 정보를 찾을 수 없습니다."));
		dislikeRepo.delete(dislike);
		return true;
	}

	@Override
	public List<SongDto> getSongListBySongName(String songName) throws Exception {
		return songRepo.findByTitleIgnoreCaseContaining(songName) //
				.stream() //
				.map( //
						(song) -> //
						SongDto.of(song) //
				) //
				.collect( //
						Collectors.toList() //
				); //
	}

}
