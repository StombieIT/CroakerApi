package com.stombie.croaker_api.repo;

import com.stombie.croaker_api.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
