package com.amauri.algafood.api.assembler;

import com.amauri.algafood.api.model.input.RestauranteInput;
import com.amauri.algafood.domain.model.Restaurante;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RestauranteInputDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public Restaurante toDomainModel (RestauranteInput restauranteInput) {
        return modelMapper.map(restauranteInput, Restaurante.class);
    }
}
