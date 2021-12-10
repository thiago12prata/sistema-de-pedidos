package com.practice.sistemadepedidos.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.practice.sistemadepedidos.entities.Cliente;
import com.practice.sistemadepedidos.entities.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
	
	@Transactional(readOnly=true)
	Page<Pedido> findByCliente(Cliente cliente, Pageable pageRequest);
}
