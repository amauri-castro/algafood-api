package com.amauri.algafood.api.v1.openapi.controller;

import com.amauri.algafood.api.v1.controller.EstatisticasController.EstatisticasModel;
import com.amauri.algafood.domain.filter.VendaDiariaFilter;
import com.amauri.algafood.domain.model.dto.VendaDiaria;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;

import java.util.List;

@SecurityRequirement(name = "security_auth")
public interface EstatisticasControllerOpenApi {

	EstatisticasModel estatisticas();

	List<VendaDiaria> consultarVendasDiarias(
			VendaDiariaFilter filtro,String timeOffset);

	ResponseEntity<byte[]> consultarVendasDiariasPdf(
			VendaDiariaFilter filtro,
			String timeOffset);

}