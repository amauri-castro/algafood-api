package com.amauri.algafood.api.v2.model.input;

import com.amauri.algafood.api.v1.model.input.EstadoIdInput;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@ApiModel("CidadeInput")
@Setter
@Getter
public class CidadeInputV2 {

    @ApiModelProperty(example = "Teresina")
    @NotBlank
    private String nomeCidade;

    @ApiModelProperty(example = "1", required = true)
    @NotNull
    private Long idCidade;

    @Valid
    @NotNull
    private EstadoIdInput estado;
}
