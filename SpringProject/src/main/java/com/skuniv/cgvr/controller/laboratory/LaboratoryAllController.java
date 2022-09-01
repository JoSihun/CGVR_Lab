package com.skuniv.cgvr.controller.laboratory;

import com.skuniv.cgvr.dto.category.CategoryListResponseDto;
import com.skuniv.cgvr.dto.project.ProjectListResponseDto;
import com.skuniv.cgvr.dto.posts.*;
import com.skuniv.cgvr.service.CategoryService;
import com.skuniv.cgvr.service.ProjectService;
import com.skuniv.cgvr.service.posts.CommentsService;
import com.skuniv.cgvr.service.posts.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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
public class LaboratoryAllController {
    private final PostsService postsService;
    private final CommentsService commentsService;
    private final ProjectService projectService;


//    /* 게시판 목록보기 */
//    @GetMapping("laboratory/all/board")
//    public String laboratoryAllBoard(Model model) {
//        List<PostsListResponseDto> responseDtoList1 = this.postsService.findAllByCategoryNameDesc("공지 게시판");
//        List<PostsListResponseDto> responseDtoList2 = this.postsService.findAllByCategoryNameDesc("논문 게시판");
//        List<PostsListResponseDto> responseDtoList3 = this.postsService.findAllByCategoryNameDesc("자료 게시판");
//        List<PostsListResponseDto> responseDtoList = new ArrayList<PostsListResponseDto>();
//        responseDtoList.addAll(responseDtoList1);
//        responseDtoList.addAll(responseDtoList2);
//        responseDtoList.addAll(responseDtoList3);
//        responseDtoList = responseDtoList.stream().sorted(
//                Comparator.comparing(PostsListResponseDto::getId).reversed()).collect(Collectors.toList());
//        model.addAttribute("posts", responseDtoList);
//        return "laboratory_all_board";
//    }

