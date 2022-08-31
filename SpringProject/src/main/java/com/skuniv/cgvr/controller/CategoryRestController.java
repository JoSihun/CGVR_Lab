package com.skuniv.cgvr.controller;

import com.skuniv.cgvr.dto.category.CategorySaveRequestDto;
import com.skuniv.cgvr.dto.category.CategoryUpdateRequestDto;
import com.skuniv.cgvr.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class CategoryRestController {
    private final CategoryService categoryService;


    /** 카테고리명 작성요청 */
    @PostMapping("/category/api")
    public Long categorySave(@RequestBody CategorySaveRequestDto requestDto) {
        return this.categoryService.save(requestDto);
    }


    /** 카테고리명 수정요청 */
    @PutMapping("/category/api/{id}")
    public Long categoryUpdate(@PathVariable Long id, @RequestBody CategoryUpdateRequestDto requestDto) {
        return this.categoryService.update(id, requestDto);
    }


    /** 카테고리명 삭제요청 */
    @DeleteMapping("/category/api/{id}")
    public Long categoryDelete(@PathVariable Long id) {
        this.categoryService.delete(id);
        return id;
    }
}
