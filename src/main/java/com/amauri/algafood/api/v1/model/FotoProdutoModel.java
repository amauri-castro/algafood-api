package com.amauri.algafood.api.v1.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "fotos")
@Setter
@Getter
public class FotoProdutoModel extends RepresentationModel<FotoProdutoModel> {

    @Schema(example = "foto-produto-1.jpg")
    private String nomeArquivo;
    @Schema(example = "Hamburguer com queijo")
    private String descricao;
    @Schema(example = "image/jpeg")
    private String contentType;
    @Schema(example = "1500")
    private Long tamanho;
}
