package com.practice.sistemadepedidos.resources;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.practice.sistemadepedidos.dto.ClienteDTO;
import com.practice.sistemadepedidos.entities.Cliente;
import com.practice.sistemadepedidos.services.ClienteService;


@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

	@Autowired
	private ClienteService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Cliente> findById(@PathVariable Long id) {
		return ResponseEntity.ok().body(service.findById(id));
	}
	@PostMapping
	public ResponseEntity<Void> insert(@Valid @RequestBody ClienteDTO objDTO){
		Cliente obj = ClienteDTO.toEntity(objDTO);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	@PutMapping(value = "/{id}")
	public ResponseEntity<Void> update(@Valid @RequestBody ClienteDTO objDTO, @PathVariable Long id){
		Cliente obj = ClienteDTO.toEntity(objDTO);
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Cliente> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	@GetMapping
	public ResponseEntity<List<ClienteDTO>> findAll() {
		List<ClienteDTO> listDTO =  new ArrayList<>();
		List<Cliente> list =  service.findAll();	
		for(Cliente c: list) {
			listDTO.add(new ClienteDTO(c));
		}
		return ResponseEntity.ok().body(listDTO);
	}
	@GetMapping(value = "/paged")
	public ResponseEntity<Page<ClienteDTO>> findAllPaged(
			@RequestParam(value = "pages", defaultValue = "0") Integer pages, 
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage, 
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy, 
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {
		Page<Cliente> page =  service.findAllPaged(pages, linesPerPage, orderBy, direction);	
		Page<ClienteDTO> pageDTO = page.map(c -> new ClienteDTO(c));
		return ResponseEntity.ok().body(pageDTO);
	}
}
