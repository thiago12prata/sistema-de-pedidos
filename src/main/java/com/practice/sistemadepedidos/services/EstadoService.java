package com.practice.sistemadepedidos.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practice.sistemadepedidos.entities.Estado;
import com.practice.sistemadepedidos.repositories.EstadoRepository;

@Service
public class EstadoService {

	@Autowired
	private EstadoRepository repository;
	
	public List<Estado> findAll() {
		return repository.findAllByOrderByNome();
	}
}
