package com.amauri.algafood.api.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@Relation(collectionRelation = "pedidos")
@Getter
@Setter
public class PedidoModel extends RepresentationModel<PedidoModel> {

    @ApiModelProperty(example = "f9981ca4-5a5e-4da3-af04-933861df3e55")
    private String codigo;
    @ApiModelProperty(example = "125.60")
    private BigDecimal subtotal;
    @ApiModelProperty(example = "10.0")
    private BigDecimal taxaFrete;
    @ApiModelProperty(example = "210.00")
    private BigDecimal valorTotal;
    @ApiModelProperty(example = "CRIADO")
    private String status;
    @ApiModelProperty(example = "2025-03-11T21:30:00Z")
    private OffsetDateTime dataCriacao;
    @ApiModelProperty(example = "2025-03-11T21:30:00Z")
    private OffsetDateTime dataConfirmacao;
    @ApiModelProperty(example = "2025-03-11T21:30:00Z")
    private OffsetDateTime dataEntrega;
    @ApiModelProperty(example = "2025-03-11T21:30:00Z")
    private OffsetDateTime dataCancelamento;

    private RestauranteResumoModel restaurante;
    private UsuarioModel cliente;
    private FormaPagamentoModel formaPagamento;
    private EnderecoModel enderecoEntrega;
    private List<ItemPedidoModel> itens;

}
