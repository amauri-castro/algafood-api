package com.amauri.algafood.api.controller;

import com.amauri.algafood.api.AlgaLinks;
import com.amauri.algafood.api.assembler.PermissaoModelAssembler;
import com.amauri.algafood.api.model.PermissaoModel;
import com.amauri.algafood.api.openapi.controller.GrupoPermissaoControllerOpenApi;
import com.amauri.algafood.domain.model.Grupo;
import com.amauri.algafood.domain.service.CadastroGrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/grupos/{grupoId}/permissoes", produces = MediaType.APPLICATION_JSON_VALUE)
public class GrupoPermissaoController implements GrupoPermissaoControllerOpenApi {


    @Autowired
    private CadastroGrupoService cadastroGrupoService;

    @Autowired
    private PermissaoModelAssembler permissaoModelAssembler;

    @Autowired
    private AlgaLinks algaLinks;

    @GetMapping
    public CollectionModel<PermissaoModel> listar(@PathVariable Long grupoId) {
        Grupo grupo = cadastroGrupoService.buscarOuFalhar(grupoId);

        CollectionModel<PermissaoModel> permissoesModel = permissaoModelAssembler
                .toCollectionModel(grupo.getPermissoes())
                .removeLinks()
                .add(algaLinks.linkToGrupoPermissoes(grupoId))
                .add(algaLinks.linkToGrupoPermissaoAssociacao(grupoId, "associar"));

        permissoesModel.getContent().forEach(permissaoModel -> {
            permissaoModel.add(algaLinks.linkToGrupoPermissaoDesassociacao(grupoId,
                    permissaoModel.getId(), "desassociar"));
        });

        return permissoesModel;
    }

    @DeleteMapping("/{permissaoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> desassociar(@PathVariable Long grupoId, @PathVariable Long permissaoId) {
        cadastroGrupoService.desvincularPermissao(grupoId, permissaoId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{permissaoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> associar(@PathVariable Long grupoId, @PathVariable Long permissaoId) {
        cadastroGrupoService.vincularPermissao(grupoId, permissaoId);
        return ResponseEntity.noContent().build();
    }


}
