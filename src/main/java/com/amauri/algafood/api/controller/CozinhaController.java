package com.amauri.algafood.api.controller;

import com.amauri.algafood.api.assembler.CozinhaInputDisassembler;
import com.amauri.algafood.api.assembler.CozinhaModelAssembler;
import com.amauri.algafood.api.model.CozinhaModel;
import com.amauri.algafood.api.model.input.CozinhaInput;
import com.amauri.algafood.domain.model.Cozinha;
import com.amauri.algafood.domain.repository.CozinhaRepository;
import com.amauri.algafood.domain.service.CadastroCozinhaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @Autowired
    private CadastroCozinhaService cadastroCozinha;

    @Autowired
    private CozinhaInputDisassembler cozinhaInputDisassembler;
    @Autowired
    private CozinhaModelAssembler cozinhaModelAssembler;

    @GetMapping
    public Page<CozinhaModel> listar(@PageableDefault(size = 2) Pageable pageable) {
        Page<Cozinha> pageCozinhas = cozinhaRepository.findAll(pageable);
        List<CozinhaModel> cozinhaModels = cozinhaModelAssembler.toCollectionModel(pageCozinhas.getContent());

        Page<CozinhaModel> cozinhaModelPage = new PageImpl<>(cozinhaModels, pageable,
                pageCozinhas.getTotalElements());

        return cozinhaModelPage;
    }

    @GetMapping("/{cozinhaId}")
    public CozinhaModel buscar(@PathVariable("cozinhaId") Long id) {
        Cozinha cozinha = cadastroCozinha.buscarOuFalhar(id);
        return cozinhaModelAssembler.toModel(cozinha);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CozinhaModel salvar(@RequestBody @Valid CozinhaInput cozinhaInput) {
        Cozinha cozinha = cozinhaInputDisassembler.toDomainObject(cozinhaInput);
        return cozinhaModelAssembler.toModel(cadastroCozinha.salvar(cozinha));
    }

    @PutMapping("/{cozinhaId}")
    public CozinhaModel atualizar(@PathVariable Long cozinhaId, @RequestBody @Valid CozinhaInput cozinhaInput) {
        Cozinha cozinhaAtual = cadastroCozinha.buscarOuFalhar(cozinhaId);
        cozinhaInputDisassembler.copyToDomainObject(cozinhaInput, cozinhaAtual);
        return cozinhaModelAssembler.toModel(cadastroCozinha.salvar(cozinhaAtual));
    }

    @DeleteMapping("/{cozinhaId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long cozinhaId){
            cadastroCozinha.excluir(cozinhaId);
    }
}
