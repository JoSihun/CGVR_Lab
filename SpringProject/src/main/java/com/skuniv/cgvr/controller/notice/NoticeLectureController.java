package com.skuniv.cgvr.controller.notice;

import com.skuniv.cgvr.dto.category.CategoryListResponseDto;
import com.skuniv.cgvr.dto.posts.*;
import com.skuniv.cgvr.service.CategoryService;
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
public class NoticeLectureController {
    private final PostsService postsService;
    private final CommentsService commentsService;
    private final CategoryService categoryService;


    /* 게시판 목록보기 */
    @GetMapping("notice/lecture/board")
    public String noticeLectureBoard(Model model) {
        List<PostsListResponseDto> responseDtoList = this.postsService.findAllByCategoryNameDesc("수업 공지사항");
        model.addAttribute("posts", responseDtoList);
        return "notice_lecture_board";
    }


    /* 게시글 상세보기 */
    @GetMapping("notice/lecture/posts/{id}")
    public String noticeLecturePost(@PathVariable Long id, Model model) {
        PostsResponseDto responseDto = this.postsService.findById(id);
        List<CommentsListResponseDto> responseDtoList = this.commentsService.findAllByPostId(id);
        model.addAttribute("posts", responseDto);
        model.addAttribute("comments", responseDtoList);
        model.addAttribute("commentsSize", responseDtoList.size());
        return "notice_lecture_posts";
    }


    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////

    /* 게시글 작성폼 */
    @GetMapping("notice/lecture/posts/form")
    public String noticeLecturePostForm(Model model) {
        List<CategoryListResponseDto> responseDtoList = this.categoryService.findAllAsc();
        model.addAttribute("category", responseDtoList);
        return "notice_lecture_posts_form";
    }


    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////

    /* 게시글 수정폼 */
    @GetMapping("notice/lecture/posts/update/{id}")
    public String noticeLecturePostUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto responseDto = this.postsService.findById(id);
        List<CategoryListResponseDto> responseDtoList = this.categoryService.findAllAsc();
        model.addAttribute("posts", responseDto);
        model.addAttribute("category", responseDtoList);
        return "notice_lecture_posts_update_form";
    }


}
