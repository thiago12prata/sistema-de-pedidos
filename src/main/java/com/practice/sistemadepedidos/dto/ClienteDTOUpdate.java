package com.practice.sistemadepedidos.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.practice.sistemadepedidos.entities.Cliente;
import com.practice.sistemadepedidos.services.validation.ClienteUpdate;

@ClienteUpdate
public class ClienteDTOUpdate implements Serializable{
	private static final long serialVersionUID = 1L;

	
	private Long id;
	@NotEmpty(message = "Preenchimento obrigatorio")
	@Length(min=5, max=120, message="O tamanho deve ser entre 5 e 120 caracteres")
	private String nome;
	@NotEmpty(message = "Preenchimento obrigatorio")
	@Email(message = "Email inválido")
	private String email;
	
	public ClienteDTOUpdate() {
	}	
	public ClienteDTOUpdate(Long id, String nome, String email) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
	}
	public ClienteDTOUpdate(Cliente obj) {
		id= obj.getId();
		nome= obj.getNome();
		email=obj.getEmail();
	}	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public static Cliente toEntity(ClienteDTOUpdate objDTO) {
		return new Cliente(objDTO.getId(), objDTO.getNome(), objDTO.getEmail(), null, null, null);
	}
}
