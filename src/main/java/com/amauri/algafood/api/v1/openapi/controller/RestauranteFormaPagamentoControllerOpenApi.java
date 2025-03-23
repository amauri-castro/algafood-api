package com.amauri.algafood.api.v1.openapi.controller;

import com.amauri.algafood.api.exceptionhandler.Problem;
import com.amauri.algafood.api.v1.model.FormaPagamentoModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

@Api(tags = "Restaurantes")
public interface RestauranteFormaPagamentoControllerOpenApi {

    @ApiOperation("Lista as formas de pagamento associadas a restaurantes")
    @ApiResponses({@ApiResponse(responseCode = "404",
                    description = "Restaurante não encontrado",
                    content = @Content(schema = @Schema(implementation = Problem.class))),
    })
    CollectionModel<FormaPagamentoModel> listar(@ApiParam(value = "ID do restaurante", example = "1", required = true)
                                                Long restauranteId);


    @ApiOperation("Desassociação de restaurante com forma de pagamento")
    @ApiResponses({@ApiResponse(responseCode = "204",
            description = "Desassociação realizada com sucesso",
            content = @Content(schema = @Schema(implementation = Problem.class))),
            @ApiResponse(responseCode = "404",
            description = "Restaurante ou forma de pagamento não encontrado",
            content = @Content(schema = @Schema(implementation = Problem.class))),
    })
    ResponseEntity<Void> desvincular(@ApiParam(value = "ID do restaurante", example = "1", required = true) Long restauranteId,
                               @ApiParam(value = "ID da forma de pagamento", example = "1", required = true) Long formaPagamentoId);


    @ApiOperation("Associação de restaurante com forma de pagamento")
    @ApiResponses({@ApiResponse(responseCode = "204",
            description = "Associação realizada com sucesso",
            content = @Content(schema = @Schema(implementation = Problem.class))),
            @ApiResponse(responseCode = "404",
                    description = "Restaurante ou forma de pagamento não encontrado",
                    content = @Content(schema = @Schema(implementation = Problem.class))),
    })
    ResponseEntity<Void> vincular(@ApiParam(value = "ID do restaurante", example = "1", required = true) Long restauranteId,
                  @ApiParam(value = "ID da forma de pagamento", example = "1", required = true)Long formaPagamentoId);


}
