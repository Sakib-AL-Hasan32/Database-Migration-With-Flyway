package com.db_migration.product.service.impl;

import com.db_migration.common.constants.ApiMessages;
import com.db_migration.common.constants.PermissionNames;
import com.db_migration.common.exception.ResourceAlreadyExistsException;
import com.db_migration.common.response.ApiResponse;
import com.db_migration.product.dto.request.CategoryCreateRequest;
import com.db_migration.product.dto.response.CategoryResponse;
import com.db_migration.product.entity.Category;
import com.db_migration.product.repository.CategoryRepository;
import com.db_migration.product.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    @PreAuthorize("hasAuthority('" + PermissionNames.CREATE_CATEGORY + "')")
    public ApiResponse<CategoryResponse> create(CategoryCreateRequest categoryCreateRequest) {
        if(categoryRepository.existsByName(categoryCreateRequest.name())) {
            throw new ResourceAlreadyExistsException(ApiMessages.Error.CATEGORY_ALREADY_EXISTS);
        }
        Category category = Category.builder()
                .name(categoryCreateRequest.name())
                .description(categoryCreateRequest.description())
                .build();
        Category saved = categoryRepository.save(category);
        CategoryResponse response = new CategoryResponse(
                saved.getId(),
                saved.getName(),
                saved.getDescription(),
                saved.isActive()
        );
        return ApiResponse.<CategoryResponse>builder()
                .data(response)
                .message(ApiMessages.Success.CATEGORY_CREATED)
                .build();
    }
}
