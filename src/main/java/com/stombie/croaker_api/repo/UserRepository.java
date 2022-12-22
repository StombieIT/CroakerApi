package com.stombie.croaker_api.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.stombie.croaker_api.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByUsername(String username);
}
