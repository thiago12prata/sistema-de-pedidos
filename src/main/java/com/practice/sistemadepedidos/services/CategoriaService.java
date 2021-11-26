package com.practice.sistemadepedidos.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
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
		Categoria newObj =findById(obj.getId());
		atualizarDados(newObj, obj);
		return repository.save(newObj);
	}
	private void atualizarDados(Categoria newObj, Categoria obj) {
		newObj.setNome(obj.getNome());
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
	public Page<Categoria> findAllPaged(Integer pages, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(pages, linesPerPage, Direction.valueOf(direction),orderBy);
		return repository.findAll(pageRequest);
	}
}
