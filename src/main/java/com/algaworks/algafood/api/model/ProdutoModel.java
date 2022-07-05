package com.algaworks.algafood.api.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProdutoModel {

    @ApiModelProperty(example = "1")
    private Long id;

    @ApiModelProperty(example = "Espetinho de cupim")
    private String nome;

    @ApiModelProperty(example = "Acompanha farinha, mandipoca e vinagrete")
    private String descricao;
    private BigDecimal preco;
    private Boolean ativo;

}
