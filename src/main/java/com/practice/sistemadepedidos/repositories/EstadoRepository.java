package com.practice.sistemadepedidos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practice.sistemadepedidos.entities.Estado;

public interface EstadoRepository extends JpaRepository<Estado, Long> {

}
