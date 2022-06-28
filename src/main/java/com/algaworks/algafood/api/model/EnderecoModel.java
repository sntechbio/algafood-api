package com.algaworks.algafood.api.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EnderecoModel {

    private String cep;
    private String logradouro;

    @ApiModelProperty(example = "\"1500\"")
    private String numero;

    private String complemento;
    private String bairro;
    private CidadeResumoModel cidade;

}
