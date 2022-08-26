package com.skuniv.cgvr.dto;

import com.skuniv.cgvr.domain.Projects;
import lombok.Getter;

@Getter
public class ProjectsListResponseDto {
    private Long id;
    private String projectName;


    public ProjectsListResponseDto(Projects entity) {
        this.id = entity.getId();
        this.projectName = entity.getProjectName();
    }
}
