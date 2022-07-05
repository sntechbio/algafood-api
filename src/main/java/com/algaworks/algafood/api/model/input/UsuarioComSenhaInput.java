package com.algaworks.algafood.api.model.input;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

public class UsuarioComSenhaInput extends UsuarioInput{

    @ApiModelProperty(example = "123", required = true)
    @NotBlank
    private String senha;
}
