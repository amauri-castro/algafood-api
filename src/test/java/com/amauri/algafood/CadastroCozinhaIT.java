package com.amauri.algafood;

import com.amauri.algafood.domain.model.Cozinha;
import com.amauri.algafood.domain.repository.CozinhaRepository;
import com.amauri.algafood.util.DatabaseCleaner;
import com.amauri.algafood.util.ResourceUtils;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
class CadastroCozinhaIT {

	public static final int ID_INEXISTENTE = 200;
	private int quantidadeCozinhas;
	private Cozinha cozinhaAmericana;
	private String jsonCorretoCozinhaChinesa;

	@LocalServerPort
	private int port;

	@Autowired
	private DatabaseCleaner databaseCleaner;

	@Autowired
	CozinhaRepository cozinhaRepository;




	@BeforeEach
	public void setUp() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port = port;
		RestAssured.basePath = "/cozinhas";

		jsonCorretoCozinhaChinesa = ResourceUtils.lerArquivoDoResource(
				"/json/correto/cozinha-chinesa.json");

		databaseCleaner.clearTables();
		prepararDados();
	}

	@Test
	public void deveRetornarStatus200_QuantoConsultarCozinhas() {


		RestAssured.given()
				.accept(ContentType.JSON)
				.when()
				.get()
				.then()
				.statusCode(HttpStatus.OK.value());
	}

	@Test
	public void deveRetornarQuantidadeCorretaDeCozinhas_QuantoConsultarCozinhas() {
		RestAssured.given()
				.accept(ContentType.JSON)
				.when()
				.get()
				.then()
				.body("", Matchers.hasSize(quantidadeCozinhas));
	}

	@Test
	public void deveRetornarStatus201_QuandoCadastrarCozinha() {
		RestAssured.given()
				.body(jsonCorretoCozinhaChinesa)
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.when()
				.post()
				.then()
				.statusCode(HttpStatus.CREATED.value());
	}

	@Test
	public void deveRetornarRepostaEStatusCorretos_QuandoConsultarCozinhaExistente() {
		RestAssured.given()
				.pathParams("cozinhaId", cozinhaAmericana.getId())
				.accept(ContentType.JSON)
				.when()
				.get("/{cozinhaId}")
				.then()
				.statusCode(HttpStatus.OK.value())
				.body("nome", Matchers.equalTo(cozinhaAmericana.getNome()));
	}

	@Test
	public void deveRetornarRepostaEStatus404_QuandoConsultarCozinhaExistente() {
		RestAssured.given()
				.pathParams("cozinhaId", ID_INEXISTENTE)
				.accept(ContentType.JSON)
				.when()
				.get("/{cozinhaId}")
				.then()
				.statusCode(HttpStatus.NOT_FOUND.value());
	}

	private void prepararDados() {
		Cozinha cozinhaTailandesa = new Cozinha();
		cozinhaTailandesa.setNome("Tailandesa");
		cozinhaRepository.save(cozinhaTailandesa);

		Cozinha cozinhaBrasileira = new Cozinha();
		cozinhaBrasileira.setNome("Brasileira");
		cozinhaRepository.save(cozinhaBrasileira);

		cozinhaAmericana = new Cozinha();
		cozinhaAmericana.setNome("Americana");
		cozinhaRepository.save(cozinhaAmericana);

		quantidadeCozinhas = (int) cozinhaRepository.count();
	}



//	@Test
//	public void testarCadastroCozinhaComSucesso() {
//		//cenario
//		Cozinha cozinha = new Cozinha();
//		cozinha.setNome("Sertaneja");
//		//ação
//		cozinha = cadastroCozinha.salvar(cozinha);
//
//		assertThat(cozinha).isNotNull();
//		assertThat(cozinha.getId()).isNotNull();
//
//
//	}
//
//	@Test
//	public void deveFalhar_QuandoCadastrarCozinhaSemNome() {
//		Cozinha novaCozinha = new Cozinha();
//		novaCozinha.setNome(null);
//
//
//		ConstraintViolationException erroEsperado = assertThrows(ConstraintViolationException.class, () -> cadastroCozinha.salvar(novaCozinha));
//		assertThat(erroEsperado).isNotNull();
//	}
//
//	@Test
//	public void deveFalhar_QuantoExcluirCozinhaEmUso() {
//		Long cozinhaId = 1L;
//		EntidadeEmUsoException erroEsperado = assertThrows(EntidadeEmUsoException.class, () -> cadastroCozinha.excluir(cozinhaId));
//		assertThat(erroEsperado).isNotNull();
//
//	}
//
//	@Test
//	public void deveFalhar_QuantoExcluirCozinhaInexistente() {
//		Long cozinhaId = 10L;
//		EntidadeNaoEncontradaException erroEsperado = assertThrows(EntidadeNaoEncontradaException.class, () -> cadastroCozinha.excluir(cozinhaId));
//		assertThat(erroEsperado).isNotNull();
//
//	}

}
