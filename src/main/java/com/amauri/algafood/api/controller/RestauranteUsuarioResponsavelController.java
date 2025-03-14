package com.amauri.algafood.api.controller;

import com.amauri.algafood.api.assembler.UsuarioModelAssembler;
import com.amauri.algafood.api.model.UsuarioModel;
import com.amauri.algafood.api.openapi.controller.RestauranteUsuarioResponsavelControllerOpenApi;
import com.amauri.algafood.domain.model.Restaurante;
import com.amauri.algafood.domain.service.CadastroRestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/restaurantes/{restauranteId}/responsaveis", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestauranteUsuarioResponsavelController implements RestauranteUsuarioResponsavelControllerOpenApi {


    @Autowired
    private CadastroRestauranteService cadastroRestauranteService;

    @Autowired
    private UsuarioModelAssembler usuarioModelAssembler;

    @GetMapping
    public CollectionModel<UsuarioModel> listar(@PathVariable Long restauranteId) {
        Restaurante restaurante = cadastroRestauranteService.buscarOuFalhar(restauranteId);
        return usuarioModelAssembler.toCollectionModel(restaurante.getResponsaveis())
                .removeLinks()
                .add(linkTo(methodOn(RestauranteUsuarioResponsavelController.class)
                        .listar(restauranteId)).withSelfRel());
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
