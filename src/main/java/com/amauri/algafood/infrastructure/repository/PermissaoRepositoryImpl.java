package com.amauri.algafood.infrastructure.repository;

import com.amauri.algafood.domain.model.Permissao;
import com.amauri.algafood.domain.repository.PermissaoRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Component
public class PermissaoRepositoryImpl implements PermissaoRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Permissao> listar() {
        TypedQuery<Permissao> query = entityManager.createQuery("FROM Permissao", Permissao.class);

        return query.getResultList();
    }

    @Override
    public Permissao buscar(Long id) {

        return entityManager.find(Permissao.class, id);
    }

    @Transactional
    @Override
    public Permissao salvar(Permissao permissao) {
        return entityManager.merge(permissao);

    }

    @Transactional
    @Override
    public void remover(Permissao permissao) {
        permissao = buscar(permissao.getId());
        entityManager.remove(permissao);
    }
}
