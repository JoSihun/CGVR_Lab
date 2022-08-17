package com.skuniv.cgvr.repository.posts;

import com.skuniv.cgvr.domain.posts.Comments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentsRepository extends JpaRepository<Comments, Long> {
}
