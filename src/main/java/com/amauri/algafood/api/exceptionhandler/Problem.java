package com.amauri.algafood.api.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

import java.time.OffsetDateTime;
import java.util.List;

@ApiModel("Problema")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Builder
public class Problem {

    @ApiModelProperty(example = "400", position = 1)
    private Integer status;

    @ApiModelProperty(example = "2025-03-10T11:23:38.5176586Z", position = 5)
    private OffsetDateTime timestamp;

    @ApiModelProperty(example = "https://algafood.com.br/erro-negocio", position = 10)
    private String type;

    @ApiModelProperty(example = "Violação de regra de negócio", position = 15)
    private String title;
    @ApiModelProperty(example = "Não existe um cadastro de Estado com o código 4", position = 20)
    private String detail;
    @ApiModelProperty(example = "Não existe um cadastro de Estado com o código 4", position = 25)
    private String userMessage;

    @ApiModelProperty(value = "Lista de objetos ou campos que geraram o erro (opcional)", position = 30)
    private List<Object> objects;

    @ApiModel("Objeto Problema")
    @Getter
    @Builder
    public static class Object {
        @ApiModelProperty(example = "preco")
        private String name;
        @ApiModelProperty(example = "o preço é obrigatório")
        private String userMessage;
    }
}
