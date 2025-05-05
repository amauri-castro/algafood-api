package com.amauri.algafood.api.v1.openapi.controller;

import com.amauri.algafood.api.v1.model.CozinhaModel;
import com.amauri.algafood.api.v1.model.input.CozinhaInput;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;

@SecurityRequirement(name = "security_auth")
public interface CozinhaControllerOpenApi {

	PagedModel<CozinhaModel> listar(Pageable pageable);

	CozinhaModel buscar(Long cozinhaId);

	CozinhaModel salvar(CozinhaInput cozinhaInput);

	CozinhaModel atualizar(Long cozinhaId, CozinhaInput cozinhaInput);

	ResponseEntity<Void> excluir(Long cozinhaId);
	
}
