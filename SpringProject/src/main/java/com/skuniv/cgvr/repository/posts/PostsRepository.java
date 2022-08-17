package com.skuniv.cgvr.repository.posts;

import com.skuniv.cgvr.domain.posts.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostsRepository extends JpaRepository<Posts, Long> {
    // 테스트 및 일원화 필요
    List<Posts> findAllByCategoryName(String categoryName);


    // 필요한 메소드, List<>로 처리하는게 맞는지 확인요망
    Posts findByTitle(String title);
    Posts findByTitleOrContent(String title, String content);
    Posts findByTitleAndContent(String title, String content);


    // 참고용 메소드, List<>로 처리하는게 맞는지 확인요망
    Posts findByTitleOrderByCreatedDateAsc(String title);
    Posts findByTitleOrderByCreatedDateDesc(String title);
    List<Posts> findByTitleLike(String title);
}
