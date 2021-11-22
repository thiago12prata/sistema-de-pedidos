package com.practice.sistemadepedidos.resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practice.sistemadepedidos.entities.Categoria;


@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

	@GetMapping
	public List<Categoria> listar() {
		Categoria cat1 = new Categoria(1L, "informatica");
		Categoria cat2 = new Categoria(2L, "Escritorio");
		List<Categoria> list = new ArrayList<>();
		list.addAll(Arrays.asList(cat1,cat2));
		return list;
	}
}
