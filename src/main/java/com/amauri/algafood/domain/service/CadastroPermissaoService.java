package com.amauri.algafood.domain.service;

import com.amauri.algafood.domain.exception.FormaPagamentoNaoEncontradaException;
import com.amauri.algafood.domain.model.FormaPagamento;
import com.amauri.algafood.domain.model.Permissao;
import com.amauri.algafood.domain.repository.PermissaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CadastroPermissaoService {


    @Autowired
    private PermissaoRepository permissaoRepository;

    public Permissao buscarOuFalhar(Long permissaoId) {
        return permissaoRepository.findById(permissaoId)
                .orElseThrow(() -> new FormaPagamentoNaoEncontradaException(permissaoId));
    }

}
