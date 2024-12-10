package com.matuzalem.produto_api.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.matuzalem.produto_api.model.Categoria;
import com.matuzalem.produto_api.model.Produto;
import com.matuzalem.produto_api.model.dto.ProdutoDTO;
import com.matuzalem.produto_api.repositories.CategoriaRepository;
import com.matuzalem.produto_api.repositories.ProdutoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    private final CategoriaRepository categoriaRepository;

    public List<ProdutoDTO> getAll() {
        List<Produto> produtos = produtoRepository.findAll();
        return produtos.stream()
            .map(ProdutoDTO::convert)
            .collect(Collectors.toList());
    }
    
    public ProdutoDTO findById(String id) {
        Produto produto = produtoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
        return ProdutoDTO.convert(produto);
    }

    public ProdutoDTO save(ProdutoDTO produtoDTO) {
        Categoria categoria = categoriaRepository.findById(produtoDTO.getCategoriaId())
            .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));
        Produto produto = Produto.convert(produtoDTO, categoria);
        produto = produtoRepository.save(produto);
        return ProdutoDTO.convert(produto);
    }

    public ProdutoDTO update(String id, ProdutoDTO produtoDTO) {
        Produto produto = produtoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
        if(produtoDTO.getName() != null && !produto.getName().equals(produtoDTO.getName())) {
            produto.setName(produtoDTO.getName());
        }
        if(produtoDTO.getDescription() != null && !produto.getDescription().equals(produtoDTO.getDescription())) {
            produto.setDescription(produtoDTO.getDescription());
        }
        if(produtoDTO.getPrice() != null && !produto.getPrice().equals(produtoDTO.getPrice())) {
            produto.setPrice(produtoDTO.getPrice());
        }
        produto = produtoRepository.save(produto);
        return ProdutoDTO.convert(produto);
    }

    public ProdutoDTO delete(String id) {
        Produto produto = produtoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
        produtoRepository.delete(produto);
        return ProdutoDTO.convert(produto);
    }

    public Page<ProdutoDTO> getAllPage(Pageable pageable) {
        Page<Produto> produtos = produtoRepository.findAll(pageable);
        return produtos.map(ProdutoDTO::convert);
    }

    public List<ProdutoDTO> findByCategoriaId(Categoria categoriaId) {
        List<Produto> produtos = produtoRepository.findByCategoryId(categoriaId);
        return produtos
            .stream()
            .map(ProdutoDTO::convert)
            .collect(Collectors.toList());
    }

    public ProdutoDTO findByProductIdentifier(String identificadorProduto) {
        Produto produto = produtoRepository.findByProductIdentifier(identificadorProduto);
        if(produto != null) 
            return ProdutoDTO.convert(produto);
        else
            throw new RuntimeException("Produto com identificador " + identificadorProduto + " não encontrado");
    }   
}
