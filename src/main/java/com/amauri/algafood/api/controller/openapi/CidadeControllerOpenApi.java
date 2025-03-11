package com.amauri.algafood.api.controller.openapi;

import com.amauri.algafood.api.exceptionhandler.Problem;
import com.amauri.algafood.api.model.CidadeModel;
import com.amauri.algafood.api.model.input.CidadeInput;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.List;

@Api(tags = "Cidades")
public interface CidadeControllerOpenApi {

    @ApiOperation("Lista as cidades")
    public List<CidadeModel> listar();

    @ApiOperation("Busca uma cidade por Id")
    @ApiResponses({@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400",
            description = "ID da cidade inválido", content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = Problem.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404",
                    description = "Cidade não encontrada", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Problem.class))),
    })
    public CidadeModel buscar(@ApiParam("ID de uma cidade") Long cidadeId);

    @ApiOperation("Cadastra uma cidade")
    @ApiResponses(@ApiResponse(responseCode = "201", description = "Cidade cadastrada"))
    public CidadeModel salvar(@ApiParam(name = "corpo", value = "Representação de uma nova cidade")CidadeInput cidadeInput);

    @ApiOperation("Atualiza uma cidade por id")
    @ApiResponses({@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
            description = "Cidade atualizada", content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = Problem.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404",
                    description = "Cidade não encontrada", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Problem.class))),
    })
    public CidadeModel atualizar(@ApiParam("ID de uma cidade") Long cidadeId, CidadeInput cidadeInput);

    @ApiOperation("Exclui uma cidade por id")
    @ApiResponses({@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "204",
            description = "Cidade excluida", content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = Problem.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404",
                    description = "Cidade não encontrada", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Problem.class))),
    })
    public void excluir(@ApiParam("ID de uma cidade") Long cidadeId);


}
