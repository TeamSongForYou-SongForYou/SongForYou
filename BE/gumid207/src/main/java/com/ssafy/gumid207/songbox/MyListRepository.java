package com.ssafy.gumid207.songbox;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.gumid207.entity.MyList;
import com.ssafy.gumid207.entity.User;

public interface MyListRepository extends JpaRepository<MyList, Long>{
	List<MyList> findByUser(User user);
}
