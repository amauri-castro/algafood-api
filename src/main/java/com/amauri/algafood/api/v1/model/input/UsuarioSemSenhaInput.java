package com.amauri.algafood.api.v1.model.input;

import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Getter
@Setter
public class UsuarioSemSenhaInput {

    @NotBlank
    private String nome;
    @Email
    private String email;
}
