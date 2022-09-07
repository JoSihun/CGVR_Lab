package com.skuniv.cgvr.controller.laboratory;

import com.skuniv.cgvr.dto.AttachmentsListResponseDto;
import com.skuniv.cgvr.dto.AttachmentsResponseDto;
import com.skuniv.cgvr.dto.posts.CommentsListResponseDto;
import com.skuniv.cgvr.dto.posts.PostsListResponseDto;
import com.skuniv.cgvr.dto.posts.PostsResponseDto;
import com.skuniv.cgvr.dto.project.ProjectListResponseDto;
import com.skuniv.cgvr.service.AttachmentsService;
import com.skuniv.cgvr.service.ProjectService;
import com.skuniv.cgvr.service.posts.CommentsService;
import com.skuniv.cgvr.service.posts.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.util.UriUtils;

import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class LaboratoryPaperController {
    private final PostsService postsService;
    private final CommentsService commentsService;
    private final ProjectService projectService;
    private final AttachmentsService attachmentsService;


//    /* 게시판 목록보기 */
//    @GetMapping("laboratory/paper/board")
//    public String laboratoryPaperBoard(Model model) {
//        List<PostsListResponseDto> responseDtoList = this.postsService.findAllByCategoryNameDesc("논문 게시판");
//        model.addAttribute("posts", responseDtoList);
//        return "laboratory_paper_board";
//    }

    /* 게시판 목록보기 */
    @GetMapping("laboratory/paper/board")
    public String laboratoryPaperBoard(Model model,
                                       @PageableDefault(sort = "id", size=20, direction = Sort.Direction.DESC) Pageable pageable,
                                       @RequestParam(name="searchFilter", defaultValue = "") String searchFilter,
                                       @RequestParam(name="searchValue", defaultValue = "") String searchValue
    ) {
        Page<PostsListResponseDto> responseDtoList;
        if(searchValue != null) {
            switch (searchFilter) {
                case "title":
                    responseDtoList = this.postsService.findAllByTitle("논문 게시판", searchValue, pageable);
                    break;
                case "content":
                    responseDtoList = this.postsService.findAllByContent("논문 게시판", searchValue, pageable);
                    break;
                case "author":
                    responseDtoList = this.postsService.findAllByAuthor("논문 게시판", searchValue, pageable);
                    break;
                default:
                    responseDtoList = this.postsService.findAllByTitleOrContent("논문 게시판", searchValue, pageable);
                    break;
            }
        }
        else {
            responseDtoList = this.postsService.findAllByCategoryName("논문 게시판", pageable);
        }

        // 날짜 포매팅
        for(PostsListResponseDto responseDto : responseDtoList) {
            responseDto.boardFormat();
        }

        // 페이지 인덱스
        int currentPage = responseDtoList.getNumber();
        int startPage = Math.max(currentPage / 5 * 5 + 1, 1);
        if ((currentPage % 5)+1 == 0) startPage -= 5;
        int endPage = Math.min(currentPage+5, responseDtoList.getTotalPages());

        // 프론트에서 처리할 페이지 인덱스 생성
        ArrayList<Integer> pageIndex = new ArrayList<>();
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

        return "laboratory_paper_board";
    }


    /* 게시글 상세보기 */
    @GetMapping("laboratory/paper/posts/{id}")
    public String laboratoryPaperPost(@PathVariable Long id, Model model, HttpSession session) {
        PostsResponseDto responseDto = this.postsService.findById(id);
        List<CommentsListResponseDto> responseDtoList1 = this.commentsService.findAllByPostId(id);
        List<AttachmentsListResponseDto> responseDtoList2 = this.attachmentsService.findAllByPostId(id);
        model.addAttribute("posts", responseDto);
        model.addAttribute("comments", responseDtoList1);
        model.addAttribute("commentsSize", responseDtoList1.size());
        model.addAttribute("attachments", responseDtoList2);
        // 본인 글 확인 (이부분은 단순 프론트단에서 버튼 유무만 결정하도록)
        String cmpUser = session.getAttribute("sessionUserId") + " " + session.getAttribute("sessionKorName");
        if(responseDto.getAuthor().equals(cmpUser)) {
            model.addAttribute("myPost", true);
        }
        // 본인 댓글 확인 (js를 통해 막음 → 댓글은 리스트라서 타임리프가 아닌 이상 불가능해보임)
        return "laboratory_paper_posts";
    }


    /** 파일 다운로드 */
    @GetMapping(value = "/laboratory/paper/posts/{postsId}/download/{attachId}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<UrlResource> downloadAttachment(@PathVariable Long postsId, @PathVariable Long attachId) throws MalformedURLException {
        AttachmentsResponseDto responseDto = this.attachmentsService.findById(attachId);
        UrlResource resource = new UrlResource("file:" + responseDto.getFilePath());
        String encodedFileName = UriUtils.encode(responseDto.getFileName(), StandardCharsets.UTF_8);
        String contentDisposition = "attachment; filename=" + encodedFileName;
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition).body(resource);
    }

    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////

    /* 게시글 작성폼 */
    @GetMapping("laboratory/paper/posts/form")
    public String laboratoryPaperPostForm(Model model) {
        List<ProjectListResponseDto> responseDtoList = this.projectService.findAllAsc();
        model.addAttribute("project", responseDtoList);
        return "laboratory_paper_posts_form";
    }


    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////

    /* 게시글 수정폼 */
    @GetMapping("laboratory/paper/posts/update/{id}")
    public String laboratoryPaperPostUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto responseDto = this.postsService.findById(id);
        List<ProjectListResponseDto> responseDtoList1 = this.projectService.findAllAsc();
        List<AttachmentsListResponseDto> responseDtoList2 = this.attachmentsService.findAllByPostId(id);
        model.addAttribute("posts", responseDto);
        model.addAttribute("project", responseDtoList1);
        model.addAttribute("attachments", responseDtoList2);
        return "laboratory_paper_posts_update_form";
    }


}
