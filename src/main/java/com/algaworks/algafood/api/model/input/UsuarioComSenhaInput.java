package com.algaworks.algafood.api.model.input;

import javax.validation.constraints.NotBlank;

public class UsuarioComSenhaInput extends UsuarioInput{

    @NotBlank
    private String senha;
}
