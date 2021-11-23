package com.practice.sistemadepedidos.entities.enums;

public enum TipoCliente {

	PESSOA_FISICA(1, "Pessoa Física"),
	PESSOA_JURIDICA(2, "Pessoa Jurídica");
	
	private Integer cod;
	private String descricao;
	
	private TipoCliente(int cod, String descricao) {
		this.cod=cod;
		this.descricao=descricao;
	}

	public Integer getCod() {
		return cod;
	}
	public String getDescricao() {
		return descricao;
	}
	
	public static TipoCliente toEnum(Integer cod) {
		if (cod==null) {
			return null;
		} 
		for(TipoCliente tp: TipoCliente.values()) {
			if (cod.equals(tp.getCod())) {
				return tp;
			}
		}
		throw new IllegalArgumentException("Não existe o Tipo de cliente com o codigo passado: "+ cod);
	}
}
