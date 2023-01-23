package com.stombie.croaker_api.repo;

import com.stombie.croaker_api.entity.Croak;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CroakRepository extends JpaRepository<Croak, Long> {
    @Query("SELECT COUNT(c) FROM Croak c WHERE c.originalCroak.id = :originalCroakId")
    Long getCountByOriginalCroakId(Long originalCroakId);

    @Query("SELECT COUNT(c) FROM Croak c WHERE c.author.id = :authorId")
    Long getCountByAuthorId(Long authorId);

    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN TRUE ELSE FALSE END FROM Croak c WHERE c.originalCroak.id = :originalCroakId AND c.author.id = :userId")
    Boolean isActiveByOriginalCroakIdAndUserId(Long originalCroakId, Long userId);

    @Query("SELECT c " +
           "FROM Croak c " +
           "WHERE c.author.id = :authorId " +
           "ORDER BY c.creationDate DESC ")
    List<Croak> findAllByAuthorIdOrderingByCreationDateDesc(Long authorId);
}
