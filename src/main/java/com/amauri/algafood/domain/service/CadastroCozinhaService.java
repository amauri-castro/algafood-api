package com.amauri.algafood.domain.service;

import com.amauri.algafood.domain.exception.EntidadeEmUsoException;
import com.amauri.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.amauri.algafood.domain.model.Cozinha;
import com.amauri.algafood.domain.repository.CozinhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CadastroCozinhaService {

    @Autowired
    private CozinhaRepository cozinhaRepository;

    public Cozinha salvar(Cozinha cozinha) {
        return cozinhaRepository.save(cozinha);
    }

    public void excluir(Long cozinhaId) {
        try {
            cozinhaRepository.deleteById(cozinhaId);
        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(
                    String.format("Não existe um cadastro de Cozinha com o código %d",cozinhaId));

        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format("Cozinha de código %d não pode ser removida, pois está em uso",cozinhaId));
        }
    }
}
