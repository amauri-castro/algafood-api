package com.amauri.algafood.domain.service;

import com.amauri.algafood.domain.exception.RestauranteNaoEncontradoException;
import com.amauri.algafood.domain.model.*;
import com.amauri.algafood.domain.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CadastroRestauranteService {

    @Autowired
    private RestauranteRepository restauranteRepository;


    @Autowired
    private CadastroCozinhaService cadastroCozinha;

    @Autowired
    private CadastroCidadeService cadastroCidadeService;

    @Autowired
    private CadastroFormaPagamentoService cadastroFormaPagamentoService;

    @Autowired
    private CadastroUsuarioService cadastroUsuarioService;

    @Transactional
    public Restaurante salvar(Restaurante restaurante) {
        Long cozinhaId = restaurante.getCozinha().getId();
        Long cidadeId = restaurante.getEndereco().getCidade().getId();
        Cozinha cozinha = cadastroCozinha.buscarOuFalhar(cozinhaId);
        Cidade cidade = cadastroCidadeService.buscarOuFalhar(cidadeId);

        restaurante.setCozinha(cozinha);
        restaurante.getEndereco().setCidade(cidade);
        return restauranteRepository.save(restaurante);
    }

    @Transactional
    public void ativar(Long restauranteId) {
        Restaurante restauranteAtual = buscarOuFalhar(restauranteId);
        restauranteAtual.ativar();
    }

    @Transactional
    public void inativar(Long restauranteId) {
        Restaurante restauranteAtual = buscarOuFalhar(restauranteId);
        restauranteAtual.inativar();
    }

    @Transactional
    public void abrir(Long restauranteId) {
        Restaurante restauranteAtual = buscarOuFalhar(restauranteId);
        restauranteAtual.abrir();
    }

    @Transactional
    public void fechar(Long restauranteId) {
        Restaurante restauranteAtual = buscarOuFalhar(restauranteId);
        restauranteAtual.fechar();
    }

    @Transactional
    public void desvincularFormaPagamento(Long restauranteId, Long formaPagamentoId) {
        Restaurante restaurante = buscarOuFalhar(restauranteId);
        FormaPagamento formaPagamento = cadastroFormaPagamentoService.buscarOuFalhar(formaPagamentoId);
        restaurante.removerFormaPagamento(formaPagamento);
    }

    @Transactional
    public void vincularFormaPagamento(Long restauranteId, Long formaPagamentoId) {
        Restaurante restaurante = buscarOuFalhar(restauranteId);
        FormaPagamento formaPagamento = cadastroFormaPagamentoService.buscarOuFalhar(formaPagamentoId);
        restaurante.adicionarFormaPagamento(formaPagamento);
    }

    @Transactional
    public void desvincularResponsavel(Long restauranteId, Long usuarioId) {
        Restaurante restaurante = buscarOuFalhar(restauranteId);
        Usuario usuario = cadastroUsuarioService.buscarOuFalhar(usuarioId);
        restaurante.removerResponsavel(usuario);
    }

    @Transactional
    public void vincularResponsavel(Long restauranteId, Long usuarioId) {
        Restaurante restaurante = buscarOuFalhar(restauranteId);
        Usuario usuario = cadastroUsuarioService.buscarOuFalhar(usuarioId);
        restaurante.adicionarResponsavel(usuario);
    }

    public Restaurante buscarOuFalhar(Long restauranteId) {
        return restauranteRepository.findById(restauranteId)
                .orElseThrow(() -> new RestauranteNaoEncontradoException(restauranteId));
    }

}
