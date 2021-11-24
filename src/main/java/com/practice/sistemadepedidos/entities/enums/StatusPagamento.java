package com.practice.sistemadepedidos.entities.enums;

public enum StatusPagamento {

	PENDENTE(1, "Pendente"),
	PAGO(2, "Pago"),
	CANCELADO(3, "Cancelado");
	
	private Integer cod;
	private String descricao;
	
	private StatusPagamento(int cod, String descricao) {
		this.cod=cod;
		this.descricao=descricao;
	}

	public Integer getCod() {
		return cod;
	}
	public String getDescricao() {
		return descricao;
	}
	
	public static StatusPagamento toEnum(Integer cod) {
		if (cod==null) {
			return null;
		} 
		for(StatusPagamento sp: StatusPagamento.values()) {
			if (cod.equals(sp.getCod())) {
				return sp;
			}
		}
		throw new IllegalArgumentException("Não existe o Tipo de cliente com o codigo passado: "+ cod);
	}
}
