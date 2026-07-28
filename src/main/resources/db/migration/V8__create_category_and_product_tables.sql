-- =====================================
-- CATEGORIES
-- =====================================

CREATE TABLE categories
(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,

    name VARCHAR(100) NOT NULL UNIQUE,

    description VARCHAR(300),

    active BOOLEAN NOT NULL DEFAULT TRUE
);



-- =====================================
-- PRODUCTS
-- =====================================

CREATE TABLE products
(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,

    name VARCHAR(150) NOT NULL UNIQUE,

    description VARCHAR(1000),

    price DECIMAL(10,2) NOT NULL,

    stock_quantity INT NOT NULL,

    sku VARCHAR(50) NOT NULL UNIQUE,

    active BOOLEAN NOT NULL DEFAULT TRUE,

    category_id BIGINT NOT NULL,

    CONSTRAINT fk_product_category
        FOREIGN KEY (category_id)
            REFERENCES categories(id)
);