package com.skuniv.cgvr.controller;

import com.skuniv.cgvr.domain.Category;
import com.skuniv.cgvr.domain.Project;
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
import org.hibernate.procedure.spi.ParameterRegistrationImplementor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PostsRestController {
    private final PostsService postsService;
    private final ProjectService projectService;
    private final CategoryService categoryService;


    /* 게시글 작성요청 */
    @PostMapping("/posts/api")
    public Long postsSave(@RequestBody PostsSaveRequestDto requestDto) {
        CategoryResponseDto categoryResponseDto = this.categoryService.findByCategoryName(requestDto.getCategoryName());
        if (categoryResponseDto == null) {
            CategorySaveRequestDto categorySaveRequestDto = new CategorySaveRequestDto();
            categorySaveRequestDto.setCategoryName(requestDto.getCategoryName());
            this.categoryService.save(categorySaveRequestDto);
        }

        ProjectResponseDto projectResponseDto = this.projectService.findByProjectName(requestDto.getProjectName());
        if (projectResponseDto == null) {
            ProjectSaveRequestDto projectSaveRequestDto = new ProjectSaveRequestDto();
            projectSaveRequestDto.setProjectName(requestDto.getProjectName());
            this.projectService.save(projectSaveRequestDto);
        }

        return this.postsService.save(requestDto);
    }

    /* 게시글 수정요청 */
    @PutMapping("/posts/api/{id}")
    public Long postsUpdate(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto) {
        CategoryResponseDto categoryResponseDto = this.categoryService.findByCategoryName(requestDto.getCategoryName());
        if (categoryResponseDto == null) {
            CategorySaveRequestDto categorySaveRequestDto = new CategorySaveRequestDto();
            categorySaveRequestDto.setCategoryName(requestDto.getCategoryName());
            this.categoryService.save(categorySaveRequestDto);
        }

        ProjectResponseDto projectResponseDto = this.projectService.findByProjectName(requestDto.getProjectName());
        if (projectResponseDto == null) {
            ProjectSaveRequestDto projectSaveRequestDto = new ProjectSaveRequestDto();
            projectSaveRequestDto.setProjectName(requestDto.getProjectName());
            this.projectService.save(projectSaveRequestDto);
        }

        return this.postsService.update(id, requestDto);
    }

    /* 게시글 삭제요청 */
    @DeleteMapping("/posts/api/{id}")
    public Long postsDelete(@PathVariable Long id) {
        postsService.delete(id);
        return id;
    }
}
