package com.amauri.algafood.domain.service;

import com.amauri.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.amauri.algafood.domain.model.Cozinha;
import com.amauri.algafood.domain.model.Restaurante;
import com.amauri.algafood.domain.repository.CozinhaRepository;
import com.amauri.algafood.domain.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CadastroRestauranteService {

    public static final String MSG_RESTAURANTE_NAO_ENCONTRADO = "Restaurante não encontrado com o código %d";
    @Autowired
    private RestauranteRepository restauranteRepository;


    @Autowired
    private CadastroCozinhaService cadastroCozinha;

    public Restaurante buscarOuFalhar(Long restauranteId) {
        return restauranteRepository.findById(restauranteId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                        String.format(MSG_RESTAURANTE_NAO_ENCONTRADO, restauranteId)
                ));
    }


    public Restaurante salvar(Restaurante restaurante) {
        Long cozinhaId = restaurante.getCozinha().getId();
        Cozinha cozinha = cadastroCozinha.buscarOuFalhar(cozinhaId);

        restaurante.setCozinha(cozinha);
        return restauranteRepository.save(restaurante);
    }

}
