package com.amauri.algafood.domain.exception;

public class CozinhaNaoEncontradaException extends EntidadeNaoEncontradaException {

    public CozinhaNaoEncontradaException(String mensagem) {
        super(mensagem);
    }

    public CozinhaNaoEncontradaException(Long estadoId) {
        this(String.format("Não existe um cadastro de Cozinha com o código %d", estadoId));
    }
}
