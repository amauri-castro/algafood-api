package com.amauri.algafood.api.openapi.controller;

import com.amauri.algafood.api.exceptionhandler.Problem;
import com.amauri.algafood.api.model.RestauranteModel;
import com.amauri.algafood.api.model.input.RestauranteInput;
import com.amauri.algafood.api.model.view.RestauranteView;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.annotations.*;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Api(tags = "Pedidos")
public interface FluxoPedidoControllerOpenApi {

    @ApiOperation("Confirmação de pedido")
    @ApiResponses({@ApiResponse(responseCode = "204",
            description = "Pedido confirmado com sucesso",
            content = @Content(schema = @Schema(implementation = Problem.class))),
            @ApiResponse(responseCode = "404",
                    description = "Pedido não encontrado",
                    content = @Content(schema = @Schema(implementation = Problem.class)))
    })
    void confirmar(@ApiParam(value = "Código do pedido", example = "f9981ca4-5a5e-4da3-af04-933861df3e55", required = true)
               String codigoPedido);


    @ApiOperation("Cancelamento de pedido")
    @ApiResponses({@ApiResponse(responseCode = "204",
            description = "Pedido cancelado com sucesso",
            content = @Content(schema = @Schema(implementation = Problem.class))),
            @ApiResponse(responseCode = "404",
                    description = "Pedido não encontrado",
                    content = @Content(schema = @Schema(implementation = Problem.class)))
    })
    void cancelar(@ApiParam(value = "Código do pedido", example = "f9981ca4-5a5e-4da3-af04-933861df3e55", required = true)
                   String codigoPedido);

    @ApiOperation("Registrar entrega de pedido")
    @ApiResponses({@ApiResponse(responseCode = "204",
            description = "Entrega de pedido registrado com sucesso",
            content = @Content(schema = @Schema(implementation = Problem.class))),
            @ApiResponse(responseCode = "404",
                    description = "Pedido não encontrado",
                    content = @Content(schema = @Schema(implementation = Problem.class)))
    })
    void entregar(@ApiParam(value = "Código do pedido", example = "f9981ca4-5a5e-4da3-af04-933861df3e55", required = true)
                  String codigoPedido);


}
