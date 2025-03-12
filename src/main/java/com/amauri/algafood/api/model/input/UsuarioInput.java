package com.amauri.algafood.api.model.input;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class UsuarioInput {

    @ApiModelProperty(example = "Maria da Silva", required = true)
    @NotBlank
    private String nome;

    @ApiModelProperty(example = "maria.silva@gmail.com", required = true)
    @NotBlank
    @Email
    private String email;
}
