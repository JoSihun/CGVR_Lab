package com.skuniv.cgvr.dto.category;

import com.skuniv.cgvr.domain.Category;
import lombok.Data;

@Data
public class CategoryUpdateRequestDto {
    private String categoryName;


    public Category toEntity() {
        return Category.builder()
                .categoryName(categoryName)
                .build();
    }
}
