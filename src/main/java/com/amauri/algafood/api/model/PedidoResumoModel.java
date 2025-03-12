package com.amauri.algafood.api.model;

import com.fasterxml.jackson.annotation.JsonFilter;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

//@JsonFilter("pedidoFilter")
@Getter
@Setter
public class PedidoResumoModel {

    @ApiModelProperty(example = "f9981ca4-5a5e-4da3-af04-933861df3e55")
    private String codigo;
    @ApiModelProperty(example = "185.90")
    private BigDecimal subtotal;
    @ApiModelProperty(example = "9.00")
    private BigDecimal taxaFrete;
    @ApiModelProperty(example = "251.90")
    private BigDecimal valorTotal;
    @ApiModelProperty(example = "CRIADO")
    private String status;
    @ApiModelProperty(example = "2025-03-11T21:30:00Z")
    private OffsetDateTime dataCriacao;

    private RestauranteResumoModel restaurante;
    private UsuarioModel cliente;

}
