package com.ssafy.gumid207.res;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseUser<T> {
	
	private Boolean success;
	private T data;
	private String message;
	
	public static <T> ResponseUser<?> of(Boolean success, T data, String message) {
		ResponseUser<T> frame = new ResponseUser<>();
		frame.setSuccess(success);
		frame.setData(data);
		frame.setMessage(message);
		return frame;
	}

}
