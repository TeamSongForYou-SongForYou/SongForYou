package com.ssafy.gumid207.song;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.gumid207.entity.Song;

public interface SongRepository extends JpaRepository<Song, Long>{
	Optional<Song> findBySongSeq(Long songSeq);
	List<Song> findByTitleIgnoreCaseContaining(String songName);
	
}
