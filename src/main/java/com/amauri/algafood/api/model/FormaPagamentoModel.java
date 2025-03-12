package com.amauri.algafood.api.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FormaPagamentoModel {

    @ApiModelProperty(example = "1")
    private Long id;

    @ApiModelProperty(example = "Cartão de débito")
    private String descricao;
}
