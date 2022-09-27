package com.ssafy.gumid207.vote;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.gumid207.res.CompetitionResDto;
import com.ssafy.gumid207.s3.FileRepository;
import com.ssafy.gumid207.s3.S3FileService;
import com.ssafy.gumid207.user.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class VoteServiceImpl implements VoteService {

	private final UserRepository userRepo;
	private final FileRepository fileRepo;
	private final S3FileService s3serv;

	private final CompetitionRepository compRepo;
	@Override
	public List<CompetitionResDto> getBeforeStartList() throws Exception {
		return compRepo.findByCompetitionExpiryDateAfterOrderByCompetitionExpiryDateDesc(LocalDateTime.now()).stream().map( //
				(comp) -> CompetitionResDto.of(comp)
				).collect(Collectors.toList());
	}
}
