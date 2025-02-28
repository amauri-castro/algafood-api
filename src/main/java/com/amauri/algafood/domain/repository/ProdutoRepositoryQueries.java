package com.amauri.algafood.domain.repository;

import com.amauri.algafood.domain.model.FotoProduto;

public interface ProdutoRepositoryQueries {

    FotoProduto save(FotoProduto foto);
    void delete(FotoProduto foto);
}
