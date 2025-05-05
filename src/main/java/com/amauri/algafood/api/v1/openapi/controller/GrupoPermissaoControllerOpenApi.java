package com.amauri.algafood.api.v1.openapi.controller;

import com.amauri.algafood.api.v1.model.PermissaoModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

public interface GrupoPermissaoControllerOpenApi {

	CollectionModel<PermissaoModel> listar(Long grupoId);

	ResponseEntity<Void> desassociar(Long grupoId,Long permissaoId);

	ResponseEntity<Void> associar(Long grupoId,Long permissaoId);

}