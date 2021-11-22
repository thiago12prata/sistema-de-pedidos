package com.practice.sistemadepedidos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practice.sistemadepedidos.entities.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}
