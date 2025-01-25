package com.amauri.algafood;

import com.amauri.algafood.domain.model.Cozinha;
import com.amauri.algafood.domain.model.Restaurante;
import com.amauri.algafood.domain.repository.CozinhaRepository;
import com.amauri.algafood.domain.repository.RestauranteRepository;
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

import java.math.BigDecimal;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
class CadastroRestauranteIT {

	private static final String VIOLACAO_DE_REGRA_DE_NEGOCIO_PROBLEM_TYPE = "Violação de regra de negócio";
	private static final String DADOS_INVALIDOS_PROBLEM_TITLE = "Dados inválidos";
	public static final int RESTAURANTE_ID_INEXISTENTE = 200;

	@LocalServerPort
	private int port;

	@Autowired
	private DatabaseCleaner databaseCleaner;

	@Autowired
	CozinhaRepository cozinhaRepository;

	@Autowired
	RestauranteRepository restauranteRepository;

	private String jsonRestauranteCorreto;
	private String jsonRestauranteCozinhaInexistente;
	private String jsonRestauranteSemCozinha;
	private String jsonRestauranteSemFrete;

	private Restaurante burgerTopRestaurante;


	@BeforeEach
	public void setUp() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port = port;
		RestAssured.basePath = "/restaurantes";

		jsonRestauranteCorreto = ResourceUtils.lerArquivoDoResource(
				"/json/correto/restaurante-bode.json");
		jsonRestauranteCozinhaInexistente = ResourceUtils.lerArquivoDoResource(
				"/json/incorreto/restaurante-bode-com-cozinha-inexistente.json");
		jsonRestauranteSemCozinha = ResourceUtils.lerArquivoDoResource(
				"/json/incorreto/restaurante-bode-sem-cozinha.json");
		jsonRestauranteSemFrete = ResourceUtils.lerArquivoDoResource(
				"/json/incorreto/restaurante-bode-sem-frete.json");

		databaseCleaner.clearTables();
		prepararDados();
	}

	@Test
	public void deveRetornarStatus200_QuantoConsultarRestaurantes() {


		RestAssured.given()
				.accept(ContentType.JSON)
				.when()
				.get()
				.then()
				.statusCode(HttpStatus.OK.value());
	}

	@Test
	public void deveRetornarStatus201_QuandoCadastrarRestaurante() {
		RestAssured.given()
				.body(jsonRestauranteCorreto)
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.when()
				.post()
				.then()
				.statusCode(HttpStatus.CREATED.value());
	}

	@Test
	public void deveRetornarStatus400_QuandoCadastrarRestauranteComCozinhaInexistente() {
		RestAssured.given()
				.body(jsonRestauranteCozinhaInexistente)
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.when()
				.post()
				.then()
				.statusCode(HttpStatus.BAD_REQUEST.value())
				.body("title", Matchers.equalTo(VIOLACAO_DE_REGRA_DE_NEGOCIO_PROBLEM_TYPE));
	}

	@Test
	public void deveRetornarStatus400_QuandoCadastrarRestauranteSemCozinha() {
		RestAssured.given()
				.body(jsonRestauranteSemCozinha)
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.when()
				.post()
				.then()
				.statusCode(HttpStatus.BAD_REQUEST.value())
				.body("title", Matchers.equalTo(DADOS_INVALIDOS_PROBLEM_TITLE));
	}

	@Test
	public void deveRetornarStatus400_QuandoCadastrarRestauranteSemTaxaFrete() {
		RestAssured.given()
				.body(jsonRestauranteSemFrete)
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.when()
				.post()
				.then()
				.statusCode(HttpStatus.BAD_REQUEST.value())
				.body("title", Matchers.equalTo(DADOS_INVALIDOS_PROBLEM_TITLE));
	}

	@Test
	public void deveRetornarRepostaEStatusCorretos_QuandoConsultarRestauranteExistente() {
		RestAssured.given()
				.pathParams("restauranteId", burgerTopRestaurante.getId())
				.accept(ContentType.JSON)
				.when()
				.get("/{restauranteId}")
				.then()
				.statusCode(HttpStatus.OK.value())
				.body("nome", Matchers.equalTo(burgerTopRestaurante.getNome()));
	}

	@Test
	public void deveRetornarStatus404_QuandoConsultarRestauranteInexistente() {
		RestAssured.given()
				.pathParams("restauranteId", RESTAURANTE_ID_INEXISTENTE)
				.accept(ContentType.JSON)
				.when()
				.get("/{restauranteId}")
				.then()
				.statusCode(HttpStatus.NOT_FOUND.value());
	}

	private void prepararDados() {
		Cozinha cozinhaBrasileira = new Cozinha();
		cozinhaBrasileira.setNome("Brasileira");
		cozinhaRepository.save(cozinhaBrasileira);

		Cozinha cozinhaAmericana = new Cozinha();
		cozinhaAmericana.setNome("Americana");
		cozinhaRepository.save(cozinhaAmericana);

		burgerTopRestaurante = new Restaurante();
		burgerTopRestaurante.setNome("Burger Top");
		burgerTopRestaurante.setTaxaFrete(new BigDecimal(10));
		burgerTopRestaurante.setCozinha(cozinhaAmericana);
		restauranteRepository.save(burgerTopRestaurante);

		Restaurante comidaMineiraRestaurante = new Restaurante();
		comidaMineiraRestaurante.setNome("Comida Mineira");
		comidaMineiraRestaurante.setTaxaFrete(new BigDecimal(10));
		comidaMineiraRestaurante.setCozinha(cozinhaBrasileira);
		restauranteRepository.save(comidaMineiraRestaurante);
	}

}
