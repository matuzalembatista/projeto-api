package com.matuzalem.shopping_api.model.dto;


import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.matuzalem.shopping_api.model.Shopping;

import lombok.Data;

@Data
@Document(collection = "shop")
public class ShoppingDTO {
    @Id
    private String id;
    private String userIdentifier;
    private LocalDateTime date;
    private List<ItemDTO> items;
    private String total;

    public static ShoppingDTO convert(Shopping shopping) {
        ShoppingDTO shoppingDTO = new ShoppingDTO();
        shoppingDTO.setId(shopping.getId());
        shoppingDTO.setUserIdentifier(shopping.getUserIdentifier());
        shoppingDTO.setDate(shopping.getDate());
        shoppingDTO.setItems(
            shopping.getItems().stream()
                .map(item -> {
                    ItemDTO itemDTO = new ItemDTO();
                    itemDTO.setProductIdentifier(item.getProductIdentifier());
                    itemDTO.setPrice(item.getPrice());
                    return itemDTO;
                }).collect(Collectors.toList())
        );
        shoppingDTO.setTotal(shopping.getTotal());
        return shoppingDTO;
    }
}