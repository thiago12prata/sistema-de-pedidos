package com.practice.sistemadepedidos.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.practice.sistemadepedidos.entities.Categoria;
import com.practice.sistemadepedidos.repositories.CategoriaRepository;

@Configuration
@Profile("test")
public class InstanciarBD implements CommandLineRunner{

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null, "Etronicos");
		Categoria cat2 = new Categoria(null, "Livros");
		Categoria cat3 = new Categoria(null, "Comptadores"); 
		categoriaRepository.saveAll(Arrays.asList(cat1,cat2,cat3));
	}
}
