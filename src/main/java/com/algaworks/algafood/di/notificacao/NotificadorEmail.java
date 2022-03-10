package com.algaworks.algafood.di.notificacao;

import ch.qos.logback.core.net.server.Client;
import com.algaworks.algafood.di.modelo.Cliente;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class NotificadorEmail implements Notificador{

    @Override
    public void notificar(Cliente cliente, String mensagem) {
        System.out.printf("Notificando %s através do e-mail %s: %s\n",
                cliente.getNome(), cliente.getEmail(), mensagem);
    }

}
