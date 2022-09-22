package com.ssafy.gumid207.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "t_review")
public class Review {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "review_seq")
	private Long reviewSeq;

	@OneToOne
	@JoinColumn(name = "user_seq", nullable = false)
	private Long userSeq;

	@ManyToOne
	@JoinColumn(name = "karaoke_seq", nullable = false)
	private Long karaokeSeq;

	@OneToOne
	@JoinColumn(name = "file_seq", nullable = false)
	private Long fileSeq;

	@Column(name = "review_price", nullable = true)
	private String reviewPrice;
	
	@Column(name = "review_pay_type", nullable = true)
	private String reviewPayType;
	
	@Column(name = "review_employee", nullable = true)
	private String reviewEmployee;
	
	@Column(name = "review_toilet", nullable = true)
	private String reviewToilet;
	
	@Column(name = "review_cleanness", nullable = false)
	private Integer reviewCleanness;

	@Column(name = "review_sound_quality", nullable = false)
	private Integer reviewSoundQuality;
	
	@Column(name = "review_content", nullable = false)
	private String reviewContent;

	@CreatedDate
	@Column(name = "review_reg_time")
	private LocalDateTime reviewRegTime;

}
