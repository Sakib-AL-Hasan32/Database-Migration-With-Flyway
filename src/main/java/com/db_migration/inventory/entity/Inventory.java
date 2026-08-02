package com.db_migration.inventory.entity;

import com.db_migration.common.entity.AuditableEntity;
import com.db_migration.product.entity.Product;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "inventories")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Inventory extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id", nullable = false, unique = true)
    private Product product;

    @Builder.Default
    @Column(nullable = false)
    private Integer totalQuantity = 0;

    @Builder.Default
    @Column(nullable = false)
    private Integer reservedQuantity = 0;

    @Version
    private Long version;

    @Transient
    public Integer getAvailableQuantity() {
        return totalQuantity - reservedQuantity;
    }
}
