package com.amauri.algafood.api.v1.assembler;

import com.amauri.algafood.api.v1.AlgaLinks;
import com.amauri.algafood.api.v1.controller.PedidoController;
import com.amauri.algafood.api.v1.model.PedidoModel;
import com.amauri.algafood.core.security.AlgaSecurity;
import com.amauri.algafood.domain.model.Pedido;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class PedidoModelAssembler extends RepresentationModelAssemblerSupport<Pedido, PedidoModel> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AlgaLinks algaLinks;

    @Autowired
    private AlgaSecurity algaSecurity;

    public PedidoModelAssembler() {
        super(PedidoController.class, PedidoModel.class);
    }

    @Override
    public PedidoModel toModel(Pedido pedido) {
        PedidoModel pedidoModel = createModelWithId(pedido.getId(), pedido);
        modelMapper.map(pedido, pedidoModel);

        if (algaSecurity.podePesquisarPedidos()) {
            pedidoModel.add(algaLinks.linkToPedidos("pedidos"));
        }

        if(algaSecurity.podeGerenciarPedidos(pedidoModel.getCodigo())) {

            if(pedido.podeSerConfirmado()) {
                pedidoModel.add(algaLinks.linkToConfirmacaoPedido(pedido.getCodigo(), "confirmar"));
            }

            if(pedido.podeSerCancelado()) {
                pedidoModel.add(algaLinks.linkToCancelamentoPedido(pedido.getCodigo(), "cancelar"));
            }

            if(pedido.podeSerEntregue()) {
                pedidoModel.add(algaLinks.linkToEntregaPedido(pedido.getCodigo(), "entregar"));
            }
        }

        if (algaSecurity.podeConsultarRestaurantes()) {
            pedidoModel.getRestaurante().add(
                    algaLinks.linkToRestaurante(pedido.getRestaurante().getId()));
        }

        if (algaSecurity.podeConsultarUsuariosGruposPermissoes()) {
            pedidoModel.getCliente().add(
                    algaLinks.linkToUsuario(pedido.getCliente().getId()));
        }

        if (algaSecurity.podeConsultarFormasPagamento()) {
            pedidoModel.getFormaPagamento().add(
                    algaLinks.linkToFormaPagamento(pedido.getFormaPagamento().getId()));
        }

        if (algaSecurity.podeConsultarCidades()) {
            pedidoModel.getEnderecoEntrega().getCidade().add(
                    algaLinks.linkToCidade(pedido.getEnderecoEntrega().getCidade().getId()));
        }

        if (algaSecurity.podeConsultarRestaurantes()) {
            pedidoModel.getItens().forEach(item -> {
                item.add(algaLinks.linkToProduto(
                        pedidoModel.getRestaurante().getId(), item.getProdutoId(), "produto"));
            });
        }

        return pedidoModel;
    }

}
