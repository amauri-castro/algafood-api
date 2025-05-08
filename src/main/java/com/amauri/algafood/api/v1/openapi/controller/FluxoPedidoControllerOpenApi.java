package com.amauri.algafood.api.v1.openapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Pedidos")
public interface FluxoPedidoControllerOpenApi {

    @Operation(summary = "Confirmação de pedido", responses = {
            @ApiResponse(responseCode = "204", description = "Pedido confirmado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Pedido não encontrado", content = {
                    @Content(schema = @Schema(ref = "Problema"))}),
    })
    ResponseEntity<Void> confirmar(@Parameter(description = "Codigo de um pedido",
            example = "040d1b4c-3f8e-4a2b-a5f7-9a2c3e4d5f6e", required = true)
                                   String codigoPedido);

    @Operation(summary = "Cancelamento de pedido", responses = {
            @ApiResponse(responseCode = "204", description = "Pedido cancelado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Pedido não encontrado", content = {
                    @Content(schema = @Schema(ref = "Problema"))}),
    })
    ResponseEntity<Void> cancelar(@Parameter(description = "Codigo de um pedido",
            example = "040d1b4c-3f8e-4a2b-a5f7-9a2c3e4d5f6e", required = true)
                                  String codigoPedido);

    @Operation(summary = "Registrar entrega de pedido", responses = {
            @ApiResponse(responseCode = "204", description = "Entrega de pedido registrada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Pedido não encontrado", content = {
                    @Content(schema = @Schema(ref = "Problema"))}),
    })
    ResponseEntity<Void> entregar(@Parameter(description = "Codigo de um pedido",
            example = "040d1b4c-3f8e-4a2b-a5f7-9a2c3e4d5f6e", required = true)
                                  String codigoPedido);

}