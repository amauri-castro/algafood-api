package com.amauri.algafood.domain.exception;

public class FotoProdutoNaoEncontradaException extends EntidadeNaoEncontradaException{
    public FotoProdutoNaoEncontradaException(String mensagem) {
        super(mensagem);
    }

    public FotoProdutoNaoEncontradaException(Long restauranteId, Long produtoId) {
        this(String.format("Não existe uma foto cadastrada para o Produto com o código %d do Restaurante de código %d", produtoId, restauranteId));
    }
}
