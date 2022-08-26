package com.skuniv.cgvr.dto;

import com.skuniv.cgvr.domain.Projects;
import lombok.*;

@Data
public class ProjectsSaveRequestDto {
    private String projectName;


    public Projects toEntity() {
        return Projects.builder()
                .projectName(projectName)
                .build();
    }
}
