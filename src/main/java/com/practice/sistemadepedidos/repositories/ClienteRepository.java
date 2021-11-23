package com.practice.sistemadepedidos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practice.sistemadepedidos.entities.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
