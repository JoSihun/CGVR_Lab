package com.skuniv.cgvr.controller;

import com.skuniv.cgvr.dto.ProjectsListResponseDto;
import com.skuniv.cgvr.dto.ProjectsResponseDto;
import com.skuniv.cgvr.dto.ProjectsUpdateRequestDto;
import com.skuniv.cgvr.dto.posts.PostsUpdateRequestDto;
import com.skuniv.cgvr.service.ProjectsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ProjectsRestController {
    private final ProjectsService projectsService;


    /* 프로젝트명 수정요청 */
    @PutMapping("/projects/api/{id}")
    public Long postsUpdate(@PathVariable Long id, @RequestBody ProjectsUpdateRequestDto requestDto) {
        return projectsService.update(id, requestDto);
    }


    /* 프로젝트명 삭제요청 */
    @DeleteMapping("/projects/api/{projectName}")
    public Long postsDelete(@PathVariable String projectName) {
        ProjectsResponseDto responseDto = projectsService.findByProjectName(projectName);
        projectsService.delete(responseDto.getId());
        return responseDto.getId();
    }
}
