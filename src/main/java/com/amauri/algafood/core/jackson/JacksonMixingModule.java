package com.amauri.algafood.core.jackson;

import com.amauri.algafood.domain.model.Cidade;
import com.amauri.algafood.domain.model.Cozinha;
import com.amauri.algafood.domain.model.Restaurante;
import com.amauri.algafood.api.model.mixing.CidadeMixing;
import com.amauri.algafood.api.model.mixing.CozinhaMixing;
import com.amauri.algafood.api.model.mixing.RestauranteMixing;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.stereotype.Component;

@Component
public class JacksonMixingModule extends SimpleModule {

    public JacksonMixingModule() {
        setMixInAnnotation(Restaurante.class, RestauranteMixing.class);
        setMixInAnnotation(Cidade.class, CidadeMixing.class);
        setMixInAnnotation(Cozinha.class, CozinhaMixing.class);
    }
}
