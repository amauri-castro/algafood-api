package com.amauri.algafood.api.v1.controller;

import com.amauri.algafood.api.v1.assembler.PermissaoModelAssembler;
import com.amauri.algafood.api.v1.model.PermissaoModel;
import com.amauri.algafood.api.v1.openapi.controller.PermissaoControllerOpenApi;
import com.amauri.algafood.core.security.CheckSecurity;
import com.amauri.algafood.domain.model.Permissao;
import com.amauri.algafood.domain.repository.PermissaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/v1/permissoes", produces = MediaType.APPLICATION_JSON_VALUE)
public class PermissaoController implements PermissaoControllerOpenApi {

    @Autowired
    private PermissaoRepository permissaoRepository;

    @Autowired
    private PermissaoModelAssembler permissaoModelAssembler;

    @CheckSecurity.UsuariosGruposPermissoes.PodeConsultar
    @Override
    @GetMapping
    public CollectionModel<PermissaoModel> listar() {
        List<Permissao> permissoes = permissaoRepository.findAll();

        return permissaoModelAssembler.toCollectionModel(permissoes);
    }
}
