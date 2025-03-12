package com.amauri.algafood.api.model.input;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class EnderecoInput {

    @ApiModelProperty(example = "64770-000", required = true)
    @NotBlank
    private String cep;

    @ApiModelProperty(example = "Rua da Paz", required = true)
    @NotBlank
    private String logradouro;

    @ApiModelProperty(example = "350", required = true)
    @NotBlank
    private String numero;

    @ApiModelProperty(example = "Apto 02")
    private String complemento;
    @NotBlank

    @ApiModelProperty(example = "Centro", required = true)
    private String bairro;

    @Valid
    @NotNull
    private CidadeIdInput cidade;

}
