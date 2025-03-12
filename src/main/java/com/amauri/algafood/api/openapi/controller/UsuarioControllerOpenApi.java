package com.amauri.algafood.api.openapi.controller;

import com.amauri.algafood.api.exceptionhandler.Problem;
import com.amauri.algafood.api.model.UsuarioModel;
import com.amauri.algafood.api.model.input.SenhaInput;
import com.amauri.algafood.api.model.input.UsuarioComSenhaInput;
import com.amauri.algafood.api.model.input.UsuarioInput;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.List;

@Api(tags = "Usuários")
public interface UsuarioControllerOpenApi {

    @ApiOperation("Lista os usuários")
    List<UsuarioModel> listar();

    @ApiOperation("Busca um usuário por Id")
    @ApiResponses({@ApiResponse(responseCode = "400",
            description = "ID do usuário inválido",
            content = @Content(schema = @Schema(implementation = Problem.class))),
            @ApiResponse(responseCode = "404",
                    description = "Usuário não encontrado",
                    content = @Content(schema = @Schema(implementation = Problem.class))),
    })
    UsuarioModel buscar(@ApiParam(value = "ID do usuário", example = "1", required = true) Long usuarioId);

    @ApiOperation("Cadastra um usuário")
    @ApiResponses({@ApiResponse(responseCode = "201",
            description = "Usuário cadastrado",
            content = @Content(schema = @Schema(implementation = Problem.class)))
    })
    UsuarioModel salvar(UsuarioComSenhaInput usuarioComSenhaInput);

    @ApiOperation("Atualiza um usuário por Id")
    @ApiResponses({@ApiResponse(responseCode = "200",
            description = "Usuário atualizado com sucesso",
            content = @Content(schema = @Schema(implementation = Problem.class))),
            @ApiResponse(responseCode = "404",
                    description = "Usuário não encontrado",
                    content = @Content(schema = @Schema(implementation = Problem.class))),
    })
    UsuarioModel atualizar(@ApiParam(value = "ID do usuário", example = "1", required = true)
                           Long usuarioId, UsuarioInput usuarioInput);

    @ApiOperation("Atualiza a senha de um usuário")
    @ApiResponses({@ApiResponse(responseCode = "204",
            description = "Senha alterada com sucesso",
            content = @Content(schema = @Schema(implementation = Problem.class))),
            @ApiResponse(responseCode = "404",
                    description = "Usuário não encontrado",
                    content = @Content(schema = @Schema(implementation = Problem.class))),
    })
    void alterarSenha(@ApiParam(value = "ID do usuário", example = "1", required = true)
                      Long usuarioId, SenhaInput senhaInput);
}
