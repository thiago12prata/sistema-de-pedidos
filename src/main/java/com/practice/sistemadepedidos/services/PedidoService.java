package com.practice.sistemadepedidos.services;

import java.time.Instant;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.practice.sistemadepedidos.entities.Cliente;
import com.practice.sistemadepedidos.entities.ItemPedido;
import com.practice.sistemadepedidos.entities.PagamentoComBoleto;
import com.practice.sistemadepedidos.entities.Pedido;
import com.practice.sistemadepedidos.entities.enums.StatusPagamento;
import com.practice.sistemadepedidos.repositories.ItemPedidoRepository;
import com.practice.sistemadepedidos.repositories.PagamentoRepository;
import com.practice.sistemadepedidos.repositories.PedidoRepository;
import com.practice.sistemadepedidos.security.UserSS;
import com.practice.sistemadepedidos.services.exception.AuthorizarionException;
import com.practice.sistemadepedidos.services.exception.ResourceNotFoundException;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private PagamentoRepository pagamentoRepository;
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	@Autowired
	private ProdutoService produtoService;
	@Autowired
	private BoletoService boletoService;
	@Autowired
	private ClienteService clienteService;
	@Autowired
	private EmailService emailService;
	
	public Pedido finById(Long id) {
		Optional<Pedido> obj = pedidoRepository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException("Não foi encontrato o recurso: " 
			+ Pedido.class.getName()
			+" com a id "
			+ id 
		));
	}

	public Pedido insert(Pedido obj) {
		UserSS user = UserService.authenticated();
		if (user==null||user.getId()!=obj.getCliente().getId()) {
			throw new AuthorizarionException("Acesso negado, O Cliente não esta logado ou foi realizado tentativa de inserção de pedido em nome de outro cliente");
		}		
		obj.setId(null);
		obj.setInstante(Instant.now());
		obj.setCliente(clienteService.findById(obj.getCliente().getId()));
		obj.getPagamento().setStatus(StatusPagamento.PENDENTE);
		obj.getPagamento().setPedido(obj);
		
		if (obj.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pagamento = (PagamentoComBoleto) obj.getPagamento();
			boletoService.preencherPagamentoComBoleto(pagamento, obj.getInstante());
		}
		obj = pedidoRepository.save(obj);
		pagamentoRepository.save(obj.getPagamento());
		for(ItemPedido ip: obj.getItens()) {
			ip.setDesconto(0.0);
			ip.setProduto(produtoService.finById(ip.getProduto().getId()));
			ip.setPreco(ip.getProduto().getPreco());
			ip.setPedido(obj);
		}
		itemPedidoRepository.saveAll(obj.getItens());
		emailService.enviarConfirmacaoPedidoHtmlEmail(obj);
		return obj;
	}
	
	public Page<Pedido> findAllFromCurrentCliente(Integer pages, Integer linesPerPage, String orderBy, String direction){
		UserSS user = UserService.authenticated();
		if (user==null) {
			throw new AuthorizarionException("Acesso negado, não há usuario logado");
		}	
		PageRequest pageRequest = PageRequest.of(pages, linesPerPage, Direction.valueOf(direction),orderBy);
		Cliente cliente = clienteService.findById(user.getId());
		return pedidoRepository.findByCliente(cliente, pageRequest);
	}
}
