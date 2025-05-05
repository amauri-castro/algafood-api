package com.amauri.algafood.api.v1.openapi.controller;

import com.amauri.algafood.api.v1.model.GrupoModel;
import com.amauri.algafood.api.v1.model.input.GrupoInput;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

@SecurityRequirement(name = "security_auth")
public interface GrupoControllerOpenApi {

	CollectionModel<GrupoModel> listar();

	GrupoModel buscar(Long grupoId);

	GrupoModel salvar(GrupoInput grupoInput);

	GrupoModel atualizar(Long grupoId,GrupoInput grupoInput);

	ResponseEntity<Void> excluir(Long grupoId);
	
}
