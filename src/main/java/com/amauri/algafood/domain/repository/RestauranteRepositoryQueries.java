package com.amauri.algafood.domain.repository;

import com.amauri.algafood.domain.model.Restaurante;

import java.math.BigDecimal;
import java.util.List;

public interface RestauranteRepositoryQueries {
    List<Restaurante> consultar(String nome, BigDecimal taxaFreteInicial,
                                BigDecimal taxaFreteFinal);
}
