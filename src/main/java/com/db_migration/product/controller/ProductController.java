package com.db_migration.product.controller;

import com.db_migration.common.constants.ApiEndpoints;
import com.db_migration.common.response.ApiResponse;
import com.db_migration.common.response.PageResponse;
import com.db_migration.product.dto.request.ProductCreateRequest;
import com.db_migration.product.dto.response.ProductResponse;
import com.db_migration.product.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiEndpoints.Product.BASE)
public class ProductController {
    private final ProductService productService;

    @PostMapping(ApiEndpoints.Product.CREATE)
    public ResponseEntity<ApiResponse<ProductResponse>> create(@Valid @RequestBody ProductCreateRequest productCreateRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.create(productCreateRequest));
    }

    @GetMapping(ApiEndpoints.Product.GET_ALL)
    public ResponseEntity<ApiResponse<PageResponse<ProductResponse>>> getAll(@PageableDefault(size = 5, sort = "id") Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.getAll(pageable));
    }
}