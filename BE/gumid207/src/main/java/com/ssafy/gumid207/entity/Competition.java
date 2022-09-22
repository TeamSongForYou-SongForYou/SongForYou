package com.ssafy.gumid207.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@Builder
@Table(name = "t_competition")
public class Competition {
	
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "competition_seq")
	private Long competitionSeq;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "song_seq", nullable = false)
	private Song song;

	@Column(name = "competition_expiry_date", nullable = false)
	private LocalDateTime competitionExpiryDate;

	@Column(name = "competition_end_date", nullable = false)
	private LocalDateTime competitionEndDate;
	
	@CreatedDate
	@Column(name = "competition_reg_time")
	private LocalDateTime competitionRegTime;

}
