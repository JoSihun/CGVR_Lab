package com.skuniv.cgvr.post;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostsRepository extends JpaRepository<Posts, Integer> {
    Posts findByTitle(String title);
    Posts findByTitleOrContent(String title, String Content);
    Posts findByTitleAndContent(String title, String Content);
    List<Posts> findByTitleLike(String title);

}
