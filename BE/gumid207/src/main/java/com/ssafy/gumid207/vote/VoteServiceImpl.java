package com.ssafy.gumid207.vote;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.gumid207.customexception.CustomAlreadyExistException;
import com.ssafy.gumid207.customexception.SongNotFoundException;
import com.ssafy.gumid207.entity.Competition;
import com.ssafy.gumid207.entity.Song;
import com.ssafy.gumid207.res.CompetitionResDto;
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
public class VoteServiceImpl implements VoteService {

	private final UserRepository userRepo;
	private final SongRepository songRepo;
	private final FileRepository fileRepo;
	private final S3FileService s3serv;

	private final CompetitionRepository compRepo;
	
	@Override
	public Boolean addCompetition(Long songSeq, LocalDateTime startDateTime, Integer days) throws Exception {
		Song song = songRepo.findBySongSeq(songSeq).orElseThrow(() -> new SongNotFoundException("해당 곡을 찾을 수 없습니다."));
		List<Competition> check = compRepo.findBySongAndCompetitionEndDateAfter(song, startDateTime);
		if (check.size() > 0) {
			throw new CustomAlreadyExistException("해당 기간 이미 해당 곡의 투표가 있습니다.");
		}
		if (days == null || days <= 0) {
			days = 7;
		}
		Competition competition = Competition.builder() //
				.competitionExpiryDate(startDateTime) //
				.competitionEndDate(startDateTime.plusDays(days))
				.build();
		compRepo.save(competition);
		return true;
	}
	
	@Override
	public List<CompetitionResDto> getBeforeStartList() throws Exception {
		return compRepo.findByCompetitionExpiryDateAfterOrderByCompetitionExpiryDateDesc(LocalDateTime.now()).stream().map( //
				(comp) -> CompetitionResDto.of(comp)
				).collect(Collectors.toList());
	}
}
