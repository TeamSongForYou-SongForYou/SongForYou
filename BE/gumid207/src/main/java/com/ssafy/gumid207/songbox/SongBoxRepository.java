package com.ssafy.gumid207.songbox;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.gumid207.entity.Song;

public interface SongBoxRepository extends JpaRepository<Song, Long>{
	Optional<Song> findBySongSeq(Long songSeq);
}
