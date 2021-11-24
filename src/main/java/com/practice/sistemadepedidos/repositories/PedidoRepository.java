package com.practice.sistemadepedidos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practice.sistemadepedidos.entities.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

}
