package com.amauri.algafood.infrastructure.service;

import com.amauri.algafood.domain.enums.StatusPedidoEnum;
import com.amauri.algafood.domain.filter.VendaDiariaFilter;
import com.amauri.algafood.domain.model.Pedido;
import com.amauri.algafood.domain.model.dto.VendaDiaria;
import com.amauri.algafood.domain.repository.VendaQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class VendaQueryServiceImpl implements VendaQueryService {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<VendaDiaria> consultarVendasDiarias(VendaDiariaFilter filtro, String timeOffset) {
        var builder = entityManager.getCriteriaBuilder();
        var query = builder.createQuery(VendaDiaria.class);
        var root = query.from(Pedido.class);
        var functionConvertTzDataCriacao = builder.function(
                "convert_tz", Date.class, root.get("dataCriacao"),
                builder.literal("+00:00"), builder.literal(timeOffset));

        var functionDateDateCriacao = builder.function("date", Date.class, functionConvertTzDataCriacao);

        List<Predicate> predicados = new ArrayList<>();

        if(filtro.getRestauranteId() != null) {
            predicados.add(builder.equal(root.get("restaurante").get("id"), filtro.getRestauranteId()));
        }

        if(filtro.getDataCriacaoInicio() != null) {
            predicados.add(builder.greaterThanOrEqualTo(root.get("dataCriacao"), filtro.getDataCriacaoInicio()));
        }

        if(filtro.getDataCriacaoFim() != null) {
            predicados.add(builder.lessThanOrEqualTo(root.get("dataCriacao"), filtro.getDataCriacaoFim()));
        }

        var listaStatus = List.of(StatusPedidoEnum.CONFIRMADO, StatusPedidoEnum.ENTREGUE);
        predicados.add(root.get("status").in(listaStatus));

        var selection = builder.construct(VendaDiaria.class,
                functionDateDateCriacao,
                builder.count(root.get("id")),
                builder.sum(root.get("valorTotal")));

        query.select(selection);
        query.where(predicados.toArray(new Predicate[0]));
        query.groupBy(functionDateDateCriacao);

        return entityManager.createQuery(query).getResultList();
    }
}
