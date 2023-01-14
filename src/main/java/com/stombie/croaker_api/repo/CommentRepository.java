package com.stombie.croaker_api.repo;

import com.stombie.croaker_api.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query("SELECT COUNT(c) FROM Comment c WHERE c.croak.id = :croakId")
    Long getCountByCroakId(Long croakId);

    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN TRUE ELSE FALSE END FROM Comment c WHERE c.croak.id = :croakId AND c.author.id = :userId")
    Boolean isActiveByCroakIdAndUserId(Long croakId, Long userId);
}
