package com.amauri.algafood.api.model.mixing;

import com.amauri.algafood.domain.model.Restaurante;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public abstract class CozinhaMixing {

    @JsonIgnore
    private List<Restaurante> restaurantes;
}
