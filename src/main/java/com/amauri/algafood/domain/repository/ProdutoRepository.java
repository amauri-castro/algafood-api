package com.amauri.algafood.domain.repository;

import com.amauri.algafood.domain.model.FotoProduto;
import com.amauri.algafood.domain.model.Produto;
import com.amauri.algafood.domain.model.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>, ProdutoRepositoryQueries {

    @Query("FROM Produto WHERE restaurante.id = :restaurante AND id = :produto")
    Optional<Produto> findById(@Param("restaurante") Long restauranteId, @Param("produto") Long produtoId);

    List<Produto> findTodosByRestaurante(Restaurante restaurante);

    @Query("FROM Produto p WHERE p.ativo = true AND p.restaurante = :restaurante")
    List<Produto> findAtivoByRestaurante(Restaurante restaurante);

    @Query("SELECT f FROM FotoProduto f JOIN f.produto p WHERE p.restaurante.id = :restauranteId " +
            "AND f.produto.id = :produtoId")
    Optional<FotoProduto> findFotoById(Long restauranteId, Long produtoId);
}
