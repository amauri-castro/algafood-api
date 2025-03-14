package com.amauri.algafood.api.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.math.BigDecimal;

@Relation(collectionRelation = "restaurantes")
@Setter
@Getter
public class RestauranteResumoModel extends RepresentationModel<RestauranteResumoModel> {

    @ApiModelProperty(example = "1")
    private Long id;
    @ApiModelProperty(example = "Rodizio Nordestino")
    private String nome;
}
