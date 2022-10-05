package com.ssafy.gumid207.review;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.gumid207.entity.Karaoke;
import com.ssafy.gumid207.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Long>{
	List<Review> findByKaraokeSeq(Karaoke karaoke);
}
