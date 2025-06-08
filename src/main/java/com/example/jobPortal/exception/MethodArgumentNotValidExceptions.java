package com.example.jobPortal.exception;


@SuppressWarnings("serial")
public class MethodArgumentNotValidExceptions extends RuntimeException{
	public String msg;
	public MethodArgumentNotValidExceptions(String msg) {
		super(msg);
		this.msg=msg;
	}
}
