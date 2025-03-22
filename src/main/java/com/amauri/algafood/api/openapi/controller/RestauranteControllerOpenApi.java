package com.amauri.algafood.api.openapi.controller;

import com.amauri.algafood.api.exceptionhandler.Problem;
import com.amauri.algafood.api.model.RestauranteApenasNomeModel;
import com.amauri.algafood.api.model.RestauranteBasicoModel;
import com.amauri.algafood.api.model.RestauranteModel;
import com.amauri.algafood.api.model.input.RestauranteInput;
import io.swagger.annotations.*;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@Api(tags = "Restaurantes")
public interface RestauranteControllerOpenApi {

    @ApiOperation("Lista os restaurantes")
    @ApiImplicitParams({ @ApiImplicitParam(value = "Nome da projeção de pedidos", allowableValues = "apenas-nome",
            name = "projecao", paramType = "query", type = "string")})
    CollectionModel<RestauranteBasicoModel> listar();

    @ApiIgnore
    @ApiOperation(value = "Lista restaurantes", hidden = true)
    @GetMapping(params = "projecao=apenas-nome")
    CollectionModel<RestauranteApenasNomeModel> listarApenasNomes();

    @ApiOperation("Busca um restaurante por Id")
    @ApiResponses({@ApiResponse(responseCode = "400",
            description = "ID do restaurante inválido",
            content = @Content(schema = @Schema(implementation = Problem.class))),
            @ApiResponse(responseCode = "404",
                    description = "Restaurante não encontrado",
                    content = @Content(schema = @Schema(implementation = Problem.class))),
    })
    RestauranteModel buscar(@ApiParam(example = "Id de um restaurante", required = true) @PathVariable Long restauranteId);

    @ApiOperation("Cadastra um grupo")
    @ApiResponses(@ApiResponse(responseCode = "201", description = "Grupo cadastrado"))
    RestauranteModel salvar(RestauranteInput restauranteInput);

    @ApiOperation("Atualiza um restaurante por id")
    @ApiResponses({@ApiResponse(responseCode = "200",
            description = "Restaurante atualizado",
            content = @Content(schema = @Schema(implementation = Problem.class))),
            @ApiResponse(responseCode = "404",
                    description = "Restaurante não encontrado",
                    content = @Content(schema = @Schema(implementation = Problem.class))),
    })
    RestauranteModel atualizar(@ApiParam(value = "Id de um restaurantes")
                               Long restauranteId,
                               RestauranteInput restauranteInput);

    @ApiOperation("Ativa um restaurante por id")
    @ApiResponses({@ApiResponse(responseCode = "204",
            description = "Restaurante ativado com sucesso",
            content = @Content(schema = @Schema(implementation = Problem.class))),
            @ApiResponse(responseCode = "404",
                    description = "Restaurante não encontrado",
                    content = @Content(schema = @Schema(implementation = Problem.class)))
    })
    ResponseEntity<Void> ativar(@ApiParam(value = "Id de um restaurante")
                Long restauranteId);

    @ApiOperation("Inativa um restaurante por id")
    @ApiResponses({@ApiResponse(responseCode = "204",
            description = "Restaurante inativado com sucesso",
            content = @Content(schema = @Schema(implementation = Problem.class))),
            @ApiResponse(responseCode = "404",
                    description = "Restaurante não encontrado",
                    content = @Content(schema = @Schema(implementation = Problem.class))),
    })
    ResponseEntity<Void> inativar(@ApiParam(value = "Id de um restaurante")
                  Long restauranteId);

    @ApiOperation("Ativa múltiplos restaurantes")
    @ApiResponses({@ApiResponse(responseCode = "204",
            description = "Restaurantes ativados com sucesso",
            content = @Content(schema = @Schema(implementation = Problem.class)))
    })
    void ativarMultiplos(List<Long> restauranteIds);

    @ApiOperation("Inativa múltiplos restaurantes")
    @ApiResponses({@ApiResponse(responseCode = "204",
            description = "Restaurantes inativados com sucesso",
            content = @Content(schema = @Schema(implementation = Problem.class)))
    })
    void inativarMultiplos(List<Long> restauranteIds);


    @ApiOperation("Abre um restaurante por ID")
    @ApiResponses({@ApiResponse(responseCode = "204",
            description = "Restaurante aberto com sucesso",
            content = @Content(schema = @Schema(implementation = Problem.class))),
            @ApiResponse(responseCode = "404",
                    description = "Restaurante não encontrado",
                    content = @Content(schema = @Schema(implementation = Problem.class)))
    })
    ResponseEntity<Void> abrir(@ApiParam(value = "Id de um restaurante", example = "1")
               Long restauranteId);


    @ApiOperation("Fecha um restaurante por ID")
    @ApiResponses({@ApiResponse(responseCode = "204",
            description = "Restaurante fechado com sucesso",
            content = @Content(schema = @Schema(implementation = Problem.class))),
            @ApiResponse(responseCode = "404",
                    description = "Restaurante não encontrado",
                    content = @Content(schema = @Schema(implementation = Problem.class)))
    })
    ResponseEntity<Void> fechar(@ApiParam(value = "Id de um restaurante", example = "1")
                Long restauranteId);


}
