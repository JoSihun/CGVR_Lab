package com.skuniv.cgvr.service;

import com.skuniv.cgvr.domain.Category;
import com.skuniv.cgvr.dto.category.CategoryListResponseDto;
import com.skuniv.cgvr.dto.category.CategoryResponseDto;
import com.skuniv.cgvr.dto.category.CategorySaveRequestDto;
import com.skuniv.cgvr.dto.category.CategoryUpdateRequestDto;
import com.skuniv.cgvr.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    /** 전체 카테고리명 목록보기 - 작성순 */
    @Transactional
    public List<CategoryListResponseDto> findAllAsc() {
        Sort sort = Sort.by(Sort.Direction.ASC, "id");
        List<Category> categoryList = this.categoryRepository.findAll(sort);
        return categoryList.stream().map(CategoryListResponseDto::new).collect(Collectors.toList());
    }


    /** 카테고리명 목록보기 - 최신순 */
    @Transactional
    public List<CategoryListResponseDto> findAllDesc() {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        List<Category> categoryList = this.categoryRepository.findAll(sort);
        return categoryList.stream().map(CategoryListResponseDto::new).collect(Collectors.toList());
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /** 카테고리명 단일보기 - ID 접근 */
    @Transactional
    public CategoryResponseDto findById(final Long id) {
        Category entity = this.categoryRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 카테고리가 존재하지 않습니다. id=" + id));
        return new CategoryResponseDto(entity);
    }


    /** 카테고리명 단일보기 - 카테고리명 접근 */
    @Transactional
    public CategoryResponseDto findByCategoryName(final String categoryName) {
        Category entity = this.categoryRepository.findByCategoryName(categoryName);
        return new CategoryResponseDto(entity);
    }


    /** 카테고리명 저장하기 */
    @Transactional
    public Long save(final CategorySaveRequestDto requestDto) {
        return this.categoryRepository.save(requestDto.toEntity()).getId();
    }


    /** 카테고리명 수정하기 */
    @Transactional
    public Long update(final Long id, final CategoryUpdateRequestDto requestDto) {
        Category entity = this.categoryRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 카테고리명이 존재하지 않습니다. id=" + id));
        entity.update(requestDto.getCategoryName());
        return id;
    }


    /** 카테고리명 삭제하기 */
    public Long delete(final Long id) {
        Category entity = this.categoryRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("이미 존재하지 않는 카테고리명입니다. id=" + id));
        this.categoryRepository.delete(entity);
        return id;
    }
}
