package com.amauri.algafood.api.controller;

import com.amauri.algafood.api.assembler.FotoProdutoModelAssembler;
import com.amauri.algafood.api.model.FotoProdutoModel;
import com.amauri.algafood.api.model.input.FotoProdutoInput;
import com.amauri.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.amauri.algafood.domain.model.FotoProduto;
import com.amauri.algafood.domain.model.Produto;
import com.amauri.algafood.domain.service.CadastroProdutoService;
import com.amauri.algafood.domain.service.CatalogoFotoProdutoService;
import com.amauri.algafood.domain.service.FotoStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/restaurantes/{restauranteId}/produtos/{produtoId}/foto")
public class RestauranteProdutoFotoController {

    @Autowired
    private CadastroProdutoService cadastroProduto;

    @Autowired
    private CatalogoFotoProdutoService catalogoFotoProduto;

    @Autowired
    private FotoProdutoModelAssembler assembler;

    @Autowired
    private FotoStorageService fotoStorageService;


    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public FotoProdutoModel atualizarFoto(@PathVariable Long restauranteId, @PathVariable Long produtoId,
                                          @Valid FotoProdutoInput fotoProdutoInput) throws IOException {

        Produto produto = cadastroProduto.buscarOuFalhar(restauranteId, produtoId);

        MultipartFile arquivo = fotoProdutoInput.getArquivo();

        FotoProduto foto = new FotoProduto();
        foto.setProduto(produto);
        foto.setDescricao(fotoProdutoInput.getDescricao());
        foto.setContentType(arquivo.getContentType());
        foto.setTamanho(arquivo.getSize());
        foto.setNomeArquivo(arquivo.getOriginalFilename());

        FotoProduto fotoSalva = catalogoFotoProduto.salvar(foto, arquivo.getInputStream());

        return assembler.toModel(fotoSalva);

    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public FotoProdutoModel buscar(@PathVariable Long restauranteId, @PathVariable Long produtoId) {
        FotoProduto fotoProduto = catalogoFotoProduto.buscarOuFalhar(restauranteId, produtoId);

        return assembler.toModel(fotoProduto);
    }

    @GetMapping(produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<InputStreamResource> buscarFoto(@PathVariable Long restauranteId, @PathVariable Long produtoId) {
        try {
            FotoProduto fotoProduto = catalogoFotoProduto.buscarOuFalhar(restauranteId, produtoId);

            InputStream inputStream = fotoStorageService.recuperar(fotoProduto.getNomeArquivo());

            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(new InputStreamResource(inputStream));
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.notFound().build();
        }

    }

}
