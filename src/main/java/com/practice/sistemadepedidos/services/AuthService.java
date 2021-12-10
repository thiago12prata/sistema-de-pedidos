package com.practice.sistemadepedidos.services;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.practice.sistemadepedidos.entities.Cliente;
import com.practice.sistemadepedidos.repositories.ClienteRepository;
import com.practice.sistemadepedidos.services.exception.ResourceNotFoundException;

@Service
public class AuthService {

	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private BCryptPasswordEncoder bCrypt;
	@Autowired
	private EmailService emailService;
	
	private Random rand = new Random();
	
	public void enviarNovaSenha(String email) {
	
		Cliente cliente = clienteRepository.findByEmail(email);
		
		if (cliente==null) {
			throw new ResourceNotFoundException("Email não encontrado");
		}
		
		String novaSenha = novaSenha();
		cliente.setSenha(bCrypt.encode(novaSenha));
		clienteRepository.save(cliente);
		emailService.enviarNovaSenhaEmail(cliente, novaSenha);
	}

	private String novaSenha() {
		char[] vetor = new char[10];
		for(int i = 0; i<10; i++) {
			vetor[i] = caracterAleatorio();
		}
		return new String(vetor);
	}

	private char caracterAleatorio() {
		int opt = rand.nextInt(3);
		if (opt == 0){//gera um digito
			return (char) (rand.nextInt(10) + 48);
		}
		else if (opt == 1) {//gera latra maiuscula
			return (char) (rand.nextInt(26) + 65); 
		}
		else {// gera letra minuscula
			return (char) (rand.nextInt(26) + 97);
		}
	}
}