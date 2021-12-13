package com.practice.sistemadepedidos.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.practice.sistemadepedidos.entities.Cidade;

public interface CidadeRepository extends JpaRepository<Cidade, Long> {

	@Transactional
	@Query("SELECT obj FROM Cidade obj WHERE obj.estado.id = :estadoId ORDER BY obj.nome")
	public List<Cidade> findAllByEstado(@Param("estadoId") Long estado_id);
}
