package com.matuzalem.shopping_api.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.matuzalem.shopping_api.model.dto.ShoppingDTO;
import com.matuzalem.shopping_api.services.ShoppingService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/shopping")
@RequiredArgsConstructor
public class ShoppingController {

    private final ShoppingService shoppingService;

    @GetMapping
    public List<ShoppingDTO> getShoppings() {
        return shoppingService.getAll();
    }

    @GetMapping("/{id}")
    public ShoppingDTO getShoppingById(@PathVariable String id) {
        return shoppingService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ShoppingDTO createShopping(@RequestBody ShoppingDTO shoppingDTO) {
        return shoppingService.save(shoppingDTO);
    }

    @GetMapping("/shopByUser")
    public List<ShoppingDTO> findShoppingByUser(@RequestParam String userIdentifier) {
        return shoppingService.findByUser(userIdentifier);
    }

    // ao testar esse endpoint o formato da data deve ser yyyy-MM-ddTHH:mm:ss (e.g 2024-12-09T11:48:04.042)
    @GetMapping("/shopByDate")
    public List<ShoppingDTO> findShoppingByDate(@RequestParam String date) {
        LocalDateTime localDateTime = LocalDateTime.parse(date, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        return shoppingService.findByDate(localDateTime);
    }

    @GetMapping("/identifier/{productIdentifier}")
    public List<ShoppingDTO> findShoppingByProductIdentifier(@PathVariable String productIdentifier) {
        return shoppingService.findByProductIdentifier(productIdentifier);
    }

    @GetMapping("/search")
    public List<ShoppingDTO> findShopsByFilter(
        @RequestParam(required = false) LocalDateTime startDate,
        @RequestParam(required = false) LocalDateTime endDate,
        @RequestParam(required = false) String minimumValue) {
            return shoppingService.findShopsByFilter(startDate, endDate, minimumValue);
        }

    @GetMapping("/report")
    public List<ShoppingDTO> getReportByDate(
        @RequestParam LocalDateTime startDate,
        @RequestParam LocalDateTime endDate) {
            return shoppingService.getReportByDate(startDate, endDate);
        }
}