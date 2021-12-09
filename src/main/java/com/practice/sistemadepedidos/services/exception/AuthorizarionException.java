package com.practice.sistemadepedidos.services.exception;

public class AuthorizarionException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public AuthorizarionException(String msg) {
		super(msg);
	}
	
	public AuthorizarionException(String msg, Throwable cause) {
		super(msg,cause);
	}
}
