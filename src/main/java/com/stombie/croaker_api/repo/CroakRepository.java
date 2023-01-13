package com.stombie.croaker_api.repo;

import com.stombie.croaker_api.entity.Croak;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CroakRepository extends JpaRepository<Croak, Long> {
}
