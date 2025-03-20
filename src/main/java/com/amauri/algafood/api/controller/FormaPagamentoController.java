package com.amauri.algafood.api.controller;

import com.amauri.algafood.api.assembler.FormaPagamentoInputDisassembler;
import com.amauri.algafood.api.assembler.FormaPagamentoModelAssembler;
import com.amauri.algafood.api.model.FormaPagamentoModel;
import com.amauri.algafood.api.model.input.FormaPagamentoInput;
import com.amauri.algafood.api.openapi.controller.FormaPagamentoControllerOpenApi;
import com.amauri.algafood.domain.model.FormaPagamento;
import com.amauri.algafood.domain.repository.FormaPagamentoRepository;
import com.amauri.algafood.domain.service.CadastroFormaPagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.ShallowEtagHeaderFilter;

import javax.validation.Valid;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping(value = "/formas-pagamento", produces = MediaType.APPLICATION_JSON_VALUE)
public class FormaPagamentoController implements FormaPagamentoControllerOpenApi {

    @Autowired
    private FormaPagamentoRepository formaPagamentoRepository;

    @Autowired
    private CadastroFormaPagamentoService cadastroFormaPagamentoService;

    @Autowired
    private FormaPagamentoModelAssembler formaPagamentoModelAssembler;

    @Autowired
    private FormaPagamentoInputDisassembler formaPagamentoInputDisassembler;

    @GetMapping
    public ResponseEntity<CollectionModel<FormaPagamentoModel>> listar(ServletWebRequest request) {
        //exemplo deep etag
        ShallowEtagHeaderFilter.disableContentCaching(request.getRequest());

        String eTag = "0";

        OffsetDateTime dataUltimaAtualizacao = formaPagamentoRepository.getDataUltimaAtualizacao();

        if(dataUltimaAtualizacao != null) {
            eTag = String.valueOf(dataUltimaAtualizacao.toEpochSecond());
        }

        if(request.checkNotModified(eTag)) {
            return null;
        }

        List<FormaPagamento> formasPagamento = formaPagamentoRepository.findAll();
        CollectionModel<FormaPagamentoModel> formasPagamentoModel = formaPagamentoModelAssembler.toCollectionModel(formasPagamento);
        return ResponseEntity.ok()
                .cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS))
                .eTag(eTag)
                .body(formasPagamentoModel);
    }

    @GetMapping("/{formaPagamentoId}")
    public ResponseEntity<FormaPagamentoModel> buscar(@PathVariable Long formaPagamentoId, ServletWebRequest request) {
        ShallowEtagHeaderFilter.disableContentCaching(request.getRequest());
        String eTag = "0";
        OffsetDateTime dataUltimaAtualizacao = formaPagamentoRepository.getDataUltimaAtualizacao(formaPagamentoId);
        if(dataUltimaAtualizacao != null) {
            eTag = String.valueOf(dataUltimaAtualizacao.toEpochSecond());
        }
        if(request.checkNotModified(eTag)) {
            return null;
        }

        FormaPagamento formaPagamento = cadastroFormaPagamentoService.buscarOuFalhar(formaPagamentoId);
        FormaPagamentoModel formaPagamentoModel = formaPagamentoModelAssembler.toModel(formaPagamento);
        return ResponseEntity.ok()
                .cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS))
                .eTag(eTag)
                .body(formaPagamentoModel);
    }


    /*
    @GetMapping("/{formaPagamentoId}")
    public ResponseEntity<FormaPagamentoModel> buscar(@PathVariable Long formaPagamentoId) {
        //exemplo shallow etag
        FormaPagamento formaPagamento = cadastroFormaPagamentoService.buscarOuFalhar(formaPagamentoId);
        FormaPagamentoModel formaPagamentoModel = formaPagamentoModelAssembler.toModel(formaPagamento);
        return ResponseEntity.ok()
                .cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS).cachePublic())
//                .cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS).cachePrivate())
//                .cacheControl(CacheControl.noCache()) sempre faz validacao do cache
                //                .cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS).cachePrivate())
//              .cacheControl(CacheControl.noStore())  desativar chave para esta reposta
                .body(formaPagamentoModel);
    }*/


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FormaPagamentoModel salvar(@RequestBody @Valid FormaPagamentoInput formaPagamentoInput) {
        FormaPagamento formaPagamento = formaPagamentoInputDisassembler.toDomainObject(formaPagamentoInput);
        return formaPagamentoModelAssembler.toModel(cadastroFormaPagamentoService.salvar(formaPagamento));
    }

    @PutMapping("/{formaPagamentoId}")
    public FormaPagamentoModel atualizar(@PathVariable Long formaPagamentoId, @RequestBody @Valid FormaPagamentoInput formaPagamentoInput) {
        FormaPagamento formaPagamento = cadastroFormaPagamentoService.buscarOuFalhar(formaPagamentoId);
        formaPagamentoInputDisassembler.copyToDomainObject(formaPagamentoInput, formaPagamento);

        return formaPagamentoModelAssembler.toModel(cadastroFormaPagamentoService.salvar(formaPagamento));
    }

    @DeleteMapping("/{formaPagamentoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long formaPagamentoId) {
        cadastroFormaPagamentoService.excluir(formaPagamentoId);
    }

}
