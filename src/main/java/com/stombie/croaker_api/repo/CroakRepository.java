package com.stombie.croaker_api.repo;

import com.stombie.croaker_api.entity.Croak;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CroakRepository extends JpaRepository<Croak, Long> {
    @Query("SELECT COUNT(c) FROM Croak c WHERE c.originalCroak.id = :originalCroakId")
    Long getCountByOriginalCroakId(Long originalCroakId);

    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN TRUE ELSE FALSE END FROM Croak c WHERE c.originalCroak.id = :originalCroakId AND c.author.id = :userId")
    Boolean isActiveByOriginalCroakIdAndUserId(Long originalCroakId, Long userId);
}
