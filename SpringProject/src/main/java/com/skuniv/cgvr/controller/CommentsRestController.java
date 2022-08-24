package com.skuniv.cgvr.controller;

import com.skuniv.cgvr.dto.posts.CommentsSaveRequestDto;
import com.skuniv.cgvr.dto.posts.CommentsUpdateRequestDto;
import com.skuniv.cgvr.service.posts.CommentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class CommentsRestController {
    private final CommentsService commentsService;

    /* 댓글 등록요청 */
    @PostMapping("/posts/api/{postsId}/comments")
    public Long commentsSave(@PathVariable Long postsId, @RequestBody CommentsSaveRequestDto requestDto) {
        return commentsService.save(postsId, requestDto);
    }

    /* 댓글 수정요청 */
    @PutMapping("/posts/api/comments/{id}")
    public Long commentsUpdate(@PathVariable Long id, @RequestBody CommentsUpdateRequestDto requestDto) {
        return commentsService.update(id, requestDto);
    }

    /* 댓글 삭제요청 */
    @DeleteMapping("/posts/api/comments/{id}")
    public Long commentDelete(@PathVariable Long id) {
        commentsService.delete(id);
        return id;
    }
}
