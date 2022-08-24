package com.skuniv.cgvr.controller.laboratory;

import com.skuniv.cgvr.dto.posts.*;
import com.skuniv.cgvr.service.posts.CommentsService;
import com.skuniv.cgvr.service.posts.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class LaboratoryAllController {
    private final PostsService postsService;
    private final CommentsService commentsService;

    /* 게시판 목록보기 */
    @GetMapping("laboratory/all/board")
    public String laboratoryAllBoard(Model model) {
        List<PostsListResponseDto> responseDtoList1 = this.postsService.findAllByCategoryNameDesc("공지");
        List<PostsListResponseDto> responseDtoList2 = this.postsService.findAllByCategoryNameDesc("논문");
        List<PostsListResponseDto> responseDtoList3 = this.postsService.findAllByCategoryNameDesc("자료");
        List<PostsListResponseDto> responseDtoList = new ArrayList<PostsListResponseDto>();
        responseDtoList.addAll(responseDtoList1);
        responseDtoList.addAll(responseDtoList2);
        responseDtoList.addAll(responseDtoList3);
        model.addAttribute("posts", responseDtoList);
        return "laboratory_all_board";
    }


    /* 게시글 상세보기 */
    @GetMapping("laboratory/all/posts/{id}")
    public String laboratoryAllPost(@PathVariable Long id, Model model) {
        PostsResponseDto responseDto = this.postsService.findById(id);
        List<CommentsListResponseDto> responseDtoList = this.commentsService.findAllByPostId(id);
        model.addAttribute("posts", responseDto);
        model.addAttribute("comments", responseDtoList);
        model.addAttribute("commentsSize", responseDtoList.size());
        return "laboratory_all_posts";
    }


    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////

    /* 게시글 작성폼 */
    @GetMapping("laboratory/all/posts/form")
    public String laboratoryAllPostForm() {
        return "laboratory_all_posts_form";
    }


    /* 게시글 작성요청 */
    // RestController 처리예정
    // JavaScript AJAX 통신 처리예정
    @PostMapping("laboratory/all/posts/form")
    public String laboratoryAllPostSave(PostsSaveRequestDto requestDto) {
        Long id = this.postsService.save(requestDto);
        return "redirect:/laboratory/all/posts/" + id;
    }


    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////

    /* 게시글 수정폼 */
    @GetMapping("laboratory/all/posts/update/{id}")
    public String laboratoryAllPostUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto responseDto = this.postsService.findById(id);
        model.addAttribute("posts", responseDto);
        return "laboratory_all_posts_update_form";
    }


    /* 게시글 수정요청 */
    // PutMapping 처리예정
    // RestController 처리예정
    // JavaScript AJAX 통신 처리예정
    @PostMapping("laboratory/all/posts/update/{id}")
    public String laboratoryAllPostUpdate(@PathVariable Long id, PostsUpdateRequestDto requestDto) {
        this.postsService.update(id, requestDto);
        return "redirect:/laboratory/all/posts/" + id;
    }


    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////

    /* 게시글 삭제요청 */
    // DeleteMapping 처리예정
    // RestController 처리예정
    // JavaScript AJAX 통신 처리예정
    // 현재 Front에서 method=GET에 의해 GetMapping처리
    @GetMapping("laboratory/all/posts/delete/{id}")
    public String laboraotryAllPostDelete(@PathVariable Long id) {
        this.postsService.delete(id);
        return "redirect:/laboratory/all/board";
    }

}
