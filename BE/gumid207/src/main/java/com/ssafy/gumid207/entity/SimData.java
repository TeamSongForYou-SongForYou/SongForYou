package com.ssafy.gumid207.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "t_sim_data")
public class SimData {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "sim_data_seq")
	private Long simDataSeq;
	
	@Column(name = "sim_data_mysong", nullable = false)
	private Long simDataMysong;
	
	@Column(name = "sim_data_targetsong", nullable = false)
	private Long simDataTargetsong;
	
	@Column(name = "sim_data_similarity", nullable = false)
	private Double simDataSimilarity;

}
