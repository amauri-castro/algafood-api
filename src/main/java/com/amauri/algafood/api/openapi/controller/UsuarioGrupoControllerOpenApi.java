package com.amauri.algafood.api.openapi.controller;

import com.amauri.algafood.api.exceptionhandler.Problem;
import com.amauri.algafood.api.model.GrupoModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.List;

@Api(tags = "Usuários")
public interface UsuarioGrupoControllerOpenApi {

    @ApiOperation("Lista os grupos associados a um usuário")
    @ApiResponses({@ApiResponse(responseCode = "404",
            description = "Usuário não encontrado",
            content = @Content(schema = @Schema(implementation = Problem.class)))
    })
    List<GrupoModel> listar(@ApiParam(value = "ID do usuário", example = "1", required = true)
                            Long usuarioId);

    @ApiOperation("Desassociação de grupo com usuário")
    @ApiResponses({@ApiResponse(responseCode = "204",
            description = "Desassociação realizada com sucesso",
            content = @Content(schema = @Schema(implementation = Problem.class))),
            @ApiResponse(responseCode = "404",
                    description = "Usuário ou grupo não encontrado",
                    content = @Content(schema = @Schema(implementation = Problem.class))),
    })
    void desvincular(@ApiParam(value = "ID do usuário", example = "1", required = true)
                     Long usuarioId,
                     @ApiParam(value = "ID do grupo", example = "1", required = true)
                     Long grupoId);

    @ApiOperation("Associação de grupo com usuário")
    @ApiResponses({@ApiResponse(responseCode = "204",
            description = "Associação realizada com sucesso",
            content = @Content(schema = @Schema(implementation = Problem.class))),
            @ApiResponse(responseCode = "404",
                    description = "Usuário ou grupo não encontrado",
                    content = @Content(schema = @Schema(implementation = Problem.class))),
    })
    void vincular(@ApiParam(value = "ID do usuário", example = "1", required = true)
                  Long usuarioId,
                  @ApiParam(value = "ID do grupo", example = "1", required = true)
                  Long grupoId);


}
