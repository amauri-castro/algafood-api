package com.amauri.algafood.api.controller;

import com.amauri.algafood.api.model.input.FotoProdutoInput;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@RestController
@RequestMapping("/restaurantes/{restauranteId}/produtos/{produtoId}/foto")
public class RestauranteProdutoFotoController {


    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void atualizarFoto(@PathVariable Long restauranteId, @PathVariable Long produtoId,
                              @Valid FotoProdutoInput fotoProdutoInput) {

        var nomeArquivo = UUID.randomUUID().toString() + "_" + fotoProdutoInput.getArquivo().getOriginalFilename();

        Path diretorioBase = Paths.get(System.getProperty("user.home"), "downloads", "uploads-backend");

        var arquivoFoto = Path.of(diretorioBase.toString(), nomeArquivo);


        System.out.println(fotoProdutoInput.getDescricao());
        System.out.println(arquivoFoto);
        System.out.println(fotoProdutoInput.getArquivo().getContentType());

        try {
            fotoProdutoInput.getArquivo().transferTo(arquivoFoto);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

}
