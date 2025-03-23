package com.amauri.algafood.api.v1.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoModel {

    @ApiModelProperty(example = "64770-000")
    private String cep;
    @ApiModelProperty(example = "Rua da Paz")
    private String logradouro;
    @ApiModelProperty(example = "350")
    private String numero;
    @ApiModelProperty(example = "Apto 02")
    private String complemento;
    @ApiModelProperty(example = "Centro")
    private String bairro;

    private CidadeResumoModel cidade;
}
