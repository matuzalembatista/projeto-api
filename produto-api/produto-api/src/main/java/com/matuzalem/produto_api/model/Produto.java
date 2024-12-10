package com.matuzalem.produto_api.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.matuzalem.produto_api.model.dto.ProdutoDTO;

import lombok.Data;

@Data
@Document(collection = "produto")
public class Produto {
    @Id
    private String id;
    @Field(name = "productIdentifier")
    private String productIdentifier;
    private String name;
    private String description;
    private String price;

    @DBRef
    private Categoria categoria;
    
    public static Produto convert(ProdutoDTO produtoDTO, Categoria categoria) {
        Produto produto = new Produto();
        produto.setProductIdentifier(produtoDTO.getProductIdentifier());
        produto.setName(produtoDTO.getName());
        produto.setDescription(produtoDTO.getDescription());
        produto.setPrice(produtoDTO.getPrice());
        produto.setCategoria(categoria);
        return produto;
    }
}
