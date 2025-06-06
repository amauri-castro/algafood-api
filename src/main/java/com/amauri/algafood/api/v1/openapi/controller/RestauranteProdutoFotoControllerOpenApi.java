package com.amauri.algafood.api.v1.openapi.controller;

import com.amauri.algafood.api.v1.model.FotoProdutoModel;
import com.amauri.algafood.api.v1.model.input.FotoProdutoInput;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;

import java.io.IOException;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Produtos")
public interface RestauranteProdutoFotoControllerOpenApi {

	@Operation(summary = "Atualiza a foto do produto de um restaurante")
	FotoProdutoModel atualizarFoto(@Parameter(description = "ID do restaurante", example = "1", required = true)  Long restauranteId,
								   @Parameter(description = "ID do produto", example = "2", required = true) Long produtoId,
								   @RequestBody(required = true) FotoProdutoInput fotoProdutoInput
								   ) throws IOException;

	@Operation(summary = "Exclui a foto do produto de um restaurante", responses = {
			@ApiResponse(responseCode = "204", description = "Foto do produto excluída"),
			@ApiResponse(responseCode = "400", description = "ID do restaurante ou produto inválido", content = {
					@Content(schema = @Schema(ref = "Problema")) }),
			@ApiResponse(responseCode = "404", description = "Foto de produto não encontrada", content = {
					@Content(schema = @Schema(ref = "Problema")) }),
	})
	ResponseEntity<Void> excluirFoto(@Parameter(description = "ID do restaurante", example = "1", required = true)
									 Long restauranteId,
									 @Parameter(description = "ID do produto", example = "2", required = true)
									 Long produtoId);

	@Operation(summary = "Busca a foto do produto de um restaurante", responses = {
			@ApiResponse(responseCode = "200", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = FotoProdutoModel.class)),
					@Content(mediaType = "image/jpeg", schema = @Schema(type = "string", format = "binary")),
					@Content(mediaType = "image/png", schema = @Schema(type = "string", format = "binary"))
			}),
			@ApiResponse(responseCode = "400", description = "ID do restaurante ou produto inválido", content = {
					@Content(schema = @Schema(ref = "Problema")) }),
			@ApiResponse(responseCode = "404", description = "Foto de produto não encontrada", content = {
					@Content(schema = @Schema(ref = "Problema")) })
	})
	FotoProdutoModel buscar(@Parameter(description = "ID do restaurante", example = "1", required = true)
							Long restauranteId,
							@Parameter(description = "ID do produto", example = "2", required = true)
							Long produtoId);

	@Operation(hidden = true)
	ResponseEntity<?> servirFoto(Long restauranteId, Long produtoId, String acceptHeader)
			throws HttpMediaTypeNotAcceptableException;

}