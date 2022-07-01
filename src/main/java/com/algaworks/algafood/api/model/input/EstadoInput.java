package com.algaworks.algafood.api.model.input;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Setter
@Getter
public class EstadoInput {

    @ApiModelProperty(value = "Minas Gerais", required = true)
    @NotNull
    private String nome;

}
