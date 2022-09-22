package com.ssafy.gumid207.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;

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
@Table(name = "t_song")
public class Song {
	
	@Id 
	@Column(name = "song_seq")
	private Long songSeq;
	
	@Column(name = "song_title", nullable = false)
	private String title;
	
	@Column(name = "song_artist_name", nullable = false)
	private String artistName;
	
	@Column(name = "song_youtube_view", nullable = true)
	private Integer youtubeView;
	
	@Column(name = "song_genre", nullable = false)
	private String genre;
	
	@Column(name = "song_thumbnail_url", nullable = true)
	private String thumbnailUrl;
	
	@Column(name = "song_youtube_url", nullable = true)
	private String youtubeUrl;
	
	@Column(name = "song_reg_time")
	private LocalDateTime regTime;

}
