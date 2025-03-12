package com.amauri.algafood.api.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class RestauranteResumoModel {

    @ApiModelProperty(example = "1")
    private Long id;
    @ApiModelProperty(example = "Rodizio Nordestino")
    private String nome;
}
