package com.practice.sistemadepedidos.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.practice.sistemadepedidos.entities.Categoria;
import com.practice.sistemadepedidos.entities.Cidade;
import com.practice.sistemadepedidos.entities.Estado;
import com.practice.sistemadepedidos.entities.Produto;
import com.practice.sistemadepedidos.repositories.CategoriaRepository;
import com.practice.sistemadepedidos.repositories.CidadeRepository;
import com.practice.sistemadepedidos.repositories.EstadoRepository;
import com.practice.sistemadepedidos.repositories.ProdutoRepository;

@Configuration
@Profile("test")
public class InstanciarBD implements CommandLineRunner{

	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	
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
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade(null, "Uberlandia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);

		categoriaRepository.saveAll(Arrays.asList(cat1,cat2));
		produtoRepository.saveAll(Arrays.asList(pr1,pr2,pr3));
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2,c3));
		
		estadoRepository.saveAll(Arrays.asList(est1,est2));
		cidadeRepository.saveAll(Arrays.asList(c1,c2,c3));

		
	}
}
