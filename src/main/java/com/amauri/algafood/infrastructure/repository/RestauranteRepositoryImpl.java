package com.amauri.algafood.infrastructure.repository;

import com.amauri.algafood.domain.model.Restaurante;
import com.amauri.algafood.domain.repository.RestauranteRepositoryQueries;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

@Repository
public class RestauranteRepositoryImpl implements RestauranteRepositoryQueries {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Restaurante> consultar(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal) {
        var jpql = new StringBuilder();
        jpql.append("FROM Restaurante WHERE 0 = 0 ");

        var parametros = new HashMap<String, Object>();

        if(StringUtils.hasLength(nome)) {
            jpql.append("AND nome LIKE :nome ");
            parametros.put("nome", "%" + nome + "%");
        }
        if(taxaFreteInicial != null) {
            jpql.append("AND taxaFrete >= :taxaInicial ");
            parametros.put("taxaInicial", taxaFreteInicial);
        }
        if(taxaFreteFinal != null) {
            jpql.append("AND taxaFrete <= :taxaFinal ");
            parametros.put("taxaFinal", taxaFreteFinal);
        }
        TypedQuery<Restaurante> query = manager
                .createQuery(jpql.toString(), Restaurante.class);

        parametros.forEach((chave, valor) -> query.setParameter(chave, valor));

        return query.getResultList();
    }
}
