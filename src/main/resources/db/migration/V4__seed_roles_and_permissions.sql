-- ============================
-- PERMISSIONS
-- ============================

INSERT INTO permissions (name)
VALUES ('CREATE_PRODUCT'),
       ('UPDATE_PRODUCT'),
       ('DELETE_PRODUCT'),
       ('VIEW_PRODUCT'),

       ('CREATE_CATEGORY'),
       ('UPDATE_CATEGORY'),
       ('DELETE_CATEGORY'),
       ('VIEW_CATEGORY'),

       ('CREATE_CART'),
       ('UPDATE_CART'),
       ('DELETE_CART'),
       ('VIEW_CART'),

       ('CREATE_ORDER'),
       ('UPDATE_ORDER'),
       ('DELETE_ORDER'),
       ('VIEW_ORDER'),

       ('MANAGE_INVENTORY'),
       ('VIEW_INVENTORY');



-- ============================
-- ROLES
-- ============================

INSERT INTO roles (name)
VALUES ('ADMIN'),
       ('USER');



-- ============================
-- ADMIN PERMISSIONS
-- ============================

INSERT INTO role_permissions (role_id, permission_id)
SELECT
    (SELECT id FROM roles WHERE name = 'ADMIN'),
    id
FROM permissions;



-- ============================
-- USER PERMISSIONS
-- ============================

INSERT INTO role_permissions (role_id, permission_id)
SELECT
    (SELECT id FROM roles WHERE name = 'USER'),
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
               'VIEW_ORDER',

               'VIEW_INVENTORY'
);