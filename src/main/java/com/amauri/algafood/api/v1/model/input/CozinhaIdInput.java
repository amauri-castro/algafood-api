package com.amauri.algafood.api.v1.model.input;

import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.NotNull;

@Setter
@Getter
public class CozinhaIdInput {

    @NotNull
    private Long id;
}
