package com.amauri.algafood.domain.service;

import com.amauri.algafood.domain.exception.RestauranteNaoEncontradoException;
import com.amauri.algafood.domain.model.Cozinha;
import com.amauri.algafood.domain.model.Restaurante;
import com.amauri.algafood.domain.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CadastroRestauranteService {

    @Autowired
    private RestauranteRepository restauranteRepository;


    @Autowired
    private CadastroCozinhaService cadastroCozinha;

    public Restaurante buscarOuFalhar(Long restauranteId) {
        return restauranteRepository.findById(restauranteId)
                .orElseThrow(() -> new RestauranteNaoEncontradoException(restauranteId));
    }

    public Restaurante salvar(Restaurante restaurante) {
        Long cozinhaId = restaurante.getCozinha().getId();
        Cozinha cozinha = cadastroCozinha.buscarOuFalhar(cozinhaId);

        restaurante.setCozinha(cozinha);
        return restauranteRepository.save(restaurante);
    }

}
