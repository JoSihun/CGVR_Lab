package com.skuniv.cgvr.dto.category;

import com.skuniv.cgvr.domain.Category;
import lombok.Getter;

import java.time.format.DateTimeFormatter;

@Getter
public class CategoryListResponseDto {
    private Long id;
    private String categoryName;
    private String createdDate;
    private String updatedDate;


    public CategoryListResponseDto(Category entity) {
        this.id = entity.getId();
        this.categoryName = entity.getCategoryName();

        this.createdDate = entity.getCreatedDate().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss"));
        this.updatedDate = entity.getUpdatedDate().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss"));
    }
}
