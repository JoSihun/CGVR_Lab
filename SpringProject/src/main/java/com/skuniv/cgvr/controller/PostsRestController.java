package com.skuniv.cgvr.controller;

import com.skuniv.cgvr.dto.ProjectsSaveRequestDto;
import com.skuniv.cgvr.dto.posts.PostsSaveRequestDto;
import com.skuniv.cgvr.dto.posts.PostsUpdateRequestDto;
import com.skuniv.cgvr.service.ProjectsService;
import com.skuniv.cgvr.service.posts.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PostsRestController {
    private final PostsService postsService;
    private final ProjectsService projectsService;

    /* 게시글 작성요청 */
    @PostMapping("/posts/api")
    public Long postsSave(@RequestBody PostsSaveRequestDto requestDto) {
        ProjectsSaveRequestDto projectsSaveRequestDto = new ProjectsSaveRequestDto();
        System.out.println(projectsService.findByProjectName(requestDto.getProjectName()));
        if (projectsService.findByProjectName(requestDto.getProjectName()) == null) {
            projectsSaveRequestDto.setProjectName(requestDto.getProjectName());
            projectsService.save(projectsSaveRequestDto);
        }
        return postsService.save(requestDto);
    }

    /* 게시글 수정요청 */
    @PutMapping("/posts/api/{id}")
    public Long postsUpdate(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto) {
        return postsService.update(id, requestDto);
    }

    /* 게시글 삭제요청 */
    @DeleteMapping("/posts/api/{id}")
    public Long postsDelete(@PathVariable Long id) {
        postsService.delete(id);
        return id;
    }
}
