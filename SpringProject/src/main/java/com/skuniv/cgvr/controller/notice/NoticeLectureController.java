package com.skuniv.cgvr.controller.notice;

import com.skuniv.cgvr.dto.AttachmentsListResponseDto;
import com.skuniv.cgvr.dto.AttachmentsResponseDto;
import com.skuniv.cgvr.dto.category.CategoryListResponseDto;
import com.skuniv.cgvr.dto.posts.*;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.util.UriUtils;

import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class NoticeLectureController {
    private final PostsService postsService;
    private final CommentsService commentsService;
    private final CategoryService categoryService;
    private final AttachmentsService attachmentsService;


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
        List<CommentsListResponseDto> responseDtoList1 = this.commentsService.findAllByPostId(id);
        List<AttachmentsListResponseDto> responseDtoList2 = this.attachmentsService.findAllByPostId(id);
        model.addAttribute("posts", responseDto);
        model.addAttribute("comments", responseDtoList1);
        model.addAttribute("commentsSize", responseDtoList1.size());
        model.addAttribute("attachments", responseDtoList2);
        return "notice_lecture_posts";
    }


    /** 파일 다운로드 */
    @GetMapping(value = "/notice/lecture/posts/{postsId}/download/{attachId}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
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
        List<CategoryListResponseDto> responseDtoList1 = this.categoryService.findAllAsc();
        List<AttachmentsListResponseDto> responseDtoList2 = this.attachmentsService.findAllByPostId(id);
        model.addAttribute("posts", responseDto);
        model.addAttribute("category", responseDtoList1);
        model.addAttribute("attachments", responseDtoList2);
        return "notice_lecture_posts_update_form";
    }


}
