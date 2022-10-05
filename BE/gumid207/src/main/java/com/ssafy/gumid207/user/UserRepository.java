package com.ssafy.gumid207.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.gumid207.entity.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	Optional<User> findByUserSeq(Long userSeq);
	Optional<User> findById(String id);
	Optional<User> findByNickName(String nickName);
	Optional<User> findByEmail(String email); 
	Optional<User> findByIdAndPass(String id, String pass);

}
