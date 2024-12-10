package com.matuzalem.produto_api.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.matuzalem.produto_api.model.dto.CategoriaDTO;

import lombok.Data;

@Data
@Document(collection = "categoria")
public class Categoria {
    @Id
    private String id;

    private String name;

    public static Categoria convert(CategoriaDTO categoriaDTO) {
        Categoria category = new Categoria();
        category.setName(categoriaDTO.getName());
        return category;
    }
}
