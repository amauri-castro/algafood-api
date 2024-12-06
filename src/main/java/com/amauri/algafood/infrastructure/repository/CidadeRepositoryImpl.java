package com.amauri.algafood.infrastructure.repository;

import com.amauri.algafood.domain.model.Cidade;
import com.amauri.algafood.domain.repository.CidadeRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Component
public class CidadeRepositoryImpl implements CidadeRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Cidade> listar() {
        TypedQuery<Cidade> query = entityManager.createQuery("FROM Cidade", Cidade.class);

        return query.getResultList();
    }

    @Override
    public Cidade buscar(Long id) {

        return entityManager.find(Cidade.class, id);
    }

    @Transactional
    @Override
    public Cidade salvar(Cidade cidade) {
        return entityManager.merge(cidade);

    }

    @Transactional
    @Override
    public void remover(Long cidadeId) {
        Cidade cidade = buscar(cidadeId);
        if(cidade == null) {
            throw new EmptyResultDataAccessException(1);
        }
        entityManager.remove(cidade);
    }
}
