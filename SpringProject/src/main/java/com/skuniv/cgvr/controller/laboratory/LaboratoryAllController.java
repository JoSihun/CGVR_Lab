package com.skuniv.cgvr.controller.laboratory;

import com.skuniv.cgvr.domain.posts.Posts;
import com.skuniv.cgvr.dto.posts.*;
import com.skuniv.cgvr.service.posts.CommentsService;
import com.skuniv.cgvr.service.posts.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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
        responseDtoList = responseDtoList.stream().sorted(
                Comparator.comparing(PostsListResponseDto::getId).reversed()).collect(Collectors.toList());
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


}
