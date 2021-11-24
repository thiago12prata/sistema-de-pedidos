package com.practice.sistemadepedidos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practice.sistemadepedidos.entities.Pagamento;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {

}
