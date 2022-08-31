package com.skuniv.cgvr.controller;

import com.skuniv.cgvr.dto.posts.PostsSaveRequestDto;
import com.skuniv.cgvr.dto.project.ProjectResponseDto;
import com.skuniv.cgvr.dto.project.ProjectSaveRequestDto;
import com.skuniv.cgvr.dto.project.ProjectUpdateRequestDto;
import com.skuniv.cgvr.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class ProjectsRestController {
    private final ProjectService projectsService;


    /* 프로젝트명 작성요청 */
    @PostMapping("/project/api")
    public Long projectSave(@RequestBody ProjectSaveRequestDto requestDto) {
        return this.projectsService.save(requestDto);
    }


    /* 프로젝트명 수정요청 */
    @PutMapping("/project/api/{id}")
    public Long projectUpdate(@PathVariable Long id, @RequestBody ProjectUpdateRequestDto requestDto) {
        return this.projectsService.update(id, requestDto);
    }


    /* 프로젝트명 삭제요청 */
    @DeleteMapping("/project/api/{id}")
    public Long projectDelete(@PathVariable Long id) {
        this.projectsService.delete(id);
        return id;
    }
}
