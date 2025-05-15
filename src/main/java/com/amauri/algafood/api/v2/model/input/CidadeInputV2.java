package com.amauri.algafood.api.v2.model.input;

import com.amauri.algafood.api.v1.model.input.EstadoIdInput;
import lombok.Getter;
import lombok.Setter;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Setter
@Getter
public class CidadeInputV2 {

    @NotBlank
    private String nomeCidade;

    @NotNull
    private Long idCidade;

    @Valid
    @NotNull
    private EstadoIdInput estado;
}
