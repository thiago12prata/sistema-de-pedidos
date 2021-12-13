package com.practice.sistemadepedidos.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practice.sistemadepedidos.entities.Cidade;
import com.practice.sistemadepedidos.repositories.CidadeRepository;

@Service
public class CidadeService {

	@Autowired
	private  CidadeRepository repository;	
	
	public List<Cidade> findAllCidadesByEstado(Long idEstado){
		return repository.findAllByEstado(idEstado);	
	}
}
