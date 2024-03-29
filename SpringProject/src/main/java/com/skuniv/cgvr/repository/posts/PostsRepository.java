package com.skuniv.cgvr.repository.posts;

import com.skuniv.cgvr.domain.posts.Posts;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostsRepository extends JpaRepository<Posts, Long> {

    /* 카테고리별 게시판 목록보기 - Sort */
    List<Posts> findAllByCategoryName(String categoryName, Sort sort);

    /* 프로젝트별 게시판 목록보기 - Sort */
    List<Posts> findAllByProjectName(String projectName, Sort sort);

    /* 카테고리별 게시판 목록보기 - Pageable */
    Page<Posts> findAllByCategoryName(String categoryName, Pageable pageable);

    /* 프로젝트별 게시판 목록보기 - Pageable */
    Page<Posts> findAllByProjectName(String projectName, Pageable pageable);


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /* 제목 검색 목록보기 */
    List<Posts> findByCategoryNameAndTitleContaining(String categoryName, String title, Sort sort);
    /* 내용 검색 목록보기 */
    List<Posts> findByCategoryNameAndContentContaining(String categoryName, String content, Sort sort);
    /* 작성자 검색 목록보기 */
    List<Posts> findByCategoryNameAndAuthorContaining(String categoryName, String content, Sort sort);
    /* 제목+작성자 검색 목록보기 */
    List<Posts> findByTitleLikeOrAuthorContaining(String categoryName, String title, String author, Sort sort);
    /* 카테고리별 제목+내용 검색 목록보기 and 안에 or을 묶어서 쓸 수가 없어서 @Query로 직접 작성 */
    @Query("select p from Posts p where p.categoryName = ?1 and (p.title like %?2% or p.content like %?3%)")
    List<Posts> findByCategoryNameAndTitleContainingOrContentContaining(String categoryName, String title, String content, Sort sort);

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /* 페이지네이션 적용 */
    /* 제목 검색 목록보기 */
    Page<Posts> findByCategoryNameAndTitleContaining(String categoryName, String title, Pageable pageable);
    /* 내용 검색 목록보기 */
    Page<Posts> findByCategoryNameAndContentContaining(String categoryName, String content, Pageable pageable);
    /* 작성자 검색 목록보기 */
    Page<Posts> findByCategoryNameAndAuthorContaining(String categoryName, String content, Pageable pageable);
    /* 제목+작성자 검색 목록보기 */
    Page<Posts> findByTitleLikeOrAuthorContaining(String categoryName, String title, String author, Pageable pageable);
    /* 카테고리별 제목+내용 검색 목록보기 and 안에 or을 묶어서 쓸 수가 없어서 @Query로 직접 작성 */
    @Query("select p from Posts p where p.categoryName = ?1 and (p.title like %?2% or p.content like %?3%)")
    Page<Posts> findByCategoryNameAndTitleContainingOrContentContaining(String categoryName, String title, String content, Pageable pageable);

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
