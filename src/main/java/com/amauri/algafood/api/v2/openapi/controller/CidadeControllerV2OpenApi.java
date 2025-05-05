package com.amauri.algafood.api.v2.openapi.controller;

import com.amauri.algafood.api.v2.model.CidadeModelV2;
import com.amauri.algafood.api.v2.model.input.CidadeInputV2;
import org.springframework.hateoas.CollectionModel;

public interface CidadeControllerV2OpenApi {

    CollectionModel<CidadeModelV2> listar();


    CidadeModelV2 buscar( Long cidadeId);


    CidadeModelV2 salvar( CidadeInputV2 cidadeInput);


    CidadeModelV2 atualizar( Long cidadeId, CidadeInputV2 cidadeInput);



    void excluir( Long cidadeId);

}
