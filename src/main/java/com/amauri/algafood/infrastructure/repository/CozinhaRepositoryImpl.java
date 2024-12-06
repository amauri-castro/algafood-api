package com.amauri.algafood.infrastructure.repository;

import com.amauri.algafood.domain.model.Cozinha;
import com.amauri.algafood.domain.repository.CozinhaRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Component
public class CozinhaRepositoryImpl implements CozinhaRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Cozinha> listar() {
        TypedQuery<Cozinha> query = entityManager.createQuery("FROM Cozinha", Cozinha.class);

        return query.getResultList();
    }

    @Override
    public Cozinha buscar(Long id) {

        return entityManager.find(Cozinha.class, id);
    }

    @Transactional
    @Override
    public Cozinha salvar(Cozinha cozinha) {
        return entityManager.merge(cozinha);

    }

    @Transactional
    @Override
    public void remover(Long id) {
        Cozinha cozinha = buscar(id);
        if(cozinha == null) {
            throw new EmptyResultDataAccessException(1);
        }
        entityManager.remove(cozinha);
    }
}
