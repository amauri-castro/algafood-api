package com.amauri.algafood.api.controller;

import com.amauri.algafood.api.assembler.UsuarioModelAssembler;
import com.amauri.algafood.api.model.UsuarioModel;
import com.amauri.algafood.domain.model.Restaurante;
import com.amauri.algafood.domain.service.CadastroRestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurantes/{restauranteId}/responsaveis")
public class RestauranteUsuarioResponsavelController {


    @Autowired
    private CadastroRestauranteService cadastroRestauranteService;

    @Autowired
    private UsuarioModelAssembler usuarioModelAssembler;

    @GetMapping
    public List<UsuarioModel> listar(@PathVariable Long restauranteId) {
        Restaurante restaurante = cadastroRestauranteService.buscarOuFalhar(restauranteId);

        return usuarioModelAssembler.toCollectionModel(restaurante.getResponsaveis());
    }

    @DeleteMapping("/{usuarioId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void desvincular(@PathVariable Long restauranteId, @PathVariable Long usuarioId) {
        cadastroRestauranteService.desvincularResponsavel(restauranteId, usuarioId);
    }

    @PutMapping("/{usuarioId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void vincular(@PathVariable Long restauranteId, @PathVariable Long usuarioId) {
        cadastroRestauranteService.vincularResponsavel(restauranteId, usuarioId);
    }


}
