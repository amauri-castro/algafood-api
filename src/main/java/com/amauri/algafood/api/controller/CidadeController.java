package com.amauri.algafood.api.controller;

import com.amauri.algafood.api.ResourceUriHelper;
import com.amauri.algafood.api.assembler.CidadeInputDisassembler;
import com.amauri.algafood.api.assembler.CidadeModelAssembler;
import com.amauri.algafood.api.model.CidadeModel;
import com.amauri.algafood.api.model.input.CidadeInput;
import com.amauri.algafood.api.openapi.controller.CidadeControllerOpenApi;
import com.amauri.algafood.domain.exception.EstadoNaoEncontradoException;
import com.amauri.algafood.domain.exception.NegocioException;
import com.amauri.algafood.domain.model.Cidade;
import com.amauri.algafood.domain.repository.CidadeRepository;
import com.amauri.algafood.domain.repository.EstadoRepository;
import com.amauri.algafood.domain.service.CadastroCidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RestController
@RequestMapping(path = "/cidades", produces = MediaType.APPLICATION_JSON_VALUE)
public class CidadeController implements CidadeControllerOpenApi {

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private CadastroCidadeService cadastroCidade;

    @Autowired
    private CidadeInputDisassembler cidadeInputDisassembler;
    @Autowired
    private CidadeModelAssembler cidadeModelAssembler;


    @GetMapping
    public CollectionModel<CidadeModel> listar() {
        List<CidadeModel> todasCidades = cidadeModelAssembler.toCollectionModel(cidadeRepository.findAll());

        todasCidades.forEach(cidade -> {
            cidade.add(linkTo(methodOn(CidadeController.class)
                    .buscar(cidade.getId()))
                    .withSelfRel());

            cidade.add(linkTo(methodOn(CidadeController.class)
                    .listar())
                    .withRel("cidades"));

            cidade.getEstado().add(linkTo(methodOn(EstadoController.class)
                    .buscar(cidade.getEstado().getId()))
                    .withSelfRel());
        });

        CollectionModel<CidadeModel> cidadesCollectionModel = CollectionModel.of(todasCidades);
        cidadesCollectionModel.add(linkTo(CidadeController.class).withSelfRel());
        return cidadesCollectionModel;
    }


    @GetMapping("/{cidadeId}")
    public CidadeModel buscar(@PathVariable Long cidadeId) {
        Cidade cidade = cadastroCidade.buscarOuFalhar(cidadeId);

        CidadeModel cidadeModel = cidadeModelAssembler.toModel(cidade);


        cidadeModel.add(linkTo(methodOn(CidadeController.class)
                        .buscar(cidadeModel.getId()))
                        .withSelfRel());

        cidadeModel.add(linkTo(methodOn(CidadeController.class)
                        .listar())
                .withRel("cidades"));

        cidadeModel.getEstado().add(linkTo(methodOn(EstadoController.class)
                .buscar(cidadeModel.getEstado().getId()))
                .withSelfRel());
//        cidadeModel.add(WebMvcLinkBuilder.linkTo(CidadeController.class)
//                .withRel("cidades"));



//        cidadeModel.getEstado().add(WebMvcLinkBuilder.linkTo(EstadoController.class)
//                .slash(cidadeModel.getEstado().getId())
//                .withSelfRel());
        return cidadeModel;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CidadeModel salvar(@RequestBody @Valid CidadeInput cidadeInput) {
        try {
            Cidade cidade = cidadeInputDisassembler.toDomainObject(cidadeInput);
            cidade = cadastroCidade.salvar(cidade);
            CidadeModel cidadeModel = cidadeModelAssembler.toModel(cidade);
            ResourceUriHelper.addUriInResponseHeader(cidadeModel.getId());
            return cidadeModel;
        } catch (EstadoNaoEncontradoException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }


    @PutMapping("/{cidadeId}")
    public CidadeModel atualizar(@PathVariable Long cidadeId, @RequestBody @Valid CidadeInput cidadeInput) {
        try {
            Cidade cidadeAtual = cadastroCidade.buscarOuFalhar(cidadeId);
            cidadeInputDisassembler.copyToDomainObject(cidadeInput, cidadeAtual);
            return cidadeModelAssembler.toModel(cadastroCidade.salvar(cidadeAtual));
        } catch (EstadoNaoEncontradoException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }


    @DeleteMapping("/{cidadeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long cidadeId) {
        cadastroCidade.excluir(cidadeId);
    }

}
