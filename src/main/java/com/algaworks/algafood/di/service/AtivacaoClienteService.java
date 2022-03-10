package com.algaworks.algafood.di.service;

import com.algaworks.algafood.di.modelo.Cliente;
import com.algaworks.algafood.di.notificacao.Notificador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AtivacaoClienteService {

    @Autowired
    private Notificador notificador;

//    @Autowired // indicando em qual construtor deve ser injentado a dependencia Autowired
//    public AtivacaoClienteService(Notificador notificador) {
//        this.notificador = notificador;
//    }
//
//    public AtivacaoClienteService(String qualquer) {
//
//    }


    public Notificador getNotificador() {
        return notificador;
    }

//    @Autowired
//    public void setNotificador(Notificador notificador) {
//        this.notificador = notificador;
//    }

    public void ativar(Cliente cliente) {
        cliente.isAtivo();
        notificador.notificar(cliente, "Seu cadastro no sistema está ativo!");
    }
}
