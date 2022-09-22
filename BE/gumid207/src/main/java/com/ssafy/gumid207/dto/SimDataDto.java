package com.ssafy.gumid207.dto;

import java.io.Serializable;

import com.ssafy.gumid207.entity.SimData;

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
public class SimDataDto implements Serializable {

	private Long simDataSeq;

	@ApiParam(value = "대상 곡")
	private Long simDataMysong;

	@ApiParam(value = "유사 곡")
	private Long simDataTargetsong;

	@ApiParam(value = "유사도")
	private Double simDataSimilarity;

	public static SimDataDto of(SimData simData) {
		if (simData == null) {
			return null;
		}
		return new SimDataDtoBuilder() //
				.simDataSeq(simData.getSimDataSeq()) //
				.simDataMysong(simData.getSimDataMysong()) //
				.simDataTargetsong(simData.getSimDataTargetsong()) //
				.simDataSimilarity(simData.getSimDataSimilarity()) //
				.build();
	}

}