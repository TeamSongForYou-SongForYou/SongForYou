package com.ssafy.gumid207.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "t_notification")
public class Notification {
	
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "notification_seq")
	private Long notificationSeq;
	
	@Column(name = "notification_title", nullable = false)
	private String notificationTitle;

	@Column(name = "notification_content", nullable = false)
	private String notificationContent;
	
	@Column(name = "notification_fcm_token", nullable = false)
	private String notificationFcmToken;

	@CreatedDate
	@Column(name = "notification_reg_time")
	private LocalDateTime notificationRegTime;

}
