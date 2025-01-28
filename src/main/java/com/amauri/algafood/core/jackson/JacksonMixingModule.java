package com.amauri.algafood.core.jackson;

import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.stereotype.Component;

@Component
public class JacksonMixingModule extends SimpleModule {

    public JacksonMixingModule() {
    }
}
