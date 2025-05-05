package com.amauri.algafood.api.v1.openapi.controller;

import com.amauri.algafood.api.v1.model.EstadoModel;
import com.amauri.algafood.api.v1.model.input.EstadoInput;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

@SecurityRequirement(name = "security_auth")
public interface EstadoControllerOpenApi {

	CollectionModel<EstadoModel> listar();

	EstadoModel buscar(Long estadoId);

	EstadoModel salvar(EstadoInput estadoInput);

	EstadoModel atualizar(Long estadoId,EstadoInput estadoInput);

	ResponseEntity<Void> excluir(Long estadoId);

}