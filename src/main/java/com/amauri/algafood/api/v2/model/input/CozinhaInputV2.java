package com.amauri.algafood.api.v2.model.input;

import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.NotBlank;

@Getter
@Setter
public class CozinhaInputV2 {

    @NotBlank
    private String nomeCozinha;
}
