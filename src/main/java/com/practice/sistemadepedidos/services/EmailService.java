package com.practice.sistemadepedidos.services;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;

import com.practice.sistemadepedidos.entities.Cliente;
import com.practice.sistemadepedidos.entities.Pedido;

public interface EmailService {

	void enviarConfirmacaoPedido(Pedido obj);
	void enviarEmail(SimpleMailMessage msg);
	void enviarConfirmacaoPedidoHtmlEmail(Pedido obj);
	void enviarEmailHtml(MimeMessage msg);
	void enviarNovaSenhaEmail(Cliente cliente, String novaSenha);
}
