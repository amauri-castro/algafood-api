package com.amauri.algafood.api.v2.openapi.controller;

import com.amauri.algafood.api.exceptionhandler.Problem;
import com.amauri.algafood.api.v2.model.CozinhaModelV2;
import com.amauri.algafood.api.v2.model.input.CozinhaInputV2;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;

@Api(tags = "Cozinhas")
public interface CozinhaControllerV2OpenApi {

    @ApiOperation("Lista as cozinhas com paginação")
    PagedModel<CozinhaModelV2> listar(Pageable pageable);

    @ApiOperation("Busca uma cozinha por Id")
    @ApiResponses({@ApiResponse(responseCode = "400",
            description = "ID da cozinha inválido",
            content = @Content( schema = @Schema(implementation = Problem.class))),
            @ApiResponse(responseCode = "404", description = "Cozinha não encontrada",
                    content = @Content(schema = @Schema(implementation = Problem.class))),
    })
    CozinhaModelV2 buscar(@ApiParam("ID de uma cozinha") Long grupoId);

    @ApiOperation("Cadastra uma cozinha")
    @ApiResponses(@ApiResponse(responseCode = "201", description = "Cozinha cadastrada"))
    CozinhaModelV2 salvar(@ApiParam(name = "corpo", value = "Representação de uma nova cozinha") CozinhaInputV2 cozinhaInput);

    @ApiOperation("Atualiza uma cozinha por ID")
    @ApiResponses({@ApiResponse(responseCode = "200",
            description = "Cozinha atualizada", content = @Content(schema = @Schema(implementation = Problem.class))),
            @ApiResponse(responseCode = "404",description = "Cozinha não encontrada",
                    content = @Content(schema = @Schema(implementation = Problem.class))),
    })
    CozinhaModelV2 atualizar(@ApiParam("ID de uma cozinha") Long cozinhaId, CozinhaInputV2 cozinhaInput);


    @ApiOperation("Exclui uma cozinha por id")
    @ApiResponses({@ApiResponse(responseCode = "204",
            description = "Cozinha excluída", content = @Content(schema = @Schema(implementation = Problem.class))),
            @ApiResponse(responseCode = "404", description = "Cozinha não encontrada",
                    content = @Content(schema = @Schema(implementation = Problem.class))),
    })
    void excluir(@ApiParam(value = "ID de uma cozinha") Long cozinhaId);

}
