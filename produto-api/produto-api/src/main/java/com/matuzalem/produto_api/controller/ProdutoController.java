package com.matuzalem.produto_api.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.matuzalem.produto_api.model.Categoria;
import com.matuzalem.produto_api.model.dto.ProdutoDTO;
import com.matuzalem.produto_api.services.ProdutoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/produto")
@RequiredArgsConstructor
public class ProdutoController {

    private final ProdutoService produtoService;

    @GetMapping
    public List<ProdutoDTO> getProdutos() {
        return produtoService.getAll();
    }

    @GetMapping("/{id}")
    public ProdutoDTO getProdutoById(@PathVariable String id) {
        return produtoService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProdutoDTO criarProduto(@RequestBody ProdutoDTO produtoDTO) {
        produtoDTO.setProductIdentifier(UUID.randomUUID().toString());         
        if (produtoDTO.getCategoriaDTO() != null) {
            produtoDTO.setCategoriaId(produtoDTO.getCategoriaDTO().getId());
        }
        return produtoService.save(produtoDTO);
    }

    @PutMapping("/{id}")
    public ProdutoDTO editarProduto(@PathVariable String id, @RequestBody ProdutoDTO produtoDTO) {
        return produtoService.update(id, produtoDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable String id) {
        produtoService.delete(id);
    }

    @GetMapping("/pageable")
    public Page<ProdutoDTO> getProdutosPageable(Pageable pageable) {
        return produtoService.getAllPage(pageable);
    }

    @GetMapping("/categoria/{categoriaId}")
    public List<ProdutoDTO> getProdutoByCategoriaId(@PathVariable Categoria categoriaId) {
        return produtoService.findByCategoriaId(categoriaId);
    }

    @GetMapping("/identificador/{productIdentifier}")
    public ProdutoDTO getProdutoByIdentifier(@PathVariable String productIdentifier) {
        return produtoService.findByProductIdentifier(productIdentifier);
    }
}

