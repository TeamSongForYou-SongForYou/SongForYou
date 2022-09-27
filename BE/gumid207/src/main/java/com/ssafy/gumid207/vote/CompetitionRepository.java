package com.ssafy.gumid207.vote;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.gumid207.entity.Competition;

public interface CompetitionRepository extends JpaRepository<Competition, Long>{
	List<Competition> findByCompetitionExpiryDateAfterOrderByCompetitionExpiryDateDesc(LocalDateTime nowTime);
}
