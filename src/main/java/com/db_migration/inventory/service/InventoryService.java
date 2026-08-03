package com.db_migration.inventory.service;

import com.db_migration.common.response.ApiResponse;
import com.db_migration.inventory.dto.request.InventoryQuantityRequest;
import com.db_migration.inventory.dto.response.InventoryResponse;

public interface InventoryService {
    ApiResponse<InventoryResponse> increase(InventoryQuantityRequest inventoryQuantityRequest);
    ApiResponse<InventoryResponse> decrease(InventoryQuantityRequest inventoryQuantityRequest);
    ApiResponse<InventoryResponse> reserve(InventoryQuantityRequest inventoryQuantityRequest);
    ApiResponse<InventoryResponse> release(InventoryQuantityRequest inventoryQuantityRequest);
}
