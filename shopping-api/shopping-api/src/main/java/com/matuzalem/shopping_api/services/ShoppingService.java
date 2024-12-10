package com.matuzalem.shopping_api.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.matuzalem.shopping_api.model.Shopping;
import com.matuzalem.shopping_api.model.dto.ShoppingDTO;
import com.matuzalem.shopping_api.repositories.ShoppingRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ShoppingService {

    private final ShoppingRepository shoppingRepository;

    public List<ShoppingDTO> getAll() {
        List<Shopping> shopping = shoppingRepository.findAll();
        return shopping.stream()
            .map(ShoppingDTO::convert)
            .collect(Collectors.toList());
    }

    public ShoppingDTO findById(String id) {
        Shopping shop = shoppingRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Purchase not found"));
        return ShoppingDTO.convert(shop);
    }

    public ShoppingDTO save(ShoppingDTO shopDTO) {
        Shopping shop = shoppingRepository.save(Shopping.convert(shopDTO));
        return ShoppingDTO.convert(shop);
    }

    public List<ShoppingDTO> findByUser(String userIdentifier) {
        List<Shopping> shopping = shoppingRepository.findByUserIdentifier(userIdentifier);
        return shopping
            .stream()
            .map(ShoppingDTO::convert)
            .collect(Collectors.toList());
    }

    public List<ShoppingDTO> findByDate(LocalDateTime date) {
        List<Shopping> shops = shoppingRepository.findByDate(date);
        if (shops.isEmpty()) {
            throw new RuntimeException("No purchases found for this date");
        }
        return shops.stream()
                    .map(ShoppingDTO::convert)
                    .collect(Collectors.toList());
    }

    public List<ShoppingDTO> findByProductIdentifier(String productIdentifier) {
        List<Shopping> shops = shoppingRepository.findByItemsProductIdentifier(productIdentifier);
        if (shops.isEmpty()) {
            throw new RuntimeException("No purchases found for this product");
        }
        return shops.stream()
                .map(ShoppingDTO::convert)
                .collect(Collectors.toList());
    }

    public List<ShoppingDTO> findShopsByFilter(LocalDateTime startDate, LocalDateTime endDate, String minimumValue) {
        List<Shopping> shops;

        if(startDate != null && endDate != null && minimumValue != null) {
            shops = shoppingRepository.findByDateBetweenAndTotalGreaterThan(startDate, endDate, minimumValue);
        } else if(startDate != null && endDate != null) {
            shops = shoppingRepository.findByDateBetween(startDate, endDate);
        } else if(minimumValue != null) {
            shops = shoppingRepository.findByTotalGreaterThan(minimumValue);
        } else {
            throw new IllegalArgumentException("At least one filter must be provided.");
        }
        return shops.stream()
                .map(ShoppingDTO::convert)
                .collect(Collectors.toList());
    }

    public List<ShoppingDTO> getReportByDate(LocalDateTime startDate, LocalDateTime endDate) {
        List<Shopping> shops = shoppingRepository.findByDateBetween(startDate, endDate);
        if (shops.isEmpty()) {
            throw new RuntimeException("No purchases found for this date range");
        }
        return shops
            .stream()
            .map(ShoppingDTO::convert)
            .collect(Collectors.toList());
    }
}