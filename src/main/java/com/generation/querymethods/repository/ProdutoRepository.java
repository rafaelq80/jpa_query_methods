package com.generation.querymethods.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.generation.querymethods.model.Produto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{

    public List <Produto> findByPrecoLessThan(BigDecimal preco);

    public List <Produto> findByPrecoLessThanEqual(BigDecimal preco);
    
    public List <Produto> findByPrecoGreaterThan(BigDecimal preco);

    public List <Produto> findByPrecoGreaterThanEqual(BigDecimal preco);

    public List <Produto> findByNomeAndFornecedor(String nome, String fornecedor);

    public List <Produto> findByNomeOrFornecedor(String nome, String fornecedor);

    public List <Produto> findByFornecedorNot(String fornecedor);

    public List <Produto> findByNomeOrderByDataValidade(String nome);

    public List <Produto> findByNomeOrderByDataValidadeDesc(String nome);

    public List <Produto> findByPrecoBetween(BigDecimal inicio, BigDecimal fim);

    public List <Produto> findByDataValidadeBetween(LocalDate inicio, LocalDate fim);

    public List <Produto> findByPrecoIn(List <BigDecimal> listaPreco);

    public List <Produto> findAllByNomeContainingIgnoreCase(@Param("nome") String nome);

    public List <Produto> findAllByNomeLikeIgnoreCase(@Param("nome") String nome);

    public List <Produto> findAllByNomeStartingWithIgnoreCase(@Param("nome") String nome);

    public List <Produto> findAllByNomeEndingWithIgnoreCase(@Param("nome") String nome);

    public List <Produto> findByDataValidadeBefore(LocalDate data);

    public List <Produto> findByDataValidadeAfter(LocalDate data);

    public List <Produto> findByDisponivelTrue();

    public List <Produto> findByDisponivelFalse();

}
