package com.db_migration.product.service;

import com.db_migration.common.response.ApiResponse;
import com.db_migration.product.dto.request.CategoryCreateRequest;
import com.db_migration.product.dto.response.CategoryResponse;

public interface CategoryService {
    ApiResponse<CategoryResponse> create(CategoryCreateRequest categoryCreateRequest);
}
