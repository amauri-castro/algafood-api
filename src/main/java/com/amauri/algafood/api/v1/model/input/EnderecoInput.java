package com.amauri.algafood.api.v1.model.input;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Getter
@Setter
public class EnderecoInput {

    @Schema(example = "88110-000")
    @NotBlank
    private String cep;

    @Schema(example = "Rua dos Pinheiros")
    @NotBlank
    private String logradouro;

    @Schema(example = "301")
    @NotBlank
    private String numero;

    @Schema(example = "Apto 301")
    private String complemento;
    @NotBlank

    @Schema(example = "Jardim")
    private String bairro;

    @Schema(example = "Florianópolis")
    @Valid
    @NotNull
    private CidadeIdInput cidade;

}
