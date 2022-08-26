package com.skuniv.cgvr.dto;

import com.skuniv.cgvr.domain.Projects;
import lombok.Getter;

@Getter
public class ProjectsResponseDto {
    private Long id;
    private String projectName;

    public ProjectsResponseDto(Projects entity) {
        this.id = entity.getId();
        this.projectName = entity.getProjectName();
    }
}
