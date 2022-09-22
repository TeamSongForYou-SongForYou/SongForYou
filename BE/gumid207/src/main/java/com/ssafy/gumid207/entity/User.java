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


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@Table(name = "t_user")
public class User {
	
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "user_seq")
	private Long userSeq;
	
	@Column(name = "user_nickname", unique = true)
	private String nickName;
	
	@Column(name = "user_point")
	private Integer point;
	
	@Column(name = "user_id", unique = true)
	private String id;
	
	@Column(name = "user_pass")
	private String pass;
	
	@OneToOne
	@JoinColumn(name = "user_profile_img_seq")
	private File profileImgSeq; 
	
	@Column(name = "user_birthday")
	private Integer birthday;
	
	@Column(name = "user_gender")
	private String gender;
	
	@Column(name = "user_fcm_token")
	private String fcmToken;
	
	@Column(name = "user_reg_time")
	private LocalDateTime regTime;
	

}