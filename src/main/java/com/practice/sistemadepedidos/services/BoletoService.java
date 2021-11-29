package com.practice.sistemadepedidos.services;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import org.springframework.stereotype.Service;

import com.practice.sistemadepedidos.entities.PagamentoComBoleto;
@Service
public class BoletoService {

	public void preencherPagamentoComBoleto(PagamentoComBoleto pagamento, Instant instante) {
		pagamento.setDataVencimento(instante.plus(7, ChronoUnit.DAYS));
	}

}
