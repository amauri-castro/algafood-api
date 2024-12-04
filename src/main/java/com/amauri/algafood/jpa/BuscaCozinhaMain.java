package com.amauri.algafood.jpa;

import com.amauri.algafood.AlgafoodApiApplication;
import com.amauri.algafood.domain.model.Cozinha;
import com.amauri.algafood.domain.repository.CozinhaRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

public class BuscaCozinhaMain {

    public static void main(String[] args) {
        ApplicationContext context = new SpringApplicationBuilder(AlgafoodApiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        CozinhaRepository cozinhas = context.getBean(CozinhaRepository.class);
        Cozinha cozinha = cozinhas.porId(2L);

        System.out.println(cozinha);
    }
}