package com.amauri.algafood.api.exceptionhandler;

public enum ProblemType {

    ENTIDADE_NAO_ENCONTRADA;

    private String title;
    private String uri;

    ProblemType(String uri, String title) {

    }
}
