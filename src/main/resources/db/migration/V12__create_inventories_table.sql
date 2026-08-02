-- ===========================================
-- CREATE INVENTORIES TABLE
-- ===========================================

CREATE TABLE inventories
(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,

    product_id BIGINT NOT NULL UNIQUE,

    total_quantity INT NOT NULL DEFAULT 0,

    reserved_quantity INT NOT NULL DEFAULT 0,

    version BIGINT NOT NULL DEFAULT 0,

    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
        ON UPDATE CURRENT_TIMESTAMP,

    created_by BIGINT,

    updated_by BIGINT,

    CONSTRAINT fk_inventory_product
        FOREIGN KEY (product_id)
            REFERENCES products(id)
            ON DELETE CASCADE,

    CONSTRAINT fk_inventory_created_by
        FOREIGN KEY (created_by)
            REFERENCES users(id)
            ON DELETE SET NULL,

    CONSTRAINT fk_inventory_updated_by
        FOREIGN KEY (updated_by)
            REFERENCES users(id)
            ON DELETE SET NULL
);