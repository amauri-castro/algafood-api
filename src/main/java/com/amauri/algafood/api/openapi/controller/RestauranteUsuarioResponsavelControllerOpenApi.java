package com.amauri.algafood.api.openapi.controller;

import com.amauri.algafood.api.exceptionhandler.Problem;
import com.amauri.algafood.api.model.UsuarioModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.hateoas.CollectionModel;

import java.util.List;

@Api(tags = "Restaurantes")
public interface RestauranteUsuarioResponsavelControllerOpenApi {

    @ApiOperation("Lista os usuários responsáveis associados a restaurante")
    @ApiResponses({@ApiResponse(responseCode = "404",
                    description = "Restaurante não encontrado",
                    content = @Content(schema = @Schema(implementation = Problem.class))),
    })
    CollectionModel<UsuarioModel> listar(@ApiParam(value = "ID do restaurante", example = "1", required = true)
                              Long restauranteId);


    @ApiOperation("Desassociação do restaurante com usuário responsável")
    @ApiResponses({@ApiResponse(responseCode = "204",
            description = "Desassociação realizada com sucesso",
            content = @Content(schema = @Schema(implementation = Problem.class))),
            @ApiResponse(responseCode = "404",
            description = "Restaurante ou usuário não encontrado",
            content = @Content(schema = @Schema(implementation = Problem.class))),
    })
    void desvincular(@ApiParam(value = "ID do restaurante", example = "1", required = true) Long restauranteId,
                     @ApiParam(value = "ID do usuário", example = "1", required = true) Long usuarioId);


    @ApiOperation("Associação de restaurante com usuário responsável")
    @ApiResponses({@ApiResponse(responseCode = "204",
            description = "Associação realizada com sucesso",
            content = @Content(schema = @Schema(implementation = Problem.class))),
            @ApiResponse(responseCode = "404",
                    description = "Restaurante ou usuário não encontrado",
                    content = @Content(schema = @Schema(implementation = Problem.class))),
    })
    void vincular(@ApiParam(value = "ID do restaurante", example = "1", required = true) Long restauranteId,
                  @ApiParam(value = "ID do usuário", example = "1", required = true)Long usuarioId);


}
