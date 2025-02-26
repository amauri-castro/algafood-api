package com.amauri.algafood.domain.repository;

import com.amauri.algafood.domain.filter.VendaDiariaFilter;
import com.amauri.algafood.domain.model.dto.VendaDiaria;

import java.util.List;

public interface VendaQueryService {

    List<VendaDiaria> consultarVendasDiarias(VendaDiariaFilter filtro, String timeOffset);

}
