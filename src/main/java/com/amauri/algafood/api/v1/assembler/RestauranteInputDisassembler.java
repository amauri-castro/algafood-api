package com.amauri.algafood.api.v1.assembler;

import com.amauri.algafood.api.v1.model.input.RestauranteInput;
import com.amauri.algafood.domain.model.Cozinha;
import com.amauri.algafood.domain.model.Endereco;
import com.amauri.algafood.domain.model.Restaurante;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RestauranteInputDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public Restaurante toDomainObject(RestauranteInput restauranteInput) {
        return modelMapper.map(restauranteInput, Restaurante.class);
    }

    public void copyToDomainObject(RestauranteInput restauranteInput, Restaurante restaurante) {
        // nova instancia de cozinha, para limpar referencia a antiga cozinha
        // para evitar org.hibernate.HibernateException: identifier of an instance of
        // com.amauri.algafood.domain.model.Cozinha was altered from 1 to 2
        restaurante.setCozinha(new Cozinha());
        if(restaurante.getEndereco() != null) {
            restaurante.setEndereco(new Endereco());
        }

        modelMapper.map(restauranteInput, restaurante);
    }
}
