-- ===========================================
-- CREATE ORDERS TABLE
-- ===========================================

CREATE TABLE orders
(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,

    order_number VARCHAR(50) NOT NULL UNIQUE,

    user_id BIGINT NOT NULL,

    status VARCHAR(30) NOT NULL,

    total_amount DECIMAL(12, 2) NOT NULL,

    shipping_address VARCHAR(500) NOT NULL,

    payment_method VARCHAR(30) NOT NULL,

    created_at DATETIME NOT NULL,
    updated_at DATETIME NOT NULL,

    created_by BIGINT,
    updated_by BIGINT,

    CONSTRAINT fk_order_user
        FOREIGN KEY (user_id)
            REFERENCES users(id)
            ON DELETE RESTRICT,

    CONSTRAINT fk_order_created_by
        FOREIGN KEY (created_by)
            REFERENCES users(id)
            ON DELETE SET NULL,

    CONSTRAINT fk_order_updated_by
        FOREIGN KEY (updated_by)
            REFERENCES users(id)
            ON DELETE SET NULL
);

-- ===========================================
-- CREATE ORDER ITEMS TABLE
-- ===========================================

CREATE TABLE order_items
(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,

    order_id BIGINT NOT NULL,

    product_id BIGINT NOT NULL,

    quantity INT NOT NULL,

    unit_price DECIMAL(10, 2) NOT NULL,

    created_at DATETIME NOT NULL,
    updated_at DATETIME NOT NULL,

    created_by BIGINT,
    updated_by BIGINT,

    CONSTRAINT fk_order_item_order
        FOREIGN KEY (order_id)
            REFERENCES orders(id)
            ON DELETE CASCADE,

    CONSTRAINT fk_order_item_product
        FOREIGN KEY (product_id)
            REFERENCES products(id)
            ON DELETE RESTRICT,

    CONSTRAINT fk_order_item_created_by
        FOREIGN KEY (created_by)
            REFERENCES users(id)
            ON DELETE SET NULL,

    CONSTRAINT fk_order_item_updated_by
        FOREIGN KEY (updated_by)
            REFERENCES users(id)
            ON DELETE SET NULL
);