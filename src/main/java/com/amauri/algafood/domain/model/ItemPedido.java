package com.amauri.algafood.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import jakarta.persistence.*;
import java.math.BigDecimal;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
@ToString(onlyExplicitlyIncluded = true)
@Entity
public class ItemPedido {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer quantidade;
    private BigDecimal precoUnitario;
    private BigDecimal precoTotal;
    private String observacao;

    @ManyToOne
    @JoinColumn(nullable = false, name = "produto_id")
    private Produto produto;

    @ManyToOne
    @JoinColumn(nullable = false, name = "pedido_id")
    private Pedido pedido;

    public void calcularPrecoTotal() {
        BigDecimal precoUnitario = this.getPrecoUnitario();
        Integer quantidade = this.getQuantidade();
        if(precoUnitario == null) {
            precoUnitario = BigDecimal.ZERO;
        }
        if(quantidade == null) {
            quantidade = 0;
        }
        this.setPrecoTotal(precoUnitario.multiply(new BigDecimal(quantidade)));
    }
}
