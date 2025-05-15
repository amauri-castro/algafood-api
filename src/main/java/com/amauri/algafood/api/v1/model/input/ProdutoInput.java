package com.amauri.algafood.api.v1.model.input;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

@Getter
@Setter
public class ProdutoInput {

    @Schema(example = "Bauru com ovo")
    @NotBlank
    private String nome;

    @Schema(example = "Pão, presunto, queijo e ovo")
    @NotBlank
    private String descricao;

    @Schema(example = "12.50")
    @NotNull
    @PositiveOrZero
    private BigDecimal preco;

    @Schema(example = "true")
    @NotNull
    private Boolean ativo;
}
