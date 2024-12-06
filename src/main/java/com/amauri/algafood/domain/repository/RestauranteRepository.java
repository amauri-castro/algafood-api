package com.amauri.algafood.domain.repository;

import com.amauri.algafood.domain.model.Cozinha;
import com.amauri.algafood.domain.model.Restaurante;

import java.util.List;

public interface RestauranteRepository {
    List<Restaurante> listar();
    Restaurante buscar(Long id);
    Restaurante salvar(Restaurante restaurante);
    void excluir(Restaurante restaurante);
}
