package com.amauri.algafood.api.v1.openapi.controller;

import com.amauri.algafood.api.v1.model.CidadeModel;
import com.amauri.algafood.api.v1.model.input.CidadeInput;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

public interface CidadeControllerOpenApi {

	CollectionModel<CidadeModel> listar();

	CidadeModel buscar(Long cidadeId);

	CidadeModel salvar(CidadeInput cidadeInput);

	CidadeModel atualizar(Long cidadeId, CidadeInput cidadeInput);

	ResponseEntity<Void> excluir(Long cidadeId);
	
}
