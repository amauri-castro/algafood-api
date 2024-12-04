package com.amauri.algafood.jpa;

import com.amauri.algafood.AlgafoodApiApplication;
import com.amauri.algafood.domain.model.Cozinha;
import com.amauri.algafood.domain.repository.CozinhaRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

public class InclusaoCozinhaMain {

    public static void main(String[] args) {
        ApplicationContext context = new SpringApplicationBuilder(AlgafoodApiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        CozinhaRepository cozinhaRepository = context.getBean(CozinhaRepository.class);
        Cozinha cozinha1 = new Cozinha();
        cozinha1.setNome("Africana");

        cozinhaRepository.adicionar(cozinha1);

        Cozinha cozinha2 = new Cozinha();
        cozinha2.setNome("Nordestina");

        cozinhaRepository.adicionar(cozinha2);
    }
}
