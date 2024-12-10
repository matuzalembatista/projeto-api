package com.matuzalem.produto_api.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.matuzalem.produto_api.model.Categoria;
import com.matuzalem.produto_api.model.Produto;

@Repository
public interface ProdutoRepository extends MongoRepository<Produto, String> {

    List<Produto> findByCategoryId(Categoria categoriaId);
    
    Produto findByProductIdentifier(String productIdentifier);
}