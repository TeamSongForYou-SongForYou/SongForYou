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
@Table(name = "t_my_record")
public class MyRecord {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "my_record_seq")
	private Long myRecordSeq;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "song_seq", nullable = false)
	private Song song;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_seq", nullable = false)
	private User user;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "file_seq", nullable = false)
	private File file;

	@CreatedDate
	@Column(name = "my_record_reg_time")
	private LocalDateTime myRecordRegTime;

}
