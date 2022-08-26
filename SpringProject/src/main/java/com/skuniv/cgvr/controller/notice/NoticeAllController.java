package com.skuniv.cgvr.controller.notice;

import com.skuniv.cgvr.dto.posts.CommentsListResponseDto;
import com.skuniv.cgvr.dto.posts.PostsListResponseDto;
import com.skuniv.cgvr.dto.posts.PostsResponseDto;
import com.skuniv.cgvr.service.posts.CommentsService;
import com.skuniv.cgvr.service.posts.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Controller
public class NoticeAllController {
    private final PostsService postsService;
    private final CommentsService commentsService;


    /* 게시판 목록보기 */
    @GetMapping("notice/all/board")
    public String noticeAllBoard(Model model, @RequestParam(name="searchFilter", required=false) String searchFilter,
                                 @RequestParam(name="searchValue", required=false) String searchValue) {
//        List<PostsListResponseDto> responseDtoList1 = this.postsService.findAllByCategoryNameDesc("일반");
//        List<PostsListResponseDto> responseDtoList2 = this.postsService.findAllByCategoryNameDesc("수업");
//        List<PostsListResponseDto> responseDtoList3 = this.postsService.findAllByCategoryNameDesc("연구");
//        List<PostsListResponseDto> responseDtoList = new ArrayList<PostsListResponseDto>();
//        responseDtoList.addAll(responseDtoList1);
//        responseDtoList.addAll(responseDtoList2);
//        responseDtoList.addAll(responseDtoList3);
//        responseDtoList = responseDtoList.stream().sorted(
//                Comparator.comparing(PostsListResponseDto::getId).reversed()).collect(Collectors.toList());
        List<PostsListResponseDto> responseDtoList;
        if(searchValue != null) {
            switch (searchFilter) {
                case "title":
                    responseDtoList = this.postsService.findAllByTitle(searchValue);
                    break;
                case "content":
                    responseDtoList = this.postsService.findAllByContent(searchValue);
                    break;
                case "author":
                    responseDtoList = this.postsService.findAllByAuthor(searchValue);
                    break;
                default:
                    responseDtoList = this.postsService.findAllByTitleOrContent(searchValue);
                    break;
            }
        }
        else {
            responseDtoList = this.postsService.findAllDesc();
        }
        model.addAttribute("posts", responseDtoList);
        return "notice_all_board";
    }

    /* 게시글 상세보기 */
    @GetMapping("notice/all/posts/{id}")
    public String noticeAllPost(@PathVariable Long id, Model model) {
        PostsResponseDto responseDto = this.postsService.findById(id);
        List<CommentsListResponseDto> responseDtoList = this.commentsService.findAllByPostId(id);
        model.addAttribute("posts", responseDto);
        model.addAttribute("comments", responseDtoList);
        model.addAttribute("commentsSize", responseDtoList.size());
        return "notice_all_posts";
    }


    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////

    /* 게시글 작성폼 */
    @GetMapping("notice/all/posts/form")
    public String noticeAllPostForm() {
        return "notice_all_posts_form";
    }


    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////

    /* 게시글 수정폼 */
    @GetMapping("notice/all/posts/update/{id}")
    public String noticeAllPostUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto responseDto = this.postsService.findById(id);
        model.addAttribute("posts", responseDto);
        return "notice_all_posts_update_form";
    }
}
