package com.amauri.algafood.domain.exception;

public class UsuarioNaoEncontradoException extends EntidadeNaoEncontradaException {

    public UsuarioNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

    public UsuarioNaoEncontradoException(Long estadoId) {
        this(String.format("Não existe um cadastro de Usuário com o código %d", estadoId));
    }
}
