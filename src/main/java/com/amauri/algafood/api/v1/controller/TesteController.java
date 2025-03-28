package com.amauri.algafood.api.v1.controller;

import com.amauri.algafood.domain.model.Cozinha;
import com.amauri.algafood.domain.model.Restaurante;
import com.amauri.algafood.domain.repository.CozinhaRepository;
import com.amauri.algafood.domain.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.amauri.algafood.infrastructure.repository.spec.RestauranteSpecs.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

//@RestController
//@RequestMapping("/teste")
public class TesteController {

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @Autowired
    private RestauranteRepository restauranteRepository;

    @GetMapping("/cozinhas/por-nome")
    public List<Cozinha> cozinhasPorNome(@RequestParam("nome") String nome) {
        return cozinhaRepository.findTodasByNomeContaining(nome);
    }

    @GetMapping("/cozinhas/primeira")
    public Optional<Cozinha> primeira() {
        return cozinhaRepository.buscarPrimeiro();
    }

    @GetMapping("/cozinhas/unico-por-nome")
    public Optional<Cozinha> cozinhasPorUnicNome(@RequestParam("nome") String nome) {
        return cozinhaRepository.findByNome(nome);
    }

    @GetMapping("/cozinhas/exists")
    public boolean existePorNome(@RequestParam("nome") String nome) {
        return cozinhaRepository.existsByNome(nome);
    }

    @GetMapping("/restaurantes/por-taxa-frete")
    public List<Restaurante> restaurantesPorTaxaFrete(BigDecimal taxaInicial, BigDecimal taxaFina) {
        return restauranteRepository.queryByTaxaFreteBetween(taxaInicial, taxaFina);
    }

    @GetMapping("/restaurantes/por-nome-e-frete")
    public List<Restaurante> restaurantesPorNomeEFrete(String  nome, BigDecimal taxaInicial, BigDecimal taxaFinal) {
        return restauranteRepository.consultar(nome, taxaInicial, taxaFinal);
    }

    @GetMapping("/restaurantes/com-frete-gratis")
    public List<Restaurante> restaurantesPorNomeEFrete(String  nome) {
        return restauranteRepository.findAll(comFreteGratis()
                .and(comNomeSemelhante(nome)));
    }

    @GetMapping("/restaurantes/primeiro")
    public Optional<Restaurante> restaurantesPrimeiro() {

        return restauranteRepository.buscarPrimeiro();
    }

    @GetMapping("/restaurantes/por-nome")
    public List<Restaurante> restaurantesPorNome(String  nome, Long cozinhaId) {
        return restauranteRepository.consultarPorNome(nome, cozinhaId);
    }


    @GetMapping("/restaurantes/primeiro-por-nome")
    public Optional<Restaurante> restaurantesPrimeiroPorNome(String  nome) {
        return restauranteRepository.findFirstByNomeContaining(nome);
    }

    @GetMapping("/restaurantes/top2-por-nome")
    public List<Restaurante> restaurantesTop2PorNome(String  nome) {
        return restauranteRepository.findTop2ByNomeContaining(nome);
    }


    @GetMapping("/restaurantes/por-cozinha")
    public int restaurantesPorCozinha(Long  id) {
        return restauranteRepository.countByCozinhaId(id);
    }
}
