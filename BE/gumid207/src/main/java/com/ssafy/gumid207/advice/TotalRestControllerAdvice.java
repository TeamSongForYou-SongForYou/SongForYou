package com.ssafy.gumid207.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ssafy.gumid207.customexception.CustomNotFoundException;
import com.ssafy.gumid207.res.ResponseFrame;

@RestControllerAdvice
public class TotalRestControllerAdvice {

	@ExceptionHandler({CustomNotFoundException.class})
	public ResponseEntity<?> userNofoundControll(CustomNotFoundException error) {

		return new ResponseEntity<>(ResponseFrame.of(false, error.getMessage()), HttpStatus.OK);
	}
}
