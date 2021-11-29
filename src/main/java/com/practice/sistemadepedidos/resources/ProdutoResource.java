package com.practice.sistemadepedidos.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.practice.sistemadepedidos.dto.ProdutoDTO;
import com.practice.sistemadepedidos.dto.ProdutoDTO;
import com.practice.sistemadepedidos.entities.Produto;
import com.practice.sistemadepedidos.entities.Produto;
import com.practice.sistemadepedidos.resources.utils.URL;
import com.practice.sistemadepedidos.services.ProdutoService;


@RestController
@RequestMapping(value = "/produtos")
public class ProdutoResource {

	@Autowired
	private ProdutoService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Produto> finById(@PathVariable Long id) {
		return ResponseEntity.ok().body(service.finById(id));
	}
	
	@GetMapping(value = "/search")
	public ResponseEntity<Page<ProdutoDTO>> findAllPaged(
			@RequestParam(value = "nome", defaultValue = "") String nome, 
			@RequestParam(value = "categorias", defaultValue = "") String categorias, 
			@RequestParam(value = "pages", defaultValue = "0") Integer pages, 
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage, 
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy, 
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {
		List<Long> ids = URL.separarIds(categorias);
		String nomeDecodificado = URL.decodificarStringParam(nome);
		Page<Produto> page =  service.search(nomeDecodificado,ids,pages, linesPerPage, orderBy, direction);	
		Page<ProdutoDTO> pageDTO = page.map(obj -> new ProdutoDTO(obj));
		return ResponseEntity.ok().body(pageDTO);
	}
	@GetMapping
	public ResponseEntity<List<ProdutoDTO>> findAll() {
		List<ProdutoDTO> listDTO =  new ArrayList<>();
		List<Produto> list =  service.findAll();	
		for(Produto c: list) {
			listDTO.add(new ProdutoDTO(c));
		}
		return ResponseEntity.ok().body(listDTO);
	}
}
