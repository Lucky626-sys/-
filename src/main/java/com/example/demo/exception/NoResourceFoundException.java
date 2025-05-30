//自訂異常類別
package com.example.demo.exception;

public class NoResourceFoundException extends RuntimeException{

	public NoResourceFoundException(String message) {
		super(message);
	}
}
