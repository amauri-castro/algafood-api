package com.amauri.algafood.api.v1.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "usuarios")
@Getter
@Setter
public class UsuarioModel extends RepresentationModel<UsuarioModel> {

    @Schema(example = "1")
    private Long id;
    @Schema(example = "Amauri Castro")
    private String nome;
    @Schema(example = "amauricastro@gmail.com")
    private String email;
}
