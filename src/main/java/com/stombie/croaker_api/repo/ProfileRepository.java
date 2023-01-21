package com.stombie.croaker_api.repo;

import com.stombie.croaker_api.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
}
