package com.amauri.algafood.api.v1.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Relation(collectionRelation = "pedidos")
@Getter
@Setter
public class PedidoResumoModel extends RepresentationModel<PedidoResumoModel> {

    @Schema(example = "045b7c4-0f8e-4a2d-a1b3-9f3c6e1a2d8f")
    private String codigo;
    @Schema(example = "298.90")
    private BigDecimal subtotal;
    @Schema(example = "10.00")
    private BigDecimal taxaFrete;
    @Schema(example = "308.90")
    private BigDecimal valorTotal;
    @Schema(example = "CRIADO")
    private String status;
    @Schema(example = "2023-10-01T10:00:00Z")
    private OffsetDateTime dataCriacao;

    private RestauranteResumoModel restaurante;
    private UsuarioModel cliente;

}
