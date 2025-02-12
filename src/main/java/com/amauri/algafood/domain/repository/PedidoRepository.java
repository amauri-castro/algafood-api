package com.amauri.algafood.domain.repository;

import com.amauri.algafood.domain.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    @Query("FROM Pedido p JOIN FETCH p.cliente JOIN FETCH p.restaurante r JOIN FETCH r.cozinha")
    List<Pedido> findAll();
}
