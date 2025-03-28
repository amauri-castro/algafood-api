package com.amauri.algafood.api.v1.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.math.BigDecimal;

@Getter
@Setter
public class ItemPedidoModel extends RepresentationModel<ItemPedidoModel> {

    @ApiModelProperty(example = "1")
    private Long produtoId;
    @ApiModelProperty(example = "Frango ao molho")
    private String produtoNome;
    @ApiModelProperty(example = "2")
    private Integer quantidade;
    @ApiModelProperty(example = "85.90")
    private BigDecimal precoUnitario;
    @ApiModelProperty(example = "151.60")
    private BigDecimal precoTotal;
    @ApiModelProperty(example = "Menos picante")
    private String observacao;
}
