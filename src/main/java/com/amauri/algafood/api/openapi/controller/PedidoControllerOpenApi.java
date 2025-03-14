package com.amauri.algafood.api.openapi.controller;

import com.amauri.algafood.api.exceptionhandler.Problem;
import com.amauri.algafood.api.model.PedidoModel;
import com.amauri.algafood.api.model.PedidoResumoModel;
import com.amauri.algafood.api.model.input.PedidoInput;
import com.amauri.algafood.domain.filter.PedidoFilter;
import io.swagger.annotations.*;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;

@Api(tags = "Pedidos")
public interface PedidoControllerOpenApi {


    @ApiOperation("Pesquisa os pedidos")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "Nomes das propriedades para filtrar na resposta, separados por vírgula",
                    name = "campos", paramType = "query", type = "string")
    })
    PagedModel<PedidoResumoModel> pesquisar(PedidoFilter filtro,
                                            Pageable pageable);

    @ApiOperation("Busca um pedido por código")
    @ApiResponses({ @ApiResponse(responseCode = "404",
                    description = "Pedido não encontrado", content = @Content(
                    schema = @Schema(implementation = Problem.class))),
    })
    PedidoModel buscar(@ApiParam(value = "Código de um pedido",
            example = "f9981ca4-5a5e-4da3-af04-933861df3e55")
                       String codigoPedido);

    @ApiOperation("Registra um pedido")
    @ApiResponses(@ApiResponse(responseCode = "201", description = "Pedido registrado"))
    PedidoModel adicionar(@ApiParam(name = "corpo", value = "Representação de um novo pedido")
                          PedidoInput pedidoInput);

}
