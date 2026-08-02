package com.db_migration.product.service.impl;

import com.db_migration.common.constants.ApiMessages;
import com.db_migration.common.constants.PermissionNames;
import com.db_migration.common.exception.ResourceAlreadyExistsException;
import com.db_migration.common.exception.ResourceNotFound;
import com.db_migration.common.response.ApiResponse;
import com.db_migration.common.response.PageResponse;
import com.db_migration.product.dto.request.ProductCreateRequest;
import com.db_migration.product.dto.response.ProductResponse;
import com.db_migration.product.entity.Category;
import com.db_migration.product.entity.Product;
import com.db_migration.product.repository.CategoryRepository;
import com.db_migration.product.repository.ProductRepository;
import com.db_migration.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Override
    @PreAuthorize("hasAuthority('" + PermissionNames.CREATE_PRODUCT + "')")
    public ApiResponse<ProductResponse> create(ProductCreateRequest productCreateRequest) {
        if(productRepository.existsBySku(productCreateRequest.sku())) {
            throw new ResourceAlreadyExistsException(ApiMessages.Error.PRODUCT_ALREADY_EXISTS);
        }
        Category category = categoryRepository.findById(productCreateRequest.categoryId()).orElseThrow(() -> new ResourceNotFound(ApiMessages.Error.CATEGORY_NOT_FOUND));
        Product product = Product.builder()
                .name(productCreateRequest.name())
                .description(productCreateRequest.description())
                .price(productCreateRequest.price())
                .stockQuantity(productCreateRequest.stockQuantity())
                .sku(productCreateRequest.sku())
                .active(productCreateRequest.active())
                .category(category)
                .build();
        Product saved = productRepository.save(product);
        ProductResponse response = new ProductResponse(
                saved.getId(),
                saved.getName(),
                saved.getDescription(),
                saved.getPrice(),
                saved.getStockQuantity(),
                saved.getSku(),
                saved.isActive(),
                category.getName()
        );
        return ApiResponse.<ProductResponse>builder()
                .data(response)
                .message(ApiMessages.Success.PRODUCT_CREATED)
                .build();
    }

    @Override
    @PreAuthorize("hasAuthority('" + PermissionNames.VIEW_PRODUCT + "')")
    public ApiResponse<PageResponse<ProductResponse>> getAll(Pageable pageable) {
        Page<Product> page = productRepository.findAll(pageable);
        List<ProductResponse> responses = new ArrayList<>();

        for(Product product : page.getContent()) {
            ProductResponse productResponses = new ProductResponse(
                    product.getId(),
                    product.getName(),
                    product.getDescription(),
                    product.getPrice(),
                    product.getStockQuantity(),
                    product.getSku(),
                    product.isActive(),
                    product.getCategory().getName()
            );
            responses.add(productResponses);
        }
        PageResponse<ProductResponse> pageResponse = PageResponse.<ProductResponse>builder()
                .content(responses)
                .page(page.getTotalPages())
                .size(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .first(page.isFirst())
                .last(page.isLast())
                .prev(page.hasPrevious())
                .next(page.hasNext())
                .build();
        return ApiResponse.<PageResponse<ProductResponse>>builder()
                .data(pageResponse)
                .message(ApiMessages.Success.PRODUCT_FETCHED)
                .build();
    }
}