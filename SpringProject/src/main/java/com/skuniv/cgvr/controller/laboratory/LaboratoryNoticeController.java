package com.skuniv.cgvr.controller.laboratory;

import com.skuniv.cgvr.dto.posts.PostsListResponseDto;
import com.skuniv.cgvr.dto.posts.PostsResponseDto;
import com.skuniv.cgvr.dto.posts.PostsSaveRequestDto;
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
public class LaboratoryNoticeController {
    private final PostsService postsService;

    /* 게시판 목록보기 */
    @GetMapping("laboratory/notice/board")
    public String laboratoryNoticeBoard(Model model) {
        List<PostsListResponseDto> responseDtoList = this.postsService.findAllByCategoryNameDesc("공지");
        model.addAttribute("posts", responseDtoList);
        return "laboratory_notice_board";
    }


    /* 게시글 상세보기 */
    @GetMapping("laboratory/notice/posts/{id}")
    public String laboratoryNoticePost(@PathVariable Long id, Model model) {
        PostsResponseDto responseDto = this.postsService.findById(id);
        model.addAttribute("posts", responseDto);
        return "laboratory_notice_posts";
    }


    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////

    /* 게시글 작성폼 */
    @GetMapping("laboratory/notice/posts/form")
    public String laboratoryNoticePostForm() {
        return "laboratory_notice_posts_form";
    }


    /* 게시글 작성요청 */
    // RestController 처리예정
    // JavaScript AJAX 통신 처리예정
    @PostMapping("laboratory/notice/posts/form")
    public String laboratoryNoticePostSave(PostsSaveRequestDto requestDto) {
        Long id = this.postsService.save(requestDto);
        return "redirect:/laboratory/notice/posts/" + id;
    }
}
