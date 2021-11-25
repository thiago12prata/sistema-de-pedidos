package com.practice.sistemadepedidos.resources.exceptions;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {
	private static final long serialVersionUID = 1L;

	private List<FieldMessage> Errors = new ArrayList<>();
	
	public ValidationError() {
		
	}
	public ValidationError(Instant timestamp, Integer status, String error, String message, String path) {
		super(timestamp, status, error, message, path);
	}


	public List<FieldMessage> getErrors() {
		return Errors;
	}
	public void addError(String fieldname, String messsage) {
		Errors.add(new FieldMessage(fieldname, messsage));
	}
}
