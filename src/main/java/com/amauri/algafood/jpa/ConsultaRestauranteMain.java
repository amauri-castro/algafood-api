package com.amauri.algafood.jpa;

import com.amauri.algafood.AlgafoodApiApplication;
import com.amauri.algafood.domain.model.Cozinha;
import com.amauri.algafood.domain.model.Restaurante;
import com.amauri.algafood.domain.repository.CozinhaRepository;
import com.amauri.algafood.infrastructure.repository.RestauranteRepositoryImpl;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.util.List;

public class ConsultaRestauranteMain {

    public static void main(String[] args) {
        ApplicationContext context = new SpringApplicationBuilder(AlgafoodApiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        RestauranteRepositoryImpl restaurantes = context.getBean(RestauranteRepositoryImpl.class);
        List<Restaurante> todosRestaurantes = restaurantes.todos();

        todosRestaurantes.forEach(System.out::println);
    }
}
