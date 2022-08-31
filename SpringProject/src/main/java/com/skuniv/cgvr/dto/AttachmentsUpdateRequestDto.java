package com.skuniv.cgvr.dto;

import com.skuniv.cgvr.domain.Attachments;
import com.skuniv.cgvr.domain.posts.Posts;
import lombok.Data;

@Data
public class AttachmentsUpdateRequestDto {
    private String fileName;
    private String filePath;
    private Long fileSize;


    public Attachments toEntity() {
        return Attachments.builder()
                .fileName(fileName)
                .filePath(filePath)
                .fileSize(fileSize)
                .build();
    }
}
