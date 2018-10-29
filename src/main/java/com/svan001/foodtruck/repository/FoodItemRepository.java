package com.svan001.foodtruck.repository;

import com.svan001.foodtruck.domain.FoodItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodItemRepository extends JpaRepository<FoodItem, Long> {
}
