package com.amauri.algafood.api.v1.openapi.controller;

import com.amauri.algafood.api.v1.model.FormaPagamentoModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

public interface RestauranteFormaPagamentoControllerOpenApi {

	CollectionModel<FormaPagamentoModel> listar(Long restauranteId);

	ResponseEntity<Void> desvincular(Long restauranteId,Long formaPagamentoId);

	ResponseEntity<Void> vincular(Long restauranteId,Long formaPagamentoId);

}