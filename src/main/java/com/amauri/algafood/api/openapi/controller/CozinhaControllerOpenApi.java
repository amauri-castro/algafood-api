package com.amauri.algafood.api.openapi.controller;

import com.amauri.algafood.api.exceptionhandler.Problem;
import com.amauri.algafood.api.model.GrupoModel;
import com.amauri.algafood.api.model.input.GrupoInput;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.List;

@Api(tags = "Grupos")
public interface GrupoControllerOpenApi {

    @ApiOperation("Lista os grupos")
    public List<GrupoModel> listar();

    @ApiOperation("Busca um grupo por Id")
    @ApiResponses({@ApiResponse(responseCode = "400",
            description = "ID do grupo inválido", content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = Problem.class))),
            @ApiResponse(responseCode = "404",
                    description = "Grupo não encontrado", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Problem.class))),
    })
    public GrupoModel buscar(@ApiParam("ID de um grupo") Long grupoId);

    @ApiOperation("Cadastra um grupo")
    @ApiResponses(@ApiResponse(responseCode = "201", description = "Grupo cadastrado"))
    public GrupoModel salvar(@ApiParam(name = "corpo", value = "Representação de um novo grupo") GrupoInput grupoInput);

    @ApiOperation("Atualiza um grupo por id")
    @ApiResponses({@ApiResponse(responseCode = "200",
            description = "Grupo atualizado", content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = Problem.class))),
            @ApiResponse(responseCode = "404",
                    description = "Grupo não encontrado", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Problem.class))),
    })
    public GrupoModel atualizar(@ApiParam("ID de um grupo") Long grupoId, GrupoInput grupoInput);

    @ApiOperation("Exclui um grupo por id")
    @ApiResponses({@ApiResponse(responseCode = "204",
            description = "Grupo excluído", content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = Problem.class))),
            @ApiResponse(responseCode = "404",
                    description = "Grupo não encontrado", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Problem.class))),
    })
    public void excluir(@ApiParam(value = "ID de um grupo") Long grupoId);


}
