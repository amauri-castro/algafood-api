package com.amauri.algafood.api.openapi.controller;

import com.amauri.algafood.api.exceptionhandler.Problem;
import com.amauri.algafood.api.model.CozinhaModel;
import com.amauri.algafood.api.model.input.CozinhaInput;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Api(tags = "Cozinhas")
public interface CozinhaControllerOpenApi {

    @ApiOperation("Lista as cozinhas com paginação")
    public Page<CozinhaModel> listar(Pageable pageable);

    @ApiOperation("Busca uma cozinha por Id")
    @ApiResponses({@ApiResponse(responseCode = "400",
            description = "ID da cozinha inválido",
            content = @Content( schema = @Schema(implementation = Problem.class))),
            @ApiResponse(responseCode = "404", description = "Cozinha não encontrada",
                    content = @Content(schema = @Schema(implementation = Problem.class))),
    })
    public CozinhaModel buscar(@ApiParam("ID de uma cozinha") Long grupoId);

    @ApiOperation("Cadastra uma cozinha")
    @ApiResponses(@ApiResponse(responseCode = "201", description = "Cozinha cadastrada"))
    public CozinhaModel salvar(@ApiParam(name = "corpo", value = "Representação de uma nova cozinha") CozinhaInput cozinhaInput);

    @ApiOperation("Atualiza uma cozinha por ID")
    @ApiResponses({@ApiResponse(responseCode = "200",
            description = "Cozinha atualizada", content = @Content(schema = @Schema(implementation = Problem.class))),
            @ApiResponse(responseCode = "404",description = "Cozinha não encontrada",
                    content = @Content(schema = @Schema(implementation = Problem.class))),
    })
    public CozinhaModel atualizar(@ApiParam("ID de uma cozinha") Long cozinhaId, CozinhaInput cozinhaInput);

    @ApiOperation("Exclui uma cozinha por id")
    @ApiResponses({@ApiResponse(responseCode = "204",
            description = "Cozinha excluída", content = @Content(schema = @Schema(implementation = Problem.class))),
            @ApiResponse(responseCode = "404", description = "Cozinha não encontrada",
                    content = @Content(schema = @Schema(implementation = Problem.class))),
    })
    public void excluir(@ApiParam(value = "ID de uma cozinha") Long cozinhaId);

}
