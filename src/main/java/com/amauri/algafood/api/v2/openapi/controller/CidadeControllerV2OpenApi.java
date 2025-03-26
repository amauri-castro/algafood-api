package com.amauri.algafood.api.v2.openapi.controller;

import com.amauri.algafood.api.exceptionhandler.Problem;
import com.amauri.algafood.api.v2.model.CidadeModelV2;
import com.amauri.algafood.api.v2.model.input.CidadeInputV2;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.hateoas.CollectionModel;

@Api(tags = "Cidades")
public interface CidadeControllerV2OpenApi {

    @ApiOperation("Lista as cidades")
    CollectionModel<CidadeModelV2> listar();

    @ApiOperation("Busca uma cidade por Id")
    @ApiResponses({@ApiResponse(responseCode = "400",
            description = "ID da cidade inválido", content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = Problem.class))),
            @ApiResponse(responseCode = "404",
                    description = "Cidade não encontrada", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Problem.class))),
    })
    CidadeModelV2 buscar(@ApiParam("ID de uma cidade") Long cidadeId);

    @ApiOperation("Cadastra uma cidade")
    @ApiResponses(@ApiResponse(responseCode = "201", description = "Cidade cadastrada"))
    CidadeModelV2 salvar(@ApiParam(name = "corpo", value = "Representação de uma nova cidade") CidadeInputV2 cidadeInput);

    @ApiOperation("Atualiza uma cidade por id")
    @ApiResponses({@ApiResponse(responseCode = "200",
            description = "Cidade atualizada", content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = Problem.class))),
            @ApiResponse(responseCode = "404",
                    description = "Cidade não encontrada", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Problem.class))),
    })
    CidadeModelV2 atualizar(@ApiParam("ID de uma cidade") Long cidadeId, CidadeInputV2 cidadeInput);


    @ApiOperation("Exclui uma cidade por id")
    @ApiResponses({@ApiResponse(responseCode = "204",
            description = "Cidade excluida", content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = Problem.class))),
            @ApiResponse(responseCode = "404",
                    description = "Cidade não encontrada", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Problem.class))),
    })
    void excluir(@ApiParam("ID de uma cidade") Long cidadeId);


}
