package com.skuniv.cgvr.dto;

import com.skuniv.cgvr.domain.Attachments;
import lombok.Getter;

import java.time.format.DateTimeFormatter;

@Getter
public class AttachmentsListResponseDto {
    private Long id;
    private String fileName;
    private String filePath;
    private Long fileSize;
    private String createdDate;
    private String updatedDate;

    public AttachmentsListResponseDto(Attachments entity) {
        this.id = entity.getId();
        this.fileName = entity.getFileName();
        this.filePath = entity.getFilePath();
        this.fileSize = entity.getFileSize();
        this.createdDate = entity.getCreatedDate().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));
        this.updatedDate = entity.getUpdatedDate().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));
    }
}
