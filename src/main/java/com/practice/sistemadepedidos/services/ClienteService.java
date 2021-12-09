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
import com.practice.sistemadepedidos.entities.enums.Perfil;
import com.practice.sistemadepedidos.repositories.ClienteRepository;
import com.practice.sistemadepedidos.repositories.EnderecoRepository;
import com.practice.sistemadepedidos.security.UserSS;
import com.practice.sistemadepedidos.services.exception.AuthorizarionException;
import com.practice.sistemadepedidos.services.exception.DataIntegrityException;
import com.practice.sistemadepedidos.services.exception.ResourceNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	public Cliente findById(Long id) {
		UserSS user = UserService.authenticated();
		if (user==null || !user.hasRole(Perfil.ADMIN) && !id.equals(user.getId())) {
			throw new AuthorizarionException("Acesso negado");
		}
		
		Optional<Cliente> obj = clienteRepository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException("Não foi encontrato o recurso: " 
			+ Cliente.class.getName()
			+" com a id "
			+ id 
		));
	}
	public Cliente insert(Cliente obj) {
		obj.setId(null);
		obj = clienteRepository.save(obj);
		enderecoRepository.saveAll(obj.getEnderecos());
		return obj;
	}
	public Cliente update(Cliente obj) {
		Cliente newObj =findById(obj.getId());
		atualizarDados(newObj, obj);
		return clienteRepository.save(newObj);
	}
	public void delete(Long id) {
		clienteRepository.findById(id);
		try {
			clienteRepository.deleteById(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir um cliente que possui pedidos");
		}
	}
	public List<Cliente> findAll() {
		return clienteRepository.findAll();
	}
	public Page<Cliente> findAllPaged(Integer pages, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(pages, linesPerPage, Direction.valueOf(direction),orderBy);
		return clienteRepository.findAll(pageRequest);
	}
	
	private void atualizarDados(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());		
	}
}
