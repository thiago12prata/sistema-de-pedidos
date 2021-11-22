package com.practice.sistemadepedidos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practice.sistemadepedidos.entities.Cidade;

public interface CidadeRepository extends JpaRepository<Cidade, Long> {

}
