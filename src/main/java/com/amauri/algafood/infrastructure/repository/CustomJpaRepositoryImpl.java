package com.amauri.algafood.infrastructure.repository;

import com.amauri.algafood.domain.repository.CustomJpaRepository;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import jakarta.persistence.EntityManager;
import java.util.Optional;

public class CustomJpaRepositoryImpl<T, ID> extends SimpleJpaRepository<T, ID> implements CustomJpaRepository<T, ID> {

    private EntityManager manager;


    public CustomJpaRepositoryImpl(JpaEntityInformation<T, ?> entityInformation,
                                   EntityManager entityManager) {
        super(entityInformation, entityManager);

        this.manager = entityManager;
    }

    @Override
    public Optional<T> buscarPrimeiro() {
        var jpql = "FROM " + getDomainClass().getName();

        T entity = manager.createQuery(jpql, getDomainClass())
                .setMaxResults(1)
                .getSingleResult();

        return Optional.ofNullable(entity);
    }

    @Override
    public void detach(T entity) {
        manager.detach(entity);
    }
}
