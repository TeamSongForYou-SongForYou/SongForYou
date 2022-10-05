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

import com.ssafy.gumid207.dto.FileDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "t_file")
public class File {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "file_seq")
	private Long fileSeq;
	
	@Column(name = "file_original_name", nullable = false)
	private String originalName;
	
	@Column(name = "file_saved_name", nullable = false)
	private String savedName;
	
	@Column(name = "file_saved_path", nullable = false)
	private String savedPath;
	
	@Column(name = "file_type", nullable = false)
	private String type;

	@CreatedDate
	@Column(name = "file_reg_time")
	private LocalDateTime regTime;
	
	public static File of (FileDto fileDto) {
		if (fileDto == null) {
			return null;
		}
		else {
			return File.builder() //
					.fileSeq(fileDto.getFileSeq()) //
					.originalName(fileDto.getFileOriginalName()) //
					.regTime(fileDto.getFileRegTime()) //
					.savedName(fileDto.getFileSavedName()) //
					.savedPath(fileDto.getFileSavedPath()) //
					.type(fileDto.getFileType()) //
					.build();
		}
	}
}
