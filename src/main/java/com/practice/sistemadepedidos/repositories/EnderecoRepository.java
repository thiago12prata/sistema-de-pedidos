package com.practice.sistemadepedidos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practice.sistemadepedidos.entities.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

}
