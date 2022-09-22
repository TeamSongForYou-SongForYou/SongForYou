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
import javax.persistence.ManyToOne;
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
@Table(name = "t_dislike")
public class Dislike {
	
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "dislike_seq")
	private Long dislikeSeq;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="song_seq", nullable = false)
	private Song song;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user_seq", nullable = false)
	private User user;

	@CreatedDate
	@Column(name = "dislike_reg_time")
	private LocalDateTime dislikeRegTime;

}
