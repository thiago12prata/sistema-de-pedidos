package com.practice.sistemadepedidos.resources;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.practice.sistemadepedidos.dto.CategoriaDTO;
import com.practice.sistemadepedidos.entities.Categoria;
import com.practice.sistemadepedidos.entities.Pedido;
import com.practice.sistemadepedidos.services.PedidoService;


@RestController
@RequestMapping(value = "/pedidos")
public class PedidoResource {

	@Autowired
	private PedidoService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Pedido> finById(@PathVariable Long id) {
		return ResponseEntity.ok().body(service.finById(id));
	}
	@PostMapping
	public ResponseEntity<Void> insert(@Valid @RequestBody Pedido obj){
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	@GetMapping
	public ResponseEntity<Page<Pedido>> findAllFromCurrentCliente(
			@RequestParam(value = "pages", defaultValue = "0") Integer pages, 
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage, 
			@RequestParam(value = "orderBy", defaultValue = "instante") String orderBy, 
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {
		Page<Pedido> page =  service.findAllFromCurrentCliente(pages, linesPerPage, orderBy, direction);	
		return ResponseEntity.ok().body(page);
	}
}
