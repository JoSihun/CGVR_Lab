package com.skuniv.cgvr.dto.project;

import com.skuniv.cgvr.domain.Project;
import lombok.Getter;

import java.time.format.DateTimeFormatter;

@Getter
public class ProjectListResponseDto {
    private Long id;
    private String projectName;
    private String createdDate;
    private String updatedDate;


    public ProjectListResponseDto(Project entity) {
        this.id = entity.getId();
        this.projectName = entity.getProjectName();

        this.createdDate = entity.getCreatedDate().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss"));
        this.updatedDate = entity.getUpdatedDate().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss"));
    }
}
