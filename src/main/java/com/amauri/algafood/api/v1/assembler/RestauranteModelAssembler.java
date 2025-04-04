package com.amauri.algafood.api.v1.assembler;

import com.amauri.algafood.api.v1.AlgaLinks;
import com.amauri.algafood.api.v1.controller.RestauranteController;
import com.amauri.algafood.api.v1.model.RestauranteModel;
import com.amauri.algafood.domain.model.Restaurante;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class RestauranteModelAssembler extends RepresentationModelAssemblerSupport<Restaurante, RestauranteModel> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AlgaLinks algaLinks;

    public RestauranteModelAssembler() {
        super(RestauranteController.class, RestauranteModel.class);
    }

    @Override
    public RestauranteModel toModel(Restaurante restaurante) {
        RestauranteModel restauranteModel = createModelWithId(restaurante.getId(), restaurante);
        modelMapper.map(restaurante, restauranteModel);

        restauranteModel.add(algaLinks.linkToRestaurantes("restaurantes"));

        restauranteModel.getCozinha()
                .add(algaLinks.linkToCozinha(restaurante.getCozinha().getId()));

        restauranteModel.getEndereco()
                .getCidade().
                add(algaLinks.linkToCidade(restaurante.getEndereco().getCidade().getId()));

        if (restaurante.ativacaoPermitida()) {
            restauranteModel.add(
                    algaLinks.linkToRestauranteAtivacao(restaurante.getId(), "ativar"));
        }

        if (restaurante.inativacaoPermitida()) {
            restauranteModel.add(
                    algaLinks.linkToRestauranteInativacao(restaurante.getId(), "inativar"));
        }

        if (restaurante.aberturaPermitida()) {
            restauranteModel.add(
                    algaLinks.linkToRestauranteAbertura(restaurante.getId(), "abrir"));
        }

        if (restaurante.fechamentoPermitido()) {
            restauranteModel.add(
                    algaLinks.linkToRestauranteFechamento(restaurante.getId(), "fechar"));
        }

        restauranteModel.add(algaLinks.linkToProdutos(restaurante.getId(), "produtos"));

        if (restauranteModel.getEndereco() != null
                && restauranteModel.getEndereco().getCidade() != null) {
            restauranteModel.getEndereco().getCidade().add(
                    algaLinks.linkToCidade(restaurante.getEndereco().getCidade().getId()));
        }

        restauranteModel.add(algaLinks.linkToRestauranteFormasPagamento(restaurante.getId(),
                "formas-pagamento"));

        restauranteModel.add(algaLinks.linkToResponsaveisRestaurante(restaurante.getId(),
                "responsaveis"));

        return restauranteModel;
    }
}
