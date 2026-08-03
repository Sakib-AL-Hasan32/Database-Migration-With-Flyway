package com.db_migration.inventory.controller;

import com.db_migration.common.constants.ApiEndpoints;
import com.db_migration.common.response.ApiResponse;
import com.db_migration.common.response.PageResponse;
import com.db_migration.inventory.dto.request.InventoryAdjustRequest;
import com.db_migration.inventory.dto.request.InventoryQuantityRequest;
import com.db_migration.inventory.dto.response.InventoryResponse;
import com.db_migration.inventory.service.InventoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiEndpoints.Inventory.BASE)
public class InventoryController {
    private final InventoryService inventoryService;

    @PostMapping(ApiEndpoints.Inventory.INCREASE)
    public ResponseEntity<ApiResponse<InventoryResponse>> increase(@Valid @RequestBody InventoryQuantityRequest inventoryQuantityRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(inventoryService.increase(inventoryQuantityRequest));
    }

    @PostMapping(ApiEndpoints.Inventory.DECREASE)
    public ResponseEntity<ApiResponse<InventoryResponse>> decrease(@Valid @RequestBody InventoryQuantityRequest inventoryQuantityRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(inventoryService.decrease(inventoryQuantityRequest));
    }

    @PostMapping(ApiEndpoints.Inventory.RESERVE)
    public ResponseEntity<ApiResponse<InventoryResponse>> reserve(@Valid @RequestBody InventoryQuantityRequest inventoryQuantityRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(inventoryService.reserve(inventoryQuantityRequest));
    }

    @PostMapping(ApiEndpoints.Inventory.RELEASE)
    public ResponseEntity<ApiResponse<InventoryResponse>> release(@Valid @RequestBody InventoryQuantityRequest inventoryQuantityRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(inventoryService.release(inventoryQuantityRequest));
    }

    @GetMapping(ApiEndpoints.Inventory.GET_ALL)
    public ResponseEntity<ApiResponse<PageResponse<InventoryResponse>>> getAll(@PageableDefault(size = 5, sort = "id") Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(inventoryService.getAll(pageable));
    }

    @PostMapping(ApiEndpoints.Inventory.ADJUST)
    public ResponseEntity<ApiResponse<InventoryResponse>> adjust(@Valid @RequestBody InventoryAdjustRequest inventoryAdjustRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(inventoryService.adjust(inventoryAdjustRequest));
    }
}
