package com.amauri.algafood;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TesteController {

    @GetMapping("/ola")
    @ResponseBody
    public String teste() {
        return "Olá Amauri novo testo";
    }
}
