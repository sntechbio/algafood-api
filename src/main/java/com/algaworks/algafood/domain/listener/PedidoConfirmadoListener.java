package com.algaworks.algafood.domain.listener;

import com.algaworks.algafood.domain.event.PedidoConfirmadoEvent;
import com.algaworks.algafood.domain.model.Pedido;
import com.algaworks.algafood.domain.service.EnvioEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class PedidoConfirmadoListener {

    @Autowired
    private EnvioEmailService envioEmailService;

    // SEMPRE QUE ESSE EVENTO FOR LANÇADO ESSE MÉTODO VAI ESCUTAR
    @EventListener
    public void aoConfirmarPedido(PedidoConfirmadoEvent event) throws IOException {
        Pedido pedido = event.getPedido();

        var mensagem = EnvioEmailService.Mensagem.builder()
                .assunto(pedido.getRestaurante().getNome() + "- Pedido confirmado")
                .variavel("pedido", pedido)
                .corpo("pedido-confirmado.html")
                .destinatario(pedido.getCliente().getEmail()).build();

        envioEmailService.enviar(mensagem);
    }

}
