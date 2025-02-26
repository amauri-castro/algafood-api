package com.amauri.algafood.api.controller;

import com.amauri.algafood.api.assembler.PedidoInputDisassembler;
import com.amauri.algafood.api.assembler.PedidoModelAssembler;
import com.amauri.algafood.api.assembler.PedidoResumoModelAssembler;
import com.amauri.algafood.api.model.PedidoModel;
import com.amauri.algafood.api.model.PedidoResumoModel;
import com.amauri.algafood.api.model.input.PedidoInput;
import com.amauri.algafood.core.data.PageableTranslator;
import com.amauri.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.amauri.algafood.domain.exception.NegocioException;
import com.amauri.algafood.domain.model.Pedido;
import com.amauri.algafood.domain.model.Usuario;
import com.amauri.algafood.domain.repository.PedidoRepository;
import com.amauri.algafood.domain.filter.PedidoFilter;
import com.amauri.algafood.domain.service.EmissaoPedidoService;
import com.amauri.algafood.infrastructure.repository.spec.PedidoSpecs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private EmissaoPedidoService emissaoPedidoService;

    @Autowired
    private PedidoInputDisassembler pedidoInputDisassembler;

    @Autowired
    private PedidoModelAssembler pedidoModelAssembler;

    @Autowired
    private PedidoResumoModelAssembler pedidoResumoModelAssembler;

//    @GetMapping
//    public MappingJacksonValue listar(@RequestParam(required = false) String campos) {
//        List<Pedido> pedidos = pedidoRepository.findAll();
//        List<PedidoResumoModel> pedidosModel = pedidoResumoModelAssembler.toCollectionModel(pedidos);
//        MappingJacksonValue pedidosWrapper = new MappingJacksonValue(pedidosModel);
//
//        SimpleFilterProvider filterProvider = new SimpleFilterProvider();
//        filterProvider.addFilter("pedidoFilter", SimpleBeanPropertyFilter
//                .serializeAll());
//        if(StringUtils.isNotBlank(campos)) {
//            filterProvider.addFilter("pedidoFilter", SimpleBeanPropertyFilter
//                    .filterOutAllExcept(campos.split(",")));
//        }
//        pedidosWrapper.setFilters(filterProvider);
//        return pedidosWrapper;
//    }

//    @GetMapping
//    public List<PedidoResumoModel> listar() {
//        List<Pedido> pedidos = pedidoRepository.findAll();
//        return pedidoResumoModelAssembler.toCollectionModel(pedidos);
//    }

    @GetMapping
    public Page<PedidoResumoModel> listar(PedidoFilter filtro,
                                          @PageableDefault(size = 5) Pageable pageable) {
        pageable = traduzirPageable(pageable);

        Page<Pedido> pagePedidos = pedidoRepository
                .findAll(PedidoSpecs.usandoFiltro(filtro), pageable);

        List<PedidoResumoModel> pedidosResumoModel = pedidoResumoModelAssembler
                .toCollectionModel(pagePedidos.getContent());

        Page<PedidoResumoModel> pagePedidoResumoModel = new PageImpl<>(pedidosResumoModel, pageable, pagePedidos.getTotalElements());
        return pagePedidoResumoModel;
    }

    @GetMapping("/{codigoPedido}")
    public PedidoModel buscar(@PathVariable String codigoPedido) {
        Pedido pedido = emissaoPedidoService.buscarOuFalhar(codigoPedido);
        return pedidoModelAssembler.toModel(pedido);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PedidoModel adicionar(@Valid @RequestBody PedidoInput pedidoInput) {
        try {
            Pedido novoPedido = pedidoInputDisassembler.toDomainObject(pedidoInput);

            novoPedido.setCliente(new Usuario());
            novoPedido.getCliente().setId(1L);

            novoPedido = emissaoPedidoService.emitir(novoPedido);
            return pedidoModelAssembler.toModel(novoPedido);

        } catch (EntidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    private Pageable traduzirPageable(Pageable apiPageable) {
        var mapeamento = Map.of(
                "codigo", "codigo",
                "subtotal", "subtotal",
                "resturante.nome", "restaurante.nome",
                "cliente.nome", "cliente.nome",
                "valorTotal", "valorTotal"
        );

        return PageableTranslator.translate(apiPageable, mapeamento);
    }
}
