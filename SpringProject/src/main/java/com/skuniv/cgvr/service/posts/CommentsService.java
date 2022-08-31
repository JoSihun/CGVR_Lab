package com.skuniv.cgvr.service.posts;

import com.skuniv.cgvr.domain.posts.Comments;
import com.skuniv.cgvr.domain.posts.Posts;
import com.skuniv.cgvr.dto.posts.CommentsListResponseDto;
import com.skuniv.cgvr.dto.posts.CommentsSaveRequestDto;
import com.skuniv.cgvr.dto.posts.CommentsUpdateRequestDto;
import com.skuniv.cgvr.repository.posts.CommentsRepository;
import com.skuniv.cgvr.repository.posts.PostsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CommentsService {
    private final PostsRepository postsRepository;
    private final CommentsRepository commentsRepository;


    /* 댓글 불러오기 */
    @Transactional
    public List<CommentsListResponseDto> findAllByPostId(Long postId) {
        Posts entity = this.postsRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다. id=" + postId));
        List<Comments> commentsList = entity.getCommentsList();
        return commentsList.stream().map(CommentsListResponseDto::new).collect(Collectors.toList());
    }


    /* 댓글 저장하기 */
    @Transactional
    public Long save(Long postId, CommentsSaveRequestDto requestDto) {
        Posts entity = this.postsRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다. id=" + postId));
        requestDto.setPosts(entity);
        return this.commentsRepository.save(requestDto.toEntity()).getId();
    }


    /* 댓글 수정하기 */
    @Transactional
    public Long update(Long id, CommentsUpdateRequestDto requestDto) {
        Comments entity = this.commentsRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 댓글이 존재하지 않습니다. id=" + id));
        entity.update(requestDto.getContent());
        return id;
    }


    /* 댓글 삭제하기 */
    @Transactional
    public Long delete(Long id) {
        Comments entity = this.commentsRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("이미 존재하지 않는 댓글입니다. id=" + id));
        this.commentsRepository.delete(entity);
        return id;
    }
}
