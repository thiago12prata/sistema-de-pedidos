package com.practice.sistemadepedidos.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practice.sistemadepedidos.entities.Categoria;
import com.practice.sistemadepedidos.repositories.CategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repository;
	
	public Categoria finById(Long id) {
		Optional<Categoria> obj = repository.findById(id);
		return obj.orElse(null);
	}
}
