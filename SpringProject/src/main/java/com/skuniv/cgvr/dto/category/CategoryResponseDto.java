package com.skuniv.cgvr.dto.category;

import com.skuniv.cgvr.domain.Category;
import lombok.Getter;

import java.time.format.DateTimeFormatter;

@Getter
public class CategoryResponseDto {
    private Long id;
    private String categoryName;

    private String createdDate;
    private String updatedDate;


    public CategoryResponseDto(Category entity) {
        this.id = entity.getId();
        this.categoryName = entity.getCategoryName();

        this.createdDate = entity.getCreatedDate().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss"));
        this.updatedDate = entity.getUpdatedDate().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss"));
    }
}
