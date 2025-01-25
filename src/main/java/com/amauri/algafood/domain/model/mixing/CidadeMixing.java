package com.amauri.algafood.domain.model.mixing;

import com.amauri.algafood.domain.model.Estado;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public abstract class CidadeMixing {

    @JsonIgnoreProperties(value = "nome", allowGetters = true)
    private Estado estado;
}
