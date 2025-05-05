package com.amauri.algafood.api.v2.openapi.controller;

import com.amauri.algafood.api.v2.model.CozinhaModelV2;
import com.amauri.algafood.api.v2.model.input.CozinhaInputV2;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;


public interface CozinhaControllerV2OpenApi {

    PagedModel<CozinhaModelV2> listar(Pageable pageable);


    CozinhaModelV2 buscar( Long grupoId);


    CozinhaModelV2 salvar( CozinhaInputV2 cozinhaInput);


    CozinhaModelV2 atualizar( Long cozinhaId, CozinhaInputV2 cozinhaInput);

    void excluir( Long cozinhaId);

}
