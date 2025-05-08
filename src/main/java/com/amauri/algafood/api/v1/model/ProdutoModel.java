package com.amauri.algafood.api.v1.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.math.BigDecimal;

@Relation(collectionRelation = "produtos")
@Getter
@Setter
public class ProdutoModel  extends RepresentationModel<ProdutoModel> {

    @Schema(example = "1")
    private Long id;
    @Schema(example = "Bauru com ovo")
    private String nome;
    @Schema(example = "Pão, presunto, queijo e ovo")
    private String descricao;
    @Schema(example = "12.50")
    private BigDecimal preco;
    @Schema(example = "true")
    private Boolean ativo;

}
