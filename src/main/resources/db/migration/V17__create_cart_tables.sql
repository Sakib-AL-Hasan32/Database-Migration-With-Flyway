-- ===========================================
-- CREATE CARTS TABLE
-- ===========================================

CREATE TABLE carts
(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,

    user_id BIGINT NOT NULL UNIQUE,

    created_at DATETIME NOT NULL,
    updated_at DATETIME NOT NULL,

    created_by BIGINT,
    updated_by BIGINT,

    CONSTRAINT fk_cart_user
        FOREIGN KEY (user_id)
            REFERENCES users(id)
            ON DELETE CASCADE,

    CONSTRAINT fk_cart_created_by
        FOREIGN KEY (created_by)
            REFERENCES users(id)
            ON DELETE SET NULL,

    CONSTRAINT fk_cart_updated_by
        FOREIGN KEY (updated_by)
            REFERENCES users(id)
            ON DELETE SET NULL
);

-- ===========================================
-- CREATE CART ITEMS TABLE
-- ===========================================

CREATE TABLE cart_items
(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,

    cart_id BIGINT NOT NULL,

    product_id BIGINT NOT NULL,

    quantity INT NOT NULL,

    created_at DATETIME NOT NULL,
    updated_at DATETIME NOT NULL,

    created_by BIGINT,
    updated_by BIGINT,

    CONSTRAINT uq_cart_product
        UNIQUE (cart_id, product_id),

    CONSTRAINT fk_cart_item_cart
        FOREIGN KEY (cart_id)
            REFERENCES carts(id)
            ON DELETE CASCADE,

    CONSTRAINT fk_cart_item_product
        FOREIGN KEY (product_id)
            REFERENCES products(id)
            ON DELETE CASCADE,

    CONSTRAINT fk_cart_item_created_by
        FOREIGN KEY (created_by)
            REFERENCES users(id)
            ON DELETE SET NULL,

    CONSTRAINT fk_cart_item_updated_by
        FOREIGN KEY (updated_by)
            REFERENCES users(id)
            ON DELETE SET NULL
);