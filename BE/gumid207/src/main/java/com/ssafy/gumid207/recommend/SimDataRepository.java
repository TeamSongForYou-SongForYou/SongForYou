package com.ssafy.gumid207.recommend;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ssafy.gumid207.entity.SimData;

public interface SimDataRepository extends JpaRepository<SimData, Long> {
	List<SimData> findAllBySimDataMysong(Long songNum);

}
