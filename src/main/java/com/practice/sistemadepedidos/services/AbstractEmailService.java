package com.practice.sistemadepedidos.services;

import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.practice.sistemadepedidos.entities.Pedido;

public abstract class AbstractEmailService implements EmailService{

	@Autowired
	private TemplateEngine templateEngine;
	@Autowired
	private JavaMailSender javaMailSender;
	
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
	
	protected String htmlFromTemplatePedido(Pedido obj) {
		Context context = new Context();
		context.setVariable("pedido", obj);
		return templateEngine.process("email/confirmacaoPedido", context);
	}
	
	@Override
	public void enviarConfirmacaoPedidoHtmlEmail(Pedido obj) {
		try {
			MimeMessage mm = prepararMimeMessageFromPedido(obj);
			enviarEmailHtml(mm);
		}catch (MessagingException e) {
			enviarConfirmacaoPedido(obj);
		}
	}

	protected MimeMessage prepararMimeMessageFromPedido(Pedido obj) throws MessagingException {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper mmh = new MimeMessageHelper(mimeMessage, true);
		mmh.setTo(obj.getCliente().getEmail());
		mmh.setFrom(remetente);
		mmh.setSubject("Pedido confirmado! Código: "+ obj.getId());
		mmh.setSentDate(new Date(System.currentTimeMillis()));
		mmh.setText(htmlFromTemplatePedido(obj), true);
		return mimeMessage;
	}
}
