package com.amauri.algafood.api.controller;

import com.amauri.algafood.domain.exception.EntidadeEmUsoException;
import com.amauri.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.amauri.algafood.domain.model.Cidade;
import com.amauri.algafood.domain.repository.CidadeRepository;
import com.amauri.algafood.domain.service.CadastroCidadeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cidades")
public class CidadeController {

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private CadastroCidadeService cadastroCidade;


    @GetMapping
    public List<Cidade> listar() {
        return cidadeRepository.listar();
    }

    @GetMapping("/{cidadeId}")
    public ResponseEntity<Cidade> buscar(@PathVariable Long cidadeId) {
        Cidade cidade = cidadeRepository.buscar(cidadeId);
        return cidade != null ? ResponseEntity.ok(cidade) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> salvar(@RequestBody Cidade cidade) {
        try {
            cidade = cadastroCidade.salvar(cidade);
            return ResponseEntity.status(HttpStatus.CREATED).body(cidade);
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{cidadeId}")
    public ResponseEntity<Cidade> atualizar(@PathVariable Long cidadeId, @RequestBody Cidade cidade) {
        Cidade cidadeAtual = cidadeRepository.buscar(cidadeId);
        if( cidadeAtual != null) {
            BeanUtils.copyProperties(cidade, cidadeAtual, "id");
            cidadeAtual = cidadeRepository.salvar(cidadeAtual);
            return ResponseEntity.ok(cidadeAtual);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{cidadeId}")
    public ResponseEntity<?> excluir(@PathVariable Long cidadeId) {
        try {
            cadastroCidade.excluir(cidadeId);
            return ResponseEntity.noContent().build();

        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.notFound().build();

        } catch (EntidadeEmUsoException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }}
