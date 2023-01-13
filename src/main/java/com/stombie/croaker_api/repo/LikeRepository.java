package com.stombie.croaker_api.repo;

import com.stombie.croaker_api.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LikeRepository extends JpaRepository<Like, Long> {

    @Query("SELECT COUNT(l) FROM Like l WHERE l.croak.id = :croakId")
    Long getCountByCroakId(Long croakId);

    @Query("SELECT CASE WHEN COUNT(l) > 0 THEN TRUE ELSE FALSE END FROM Like l WHERE l.croak.id = :croakId AND l.author.id = :userId")
    Boolean isActiveByCroakIdAndUserId(Long croakId, Long userId);
}
