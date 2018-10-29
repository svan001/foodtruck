package com.svan001.foodtruck.service;

import com.svan001.foodtruck.domain.FoodItem;
import com.svan001.foodtruck.repository.FoodItemRepository;
import com.svan001.foodtruck.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional(rollbackOn = Exception.class)
public class FoodItemService {

    @Autowired
    private FoodItemRepository foodItemRepository;

    public FoodItem getById(Long id) throws NotFoundException {
        return foodItemRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id, ""));
    }

}
