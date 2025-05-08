package com.amauri.algafood.api.v1.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoModel {

    @Schema(example = "64015-000")
    private String cep;
    @Schema(example = "Rua 7 de Setembro")
    private String logradouro;
    @Schema(example = "1000")
    private String numero;
    @Schema(example = "Apto 101")
    private String complemento;
    @Schema(example = "Centro")
    private String bairro;

    private CidadeResumoModel cidade;
}
