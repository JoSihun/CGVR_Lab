package com.skuniv.cgvr.controller.notice;

import com.skuniv.cgvr.dto.AttachmentsListResponseDto;
import com.skuniv.cgvr.dto.AttachmentsResponseDto;
import com.skuniv.cgvr.dto.category.CategoryListResponseDto;
import com.skuniv.cgvr.dto.posts.CommentsListResponseDto;
import com.skuniv.cgvr.dto.posts.PostsListResponseDto;
import com.skuniv.cgvr.dto.posts.PostsResponseDto;
import com.skuniv.cgvr.dto.project.ProjectListResponseDto;
import com.skuniv.cgvr.service.AttachmentsService;
import com.skuniv.cgvr.service.CategoryService;
import com.skuniv.cgvr.service.posts.CommentsService;
import com.skuniv.cgvr.service.posts.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriUtils;

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
                                 @RequestParam(name="searchFilter", required=false) String searchFilter,
                                 @RequestParam(name="searchValue", required=false) String searchValue) {

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


        model.addAttribute("posts", responseDtoList);
        return "notice_all_board";
    }

    /* 게시글 상세보기 */
    @GetMapping("notice/all/posts/{id}")
    public String noticeAllPost(@PathVariable Long id, Model model) {
        PostsResponseDto responseDto = this.postsService.findById(id);
        List<CommentsListResponseDto> responseDtoList1 = this.commentsService.findAllByPostId(id);
        List<AttachmentsListResponseDto> responseDtoList2 = this.attachmentsService.findAllByPostId(id);
        model.addAttribute("posts", responseDto);
        model.addAttribute("comments", responseDtoList1);
        model.addAttribute("commentsSize", responseDtoList1.size());
        model.addAttribute("attachments", responseDtoList2);
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
