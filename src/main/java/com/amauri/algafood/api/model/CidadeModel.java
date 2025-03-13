package com.amauri.algafood.api.model;

import com.amauri.algafood.domain.model.Estado;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

//@ApiModel(value = "Cidade", description = "Representa uma cidade")
@Relation(collectionRelation = "cidades")
@Setter
@Getter
public class CidadeModel extends RepresentationModel<CidadeModel> {

    @ApiModelProperty(value = "ID da cidade", example = "1")
    private Long id;

    @ApiModelProperty(example = "Teresina")
    private String nome;
    private EstadoModel estado;
}
