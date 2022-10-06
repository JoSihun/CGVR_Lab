package com.skuniv.cgvr.controller;

import com.skuniv.cgvr.dto.category.CategoryResponseDto;
import com.skuniv.cgvr.dto.category.CategorySaveRequestDto;
import com.skuniv.cgvr.dto.project.ProjectResponseDto;
import com.skuniv.cgvr.dto.project.ProjectSaveRequestDto;
import com.skuniv.cgvr.dto.posts.PostsSaveRequestDto;
import com.skuniv.cgvr.dto.posts.PostsUpdateRequestDto;
import com.skuniv.cgvr.service.CategoryService;
import com.skuniv.cgvr.service.ProjectService;
import com.skuniv.cgvr.service.posts.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class PostsRestController {
    private final PostsService postsService;
    private final ProjectService projectService;
    private final CategoryService categoryService;


    /* 게시글 작성요청 */
    @PostMapping("posts/api")
    public Long postsSave(PostsSaveRequestDto requestDto, List<MultipartFile> files) throws Exception {
        /* 카테고리명 존재유무 확인 및 저장 */
        if (requestDto.getCategoryName() != null) {
            CategoryResponseDto categoryResponseDto = this.categoryService.findByCategoryName(requestDto.getCategoryName());
            if (categoryResponseDto == null) {
                CategorySaveRequestDto categorySaveRequestDto = new CategorySaveRequestDto();
                categorySaveRequestDto.setCategoryName(requestDto.getCategoryName());
                this.categoryService.save(categorySaveRequestDto);
            }
        }

        /* 프로젝트명 존재유무 확인 및 저장 */
        if (requestDto.getProjectName() != null){
            ProjectResponseDto projectResponseDto = this.projectService.findByProjectName(requestDto.getProjectName());
            if (projectResponseDto == null) {
                ProjectSaveRequestDto projectSaveRequestDto = new ProjectSaveRequestDto();
                projectSaveRequestDto.setProjectName(requestDto.getProjectName());
                this.projectService.save(projectSaveRequestDto);
            }
        }

        return this.postsService.save(requestDto, files);
    }

    /* 게시글 수정요청 */
    @PutMapping("/posts/api/{id}")
    public Long postsUpdate(@PathVariable Long id, PostsUpdateRequestDto requestDto, List<MultipartFile> files,
                            @RequestParam Map<String, Object> map) throws Exception {
        /* 카테고리명 존재유무 확인 및 저장 */
        if (requestDto.getCategoryName() != null) {
            CategoryResponseDto categoryResponseDto = this.categoryService.findByCategoryName(requestDto.getCategoryName());
            if (categoryResponseDto == null) {
                CategorySaveRequestDto categorySaveRequestDto = new CategorySaveRequestDto();
                categorySaveRequestDto.setCategoryName(requestDto.getCategoryName());
                this.categoryService.save(categorySaveRequestDto);
            }
        }

        /* 프로젝트명 존재유무 확인 및 저장 */
        if (requestDto.getProjectName() != null){
            ProjectResponseDto projectResponseDto = this.projectService.findByProjectName(requestDto.getProjectName());
            if (projectResponseDto == null) {
                ProjectSaveRequestDto projectSaveRequestDto = new ProjectSaveRequestDto();
                projectSaveRequestDto.setProjectName(requestDto.getProjectName());
                this.projectService.save(projectSaveRequestDto);
            }
        }

        /* 기존 첨부파일 저장경로 ArrayList 생성 */
        ArrayList<String> existFileList = new ArrayList<String>();
        String existFileListLength = (String) map.get("existFileListLength");
        for (int i = 0; i < Integer.parseInt(existFileListLength); i++) {
            existFileList.add((String) map.get("existFileList[" + i + "]"));
        }

        return this.postsService.update(id, requestDto, files, existFileList);
    }

    /* 게시글 삭제요청 */
    @DeleteMapping("/posts/api/{id}")
    public Long postsDelete(@PathVariable Long id) {
        postsService.delete(id);
        return id;
    }
}
