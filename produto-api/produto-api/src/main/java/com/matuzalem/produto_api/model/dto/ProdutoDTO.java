package com.matuzalem.produto_api.model.dto;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.matuzalem.produto_api.model.Produto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "produto")
public class ProdutoDTO {
    @Id
    private String id;
    private String productIdentifier;
    private String name;
    private String description;
    private String price;

    @JsonIgnore
    private String categoriaId;
    private CategoriaDTO categoriaDTO;

    public static ProdutoDTO convert(Produto produto) {
        ProdutoDTO produtoDTO = new ProdutoDTO();
        produtoDTO.setId(produto.getId());
        produtoDTO.setProductIdentifier(produto.getProductIdentifier());
        produtoDTO.setName(produto.getName());
        produtoDTO.setDescription(produto.getDescription());
        produtoDTO.setPrice(produto.getPrice());

        produtoDTO.setCategory(CategoriaDTO.convert(produto.getCategoria()));
        return produtoDTO;
    }

    public void setCategory(CategoriaDTO categoriaDTO) {
        this.categoriaDTO = categoriaDTO;
        if(categoriaDTO != null) {
            this.categoriaId = categoriaDTO.getId();
        }
    }
}
