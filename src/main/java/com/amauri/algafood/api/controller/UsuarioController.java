package com.amauri.algafood.api.controller;

import com.amauri.algafood.api.assembler.UsuarioInputDisassembler;
import com.amauri.algafood.api.assembler.UsuarioModelAssembler;
import com.amauri.algafood.api.model.UsuarioModel;
import com.amauri.algafood.api.model.input.SenhaInput;
import com.amauri.algafood.api.model.input.UsuarioComSenhaInput;
import com.amauri.algafood.api.model.input.UsuarioInput;
import com.amauri.algafood.api.openapi.controller.UsuarioControllerOpenApi;
import com.amauri.algafood.domain.model.Usuario;
import com.amauri.algafood.domain.repository.UsuarioRepository;
import com.amauri.algafood.domain.service.CadastroUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/usuarios", produces = MediaType.APPLICATION_JSON_VALUE)
public class UsuarioController implements UsuarioControllerOpenApi {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CadastroUsuarioService cadastroUsuarioService;

    @Autowired
    private UsuarioInputDisassembler usuarioInputDisassembler;
    @Autowired
    private UsuarioModelAssembler usuarioModelAssembler;


    @GetMapping
    public List<UsuarioModel> listar() {
        return usuarioModelAssembler.toCollectionModel(usuarioRepository.findAll());
    }

    @GetMapping("/{usuarioId}")
    public UsuarioModel buscar(@PathVariable Long usuarioId) {
        Usuario usuario = cadastroUsuarioService.buscarOuFalhar(usuarioId);
        return usuarioModelAssembler.toModel(usuario);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioModel salvar(@RequestBody @Valid UsuarioComSenhaInput usuarioComSenhaInput) {
        Usuario usuario = usuarioInputDisassembler.toDomainObject(usuarioComSenhaInput);
        return usuarioModelAssembler.toModel(cadastroUsuarioService.salvar(usuario));
    }

    @PutMapping("/{usuarioId}")
    public UsuarioModel atualizar(@PathVariable Long usuarioId, @RequestBody @Valid UsuarioInput usuarioInput) {
        Usuario usuarioAtual = cadastroUsuarioService.buscarOuFalhar(usuarioId);
        usuarioInputDisassembler.copyToDomainObject(usuarioInput, usuarioAtual);
        return usuarioModelAssembler.toModel(cadastroUsuarioService.salvar(usuarioAtual));
    }

    @PutMapping("/{usuarioId}/senha")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void alterarSenha(@PathVariable Long usuarioId, @RequestBody @Valid SenhaInput senhaInput) {
        cadastroUsuarioService.alterarSenha(usuarioId, senhaInput.getSenhaAtual(), senhaInput.getNovaSenha());
    }

}
