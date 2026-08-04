-- ===========================================
-- ADD REMOVE_ITEM_FROM_CART PERMISSION
-- ===========================================

INSERT INTO permissions (name)
VALUES ('REMOVE_ITEM_FROM_CART');

-- ===========================================
-- ASSIGN PERMISSION TO CUSTOMER ROLE
-- ===========================================

INSERT INTO role_permissions (role_id, permission_id)
SELECT
    r.id,
    p.id
FROM roles r
         JOIN permissions p
              ON p.name = 'REMOVE_ITEM_FROM_CART'
WHERE r.name = 'CUSTOMER';