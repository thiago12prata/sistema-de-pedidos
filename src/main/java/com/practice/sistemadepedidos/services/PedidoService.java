package com.practice.sistemadepedidos.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practice.sistemadepedidos.entities.Pedido;
import com.practice.sistemadepedidos.repositories.PedidoRepository;
import com.practice.sistemadepedidos.servicesexception.ResourceNotFoundException;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository repository;
	
	public Pedido finById(Long id) {
		Optional<Pedido> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException("Não foi encontrato o recurso: " 
			+ Pedido.class.getName()
			+" com a id "
			+ id 
		));
	}
}
