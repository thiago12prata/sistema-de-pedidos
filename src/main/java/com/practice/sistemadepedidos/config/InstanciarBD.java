package com.practice.sistemadepedidos.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.practice.sistemadepedidos.entities.Categoria;
import com.practice.sistemadepedidos.entities.Produto;
import com.practice.sistemadepedidos.repositories.CategoriaRepository;
import com.practice.sistemadepedidos.repositories.ProdutoRepository;

@Configuration
@Profile("test")
public class InstanciarBD implements CommandLineRunner{

	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null, "Informatica");
		Categoria cat2 = new Categoria(null, "Escritorio");
		
		Produto pr1 = new Produto(null, "Computador", 2000.00);
		Produto pr2 = new Produto(null, "impressora", 800.00);
		Produto pr3 = new Produto(null, "mouse", 80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(pr1,pr2,pr3));
		cat2.getProdutos().addAll(Arrays.asList(pr2));
		
		pr1.getCategorias().add(cat1);
		pr2.getCategorias().addAll(Arrays.asList(cat1,cat2));
		pr3.getCategorias().add(cat1);
		
		categoriaRepository.saveAll(Arrays.asList(cat1,cat2));
		produtoRepository.saveAll(Arrays.asList(pr1,pr2,pr3));
	}
}
