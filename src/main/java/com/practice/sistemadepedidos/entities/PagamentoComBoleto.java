package com.practice.sistemadepedidos.entities;

import java.time.Instant;
import java.util.Objects;

import javax.persistence.Entity;

import com.practice.sistemadepedidos.entities.enums.StatusPagamento;

@Entity
public class PagamentoComBoleto extends Pagamento{
	private static final long serialVersionUID = 1L;
	
	private Instant dataVencimento;
	private Instant dataPagamento;
	
	public PagamentoComBoleto() {
	}
	public PagamentoComBoleto(Long id, StatusPagamento status, Pedido pedido, Instant dataVencimento, Instant dataPagamento) {
		super(id, status, pedido);
		this.dataVencimento = dataVencimento;
		this.dataPagamento = dataPagamento;
	}
	
	public Instant getDataVencimento() {
		return dataVencimento;
	}
	public void setDataVencimento(Instant dataVencimento) {
		this.dataVencimento = dataVencimento;
	}
	public Instant getDataPagamento() {
		return dataPagamento;
	}
	public void setDataPagamento(Instant dataPagamento) {
		this.dataPagamento = dataPagamento;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(dataPagamento, dataVencimento);
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		PagamentoComBoleto other = (PagamentoComBoleto) obj;
		return Objects.equals(dataPagamento, other.dataPagamento)
				&& Objects.equals(dataVencimento, other.dataVencimento);
	}
}
