package com.stombie.croaker_api.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.stombie.croaker_api.entity.User;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByUsername(String username);

    @Query("SELECT COUNT(f) FROM User u JOIN u.followers f WHERE u.id = :userId")
    Long getFollowersCountByUserId(Long userId);

    @Query("SELECT COUNT(f) FROM User u JOIN u.followers f WHERE f.id = :userId")
    Long getFollowingCountByUserId(Long userId);

    @Query("SELECT " +
           "CASE WHEN COUNT(u) > 0 THEN TRUE ELSE FALSE END " +
           "FROM User u " +
           "JOIN u.followers f " +
           "WHERE f.id = :checkerId " +
           "AND u.id = :userId")
    Boolean isActiveByUserIdAndCheckerId(Long userId, Long checkerId);
}
