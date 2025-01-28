package com.amauri.algafood.api.model;

import com.amauri.algafood.domain.model.Estado;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CidadeModel {
    private Long id;
    private String nome;
    private EstadoModel estado;
}
