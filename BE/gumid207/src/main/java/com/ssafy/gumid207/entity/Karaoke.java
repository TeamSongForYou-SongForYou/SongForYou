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
@Table(name = "t_karaoke")
public class Karaoke {
	
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "karaoke_seq")
	private Long karaokeSeq;

	@Column(name="karaoke_name", nullable = false)
	private String karaoke_name;
	
	@Column(name="karaoke_address", nullable = false)
	private String karaoke_address;
	
	@CreatedDate
	@Column(name = "karaoke_reg_time")
	private LocalDateTime karaokeRegTime;

}
