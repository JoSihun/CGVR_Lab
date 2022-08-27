package com.skuniv.cgvr.dto.project;

import com.skuniv.cgvr.domain.Project;
import lombok.Data;

@Data
public class ProjectUpdateRequestDto {
    private String projectName;


    public Project toEntity() {
        return Project.builder()
                .projectName(projectName)
                .build();
    }
}
