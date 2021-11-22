package com.practice.sistemadepedidos.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practice.sistemadepedidos.entities.Produto;
import com.practice.sistemadepedidos.repositories.ProdutoRepository;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository repository;
	
	public Produto finById(Long id) {
		Optional<Produto> obj = repository.findById(id);
		return obj.orElse(null);
	}
}
