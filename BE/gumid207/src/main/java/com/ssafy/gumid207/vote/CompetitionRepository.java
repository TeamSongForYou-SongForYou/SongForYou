package com.ssafy.gumid207.vote;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.gumid207.entity.Competition;
import com.ssafy.gumid207.entity.Song;

public interface CompetitionRepository extends JpaRepository<Competition, Long>{
	List<Competition> findByCompetitionExpiryDateAfterOrderByCompetitionExpiryDateDesc(LocalDateTime nowTime);
	List<Competition> findBySongAndCompetitionEndDateAfter(Song song, LocalDateTime startDateTime);
}
