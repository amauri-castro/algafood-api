package com.amauri.algafood.api.v1.openapi.controller;

import com.amauri.algafood.api.v1.model.EstadoModel;
import com.amauri.algafood.api.v1.model.input.EstadoInput;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Estados", description = "Gerencia os estados")
public interface EstadoControllerOpenApi {

    @Operation(summary = "Lista os estados")
    CollectionModel<EstadoModel> listar();

    @Operation(summary = "Busca um estado por ID", responses = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "400", description = "ID do estado inválido", content = {
                    @Content(schema = @Schema(ref = "Problema"))}),
            @ApiResponse(responseCode = "404", description = "Estado não encontrado", content = {
                    @Content(schema = @Schema(ref = "Problema"))})
    })
    EstadoModel buscar(@Parameter(description = "ID de um estado", example = "1", required = true)
                       Long estadoId);

    @Operation(summary = "Cadastra um estado", responses = {
            @ApiResponse(responseCode = "201", description = "Estado cadastrado")
    })
    EstadoModel salvar(@RequestBody(description = "Representação de um novo estado", required = true)
                       EstadoInput estadoInput);

    @Operation(summary = "Atualiza um estado por ID", responses = {
            @ApiResponse(responseCode = "200", description = "Estado atualizado"),
            @ApiResponse(responseCode = "404", description = "Estado não encontrado", content = {
                    @Content(schema = @Schema(ref = "Problema"))})
    })
    EstadoModel atualizar(@Parameter(description = "ID de um estado", example = "1", required = true)
                          Long estadoId,
                          @RequestBody(description = "Representação de um estado com novos dados", required = true)
                          EstadoInput estadoInput);

    @Operation(summary = "Exclui um estado por ID", responses = {
            @ApiResponse(responseCode = "204", description = "Estado excluído"),
            @ApiResponse(responseCode = "404", description = "Estado não encontrado", content = {
                    @Content(schema = @Schema(ref = "Problema"))})
    })
    ResponseEntity<Void> excluir(@Parameter(description = "ID de um estado", example = "1", required = true)
                                 Long estadoId);

}