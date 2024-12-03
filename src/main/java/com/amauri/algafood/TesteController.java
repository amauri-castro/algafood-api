package com.amauri.algafood;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ConfigurationProperties("notificador.email")
public class TesteController {

    private String hostServidor;

    private String porta;

    @GetMapping("/ola")
    @ResponseBody
    public String teste() {
        return "O host é: " + hostServidor + " porta: " + porta;
    }

    public String getHostServidor() {
        return hostServidor;
    }

    public void setHostServidor(String hostServidor) {
        this.hostServidor = hostServidor;
    }

    public String getPorta() {
        return porta;
    }

    public void setPorta(String porta) {
        this.porta = porta;
    }
}
