package com.amauri.algafood.api.v1.assembler;

import com.amauri.algafood.api.v1.AlgaLinks;
import com.amauri.algafood.api.v1.controller.EstadoController;
import com.amauri.algafood.api.v1.model.EstadoModel;
import com.amauri.algafood.core.security.AlgaSecurity;
import com.amauri.algafood.domain.model.Estado;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Component
public class EstadoModelAssembler extends RepresentationModelAssemblerSupport<Estado, EstadoModel> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AlgaLinks algaLinks;

    @Autowired
    private AlgaSecurity algaSecurity;

    public EstadoModelAssembler() {
        super(EstadoController.class, EstadoModel.class);
    }

    @Override
    public EstadoModel toModel(Estado estado) {
        EstadoModel estadoModel = createModelWithId(estado.getId(), estado);
        modelMapper.map(estado, estadoModel);

        if (algaSecurity.podeConsultarEstados()) {
            estadoModel.add(algaLinks.linkToEstados("estados"));
        }

        return estadoModel;
    }

    @Override
    public CollectionModel<EstadoModel> toCollectionModel(Iterable<? extends Estado> entities) {
        CollectionModel<EstadoModel> collectionModel = super.toCollectionModel(entities);

        if (algaSecurity.podeConsultarEstados()) {
            collectionModel.add(algaLinks.linkToEstados());
        }

        return collectionModel;
    }
}
