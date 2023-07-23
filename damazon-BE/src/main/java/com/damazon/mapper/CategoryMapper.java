package com.damazon.mapper;

import com.damazon.dto.request.CategoryRequest;
import com.damazon.dto.response.CategoryResponse;
import com.damazon.model.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    Category categoryRequestToCategory(CategoryRequest categoryRequest);

    CategoryResponse categoryToCategoryResponse(Category category);
}