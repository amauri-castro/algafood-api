package com.amauri.algafood.api.v1.controller;

import com.amauri.algafood.api.v1.assembler.CozinhaInputDisassembler;
import com.amauri.algafood.api.v1.assembler.CozinhaModelAssembler;
import com.amauri.algafood.api.v1.model.CozinhaModel;
import com.amauri.algafood.api.v1.model.input.CozinhaInput;
import com.amauri.algafood.api.v1.openapi.controller.CozinhaControllerOpenApi;
import com.amauri.algafood.domain.model.Cozinha;
import com.amauri.algafood.domain.repository.CozinhaRepository;
import com.amauri.algafood.domain.service.CadastroCozinhaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping(value = "/v1/cozinhas", produces = MediaType.APPLICATION_JSON_VALUE)
public class CozinhaController implements CozinhaControllerOpenApi {

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @Autowired
    private CadastroCozinhaService cadastroCozinha;

    @Autowired
    private CozinhaInputDisassembler cozinhaInputDisassembler;
    @Autowired
    private CozinhaModelAssembler cozinhaModelAssembler;

    @Autowired
    private PagedResourcesAssembler<Cozinha> pagedResourcesAssembler;

    @PreAuthorize("isAuthenticated()")
    @Override
    @GetMapping
    public PagedModel<CozinhaModel> listar(@PageableDefault(size = 2) Pageable pageable) {
//        log.info("Consultando cozinhas com páginas de {} registros...", pageable.getPageSize());
//
//        if(true) {
//            throw new RuntimeException("Teste de exception");
//        }

        Page<Cozinha> pageCozinhas = cozinhaRepository.findAll(pageable);
        PagedModel<CozinhaModel> cozinhasPagedModel = pagedResourcesAssembler
                .toModel(pageCozinhas, cozinhaModelAssembler);

        return cozinhasPagedModel;
    }

    @PreAuthorize("isAuthenticated()")
    @Override
    @GetMapping("/{cozinhaId}")
    public CozinhaModel buscar(@PathVariable("cozinhaId") Long id) {
        Cozinha cozinha = cadastroCozinha.buscarOuFalhar(id);
        return cozinhaModelAssembler.toModel(cozinha);
    }

    @PreAuthorize("hasAuthority('EDITAR_COZINHAS')")
    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CozinhaModel salvar(@RequestBody @Valid CozinhaInput cozinhaInput) {
        Cozinha cozinha = cozinhaInputDisassembler.toDomainObject(cozinhaInput);
        return cozinhaModelAssembler.toModel(cadastroCozinha.salvar(cozinha));
    }

    @PreAuthorize("hasAuthority('EDITAR_COZINHAS')")
    @Override
    @PutMapping("/{cozinhaId}")
    public CozinhaModel atualizar(@PathVariable Long cozinhaId, @RequestBody @Valid CozinhaInput cozinhaInput) {
        Cozinha cozinhaAtual = cadastroCozinha.buscarOuFalhar(cozinhaId);
        cozinhaInputDisassembler.copyToDomainObject(cozinhaInput, cozinhaAtual);
        return cozinhaModelAssembler.toModel(cadastroCozinha.salvar(cozinhaAtual));
    }

    @PreAuthorize("hasAuthority('EDITAR_COZINHAS')")
    @Override
    @DeleteMapping("/{cozinhaId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long cozinhaId){
            cadastroCozinha.excluir(cozinhaId);
    }
}
