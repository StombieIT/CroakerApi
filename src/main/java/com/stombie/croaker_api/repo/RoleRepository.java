package com.stombie.croaker_api.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stombie.croaker_api.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findRoleByName(String name);
}
