package com.skuniv.cgvr.repository.posts;

import com.skuniv.cgvr.domain.posts.Posts;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostsRepository extends JpaRepository<Posts, Long> {
    /* 카테고리별 게시판 목록보기 */
//    List<Posts> findByCategoryName(String categoryName, Sort sort);


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /* 제목 검색 목록보기 */
    List<Posts> findByTitleLike(String title, Sort sort);
    /* 내용 검색 목록보기 */
    List<Posts> findByContentLike(String content, Sort sort);
    /* 작성자 검색 목록보기 */
    List<Posts> findByAuthorLike(String content, Sort sort);
    /* 제목+내용 검색 목록보기 */
    List<Posts> findByTitleLikeOrContentLike(String title, String content, Sort sort);


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // 참고용 메소드, List<>로 처리하는게 맞는지 확인요망
    Posts findByTitle(String title);
    Posts findByTitleOrContent(String title, String content);
    Posts findByTitleAndContent(String title, String content);


    // 참고용 메소드, List<>로 처리하는게 맞는지 확인요망
    Posts findByTitleOrderByCreatedDateAsc(String title);
    Posts findByTitleOrderByCreatedDateDesc(String title);
    List<Posts> findByTitleLike(String title);
}
