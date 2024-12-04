package com.amauri.algafood.infrastructure.repository;

import com.amauri.algafood.domain.model.Restaurante;
import com.amauri.algafood.domain.repository.RestauranteRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Component
public class RestauranteRepositoryImpl implements RestauranteRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Restaurante> todos() {
        TypedQuery<Restaurante> query = entityManager.createQuery("FROM Restaurante", Restaurante.class);

        return query.getResultList();
    }

    @Override
    public Restaurante porId(Long id) {

        return entityManager.find(Restaurante.class, id);
    }

    @Transactional
    @Override
    public Restaurante adicionar(Restaurante restaurante) {
        return entityManager.merge(restaurante);

    }

    @Transactional
    @Override
    public void remover(Restaurante restaurante) {
        restaurante = porId(restaurante.getId());
        entityManager.remove(restaurante);
    }
}
