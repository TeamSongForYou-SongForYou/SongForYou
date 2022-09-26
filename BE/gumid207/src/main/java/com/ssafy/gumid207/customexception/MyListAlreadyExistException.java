package com.ssafy.gumid207.customexception;

public class MyListAlreadyExistException extends CustomAlreadyExistException {

	public MyListAlreadyExistException(String msg) {
		super(msg);
	}
}
