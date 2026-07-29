package com.db_migration.product.service.impl;

import com.db_migration.auth.entity.User;
import com.db_migration.auth.repository.UserRepository;
import com.db_migration.common.constants.ApiMessages;
import com.db_migration.common.constants.PermissionNames;
import com.db_migration.common.exception.ResourceAlreadyExistsException;
import com.db_migration.common.response.ApiResponse;
import com.db_migration.common.response.PageResponse;
import com.db_migration.product.dto.request.CategoryCreateRequest;
import com.db_migration.product.dto.response.CategoryResponse;
import com.db_migration.product.entity.Category;
import com.db_migration.product.repository.CategoryRepository;
import com.db_migration.product.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

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
                saved.isActive(),
                saved.getCreatedAt(),
                saved.getCreatedBy().getUsername(),
                saved.getUpdatedAt(),
                saved.getUpdatedBy().getUsername()
        );
        return ApiResponse.<CategoryResponse>builder()
                .data(response)
                .message(ApiMessages.Success.CATEGORY_CREATED)
                .build();
    }

    @Override
    public ApiResponse<PageResponse<CategoryResponse>> getAll(Pageable pageable) {
        Page<Category> page = categoryRepository.findAll(pageable);
        List<CategoryResponse> responses = new ArrayList<>();

        for(Category category : page.getContent()) {
            CategoryResponse response = new CategoryResponse(
                    category.getId(),
                    category.getName(),
                    category.getDescription(),
                    category.isActive(),
                    category.getCreatedAt(),
                    category.getCreatedBy().getUsername(),
                    category.getUpdatedAt(),
                    category.getUpdatedBy().getUsername()
            );
        }
        return null;
    }
}
