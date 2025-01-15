package com.amauri.algafood.domain.exception;

public class RestauranteNaoEncontradoException extends EntidadeNaoEncontradaException {

    public RestauranteNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

    public RestauranteNaoEncontradoException(Long estadoId) {
        this(String.format("Não existe um cadastro de Restaurante com o código %d", estadoId));
    }
}
