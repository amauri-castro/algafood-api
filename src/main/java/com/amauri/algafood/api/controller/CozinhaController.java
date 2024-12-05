package com.amauri.algafood.api.controller;

import com.amauri.algafood.api.model.CozinhasXmlWrapper;
import com.amauri.algafood.domain.model.Cozinha;
import com.amauri.algafood.infrastructure.repository.CozinhaRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {

    @Autowired
    private CozinhaRepositoryImpl cozinhaRepository;

    @GetMapping
    public List<Cozinha> listar() {
        return cozinhaRepository.listar();
    }

    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public CozinhasXmlWrapper listarXml() {
        return new CozinhasXmlWrapper(cozinhaRepository.listar());
    }

    @GetMapping("/{cozinhaId}")
    public ResponseEntity<Cozinha> buscar(@PathVariable("cozinhaId") Long id) {
        Cozinha cozinha = cozinhaRepository.buscar(id);
        return cozinha != null ? ResponseEntity.ok(cozinha) : ResponseEntity.notFound().build();
    }
}
