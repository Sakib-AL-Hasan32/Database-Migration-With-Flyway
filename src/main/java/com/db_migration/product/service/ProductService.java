package com.db_migration.product.service;

import com.db_migration.common.response.ApiResponse;
import com.db_migration.product.dto.request.ProductCreateRequest;
import com.db_migration.product.dto.response.ProductResponse;

public interface ProductService {
    ApiResponse<ProductResponse> create(ProductCreateRequest productCreateRequest);
}
