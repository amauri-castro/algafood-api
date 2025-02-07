package com.amauri.algafood.api.assembler;

import com.amauri.algafood.api.model.ProdutoModel;
import com.amauri.algafood.domain.model.Produto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProdutoModelAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public ProdutoModel toModel(Produto produto) {
        return modelMapper.map(produto, ProdutoModel.class);
    }

    public List<ProdutoModel> toCollectionModel(Collection<Produto> produtos) {
        return produtos.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

}
