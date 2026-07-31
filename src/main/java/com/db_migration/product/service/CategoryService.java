package com.db_migration.product.service;

import com.db_migration.common.response.ApiResponse;
import com.db_migration.common.response.PageResponse;
import com.db_migration.product.dto.request.CategoryCreateRequest;
import com.db_migration.product.dto.request.CategoryUpdateRequest;
import com.db_migration.product.dto.response.CategoryResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;

public interface CategoryService {
    ApiResponse<CategoryResponse> create(CategoryCreateRequest categoryCreateRequest);
    ApiResponse<PageResponse<CategoryResponse>> getAll(Pageable pageable);
    ApiResponse<CategoryResponse> update (CategoryUpdateRequest categoryUpdateRequest, Long id);
    ApiResponse<Void> delete(Long id);
}
