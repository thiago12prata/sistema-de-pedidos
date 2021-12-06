package com.practice.sistemadepedidos.services;

import org.springframework.mail.SimpleMailMessage;

import com.practice.sistemadepedidos.entities.Pedido;

public interface EmailService {

	void enviarConfirmacaoPedido(Pedido obj);
	void enviarEmail(SimpleMailMessage msg);
}
