package com.amauri.algafood.infrastructure.service.email;

import freemarker.template.Configuration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class FakeEnvioEmailService extends SmtpEnvioEmailService {

    @Autowired
    private Configuration freeMarkerConfig;

    @Override
    public void enviar(Mensagem mensagem) {
        String corpo = processarTemplate(mensagem);

        log.info("[E-MAIL FAKE] para: {}\n{}", mensagem.getDestinatarios(), corpo);
    }

}
