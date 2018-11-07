package com.svan001.foodtruck.repository;

import com.svan001.foodtruck.domain.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
}
