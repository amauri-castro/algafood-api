package com.amauri.algafood.api.v1.openapi.controller;

import com.amauri.algafood.api.v1.model.PermissaoModel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.CollectionModel;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Permissões", description = "Gerencia as permissões")
public interface PermissaoControllerOpenApi {

	@Operation(summary = "Lista as permissões")
	CollectionModel<PermissaoModel> listar();
	
}
