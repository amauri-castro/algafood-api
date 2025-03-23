package com.amauri.algafood.api.v1.openapi.controller;

import com.amauri.algafood.api.exceptionhandler.Problem;
import com.amauri.algafood.api.v1.model.FotoProdutoModel;
import com.amauri.algafood.api.v1.model.input.FotoProdutoInput;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Api(tags = "Produtos")
public interface RestauranteProdutoFotoControllerOpenApi {

    @ApiOperation("Atualiza a foto do produto de um restaurante")
    @ApiResponses({@ApiResponse(responseCode = "200",
            description = "Foto do produto atualizada",
            content = @Content(schema = @Schema(implementation = Problem.class))),
            @ApiResponse(responseCode = "404", description = "Foto de produto não encontrada",
                    content = @Content(schema = @Schema(implementation = Problem.class))),
    })
    FotoProdutoModel atualizarFoto( @ApiParam(value = "ID do restaurante", example = "1", required = true) Long restauranteId,
                                    @ApiParam(value = "ID do produto", example = "1", required = true) Long produtoId,
                                    FotoProdutoInput fotoProdutoInput,
                                    @ApiParam(value = "Arquivo da foto do produto (máximo 1500KB, apenas JPG e PNG)", required = true)
                                    MultipartFile arquivo) throws IOException;

    @ApiOperation(value = "Busca a foto do produto de um restaurante")
    @ApiResponses({@ApiResponse(responseCode = "400",
            description = "ID do restaurante ou produto inválido",
            content = @Content( schema = @Schema(implementation = Problem.class))),
            @ApiResponse(responseCode = "404", description = "Foto de produto não encontrada",
                    content = @Content( schema = @Schema(implementation = Problem.class))),
    })
    FotoProdutoModel buscar(@ApiParam(value = "ID do restaurante", example = "1", required = true) Long restauranteId,
                            @ApiParam(value = "ID do produto", example = "1", required = true) Long produtoId);


    @ApiOperation(value = "Busca a foto do produto de um restaurante", hidden = true)
    ResponseEntity<?> servirFoto( Long restauranteId,
                                  Long produtoId,
                                  String acceptHeader) throws HttpMediaTypeNotAcceptableException;

    @ApiOperation("Exclui a foto do produto de um restaurante")
    @ApiResponses({@ApiResponse(responseCode = "204",
            description = "Foto do produto excluída",
            content = @Content(schema = @Schema(implementation = Problem.class))),
            @ApiResponse(responseCode = "400",
                    description = "ID do restaurante ou produto inválido",
                    content = @Content(schema = @Schema(implementation = Problem.class))),
            @ApiResponse(responseCode = "404", description = "Foto de produto não encontrada",
                    content = @Content(schema = @Schema(implementation = Problem.class))),
    })
    void excluirFoto(@ApiParam(value = "ID do restaurante", example = "1", required = true) Long restauranteId,
                     @ApiParam(value = "ID do produto", example = "1", required = true) Long produtoId);

}
