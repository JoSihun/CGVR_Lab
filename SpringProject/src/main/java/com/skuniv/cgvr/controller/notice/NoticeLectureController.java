package com.skuniv.cgvr.controller.notice;

import com.skuniv.cgvr.dto.posts.PostsListResponseDto;
import com.skuniv.cgvr.dto.posts.PostsResponseDto;
import com.skuniv.cgvr.dto.posts.PostsSaveRequestDto;
import com.skuniv.cgvr.dto.posts.PostsUpdateRequestDto;
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
public class NoticeLectureController {
    private final PostsService postsService;


    /* 게시판 목록보기 */
    @GetMapping("notice/lecture/board")
    public String noticeLectureBoard(Model model) {
        // PostsRepository -> findByCategory~~~ 처리 후
        // PostsService -> findByCategory~~~ 추가한 뒤 카테고리별 추출처리필요
        List<PostsListResponseDto> responseDtoList = this.postsService.findAllDesc();
        model.addAttribute("posts", responseDtoList);
        return "notice_lecture_board";
    }


    /* 게시글 상세보기 */
    @GetMapping("notice/lecture/posts/{id}")
    public String noticeLecturePost(@PathVariable Long id, Model model) {
        PostsResponseDto responseDto = this.postsService.findById(id);
        model.addAttribute("posts", responseDto);
        return "notice_lecture_posts";
    }


    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////


    /* 게시글 작성폼 */
    @GetMapping("notice/lecture/posts/form")
    public String noticeLecturePostForm() {
        return "notice_lecture_posts_form";
    }


    /* 게시글 수정폼 */
    @GetMapping("notice/lecture/posts/update/{id}")
    public String noticeLecturePostUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto responseDto = this.postsService.findById(id);
        model.addAttribute("posts", responseDto);
        return "notice_lecture_posts_update_form";
    }


    /* 게시글 작성요청 */
    // RestController 처리예정
    // JavaScript AJAX 통신 처리예정
    @PostMapping("notice/lecture/posts/form")
    public String noticeLecturePostSave(PostsSaveRequestDto requestDto) {
        Long id = this.postsService.save(requestDto);
        return "redirect:/notice/lecture/posts/" + id;
    }


    /* 게시글 수정요청 */
    // PutMapping 처리예정
    // RestController 처리예정
    // JavaScript AJAX 통신 처리예정
    @PostMapping("notice/lecture/posts/update/{id}")
    public String noticeLecturePostUpdate(@PathVariable Long id, PostsUpdateRequestDto requestDto) {
        this.postsService.update(id, requestDto);
        return "redirect:/notice/lecture/posts/" + id;
    }


    /* 게시글 삭제요청 */
    // DeleteMapping 처리예정
    // RestController 처리예정
    // JavaScript AJAX 통신 처리예정
    // 왜인지는 모르나 현재 GetMapping으로 처리되고 있음
    @GetMapping("notice/lecture/posts/delete/{id}")
    public String noticeLecturePostDelete(@PathVariable Long id) {
        this.postsService.delete(id);
        return "redirect:/notice/lecture/board";
    }
}
