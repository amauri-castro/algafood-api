package com.amauri.algafood;

import com.amauri.algafood.domain.model.Cozinha;
import com.amauri.algafood.domain.service.CadastroCozinhaService;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolationException;

@SpringBootTest
class CadastroCozinhaIntegrationTests {

	@Autowired
	private CadastroCozinhaService cadastroCozinha;

	@Test
	public void testarCadastroCozinhaComSucesso() {
		//cenario
		Cozinha cozinha = new Cozinha();
		cozinha.setNome("Sertaneja");
		//ação
		cozinha = cadastroCozinha.salvar(cozinha);

		assertThat(cozinha).isNotNull();
		assertThat(cozinha.getId()).isNotNull();


	}

	@Test
	public void testarCadastroCozinhaSemNome() {
		Cozinha novaCozinha = new Cozinha();
		novaCozinha.setNome("Pereira Food");


		ConstraintViolationException erroEsperado = Assertions
				.assertThrows(ConstraintViolationException.class, () -> cadastroCozinha.salvar(novaCozinha));
		assertThat(erroEsperado).isNotNull();
	}

}
