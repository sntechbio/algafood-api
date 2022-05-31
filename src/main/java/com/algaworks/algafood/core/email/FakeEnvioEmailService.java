package com.algaworks.algafood.core.email;

import com.algaworks.algafood.infrastructure.service.email.SmtpEnvioEmailService;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class FakeEnvioEmailService extends SmtpEnvioEmailService {

    @Override
    public void enviar(Mensagem mensagem) throws IOException {

        String corpo = processarTemplate(mensagem);

        log.info("[FAKE E-MAIL] Para: {}\n{}", mensagem.getDestinatarios(), corpo);
    }
}
