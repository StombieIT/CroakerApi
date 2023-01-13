package com.stombie.croaker_api.repo;

import com.stombie.croaker_api.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
