package com.amauri.algafood;

import com.amauri.algafood.domain.exception.EntidadeEmUsoException;
import com.amauri.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.amauri.algafood.domain.model.Cozinha;
import com.amauri.algafood.domain.service.CadastroCozinhaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolationException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CadastroCozinhaIT {

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
	public void deveFalhar_QuandoCadastrarCozinhaSemNome() {
		Cozinha novaCozinha = new Cozinha();
		novaCozinha.setNome(null);


		ConstraintViolationException erroEsperado = assertThrows(ConstraintViolationException.class, () -> cadastroCozinha.salvar(novaCozinha));
		assertThat(erroEsperado).isNotNull();
	}

	@Test
	public void deveFalhar_QuantoExcluirCozinhaEmUso() {
		Long cozinhaId = 1L;
		EntidadeEmUsoException erroEsperado = assertThrows(EntidadeEmUsoException.class, () -> cadastroCozinha.excluir(cozinhaId));
		assertThat(erroEsperado).isNotNull();

	}

	@Test
	public void deveFalhar_QuantoExcluirCozinhaInexistente() {
		Long cozinhaId = 10L;
		EntidadeNaoEncontradaException erroEsperado = assertThrows(EntidadeNaoEncontradaException.class, () -> cadastroCozinha.excluir(cozinhaId));
		assertThat(erroEsperado).isNotNull();

	}

}
