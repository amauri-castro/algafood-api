package com.amauri.algafood.core.jackson;

import com.amauri.algafood.api.model.mixing.CozinhaMixing;
import com.amauri.algafood.domain.model.Cidade;
import com.amauri.algafood.domain.model.Cozinha;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.stereotype.Component;

@Component
public class JacksonMixingModule extends SimpleModule {

    public JacksonMixingModule() {
        setMixInAnnotation(Cozinha.class, CozinhaMixing.class);
    }
}
