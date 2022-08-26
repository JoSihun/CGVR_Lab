package com.skuniv.cgvr.controller.notice;

import com.skuniv.cgvr.dto.posts.*;
import com.skuniv.cgvr.service.posts.CommentsService;
import com.skuniv.cgvr.service.posts.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class NoticeNormalController {
    private final PostsService postsService;
    private final CommentsService commentsService;


    /* 게시판 목록보기 */
//    @GetMapping("notice/normal/board")
//    public String noticeNormalBoard(Model model) {
//        List<PostsListResponseDto> responseDtoList = this.postsService.findAllByCategoryNameDesc("일반");
//        model.addAttribute("posts", responseDtoList);
//        return "notice_normal_board";
//    }

    /* 페이징 테스트 */
    @GetMapping("notice/normal/board")
    public String noticeNormalBoard(Model model, @PageableDefault(sort = "id", size=20, direction = Sort.Direction.DESC) Pageable pageable) {
        Page<PostsListResponseDto> responseDtoList = this.postsService.findAllByCategoryName("일반", pageable);
        ArrayList pageIndex = new ArrayList();
        for (int i = 1; i <= responseDtoList.getTotalPages(); i++) pageIndex.add(i);

        model.addAttribute("posts", responseDtoList);
        model.addAttribute("prev", pageable.previousOrFirst().getPageNumber());         // 이전 페이지 이동
        model.addAttribute("next", pageable.next().getPageNumber());                    // 다음 페이지 이동
        model.addAttribute("hasPrev", responseDtoList.hasPrevious());                   // 이전 페이지 존재 여부
        model.addAttribute("hasNext", responseDtoList.hasNext());                       // 다음 페이지 존재 여부
        model.addAttribute("pageIndex", pageIndex);                                     // 페이지 인덱스
        model.addAttribute("startPage", pageable.first().getPageNumber());              // 시작 페이지 번호
        model.addAttribute("endPage", responseDtoList.getTotalPages()-1);   // 마지막 페이지 번호
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


    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////

    /* 게시글 수정폼 */
    @GetMapping("notice/normal/posts/update/{id}")
    public String noticeNormalPostUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto responseDto = this.postsService.findById(id);
        model.addAttribute("posts", responseDto);
        return "notice_normal_posts_update_form";
    }


}
