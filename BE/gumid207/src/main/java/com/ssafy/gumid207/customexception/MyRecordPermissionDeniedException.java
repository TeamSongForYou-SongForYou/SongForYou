package com.ssafy.gumid207.customexception;

public class MyRecordPermissionDeniedException extends CustomPermissionDeniedException {

	public MyRecordPermissionDeniedException(String msg) {
		super(msg);
	}
}
