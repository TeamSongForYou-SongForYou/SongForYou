package com.ssafy.gumid207.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Builder;
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
	
	@CreatedDate
	@Column(name = "user_reg_time")
	private LocalDateTime regTime;
	
	@Enumerated(EnumType.STRING)
	private Authority authority;


	@Builder
	public User(String nickName, String email, String id, String pass, Integer point, File profileImgSeq, Integer birthday, String gender,
			String fcmToken, LocalDateTime regTime) {
		this.nickName = nickName;
		this.email = email;
		this.id = id;
		this.pass = pass;
		this.point = point;
		this.profileImgSeq = profileImgSeq;
		this.birthday = birthday;
		this.gender = gender;
		this.fcmToken = fcmToken;
		this.authority = Authority.ROLE_USER;
		this.regTime = LocalDateTime.now();
	}
	
	public void modifyPass(String pass) {
		this.pass = pass;
	}
	
	public User update(String name, String picture) {
	       this.id = name;
	       this.nickName = picture;

	       return this;
	   }
	
	

}