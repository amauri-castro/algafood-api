package com.amauri.algafood.api.v1.openapi.controller;

import com.amauri.algafood.api.v1.model.UsuarioModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

public interface RestauranteUsuarioResponsavelControllerOpenApi {

	CollectionModel<UsuarioModel> listar(Long restauranteId);

	ResponseEntity<Void> desvincular(Long restauranteId,Long usuarioId);

	ResponseEntity<Void> vincular(Long restauranteId,Long usuarioId);

}