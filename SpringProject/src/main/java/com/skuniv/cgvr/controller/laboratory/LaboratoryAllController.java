package com.skuniv.cgvr.controller.laboratory;

import com.skuniv.cgvr.dto.category.CategoryListResponseDto;
import com.skuniv.cgvr.dto.project.ProjectListResponseDto;
import com.skuniv.cgvr.dto.posts.*;
import com.skuniv.cgvr.service.CategoryService;
import com.skuniv.cgvr.service.ProjectService;
import com.skuniv.cgvr.service.posts.CommentsService;
import com.skuniv.cgvr.service.posts.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Controller
public class LaboratoryAllController {
    private final PostsService postsService;
    private final CommentsService commentsService;
    private final ProjectService projectService;


    /* 게시판 목록보기 */
    @GetMapping("laboratory/all/board")
    public String laboratoryAllBoard(Model model) {
        List<PostsListResponseDto> responseDtoList1 = this.postsService.findAllByCategoryNameDesc("공지 게시판");
        List<PostsListResponseDto> responseDtoList2 = this.postsService.findAllByCategoryNameDesc("논문 게시판");
        List<PostsListResponseDto> responseDtoList3 = this.postsService.findAllByCategoryNameDesc("자료 게시판");
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
    public String laboratoryAllPostForm(Model model) {
        List<ProjectListResponseDto> responseDtoList = this.projectService.findAllAsc();
        model.addAttribute("project", responseDtoList);
        return "laboratory_all_posts_form";
    }


    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////

    /* 게시글 수정폼 */
    @GetMapping("laboratory/all/posts/update/{id}")
    public String laboratoryAllPostUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto responseDto = this.postsService.findById(id);
        List<ProjectListResponseDto> responseDtoList = this.projectService.findAllAsc();
        model.addAttribute("posts", responseDto);
        model.addAttribute("project", responseDtoList);
        return "laboratory_all_posts_update_form";
    }


}
