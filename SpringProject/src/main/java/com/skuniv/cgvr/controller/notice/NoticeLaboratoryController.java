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
public class NoticeLaboratoryController {
    private final PostsService postsService;


    /* 게시판 목록보기 */
    @GetMapping("notice/laboratory/board")
    public String noticeLaboratoryBoard(Model model) {
        List<PostsListResponseDto> responseDtoList = this.postsService.findAllByCategoryNameDesc("연구");
        model.addAttribute("posts", responseDtoList);
        return "notice_laboratory_board";
    }


    /* 게시글 상세보기 */
    @GetMapping("notice/laboratory/posts/{id}")
    public String noticeLaboratoryPost(@PathVariable Long id, Model model) {
        PostsResponseDto responseDto = this.postsService.findById(id);
        model.addAttribute("posts", responseDto);
        return "notice_laboratory_posts";
    }


    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////


    /* 게시글 작성폼 */
    @GetMapping("notice/laboratory/posts/form")
    public String noticeLaboratoryPostForm() {
        return "notice_laboratory_posts_form";
    }


    /* 게시글 수정폼 */
    @GetMapping("notice/laboratory/posts/update/{id}")
    public String noticeLaboratoryPostUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto responseDto = this.postsService.findById(id);
        model.addAttribute("posts", responseDto);
        return "notice_laboratory_posts_update_form";
    }


    /* 게시글 작성요청 */
    // RestController 처리예정
    // JavaScript AJAX 통신 처리예정
    @PostMapping("notice/laboratory/posts/form")
    public String noticeLaboratoryPostSave(PostsSaveRequestDto requestDto) {
        Long id = this.postsService.save(requestDto);
        return "redirect:/notice/laboratory/posts/" + id;
    }


    /* 게시글 수정요청 */
    // PutMapping 처리예정
    // RestController 처리예정
    // JavaScript AJAX 통신 처리예정
    @PostMapping("notice/laboratory/posts/update/{id}")
    public String noticeLaboratoryPostUpdate(@PathVariable Long id, PostsUpdateRequestDto requestDto) {
        this.postsService.update(id, requestDto);
        return "redirect:/notice/laboratory/posts/" + id;
    }


    /* 게시글 삭제요청 */
    // DeleteMapping 처리예정
    // RestController 처리예정
    // JavaScript AJAX 통신 처리예정
    // 왜인지는 모르나 현재 GetMapping으로 처리되고 있음
    @GetMapping("notice/laboratory/posts/delete/{id}")
    public String noticeLaboratoryPostDelete(@PathVariable Long id) {
        this.postsService.delete(id);
        return "redirect:/notice/laboratory/board";
    }
}
