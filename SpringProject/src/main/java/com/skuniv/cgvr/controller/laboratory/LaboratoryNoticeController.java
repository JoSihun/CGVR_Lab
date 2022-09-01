package com.skuniv.cgvr.controller.laboratory;

import com.skuniv.cgvr.dto.posts.CommentsListResponseDto;
import com.skuniv.cgvr.dto.posts.PostsListResponseDto;
import com.skuniv.cgvr.dto.posts.PostsResponseDto;
import com.skuniv.cgvr.dto.posts.PostsSaveRequestDto;
import com.skuniv.cgvr.dto.project.ProjectListResponseDto;
import com.skuniv.cgvr.service.ProjectService;
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
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class LaboratoryNoticeController {
    private final PostsService postsService;
    private final CommentsService commentsService;
    private final ProjectService projectService;


//    /* 게시판 목록보기 */
//    @GetMapping("laboratory/notice/board")
//    public String laboratoryNoticeBoard(Model model) {
//        List<PostsListResponseDto> responseDtoList = this.postsService.findAllByCategoryNameDesc("공지 게시판");
//        model.addAttribute("posts", responseDtoList);
//        return "laboratory_notice_board";
//    }

    /* 게시판 목록보기 */
    @GetMapping("laboratory/notice/board")
    public String laboratoryNoticeBoard(Model model,
                                    @PageableDefault(sort = "id", size=20, direction = Sort.Direction.DESC) Pageable pageable,
                                    @RequestParam(name="searchFilter", defaultValue = "") String searchFilter,
                                    @RequestParam(name="searchValue", defaultValue = "") String searchValue
    ) {
        Page<PostsListResponseDto> responseDtoList;
        if(searchValue != null) {
            switch (searchFilter) {
                case "title":
                    responseDtoList = this.postsService.findAllByTitle("공지 게시판", searchValue, pageable);
                    break;
                case "content":
                    responseDtoList = this.postsService.findAllByContent("공지 게시판", searchValue, pageable);
                    break;
                case "author":
                    responseDtoList = this.postsService.findAllByAuthor("공지 게시판", searchValue, pageable);
                    break;
                default:
                    responseDtoList = this.postsService.findAllByTitleOrContent("공지 게시판", searchValue, pageable);
                    break;
            }
        }
        else {
            responseDtoList = this.postsService.findAllByCategoryName("공지 게시판", pageable);
        }

        // 페이지 인덱스
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

        /* 페이징을 위한 파라미터 */
        model.addAttribute("hasPrev", responseDtoList.hasPrevious());                       // 이전 페이지 존재 여부
        model.addAttribute("hasNext", responseDtoList.hasNext());                           // 다음 페이지 존재 여부
        if (responseDtoList.hasPrevious())
            model.addAttribute("prev",
                    responseDtoList.previousOrFirstPageable().getPageNumber()+1);            // 이전 페이지 번호
        if (responseDtoList.hasNext())
            model.addAttribute("next",
                    responseDtoList.nextOrLastPageable().getPageNumber()+1);                 // 다음 페이지 번호
        model.addAttribute("pageIndex", pageIndex);                                          // 프론트에서 처리할 페이지 인덱스
        model.addAttribute("startPage", startPage);                                          // 페이지 인덱스 시작 번호
        model.addAttribute("endPage", endPage);                                              // 페이지 인덱스 마지막 번호
        model.addAttribute("lastPage", responseDtoList.getTotalPages());                     // 마지막 페이지
        model.addAttribute("currentPage", currentPage);                                      // 현재 페이지 번호

        /* 검색 기능용 파라미터 */
        model.addAttribute("searchValue", searchValue);     // 검색어
        model.addAttribute("searchFilter", searchFilter);   // 검색필터

        return "laboratory_notice_board";
    }

    /* 게시글 상세보기 */
    @GetMapping("laboratory/notice/posts/{id}")
    public String laboratoryNoticePost(@PathVariable Long id, Model model) {
        PostsResponseDto responseDto = this.postsService.findById(id);
        List<CommentsListResponseDto> responseDtoList = this.commentsService.findAllByPostId(id);
        model.addAttribute("posts", responseDto);
        model.addAttribute("comments", responseDtoList);
        model.addAttribute("commentsSize", responseDtoList.size());
        return "laboratory_notice_posts";
    }


    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////

    /* 게시글 작성폼 */
    @GetMapping("laboratory/notice/posts/form")
    public String laboratoryNoticePostForm(Model model) {
        List<ProjectListResponseDto> responseDtoList = this.projectService.findAllAsc();
        model.addAttribute("project", responseDtoList);
        return "laboratory_notice_posts_form";
    }


    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////

    /* 게시글 수정폼 */
    @GetMapping("laboratory/notice/posts/update/{id}")
    public String laboratoryNoticePostUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto responseDto = this.postsService.findById(id);
        List<ProjectListResponseDto> responseDtoList = this.projectService.findAllAsc();
        model.addAttribute("posts", responseDto);
        model.addAttribute("project", responseDtoList);
        return "laboratory_notice_posts_update_form";
    }


}
