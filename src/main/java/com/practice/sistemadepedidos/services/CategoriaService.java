package com.practice.sistemadepedidos.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.practice.sistemadepedidos.entities.Categoria;
import com.practice.sistemadepedidos.repositories.CategoriaRepository;
import com.practice.sistemadepedidos.servicesexception.DataIntegrityException;
import com.practice.sistemadepedidos.servicesexception.ResourceNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repository;
	
	public Categoria findById(Long id) {
		Optional<Categoria> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException("Não foi encontrato o recurso: " 
			+ Categoria.class.getName()
			+" com a id "
			+ id 
		));
	}
	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return repository.save(obj);
	}
	public Categoria update(Categoria obj) {
		findById(obj.getId());
		return repository.save(obj);
	}
	public void delete(Long id) {
		repository.findById(id);
		try {
			repository.deleteById(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir uma categoria que possui produtos");
		}
	}
	public List<Categoria> findAll() {
		return repository.findAll();
	}
}
