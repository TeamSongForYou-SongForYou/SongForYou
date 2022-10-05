package com.ssafy.gumid207.entity;

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
@Table(name = "t_lyrics")
public class Lyrics {
	
	@Id 
	@Column(name = "lyrics_seq")
	private Long lyricsSeq;
	
	@Column(name = "lyrics", length = 5000)
	private String lyrics;

}
