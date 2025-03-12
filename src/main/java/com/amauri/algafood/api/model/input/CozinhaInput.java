package com.amauri.algafood.api.model.input;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class CozinhaInput {

    @ApiModelProperty(example = "Nordestina", required = true)
    @NotBlank
    private String nome;
}
