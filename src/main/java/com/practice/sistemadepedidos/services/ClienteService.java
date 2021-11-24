package com.practice.sistemadepedidos.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practice.sistemadepedidos.entities.Cliente;
import com.practice.sistemadepedidos.repositories.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repository;
	
	public Cliente finById(Long id) {
		Optional<Cliente> obj = repository.findById(id);
		return obj.orElse(null);
	}
}
