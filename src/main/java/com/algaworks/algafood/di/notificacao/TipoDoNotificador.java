package com.algaworks.algafood.di.notificacao;

import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME) // tempo que essa anotação vai ficar em tempo de execução
@Qualifier // isso quer dizer que nossa anotação é um qualidicador
public @interface TipoDoNotificador {

    NivelUrgencia value();

}
