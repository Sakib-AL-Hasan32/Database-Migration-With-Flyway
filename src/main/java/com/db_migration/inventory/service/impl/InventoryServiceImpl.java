package com.db_migration.inventory.service.impl;

import com.db_migration.common.constants.ApiMessages;
import com.db_migration.common.constants.PermissionNames;
import com.db_migration.common.exception.ResourceNotFound;
import com.db_migration.common.response.ApiResponse;
import com.db_migration.inventory.dto.request.InventoryQuantityRequest;
import com.db_migration.inventory.dto.response.InventoryResponse;
import com.db_migration.inventory.entity.Inventory;
import com.db_migration.inventory.repository.InventoryRepository;
import com.db_migration.inventory.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {
    private final InventoryRepository inventoryRepository;

    @Override
    @PreAuthorize("hasAuthority('" + PermissionNames.INCREASE_INVENTORY + "')")
    public ApiResponse<InventoryResponse> increase(InventoryQuantityRequest inventoryQuantityRequest) {
        Inventory inventory = inventoryRepository.findByProductId(inventoryQuantityRequest.productId()).orElseThrow(() -> new ResourceNotFound(ApiMessages.Error.INVENTORY_NOT_FOUND));

        inventory.setTotalQuantity(inventoryQuantityRequest.quantity());
        Inventory saved = inventoryRepository.save(inventory);

        InventoryResponse response = mapToResponse(saved);
        return ApiResponse.<InventoryResponse>builder()
                .data(response)
                .message(ApiMessages.Success.INVENTORY_INCREASED)
                .build();
    }

    private InventoryResponse mapToResponse(Inventory inventory) {
        return new InventoryResponse(
                inventory.getId(),
                inventory.getProduct().getId(),
                inventory.getProduct().getName(),
                inventory.getTotalQuantity(),
                inventory.getReservedQuantity(),
                inventory.getAvailableQuantity(),
                inventory.getCreatedAt(),
                inventory.getCreatedBy().getUsername(),
                inventory.getUpdatedAt(),
                inventory.getUpdatedBy().getUsername()
        );
    }
}
