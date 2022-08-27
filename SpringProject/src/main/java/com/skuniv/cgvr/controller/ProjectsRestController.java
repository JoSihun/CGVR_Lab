package com.skuniv.cgvr.controller;

import com.skuniv.cgvr.dto.project.ProjectResponseDto;
import com.skuniv.cgvr.dto.project.ProjectUpdateRequestDto;
import com.skuniv.cgvr.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class ProjectsRestController {
    private final ProjectService projectsService;


    /* 프로젝트명 수정요청 */
    @PutMapping("/projects/api/{id}")
    public Long postsUpdate(@PathVariable Long id, @RequestBody ProjectUpdateRequestDto requestDto) {
        return projectsService.update(id, requestDto);
    }


    /* 프로젝트명 삭제요청 */
    @DeleteMapping("/projects/api/{id}")
    public Long postsDelete(@PathVariable Long id) {
        projectsService.delete(id);
        return id;
    }
}
