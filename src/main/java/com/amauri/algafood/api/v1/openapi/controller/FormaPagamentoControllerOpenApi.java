package com.amauri.algafood.api.v1.openapi.controller;

import com.amauri.algafood.api.exceptionhandler.Problem;
import com.amauri.algafood.api.v1.model.FormaPagamentoModel;
import com.amauri.algafood.api.v1.model.input.FormaPagamentoInput;
import com.amauri.algafood.api.v1.openapi.model.FormasPagamentoModelOpenApi;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.context.request.ServletWebRequest;

import javax.validation.Valid;

@Api(tags = "Formas de pagamento")
public interface FormaPagamentoControllerOpenApi {

    @ApiOperation(value = "Lista as formas de pagamento")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = FormasPagamentoModelOpenApi.class)))
    })
    ResponseEntity<CollectionModel<FormaPagamentoModel>> listar(ServletWebRequest request);

    @ApiOperation("Busca uma forma de pagamento por Id")
    @ApiResponses({@ApiResponse(responseCode = "400",
            description = "ID da forma de pagamento inválido",
            content = @Content( schema = @Schema(implementation = Problem.class))),
            @ApiResponse(responseCode = "404", description = "Forma de pagamento não encontrada",
                    content = @Content(schema = @Schema(implementation = Problem.class))),
    })
    ResponseEntity<FormaPagamentoModel> buscar(@ApiParam(value = "ID de uma forma de pagamento")
                                               @PathVariable Long formaPagamentoId,
                                               ServletWebRequest request);

    @ApiOperation("Cadastra uma forma de pagamento")
    @ApiResponses(@ApiResponse(responseCode = "201", description = "Forma de pagamento cadastrada"))
    FormaPagamentoModel salvar(@ApiParam(name = "corpo", value = "Representacao de uma nova forma de pagamento")
                               @RequestBody @Valid FormaPagamentoInput formaPagamentoInput);

    @ApiOperation("Atualiza uma forma de pagamento por ID")
    @ApiResponses({@ApiResponse(responseCode = "200",
            description = "Forma de pagamento atualizada", content = @Content(schema = @Schema(implementation = Problem.class))),
            @ApiResponse(responseCode = "404",description = "Forma de pagamento não encontrada",
                    content = @Content(schema = @Schema(implementation = Problem.class))),
    })
    FormaPagamentoModel atualizar(@ApiParam(value = "Id de uma forma de pagamento")
                                  @PathVariable Long formaPagamentoId, @RequestBody @Valid FormaPagamentoInput formaPagamentoInput);

    @ApiOperation("Exclui uma forma de pagamento por id")
    @ApiResponses({@ApiResponse(responseCode = "204",
            description = "Forma de pagamento excluída", content = @Content(schema = @Schema(implementation = Problem.class))),
            @ApiResponse(responseCode = "404", description = "Forma de Pagamento não encontrada",
                    content = @Content(schema = @Schema(implementation = Problem.class))),
    })
    void excluir(@ApiParam(value = "ID de uma forma de pagamento") Long formaPagamentoId);

}
