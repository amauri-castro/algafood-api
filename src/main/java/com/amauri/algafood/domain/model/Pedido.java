package com.amauri.algafood.domain.model;

import com.amauri.algafood.domain.enums.StatusPedidoEnum;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Pedido {

    @Column(nullable = false)
    private BigDecimal subtotal;

    @Column(nullable = false)
    private BigDecimal taxaFrete;

    @Column(nullable = false)
    private BigDecimal valorTotal;

    @Column(nullable = false)
    private LocalDateTime dataCriacao;

    private LocalDateTime dataConfirmacao;

    private LocalDateTime dataCancelamento;

    private LocalDateTime dataEntrega;

    @ManyToOne
    @JoinColumn(name = "forma_pagamento_id", nullable = false)
    private FormaPagamento formaPagamento;

    @ManyToOne
    @JoinColumn(name = "restaurante_id", nullable = false)
    private Restaurante restaurante;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusPedidoEnum status;

    @Embedded
    private Endereco endereco;
}
