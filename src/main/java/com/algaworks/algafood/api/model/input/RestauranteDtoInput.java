package com.algaworks.algafood.api.model.input;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class RestauranteDtoInput {

    private String nome;
    private BigDecimal taxaFrete;
    private CozinhaDtoIdInput cozinha;

}
