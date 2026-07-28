-- ===========================================
-- CUSTOMER
-- ===========================================

INSERT INTO role_permissions (role_id, permission_id)
SELECT
    (SELECT id FROM roles WHERE name = 'CUSTOMER'),
    id
FROM permissions
WHERE name IN (
               'VIEW_PRODUCT',
               'VIEW_CATEGORY',

               'CREATE_CART',
               'UPDATE_CART',
               'DELETE_CART',
               'VIEW_CART',

               'CREATE_ORDER',
               'VIEW_ORDER'
    );



-- ===========================================
-- PRODUCT MANAGER
-- ===========================================

INSERT INTO role_permissions (role_id, permission_id)
SELECT
    (SELECT id FROM roles WHERE name = 'PRODUCT_MANAGER'),
    id
FROM permissions
WHERE name IN (
               'CREATE_PRODUCT',
               'UPDATE_PRODUCT',
               'DELETE_PRODUCT',
               'VIEW_PRODUCT',

               'CREATE_CATEGORY',
               'UPDATE_CATEGORY',
               'DELETE_CATEGORY',
               'VIEW_CATEGORY'
    );



-- ===========================================
-- INVENTORY MANAGER
-- ===========================================

INSERT INTO role_permissions (role_id, permission_id)
SELECT
    (SELECT id FROM roles WHERE name = 'INVENTORY_MANAGER'),
    id
FROM permissions
WHERE name IN (
               'MANAGE_INVENTORY',
               'VIEW_INVENTORY',

               'VIEW_PRODUCT',
               'VIEW_CATEGORY'
    );



-- ===========================================
-- ORDER MANAGER
-- ===========================================

INSERT INTO role_permissions (role_id, permission_id)
SELECT
    (SELECT id FROM roles WHERE name = 'ORDER_MANAGER'),
    id
FROM permissions
WHERE name IN (
               'VIEW_ORDER',
               'UPDATE_ORDER',
               'DELETE_ORDER'
    );