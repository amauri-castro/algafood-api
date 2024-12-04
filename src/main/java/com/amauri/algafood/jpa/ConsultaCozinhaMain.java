package com.amauri.algafood.jpa;

import com.amauri.algafood.AlgafoodApiApplication;
import com.amauri.algafood.domain.model.Cozinha;
import com.amauri.algafood.domain.repository.CozinhaRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.util.List;

public class ConsultaCozinhaMain {

    public static void main(String[] args) {
        ApplicationContext context = new SpringApplicationBuilder(AlgafoodApiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        CozinhaRepository cozinhas = context.getBean(CozinhaRepository.class);
        List<Cozinha> todasCozinhas = cozinhas.listar();

        todasCozinhas.forEach(System.out::println);
    }
}
