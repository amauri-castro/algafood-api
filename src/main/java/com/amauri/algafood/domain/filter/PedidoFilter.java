package com.amauri.algafood.domain.filter;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.OffsetDateTime;

@Setter
@Getter
public class PedidoFilter {

    @ApiModelProperty(example = "1", value = "ID do cliente para filtro da pesquisa")
    private Long clienteId;
    @ApiModelProperty(example = "1", value = "ID do restaurante para filtro da pesquisa")
    private Long restauranteId;
    @ApiModelProperty(example = "2025-03-11T21:30:00Z", value = "data/hora de criação inicial para filtro da pesquisa")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private OffsetDateTime dataCriacaoInicio;
    @ApiModelProperty(example = "2025-03-11T22:30:00Z", value = "data/hora de criação final para filtro da pesquisa")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private OffsetDateTime dataCriacaoFim;
}
