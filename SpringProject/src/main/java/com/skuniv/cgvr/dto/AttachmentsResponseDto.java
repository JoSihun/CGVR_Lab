package com.skuniv.cgvr.dto;

import com.skuniv.cgvr.domain.Attachments;
import lombok.Getter;

import java.time.format.DateTimeFormatter;

@Getter
public class AttachmentsResponseDto {
    private Long id;
    private Long fileSize;
    private String fileName;
    private String filePath;
    private String createdDate;
    private String updatedDate;

    public AttachmentsResponseDto(Attachments entity) {
        this.id = entity.getId();
        this.fileSize = entity.getFileSize();
        this.fileName = entity.getFileName();
        this.filePath = entity.getFilePath();
        this.createdDate = entity.getCreatedDate().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));
        this.updatedDate = entity.getUpdatedDate().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));
    }
}
