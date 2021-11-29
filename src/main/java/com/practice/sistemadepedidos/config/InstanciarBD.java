package com.practice.sistemadepedidos.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.practice.sistemadepedidos.entities.Categoria;
import com.practice.sistemadepedidos.entities.Cidade;
import com.practice.sistemadepedidos.entities.Cliente;
import com.practice.sistemadepedidos.entities.Endereco;
import com.practice.sistemadepedidos.entities.Estado;
import com.practice.sistemadepedidos.entities.ItemPedido;
import com.practice.sistemadepedidos.entities.Pagamento;
import com.practice.sistemadepedidos.entities.PagamentoComBoleto;
import com.practice.sistemadepedidos.entities.PagamentoComCartao;
import com.practice.sistemadepedidos.entities.Pedido;
import com.practice.sistemadepedidos.entities.Produto;
import com.practice.sistemadepedidos.entities.enums.StatusPagamento;
import com.practice.sistemadepedidos.entities.enums.TipoCliente;
import com.practice.sistemadepedidos.repositories.CategoriaRepository;
import com.practice.sistemadepedidos.repositories.CidadeRepository;
import com.practice.sistemadepedidos.repositories.ClienteRepository;
import com.practice.sistemadepedidos.repositories.EnderecoRepository;
import com.practice.sistemadepedidos.repositories.EstadoRepository;
import com.practice.sistemadepedidos.repositories.ItemPedidoRepository;
import com.practice.sistemadepedidos.repositories.PagamentoRepository;
import com.practice.sistemadepedidos.repositories.PedidoRepository;
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
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private PagamentoRepository pagamentoRepository;
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null, "Informatica");
		Categoria cat2 = new Categoria(null, "Escritorio");
		Categoria cat3 = new Categoria(null, "Cama mesa e banho");
		Categoria cat4 = new Categoria(null, "Eletronicos");
		Categoria cat5 = new Categoria(null, "Jardinagem");
		Categoria cat6 = new Categoria(null, "Decoração");
		Categoria cat7 = new Categoria(null, "Perfumaria");
		
		
		Produto pr1 = new Produto(null, "Computador", 2000.00);
		Produto pr2 = new Produto(null, "impressora", 800.00);
		Produto pr3 = new Produto(null, "mouse", 800.00);
		Produto pr4 = new Produto(null, "Mesa de escritorio", 80.00);
		Produto pr5 = new Produto(null, "Toalha", 80.00);
		Produto pr6 = new Produto(null, "Colcha", 80.00);
		Produto pr7 = new Produto(null, "TV true color", 80.00);
		Produto pr8 = new Produto(null, "Roçadeira", 80.00);
		Produto pr9 = new Produto(null, "Abajour", 80.00);
		Produto pr10 = new Produto(null, "Pente", 80.00);
		Produto pr11 = new Produto(null, "Shampoo", 80.00);
	
		
		cat1.getProdutos().addAll(Arrays.asList(pr1,pr2,pr3));
		cat2.getProdutos().addAll(Arrays.asList(pr2,pr4));
		cat3.getProdutos().addAll(Arrays.asList(pr5, pr6));
		cat4.getProdutos().addAll(Arrays.asList(pr1, pr2, pr3, pr7));
		cat5.getProdutos().addAll(Arrays.asList(pr8));
		cat6.getProdutos().addAll(Arrays.asList(pr9, pr10));
		cat7.getProdutos().addAll(Arrays.asList(pr11));
		
		pr1.getCategorias().addAll(Arrays.asList(cat1, cat4));
		pr2.getCategorias().addAll(Arrays.asList(cat1, cat2, cat4));
		pr3.getCategorias().addAll(Arrays.asList(cat1, cat4));
		pr4.getCategorias().addAll(Arrays.asList(cat2));
		pr5.getCategorias().addAll(Arrays.asList(cat3));
		pr6.getCategorias().addAll(Arrays.asList(cat3));
		pr7.getCategorias().addAll(Arrays.asList(cat4));
		pr8.getCategorias().addAll(Arrays.asList(cat5));
		pr9.getCategorias().addAll(Arrays.asList(cat6));
		pr10.getCategorias().addAll(Arrays.asList(cat6));
		pr11.getCategorias().addAll(Arrays.asList(cat7));
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade(null, "Uberlandia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);

		categoriaRepository.saveAll(Arrays.asList(cat1,cat2,cat3,cat4,cat5,cat6,cat7));
		produtoRepository.saveAll(Arrays.asList(pr1,pr2,pr3,pr4,pr5,pr6,pr7,pr8,pr9,pr10,pr11));
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2,c3));
		
		estadoRepository.saveAll(Arrays.asList(est1,est2));
		cidadeRepository.saveAll(Arrays.asList(c1,c2,c3));	
		
		Cliente cli1 = new Cliente(null, "Maria silva", "maria@gmail.com", "3333333333333", TipoCliente.PESSOA_FISICA);
		cli1.getTelefones().addAll(Arrays.asList("95959-9595", "96363-2525"));
		clienteRepository.saveAll(Arrays.asList(cli1));
		Endereco e1 = new Endereco(null, "Rua flores", "300", "apto 303", "jardim", "38220834", cli1, c1);
		e1.setCidade(c1);
		Endereco e2 = new Endereco(null, "Av Matos", "105", "Sala 800", "Centro", "38220834", cli1, c2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1,e2));
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1,e2));
		
		Pedido ped1 = new Pedido(null, Instant.parse("2017-09-30T10:15:30.00Z"), cli1, e1);
		Pedido ped2 = new Pedido(null, Instant.parse("2017-10-10T10:15:30.00Z"), cli1, e2);
		
		Pagamento pag1 = new PagamentoComCartao(null, StatusPagamento.PAGO, ped1, 6);
		ped1.setPagamento(pag1);
		
		Pagamento pag2 = new PagamentoComBoleto(null, StatusPagamento.PENDENTE, ped2, Instant.parse("2017-10-20T10:15:30.00Z"), null);
		ped2.setPagamento(pag2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1,ped2));
		
		pedidoRepository.saveAll(Arrays.asList(ped1,ped2));
		pagamentoRepository.saveAll(Arrays.asList(pag1,pag2));
		
		ItemPedido ip1 = new ItemPedido(ped1, pr1, 0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, pr3, 0.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, pr2, 100.00, 1, 800.00);
		
		ped1.getItens().addAll(Arrays.asList(ip1,ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		pr1.getItens().addAll(Arrays.asList(ip1));
		pr2.getItens().addAll(Arrays.asList(ip3));
		pr3.getItens().addAll(Arrays.asList(ip2));
		
		itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));
	}
}
