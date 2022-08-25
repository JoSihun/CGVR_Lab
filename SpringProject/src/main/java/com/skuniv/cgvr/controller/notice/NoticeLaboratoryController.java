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
public class NoticeLaboratoryController {
    private final PostsService postsService;
    private final CommentsService commentsService;


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
        List<CommentsListResponseDto> responseDtoList = this.commentsService.findAllByPostId(id);
        model.addAttribute("posts", responseDto);
        model.addAttribute("comments", responseDtoList);
        model.addAttribute("commentsSize", responseDtoList.size());
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


    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////

    /* 게시글 수정폼 */
    @GetMapping("notice/laboratory/posts/update/{id}")
    public String noticeLaboratoryPostUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto responseDto = this.postsService.findById(id);
        model.addAttribute("posts", responseDto);
        return "notice_laboratory_posts_update_form";
    }


}
