package com.amauri.algafood.api.v2.controller;

import com.amauri.algafood.api.ResourceUriHelper;
import com.amauri.algafood.api.v2.assembler.CidadeInputDisassemblerV2;
import com.amauri.algafood.api.v2.assembler.CidadeModelAssemblerV2;
import com.amauri.algafood.api.v2.model.CidadeModelV2;
import com.amauri.algafood.api.v2.model.input.CidadeInputV2;
import com.amauri.algafood.core.web.AlgaMediaTypes;
import com.amauri.algafood.domain.exception.EstadoNaoEncontradoException;
import com.amauri.algafood.domain.exception.NegocioException;
import com.amauri.algafood.domain.model.Cidade;
import com.amauri.algafood.domain.repository.CidadeRepository;
import com.amauri.algafood.domain.repository.EstadoRepository;
import com.amauri.algafood.domain.service.CadastroCidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping(path = "/cidades")
public class CidadeControllerV2 {

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private CadastroCidadeService cadastroCidade;

    @Autowired
    private CidadeInputDisassemblerV2 cidadeInputDisassembler;
    @Autowired
    private CidadeModelAssemblerV2 cidadeModelAssembler;


    @GetMapping(produces = AlgaMediaTypes.V2_APPLICATION_JSON_VALUE)
    public CollectionModel<CidadeModelV2> listar() {
        List<Cidade> todasCidades = cidadeRepository.findAll();
        return cidadeModelAssembler.toCollectionModel(todasCidades);
    }


    @GetMapping(value = "/{cidadeId}", produces = AlgaMediaTypes.V2_APPLICATION_JSON_VALUE)
    public CidadeModelV2 buscar(@PathVariable Long cidadeId) {
        Cidade cidade = cadastroCidade.buscarOuFalhar(cidadeId);
        return cidadeModelAssembler.toModel(cidade);
    }

    @PostMapping(produces = AlgaMediaTypes.V2_APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public CidadeModelV2 salvar(@RequestBody @Valid CidadeInputV2 cidadeInput) {
        try {
            Cidade cidade = cidadeInputDisassembler.toDomainObject(cidadeInput);
            cidade = cadastroCidade.salvar(cidade);
            CidadeModelV2 cidadeModel = cidadeModelAssembler.toModel(cidade);
            ResourceUriHelper.addUriInResponseHeader(cidadeModel.getIdCidade());
            return cidadeModel;
        } catch (EstadoNaoEncontradoException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }


    @PutMapping(value = "/{cidadeId}", produces = AlgaMediaTypes.V2_APPLICATION_JSON_VALUE)
    public CidadeModelV2 atualizar(@PathVariable Long cidadeId, @RequestBody @Valid CidadeInputV2 cidadeInput) {
        try {
            Cidade cidadeAtual = cadastroCidade.buscarOuFalhar(cidadeId);
            cidadeInputDisassembler.copyToDomainObject(cidadeInput, cidadeAtual);
            return cidadeModelAssembler.toModel(cadastroCidade.salvar(cidadeAtual));
        } catch (EstadoNaoEncontradoException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }


//    @DeleteMapping("/{cidadeId}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void excluir(@PathVariable Long cidadeId) {
//        cadastroCidade.excluir(cidadeId);
//    }

}
