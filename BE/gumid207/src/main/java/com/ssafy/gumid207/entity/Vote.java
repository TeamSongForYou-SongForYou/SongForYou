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
@Table(name = "t_vote")
public class Vote {
	
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "vote_seq")
	private Long voteSeq;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "vote_img_seq", nullable = true)
	private File voteImgSeq;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "vote_user_seq", nullable = false)
	private User voteUserSeq;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "vote_competition_seq", nullable = false)
	private Competition voteCompetitionSeq;

	@Column(name = "vote_file_type", nullable = false)
	private String voteFileType;

	@Column(name = "vote_item_title", nullable = false)
	private String voteItemTitle;

	@Column(name = "vote_point", nullable = false)
	private Integer votePoint;
	
	@CreatedDate
	@Column(name = "vote_reg_time")
	private LocalDateTime voteRegTime;

}
