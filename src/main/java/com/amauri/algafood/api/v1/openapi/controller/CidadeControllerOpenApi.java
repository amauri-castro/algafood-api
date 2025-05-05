package com.amauri.algafood.api.v1.openapi.controller;

import com.amauri.algafood.api.v1.model.CidadeModel;
import com.amauri.algafood.api.v1.model.input.CidadeInput;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Cidades", description = "Gerencia as cidades")
public interface CidadeControllerOpenApi {

	@Operation(summary = "Lista as cidades", description = "Lista todas as cidades")
	CollectionModel<CidadeModel> listar();

	@Operation(summary = "Busca uma cidade por ID")
	CidadeModel buscar(Long cidadeId);

	@Operation(summary = "Cadastra uma nova cidade", description = "Cadastro de uma cidade," +
			" necessita de um estado e um nome valido")
	CidadeModel salvar(CidadeInput cidadeInput);

	@Operation(summary = "Atualiza uma cidade por ID")
	CidadeModel atualizar(Long cidadeId, CidadeInput cidadeInput);

	@Operation(summary = "Exclui uma cidade por ID")
	ResponseEntity<Void> excluir(Long cidadeId);
	
}
