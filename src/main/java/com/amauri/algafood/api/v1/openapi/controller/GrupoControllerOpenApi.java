package com.amauri.algafood.api.v1.openapi.controller;

import com.amauri.algafood.api.v1.model.GrupoModel;
import com.amauri.algafood.api.v1.model.input.GrupoInput;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Grupos", description = "Gerencia os Grupos")
public interface GrupoControllerOpenApi {

	@Operation(summary = "Lista os grupos")
	CollectionModel<GrupoModel> listar();

	@Operation(summary = "Busca um grupo por ID")
	GrupoModel buscar(@Parameter(description = "ID de um grupo", example = "1", required = true) Long grupoId);

	@Operation(summary = "Cadastra um no grupo", description = "Cadastro de um grupo")
	GrupoModel salvar(@RequestBody(description = "Representação de um novo grupo", required = true) GrupoInput grupoInput);

	@Operation(summary = "Atualiza um grupo por ID")
	GrupoModel atualizar(@Parameter(description = "ID de um grupo", example = "1", required = true) Long grupoId,
						 @RequestBody(description = "Representação de um grupo existente", required = true)GrupoInput grupoInput);

	@Operation(summary = "Exclui um grupo por ID")
	ResponseEntity<Void> excluir(@Parameter(description = "ID de um grupo", example = "1", required = true) Long grupoId);
	
}
