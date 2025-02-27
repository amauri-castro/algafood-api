package com.amauri.algafood.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {


    DADOS_INVALIDOS("/dados-invalidos", "Dados inválidos"),
    ERRO_SISTEMA("/erro-sistema", "Erro de Sistema"),
    PARAMETRO_INVALIDO("/parametro-invalido", "Parâmetro inválido"),
    MENSAGEM_INCOMPREENSIVEL("/mensagem-incompreensivel", "Mensagem incompreensível"),
    RECURSO_NAO_ENCONTRADO("/recurso-nao-encontrado", "Recurso não encontrado"),
    ENTIDADE_EM_USO("/entidade-em-uso", "Entidade em uso"),
    ERRO_NEGOCIO("/erro-negocio", "Violação de regra de negócio"),
    ERRO_MAX_FILE_EXCEEDED("/erro-payload-grande", "Payload excedou o limite");

    private String title;
    private String uri;

    ProblemType(String path, String title) {
        this.uri = "https://algafood.com.br" + path;
        this.title = title;
    }
}
