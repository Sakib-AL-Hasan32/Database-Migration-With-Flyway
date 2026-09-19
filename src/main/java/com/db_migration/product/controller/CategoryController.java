package com.db_migration.product.controller;

import com.db_migration.common.constants.ApiEndpoints;
import com.db_migration.common.response.ApiResponse;
import com.db_migration.common.response.PageResponse;
import com.db_migration.product.dto.request.CategoryCreateRequest;
import com.db_migration.product.dto.request.CategoryUpdateRequest;
import com.db_migration.product.dto.response.CategoryResponse;
import com.db_migration.product.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiEndpoints.Category.BASE)
public class CategoryController {
    private final CategoryService categoryService;

    @Operation(
            summary = "Create category",
            description = "Creates a new product category.",
            security = {
                    @SecurityRequirement(name = "bearerAuth")
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    description = "Category creation payload.",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = CategoryCreateRequest.class),
                            examples = @ExampleObject(
                                    name = "Create category",
                                    value = """
                                        {
                                          "name": "Electronics",
                                          "description": "Electronic devices and accessories"
                                        }
                                        """
                            )
                    )
            ),
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "201",
                            description = "Category created successfully",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(
                                            name = "Category created",
                                            value = """
                                                    {
                                                        "data": {
                                                            "id": 1,
                                                            "name": "Electronics",
                                                            "description": "Electronic devices and accessories",
                                                            "active": true,
                                                            "createdAt": "2026-09-20T00:31:34.806753",
                                                            "createdBy": "admin",
                                                            "updatedAt": "2026-09-20T00:31:34.806753",
                                                            "updatedBy": "admin"
                                                        },
                                                        "message": "Category created!"
                                                    }
                                                """
                                    )
                            )
                    ),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "400",
                            description = "Invalid category payload",
                            content = @Content
                    ),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "409",
                            description = "Category already exists",
                            content = @Content
                    )
            }
    )
    @PostMapping(ApiEndpoints.Category.CREATE)
    public ResponseEntity<ApiResponse<CategoryResponse>> create(@Valid @RequestBody CategoryCreateRequest categoryCreateRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.create(categoryCreateRequest));
    }

    @Operation(
            summary = "Get all categories",
            description = "Retrieves a paginated list of all product categories.",
            security = {
                    @SecurityRequirement(name = "bearerAuth")
            },
            parameters = {
                    @Parameter(
                            name = "page",
                            description = "Page number (zero-based).",
                            example = "0"
                    ),
                    @Parameter(
                            name = "size",
                            description = "Number of categories per page.",
                            example = "5"
                    )
            },
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "200",
                            description = "Categories retrieved successfully",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(
                                            name = "Category list",
                                            value = """
                                                    {
                                                        "data": {
                                                            "content": [
                                                                {
                                                                    "id": 1,
                                                                    "name": "Electronics",
                                                                    "description": "Electronic devices and accessories",
                                                                    "active": true,
                                                                    "createdAt": "2026-09-16T23:33:33",
                                                                    "createdBy": "admin",
                                                                    "updatedAt": "2026-09-16T23:33:33",
                                                                    "updatedBy": "admin"
                                                                },
                                                                {
                                                                    "id": 2,
                                                                    "name": "Books",
                                                                    "description": "Books and educational materials",
                                                                    "active": true,
                                                                    "createdAt": "2026-09-20T00:31:35",
                                                                    "createdBy": "admin",
                                                                    "updatedAt": "2026-09-20T00:31:35",
                                                                    "updatedBy": "admin"
                                                                }
                                                            ],
                                                            "page": 1,
                                                            "size": 5,
                                                            "totalElements": 2,
                                                            "totalPages": 1,
                                                            "first": true,
                                                            "last": true,
                                                            "prev": false,
                                                            "next": false
                                                        },
                                                        "message": "Category fetched!"
                                                    }
                                                """
                                    )
                            )
                    ),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "400",
                            description = "Invalid pagination parameters",
                            content = @Content
                    )
            }
    )
    @GetMapping(ApiEndpoints.Category.GET_ALL)
    public ResponseEntity<ApiResponse<PageResponse<CategoryResponse>>> getAll(@Parameter(hidden = true) @PageableDefault(size = 5, sort = "id") Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.getAll(pageable));
    }

    @Operation(
            summary = "Update category",
            description = "Updates an existing category with the provided category details.",
            security = {
                    @SecurityRequirement(name = "bearerAuth")
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    description = "Updated category details.",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = CategoryUpdateRequest.class),
                            examples = @ExampleObject(
                                    name = "Update category",
                                    value = """
                                        {
                                          "name": "Electronics",
                                          "description": "Electronic products and accessories"
                                        }
                                        """
                            )
                    )
            ),
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "200",
                            description = "Category updated successfully",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(
                                            name = "Category created",
                                            value = """
                                                    {
                                                        "data": {
                                                            "id": 1,
                                                            "name": "Electronics",
                                                            "description": "Electronic devices and accessories",
                                                            "active": true,
                                                            "createdAt": "2026-09-20T00:31:34.806753",
                                                            "createdBy": "admin",
                                                            "updatedAt": "2026-09-20T00:31:34.806753",
                                                            "updatedBy": "admin"
                                                        },
                                                        "message": "Category updated!"
                                                    }
                                                """
                                    )
                            )
                    ),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "400",
                            description = "Invalid category update request",
                            content = @Content
                    ),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized or invalid access token",
                            content = @Content
                    ),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "404",
                            description = "Category not found",
                            content = @Content
                    )
            }
    )
    @PutMapping(ApiEndpoints.Category.UPDATE)
    public ResponseEntity<ApiResponse<CategoryResponse>> update(@Valid @RequestBody CategoryUpdateRequest categoryUpdateRequest, @PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.update(categoryUpdateRequest, id));
    }

    @Operation(
            summary = "Delete category",
            description = "Deletes an existing category by its ID.",
            security = {
                    @SecurityRequirement(name = "bearerAuth")
            },
            parameters = @Parameter(
                    name = "id",
                    description = "Category ID",
                    example = "1"
            ),
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "200",
                            description = "Category deleted successfully",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(
                                            name = "Category Deleted",
                                            value = """
                                                    {
                                                        "message": "Category deleted!"
                                                    }
                                                    """
                                    )
                            )
                    ),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized or invalid access token",
                            content = @Content
                    ),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "404",
                            description = "Category not found",
                            content = @Content
                    )
            }
    )
    @DeleteMapping(ApiEndpoints.Category.DELETE)
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.delete(id));
    }
}
