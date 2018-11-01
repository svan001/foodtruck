package com.svan001.foodtruck.bootstrap;

import com.svan001.foodtruck.domain.FoodItem;
import com.svan001.foodtruck.domain.User;
import com.svan001.foodtruck.repository.FoodItemRepository;
import com.svan001.foodtruck.repository.UserRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private UserRepository userRepository;
    private FoodItemRepository foodItemRepository;

    public DevBootstrap(
            FoodItemRepository foodItemRepository,
            UserRepository userRepository
    ) {
        this.foodItemRepository = foodItemRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initUser();
        initFoodItem();
    }

    private void initUser() {
        User admin = new User("admin", "admin");

        userRepository.save(admin);
    }

    private void initFoodItem() {
        FoodItem burger = new FoodItem("Burger", 9.50);

        foodItemRepository.save(burger);
    }
}
