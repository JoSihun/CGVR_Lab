package com.skuniv.cgvr.controller.notice;

import com.skuniv.cgvr.dto.category.CategoryListResponseDto;
import com.skuniv.cgvr.dto.posts.*;
import com.skuniv.cgvr.service.CategoryService;
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

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class NoticeNormalController {
    private final PostsService postsService;
    private final CommentsService commentsService;
    private final CategoryService categoryService;


    /* 게시판 목록보기 */
//    @GetMapping("notice/normal/board")
//    public String noticeNormalBoard(Model model) {
//        List<PostsListResponseDto> responseDtoList = this.postsService.findAllByCategoryNameDesc("일반");
//        model.addAttribute("posts", responseDtoList);
//        return "notice_normal_board";
//    }

    /* 페이징 테스트 */
    @GetMapping("notice/normal/board")
    public String noticeNormalBoard(Model model, @PageableDefault(sort = "id", size=1, direction = Sort.Direction.DESC) Pageable pageable) {
        Page<PostsListResponseDto> responseDtoList = this.postsService.findAllByCategoryName("일반 공지사항", pageable);

        int currentPage = responseDtoList.getNumber();
        int startPage = Math.max(currentPage / 5 * 5 + 1, 1);
        if ((currentPage % 5)+1 == 0) startPage -= 5;
        int endPage = Math.min(currentPage+5, responseDtoList.getTotalPages());

        // 프론트에서 처리할 페이지 인덱스 생성
        ArrayList pageIndex = new ArrayList();
        for (int i = startPage; i <= startPage + 4; i++) {
            pageIndex.add(i);
            if (i >= endPage) break;
        }

        model.addAttribute("posts", responseDtoList);
        model.addAttribute("hasPrev", responseDtoList.hasPrevious());                       // 이전 페이지 존재 여부
        model.addAttribute("hasNext", responseDtoList.hasNext());                           // 다음 페이지 존재 여부
        // 이전, 다음 페이지 번호
        if (responseDtoList.hasPrevious())
            model.addAttribute("prev", responseDtoList.previousOrFirstPageable().getPageNumber()+1);
        if (responseDtoList.hasNext())
            model.addAttribute("next", responseDtoList.nextOrLastPageable().getPageNumber()+1);
        model.addAttribute("pageIndex", pageIndex);     // 프론트에서 처리할 페이지 인덱스
        model.addAttribute("startPage", startPage);     // 페이지 인덱스 시작 번호
        model.addAttribute("endPage", endPage);         // 페이지 인덱스 마지막 번호
        model.addAttribute("lastPage", responseDtoList.getTotalPages());  // 마지막 페이지
        model.addAttribute("currentPage", currentPage); // 현재 페이지 번호
        return "notice_normal_board";
    }
    
    /* 동빈 검색 테스트
    @GetMapping("notice/normal/board")
    public String noticeNormalBoard(Model model,
                                    @RequestParam(name="searchFilter", required=false) String searchFilter,
                                    @RequestParam(name="searchValue", required=false) String searchValue) {
                                    List<PostsListResponseDto> responseDtoList;
                                 if(searchValue != null) {
            switch (searchFilter) {
                case "title":
                    responseDtoList = this.postsService.findAllByTitle("일반 공지사항", searchValue);
                    break;
                case "content":
                    responseDtoList = this.postsService.findAllByContent("일반 공지사항", searchValue);
                    break;
                case "author":
                    responseDtoList = this.postsService.findAllByAuthor("일반 공지사항", searchValue);
                    break;
                default:
                    responseDtoList = this.postsService.findAllByTitleOrContent("일반 공지사항", searchValue);
                    break;
            }
        }
        else {
            responseDtoList = this.postsService.findAllByCategoryNameDesc("일반 공지사항");
        }

        model.addAttribute("posts", responseDtoList);
        return "notice_normal_board";
     }
     */


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
    public String noticeNormalPostForm(Model model) {
        List<CategoryListResponseDto> responseDtoList = this.categoryService.findAllAsc();
        model.addAttribute("category", responseDtoList);
        return "notice_normal_posts_form";
    }


    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////

    /* 게시글 수정폼 */
    @GetMapping("notice/normal/posts/update/{id}")
    public String noticeNormalPostUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto responseDto = this.postsService.findById(id);
        List<CategoryListResponseDto> responseDtoList = this.categoryService.findAllAsc();
        model.addAttribute("posts", responseDto);
        model.addAttribute("category", responseDtoList);
        return "notice_normal_posts_update_form";
    }


}
