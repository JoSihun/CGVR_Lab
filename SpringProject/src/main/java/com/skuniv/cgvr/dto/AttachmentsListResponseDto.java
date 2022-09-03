package com.skuniv.cgvr.dto;

import com.skuniv.cgvr.domain.Attachments;
import lombok.Getter;

import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;

@Getter
public class AttachmentsListResponseDto {
    private Long id;
    private String fileName;
    private String filePath;
    private String fileSize;
    private String createdDate;
    private String updatedDate;

    public AttachmentsListResponseDto(Attachments entity) {
        this.id = entity.getId();
        this.fileName = entity.getFileName();
        this.filePath = entity.getFilePath();
        this.fileSize = fileSizeFormatting(entity.getFileSize());
        this.createdDate = entity.getCreatedDate().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));
        this.updatedDate = entity.getUpdatedDate().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));
    }

    public String fileSizeFormatting(Long fileSize) {
        String result = "";
        DecimalFormat decimalFormat = new DecimalFormat("###,###.##");
        if (fileSize < 1024) {
            result = decimalFormat.format(fileSize) + " Bytes";
        } else if (fileSize < 1024 * 1024) {
            result = decimalFormat.format((double) fileSize / 1024) + " KBytes";
        } else if (fileSize < 1024 * 1024 * 1024) {
            result = decimalFormat.format((double) fileSize / 1024 / 1024) + " MBytes";
        } else if (fileSize < 1024 * 1024 * 1024 * 1024) {
            result = decimalFormat.format((double) fileSize / 1024 / 1024 / 1024) + " GBytes";
        }
        return result;
    }
}
