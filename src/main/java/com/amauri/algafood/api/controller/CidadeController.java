package com.amauri.algafood.api.controller;

import com.amauri.algafood.domain.exception.EntidadeEmUsoException;
import com.amauri.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.amauri.algafood.domain.model.Cidade;
import com.amauri.algafood.domain.model.Estado;
import com.amauri.algafood.domain.repository.CidadeRepository;
import com.amauri.algafood.domain.repository.EstadoRepository;
import com.amauri.algafood.domain.service.CadastroCidadeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cidades")
public class CidadeController {

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private CadastroCidadeService cadastroCidade;


    @GetMapping
    public List<Cidade> listar() {
        return cidadeRepository.findAll();
    }

    @GetMapping("/{cidadeId}")
    public ResponseEntity<Cidade> buscar(@PathVariable Long cidadeId) {
        Optional<Cidade> cidade = cidadeRepository.findById(cidadeId);
        return cidade.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
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
        estadoRepository.findById(cidade.getEstado().getId());
        Optional<Cidade> cidadeAtual = cidadeRepository.findById(cidadeId);
        if( cidadeAtual.isPresent()) {
            BeanUtils.copyProperties(cidade, cidadeAtual.get(), "id");
            Cidade cidadeSalva = cidadeRepository.save(cidadeAtual.get());
            return ResponseEntity.ok(cidadeSalva);
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
