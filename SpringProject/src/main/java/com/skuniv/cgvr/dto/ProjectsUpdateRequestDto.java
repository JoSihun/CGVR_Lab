package com.skuniv.cgvr.dto;

import com.skuniv.cgvr.domain.Projects;
import lombok.Data;

@Data
public class ProjectsUpdateRequestDto {
    private String projectName;


    public Projects toEntity() {
        return Projects.builder()
                .projectName(projectName)
                .build();
    }
}
