package com.skuniv.cgvr.service.posts;

import com.skuniv.cgvr.domain.posts.Posts;
import com.skuniv.cgvr.dto.posts.PostsListResponseDto;
import com.skuniv.cgvr.dto.posts.PostsResponseDto;
import com.skuniv.cgvr.dto.posts.PostsSaveRequestDto;
import com.skuniv.cgvr.dto.posts.PostsUpdateRequestDto;
import com.skuniv.cgvr.repository.posts.PostsRepository;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.TypeCache;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.TransactionScoped;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    /* 전체 게시판 목록보기 - 작성순 */
    @Transactional
    public List<PostsListResponseDto> findAllAsc() {
        // 최초작성일자 기준으로 정렬하려면 id 대신 createdDate 사용
        // 최종수정일자 기준으로 정렬하려면 id 대신 updatedDate 사용
        Sort sort = Sort.by(Sort.Direction.ASC, "id");
        List<Posts> postsList = this.postsRepository.findAll(sort);
        return postsList.stream().map(PostsListResponseDto::new).collect(Collectors.toList());
    }


    /* 전체 게시판 목록보기 - 최신순 */
    @Transactional
    public List<PostsListResponseDto> findAllDesc() {
        // 최초작성일자 기준으로 정렬하려면 id 대신 createdDate 사용
        // 최종수정일자 기준으로 정렬하려면 id 대신 updatedDate 사용
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        List<Posts> postsList = this.postsRepository.findAll(sort);
        return postsList.stream().map(PostsListResponseDto::new).collect(Collectors.toList());
    }


    /* 카테고리별 게시판 목록보기 - 작성순 */
    @Transactional
    public List<PostsListResponseDto> findAllByCategoryNameAsc(String categoryName) {
        // 최초작성일자 기준으로 정렬하려면 id 대신 createdDate 사용
        // 최종수정일자 기준으로 정렬하려면 id 대신 updatedDate 사용
        Sort sort = Sort.by(Sort.Direction.ASC, "id");
        List<Posts> postsList = this.postsRepository.findByCategoryName(categoryName, sort);
        return postsList.stream().map(PostsListResponseDto::new).collect(Collectors.toList());
    }


    /* 카테고리별 게시판 목록보기 - 최신순 */
    @Transactional
    public List<PostsListResponseDto> findAllByCategoryNameDesc(String categoryName) {
        // 최초작성일자 기준으로 정렬하려면 id 대신 createdDate 사용
        // 최종수정일자 기준으로 정렬하려면 id 대신 updatedDate 사용
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        List<Posts> postsList = this.postsRepository.findByCategoryName(categoryName, sort);
        return postsList.stream().map(PostsListResponseDto::new).collect(Collectors.toList());
    }

    /* 제목으로 검색 게시판 목록보기 - 최신순 */
    @Transactional
    public List<PostsListResponseDto> findAllByTitle(String categoryName, String title) {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        List<Posts> postsList = this.postsRepository.findByCategoryNameAndTitleContaining(categoryName, title, sort);
        return postsList.stream().map(PostsListResponseDto::new).collect(Collectors.toList());
    }

    /* 내용으로 검색 게시판 목록보기 - 최신순 */
    @Transactional
    public List<PostsListResponseDto> findAllByContent(String categoryName, String content) {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        List<Posts> postsList = this.postsRepository.findByCategoryNameAndContentContaining(categoryName, content, sort);
        return postsList.stream().map(PostsListResponseDto::new).collect(Collectors.toList());
    }

    /* 작성자로 검색 게시판 목록보기 - 최신순 */
    @Transactional
    public List<PostsListResponseDto> findAllByAuthor(String categoryName, String author) {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        List<Posts> postsList = this.postsRepository.findByCategoryNameAndAuthorContaining(categoryName, author, sort);
        return postsList.stream().map(PostsListResponseDto::new).collect(Collectors.toList());
    }

    /* 제목&내용으로 검색 게시판 목록보기 - 최신순 */
    @Transactional
    public List<PostsListResponseDto> findAllByTitleOrContent(String categoryName, String search) {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        List<Posts> postsList = this.postsRepository.findByCategoryNameAndTitleContainingOrContentContaining(categoryName, search, search, sort);
        for(Posts post : postsList) {
            System.out.println(post.getCategoryName() + post.getTitle());
        }
        return postsList.stream().map(PostsListResponseDto::new).collect(Collectors.toList());
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /* 게시글 상세조회 */
    @Transactional
    public PostsResponseDto findById(final Long id) {
        Posts entity = this.postsRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다. id = " + id));
        entity.increaseHits();
        return new PostsResponseDto(entity);
    }


    /* 게시글 저장하기 */
    @Transactional
    public Long save(final PostsSaveRequestDto requestDto) {
        return this.postsRepository.save(requestDto.toEntity()).getId();
    }


    /* 게시글 수정하기 */
    @Transactional
    public Long update(final Long id, final PostsUpdateRequestDto requestDto) {
        Posts entity = this.postsRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다. id=" + id));
        entity.update(requestDto.getTitle(), requestDto.getContent(), requestDto.getAttachment_id(),
                requestDto.getProjectName(), requestDto.getCategoryName());
        return id;
    }


    /* 게시글 삭제하기 */
    @Transactional
    public Long delete(final Long id) {
        Posts entity = this.postsRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("이미 존재하지 않는 게시글입니다. id=" + id));
        this.postsRepository.delete(entity);
        return id;
    }

}
