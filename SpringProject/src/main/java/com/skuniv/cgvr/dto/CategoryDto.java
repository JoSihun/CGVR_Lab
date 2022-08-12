package com.skuniv.cgvr.dto;

import com.skuniv.cgvr.domain.Category;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class CategoryDto {
    private Long id;
    private String content;

    public CategoryDto(Category entity) {
        this.id = entity.getId();
        this.content = entity.getContent();
    }
}
