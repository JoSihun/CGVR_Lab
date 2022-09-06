package com.skuniv.cgvr.controller.notice;

import com.skuniv.cgvr.dto.AttachmentsListResponseDto;
import com.skuniv.cgvr.dto.AttachmentsResponseDto;
import com.skuniv.cgvr.dto.category.CategoryListResponseDto;
import com.skuniv.cgvr.dto.posts.CommentsListResponseDto;
import com.skuniv.cgvr.dto.posts.PostsListResponseDto;
import com.skuniv.cgvr.dto.posts.PostsResponseDto;
import com.skuniv.cgvr.service.AttachmentsService;
import com.skuniv.cgvr.service.CategoryService;
import com.skuniv.cgvr.service.posts.CommentsService;
import com.skuniv.cgvr.service.posts.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
import org.springframework.web.util.UriUtils;

import javax.servlet.http.HttpSession;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Controller
public class NoticeAllController {
    private final PostsService postsService;
    private final CommentsService commentsService;
    private final CategoryService categoryService;
    private final AttachmentsService attachmentsService;


    /* 게시판 목록보기 */
    @GetMapping("notice/all/board")
    public String noticeAllBoard(Model model,
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
                    responseDtoList1 = this.postsService.findAllByTitle("일반 공지사항", searchValue);
                    responseDtoList2 = this.postsService.findAllByTitle("수업 공지사항", searchValue);
                    responseDtoList3 = this.postsService.findAllByTitle("연구 공지사항", searchValue);
                    responseDtoList = new ArrayList<PostsListResponseDto>();
                    responseDtoList.addAll(responseDtoList1);
                    responseDtoList.addAll(responseDtoList2);
                    responseDtoList.addAll(responseDtoList3);
                    responseDtoList = responseDtoList.stream().sorted(
                            Comparator.comparing(PostsListResponseDto::getId).reversed()).collect(Collectors.toList());
                    break;
                case "content":
                    responseDtoList1 = this.postsService.findAllByContent("일반 공지사항", searchValue);
                    responseDtoList2 = this.postsService.findAllByContent("수업 공지사항", searchValue);
                    responseDtoList3 = this.postsService.findAllByContent("연구 공지사항", searchValue);
                    responseDtoList = new ArrayList<PostsListResponseDto>();
                    responseDtoList.addAll(responseDtoList1);
                    responseDtoList.addAll(responseDtoList2);
                    responseDtoList.addAll(responseDtoList3);
                    responseDtoList = responseDtoList.stream().sorted(
                            Comparator.comparing(PostsListResponseDto::getId).reversed()).collect(Collectors.toList());
                    break;
                case "author":
                    responseDtoList1 = this.postsService.findAllByAuthor("일반 공지사항", searchValue);
                    responseDtoList2 = this.postsService.findAllByAuthor("수업 공지사항", searchValue);
                    responseDtoList3 = this.postsService.findAllByAuthor("연구 공지사항", searchValue);
                    responseDtoList = new ArrayList<PostsListResponseDto>();
                    responseDtoList.addAll(responseDtoList1);
                    responseDtoList.addAll(responseDtoList2);
                    responseDtoList.addAll(responseDtoList3);
                    responseDtoList = responseDtoList.stream().sorted(
                            Comparator.comparing(PostsListResponseDto::getId).reversed()).collect(Collectors.toList());
                    break;
                default:
                    responseDtoList1 = this.postsService.findAllByTitleOrContent("일반 공지사항", searchValue);
                    responseDtoList2 = this.postsService.findAllByTitleOrContent("수업 공지사항", searchValue);
                    responseDtoList3 = this.postsService.findAllByTitleOrContent("연구 공지사항", searchValue);
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
            responseDtoList1 = this.postsService.findAllByCategoryNameDesc("일반 공지사항");
            responseDtoList2 = this.postsService.findAllByCategoryNameDesc("수업 공지사항");
            responseDtoList3 = this.postsService.findAllByCategoryNameDesc("연구 공지사항");
            responseDtoList = new ArrayList<PostsListResponseDto>();
            responseDtoList.addAll(responseDtoList1);
            responseDtoList.addAll(responseDtoList2);
            responseDtoList.addAll(responseDtoList3);

            responseDtoList = responseDtoList.stream().sorted(
                    Comparator.comparing(PostsListResponseDto::getId).reversed()).collect(Collectors.toList());
        }

        // 날짜 포매팅
        for(PostsListResponseDto responseDto : responseDtoList) {
            responseDto.boardFormat();
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

        return "notice_all_board";
    }

    /* 게시글 상세보기 */
    @GetMapping("notice/all/posts/{id}")
    public String noticeAllPost(@PathVariable Long id, Model model, HttpSession session) {
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
        return "notice_all_posts";
    }


    /** 파일 다운로드 */
    @GetMapping(value = "/notice/all/posts/{postsId}/download/{attachId}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
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
    @GetMapping("notice/all/posts/form")
    public String noticeAllPostForm(Model model) {
        List<CategoryListResponseDto> responseDtoList = this.categoryService.findAllAsc();
        model.addAttribute("category", responseDtoList);
        return "notice_all_posts_form";
    }


    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////

    /* 게시글 수정폼 */
    @GetMapping("notice/all/posts/update/{id}")
    public String noticeAllPostUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto responseDto = this.postsService.findById(id);
        List<CategoryListResponseDto> responseDtoList1 = this.categoryService.findAllAsc();
        List<AttachmentsListResponseDto> responseDtoList2 = this.attachmentsService.findAllByPostId(id);
        model.addAttribute("posts", responseDto);
        model.addAttribute("category", responseDtoList1);
        model.addAttribute("attachments", responseDtoList2);
        return "notice_all_posts_update_form";
    }
}
