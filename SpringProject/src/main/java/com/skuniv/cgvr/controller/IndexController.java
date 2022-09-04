package com.skuniv.cgvr.controller;

import com.skuniv.cgvr.domain.Project;
import com.skuniv.cgvr.dto.category.CategoryListResponseDto;
import com.skuniv.cgvr.dto.category.CategoryResponseDto;
import com.skuniv.cgvr.dto.posts.PostsListResponseDto;
import com.skuniv.cgvr.dto.project.ProjectListResponseDto;
import com.skuniv.cgvr.service.CategoryService;
import com.skuniv.cgvr.service.ProjectService;
import com.skuniv.cgvr.service.posts.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class IndexController {
    private final PostsService postsService;
    private final ProjectService projectService;
    private final CategoryService categoryService;

    @GetMapping("/")
    public String index(Model model) {
        // 최대 5개만 가져오는 기능필요
        // 임시로 변수명 숫자로 일원화 처리, 추후수정필요
        List<PostsListResponseDto> responseDtoList1 = this.postsService.findAllByCategoryNameDesc("일반 공지사항");
        List<PostsListResponseDto> responseDtoList2 = this.postsService.findAllByCategoryNameDesc("수업 공지사항");
        List<PostsListResponseDto> responseDtoList3 = this.postsService.findAllByCategoryNameDesc("연구 공지사항");
        List<PostsListResponseDto> responseDtoList4 = this.postsService.findAllByCategoryNameDesc("공지 게시판");
        List<PostsListResponseDto> responseDtoList5 = this.postsService.findAllByCategoryNameDesc("논문 게시판");
        List<PostsListResponseDto> responseDtoList6 = this.postsService.findAllByCategoryNameDesc("자료 게시판");

        if (responseDtoList1.size() > 5) { responseDtoList1 = responseDtoList1.subList(0, 5); }
        if (responseDtoList2.size() > 5) { responseDtoList2 = responseDtoList2.subList(0, 5); }
        if (responseDtoList3.size() > 5) { responseDtoList3 = responseDtoList3.subList(0, 5); }
        if (responseDtoList4.size() > 5) { responseDtoList4 = responseDtoList4.subList(0, 5); }
        if (responseDtoList5.size() > 5) { responseDtoList5 = responseDtoList5.subList(0, 5); }
        if (responseDtoList6.size() > 5) { responseDtoList6 = responseDtoList6.subList(0, 5); }

        for(PostsListResponseDto responseDto : responseDtoList1) {
            responseDto.indexFormat();
        }
        for(PostsListResponseDto responseDto : responseDtoList2) {
            responseDto.indexFormat();
        }
        for(PostsListResponseDto responseDto : responseDtoList3) {
            responseDto.indexFormat();
        }
        for(PostsListResponseDto responseDto : responseDtoList4) {
            responseDto.indexFormat();
        }
        for(PostsListResponseDto responseDto : responseDtoList5) {
            responseDto.indexFormat();
        }
        for(PostsListResponseDto responseDto : responseDtoList6) {
            responseDto.indexFormat();
        }

        model.addAttribute("noticeNormalPosts", responseDtoList1);
        model.addAttribute("noticeLecturePosts", responseDtoList2);
        model.addAttribute("noticeLaboratoryPosts", responseDtoList3);
        model.addAttribute("LaboratoryNoticePosts", responseDtoList4);
        model.addAttribute("LaboratoryPaperPosts", responseDtoList5);
        model.addAttribute("LaboratoryMaterialPosts", responseDtoList6);

        return "index";
    }


    @GetMapping("/manage/posts/title")
    public String managePostsTitle(Model model) {
        List<ProjectListResponseDto> projectListResponseDtos = this.projectService.findAllAsc();
        List<CategoryListResponseDto> categoryListResponseDtos = this.categoryService.findAllAsc();
        model.addAttribute("project", projectListResponseDtos);
        model.addAttribute("category", categoryListResponseDtos);
        return "manage_posts_title";
    }
}
