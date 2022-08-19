package com.skuniv.cgvr.controller.notice;

import com.skuniv.cgvr.dto.posts.PostsSaveRequestDto;
import com.skuniv.cgvr.dto.posts.PostsUpdateRequestDto;
import com.skuniv.cgvr.service.posts.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
@RestController
public class NoticeAllRestController {
    private final PostsService postsService;

    /* 게시글 작성요청 */
    @PostMapping("/notice/all/api/posts")
    public Long noticeAllPostSave(@RequestBody PostsSaveRequestDto requestDto) {
        return postsService.save(requestDto);
    }

    /* 게시글 수정요청 */
    @PutMapping("/notice/all/api/posts/{id}")
    public Long noticeAllPostUpdate(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto) {
        return postsService.update(id, requestDto);
    }

    /* 게시글 삭제요청 */
    @DeleteMapping("/notice/all/api/posts/{id}")
    public Long noticeAllPostDelete(@PathVariable Long id) {
        postsService.delete(id);
        return id;
    }
}
