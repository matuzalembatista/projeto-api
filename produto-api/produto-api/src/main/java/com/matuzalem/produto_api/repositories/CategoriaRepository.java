package com.matuzalem.produto_api.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.matuzalem.produto_api.model.Categoria;

@Repository
public interface CategoriaRepository extends MongoRepository<Categoria, String> {
    
}