-- ============================
-- REMOVE OLD INVENTORY PERMISSION
-- ============================

DELETE FROM role_permissions
WHERE permission_id = (
    SELECT id
    FROM permissions
    WHERE name = 'MANAGE_INVENTORY'
);

DELETE FROM permissions
WHERE name = 'MANAGE_INVENTORY';



-- ============================
-- ADD NEW INVENTORY PERMISSIONS
-- ============================

INSERT INTO permissions (name)
VALUES ('CREATE_INVENTORY'),
       ('INCREASE_INVENTORY'),
       ('DECREASE_INVENTORY'),
       ('RESERVE_INVENTORY'),
       ('RELEASE_INVENTORY'),
       ('ADJUST_INVENTORY');



-- ============================
-- ASSIGN TO ADMIN
-- ============================

INSERT IGNORE INTO role_permissions (role_id, permission_id)
SELECT
    (SELECT id FROM roles WHERE name = 'ADMIN'),
    id
FROM permissions
WHERE name IN (
               'VIEW_INVENTORY',
               'CREATE_INVENTORY',
               'INCREASE_INVENTORY',
               'DECREASE_INVENTORY',
               'RESERVE_INVENTORY',
               'RELEASE_INVENTORY',
               'ADJUST_INVENTORY'
    );



-- ===========================================
-- ASSIGN PERMISSIONS TO INVENTORY_MANAGER
-- ===========================================

INSERT IGNORE INTO role_permissions (role_id, permission_id)
SELECT
    (SELECT id FROM roles WHERE name = 'INVENTORY_MANAGER'),
    id
FROM permissions
WHERE name IN (
               'VIEW_INVENTORY',
               'CREATE_INVENTORY',
               'INCREASE_INVENTORY',
               'DECREASE_INVENTORY',
               'RESERVE_INVENTORY',
               'RELEASE_INVENTORY',
               'ADJUST_INVENTORY'
    );