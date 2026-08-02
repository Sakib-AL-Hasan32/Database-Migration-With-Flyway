package com.db_migration.product.service;

import com.db_migration.common.response.ApiResponse;
import com.db_migration.common.response.PageResponse;
import com.db_migration.product.dto.request.ProductCreateRequest;
import com.db_migration.product.dto.request.ProductUpdateRequest;
import com.db_migration.product.dto.response.ProductResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface ProductService {
    ApiResponse<ProductResponse> create(ProductCreateRequest productCreateRequest);
    ApiResponse<PageResponse<ProductResponse>> getAll(Pageable pageable);
    ApiResponse<ProductResponse> update(ProductUpdateRequest productUpdateRequest, Long id);
    ApiResponse<Void> delete(Long id);
}
