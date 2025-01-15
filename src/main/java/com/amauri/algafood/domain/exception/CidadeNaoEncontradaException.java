package com.amauri.algafood.domain.exception;

public class CidadeNaoEncontradaException extends EntidadeNaoEncontradaException {

    public CidadeNaoEncontradaException(String mensagem) {
        super(mensagem);
    }

    public CidadeNaoEncontradaException(Long estadoId) {
        this(String.format("Não existe um cadastro de Cidade com o código %d", estadoId));
    }
}
