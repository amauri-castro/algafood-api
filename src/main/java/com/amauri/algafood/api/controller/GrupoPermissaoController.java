package com.amauri.algafood.api.controller;

import com.amauri.algafood.api.assembler.PermissaoModelAssembler;
import com.amauri.algafood.api.model.PermissaoModel;
import com.amauri.algafood.domain.model.Grupo;
import com.amauri.algafood.domain.service.CadastroGrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/grupos/{grupoId}/permissoes")
public class GrupoPermissaoController {


    @Autowired
    private CadastroGrupoService cadastroGrupoService;

    @Autowired
    private PermissaoModelAssembler permissaoModelAssembler;

    @GetMapping
    public List<PermissaoModel> listar(@PathVariable Long grupoId) {
        Grupo grupo = cadastroGrupoService.buscarOuFalhar(grupoId);

        return permissaoModelAssembler.toCollectionModel(grupo.getPermissoes());
    }

    @DeleteMapping("/{permissaoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void desvincular(@PathVariable Long grupoId, @PathVariable Long permissaoId) {
        cadastroGrupoService.desvincularPermissao(grupoId, permissaoId);
    }

    @PutMapping("/{permissaoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void vincular(@PathVariable Long grupoId, @PathVariable Long permissaoId) {
        cadastroGrupoService.vincularPermissao(grupoId, permissaoId);
    }


}
