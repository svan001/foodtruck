package com.svan001.foodtruck.controller;

import com.svan001.foodtruck.domain.FoodItem;
import com.svan001.foodtruck.service.FoodItemService;
import com.svan001.foodtruck.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("foodItem")
public class FoodItemController {

    @Autowired
    private FoodItemService foodItemService;

    @GetMapping("/{id}")
    public FoodItem getById(@PathVariable Long id) throws NotFoundException {
        return foodItemService.getById(id);
    }
}
