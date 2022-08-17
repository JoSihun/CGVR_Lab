package com.skuniv.cgvr.service;

import com.skuniv.cgvr.domain.Category;
import com.skuniv.cgvr.dto.CategoryDto;
import com.skuniv.cgvr.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoryService {
    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
    @Transactional
    public CategoryDto findById(Long id) {
        Category entity = categoryRepository.findById(id).orElseThrow(()->new IllegalArgumentException("해당 카테고리가 존재하지 않음. id = " + id));
        return new CategoryDto(entity);
    }
}
