package com.practice.sistemadepedidos.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.practice.sistemadepedidos.entities.Cliente;
import com.practice.sistemadepedidos.repositories.ClienteRepository;
import com.practice.sistemadepedidos.servicesexception.DataIntegrityException;
import com.practice.sistemadepedidos.servicesexception.ResourceNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repository;
	
	public Cliente findById(Long id) {
		Optional<Cliente> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException("Não foi encontrato o recurso: " 
			+ Cliente.class.getName()
			+" com a id "
			+ id 
		));
	}
	public Cliente insert(Cliente obj) {
		obj.setId(null);
		return repository.save(obj);
	}
	public Cliente update(Cliente obj) {
		Cliente newObj =findById(obj.getId());
		atualizarDados(newObj, obj);
		return repository.save(newObj);
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
	public List<Cliente> findAll() {
		return repository.findAll();
	}
	public Page<Cliente> findAllPaged(Integer pages, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(pages, linesPerPage, Direction.valueOf(direction),orderBy);
		return repository.findAll(pageRequest);
	}
	
	private void atualizarDados(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());		
	}
}
