package com.amauri.algafood.infrastructure.repository;

import com.amauri.algafood.domain.model.Estado;
import com.amauri.algafood.domain.repository.EstadoRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Component
public class EstadoRepositoryImpl implements EstadoRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Estado> listar() {
        TypedQuery<Estado> query = entityManager.createQuery("FROM Estado", Estado.class);

        return query.getResultList();
    }

    @Override
    public Estado buscar(Long id) {

        return entityManager.find(Estado.class, id);
    }

    @Transactional
    @Override
    public Estado salvar(Estado estado) {
        return entityManager.merge(estado);

    }

    @Transactional
    @Override
    public void remover(Long id) {
        Estado estado = buscar(id);
        if(estado == null) {
            throw new EmptyResultDataAccessException(1);
        }
        entityManager.remove(estado);
    }
}
