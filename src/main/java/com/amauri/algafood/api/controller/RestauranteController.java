package com.amauri.algafood.api.controller;

import com.amauri.algafood.api.assembler.RestauranteApenasNomeModelAssembler;
import com.amauri.algafood.api.assembler.RestauranteBasicoModelAssembler;
import com.amauri.algafood.api.assembler.RestauranteInputDisassembler;
import com.amauri.algafood.api.assembler.RestauranteModelAssembler;
import com.amauri.algafood.api.model.RestauranteApenasNomeModel;
import com.amauri.algafood.api.model.RestauranteBasicoModel;
import com.amauri.algafood.api.model.RestauranteModel;
import com.amauri.algafood.api.model.input.RestauranteInput;
import com.amauri.algafood.api.model.view.RestauranteView;
import com.amauri.algafood.api.openapi.controller.RestauranteControllerOpenApi;
import com.amauri.algafood.domain.exception.CidadeNaoEncontradaException;
import com.amauri.algafood.domain.exception.CozinhaNaoEncontradaException;
import com.amauri.algafood.domain.exception.NegocioException;
import com.amauri.algafood.domain.exception.RestauranteNaoEncontradoException;
import com.amauri.algafood.domain.model.Restaurante;
import com.amauri.algafood.domain.repository.RestauranteRepository;
import com.amauri.algafood.domain.service.CadastroRestauranteService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/restaurantes", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestauranteController implements RestauranteControllerOpenApi {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private CadastroRestauranteService cadastroRestauranteService;

    @Autowired
    private RestauranteModelAssembler restauranteModelAssembler;

    @Autowired
    private RestauranteInputDisassembler restauranteInputDisassembler;

    @Autowired
    private RestauranteBasicoModelAssembler restauranteBasicoModelAssembler;

    @Autowired
    private RestauranteApenasNomeModelAssembler restauranteApenasNomeModelAssembler;

//    @JsonView(RestauranteView.Resumo.class)
    @GetMapping
    public CollectionModel<RestauranteBasicoModel> listar() {
        return restauranteBasicoModelAssembler.toCollectionModel(restauranteRepository.findAll());
    }


//    @JsonView(RestauranteView.ApenasNome.class)
    @GetMapping(params = "projecao=apenas-nome")
    public CollectionModel<RestauranteApenasNomeModel> listarApenasNomes() {
        return restauranteApenasNomeModelAssembler.toCollectionModel(restauranteRepository.findAll());
    }

//    @GetMapping
//    public MappingJacksonValue listar(@RequestParam(required = false) String projecao) {
//        List<Restaurante> restaurantes = restauranteRepository.findAll();
//        List<RestauranteModel> restauranteModels = restauranteModelAssembler.toCollectionModel(restaurantes);
//        MappingJacksonValue restaurantesWrapper = new MappingJacksonValue(restauranteModels);
//        restaurantesWrapper.setSerializationView(RestauranteView.Resumo.class);
//        if("apenas-nome".equals(projecao)) {
//            restaurantesWrapper.setSerializationView(RestauranteView.ApenasNome.class);
//        }else if("completo".equals(projecao)) {
//            restaurantesWrapper.setSerializationView(null);
//        }
//
//        return restaurantesWrapper;
//    }

    @GetMapping("/{restauranteId}")
    public RestauranteModel buscar( @PathVariable Long restauranteId) {
        Restaurante restaurante = cadastroRestauranteService.buscarOuFalhar(restauranteId);
        return restauranteModelAssembler.toModel(restaurante);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RestauranteModel salvar(@RequestBody @Valid RestauranteInput restauranteInput) {
        try {
            Restaurante restaurante = restauranteInputDisassembler.toDomainObject(restauranteInput);
            return restauranteModelAssembler.toModel(cadastroRestauranteService.salvar(restaurante));
        } catch (CozinhaNaoEncontradaException | CidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    @PutMapping("/{restauranteId}")
    public RestauranteModel atualizar(@PathVariable Long restauranteId, @RequestBody @Valid RestauranteInput restauranteInput) {
        try {

            Restaurante restauranteAtual = cadastroRestauranteService.buscarOuFalhar(restauranteId);
            restauranteInputDisassembler.copyToDomainObject(restauranteInput, restauranteAtual);
            return restauranteModelAssembler.toModel(cadastroRestauranteService.salvar(restauranteAtual));

        } catch (CozinhaNaoEncontradaException | CidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    @PutMapping("/{restauranteId}/ativo")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> ativar(@PathVariable Long restauranteId) {
        cadastroRestauranteService.ativar(restauranteId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{restauranteId}/ativo")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> inativar(@PathVariable Long restauranteId) {
        cadastroRestauranteService.inativar(restauranteId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/ativacoes")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void ativarMultiplos(@RequestBody List<Long> restauranteIds) {
        try {
            cadastroRestauranteService.ativar(restauranteIds);
        } catch (RestauranteNaoEncontradoException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @DeleteMapping("/ativacoes")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void inativarMultiplos(@RequestBody List<Long> restauranteIds) {
        try {
            cadastroRestauranteService.inativar(restauranteIds);
        } catch (RestauranteNaoEncontradoException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @PutMapping("/{restauranteId}/abertura")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> abrir(@PathVariable Long restauranteId) {
        cadastroRestauranteService.abrir(restauranteId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{restauranteId}/fechamento")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> fechar(@PathVariable Long restauranteId) {
        cadastroRestauranteService.fechar(restauranteId);
        return ResponseEntity.noContent().build();
    }

}
