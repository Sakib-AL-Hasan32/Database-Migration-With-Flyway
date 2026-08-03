-- ===========================================
-- ADD ADD_ITEM_TO_CART PERMISSION
-- ===========================================

INSERT INTO permissions (name)
VALUES ('ADD_ITEM_TO_CART');

-- ===========================================
-- ASSIGN PERMISSION TO CUSTOMER ROLE
-- ===========================================

INSERT INTO role_permissions (role_id, permission_id)
SELECT
    r.id,
    p.id
FROM roles r
         JOIN permissions p
              ON p.name = 'ADD_ITEM_TO_CART'
WHERE r.name = 'CUSTOMER';