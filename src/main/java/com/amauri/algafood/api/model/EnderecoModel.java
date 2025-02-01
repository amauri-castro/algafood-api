package com.amauri.algafood.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoModel {
    private String cep;
    private String Logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private CidadeResumoModel cidade;
}
