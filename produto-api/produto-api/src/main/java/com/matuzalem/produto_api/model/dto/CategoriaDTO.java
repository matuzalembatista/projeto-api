package com.matuzalem.produto_api.model.dto;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.matuzalem.produto_api.model.Categoria;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "categoria")
public class CategoriaDTO {
    @Id
    private String id;
    private String name;

    public static CategoriaDTO convert(Categoria categoria) {
        CategoriaDTO categoriaDTO = new CategoriaDTO();
        categoriaDTO.setId(categoria.getId());
        categoriaDTO.setName(categoria.getName());
        return categoriaDTO;
    }
}