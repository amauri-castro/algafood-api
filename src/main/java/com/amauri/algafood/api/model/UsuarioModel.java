package com.amauri.algafood.api.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioModel {

    @ApiModelProperty(example = "1")
    private Long id;
    @ApiModelProperty(example = "José Gomes")
    private String nome;
    @ApiModelProperty(example = "josegome@gmail.com")
    private String email;
}
