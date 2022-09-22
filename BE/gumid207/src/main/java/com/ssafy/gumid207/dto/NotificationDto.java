package com.ssafy.gumid207.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ssafy.gumid207.entity.Notification;

import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 직렬화 기능을 가진 User클래스
 */
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NotificationDto implements Serializable {

	private Long notificationSeq;

	@ApiParam(value = "알림 이름")
	private String notificationTitle;

	@ApiParam(value = "알림 내용")
	private String notificationContent;

	@ApiParam(value = "FCM 토큰")
	private String notificationFcmToken;

	@ApiParam(value = "등록 시간. (yyyy-MM-dd 00:00:00)")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime notificationRegTime;

	public static NotificationDto of(Notification notification) {
		if (notification == null) {
			return null;
		}
		return new NotificationDtoBuilder() //
				.notificationSeq(notification.getNotificationSeq()) //
				.notificationContent(notification.getNotificationContent()) //
				.notificationFcmToken(notification.getNotificationFcmToken()) //
				.notificationRegTime(notification.getNotificationRegTime()) //
				.build();
	}

}