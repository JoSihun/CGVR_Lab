package com.skuniv.cgvr.controller.notice;

import com.skuniv.cgvr.dto.posts.*;
import com.skuniv.cgvr.service.posts.CommentsService;
import com.skuniv.cgvr.service.posts.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class NoticeNormalController {
    private final PostsService postsService;
    private final CommentsService commentsService;


    /* 게시판 목록보기 */
    @GetMapping("notice/normal/board")
    public String noticeNormalBoard(Model model) {
        List<PostsListResponseDto> responseDtoList = this.postsService.findAllByCategoryNameDesc("일반");
        model.addAttribute("posts", responseDtoList);
        return "notice_normal_board";
    }


    /* 게시글 상세보기 */
    // 댓글 기능을 추가하였으나, 테스트 해보지 않았음
    @GetMapping("notice/normal/posts/{id}")
    public String noticeNormalPost(@PathVariable Long id, Model model) {
        PostsResponseDto responseDto = this.postsService.findById(id);
        List<CommentsListResponseDto> responseDtoList = this.commentsService.findAllByPostId(id);
        model.addAttribute("posts", responseDto);
        model.addAttribute("comments", responseDtoList);
        model.addAttribute("commentsSize", responseDtoList.size());
        return "notice_normal_posts";
    }


    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////


    /* 게시글 작성폼 */
    @GetMapping("notice/normal/posts/form")
    public String noticeNormalPostForm() {
        return "notice_normal_posts_form";
    }


    /* 게시글 수정폼 */
    @GetMapping("notice/normal/posts/update/{id}")
    public String noticeNormalPostUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto responseDto = this.postsService.findById(id);
        model.addAttribute("posts", responseDto);
        return "notice_normal_posts_update_form";
    }


    /* 게시글 작성요청 */
    // RestController 처리예정
    // JavaScript AJAX 통신 처리예정
    @PostMapping("notice/normal/posts/form")
    public String noticeNormalPostSave(PostsSaveRequestDto requestDto) {
        Long id = this.postsService.save(requestDto);
        return "redirect:/notice/normal/posts/" + id;
    }


    /* 게시글 수정요청 */
    // PutMapping 처리예정
    // RestController 처리예정
    // JavaScript AJAX 통신 처리예정
    @PostMapping("notice/normal/posts/update/{id}")
    public String noticeNormalPostUpdate(@PathVariable Long id, PostsUpdateRequestDto requestDto) {
        this.postsService.update(id, requestDto);
        return "redirect:/notice/normal/posts/" + id;
    }


    /* 게시글 삭제요청 */
    // DeleteMapping 처리예정
    // RestController 처리예정
    // JavaScript AJAX 통신 처리예정
    // 현재 <a href=""> 사용하여 GetMapping으로 처리되고 있음
    @GetMapping("notice/normal/posts/delete/{id}")
    public String noticeNormalPostDelete(@PathVariable Long id) {
        this.postsService.delete(id);
        return "redirect:/notice/normal/board";
    }


    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////


    /* 댓글 등록요청 */
    // RestController 처리예정
    // JavaScript AJAX 통신 처리예정
    // 루틴에 맞게 구현은 하였으나, 테스트 해보지 않았음
    // 현재 <a href=""> 사용하여 GetMapping으로 처리되고 있음
//    @PostMapping("notice/normal/posts/{id}/comments")
//    public String CommentSave(@PathVariable Long id, CommentsSaveRequestDto requestDto) {
//        this.commentsService.save(id, requestDto);
//        return "redirect:/notice/normal/posts/" + id;
//    }
}
