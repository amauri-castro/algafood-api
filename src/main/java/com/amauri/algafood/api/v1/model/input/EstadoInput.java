package com.amauri.algafood.api.v1.model.input;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class EstadoInput {

    @ApiModelProperty(example = "Bahia", required = true)
    @NotBlank
    private String nome;
}
