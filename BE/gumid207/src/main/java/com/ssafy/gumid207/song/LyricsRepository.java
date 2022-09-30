package com.ssafy.gumid207.song;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.gumid207.entity.Lyrics;

public interface LyricsRepository extends JpaRepository<Lyrics, Long>{

}
