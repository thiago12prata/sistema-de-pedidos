package com.practice.sistemadepedidos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practice.sistemadepedidos.entities.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
