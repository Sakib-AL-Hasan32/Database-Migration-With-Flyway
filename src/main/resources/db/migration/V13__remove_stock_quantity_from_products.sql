-- ===========================================
-- REMOVE STOCK QUANTITY FROM PRODUCTS
-- ===========================================

ALTER TABLE products
    DROP COLUMN stock_quantity;