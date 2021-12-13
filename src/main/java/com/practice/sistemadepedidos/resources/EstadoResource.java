package com.practice.sistemadepedidos.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practice.sistemadepedidos.dto.CidadeDTO;
import com.practice.sistemadepedidos.dto.EstadoDTO;
import com.practice.sistemadepedidos.entities.Cidade;
import com.practice.sistemadepedidos.entities.Estado;
import com.practice.sistemadepedidos.services.CidadeService;
import com.practice.sistemadepedidos.services.EstadoService;

@RestController
@RequestMapping(value = "/estados")
public class EstadoResource {

	@Autowired
	private EstadoService estadoService;
	@Autowired
	private CidadeService cidadeService;
	
	@GetMapping
	public ResponseEntity<List<EstadoDTO>> findAll(){
		List<Estado> list =  estadoService.findAll();	
		List<EstadoDTO> listDTO = list.stream().map(x -> new EstadoDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}
	
	@GetMapping(value = "/{estado_id}/cidades")
	public ResponseEntity<List<CidadeDTO>> findAllByEstado(@PathVariable Long estado_id){
		List<Cidade> list =  cidadeService.findAllCidadesByEstado(estado_id);	
		List<CidadeDTO> listDTO = list.stream().map(x -> new CidadeDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}
}
