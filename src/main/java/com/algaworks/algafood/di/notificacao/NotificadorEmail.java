package com.algaworks.algafood.di.notificacao;

import ch.qos.logback.core.net.server.Client;
import com.algaworks.algafood.di.modelo.Cliente;
import org.springframework.stereotype.Component;

import java.util.Locale;

public class NotificadorEmail implements Notificador{

    private boolean caixaAlta;
    private String hostServidorSmtp;

    public NotificadorEmail(String hostServidorSmtp) {
        this.hostServidorSmtp = hostServidorSmtp;
        System.out.println("Notificador E-mail");
    }

    @Override
    public void notificar(Cliente cliente, String mensagem) {
        if (this.caixaAlta) {
            mensagem = mensagem.toUpperCase();
        }

        System.out.printf("Notificando %s através do e-mail %s: %s\n", cliente.getNome(), cliente.getEmail(),
                mensagem);
    }

    public void setCaixaAlta(boolean caixaAlta) {
        this.caixaAlta = caixaAlta;
    }
}
