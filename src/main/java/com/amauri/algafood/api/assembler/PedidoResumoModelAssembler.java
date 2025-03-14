package com.amauri.algafood.api.assembler;

import com.amauri.algafood.api.controller.PedidoController;
import com.amauri.algafood.api.controller.RestauranteController;
import com.amauri.algafood.api.controller.UsuarioController;
import com.amauri.algafood.api.model.PedidoResumoModel;
import com.amauri.algafood.domain.model.Pedido;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class PedidoResumoModelAssembler extends RepresentationModelAssemblerSupport<Pedido, PedidoResumoModel> {

    @Autowired
    private ModelMapper modelMapper;

    public PedidoResumoModelAssembler() {
        super(PedidoController.class, PedidoResumoModel.class);
    }

    @Override
    public PedidoResumoModel toModel(Pedido pedido) {
        PedidoResumoModel pedidoResumoModel = createModelWithId(pedido.getId(), pedido);
        modelMapper.map(pedido, pedidoResumoModel);

        pedidoResumoModel.add(linkTo(PedidoController.class).withRel("pedidos"));

        pedidoResumoModel.getRestaurante().add(linkTo(methodOn(RestauranteController.class)
                .buscar(pedidoResumoModel.getRestaurante().getId())).withSelfRel());

        pedidoResumoModel.getCliente().add(linkTo(methodOn(UsuarioController.class)
                .buscar(pedidoResumoModel.getCliente().getId())).withSelfRel());

        return pedidoResumoModel;
    }

}
