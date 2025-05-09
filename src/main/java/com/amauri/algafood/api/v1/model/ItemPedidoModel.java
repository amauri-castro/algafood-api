package com.amauri.algafood.api.v1.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.math.BigDecimal;

@Getter
@Setter
public class ItemPedidoModel extends RepresentationModel<ItemPedidoModel> {

    @Schema(example = "1")
    private Long produtoId;
    @Schema(example = "Pizza de Calabresa")
    private String produtoNome;
    @Schema(example = "2")
    private Integer quantidade;
    @Schema(example = "29.90")
    private BigDecimal precoUnitario;
    @Schema(example = "59.80")
    private BigDecimal precoTotal;
    @Schema(example = "Sem cebola")
    private String observacao;
}
