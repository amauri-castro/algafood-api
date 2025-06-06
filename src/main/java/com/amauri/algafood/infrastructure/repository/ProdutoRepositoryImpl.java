package com.amauri.algafood.infrastructure.repository;

import com.amauri.algafood.domain.model.FotoProduto;
import com.amauri.algafood.domain.repository.ProdutoRepositoryQueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;

@Repository
public class ProdutoRepositoryImpl implements ProdutoRepositoryQueries {

    @Autowired
    private EntityManager manager;

    @Transactional
    @Override
    public FotoProduto save(FotoProduto foto) {
        return manager.merge(foto);
    }

    @Transactional
    @Override
    public void delete(FotoProduto foto) {
        manager.remove(foto);
    }
}
