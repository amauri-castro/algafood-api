package com.amauri.algafood.api.v1.model.input;


import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class GrupoInput {

    @ApiModelProperty(example = "Gerente", required = true)
    @NotBlank
    private String nome;
}
