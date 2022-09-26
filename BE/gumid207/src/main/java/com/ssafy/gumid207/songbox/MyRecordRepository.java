package com.ssafy.gumid207.songbox;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.gumid207.entity.MyRecord;
import com.ssafy.gumid207.entity.User;

public interface MyRecordRepository extends JpaRepository<MyRecord, Long>{
	List<MyRecord> findByUserAndMyRecordRegTimeAfter(User user, LocalDateTime splitTime);
	List<MyRecord> findTop1000ByOrderByMyRecordRegTimeDesc();
}
