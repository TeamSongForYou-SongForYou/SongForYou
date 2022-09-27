package com.ssafy.gumid207.s3;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.gumid207.entity.File;

public interface FileRepository extends JpaRepository<File, Long> {

}
