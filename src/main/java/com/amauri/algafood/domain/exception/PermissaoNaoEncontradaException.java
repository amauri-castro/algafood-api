package com.amauri.algafood.domain.exception;

public class PermissaoNaoEncontradaException extends EntidadeNaoEncontradaException{
    public PermissaoNaoEncontradaException(String mensagem) {
        super(mensagem);
    }

    public PermissaoNaoEncontradaException(Long formaPagamentoId) {
        this(String.format("Não existe um cadastro de Forma de pagamento com o código %d", formaPagamentoId));
    }
}
