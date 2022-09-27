package com.ssafy.gumid207.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ssafy.gumid207.customexception.CustomAlreadyExistException;
import com.ssafy.gumid207.customexception.CustomNotFoundException;
import com.ssafy.gumid207.customexception.CustomPermissionDeniedException;
import com.ssafy.gumid207.res.ResponseFrame;

@RestControllerAdvice
public class TotalRestControllerAdvice {

	@ExceptionHandler({CustomNotFoundException.class})
	public ResponseEntity<?> notfoundControll(CustomNotFoundException error) {

		return new ResponseEntity<>(ResponseFrame.of(false, error.getMessage()), HttpStatus.OK);
	}
	@ExceptionHandler({CustomAlreadyExistException.class})
	public ResponseEntity<?> alreadyExistControll(CustomAlreadyExistException error) {

		return new ResponseEntity<>(ResponseFrame.of(false, error.getMessage()), HttpStatus.OK);
	}
	@ExceptionHandler({CustomPermissionDeniedException.class})
	public ResponseEntity<?> permissionDeniedControll(CustomPermissionDeniedException error) {

		return new ResponseEntity<>(ResponseFrame.of(false, error.getMessage()), HttpStatus.OK);
	}
}
