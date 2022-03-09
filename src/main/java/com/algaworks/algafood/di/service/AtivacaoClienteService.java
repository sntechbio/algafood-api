package com.algaworks.algafood.di.service;

import com.algaworks.algafood.di.modelo.Cliente;
import com.algaworks.algafood.di.notificacao.Notificador;
import org.springframework.stereotype.Component;

public class AtivacaoClienteService {

    private Notificador notificador;

    public AtivacaoClienteService(Notificador notificador) {
        this.notificador = notificador;

        System.out.println("AtivaçãoClienteService" + notificador);
    }

    public void ativar(Cliente cliente) {
        cliente.isAtivo();
        notificador.notificar(cliente, "Seu cadastro no sistema está ativo!");
    }
}
