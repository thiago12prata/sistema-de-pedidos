package com.practice.sistemadepedidos.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practice.sistemadepedidos.entities.Estado;

public interface EstadoRepository extends JpaRepository<Estado, Long> {

	public List<Estado> findAllByOrderByNome();
}
