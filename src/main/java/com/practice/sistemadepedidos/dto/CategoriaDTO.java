package com.practice.sistemadepedidos.dto;

import java.io.Serializable;

import com.practice.sistemadepedidos.entities.Categoria;

public class CategoriaDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	private Long id;
	private String nome;
	
	public CategoriaDTO() {
	}
	public CategoriaDTO(Long id, String nome) {
		this.id = id;
		this.nome = nome;
	}
	public CategoriaDTO(Categoria obj) {
		id = obj.getId();
		nome = obj.getNome();
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
}
