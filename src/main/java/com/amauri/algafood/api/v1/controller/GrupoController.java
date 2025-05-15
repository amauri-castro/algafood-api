package com.amauri.algafood.api.v1.controller;

import com.amauri.algafood.api.v1.assembler.GrupoInputDisassembler;
import com.amauri.algafood.api.v1.assembler.GrupoModelAssembler;
import com.amauri.algafood.api.v1.model.GrupoModel;
import com.amauri.algafood.api.v1.model.input.GrupoInput;
import com.amauri.algafood.api.v1.openapi.controller.GrupoControllerOpenApi;
import com.amauri.algafood.core.security.CheckSecurity;
import com.amauri.algafood.domain.model.Grupo;
import com.amauri.algafood.domain.repository.GrupoRepository;
import com.amauri.algafood.domain.service.CadastroGrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/v1/grupos", produces = MediaType.APPLICATION_JSON_VALUE)
public class GrupoController implements GrupoControllerOpenApi {

    @Autowired
    private GrupoRepository grupoRepository;

    @Autowired
    private CadastroGrupoService cadastroGrupoService;

    @Autowired
    private GrupoInputDisassembler grupoInputDisassembler;
    @Autowired
    private GrupoModelAssembler grupoModelAssembler;


    @CheckSecurity.UsuariosGruposPermissoes.PodeConsultar
    @Override
    @GetMapping
    public CollectionModel<GrupoModel> listar() {
        return grupoModelAssembler.toCollectionModel(grupoRepository.findAll());
    }

    @CheckSecurity.UsuariosGruposPermissoes.PodeConsultar
    @Override
    @GetMapping("/{grupoId}")
    public GrupoModel buscar(@PathVariable Long grupoId) {
        Grupo grupo = cadastroGrupoService.buscarOuFalhar(grupoId);
        return grupoModelAssembler.toModel(grupo);
    }

    @CheckSecurity.UsuariosGruposPermissoes.PodeEditar
    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GrupoModel salvar(@RequestBody @Valid GrupoInput grupoInput) {
        Grupo grupo = grupoInputDisassembler.toDomainObject(grupoInput);
        return grupoModelAssembler.toModel(cadastroGrupoService.salvar(grupo));
    }

    @CheckSecurity.UsuariosGruposPermissoes.PodeEditar
    @Override
    @PutMapping("/{grupoId}")
    public GrupoModel atualizar(@PathVariable Long grupoId, @RequestBody @Valid GrupoInput grupoInput) {
        Grupo grupoAtual = cadastroGrupoService.buscarOuFalhar(grupoId);
        grupoInputDisassembler.copyToDomainObject(grupoInput, grupoAtual);
        return grupoModelAssembler.toModel(cadastroGrupoService.salvar(grupoAtual));
    }

    @CheckSecurity.UsuariosGruposPermissoes.PodeEditar
    @Override
    @DeleteMapping("/{grupoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> excluir(@PathVariable Long grupoId) {
        cadastroGrupoService.excluir(grupoId);
        return ResponseEntity.noContent().build();
    }
}
