package org.jzz.spbootDemo.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "报错需要理由吗，这可是自定义异常！")
public class MyException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MyException() {
		
	}
	
	public MyException(String message) {
		super(message);
	}
}
