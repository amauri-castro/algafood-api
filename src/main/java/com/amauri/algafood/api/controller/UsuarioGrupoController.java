package com.amauri.algafood.api.controller;

import com.amauri.algafood.api.assembler.GrupoModelAssembler;
import com.amauri.algafood.api.model.GrupoModel;
import com.amauri.algafood.api.openapi.controller.UsuarioGrupoControllerOpenApi;
import com.amauri.algafood.domain.model.Usuario;
import com.amauri.algafood.domain.service.CadastroUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/usuarios/{usuarioId}/grupos", produces = MediaType.APPLICATION_JSON_VALUE)
public class UsuarioGrupoController implements UsuarioGrupoControllerOpenApi {

    @Autowired
    private CadastroUsuarioService cadastroUsuarioService;

    @Autowired
    private GrupoModelAssembler grupoModelAssembler;

    @GetMapping
    public List<GrupoModel> listar(@PathVariable Long usuarioId) {
        Usuario usuario = cadastroUsuarioService.buscarOuFalhar(usuarioId);
        return grupoModelAssembler.toCollectionModel(usuario.getGrupos());
    }

    @DeleteMapping("/{grupoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void desvincular(@PathVariable Long usuarioId, @PathVariable Long grupoId) {
        cadastroUsuarioService.desvincularGrupo(usuarioId, grupoId);
    }

    @PutMapping("/{grupoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void vincular(@PathVariable Long usuarioId, @PathVariable Long grupoId) {
        cadastroUsuarioService.vincularGrupo(usuarioId, grupoId);
    }


}
