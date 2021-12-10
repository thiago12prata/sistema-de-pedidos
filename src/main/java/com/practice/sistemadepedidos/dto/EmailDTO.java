package com.practice.sistemadepedidos.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class EmailDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@NotEmpty(message = "Preenchimento obrigatorio")
	@Email(message = "Email inválido")
	private String email;
	
	public EmailDTO() {
	}
	public EmailDTO(String email) {
		super();
		this.email = email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEmail() {
		return email;
	}
}
