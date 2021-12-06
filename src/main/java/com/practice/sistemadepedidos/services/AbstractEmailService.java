package com.practice.sistemadepedidos.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import com.practice.sistemadepedidos.entities.Pedido;

public abstract class AbstractEmailService implements EmailService{

	@Value("${default.sender}")
	private String remetente;
	
	@Override
	public void enviarConfirmacaoPedido(Pedido obj) {
		SimpleMailMessage sm = prepararSimpleMailMessageFromPedido(obj);
		enviarEmail(sm);
	}

	protected SimpleMailMessage prepararSimpleMailMessageFromPedido(Pedido obj) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(obj.getCliente().getEmail());
		sm.setFrom(remetente);
		sm.setSubject("Pedido Confirmado! Numero do pedido é: " +obj.getId());
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText(obj.toString());
		return sm;
	}
}
