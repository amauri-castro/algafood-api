package com.amauri.algafood.api.openapi.controller;

import com.amauri.algafood.api.exceptionhandler.Problem;
import com.amauri.algafood.api.model.EstadoModel;
import com.amauri.algafood.api.model.input.EstadoInput;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.hateoas.CollectionModel;

@Api(tags = "Estados")
public interface EstadoControllerOpenApi {

    @ApiOperation("Lista os estados")
    CollectionModel<EstadoModel> listar();

    @ApiOperation("Busca um estado por Id")
    @ApiResponses({@ApiResponse(responseCode = "400",
            description = "ID do inválido",
            content = @Content(schema = @Schema(implementation = Problem.class))),
            @ApiResponse(responseCode = "404",
                    description = "Estado não encontrada",
                    content = @Content(schema = @Schema(implementation = Problem.class))),
    })
    EstadoModel buscar(@ApiParam(value = "ID de um estado", example = "1") Long estadoId);

    @ApiOperation("Cadastra um estado")
    @ApiResponses(@ApiResponse(responseCode = "201", description = "Estado cadastrado"))
    EstadoModel salvar(@ApiParam(name = "corpo", value = "Representação de um novo estado") EstadoInput estadoInput);

    @ApiOperation("Atualiza um estado por id")
    @ApiResponses({@ApiResponse(responseCode = "200",
            description = "Estado atualizado",
            content = @Content(schema = @Schema(implementation = Problem.class))),
            @ApiResponse(responseCode = "404",
                    description = "Estado não encontrado",
                    content = @Content(schema = @Schema(implementation = Problem.class))),
    })
    EstadoModel atualizar(@ApiParam("ID de um estado") Long estadoId, EstadoInput estadoInput);

    @ApiOperation("Exclui um estado por id")
    @ApiResponses({@ApiResponse(responseCode = "204",
            description = "Estado excluído",
            content = @Content(schema = @Schema(implementation = Problem.class))),
            @ApiResponse(responseCode = "404",
                    description = "Estado não encontrado",
                    content = @Content(schema = @Schema(implementation = Problem.class))),
    })
    void excluir(@ApiParam("ID de um estado") Long estadoId);

}
