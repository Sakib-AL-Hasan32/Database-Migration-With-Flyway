package com.db_migration.product.controller;

import com.db_migration.common.constants.ApiEndpoints;
import com.db_migration.common.response.ApiResponse;
import com.db_migration.common.response.PageResponse;
import com.db_migration.product.dto.request.CategoryCreateRequest;
import com.db_migration.product.dto.request.CategoryUpdateRequest;
import com.db_migration.product.dto.response.CategoryResponse;
import com.db_migration.product.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiEndpoints.Category.BASE)
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping(ApiEndpoints.Category.CREATE)
    public ResponseEntity<ApiResponse<CategoryResponse>> create(@Valid @RequestBody CategoryCreateRequest categoryCreateRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.create(categoryCreateRequest));
    }

    @GetMapping(ApiEndpoints.Category.GET_ALL)
    public ResponseEntity<ApiResponse<PageResponse<CategoryResponse>>> getAll(@PageableDefault(size = 5, sort = "id") Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.getAll(pageable));
    }

    @PutMapping(ApiEndpoints.Category.UPDATE)
    public ResponseEntity<ApiResponse<CategoryResponse>> update(@Valid @RequestBody CategoryUpdateRequest categoryUpdateRequest, @PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.update(categoryUpdateRequest, id));
    }
}
