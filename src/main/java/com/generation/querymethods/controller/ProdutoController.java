package com.generation.querymethods.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import com.generation.querymethods.model.Produto;
import com.generation.querymethods.repository.ProdutoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/produtos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProdutoController {
    
    @Autowired
	private ProdutoRepository produtoRepository;
    
    @GetMapping
	public ResponseEntity<List<Produto>> getAll(){ 
		return ResponseEntity.ok(produtoRepository.findAll());
	}

    @GetMapping("/preco_menor/{preco}")
	public ResponseEntity<List<Produto>> getByPrecoMenor(@PathVariable BigDecimal preco){
		return ResponseEntity.ok(produtoRepository.findByPrecoLessThan(preco));
	}

    @GetMapping("/preco_menor_igual/{preco}")
	public ResponseEntity<List<Produto>> getByPrecoMenorIgual(@PathVariable BigDecimal preco){
		return ResponseEntity.ok(produtoRepository.findByPrecoLessThanEqual(preco));
	}

    @GetMapping("/preco_maior/{preco}")
	public ResponseEntity<List<Produto>> getByPrecoMaior(@PathVariable BigDecimal preco){
		return ResponseEntity.ok(produtoRepository.findByPrecoGreaterThan(preco));
	}

    @GetMapping("/preco_maior_igual/{preco}")
	public ResponseEntity<List<Produto>> getByPrecoMaiorIgual(@PathVariable BigDecimal preco){
		return ResponseEntity.ok(produtoRepository.findByPrecoGreaterThanEqual(preco));
	}

    @GetMapping("/nome/{nome}/efornecedor/{fornecedor}")
	public ResponseEntity<List<Produto>> getByNomeAndFornecedor(@PathVariable String nome, @PathVariable String fornecedor){
		return ResponseEntity.ok(produtoRepository.findByNomeAndFornecedor(nome, fornecedor));
	}

    @GetMapping("/nome/{nome}/oufornecedor/{fornecedor}")
	public ResponseEntity<List<Produto>> getByNomeOrFornecedor(@PathVariable String nome, @PathVariable String fornecedor){
		return ResponseEntity.ok(produtoRepository.findByNomeOrFornecedor(nome, fornecedor));
	}

    @GetMapping("/not_fornecedor/{fornecedor}")
	public ResponseEntity<List<Produto>> getByNotFornecedor(@PathVariable String fornecedor){
		return ResponseEntity.ok(produtoRepository.findByFornecedorNot(fornecedor));
	}

    @GetMapping("/nome_asc_data/{nome}")
	public ResponseEntity<List<Produto>> getByNomeOrderByDataValidade(@PathVariable String nome){
		return ResponseEntity.ok(produtoRepository.findByNomeOrderByDataValidade(nome));
	}

    @GetMapping("/nome_desc_data/{nome}")
	public ResponseEntity<List<Produto>> getByNomeOrderByDataValidadeDesc(@PathVariable String nome){
		return ResponseEntity.ok(produtoRepository.findByNomeOrderByDataValidadeDesc(nome));
	}

	@GetMapping("/preco_inicial/{inicio}/preco_final/{fim}")
	public ResponseEntity<List<Produto>> getByPrecoIntervalo(@PathVariable BigDecimal inicio, @PathVariable BigDecimal fim){
		return ResponseEntity.ok(produtoRepository.findByPrecoBetween(inicio, fim));
	}

	@GetMapping("/lista_preco/{preco01}/{preco02}/{preco03}")
	public ResponseEntity<List<Produto>> getByPrecoLista(@PathVariable BigDecimal preco01, @PathVariable BigDecimal preco02, @PathVariable BigDecimal preco03){
		List <BigDecimal> listaPreco = Arrays.asList(preco01, preco02, preco03);
		return ResponseEntity.ok(produtoRepository.findByPrecoIn(listaPreco));
	}

	@GetMapping("/nome_containing/{nome}")
	public ResponseEntity<List<Produto>> getByNomeContaining(@PathVariable String nome){
		return ResponseEntity.ok(produtoRepository.findAllByNomeContainingIgnoreCase(nome));
	}	
	
	@GetMapping("/nome_like/{nome}")
	public ResponseEntity<List<Produto>> getByNomeLike(@PathVariable String nome){
		return ResponseEntity.ok(produtoRepository.findAllByNomeLikeIgnoreCase(nome));
	}	

	@GetMapping("/nome_start/{nome}")
	public ResponseEntity<List<Produto>> getByNomeStart(@PathVariable String nome){
		return ResponseEntity.ok(produtoRepository.findAllByNomeStartingWithIgnoreCase(nome));
	}	

	@GetMapping("/nome_end/{nome}")
	public ResponseEntity<List<Produto>> getByNomeEnd(@PathVariable String nome){
		return ResponseEntity.ok(produtoRepository.findAllByNomeEndingWithIgnoreCase(nome));
	}	

	@GetMapping("/data_inicial/{inicio}/data_final/{fim}")
	public ResponseEntity<List<Produto>> getByDataValidadeIntervalo(@PathVariable String inicio, @PathVariable String fim){
		
		LocalDate data_start = LocalDate.parse(inicio, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		LocalDate data_end = LocalDate.parse(fim, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		
		return ResponseEntity.ok(produtoRepository.findByDataValidadeBetween(data_start, data_end));
	}

	@GetMapping("/data_anterior/{data}")
	public ResponseEntity<List<Produto>> getByDataValidadePassado(@PathVariable String data){

		LocalDate dataPast = LocalDate.parse(data, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

		return ResponseEntity.ok(produtoRepository.findByDataValidadeBefore(dataPast));
	}

	@GetMapping("/data_posterior/{data}")
	public ResponseEntity<List<Produto>> getByDataValidadeFuturo(@PathVariable String data){

		LocalDate dataFuture = LocalDate.parse(data, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

		return ResponseEntity.ok(produtoRepository.findByDataValidadeAfter(dataFuture));

	}
	
	@GetMapping("/disponivel")
	public ResponseEntity<List<Produto>> getByDisponivelTrue(){
		return ResponseEntity.ok(produtoRepository.findByDisponivelTrue());
	}	

	@GetMapping("/indisponivel")
	public ResponseEntity<List<Produto>> getByDisponivelFalse(){
		return ResponseEntity.ok(produtoRepository.findByDisponivelFalse());
	}	

}
