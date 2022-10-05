package com.ssafy.gumid207.res;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ssafy.gumid207.dto.FileDto;
import com.ssafy.gumid207.dto.SongDto;
import com.ssafy.gumid207.entity.MyRecord;

import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MyRecordResDto implements Serializable {

	private Long myRecordSeq;
	
	private SongDto songDto;
	
	private FileDto fileDto;
	
	@ApiParam(value = "등록 시간. (yyyy-MM-dd 00:00:00)")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime myRecordRegTime;

	public static MyRecordResDto of(MyRecord myRecord) {
		if (myRecord == null) {
			return null;
		}
		return new MyRecordResDtoBuilder() //
				.myRecordSeq(myRecord.getMyRecordSeq()) //
				.fileDto(FileDto.of(myRecord.getFile())) //
				.songDto(SongDto.of(myRecord.getSong())) //
				.myRecordRegTime(myRecord.getMyRecordRegTime()) //
				.build();
	}

}