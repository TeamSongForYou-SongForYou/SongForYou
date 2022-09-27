package com.ssafy.gumid207.review;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.gumid207.entity.Karaoke;

public interface KaraokeRepository extends JpaRepository<Karaoke, Long>{
	Optional<Karaoke> findByKaraokeNameAndKaraokeAddress(String karaokeName, String karaokeAddress);
}
