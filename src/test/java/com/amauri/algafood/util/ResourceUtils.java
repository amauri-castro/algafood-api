package com.amauri.algafood.util;

import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class ResourceUtils {

    public static String lerArquivoDoResource(String nomeDoRecurso) {
        try {
            InputStream stream = org.springframework.util.ResourceUtils.class.getResourceAsStream(nomeDoRecurso);
            return StreamUtils.copyToString(stream, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
