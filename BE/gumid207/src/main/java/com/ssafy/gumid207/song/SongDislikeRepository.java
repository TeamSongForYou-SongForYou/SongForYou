package com.ssafy.gumid207.song;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.gumid207.entity.Dislike;
import com.ssafy.gumid207.entity.Song;
import com.ssafy.gumid207.entity.User;

public interface SongDislikeRepository extends JpaRepository<Dislike, Long> {
	Optional<Dislike> findByUserAndSong(User user, Song song);
	List<Dislike> findByUser_userSeq(Long userSeq);

}
