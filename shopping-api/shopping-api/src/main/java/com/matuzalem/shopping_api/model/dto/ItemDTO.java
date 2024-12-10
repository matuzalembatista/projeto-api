package com.matuzalem.shopping_api.model.dto;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "item")
public class ItemDTO {

    private String productIdentifier;
    private String price;
}
