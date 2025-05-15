package com.amauri.algafood.api.v1.controller;

import com.amauri.algafood.api.v1.AlgaLinks;
import com.amauri.algafood.api.v1.assembler.ProdutoInputDisassembler;
import com.amauri.algafood.api.v1.assembler.ProdutoModelAssembler;
import com.amauri.algafood.api.v1.model.ProdutoModel;
import com.amauri.algafood.api.v1.model.input.ProdutoInput;
import com.amauri.algafood.api.v1.openapi.controller.RestauranteProdutoControllerOpenApi;
import com.amauri.algafood.core.security.CheckSecurity;
import com.amauri.algafood.domain.model.Produto;
import com.amauri.algafood.domain.model.Restaurante;
import com.amauri.algafood.domain.repository.ProdutoRepository;
import com.amauri.algafood.domain.service.CadastroProdutoService;
import com.amauri.algafood.domain.service.CadastroRestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/v1/restaurantes/{restauranteId}/produtos", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestauranteProdutoController implements RestauranteProdutoControllerOpenApi {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CadastroProdutoService cadastroProdutoService;

    @Autowired
    private CadastroRestauranteService cadastroRestauranteService;

    @Autowired
    private ProdutoModelAssembler produtoModelAssembler;

    @Autowired
    private ProdutoInputDisassembler produtoInputDisassembler;

    @Autowired
    private AlgaLinks algaLinks;

    @CheckSecurity.Restaurantes.PodeConsultar
    @Override
    @GetMapping
    public CollectionModel<ProdutoModel> listar(@PathVariable Long restauranteId,
                                                @RequestParam(required = false, defaultValue = "false") Boolean incluirInativos) {
        Restaurante restaurante = cadastroRestauranteService.buscarOuFalhar(restauranteId);

        List<Produto> todosProdutos = null;
        if (incluirInativos) {
            todosProdutos = produtoRepository.findTodosByRestaurante(restaurante);
        } else {
            todosProdutos = produtoRepository.findAtivoByRestaurante(restaurante);
        }

        return produtoModelAssembler.toCollectionModel(todosProdutos)
                .add(algaLinks.linkToProdutos(restauranteId));

    }

    @CheckSecurity.Restaurantes.PodeConsultar
    @Override
    @GetMapping("/{produtoId}")
    public ProdutoModel buscar(@PathVariable Long restauranteId, @PathVariable Long produtoId) {
        cadastroRestauranteService.buscarOuFalhar(restauranteId);
        return produtoModelAssembler.toModel(cadastroProdutoService.buscarOuFalhar(restauranteId, produtoId));
    }

    @CheckSecurity.Restaurantes.PodeGerenciarFuncionamento
    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProdutoModel salvar(@PathVariable Long restauranteId, @RequestBody @Valid ProdutoInput produtoInput) {
        Restaurante restaurante = cadastroRestauranteService.buscarOuFalhar(restauranteId);
        Produto produto = produtoInputDisassembler.toDomainObject(produtoInput);
        produto.setRestaurante(restaurante);
        produto = cadastroProdutoService.salvar(produto);

        return produtoModelAssembler.toModel(produto);
    }

    @CheckSecurity.Restaurantes.PodeGerenciarFuncionamento
    @Override
    @PutMapping("/{produtoId}")
    public ProdutoModel atualizar(@PathVariable Long restauranteId, @PathVariable Long produtoId, @RequestBody @Valid ProdutoInput produtoInput) {
        cadastroRestauranteService.buscarOuFalhar(restauranteId);
        Produto produtoAtual = cadastroProdutoService.buscarOuFalhar(restauranteId, produtoId);
        produtoInputDisassembler.copyToDomainObject(produtoInput, produtoAtual);
        produtoAtual = cadastroProdutoService.salvar(produtoAtual);
        return produtoModelAssembler.toModel(produtoAtual);
    }
}
