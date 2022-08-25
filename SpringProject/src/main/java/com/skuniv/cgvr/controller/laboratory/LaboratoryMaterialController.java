package com.skuniv.cgvr.controller.laboratory;

import com.skuniv.cgvr.dto.posts.CommentsListResponseDto;
import com.skuniv.cgvr.dto.posts.PostsListResponseDto;
import com.skuniv.cgvr.dto.posts.PostsResponseDto;
import com.skuniv.cgvr.dto.posts.PostsSaveRequestDto;
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
public class LaboratoryMaterialController {
    private final PostsService postsService;
    private final CommentsService commentsService;

    /* 게시판 목록보기 */
    @GetMapping("laboratory/material/board")
    public String laboratoryMaterialBoard(Model model) {
        List<PostsListResponseDto> responseDtoList = this.postsService.findAllByCategoryNameDesc("자료");
        model.addAttribute("posts", responseDtoList);
        return "laboratory_material_board";
    }


    /* 게시글 상세보기 */
    @GetMapping("laboratory/material/posts/{id}")
    public String laboratoryMaterialPost(@PathVariable Long id, Model model) {
        PostsResponseDto responseDto = this.postsService.findById(id);
        List<CommentsListResponseDto> responseDtoList = this.commentsService.findAllByPostId(id);
        model.addAttribute("posts", responseDto);
        model.addAttribute("comments", responseDtoList);
        model.addAttribute("commentsSize", responseDtoList.size());
        return "laboratory_material_posts";
    }


    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////

    /* 게시글 작성폼 */
    @GetMapping("laboratory/material/posts/form")
    public String laboratoryMaterialPostForm() {
        return "laboratory_material_posts_form";
    }


    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////

    /* 게시글 수정폼 */
    @GetMapping("laboratory/material/posts/update/{id}")
    public String laboratoryMaterialPostUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto responseDto = this.postsService.findById(id);
        model.addAttribute("posts", responseDto);
        return "laboratory_material_posts_update_form";
    }


}
