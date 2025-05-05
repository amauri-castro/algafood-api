package com.amauri.algafood.api.v1.openapi.controller;

import com.amauri.algafood.api.v1.model.ProdutoModel;
import com.amauri.algafood.api.v1.model.input.ProdutoInput;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.hateoas.CollectionModel;

@SecurityRequirement(name = "security_auth")
public interface RestauranteProdutoControllerOpenApi {

	CollectionModel<ProdutoModel> listar(Long restauranteId,Boolean incluirInativos);

	ProdutoModel buscar(Long restauranteId,Long produtoId);

	ProdutoModel salvar(Long restauranteId,ProdutoInput produtoInput);

	ProdutoModel atualizar(Long restauranteId,Long produtoId,ProdutoInput produtoInput);

}