package com.amauri.algafood.api.v1.openapi.controller;

import com.amauri.algafood.api.v1.controller.EstatisticasController;
import com.amauri.algafood.domain.filter.VendaDiariaFilter;
import com.amauri.algafood.domain.model.dto.VendaDiaria;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Api(tags = "Estatísticas")
public interface EstatisticasControllerOpenApi {

    @ApiOperation(value = "Estatísticas", hidden = true)
    EstatisticasController.EstatisticasModel estatisticas();

    @ApiOperation("Consulta estatísticas de vendas diárias")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "restauranteId", value = "ID do restaurante",
            example = "1", dataType = "int"),
            @ApiImplicitParam(name = "dataCriacaoInicio", value = "Data/hora inicial da criação do pedido",
                    example = "2025-03-12T00:00:00Z", dataType = "date-time"),
            @ApiImplicitParam(name = "dataCriacaoFim", value = "Data/hora final da criação do pedido",
                    example = "2025-03-12T00:00:00Z", dataType = "date-time")
    })
    List<VendaDiaria> consultarVendasDiarias(VendaDiariaFilter filtro,
                                             @ApiParam(value = "Deslocamento de horário a ser considerado" +
                                                     " na consulta em relação ao UTC",
                                                     defaultValue = "+00:00")String timeOffset);

    ResponseEntity<byte[]> consultarVendasDiariasPdf(VendaDiariaFilter filtro,
                                                            String timeOffset);
}