    /* 게시판 목록보기 */
    @GetMapping("laboratory/all/board")
    public String laboratoryAllBoard(Model model,
                                     @PageableDefault(sort = "id", size=20, direction = Sort.Direction.DESC) Pageable pageable,
                                     @RequestParam(name="searchFilter", defaultValue = "") String searchFilter,
                                     @RequestParam(name="searchValue", defaultValue = "") String searchValue) {
        List<PostsListResponseDto> responseDtoList;
        List<PostsListResponseDto> responseDtoList1;
        List<PostsListResponseDto> responseDtoList2;
        List<PostsListResponseDto> responseDtoList3;
        if(searchValue != null) {
            switch (searchFilter) {
                case "title":
                    responseDtoList1 = this.postsService.findAllByTitle("공지 게시판", searchValue);
                    responseDtoList2 = this.postsService.findAllByTitle("논문 게시판", searchValue);
                    responseDtoList3 = this.postsService.findAllByTitle("자료 게시판", searchValue);
                    responseDtoList = new ArrayList<PostsListResponseDto>();
                    responseDtoList.addAll(responseDtoList1);
                    responseDtoList.addAll(responseDtoList2);
                    responseDtoList.addAll(responseDtoList3);
                    responseDtoList = responseDtoList.stream().sorted(
                            Comparator.comparing(PostsListResponseDto::getId).reversed()).collect(Collectors.toList());
                    break;
                case "content":
                    responseDtoList1 = this.postsService.findAllByContent("공지 게시판", searchValue);
                    responseDtoList2 = this.postsService.findAllByContent("논문 게시판", searchValue);
                    responseDtoList3 = this.postsService.findAllByContent("자료 게시판", searchValue);
                    responseDtoList = new ArrayList<PostsListResponseDto>();
                    responseDtoList.addAll(responseDtoList1);
                    responseDtoList.addAll(responseDtoList2);
                    responseDtoList.addAll(responseDtoList3);
                    responseDtoList = responseDtoList.stream().sorted(
                            Comparator.comparing(PostsListResponseDto::getId).reversed()).collect(Collectors.toList());
                    break;
                case "author":
                    responseDtoList1 = this.postsService.findAllByAuthor("공지 게시판", searchValue);
                    responseDtoList2 = this.postsService.findAllByAuthor("논문 게시판", searchValue);
                    responseDtoList3 = this.postsService.findAllByAuthor("자료 게시판", searchValue);
                    responseDtoList = new ArrayList<PostsListResponseDto>();
                    responseDtoList.addAll(responseDtoList1);
                    responseDtoList.addAll(responseDtoList2);
                    responseDtoList.addAll(responseDtoList3);
                    responseDtoList = responseDtoList.stream().sorted(
                            Comparator.comparing(PostsListResponseDto::getId).reversed()).collect(Collectors.toList());
                    break;
                default:
                    responseDtoList1 = this.postsService.findAllByTitleOrContent("공지 게시판", searchValue);
                    responseDtoList2 = this.postsService.findAllByTitleOrContent("논문 게시판", searchValue);
                    responseDtoList3 = this.postsService.findAllByTitleOrContent("자료 게시판", searchValue);
                    responseDtoList = new ArrayList<PostsListResponseDto>();
                    responseDtoList.addAll(responseDtoList1);
                    responseDtoList.addAll(responseDtoList2);
                    responseDtoList.addAll(responseDtoList3);
                    responseDtoList = responseDtoList.stream().sorted(
                            Comparator.comparing(PostsListResponseDto::getId).reversed()).collect(Collectors.toList());
                    break;
            }
        }
        else {
            responseDtoList1 = this.postsService.findAllByCategoryNameDesc("공지 게시판");
            responseDtoList2 = this.postsService.findAllByCategoryNameDesc("논문 게시판");
            responseDtoList3 = this.postsService.findAllByCategoryNameDesc("자료 게시판");
            responseDtoList = new ArrayList<PostsListResponseDto>();
            responseDtoList.addAll(responseDtoList1);
            responseDtoList.addAll(responseDtoList2);
            responseDtoList.addAll(responseDtoList3);
            responseDtoList = responseDtoList.stream().sorted(
                    Comparator.comparing(PostsListResponseDto::getId).reversed()).collect(Collectors.toList());
        }

        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), responseDtoList.size());

        // 페이지 객체 선언
        Page<PostsListResponseDto> responseDtoPage = new PageImpl<>(responseDtoList.subList(start, end), pageable, responseDtoList.size());

        // 페이지 인덱스
        int currentPage = responseDtoPage.getNumber();
        int startPage = Math.max(currentPage / 5 * 5 + 1, 1);
        if ((currentPage % 5)+1 == 0) startPage -= 5;
        int endPage = Math.min(currentPage+5, responseDtoPage.getTotalPages());

        // 프론트에서 처리할 페이지 인덱스 생성
        ArrayList pageIndex = new ArrayList();
        for (int i = startPage; i <= startPage + 4; i++) {
            pageIndex.add(i);
            if (i >= endPage) break;
        }

        model.addAttribute("posts", responseDtoPage);

        /* 페이징을 위한 파라미터 */
        model.addAttribute("hasPrev", responseDtoPage.hasPrevious());                       // 이전 페이지 존재 여부
        model.addAttribute("hasNext", responseDtoPage.hasNext());                           // 다음 페이지 존재 여부
        if (responseDtoPage.hasPrevious())
            model.addAttribute("prev",
                    responseDtoPage.previousOrFirstPageable().getPageNumber()+1);           // 이전 페이지 번호
        if (responseDtoPage.hasNext())
            model.addAttribute("next",
                    responseDtoPage.nextOrLastPageable().getPageNumber()+1);                // 이전 페이지 번호
        model.addAttribute("pageIndex", pageIndex);                                         // 프론트에서 처리할 페이지 인덱스
        model.addAttribute("startPage", startPage);                                         // 페이지 인덱스 시작 번호
        model.addAttribute("endPage", endPage);                                             // 페이지 인덱스 마지막 번호
        model.addAttribute("lastPage", responseDtoPage.getTotalPages());                    // 마지막 페이지
        model.addAttribute("currentPage", currentPage);                                     // 현재 페이지 번호

        /* 검색 기능용 파라미터 */
        model.addAttribute("searchValue", searchValue);     // 검색어
        model.addAttribute("searchFilter", searchFilter);   // 검색필터

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
