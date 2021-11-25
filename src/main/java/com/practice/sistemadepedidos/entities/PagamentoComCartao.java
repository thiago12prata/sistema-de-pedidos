package com.practice.sistemadepedidos.entities;

import javax.persistence.Entity;

import com.practice.sistemadepedidos.entities.enums.StatusPagamento;

@Entity
public class PagamentoComCartao extends Pagamento{
	private static final long serialVersionUID = 1L;
	
	private Integer qtdParcelas;
	
	public PagamentoComCartao() {
	}
	public PagamentoComCartao(Long id, StatusPagamento status, Pedido pedido, Integer qtdParcelas) {
		super(id, status, pedido);
		this.qtdParcelas = qtdParcelas;
	}
	
	public Integer getQtdParcelas() {
		return qtdParcelas;
	}
	public void setQtdParcelas(Integer qtdParcelas) {
		this.qtdParcelas = qtdParcelas;
	}
}
