package com.skuniv.cgvr.dto.project;

import com.skuniv.cgvr.domain.Project;
import lombok.*;

@Data
public class ProjectSaveRequestDto {
    private String projectName;


    public Project toEntity() {
        return Project.builder()
                .projectName(projectName)
                .build();
    }
}
