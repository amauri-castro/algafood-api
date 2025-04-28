package com.amauri.algafood.infrastructure.service.email;

import com.amauri.algafood.domain.service.EnvioEmailService;
import freemarker.template.Configuration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class FakeEnvioEmailService implements EnvioEmailService {

    @Autowired
    private Configuration freeMarkerConfig;

    @Autowired
    private ProcessadorEmailTemplate processadorEmailTemplate;

    @Override
    public void enviar(Mensagem mensagem) {
        String corpo = processadorEmailTemplate.processarTemplate(mensagem);

        log.info("[E-MAIL FAKE] para: {}\n{}", mensagem.getDestinatarios(), corpo);
    }

}
