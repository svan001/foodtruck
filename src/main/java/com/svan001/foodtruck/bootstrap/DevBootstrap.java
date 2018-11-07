package com.svan001.foodtruck.bootstrap;

import com.svan001.foodtruck.domain.FoodItem;
import com.svan001.foodtruck.domain.User;
import com.svan001.foodtruck.domain.UserRole;
import com.svan001.foodtruck.repository.FoodItemRepository;
import com.svan001.foodtruck.repository.UserRepository;
import com.svan001.foodtruck.util.enums.Role;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import static java.util.Arrays.asList;

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
        admin.addRole(new UserRole(admin, Role.ADMIN));

        User manager1 = new User("manager1", "admin");
        manager1.addRole(new UserRole(manager1, Role.MANAGER));

        User customer1 = new User("customer1", "admin");
        customer1.addRole(new UserRole(customer1, Role.CUSTOMER));

        userRepository.saveAll(asList(
                admin, manager1, customer1
        ));
    }

    private void initFoodItem() {
        FoodItem burger = new FoodItem("Burger", 9.50);

        foodItemRepository.save(burger);
    }
}
