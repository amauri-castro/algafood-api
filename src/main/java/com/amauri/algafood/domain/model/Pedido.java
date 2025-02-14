package com.amauri.algafood.domain.model;

import com.amauri.algafood.domain.enums.StatusPedidoEnum;
import com.amauri.algafood.domain.exception.NegocioException;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
@ToString(onlyExplicitlyIncluded = true)
@Entity
public class Pedido {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String codigo;

    private BigDecimal subtotal;
    private BigDecimal taxaFrete;
    private BigDecimal valorTotal;

    @ManyToOne
    @JoinColumn(name = "restaurante_id", nullable = false)
    private Restaurante restaurante;

    @ManyToOne
    @JoinColumn(name = "usuario_cliente_id", nullable = false)
    private Usuario cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "forma_pagamento_id", nullable = false)
    private FormaPagamento formaPagamento;

    @Embedded
    private Endereco enderecoEntrega;

    @Enumerated(EnumType.STRING)
    private StatusPedidoEnum status = StatusPedidoEnum.CRIADO;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<ItemPedido> itens = new ArrayList<>();

    @CreationTimestamp
    private OffsetDateTime dataCriacao;

    private OffsetDateTime dataConfirmacao;
    private OffsetDateTime dataCancelamento;
    private OffsetDateTime dataEntrega;

    public void calcularValorTotal() {
        getItens().forEach(ItemPedido::calcularPrecoTotal);

        this.subtotal = getItens()
                .stream()
                .map(ItemPedido::getPrecoTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        this.valorTotal = this.subtotal.add(this.taxaFrete);
    }

    public void confirmar() {
        setStatus(StatusPedidoEnum.CONFIRMADO);
        setDataConfirmacao(OffsetDateTime.now());
    }

    public void entregar() {
        setStatus(StatusPedidoEnum.ENTREGUE);
        setDataEntrega(OffsetDateTime.now());
    }

    public void cancelar() {
        setStatus(StatusPedidoEnum.CANCELADO);
        setDataCancelamento(OffsetDateTime.now());
    }

    private void setStatus(StatusPedidoEnum novoStatus) {
        if (getStatus().naoPodeAlterarPara(novoStatus)) {
            throw new NegocioException(String.format("Status do pedido %s não pode ser " +
                            "alterado de %s para %s", getCodigo(),
                    getStatus().getDescricao(), novoStatus.getDescricao()));
        }
        this.status = novoStatus;
    }

    @PrePersist
    private void setCodigo() {
        setCodigo(UUID.randomUUID().toString());
    }

}
