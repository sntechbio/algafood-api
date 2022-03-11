package com.algaworks.algafood.di.notificacao;

import com.algaworks.algafood.di.modelo.Cliente;
import org.springframework.stereotype.Component;

@Component
public interface Notificador {
    public void notificar(Cliente cliente, String mensagem);
}
