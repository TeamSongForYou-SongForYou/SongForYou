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

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "t_user")
public class User {
	
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "user_seq")
	private Long userSeq;
	
	@Column(name = "user_nickname", unique = true, nullable = false)
	private String nickName;
	
	@Column(name = "user_point", nullable = true)
	private Integer point;
	
	@Column(name = "user_id", unique = true, nullable = false)
	private String id;
	
	@Column(name = "user_pass", nullable = true)
	private String pass;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_profile_img_seq", nullable = true)
	private File profileImgSeq; 

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_voice_file_seq", nullable = true)
	private File voiceFileSeq; 
	
	@Column(name = "user_birthday", nullable = true)
	private Integer birthday;
	
	@Column(name = "user_gender", nullable = true)
	private String gender;
	
	@Column(name = "user_fcm_token", nullable = true)
	private String fcmToken;
	
	@Column(name = "user_email", nullable = true)
	private String email;
	
	@Column(name = "user_reg_time")
	private LocalDateTime regTime;
	

}