package com.amauri.algafood.domain.service;

import com.amauri.algafood.domain.exception.EntidadeEmUsoException;
import com.amauri.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.amauri.algafood.domain.model.Estado;
import com.amauri.algafood.domain.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CadastroEstadoService {

    public static final String MSG_ESTADO_NAO_ENCONTRADO = "Não existe um cadastro de Estado com o código %d";
    public static final String MSG_ESTADO_EM_USO = "Estado de código %d não pode ser removida, pois está em uso";
    @Autowired
    private EstadoRepository estadoRepository;

    public Estado buscarOuFalhar(Long estadoId) {
        return estadoRepository.findById(estadoId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                        String.format(MSG_ESTADO_NAO_ENCONTRADO, estadoId)
                ));
    }

    public Estado salvar(Estado estado) {
        return estadoRepository.save(estado);
    }

    public void excluir(Long estadoId) {
        try {
            estadoRepository.deleteById(estadoId);
        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(
                    String.format(MSG_ESTADO_NAO_ENCONTRADO,estadoId));
        }  catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                String.format(MSG_ESTADO_EM_USO,estadoId));
        }
    }
}
