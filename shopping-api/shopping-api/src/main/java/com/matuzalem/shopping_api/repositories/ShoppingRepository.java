package com.matuzalem.shopping_api.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.matuzalem.shopping_api.model.Shopping;

@Repository
public interface ShoppingRepository extends MongoRepository<Shopping, String> {
    List<Shopping> findByUserIdentifier(String userIdentifier);

    List<Shopping> findByDate(LocalDateTime date);

    List<Shopping> findByItemsProductIdentifier(String productIdentifier);
    
    List<Shopping> findByDateBetween(LocalDateTime startDate, LocalDateTime endDate);

    List<Shopping> findByDateBetweenAndTotalGreaterThan(LocalDateTime startDate, LocalDateTime endDate, String total);

    List<Shopping> findByTotalGreaterThan(String total);
}
