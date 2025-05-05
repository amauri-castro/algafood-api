package com.amauri.algafood.api.v1.openapi.controller;

import com.amauri.algafood.api.v1.model.CozinhaModel;
import com.amauri.algafood.api.v1.model.input.CozinhaInput;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;

public interface CozinhaControllerOpenApi {

	PagedModel<CozinhaModel> listar(Pageable pageable);

	CozinhaModel buscar(Long cozinhaId);

	CozinhaModel salvar(CozinhaInput cozinhaInput);

	CozinhaModel atualizar(Long cozinhaId, CozinhaInput cozinhaInput);

	ResponseEntity<Void> excluir(Long cozinhaId);
	
}
