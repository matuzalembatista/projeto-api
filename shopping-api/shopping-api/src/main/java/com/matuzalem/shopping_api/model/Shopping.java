package com.matuzalem.shopping_api.model;


import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.matuzalem.shopping_api.model.Shopping;
import com.matuzalem.shopping_api.model.dto.ShoppingDTO;

import lombok.Data;


@Data
@Document(collection = "shopping")
public class Shopping {
    @Id
    private String id;
    private String userIdentifier;
    private LocalDateTime date;
    private List<Item> items;
    private String total;

    public static Shopping convert(ShoppingDTO shoppingDTO) {
        Shopping shopping = new Shopping();
        shopping.setUserIdentifier(shoppingDTO.getUserIdentifier());
        shopping.setDate(LocalDateTime.now());
        shopping.setItems(
            shoppingDTO.getItems()
                .stream()
                .map(itemDTO -> {
                    Item item = new Item();
                    item.setProductIdentifier(itemDTO.getProductIdentifier());
                    item.setPrice(itemDTO.getPrice());
                    return item;
                }).collect(Collectors.toList())
        );
        shopping.setTotal(shoppingDTO.getTotal());
        return shopping;
    }


    @Data
    public static class Item {
        private String productIdentifier;
        private String price;
    }

}
