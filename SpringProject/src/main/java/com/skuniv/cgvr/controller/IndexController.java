package com.skuniv.cgvr.controller;

import com.skuniv.cgvr.dto.posts.PostsListResponseDto;
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

    @GetMapping("/")
    public String index(Model model) {
        // 최대 5개만 가져오는 기능필요
        // 임시로 변수명 숫자로 일원화 처리, 추후수정필요
        List<PostsListResponseDto> responseDtoList1 = this.postsService.findAllByCategoryNameDesc("일반");
        List<PostsListResponseDto> responseDtoList2 = this.postsService.findAllByCategoryNameDesc("수업");
        List<PostsListResponseDto> responseDtoList3 = this.postsService.findAllByCategoryNameDesc("연구");
        List<PostsListResponseDto> responseDtoList4 = this.postsService.findAllByCategoryNameDesc("공지");
        List<PostsListResponseDto> responseDtoList5 = this.postsService.findAllByCategoryNameDesc("논문");
        List<PostsListResponseDto> responseDtoList6 = this.postsService.findAllByCategoryNameDesc("자료");

        model.addAttribute("noticeNormalPosts", responseDtoList1);
        model.addAttribute("noticeLecturePosts", responseDtoList2);
        model.addAttribute("noticeLaboratoryPosts", responseDtoList3);
        model.addAttribute("LaboratoryNoticePosts", responseDtoList4);
        model.addAttribute("LaboratoryPaperPosts", responseDtoList5);
        model.addAttribute("LaboratoryMaterialPosts", responseDtoList6);

        return "index";
    }

}
