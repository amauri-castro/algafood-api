package com.amauri.algafood.api.v1.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "cidades")
@Setter
@Getter
public class CidadeResumoModel extends RepresentationModel<CidadeResumoModel> {

    @ApiModelProperty(example = "1")
    private Long id;
    @ApiModelProperty(example = "Teresina")
    private String nome;
    @ApiModelProperty(example = "Piauí")
    private String estado;
}
