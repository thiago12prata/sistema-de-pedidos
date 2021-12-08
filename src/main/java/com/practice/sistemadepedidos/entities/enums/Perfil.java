package com.practice.sistemadepedidos.entities.enums;

public enum Perfil {

	ADMIN(1, "ROLE_ADMIN"),
	CLIENTE(2, "ROLE_CLIENTE");
	
	private Integer cod;
	private String descricao;
	
	private Perfil(int cod, String descricao) {
		this.cod=cod;
		this.descricao=descricao;
	}

	public Integer getCod() {
		return cod;
	}
	public String getDescricao() {
		return descricao;
	}
	
	public static Perfil toEnum(Integer cod) {
		if (cod==null) {
			return null;
		} 
		for(Perfil sp: Perfil.values()) {
			if (cod.equals(sp.getCod())) {
				return sp;
			}
		}
		throw new IllegalArgumentException("Não existe o Tipo de cliente com o codigo passado: "+ cod);
	}
}
